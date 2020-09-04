// ======================================================================
//
// wendle_bar.java
// Copyright 2004, Sony Online Entertainment
// All Rights Reserved.
//
// Created with SwgConversationEditor 1.37 - DO NOT EDIT THIS AUTO-GENERATED FILE!
// blour says hi
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

public class wendle_bar extends script.base_script
{
	public wendle_bar()

	{

	}

// ======================================================================
// Script Constants
// ======================================================================

public static String c_stringFile = "conversation/wendle_bar";

// ======================================================================
// Script Conditions
// ======================================================================

public boolean wendle_bar_condition__defaultCondition (obj_id player, obj_id npc) throws InterruptedException
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

int wendle_bar_handleBranch1 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Welcome to Wendle's Casino! What can I get you?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Blue Milk
	if (response.equals("s_6"))
	{
		//-- [NOTE] 
		if (wendle_bar_condition__defaultCondition (player, npc))
		{
			doAnimationAction (player, "celebrate");

			//-- NPC: Welcome to Wendle's Casino! What can I get you?
			string_id message = new string_id (c_stringFile, "s_4");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Blue Milk
			boolean hasResponse0 = false;
			if (wendle_bar_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: Water
			boolean hasResponse1 = false;
			if (wendle_bar_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			//-- PLAYER: Wendle's House Special
			boolean hasResponse2 = false;
			if (wendle_bar_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse2 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses [] = new string_id [numberOfResponses];

				if (hasResponse0)
					responses [responseIndex++] = new string_id (c_stringFile, "s_6");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_8");

				if (hasResponse2)
					responses [responseIndex++] = new string_id (c_stringFile, "s_10");

				utils.setScriptVar (player, "conversation.wendle_bar.branchId", 1);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wendle_bar.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Water
	if (response.equals("s_8"))
	{
		//-- [NOTE] 
		if (wendle_bar_condition__defaultCondition (player, npc))
		{
			doAnimationAction (player, "celebrate");

			//-- NPC: Welcome to Wendle's Casino! What can I get you?
			string_id message = new string_id (c_stringFile, "s_4");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Blue Milk
			boolean hasResponse0 = false;
			if (wendle_bar_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: Water
			boolean hasResponse1 = false;
			if (wendle_bar_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			//-- PLAYER: Wendle's House Special
			boolean hasResponse2 = false;
			if (wendle_bar_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse2 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses [] = new string_id [numberOfResponses];

				if (hasResponse0)
					responses [responseIndex++] = new string_id (c_stringFile, "s_6");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_8");

				if (hasResponse2)
					responses [responseIndex++] = new string_id (c_stringFile, "s_10");

				utils.setScriptVar (player, "conversation.wendle_bar.branchId", 1);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wendle_bar.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Wendle's House Special
	if (response.equals("s_10"))
	{
		//-- [NOTE] 
		if (wendle_bar_condition__defaultCondition (player, npc))
		{
			doAnimationAction (player, "celebrate");

			//-- NPC: Welcome to Wendle's Casino! What can I get you?
			string_id message = new string_id (c_stringFile, "s_4");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Blue Milk
			boolean hasResponse0 = false;
			if (wendle_bar_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: Water
			boolean hasResponse1 = false;
			if (wendle_bar_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			//-- PLAYER: Wendle's House Special
			boolean hasResponse2 = false;
			if (wendle_bar_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse2 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses [] = new string_id [numberOfResponses];

				if (hasResponse0)
					responses [responseIndex++] = new string_id (c_stringFile, "s_6");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_8");

				if (hasResponse2)
					responses [responseIndex++] = new string_id (c_stringFile, "s_10");

				utils.setScriptVar (player, "conversation.wendle_bar.branchId", 1);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wendle_bar.branchId");

				npcEndConversationWithMessage (player, message);
			}

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
		detachScript(self, "conversation.wendle_bar");
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
	detachScript (self, "conversation.wendle_bar");

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
	if (wendle_bar_condition__defaultCondition (player, npc))
	{
		doAnimationAction (player, "celebrate");

		//-- NPC: Welcome to Wendle's Casino! What can I get you?
		string_id message = new string_id (c_stringFile, "s_4");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: Blue Milk
		boolean hasResponse0 = false;
		if (wendle_bar_condition__defaultCondition (player, npc))
		{
			++numberOfResponses;
			hasResponse = true;
			hasResponse0 = true;
		}

		//-- PLAYER: Water
		boolean hasResponse1 = false;
		if (wendle_bar_condition__defaultCondition (player, npc))
		{
			++numberOfResponses;
			hasResponse = true;
			hasResponse1 = true;
		}

		//-- PLAYER: Wendle's House Special
		boolean hasResponse2 = false;
		if (wendle_bar_condition__defaultCondition (player, npc))
		{
			++numberOfResponses;
			hasResponse = true;
			hasResponse2 = true;
		}

		if (hasResponse)
		{
			int responseIndex = 0;
			string_id responses [] = new string_id [numberOfResponses];

			if (hasResponse0)
				responses [responseIndex++] = new string_id (c_stringFile, "s_6");

			if (hasResponse1)
				responses [responseIndex++] = new string_id (c_stringFile, "s_8");

			if (hasResponse2)
				responses [responseIndex++] = new string_id (c_stringFile, "s_10");

			utils.setScriptVar (player, "conversation.wendle_bar.branchId", 1);

			npcStartConversation (player, npc, "wendle_bar", message, responses);
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
	if (!conversationId.equals("wendle_bar"))
		return SCRIPT_CONTINUE;

	obj_id npc = self;

	int branchId = utils.getIntScriptVar (player, "conversation.wendle_bar.branchId");

	if (branchId == 1 && wendle_bar_handleBranch1 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	chat.chat (npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

	utils.removeScriptVar (player, "conversation.wendle_bar.branchId");

	return SCRIPT_CONTINUE;
}

// ======================================================================

}