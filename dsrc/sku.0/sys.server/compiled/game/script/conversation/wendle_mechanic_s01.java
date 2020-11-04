// ======================================================================
//
// wendle_mechanic_s01.java
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

public class wendle_mechanic_s01 extends script.base_script
{
	public wendle_mechanic_s01()
	{
	}
// ======================================================================
// Script Constants
// ======================================================================

public static String c_stringFile = "conversation/wendle_mechanic_s01";

// ======================================================================
// Script Conditions
// ======================================================================

public boolean wendle_mechanic_s01_condition__defaultCondition (obj_id player, obj_id npc) throws InterruptedException
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

int wendle_mechanic_s01_handleBranch1 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: You here to fix my this airspeeder?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: What?
	if (response.equals("s_4"))
	{
		//-- [NOTE] Grant Quest on Reponse
		if (wendle_mechanic_s01_condition__defaultCondition (player, npc))
		{
			//-- NPC: Sorry, I thought you were sent to help me. This old T-47 airspeeder is getting on my nerve! Say, would you mind helping me for a small reward?
			string_id message = new string_id (c_stringFile, "s_6");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Sure!
			boolean hasResponse0 = false;
			if (wendle_mechanic_s01_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_7");

				utils.setScriptVar (player, "conversation.wendle_mechanic_s01.branchId", 2);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wendle_mechanic_s01.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Can't say I am.
	if (response.equals("s_5"))
	{
		//-- [NOTE] 
		if (wendle_mechanic_s01_condition__defaultCondition (player, npc))
		{
			//-- NPC: You here to fix my this airspeeder?
			string_id message = new string_id (c_stringFile, "s_3");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: What?
			boolean hasResponse0 = false;
			if (wendle_mechanic_s01_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: Can't say I am.
			boolean hasResponse1 = false;
			if (wendle_mechanic_s01_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses [] = new string_id [numberOfResponses];

				if (hasResponse0)
					responses [responseIndex++] = new string_id (c_stringFile, "s_4");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_5");

				utils.setScriptVar (player, "conversation.wendle_mechanic_s01.branchId", 1);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.wendle_mechanic_s01.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int wendle_mechanic_s01_handleBranch2 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] Grant Quest on Reponse
	//-- NPC: Sorry, I thought you were sent to help me. This old T-47 airspeeder is getting on my nerve! Say, would you mind helping me for a small reward?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Sure!
	if (response.equals("s_7"))
	{
		//-- [NOTE] 
		if (wendle_mechanic_s01_condition__defaultCondition (player, npc))
		{
			//-- NPC: Wonderful. I need to you find some parts around Pendath. They *should* be free. Not sure though. Anyways, here's the list.
			string_id message = new string_id (c_stringFile, "s_8");
			utils.removeScriptVar (player, "conversation.wendle_mechanic_s01.branchId");

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
		detachScript(self, "conversation.wendle_mechanic_s01");
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
	detachScript (self, "conversation.wendle_mechanic_s01");

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
	if (wendle_mechanic_s01_condition__defaultCondition (player, npc))
	{
		//-- NPC: You here to fix my this airspeeder?
		string_id message = new string_id (c_stringFile, "s_3");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: What?
		boolean hasResponse0 = false;
		if (wendle_mechanic_s01_condition__defaultCondition (player, npc))
		{
			++numberOfResponses;
			hasResponse = true;
			hasResponse0 = true;
		}

		//-- PLAYER: Can't say I am.
		boolean hasResponse1 = false;
		if (wendle_mechanic_s01_condition__defaultCondition (player, npc))
		{
			++numberOfResponses;
			hasResponse = true;
			hasResponse1 = true;
		}

		if (hasResponse)
		{
			int responseIndex = 0;
			string_id responses [] = new string_id [numberOfResponses];

			if (hasResponse0)
				responses [responseIndex++] = new string_id (c_stringFile, "s_4");

			if (hasResponse1)
				responses [responseIndex++] = new string_id (c_stringFile, "s_5");

			utils.setScriptVar (player, "conversation.wendle_mechanic_s01.branchId", 1);

			npcStartConversation (player, npc, "wendle_mechanic_s01", message, responses);
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
	if (!conversationId.equals("wendle_mechanic_s01"))
		return SCRIPT_CONTINUE;

	obj_id npc = self;

	int branchId = utils.getIntScriptVar (player, "conversation.wendle_mechanic_s01.branchId");

	if (branchId == 1 && wendle_mechanic_s01_handleBranch1 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 2 && wendle_mechanic_s01_handleBranch2 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	chat.chat (npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

	utils.removeScriptVar (player, "conversation.wendle_mechanic_s01.branchId");

	return SCRIPT_CONTINUE;
}

// ======================================================================

}