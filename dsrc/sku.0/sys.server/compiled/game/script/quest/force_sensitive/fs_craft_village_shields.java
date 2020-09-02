package script.quest.force_sensitive;

import script.dictionary;
import script.library.community_crafting;
import script.library.fs_dyn_village;
import script.library.quests;
import script.library.utils;
import script.obj_id;

public class fs_craft_village_shields extends script.base_script
{
    public fs_craft_village_shields()
    {
    }
    public static final String QUEST_MAIN = "fs_phase_3_craft_shields_main";
    public static final String QUEST_STEP_01 = "fs_phase_3_craft_shields_01";
    public static final String QUEST_STEP_02 = "fs_phase_3_craft_shields_02";
    public int OnLogin(obj_id self) throws InterruptedException
    {
        utils.removeScriptVar(self, fs_dyn_village.OBJVAR_MY_MASTER_OBJECT);
        utils.removeScriptVar(self, fs_dyn_village.OBJVAR_CURRENT_PHASE);
        requestVillageMaster(self);
        requestVillagePhase(self);
        return SCRIPT_CONTINUE;
    }
    public int handleRetryGetVillageManager(obj_id self, dictionary params) throws InterruptedException
    {
        requestVillageMaster(self);
        return SCRIPT_CONTINUE;
    }
    public int handleRetryGetVillagePhase(obj_id self, dictionary params) throws InterruptedException
    {
        requestVillagePhase(self);
        return SCRIPT_CONTINUE;
    }
    public int handleGetVillageManager(obj_id self, dictionary params) throws InterruptedException
    {
        boolean rslt = false;
        String key = "";
        if (params.containsKey("key"))
        {
            key = params.getString("key");
        }
        if (params.containsKey("success"))
        {
            rslt = params.getBoolean("success");
        }
        if (rslt && key != null && key.length() > 0)
        {
            obj_id id = null;
            if (params.containsKey(key))
            {
                id = params.getObjId(key);
                if (isIdValid(id))
                {
                    utils.setScriptVar(self, fs_dyn_village.OBJVAR_MY_MASTER_OBJECT, id);
                    checkVillagePhase(self);
                    return SCRIPT_CONTINUE;
                }
            }
        }
        messageTo(self, "handleRetryGetVillageManager", null, 30, false);
        return SCRIPT_CONTINUE;
    }
    public int handleGetVillagePhase(obj_id self, dictionary params) throws InterruptedException
    {
        boolean rslt = false;
        String key = "";
        if (params.containsKey("key"))
        {
            key = params.getString("key");
        }
        if (params.containsKey("success"))
        {
            rslt = params.getBoolean("success");
        }
        if (rslt && key != null && key.length() > 0)
        {
            if (params.containsKey(key))
            {
                int phase = params.getInt(key);
                if (phase >= 1)
                {
                    utils.setScriptVar(self, fs_dyn_village.OBJVAR_CURRENT_PHASE, phase);
                    checkVillagePhase(self);
                    return SCRIPT_CONTINUE;
                }
            }
        }
        messageTo(self, "handleRetryGetVillagePhase", null, 30, false);
        return SCRIPT_CONTINUE;
    }
    public void requestVillageMaster(obj_id self) throws InterruptedException
    {
        if (!utils.hasScriptVar(self, fs_dyn_village.OBJVAR_MY_MASTER_OBJECT))
        {
            messageTo(self, "handleRetryGetVillageManager", null, 60.0f, false);
            fs_dyn_village.getRegisteredObjIdFromClusterWideData(fs_dyn_village.CLUSTER_OBJID_KEY_MASTER, "handleGetVillageManager", self);
        }
        else 
        {
            checkVillagePhase(self);
        }
    }
    public void requestVillagePhase(obj_id self) throws InterruptedException
    {
        if (!utils.hasScriptVar(self, fs_dyn_village.OBJVAR_CURRENT_PHASE))
        {
            messageTo(self, "handleRetryGetVillagePhase", null, 60.0f, false);
            fs_dyn_village.getRegisteredIntegerFromClusterWideData(fs_dyn_village.CLUSTER_INT_KEY_CUR_PHASE, "handleGetVillagePhase", self);
        }
        else 
        {
            checkVillagePhase(self);
        }
    }
    public void checkVillagePhase(obj_id self) throws InterruptedException
    {
        if (!utils.hasScriptVar(self, fs_dyn_village.OBJVAR_MY_MASTER_OBJECT) || !utils.hasScriptVar(self, fs_dyn_village.OBJVAR_CURRENT_PHASE))
        {
            return;
        }
        int currentPhase = utils.getIntScriptVar(self, fs_dyn_village.OBJVAR_CURRENT_PHASE);
        if (currentPhase != 3)
        {
            testAbortQuest(self);
        }
        else 
        {
            int questIdMain = quests.getQuestId(QUEST_MAIN);
            int questId01 = quests.getQuestId(QUEST_STEP_01);
            int questId02 = quests.getQuestId(QUEST_STEP_02);
            if (questIdMain > -1 && questId01 > -1 && questId02 > -1)
            {
                if (!isQuestComplete(self, questIdMain))
                {
                    boolean finishQuest = false;
                    if (isQuestActive(self, questId02))
                    {
                        quests.complete(QUEST_STEP_02, self, false);
                        clearCompletedQuest(self, questId02);
                    }
                    else if (isQuestComplete(self, questId02))
                    {
                        finishQuest = true;
                        clearCompletedQuest(self, questId02);
                    }
                    quests.activate(QUEST_MAIN, self, null);
                    if (hasObjVar(self, "_notskill.schematics.1700583901"))
                    {
                        quests.complete(QUEST_STEP_01, self, true);
                        if (finishQuest)
                        {
                            messageTo(self, "handleCommunityCraftingMinimumQuantityMet", null, 0.1f, true);
                        }
                    }
                }
            }
        }
    }
    public int msgQuestAbortPhaseChange(obj_id self, dictionary params) throws InterruptedException
    {
        testAbortQuest(self);
        return SCRIPT_CONTINUE;
    }
    public void testAbortQuest(obj_id self) throws InterruptedException
    {
        int questIdMain = quests.getQuestId(QUEST_MAIN);
        int questId01 = quests.getQuestId(QUEST_STEP_01);
        int questId02 = quests.getQuestId(QUEST_STEP_02);
        if (questIdMain > -1 && questId01 > -1 && questId02 > -1)
        {
            if (!isQuestComplete(self, questId02))
            {
                if (isQuestActive(self, questId02))
                {
                    quests.complete(QUEST_STEP_02, self, false);
                }
                if (isQuestActive(self, questId01))
                {
                    quests.complete(QUEST_STEP_01, self, false);
                }
                if (isQuestActive(self, questIdMain))
                {
                    quests.complete(QUEST_MAIN, self, false);
                }
                if (isQuestComplete(self, questId02))
                {
                    clearCompletedQuest(self, questId02);
                }
                if (isQuestComplete(self, questId01))
                {
                    clearCompletedQuest(self, questId01);
                }
                if (isQuestComplete(self, questIdMain))
                {
                    clearCompletedQuest(self, questIdMain);
                }
            }
        }
        detachScript(self, "quest.force_sensitive.fs_craft_village_shields");
    }
    public int handleVerifyQuestStatus(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id masterObject = null;
        if (utils.hasScriptVar(self, fs_dyn_village.OBJVAR_MY_MASTER_OBJECT))
        {
            masterObject = utils.getObjIdScriptVar(self, fs_dyn_village.OBJVAR_MY_MASTER_OBJECT);
        }
        if (!exists(masterObject))
        {
            if (!isIdValid(masterObject))
            {
                requestVillageMaster(self);
            }
            messageTo(self, "handleVerifyQuestStatus", null, 15.0f, false);
            return SCRIPT_CONTINUE;
        }
        int questIdMain = quests.getQuestId(QUEST_MAIN);
        int questId01 = quests.getQuestId(QUEST_STEP_01);
        int questId02 = quests.getQuestId(QUEST_STEP_02);
        if (questIdMain > -1 && questId01 > -1 && questId02 > -1)
        {
            if (!isQuestComplete(self, questId02))
            {
                quests.activate(QUEST_MAIN, self, null);
                quests.complete(QUEST_MAIN, self, true);
                if (hasObjVar(self, "community_crafting.schematics"))
                {
                    quests.complete(QUEST_STEP_01, self, true);
                    int itemsNeeded = community_crafting.getNumIngredientsNeededByPlayer(masterObject, self);
                    if (itemsNeeded == 0)
                    {
                        messageTo(self, community_crafting.MSG_HANDLER_MIN_QUANTITY_MET, null, 5.0f, true);
                    }
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
