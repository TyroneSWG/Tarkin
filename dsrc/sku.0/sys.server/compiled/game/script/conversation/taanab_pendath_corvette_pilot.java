// ======================================================================
//
// taanab_pendath_corvette_pilot.java
// Copyright 2004-2020, Sony Online Entertainment
// All Rights Reserved.
//
// Created with SwgConversationEditor 1.37 - DO NOT EDIT THIS AUTO-GENERATED FILE!
//Roachie says herro UwU
// ======================================================================

package script.conversation;

// ======================================================================
// Library Includes
// ======================================================================

import script.library.ai_lib;
import script.library.chat;
import script.library.conversation;
import script.library.utils;
import script.*;

public class taanab_pendath_corvette_pilot extends script.base_script
{
	public taanab_pendath_corvette_pilot()

	{

	}

// ======================================================================
// Script Constants
// ======================================================================

public static String c_stringFile = "conversation/taanab_pendath_corvette_pilot";

// ======================================================================
// Script Conditions
// ======================================================================

public boolean taanab_pendath_corvette_pilot_condition__defaultCondition (obj_id player, obj_id npc) throws InterruptedException
{
	return true;
}

// ======================================================================
// Script Actions
// ======================================================================

// ======================================================================
// Script %TO Tokens
// ======================================================================

// ======================================================================
// Script %DI Tokens
// ======================================================================

// ======================================================================
// Script %DF Tokens
// ======================================================================

// ======================================================================
// handleBranch<n> Functions 
// ======================================================================

int taanab_pendath_corvette_pilot_handleBranch1 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: What can I do fer' ya?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I need transport to the Corvette hovering above Pendath.
	if (response.equals("s_4"))
	{
		//-- [NOTE] 
		if (taanab_pendath_corvette_pilot_condition__defaultCondition (player, npc))
		{
			//-- NPC: Hmm. Well if it's transport you seek I can help you out. For a price! [2500 Credits]
			string_id message = new string_id (c_stringFile, "s_5");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: [Accept]
			boolean hasResponse0 = false;
			if (taanab_pendath_corvette_pilot_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses [] = new string_id [numberOfResponses];

				if (hasResponse0)
					responses [responseIndex++] = new string_id (c_stringFile, "s_6");

				utils.setScriptVar (player, "conversation.taanab_pendath_corvette_pilot.branchId", 2);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.taanab_pendath_corvette_pilot.branchId");
				obj_id[] targetLocs = getAllObjectsWithTemplate(getLocation(self), 16000.0f, "object/building/general/space_dungeon_corellian_corvette_base.iffs");
				obj_id cell = getCellId(targetLocs[0], "hall2");
				warpPlayer(player, getCurrentSceneName(), -35.1f, 0.0f, 0.0f, cell, -35.1f, 0.0f, 0.0f);
				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int taanab_pendath_corvette_pilot_handleBranch2 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Hmm. Well if it's transport you seek I can help you out. For a price! [2500 Credits]

	//-- [RESPONSE NOTE] 
	//-- PLAYER: [Accept]
	if (response.equals("s_6"))
	{
		//-- [NOTE] 
		if (taanab_pendath_corvette_pilot_condition__defaultCondition (player, npc))
		{
			//-- NPC: [Decline]
			string_id message = new string_id (c_stringFile, "s_7");
			utils.removeScriptVar (player, "conversation.taanab_pendath_corvette_pilot.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

// ======================================================================
// User Script Triggers
// ======================================================================

public int OnInitialize(obj_id self) throws InterruptedException
{
	if ((!isMob (self)) || (isPlayer (self)))
	{
		detachScript(self, "conversation.taanab_pendath_corvette_pilot");
	}

	setCondition (self, CONDITION_CONVERSABLE);

	return SCRIPT_CONTINUE;
}

public int OnAttach(obj_id self) throws InterruptedException
{
	setCondition (self, CONDITION_CONVERSABLE);

	return SCRIPT_CONTINUE;
}

public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info menuInfo) throws InterruptedException
{
	int menu = menuInfo.addRootMenu (menu_info_types.CONVERSE_START, null);
	menu_info_data menuInfoData = menuInfo.getMenuItemById (menu);
	menuInfoData.setServerNotify (false);
	setCondition (self, CONDITION_CONVERSABLE);

	return SCRIPT_CONTINUE;
}

public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
{
	clearCondition (self, CONDITION_CONVERSABLE);
	detachScript (self, "conversation.taanab_pendath_corvette_pilot");

	return SCRIPT_CONTINUE;
}


// ======================================================================
// Script Triggers
// ======================================================================

//-- This function should move to base_class.java
boolean npcStartConversation(obj_id player, obj_id npc, String convoName, string_id greetingId, prose_package greetingProse, string_id[] responses)
{
	Object[] objects = new Object[responses.length];
	System.arraycopy(responses, 0, objects, 0, responses.length);
	return npcStartConversation(player, npc, convoName, greetingId, greetingProse, objects);
}

// ----------------------------------------------------------------------

public int OnStartNpcConversation(obj_id self, obj_id player) throws InterruptedException
{
	obj_id npc = self;

	if (ai_lib.isInCombat (npc) || ai_lib.isInCombat (player))
		return SCRIPT_OVERRIDE;

	//-- [NOTE] 
	if (taanab_pendath_corvette_pilot_condition__defaultCondition (player, npc))
	{
		//-- NPC: What can I do fer' ya?
		string_id message = new string_id (c_stringFile, "s_3");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: I need transport to the Corvette hovering above Pendath.
		boolean hasResponse0 = false;
		if (taanab_pendath_corvette_pilot_condition__defaultCondition (player, npc))
		{
			++numberOfResponses;
			hasResponse = true;
			hasResponse0 = true;
		}

		if (hasResponse)
		{
			int responseIndex = 0;
			string_id responses [] = new string_id [numberOfResponses];

			if (hasResponse0)
				responses [responseIndex++] = new string_id (c_stringFile, "s_4");

			utils.setScriptVar (player, "conversation.taanab_pendath_corvette_pilot.branchId", 1);

			npcStartConversation (player, npc, "taanab_pendath_corvette_pilot", message, responses);
		}
		else
		{
			chat.chat (npc, player, message);
		}

		return SCRIPT_CONTINUE;
	}

	chat.chat (npc, "Error:  All conditions for OnStartNpcConversation were false.");

	return SCRIPT_CONTINUE;
}

// ----------------------------------------------------------------------

public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
{
	if (!conversationId.equals("taanab_pendath_corvette_pilot"))
		return SCRIPT_CONTINUE;

	obj_id npc = self;

	int branchId = utils.getIntScriptVar (player, "conversation.taanab_pendath_corvette_pilot.branchId");

	if (branchId == 1 && taanab_pendath_corvette_pilot_handleBranch1 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 2 && taanab_pendath_corvette_pilot_handleBranch2 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	chat.chat (npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

	utils.removeScriptVar (player, "conversation.taanab_pendath_corvette_pilot.branchId");

	return SCRIPT_CONTINUE;
}

// ======================================================================

}