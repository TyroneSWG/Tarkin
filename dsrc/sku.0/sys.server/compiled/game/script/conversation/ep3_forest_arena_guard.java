package script.conversation;

import script.library.*;
import script.*;

public class ep3_forest_arena_guard extends script.base_script
{
    public ep3_forest_arena_guard()
    {
    }
    public static String c_stringFile = "conversation/ep3_forest_arena_guard";
    public boolean ep3_forest_arena_guard_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }
    public boolean ep3_forest_arena_guard_condition_TooMany(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.isTaskActive(player, "ep3_forest_kerritamba_epic_6", 0);
    }
    public boolean ep3_forest_arena_guard_condition_hasArenaQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.isQuestActive(player, "ep3_arena_challenge");
    }
    public void ep3_forest_arena_guard_action_selectTicketAndSend(obj_id player, obj_id npc) throws InterruptedException
    {
        space_dungeon.sendGroupToDungeonWithoutTicket(player, space_dungeon.KASH_THE_ARENA, "kashyyyk_dead_forest", "kashyyyk_dead_forest", "arenaChallengeGeneric", npc);
    }
    public int ep3_forest_arena_guard_handleBranch1(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_577"))
        {
            if (ep3_forest_arena_guard_condition__defaultCondition(player, npc))
            {
                ep3_forest_arena_guard_action_selectTicketAndSend(player, npc);
                string_id message = new string_id(c_stringFile, "s_579");
                utils.removeScriptVar(player, "conversation.ep3_forest_arena_guard.branchId");
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
            detachScript(self, "conversation.ep3_forest_arena_guard");
        }
        setCondition(self, CONDITION_CONVERSABLE);
        setName(self, new string_id("ep3/npc_names", "hwruarca"));
        setInvulnerable(self, true);
        if (!hasObjVar(self, "space_dungeon.ticket.point"))
        {
            setObjVar(self, "space_dungeon.ticket.point", "kashyyyk_dead_forest");
        }
        if (!hasObjVar(self, "space_dungeon.ticket.dungeon"))
        {
            setObjVar(self, "space_dungeon.ticket.dungeon", "kash_the_arena");
        }
        if (!hasScript(self, "item.travel_ticket.travel_space_dungeon"))
        {
            attachScript(self, "item.travel_ticket.travel_space_dungeon");
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setCondition(self, CONDITION_CONVERSABLE);
        setName(self, new string_id("ep3/npc_names", "hwruarca"));
        setInvulnerable(self, true);
        if (!hasObjVar(self, "space_dungeon.ticket.point"))
        {
            setObjVar(self, "space_dungeon.ticket.point", "kashyyyk_dead_forest");
        }
        if (!hasObjVar(self, "space_dungeon.ticket.dungeon"))
        {
            setObjVar(self, "space_dungeon.ticket.dungeon", "kash_the_arena");
        }
        if (!hasScript(self, "item.travel_ticket.travel_space_dungeon"))
        {
            attachScript(self, "item.travel_ticket.travel_space_dungeon");
        }
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
        detachScript(self, "conversation.ep3_forest_arena_guard");
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
        if (ep3_forest_arena_guard_condition_hasArenaQuest(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_575");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (ep3_forest_arena_guard_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_577");
                }
                utils.setScriptVar(player, "conversation.ep3_forest_arena_guard.branchId", 1);
                npcStartConversation(player, npc, "ep3_forest_arena_guard", message, responses);
            }
            else 
            {
                chat.chat(npc, player, message);
            }
            return SCRIPT_CONTINUE;
        }
        if (ep3_forest_arena_guard_condition__defaultCondition(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_581");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
    {
        if (!conversationId.equals("ep3_forest_arena_guard"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.ep3_forest_arena_guard.branchId");
        if (branchId == 1 && ep3_forest_arena_guard_handleBranch1(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.ep3_forest_arena_guard.branchId");
        return SCRIPT_CONTINUE;
    }
}
