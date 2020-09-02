package script.conversation;

import script.library.ai_lib;
import script.library.chat;
import script.library.utils;
import script.*;

public class npe_han_solo_falcon extends script.base_script
{
    public npe_han_solo_falcon()
    {
    }
    public static String c_stringFile = "conversation/npe_han_solo_falcon";
    public boolean npe_han_solo_falcon_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }
    public void npe_han_solo_falcon_action_talkedToHan(obj_id player, obj_id npc) throws InterruptedException
    {
        obj_id building = getTopMostContainer(npc);
        if (isIdValid(building))
        {
            messageTo(building, "playerTalkedToHan", null, 0, false);
        }
    }
    public int npe_han_solo_falcon_handleBranch1(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_6"))
        {
            if (npe_han_solo_falcon_condition__defaultCondition(player, npc))
            {
                npe_han_solo_falcon_action_talkedToHan(player, npc);
                string_id message = new string_id(c_stringFile, "s_8");
                utils.removeScriptVar(player, "conversation.npe_han_solo_falcon.branchId");
                chat.chat(npc, player, message);
                npcEndConversation(player);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_10"))
        {
            if (npe_han_solo_falcon_condition__defaultCondition(player, npc))
            {
                npe_han_solo_falcon_action_talkedToHan(player, npc);
                string_id message = new string_id(c_stringFile, "s_12");
                utils.removeScriptVar(player, "conversation.npe_han_solo_falcon.branchId");
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
            detachScript(self, "conversation.npe_han_solo_falcon");
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
        detachScript(self, "conversation.npe_han_solo_falcon");
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
        if (npe_han_solo_falcon_condition__defaultCondition(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_4");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (npe_han_solo_falcon_condition__defaultCondition(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }
            boolean hasResponse1 = false;
            if (npe_han_solo_falcon_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_6");
                }
                if (hasResponse1)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_10");
                }
                utils.setScriptVar(player, "conversation.npe_han_solo_falcon.branchId", 1);
                npcStartConversation(player, npc, "npe_han_solo_falcon", message, responses);
            }
            else 
            {
                chat.chat(npc, player, message);
            }
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
    {
        if (!conversationId.equals("npe_han_solo_falcon"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.npe_han_solo_falcon.branchId");
        if (branchId == 1 && npe_han_solo_falcon_handleBranch1(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.npe_han_solo_falcon.branchId");
        return SCRIPT_CONTINUE;
    }
}
