package script.conversation;

import script.library.ai_lib;
import script.library.chat;
import script.library.jedi_trials;
import script.library.utils;
import script.*;

public class padawan_kill_baz_nitch_01 extends script.base_script
{
    public padawan_kill_baz_nitch_01()
    {
    }
    public static String c_stringFile = "conversation/padawan_kill_baz_nitch_01";
    public boolean padawan_kill_baz_nitch_01_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }
    public boolean padawan_kill_baz_nitch_01_condition_isTrialPlayer(obj_id player, obj_id npc) throws InterruptedException
    {
        obj_id trialPlayer = getObjIdObjVar(npc, jedi_trials.PADAWAN_TRIAL_PLAYER_OBJVAR);
        if (player == trialPlayer)
        {
            String trialName = jedi_trials.getJediTrialName(player);
            if (trialName != null && trialName.equals("kill_baz_nitch"))
            {
                return true;
            }
        }
        return false;
    }
    public boolean padawan_kill_baz_nitch_01_condition_acceptedQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        obj_id trialPlayer = getObjIdObjVar(npc, jedi_trials.PADAWAN_TRIAL_PLAYER_OBJVAR);
        if (player == trialPlayer)
        {
            return hasObjVar(player, "jedi_trials.padawan_trials.temp.acceptedTask");
        }
        return false;
    }
    public boolean padawan_kill_baz_nitch_01_condition_killedRequiredAmt(obj_id player, obj_id npc) throws InterruptedException
    {
        obj_id trialPlayer = getObjIdObjVar(npc, jedi_trials.PADAWAN_TRIAL_PLAYER_OBJVAR);
        if (player == trialPlayer)
        {
            if (hasObjVar(player, jedi_trials.JEDI_TRIALS_CUR_TRIAL_TOTAL_OBJVAR))
            {
                int numKilled = getIntObjVar(player, jedi_trials.JEDI_TRIALS_CUR_TRIAL_TOTAL_OBJVAR);
                if (numKilled >= 20)
                {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean padawan_kill_baz_nitch_01_condition_completedSuccess(obj_id player, obj_id npc) throws InterruptedException
    {
        obj_id trialPlayer = getObjIdObjVar(npc, jedi_trials.PADAWAN_TRIAL_PLAYER_OBJVAR);
        if (player == trialPlayer)
        {
            String trialName = jedi_trials.getJediTrialName(player);
            if (trialName != null && !trialName.equals("kill_baz_nitch"))
            {
                return hasObjVar(npc, "padawan_trials.playerSucceeded");
            }
        }
        return false;
    }
    public void padawan_kill_baz_nitch_01_action_questSuccess(obj_id player, obj_id npc) throws InterruptedException
    {
        removeObjVar(player, "handlePlayerCombatKill");
        setObjVar(npc, "padawan_trials.playerSucceeded", true);
        messageTo(player, "handleTrialComplete", null, 1, false);
        return;
    }
    public void padawan_kill_baz_nitch_01_action_acceptQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        setObjVar(npc, "padawan_trials.playerAccepted", true);
        setObjVar(player, "jedi_trials.padawan_trials.temp.acceptedTask", true);
        setObjVar(player, "handlePlayerCombatKill", true);
        if (hasObjVar(player, jedi_trials.PADAWAN_WAYPOINT_OBJVAR))
        {
            obj_id oldWaypoint = getObjIdObjVar(player, jedi_trials.PADAWAN_WAYPOINT_OBJVAR);
            if (isIdValid(oldWaypoint))
            {
                destroyWaypointInDatapad(oldWaypoint, player);
            }
        }
        return;
    }
    public int padawan_kill_baz_nitch_01_handleBranch4(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_2313ac9e"))
        {
            if (padawan_kill_baz_nitch_01_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_88122b41");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (padawan_kill_baz_nitch_01_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses[] = new string_id[numberOfResponses];
                    if (hasResponse0)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_983337f5");
                    }
                    utils.setScriptVar(player, "conversation.padawan_kill_baz_nitch_01.branchId", 5);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.padawan_kill_baz_nitch_01.branchId");
                    chat.chat(npc, player, message);
                    npcEndConversation(player);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int padawan_kill_baz_nitch_01_handleBranch5(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_983337f5"))
        {
            if (padawan_kill_baz_nitch_01_condition__defaultCondition(player, npc))
            {
                padawan_kill_baz_nitch_01_action_acceptQuest(player, npc);
                string_id message = new string_id(c_stringFile, "s_4498c7a3");
                utils.removeScriptVar(player, "conversation.padawan_kill_baz_nitch_01.branchId");
                chat.chat(npc, player, message);
                npcEndConversation(player);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if ((!isMob(self)) || (isPlayer(self)))
        {
            detachScript(self, "conversation.padawan_kill_baz_nitch_01");
        }
        setCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info menuInfo) throws InterruptedException
    {
        int menu = menuInfo.addRootMenu(menu_info_types.CONVERSE_START, null);
        menu_info_data menuInfoData = menuInfo.getMenuItemById(menu);
        menuInfoData.setServerNotify(false);
        setCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        clearCondition(self, CONDITION_CONVERSABLE);
        detachScript(self, "conversation.padawan_kill_baz_nitch_01");
        return SCRIPT_CONTINUE;
    }
    public boolean npcStartConversation(obj_id player, obj_id npc, String convoName, string_id greetingId, prose_package greetingProse, string_id[] responses) throws InterruptedException
    {
        Object[] objects = new Object[responses.length];
        System.arraycopy(responses, 0, objects, 0, responses.length);
        return npcStartConversation(player, npc, convoName, greetingId, greetingProse, objects);
    }
    public int OnStartNpcConversation(obj_id self, obj_id player) throws InterruptedException
    {
        obj_id npc = self;
        if (ai_lib.isInCombat(npc) || ai_lib.isInCombat(player))
        {
            return SCRIPT_OVERRIDE;
        }
        if (padawan_kill_baz_nitch_01_condition_completedSuccess(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_70d6b213");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (padawan_kill_baz_nitch_01_condition_killedRequiredAmt(player, npc))
        {
            doAnimationAction(npc, "nod_head_multiple");
            padawan_kill_baz_nitch_01_action_questSuccess(player, npc);
            string_id message = new string_id(c_stringFile, "s_a7c60196");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (padawan_kill_baz_nitch_01_condition_acceptedQuest(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_a8f84c9f");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (padawan_kill_baz_nitch_01_condition_isTrialPlayer(player, npc))
        {
            doAnimationAction(npc, "beckon");
            string_id message = new string_id(c_stringFile, "s_95db8861");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (padawan_kill_baz_nitch_01_condition__defaultCondition(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }
            if (hasResponse)
            {
                int responseIndex = 0;
                string_id responses[] = new string_id[numberOfResponses];
                if (hasResponse0)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_2313ac9e");
                }
                utils.setScriptVar(player, "conversation.padawan_kill_baz_nitch_01.branchId", 4);
                npcStartConversation(player, npc, "padawan_kill_baz_nitch_01", message, responses);
            }
            else 
            {
                chat.chat(npc, player, message);
            }
            return SCRIPT_CONTINUE;
        }
        if (padawan_kill_baz_nitch_01_condition__defaultCondition(player, npc))
        {
            doAnimationAction(npc, "explain");
            string_id message = new string_id(c_stringFile, "s_2b2c13e4");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
    {
        if (!conversationId.equals("padawan_kill_baz_nitch_01"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.padawan_kill_baz_nitch_01.branchId");
        if (branchId == 4 && padawan_kill_baz_nitch_01_handleBranch4(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 5 && padawan_kill_baz_nitch_01_handleBranch5(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.padawan_kill_baz_nitch_01.branchId");
        return SCRIPT_CONTINUE;
    }
}
