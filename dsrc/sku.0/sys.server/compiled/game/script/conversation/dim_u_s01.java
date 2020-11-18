// ======================================================================
//
// dim_u_s01.java
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

public class dim_u_s01 extends script.base_script
{
	public dim_u_s01()
	{
	}
// ======================================================================
// Script Constants
// ======================================================================

public static String c_stringFile = "conversation/dim_u_s01";

// ======================================================================
// Script Conditions
// ======================================================================

public boolean dim_u_s01_condition__defaultCondition (obj_id player, obj_id npc) throws InterruptedException
{
	return true;
}

public boolean dim_u_s01_condition__hasQuest (obj_id player, obj_id npc) throws InterruptedException
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

int dim_u_s01_handleBranch1 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Hello.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Who are you?
	if (response.equals("s_6"))
	{
		//-- [NOTE] 
		if (dim_u_s01_condition__defaultCondition (player, npc))
		{
			//-- NPC: I am Jerooba Vir. I arrived here on Taanab a while ago. I am looking for followers of Dim-U to join me in worship.
			string_id message = new string_id (c_stringFile, "s_8");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: What do you worship?
			boolean hasResponse0 = false;
			if (dim_u_s01_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_10");

				utils.setScriptVar (player, "conversation.dim_u_s01.branchId", 2);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.dim_u_s01.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int dim_u_s01_handleBranch2 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: I am Jerooba Vir. I arrived here on Taanab a while ago. I am looking for followers of Dim-U to join me in worship.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: What do you worship?
	if (response.equals("s_10"))
	{
		//-- [NOTE] 
		if (dim_u_s01_condition__defaultCondition (player, npc))
		{
			//-- NPC: Banthas!
			string_id message = new string_id (c_stringFile, "s_12");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Banthas? On second thought, I gotta go!
			boolean hasResponse0 = false;
			if (!dim_u_s01_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: Interesting! Tell me more!
			boolean hasResponse1 = false;
			if (dim_u_s01_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_14");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_16");

				utils.setScriptVar (player, "conversation.dim_u_s01.branchId", 3);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.dim_u_s01.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int dim_u_s01_handleBranch3 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Banthas!

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Banthas? On second thought, I gotta go!
	if (response.equals("s_14"))
	{
		//-- [NOTE] 
		if (dim_u_s01_condition__defaultCondition (player, npc))
		{
			doAnimationAction (npc, "wave1");

			//-- NPC: Hello.
			string_id message = new string_id (c_stringFile, "s_4");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Who are you?
			boolean hasResponse0 = false;
			if (dim_u_s01_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.dim_u_s01.branchId", 1);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.dim_u_s01.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Interesting! Tell me more!
	if (response.equals("s_16"))
	{
		//-- [NOTE] 
		if (dim_u_s01_condition__defaultCondition (player, npc))
		{
			//-- NPC: We have a deep spiritual belief that the Banthas are the ones who bless the galaxy. Are you interested in joining?
			string_id message = new string_id (c_stringFile, "s_18");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Yes
			boolean hasResponse0 = false;
			if (dim_u_s01_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: No
			boolean hasResponse1 = false;
			if (!dim_u_s01_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_20");

				if (hasResponse1)
					responses [responseIndex++] = new string_id (c_stringFile, "s_24");

				utils.setScriptVar (player, "conversation.dim_u_s01.branchId", 4);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.dim_u_s01.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int dim_u_s01_handleBranch4 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: We have a deep spiritual belief that the Banthas are the ones who bless the galaxy. Are you interested in joining?

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Yes
	if (response.equals("s_20"))
	{
		//-- [NOTE] 
		if (dim_u_s01_condition__defaultCondition (player, npc))
		{
			//-- NPC: Oh wonderful! Here is a an orientation packet. Once you complete this you will have access to the sanctuary. 
			string_id message = new string_id (c_stringFile, "s_22");
			utils.removeScriptVar (player, "conversation.dim_u_s01.branchId");

			npcEndConversationWithMessage (player, message);

			return SCRIPT_CONTINUE;
		}

	}

	//-- [RESPONSE NOTE] 
	//-- PLAYER: No
	if (response.equals("s_24"))
	{
		//-- [NOTE] 
		if (dim_u_s01_condition__defaultCondition (player, npc))
		{
			doAnimationAction (npc, "wave1");

			//-- NPC: Hello.
			string_id message = new string_id (c_stringFile, "s_4");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Who are you?
			boolean hasResponse0 = false;
			if (dim_u_s01_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.dim_u_s01.branchId", 1);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.dim_u_s01.branchId");

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
		detachScript(self, "conversation.dim_u_s01");
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
	detachScript (self, "conversation.dim_u_s01");

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
	if (dim_u_s01_condition__defaultCondition (player, npc))
	{
		doAnimationAction (npc, "wave1");

		//-- NPC: Hello.
		string_id message = new string_id (c_stringFile, "s_4");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: Who are you?
		boolean hasResponse0 = false;
		if (dim_u_s01_condition__defaultCondition (player, npc))
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

			utils.setScriptVar (player, "conversation.dim_u_s01.branchId", 1);

			npcStartConversation (player, npc, "dim_u_s01", message, responses);
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
	if (!conversationId.equals("dim_u_s01"))
	{
		return SCRIPT_CONTINUE;
	}
	obj_id npc = self;

	int branchId = utils.getIntScriptVar (player, "conversation.dim_u_s01.branchId");

	if (branchId == 1 && dim_u_s01_handleBranch1 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 2 && dim_u_s01_handleBranch2 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 3 && dim_u_s01_handleBranch3 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 4 && dim_u_s01_handleBranch4 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	chat.chat (npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

	utils.removeScriptVar (player, "conversation.dim_u_s01.branchId");

	return SCRIPT_CONTINUE;
}

// ======================================================================

}