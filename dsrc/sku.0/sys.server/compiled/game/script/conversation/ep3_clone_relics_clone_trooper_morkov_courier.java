package script.conversation;

import script.library.ai_lib;
import script.library.chat;
import script.library.groundquests;
import script.library.utils;
import script.*;

public class ep3_clone_relics_clone_trooper_morkov_courier extends script.base_script
{
    public ep3_clone_relics_clone_trooper_morkov_courier()
    {
    }
    public static String c_stringFile = "conversation/ep3_clone_relics_clone_trooper_morkov_courier";
    public boolean ep3_clone_relics_clone_trooper_morkov_courier_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }
    public boolean ep3_clone_relics_clone_trooper_morkov_courier_condition_courierChat(obj_id player, obj_id npc) throws InterruptedException
    {
        return (groundquests.isTaskActive(player, "ep3_clone_relics_clone_trooper_mort_1", "meetingCourier"));
    }
    public void ep3_clone_relics_clone_trooper_morkov_courier_action_fight(obj_id player, obj_id npc) throws InterruptedException
    {
        groundquests.sendSignal(player, "talkedToCourier");
        setInvulnerable(npc, false);
        clearCondition(npc, CONDITION_CONVERSABLE);
    }
    public int ep3_clone_relics_clone_trooper_morkov_courier_handleBranch1(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_139"))
        {
            if (ep3_clone_relics_clone_trooper_morkov_courier_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "point_accusingly");
                string_id message = new string_id(c_stringFile, "s_141");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (ep3_clone_relics_clone_trooper_morkov_courier_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_142");
                    }
                    utils.setScriptVar(player, "conversation.ep3_clone_relics_clone_trooper_morkov_courier.branchId", 2);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.ep3_clone_relics_clone_trooper_morkov_courier.branchId");
                    chat.chat(npc, player, message);
                    npcEndConversation(player);
                }
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_140"))
        {
            doAnimationAction(player, "shrug_shoulders");
            if (ep3_clone_relics_clone_trooper_morkov_courier_condition__defaultCondition(player, npc))
            {
                ep3_clone_relics_clone_trooper_morkov_courier_action_fight(player, npc);
                string_id message = new string_id(c_stringFile, "s_143");
                utils.removeScriptVar(player, "conversation.ep3_clone_relics_clone_trooper_morkov_courier.branchId");
                chat.chat(npc, player, message);
                npcEndConversation(player);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int ep3_clone_relics_clone_trooper_morkov_courier_handleBranch2(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_142"))
        {
            if (ep3_clone_relics_clone_trooper_morkov_courier_condition__defaultCondition(player, npc))
            {
                ep3_clone_relics_clone_trooper_morkov_courier_action_fight(player, npc);
                string_id message = new string_id(c_stringFile, "s_143");
                utils.removeScriptVar(player, "conversation.ep3_clone_relics_clone_trooper_morkov_courier.branchId");
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
            detachScript(self, "conversation.ep3_clone_relics_clone_trooper_morkov_courier");
        }
        setCondition(self, CONDITION_CONVERSABLE);
        setInvulnerable(self, true);
        setName(self, new string_id("ep3/npc_names", "clone_relics_morkov_courier"));
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setCondition(self, CONDITION_CONVERSABLE);
        setInvulnerable(self, true);
        setName(self, new string_id("ep3/npc_names", "clone_relics_morkov_courier"));
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
        detachScript(self, "conversation.ep3_clone_relics_clone_trooper_morkov_courier");
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
        if (ep3_clone_relics_clone_trooper_morkov_courier_condition_courierChat(player, npc))
        {
            doAnimationAction(npc, "nod_head_once");
            doAnimationAction(player, "nod_head_once");
            string_id message = new string_id(c_stringFile, "s_138");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (ep3_clone_relics_clone_trooper_morkov_courier_condition__defaultCondition(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }
            boolean hasResponse1 = false;
            if (ep3_clone_relics_clone_trooper_morkov_courier_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_139");
                }
                if (hasResponse1)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_140");
                }
                utils.setScriptVar(player, "conversation.ep3_clone_relics_clone_trooper_morkov_courier.branchId", 1);
                npcStartConversation(player, npc, "ep3_clone_relics_clone_trooper_morkov_courier", message, responses);
            }
            else 
            {
                chat.chat(npc, player, message);
            }
            return SCRIPT_CONTINUE;
        }
        if (ep3_clone_relics_clone_trooper_morkov_courier_condition__defaultCondition(player, npc))
        {
            doAnimationAction(npc, "slit_throat");
            string_id message = new string_id(c_stringFile, "s_137");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
    {
        if (!conversationId.equals("ep3_clone_relics_clone_trooper_morkov_courier"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.ep3_clone_relics_clone_trooper_morkov_courier.branchId");
        if (branchId == 1 && ep3_clone_relics_clone_trooper_morkov_courier_handleBranch1(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 2 && ep3_clone_relics_clone_trooper_morkov_courier_handleBranch2(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.ep3_clone_relics_clone_trooper_morkov_courier.branchId");
        return SCRIPT_CONTINUE;
    }
}
