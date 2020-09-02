package script.conversation;

import script.library.ai_lib;
import script.library.chat;
import script.library.groundquests;
import script.library.utils;
import script.*;

public class npe_brawler_bully extends script.base_script
{
    public npe_brawler_bully()
    {
    }
    public static String c_stringFile = "conversation/npe_brawler_bully";
    public boolean npe_brawler_bully_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }
    public boolean npe_brawler_bully_condition_isTaskActiveBully(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.isTaskActive(player, "npe_brawler_2", "killdude");
    }
    public boolean npe_brawler_bully_condition_hasCompletedTaskBully(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.hasCompletedTask(player, "npe_brawler_2", "killdude");
    }
    public void npe_brawler_bully_action_giveSignalBully(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.sendSignal(player, "npe_brawler1_bully_signal");
    }
    public void npe_brawler_bully_action_facePlayer(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
    }
    public int npe_brawler_bully_handleBranch2(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_20"))
        {
            if (npe_brawler_bully_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "pose_proudly");
                string_id message = new string_id(c_stringFile, "s_22");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (npe_brawler_bully_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_24");
                    }
                    utils.setScriptVar(player, "conversation.npe_brawler_bully.branchId", 3);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.npe_brawler_bully.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int npe_brawler_bully_handleBranch3(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_24"))
        {
            if (npe_brawler_bully_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "taken_aback");
                npe_brawler_bully_action_giveSignalBully(player, npc);
                string_id message = new string_id(c_stringFile, "s_26");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (npe_brawler_bully_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_29");
                    }
                    utils.setScriptVar(player, "conversation.npe_brawler_bully.branchId", 4);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.npe_brawler_bully.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int npe_brawler_bully_handleBranch4(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_29"))
        {
            if (npe_brawler_bully_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_30");
                utils.removeScriptVar(player, "conversation.npe_brawler_bully.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if ((!isMob(self)) || (isPlayer(self)))
        {
            detachScript(self, "conversation.npe_brawler_bully");
        }
        setCondition(self, CONDITION_CONVERSABLE);
        setInvulnerable(self, true);
        setName(self, "Cantina Bully");
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setCondition(self, CONDITION_CONVERSABLE);
        setInvulnerable(self, true);
        setName(self, "Cantina Bully");
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
        detachScript(self, "conversation.npe_brawler_bully");
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
        if (npe_brawler_bully_condition_hasCompletedTaskBully(player, npc))
        {
            doAnimationAction(npc, "thumb_up");
            npe_brawler_bully_action_facePlayer(player, npc);
            string_id message = new string_id(c_stringFile, "s_13");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (npe_brawler_bully_condition_isTaskActiveBully(player, npc))
        {
            doAnimationAction(npc, "laugh");
            npe_brawler_bully_action_facePlayer(player, npc);
            string_id message = new string_id(c_stringFile, "s_18");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (npe_brawler_bully_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_20");
                }
                utils.setScriptVar(player, "conversation.npe_brawler_bully.branchId", 2);
                npcStartConversation(player, npc, "npe_brawler_bully", message, responses);
            }
            else 
            {
                chat.chat(npc, player, message);
            }
            return SCRIPT_CONTINUE;
        }
        if (npe_brawler_bully_condition__defaultCondition(player, npc))
        {
            npe_brawler_bully_action_facePlayer(player, npc);
            string_id message = new string_id(c_stringFile, "s_28");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
    {
        if (!conversationId.equals("npe_brawler_bully"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.npe_brawler_bully.branchId");
        if (branchId == 2 && npe_brawler_bully_handleBranch2(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 3 && npe_brawler_bully_handleBranch3(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 4 && npe_brawler_bully_handleBranch4(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.npe_brawler_bully.branchId");
        return SCRIPT_CONTINUE;
    }
}
