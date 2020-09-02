package script.conversation;

import script.*;
import script.library.ai_lib;
import script.library.chat;
import script.library.utils;

public class ep3_wke_junk_dealer extends script.base_script
{
    public ep3_wke_junk_dealer()
    {
    }
    public static String c_stringFile = "conversation/ep3_wke_junk_dealer";
    public boolean ep3_wke_junk_dealer_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }
    public boolean ep3_wke_junk_dealer_condition_check_inv(obj_id player, obj_id npc) throws InterruptedException
    {
        String datatable = "datatables/npc/junk_dealer/junk_dealer.iff";
        String item = "";
        String itemTemplate = "";
        int itemLength = dataTableGetNumRows(datatable);
        int x = 0;
        int y = 0;
        obj_id inventory = utils.getInventoryContainer(player);
        if (isIdValid(inventory))
        {
            obj_id[] contents = utils.getContents(inventory, true);
            if (contents != null)
            {
                for (x = 0; x < contents.length; x++)
                {
                    y = 0;
                    if (hasObjVar(contents[x], "junkDealer.intPrice"))
                    {
                        return true;
                    }
                    if (!isCrafted(contents[x]))
                    {
                        itemTemplate = getTemplateName(contents[x]);
                        while (y < itemLength)
                        {
                            item = dataTableGetString(datatable, y, "items");
                            if (itemTemplate.equals(item))
                            {
                                return true;
                            }
                            y++;
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean ep3_wke_junk_dealer_condition_cannotSpeakWookiee(obj_id player, obj_id npc) throws InterruptedException
    {
        if (hasSkill(player, "class_smuggler_phase1_novice"))
        {
            return false;
        }
        if (hasSkill(player, "social_language_wookiee_comprehend"))
        {
            return false;
        }
        else 
        {
            return true;
        }
    }
    public void ep3_wke_junk_dealer_action_start_dealing(obj_id player, obj_id npc) throws InterruptedException
    {
        playClientEffectObj(npc, "clienteffect/voc_wookiee_med_4sec.cef", player, "");
        dictionary params = new dictionary();
        params.put("player", player);
        messageTo(npc, "startDealing", params, 1.0f, false);
    }
    public void ep3_wke_junk_dealer_action_vocalizeMed(obj_id player, obj_id npc) throws InterruptedException
    {
        playClientEffectObj(npc, "clienteffect/voc_wookiee_med_4sec.cef", player, "");
    }
    public int ep3_wke_junk_dealer_handleBranch2(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_54fab04f"))
        {
            if (ep3_wke_junk_dealer_condition__defaultCondition(player, npc))
            {
                ep3_wke_junk_dealer_action_start_dealing(player, npc);
                string_id message = new string_id(c_stringFile, "s_84a67771");
                utils.removeScriptVar(player, "conversation.ep3_wke_junk_dealer.branchId");
                chat.chat(npc, player, message);
                npcEndConversation(player);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_cd7a3f41"))
        {
            if (ep3_wke_junk_dealer_condition__defaultCondition(player, npc))
            {
                ep3_wke_junk_dealer_action_vocalizeMed(player, npc);
                string_id message = new string_id(c_stringFile, "s_4bd9d15e");
                utils.removeScriptVar(player, "conversation.ep3_wke_junk_dealer.branchId");
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
            detachScript(self, "conversation.ep3_wke_junk_dealer");
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
        faceTo(self, player);
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        clearCondition(self, CONDITION_CONVERSABLE);
        detachScript(self, "conversation.ep3_wke_junk_dealer");
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
        if (ep3_wke_junk_dealer_condition_cannotSpeakWookiee(player, npc))
        {
            ep3_wke_junk_dealer_action_vocalizeMed(player, npc);
            string_id message = new string_id(c_stringFile, "s_7");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (ep3_wke_junk_dealer_condition__defaultCondition(player, npc))
        {
            ep3_wke_junk_dealer_action_vocalizeMed(player, npc);
            string_id message = new string_id(c_stringFile, "s_bef51e38");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (ep3_wke_junk_dealer_condition_check_inv(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }
            boolean hasResponse1 = false;
            if (ep3_wke_junk_dealer_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_54fab04f");
                }
                if (hasResponse1)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_cd7a3f41");
                }
                utils.setScriptVar(player, "conversation.ep3_wke_junk_dealer.branchId", 2);
                npcStartConversation(player, npc, "ep3_wke_junk_dealer", message, responses);
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
        if (!conversationId.equals("ep3_wke_junk_dealer"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.ep3_wke_junk_dealer.branchId");
        if (branchId == 2 && ep3_wke_junk_dealer_handleBranch2(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.ep3_wke_junk_dealer.branchId");
        return SCRIPT_CONTINUE;
    }
}
