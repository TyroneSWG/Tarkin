package script.conversation;

import script.library.ai_lib;
import script.library.chat;
import script.library.groundquests;
import script.library.utils;
import script.*;

public class corellia_tyrena_noro extends script.base_script
{
    public corellia_tyrena_noro()
    {
    }
    public static String c_stringFile = "conversation/corellia_tyrena_noro";
    public boolean corellia_tyrena_noro_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }
    public boolean corellia_tyrena_noro_condition_talkNoro(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.isTaskActive(player, "corellia_tyrena_slaver_access", "talk_to_slave_2");
    }
    public boolean corellia_tyrena_noro_condition_completeSlaverAccess(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.hasCompletedQuest(player, "corellia_tyrena_slaver_access");
    }
    public boolean corellia_tyrena_noro_condition_onSpike(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.isTaskActive(player, "corellia_tyrena_slaver_access", "hack_term_2b");
    }
    public boolean corellia_tyrena_noro_condition_onAdminComp(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.isTaskActive(player, "corellia_tyrena_slaver_access", "hack_term_2a");
    }
    public boolean corellia_tyrena_noro_condition_onAdminComp2(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.isTaskActive(player, "corellia_tyrena_slaver_access", "hack_term_2c");
    }
    public boolean corellia_tyrena_noro_condition_onSlaverAccess(obj_id player, obj_id npc) throws InterruptedException
    {
        return (groundquests.isTaskActive(player, "corellia_tyrena_slaver_access", "pw_confirmation_2d") || groundquests.isTaskActive(player, "corellia_tyrena_slaver_access", "talk_to_akkus_3a") || groundquests.isTaskActive(player, "corellia_tyrena_slaver_access", "hack_term_3b") || groundquests.isTaskActive(player, "corellia_tyrena_slaver_access", "pw_confirmation_3c") || groundquests.isTaskActive(player, "corellia_tyrena_slaver_access", "hack_term_4") || groundquests.isTaskActive(player, "corellia_tyrena_slaver_access", "hack_term_4a") || groundquests.isTaskActive(player, "corellia_tyrena_slaver_access", "kill_detention_guard_5") || groundquests.isTaskActive(player, "corellia_tyrena_slaver_access", "finale_hack_5a") || groundquests.isTaskActive(player, "corellia_tyrena_slaver_access", "finale_msgbox_5b"));
    }
    public void corellia_tyrena_noro_action_signalNoro(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.sendSignal(player, "talk_to_slave_2");
    }
    public int corellia_tyrena_noro_handleBranch3(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_23"))
        {
            if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "point_accusingly");
                string_id message = new string_id(c_stringFile, "s_24");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_25");
                    }
                    utils.setScriptVar(player, "conversation.corellia_tyrena_noro.branchId", 4);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.corellia_tyrena_noro.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int corellia_tyrena_noro_handleBranch4(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_25"))
        {
            if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_26");
                utils.removeScriptVar(player, "conversation.corellia_tyrena_noro.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int corellia_tyrena_noro_handleBranch6(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_16"))
        {
            doAnimationAction(player, "explain");
            if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "rub_chin_thoughtful");
                string_id message = new string_id(c_stringFile, "s_17");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_18");
                    }
                    utils.setScriptVar(player, "conversation.corellia_tyrena_noro.branchId", 7);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.corellia_tyrena_noro.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int corellia_tyrena_noro_handleBranch7(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_18"))
        {
            if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "explain");
                string_id message = new string_id(c_stringFile, "s_19");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_21");
                    }
                    utils.setScriptVar(player, "conversation.corellia_tyrena_noro.branchId", 8);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.corellia_tyrena_noro.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int corellia_tyrena_noro_handleBranch8(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_21"))
        {
            if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "thumb_up");
                string_id message = new string_id(c_stringFile, "s_30");
                utils.removeScriptVar(player, "conversation.corellia_tyrena_noro.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int corellia_tyrena_noro_handleBranch11(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_35"))
        {
            if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "shrug_hands");
                string_id message = new string_id(c_stringFile, "s_37");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_39");
                    }
                    utils.setScriptVar(player, "conversation.corellia_tyrena_noro.branchId", 12);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.corellia_tyrena_noro.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int corellia_tyrena_noro_handleBranch12(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_39"))
        {
            doAnimationAction(player, "explain");
            if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "shrug_hands");
                string_id message = new string_id(c_stringFile, "s_41");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_43");
                    }
                    utils.setScriptVar(player, "conversation.corellia_tyrena_noro.branchId", 13);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.corellia_tyrena_noro.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int corellia_tyrena_noro_handleBranch13(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_43"))
        {
            if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "point_forward");
                string_id message = new string_id(c_stringFile, "s_45");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_47");
                    }
                    utils.setScriptVar(player, "conversation.corellia_tyrena_noro.branchId", 14);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.corellia_tyrena_noro.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int corellia_tyrena_noro_handleBranch14(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_47"))
        {
            doAnimationAction(player, "shrug_hands");
            if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "shake_head_no");
                string_id message = new string_id(c_stringFile, "s_49");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_51");
                    }
                    utils.setScriptVar(player, "conversation.corellia_tyrena_noro.branchId", 15);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.corellia_tyrena_noro.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int corellia_tyrena_noro_handleBranch15(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_51"))
        {
            doAnimationAction(player, "thank");
            if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "nod_head_multiple");
                corellia_tyrena_noro_action_signalNoro(player, npc);
                string_id message = new string_id(c_stringFile, "s_53");
                utils.removeScriptVar(player, "conversation.corellia_tyrena_noro.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if ((!isTangible(self)) || (isPlayer(self)))
        {
            detachScript(self, "conversation.corellia_tyrena_noro");
        }
        setCondition(self, CONDITION_CONVERSABLE);
        setCondition(self, CONDITION_INTERESTING);
        setInvulnerable(self, true);
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setCondition(self, CONDITION_CONVERSABLE);
        setCondition(self, CONDITION_INTERESTING);
        setInvulnerable(self, true);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info menuInfo) throws InterruptedException
    {
        int menu = menuInfo.addRootMenu(menu_info_types.CONVERSE_START, null);
        menu_info_data menuInfoData = menuInfo.getMenuItemById(menu);
        menuInfoData.setServerNotify(false);
        setCondition(self, CONDITION_CONVERSABLE);
        faceTo(self, player);
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        clearCondition(self, CONDITION_CONVERSABLE);
        detachScript(self, "conversation.corellia_tyrena_noro");
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
        if (corellia_tyrena_noro_condition_completeSlaverAccess(player, npc))
        {
            doAnimationAction(npc, "thank");
            string_id message = new string_id(c_stringFile, "s_28");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (corellia_tyrena_noro_condition_onSlaverAccess(player, npc))
        {
            doAnimationAction(npc, "explain");
            string_id message = new string_id(c_stringFile, "s_27");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (corellia_tyrena_noro_condition_onAdminComp2(player, npc))
        {
            doAnimationAction(npc, "nervous");
            string_id message = new string_id(c_stringFile, "s_22");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_23");
                }
                utils.setScriptVar(player, "conversation.corellia_tyrena_noro.branchId", 3);
                npcStartConversation(player, npc, "corellia_tyrena_noro", message, responses);
            }
            else 
            {
                chat.chat(npc, player, message);
            }
            return SCRIPT_CONTINUE;
        }
        if (corellia_tyrena_noro_condition_onSpike(player, npc))
        {
            doAnimationAction(npc, "shrug_hands");
            string_id message = new string_id(c_stringFile, "s_15");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_16");
                }
                utils.setScriptVar(player, "conversation.corellia_tyrena_noro.branchId", 6);
                npcStartConversation(player, npc, "corellia_tyrena_noro", message, responses);
            }
            else 
            {
                chat.chat(npc, player, message);
            }
            return SCRIPT_CONTINUE;
        }
        if (corellia_tyrena_noro_condition_onAdminComp(player, npc))
        {
            doAnimationAction(npc, "explain");
            string_id message = new string_id(c_stringFile, "s_55");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (corellia_tyrena_noro_condition_talkNoro(player, npc))
        {
            doAnimationAction(npc, "nervous");
            string_id message = new string_id(c_stringFile, "s_33");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_35");
                }
                utils.setScriptVar(player, "conversation.corellia_tyrena_noro.branchId", 11);
                npcStartConversation(player, npc, "corellia_tyrena_noro", message, responses);
            }
            else 
            {
                chat.chat(npc, player, message);
            }
            return SCRIPT_CONTINUE;
        }
        if (corellia_tyrena_noro_condition__defaultCondition(player, npc))
        {
            doAnimationAction(npc, "implore");
            string_id message = new string_id(c_stringFile, "s_56");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
    {
        if (!conversationId.equals("corellia_tyrena_noro"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.corellia_tyrena_noro.branchId");
        if (branchId == 3 && corellia_tyrena_noro_handleBranch3(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 4 && corellia_tyrena_noro_handleBranch4(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 6 && corellia_tyrena_noro_handleBranch6(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 7 && corellia_tyrena_noro_handleBranch7(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 8 && corellia_tyrena_noro_handleBranch8(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 11 && corellia_tyrena_noro_handleBranch11(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 12 && corellia_tyrena_noro_handleBranch12(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 13 && corellia_tyrena_noro_handleBranch13(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 14 && corellia_tyrena_noro_handleBranch14(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 15 && corellia_tyrena_noro_handleBranch15(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.corellia_tyrena_noro.branchId");
        return SCRIPT_CONTINUE;
    }
}
