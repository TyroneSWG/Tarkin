package script.conversation;

import script.library.ai_lib;
import script.library.chat;
import script.library.jedi_trials;
import script.library.utils;
import script.*;

public class padawan_spice_mom_01 extends script.base_script
{
    public padawan_spice_mom_01()
    {
    }
    public static String c_stringFile = "conversation/padawan_spice_mom_01";
    public boolean padawan_spice_mom_01_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }
    public boolean padawan_spice_mom_01_condition_acceptedQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        obj_id trialPlayer = getObjIdObjVar(npc, jedi_trials.PADAWAN_TRIAL_PLAYER_OBJVAR);
        if (player == trialPlayer)
        {
            return hasObjVar(player, "jedi_trials.padawan_trials.temp.acceptedTask");
        }
        return false;
    }
    public boolean padawan_spice_mom_01_condition_isTrialPlayer(obj_id player, obj_id npc) throws InterruptedException
    {
        obj_id trialPlayer = getObjIdObjVar(npc, jedi_trials.PADAWAN_TRIAL_PLAYER_OBJVAR);
        if (player == trialPlayer)
        {
            String trialName = jedi_trials.getJediTrialName(player);
            if (trialName != null && trialName.equals("spice_mom"))
            {
                return !hasObjVar(npc, "padawan_trials.playerFailed");
            }
        }
        return false;
    }
    public boolean padawan_spice_mom_01_condition_spokeToNpc(obj_id player, obj_id npc) throws InterruptedException
    {
        obj_id trialPlayer = getObjIdObjVar(npc, jedi_trials.PADAWAN_TRIAL_PLAYER_OBJVAR);
        if (player == trialPlayer)
        {
            return hasObjVar(player, "jedi_trials.padawan_trials.temp.spokeToTarget_01");
        }
        return false;
    }
    public boolean padawan_spice_mom_01_condition_completedQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        obj_id trialPlayer = getObjIdObjVar(npc, jedi_trials.PADAWAN_TRIAL_PLAYER_OBJVAR);
        if (player == trialPlayer)
        {
            String trialName = jedi_trials.getJediTrialName(player);
            if (trialName != null && !trialName.equals("spice_mom"))
            {
                return hasObjVar(npc, "padawan_trials.playerSucceeded");
            }
        }
        return false;
    }
    public void padawan_spice_mom_01_action_questFailure(obj_id player, obj_id npc) throws InterruptedException
    {
        setObjVar(npc, "padawan_trials.playerFailed", true);
        messageTo(player, "handleQuestFailure", null, 1, false);
        return;
    }
    public void padawan_spice_mom_01_action_questSuccess(obj_id player, obj_id npc) throws InterruptedException
    {
        setObjVar(npc, "padawan_trials.playerSucceeded", true);
        messageTo(player, "handleTrialComplete", null, 1, false);
        return;
    }
    public void padawan_spice_mom_01_action_sendToNextNpc(obj_id player, obj_id npc) throws InterruptedException
    {
        setObjVar(npc, "padawan_trials.playerAccepted", true);
        setObjVar(player, "jedi_trials.padawan_trials.temp.acceptedTask", true);
        messageTo(player, "handleSetSecondLoc", null, 1, false);
        return;
    }
    public int padawan_spice_mom_01_handleBranch2(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_9b2292c8"))
        {
            if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_cf8c69f5");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                boolean hasResponse1 = false;
                if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse1 = true;
                }
                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses[] = new string_id[numberOfResponses];
                    if (hasResponse0)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_829888a9");
                    }
                    if (hasResponse1)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_85da54b7");
                    }
                    utils.setScriptVar(player, "conversation.padawan_spice_mom_01.branchId", 3);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.padawan_spice_mom_01.branchId");
                    chat.chat(npc, player, message);
                    npcEndConversation(player);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int padawan_spice_mom_01_handleBranch3(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_829888a9"))
        {
            if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "nod_head_multiple");
                padawan_spice_mom_01_action_questSuccess(player, npc);
                string_id message = new string_id(c_stringFile, "s_dc552ff6");
                utils.removeScriptVar(player, "conversation.padawan_spice_mom_01.branchId");
                chat.chat(npc, player, message);
                npcEndConversation(player);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_85da54b7"))
        {
            if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "point_accusingly");
                string_id message = new string_id(c_stringFile, "s_dcecfc37");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                boolean hasResponse1 = false;
                if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse1 = true;
                }
                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses[] = new string_id[numberOfResponses];
                    if (hasResponse0)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_72b6d639");
                    }
                    if (hasResponse1)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_d1aec57f");
                    }
                    utils.setScriptVar(player, "conversation.padawan_spice_mom_01.branchId", 5);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.padawan_spice_mom_01.branchId");
                    chat.chat(npc, player, message);
                    npcEndConversation(player);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int padawan_spice_mom_01_handleBranch5(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_72b6d639"))
        {
            if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "nod_head_multiple");
                padawan_spice_mom_01_action_questSuccess(player, npc);
                string_id message = new string_id(c_stringFile, "s_586cd026");
                utils.removeScriptVar(player, "conversation.padawan_spice_mom_01.branchId");
                chat.chat(npc, player, message);
                npcEndConversation(player);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_d1aec57f"))
        {
            if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "wave_on_dismissing");
                string_id message = new string_id(c_stringFile, "s_a687a0a6");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                boolean hasResponse1 = false;
                if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse1 = true;
                }
                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses[] = new string_id[numberOfResponses];
                    if (hasResponse0)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_1aa2a6e6");
                    }
                    if (hasResponse1)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_a8241eb1");
                    }
                    utils.setScriptVar(player, "conversation.padawan_spice_mom_01.branchId", 7);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.padawan_spice_mom_01.branchId");
                    chat.chat(npc, player, message);
                    npcEndConversation(player);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int padawan_spice_mom_01_handleBranch7(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_1aa2a6e6"))
        {
            if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "nod_head_multiple");
                padawan_spice_mom_01_action_questSuccess(player, npc);
                string_id message = new string_id(c_stringFile, "s_78f7bd03");
                utils.removeScriptVar(player, "conversation.padawan_spice_mom_01.branchId");
                chat.chat(npc, player, message);
                npcEndConversation(player);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_a8241eb1"))
        {
            if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "point_accusingly");
                padawan_spice_mom_01_action_questFailure(player, npc);
                string_id message = new string_id(c_stringFile, "s_751ed2f1");
                utils.removeScriptVar(player, "conversation.padawan_spice_mom_01.branchId");
                chat.chat(npc, player, message);
                npcEndConversation(player);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int padawan_spice_mom_01_handleBranch10(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_42c15c5"))
        {
            if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_238c7ac2");
                utils.removeScriptVar(player, "conversation.padawan_spice_mom_01.branchId");
                chat.chat(npc, player, message);
                npcEndConversation(player);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_8a806d30"))
        {
            if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "cover_mouth");
                padawan_spice_mom_01_action_questFailure(player, npc);
                string_id message = new string_id(c_stringFile, "s_a3b22c35");
                utils.removeScriptVar(player, "conversation.padawan_spice_mom_01.branchId");
                chat.chat(npc, player, message);
                npcEndConversation(player);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int padawan_spice_mom_01_handleBranch13(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_16ec7737"))
        {
            if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_6a11e41a");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                boolean hasResponse1 = false;
                if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse1 = true;
                }
                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses[] = new string_id[numberOfResponses];
                    if (hasResponse0)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_d471cdd");
                    }
                    if (hasResponse1)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_d4ade5e3");
                    }
                    utils.setScriptVar(player, "conversation.padawan_spice_mom_01.branchId", 14);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.padawan_spice_mom_01.branchId");
                    chat.chat(npc, player, message);
                    npcEndConversation(player);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int padawan_spice_mom_01_handleBranch14(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_d471cdd"))
        {
            if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "applause_polite");
                padawan_spice_mom_01_action_sendToNextNpc(player, npc);
                string_id message = new string_id(c_stringFile, "s_93965747");
                utils.removeScriptVar(player, "conversation.padawan_spice_mom_01.branchId");
                chat.chat(npc, player, message);
                npcEndConversation(player);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_d4ade5e3"))
        {
            if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "slump_head");
                padawan_spice_mom_01_action_questFailure(player, npc);
                string_id message = new string_id(c_stringFile, "s_b40d662");
                utils.removeScriptVar(player, "conversation.padawan_spice_mom_01.branchId");
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
            detachScript(self, "conversation.padawan_spice_mom_01");
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
        detachScript(self, "conversation.padawan_spice_mom_01");
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
        if (padawan_spice_mom_01_condition_completedQuest(player, npc))
        {
            doAnimationAction(npc, "sigh_deeply");
            string_id message = new string_id(c_stringFile, "s_bf3942ab");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (padawan_spice_mom_01_condition_spokeToNpc(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_98244955");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_9b2292c8");
                }
                utils.setScriptVar(player, "conversation.padawan_spice_mom_01.branchId", 2);
                npcStartConversation(player, npc, "padawan_spice_mom_01", message, responses);
            }
            else 
            {
                chat.chat(npc, player, message);
            }
            return SCRIPT_CONTINUE;
        }
        if (padawan_spice_mom_01_condition_acceptedQuest(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_b638139d");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }
            boolean hasResponse1 = false;
            if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse1 = true;
            }
            if (hasResponse)
            {
                int responseIndex = 0;
                string_id responses[] = new string_id[numberOfResponses];
                if (hasResponse0)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_42c15c5");
                }
                if (hasResponse1)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_8a806d30");
                }
                utils.setScriptVar(player, "conversation.padawan_spice_mom_01.branchId", 10);
                npcStartConversation(player, npc, "padawan_spice_mom_01", message, responses);
            }
            else 
            {
                chat.chat(npc, player, message);
            }
            return SCRIPT_CONTINUE;
        }
        if (padawan_spice_mom_01_condition_isTrialPlayer(player, npc))
        {
            doAnimationAction(npc, "beckon");
            string_id message = new string_id(c_stringFile, "s_4f24860b");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_16ec7737");
                }
                utils.setScriptVar(player, "conversation.padawan_spice_mom_01.branchId", 13);
                npcStartConversation(player, npc, "padawan_spice_mom_01", message, responses);
            }
            else 
            {
                chat.chat(npc, player, message);
            }
            return SCRIPT_CONTINUE;
        }
        if (padawan_spice_mom_01_condition__defaultCondition(player, npc))
        {
            doAnimationAction(npc, "weeping");
            string_id message = new string_id(c_stringFile, "s_2aa97a99");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
    {
        if (!conversationId.equals("padawan_spice_mom_01"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.padawan_spice_mom_01.branchId");
        if (branchId == 2 && padawan_spice_mom_01_handleBranch2(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 3 && padawan_spice_mom_01_handleBranch3(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 5 && padawan_spice_mom_01_handleBranch5(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 7 && padawan_spice_mom_01_handleBranch7(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 10 && padawan_spice_mom_01_handleBranch10(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 13 && padawan_spice_mom_01_handleBranch13(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 14 && padawan_spice_mom_01_handleBranch14(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.padawan_spice_mom_01.branchId");
        return SCRIPT_CONTINUE;
    }
}
