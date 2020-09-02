package script.quest.force_sensitive;

import script.dictionary;
import script.library.*;
import script.obj_id;
import script.string_id;

import java.util.Vector;

public class fs_survey_npc_convo extends script.base_script
{
    public fs_survey_npc_convo()
    {
    }
    public static final String CONVO = "quest/force_sensitive/fs_survey";
    public static final String table = "datatables/quest/force_sensitive/fs_survey_quest_types.iff";
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        attachScript(self, "npc.converse.npc_converse_menu");
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        obj_id master = getObjIdObjVar(self, "fs_dyn_village.OBJVAR_MY_MASTER_OBJECT");
        int phase = getIntObjVar(master, fs_dyn_village.OBJVAR_PHASE_VERSION);
        if (!hasObjVar(self, "village.phase"))
        {
            setObjVar(self, "village.phase", phase);
        }
        else 
        {
            int lastPhase = getIntObjVar(self, "village.phase");
            if (lastPhase != phase)
            {
                messageTo(self, "doWinnerCircleForSurveyQuests", null, 3, false);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        attachScript(self, "npc.converse.npc_converse_menu");
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        return SCRIPT_CONTINUE;
    }
    public int OnStartNpcConversation(obj_id self, obj_id speaker) throws InterruptedException
    {
        if (ai_lib.isInCombat(self) || ai_lib.isInCombat(speaker))
        {
            return SCRIPT_OVERRIDE;
        }
        if (!hasSkill(speaker, "crafting_artisan_novice"))
        {
            string_id noSkill = new string_id(CONVO, "no_skill");
            chat.chat(self, noSkill);
            return SCRIPT_CONTINUE;
        }
        obj_id master = getObjIdObjVar(self, "fs_quest.myMaster");
        int phase = getIntObjVar(master, fs_dyn_village.OBJVAR_CURRENT_PHASE);
        if (phase != 2 && phase != 3)
        {
            string_id notRightNow = new string_id(CONVO, "wrong_phase");
            chat.chat(self, notRightNow);
            return SCRIPT_CONTINUE;
        }
        if (phase == 2 && fs_quests.hasUnlockedBranch(speaker, "force_sensitive_heightened_senses_surveying"))
        {
            string_id thanks = new string_id(CONVO, "thanks");
            chat.chat(self, thanks);
            return SCRIPT_CONTINUE;
        }
        if (phase == 3 && fs_quests.hasUnlockedBranch(speaker, "force_sensitive_heightened_senses_luck"))
        {
            string_id thanks = new string_id(CONVO, "thanks");
            chat.chat(self, thanks);
            return SCRIPT_CONTINUE;
        }
        if (phase == 2 && quests.isComplete("fs_survey_phase2_reward", speaker))
        {
            string_id thanks = new string_id(CONVO, "thanks");
            chat.chat(self, thanks);
            return SCRIPT_CONTINUE;
        }
        if (phase == 3 && quests.isComplete("fs_survey_phase3_reward", speaker))
        {
            string_id thanks = new string_id(CONVO, "thanks");
            chat.chat(self, thanks);
            return SCRIPT_CONTINUE;
        }
        String questName = hasOneOfMyQuests(speaker);
        obj_id winner = getObjIdObjVar(self, "quest.winner");
        if (speaker == winner)
        {
            obj_id backpack = utils.getInventoryContainer(speaker);
            obj_id madeObject = createObject("object/tangible/survey_tool/survey_tool_all.iff", backpack, null);
        }
        if (hasObjVar(self, "gave." + speaker))
        {
            string_id greeting = new string_id(CONVO, "want_task");
            string_id response[] = new string_id[3];
            response[0] = new string_id(CONVO, "yes_task");
            response[1] = new string_id(CONVO, "no_task");
            response[2] = new string_id(CONVO, "how_much");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
            return SCRIPT_CONTINUE;
        }
        else if (!questName.equals("none"))
        {
            int row = dataTableSearchColumnForString(questName, "NAME", "datatables/player/quests.iff");
            String resourceType = dataTableGetString("datatables/player/quests.iff", row, "TARGET");
            resourceType = "sample_for_" + resourceType;
            string_id goSample = new string_id(CONVO, resourceType);
            chat.chat(self, goSample);
            return SCRIPT_CONTINUE;
        }
        else 
        {
            if (!fs_quests.hasQuestAccepted(speaker) && fs_quests.isVillageEligible(speaker))
            {
                string_id greeting = new string_id(CONVO, "want_task");
                string_id response[] = new string_id[2];
                response[0] = new string_id(CONVO, "explain");
                response[1] = new string_id(CONVO, "no_task");
                npcStartConversation(speaker, self, "celebConvo", greeting, response);
                return SCRIPT_CONTINUE;
            }
            else 
            {
                string_id youreBusy = new string_id(CONVO, "youre_busy");
                chat.chat(self, youreBusy);
                return SCRIPT_CONTINUE;
            }
        }
    }
    public int OnNpcConversationResponse(obj_id self, String convo, obj_id player, string_id response) throws InterruptedException
    {
        obj_id master = getObjIdObjVar(self, "fs_quest.myMaster");
        int phase = getIntObjVar(master, fs_dyn_village.OBJVAR_CURRENT_PHASE);
        if ((response.getAsciiId()).equals("yes_task"))
        {
            if (phase == 3)
            {
                string_id message = new string_id(CONVO, "sample_for_iron");
                npcSpeak(player, message);
                if (!fs_quests.hasQuestAccepted(player) && fs_quests.isVillageEligible(player))
                {
                    quests.activate("survey_phase3_main", player, self);
                    fs_quests.setQuestAccepted(player);
                }
            }
            else if (phase == 2)
            {
                string_id message = new string_id(CONVO, "sample_for_aluminum");
                npcSpeak(player, message);
                if (!fs_quests.hasQuestAccepted(player) && fs_quests.isVillageEligible(player))
                {
                    quests.activate("survey_phase2_main", player, self);
                    fs_quests.setQuestAccepted(player);
                }
            }
            else 
            {
                return SCRIPT_CONTINUE;
            }
            Vector questCrafter = utils.getResizeableObjIdBatchObjVar(master, "quest.survey.giver");
            Vector crafterGivers = utils.getResizeableIntBatchObjVar(master, "quest.survey.count");
            if (!utils.isElementInArray(questCrafter, player))
            {
                questCrafter = utils.addElement(questCrafter, player);
                crafterGivers = utils.addElement(crafterGivers, 0);
                utils.setResizeableBatchObjVar(master, "quest.survey.giver", questCrafter);
                utils.setResizeableBatchObjVar(master, "quest.survey.count", crafterGivers);
            }
            npcEndConversation(player);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("no_task"))
        {
            string_id message = new string_id(CONVO, "too_bad");
            npcSpeak(player, message);
            npcEndConversation(player);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("how_much"))
        {
            string_id message = new string_id(CONVO, "this_much");
            int mucho = getIntObjVar(self, "gave." + player);
            sendSystemMessage(player, "You've given him " + mucho + " units of resource.", null);
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "how_much"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("explain"))
        {
            string_id message = new string_id(CONVO, "explanation");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "explain"));
            npcAddConversationResponse(player, new string_id(CONVO, "sure"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("sure"))
        {
            string_id message = new string_id(CONVO, "last_piece");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "sure"));
            npcAddConversationResponse(player, new string_id(CONVO, "special_question"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("special_question"))
        {
            string_id message = new string_id(CONVO, "special_answer_" + phase);
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "special_question"));
            npcAddConversationResponse(player, new string_id(CONVO, "yes_task"));
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public String getQuestNumber() throws InterruptedException
    {
        String resource = "type_one";
        int stochastic = rand(1, 6);
        switch (stochastic)
        {
            case 1:
            resource = "type_one";
            break;
            case 2:
            resource = "type_two";
            break;
            case 3:
            resource = "type_three";
            break;
            case 4:
            resource = "type_four";
            break;
            case 5:
            resource = "type_five";
            break;
            case 6:
            resource = "type_six";
            break;
        }
        return resource;
    }
    public int OnGiveItem(obj_id self, obj_id item, obj_id player) throws InterruptedException
    {
        String name = getTemplateName(item);
        obj_id master = getObjIdObjVar(self, "fs_dyn_village.OBJVAR_MY_MASTER_OBJECT");
        if (isRequiredResource(name))
        {
            if (master != null)
            {
                obj_id[] players = getObjIdArrayObjVar(master, "quest.survey.giver");
                int[] given = getIntArrayObjVar(master, "quest.survey.count");
                int numPlayers = players.length;
                int numGiven = given.length;
                if (numPlayers == numGiven)
                {
                    if (numPlayers > 0)
                    {
                        for (int x = 0; x < numPlayers; x++)
                        {
                            obj_id thisOne = players[x];
                            int count = given[x];
                            if (player == thisOne)
                            {
                                ++count;
                                setObjVar(master, "quest.survey.count", given);
                            }
                        }
                    }
                }
            }
            destroyObject(item);
            string_id gave = new string_id(CONVO, "gave_resource");
            chat.chat(self, gave);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public boolean isRequiredResource(String name) throws InterruptedException
    {
        if (name.equals("object/tangible/loot/quest/wind_crystal.iff"))
        {
            return true;
        }
        if (name.equals("object/tangible/loot/quest/ardanium_ii.iff"))
        {
            return true;
        }
        if (name.equals("object/tangible/loot/quest/rudic.iff"))
        {
            return true;
        }
        if (name.equals("object/tangible/loot/quest/ostrine.iff"))
        {
            return true;
        }
        if (name.equals("object/tangible/loot/quest/endrine.iff"))
        {
            return true;
        }
        return false;
    }
    public int doWinnerCircleForSurveyQuests(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id master = getObjIdObjVar(self, "fs_dyn_village.OBJVAR_MY_MASTER_OBJECT");
        obj_id[] questCrafter = getObjIdArrayObjVar(master, "quest.survey.giver");
        int[] crafterGivers = getIntArrayObjVar(master, "quest.survey.count");
        community_crafting.doubleSort(crafterGivers, questCrafter);
        obj_id winner = questCrafter[0];
        if (winner != null)
        {
            setObjVar(self, "quest.winner", winner);
        }
        return SCRIPT_CONTINUE;
    }
    public String hasOneOfMyQuests(obj_id player) throws InterruptedException
    {
        String what = "none";
        boolean phase2_1 = quests.isActive("survey_phase2_01", player);
        if (phase2_1 == true)
        {
            what = "survey_phase2_01";
        }
        boolean phase2_2 = quests.isActive("survey_phase2_02", player);
        if (phase2_2 == true)
        {
            what = "survey_phase2_02";
        }
        boolean phase2_3 = quests.isActive("survey_phase2_03", player);
        if (phase2_3 == true)
        {
            what = "survey_phase2_03";
        }
        boolean phase2_4 = quests.isActive("survey_phase2_04", player);
        if (phase2_4 == true)
        {
            what = "survey_phase2_04";
        }
        boolean phase2_5 = quests.isActive("survey_phase2_05", player);
        if (phase2_5 == true)
        {
            what = "survey_phase2_05";
        }
        boolean phase2_6 = quests.isActive("survey_phase2_06", player);
        if (phase2_6 == true)
        {
            what = "survey_phase2_06";
        }
        boolean phase2_7 = quests.isActive("survey_phase2_07", player);
        if (phase2_7 == true)
        {
            what = "survey_phase2_07";
        }
        boolean phase2_8 = quests.isActive("survey_phase2_08", player);
        if (phase2_8 == true)
        {
            what = "survey_phase2_08";
        }
        boolean phase3_1 = quests.isActive("survey_phase3_01", player);
        if (phase3_1 == true)
        {
            what = "survey_phase3_01";
        }
        boolean phase3_2 = quests.isActive("survey_phase3_02", player);
        if (phase3_2 == true)
        {
            what = "survey_phase3_02";
        }
        boolean phase3_3 = quests.isActive("survey_phase3_03", player);
        if (phase3_3 == true)
        {
            what = "survey_phase3_03";
        }
        boolean phase3_4 = quests.isActive("survey_phase3_04", player);
        if (phase3_4 == true)
        {
            what = "survey_phase3_04";
        }
        boolean phase3_5 = quests.isActive("survey_phase3_05", player);
        if (phase3_5 == true)
        {
            what = "survey_phase3_05";
        }
        boolean phase3_6 = quests.isActive("survey_phase3_06", player);
        if (phase3_6 == true)
        {
            what = "survey_phase3_06";
        }
        boolean phase3_7 = quests.isActive("survey_phase3_07", player);
        if (phase3_7 == true)
        {
            what = "survey_phase3_07";
        }
        boolean phase3_8 = quests.isActive("survey_phase3_08", player);
        if (phase3_8 == true)
        {
            what = "survey_phase3_08";
        }
        return what;
    }
}
