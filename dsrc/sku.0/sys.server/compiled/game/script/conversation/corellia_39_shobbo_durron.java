package script.conversation;

import script.library.ai_lib;
import script.library.chat;
import script.library.groundquests;
import script.library.utils;
import script.*;

public class corellia_39_shobbo_durron extends script.base_script
{
    public corellia_39_shobbo_durron()
    {
    }
    public static String c_stringFile = "conversation/corellia_39_shobbo_durron";
    public boolean corellia_39_shobbo_durron_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }
    public boolean corellia_39_shobbo_durron_condition_taskOneActive(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.isQuestActive(player, "corellia_38_sidequest_01");
    }
    public boolean corellia_39_shobbo_durron_condition_taskOneComplete(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.isTaskActive(player, "corellia_39_sidequest_02", "corellia_39_sidequest_02_02") || groundquests.hasCompletedQuest(player, "corellia_39_sidequest_02");
    }
    public void corellia_39_shobbo_durron_action_questGranted(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.grantQuest(player, "corellia_39_sidequest_02");
    }
    public void corellia_39_shobbo_durron_action_taskOneCompletedSignal(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.sendSignal(player, "corellia_39_sidequest_02_02");
    }
    public int corellia_39_shobbo_durron_handleBranch3(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_44"))
        {
            if (corellia_39_shobbo_durron_condition__defaultCondition(player, npc))
            {
                corellia_39_shobbo_durron_action_questGranted(player, npc);
                string_id message = new string_id(c_stringFile, "s_46");
                utils.removeScriptVar(player, "conversation.corellia_39_shobbo_durron.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_45"))
        {
            if (corellia_39_shobbo_durron_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_47");
                utils.removeScriptVar(player, "conversation.corellia_39_shobbo_durron.branchId");
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
            detachScript(self, "conversation.corellia_39_shobbo_durron");
        }
        setCondition(self, CONDITION_CONVERSABLE);
        setCondition(self, CONDITION_INTERESTING);
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setCondition(self, CONDITION_CONVERSABLE);
        setCondition(self, CONDITION_INTERESTING);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info menuInfo) throws InterruptedException
    {
        int menu = menuInfo.addRootMenu(menu_info_types.CONVERSE_START, null);
        menu_info_data menuInfoData = menuInfo.getMenuItemById(menu);
        menuInfoData.setServerNotify(false);
        faceTo(self, player);
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        clearCondition(self, CONDITION_CONVERSABLE);
        detachScript(self, "conversation.corellia_39_shobbo_durron");
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
        if (corellia_39_shobbo_durron_condition_taskOneComplete(player, npc))
        {
            corellia_39_shobbo_durron_action_taskOneCompletedSignal(player, npc);
            string_id message = new string_id(c_stringFile, "s_48");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (corellia_39_shobbo_durron_condition_taskOneActive(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_49");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (corellia_39_shobbo_durron_condition__defaultCondition(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_43");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (corellia_39_shobbo_durron_condition__defaultCondition(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }
            boolean hasResponse1 = false;
            if (corellia_39_shobbo_durron_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_44");
                }
                if (hasResponse1)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_45");
                }
                utils.setScriptVar(player, "conversation.corellia_39_shobbo_durron.branchId", 3);
                npcStartConversation(player, npc, "corellia_39_shobbo_durron", message, responses);
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
        if (!conversationId.equals("corellia_39_shobbo_durron"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.corellia_39_shobbo_durron.branchId");
        if (branchId == 3 && corellia_39_shobbo_durron_handleBranch3(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.corellia_39_shobbo_durron.branchId");
        return SCRIPT_CONTINUE;
    }
}
