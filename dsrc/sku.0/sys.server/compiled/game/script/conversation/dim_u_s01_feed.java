// ======================================================================
//
// dim_u_s01_feed.java
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

public class dim_u_s01_feed extends script.base_script
{
	public dim_u_s01_feed()
	{
	}
// ======================================================================
// Script Constants
// ======================================================================

public static String c_stringFile = "conversation/dim_u_s01_feed";

// ======================================================================
// Script Conditions
// ======================================================================

public boolean dim_u_s01_feed_condition__defaultCondition (obj_id player, obj_id npc) throws InterruptedException
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

int dim_u_s01_feed_handleBranch1 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Ahh I got some bantha dung on my boots... Oh hi!

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Hello. I was told to find this place by a fellow follower of the Dim-U.
	if (response.equals("s_4"))
	{
		//-- [NOTE] 
		if (dim_u_s01_feed_condition__defaultCondition (player, npc))
		{
			//-- NPC: I've heard of a newcomer that just arrived on Taanab. I was just about to go to the the Temple but my extra crates of Bantha feed got stolen!
			string_id message = new string_id (c_stringFile, "s_5");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Bantha Feed? What do Banthas even eat?
			boolean hasResponse0 = false;
			if (dim_u_s01_feed_condition__defaultCondition (player, npc))
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

				utils.setScriptVar (player, "conversation.dim_u_s01_feed.branchId", 2);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.dim_u_s01_feed.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int dim_u_s01_feed_handleBranch2 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: I've heard of a newcomer that just arrived on Taanab. I was just about to go to the the Temple but my extra crates of Bantha feed got stolen!

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Bantha Feed? What do Banthas even eat?
	if (response.equals("s_6"))
	{
		//-- [NOTE] 
		if (dim_u_s01_feed_condition__defaultCondition (player, npc))
		{
			//-- NPC: A mix of plants.. I hope. Ya know this could prove to be some good experience for you.
			string_id message = new string_id (c_stringFile, "s_7");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Experience?
			boolean hasResponse0 = false;
			if (dim_u_s01_feed_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_8");

				utils.setScriptVar (player, "conversation.dim_u_s01_feed.branchId", 3);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.dim_u_s01_feed.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int dim_u_s01_feed_handleBranch3 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: A mix of plants.. I hope. Ya know this could prove to be some good experience for you.

	//-- [RESPONSE NOTE] 
	//-- PLAYER: Experience?
	if (response.equals("s_8"))
	{
		//-- [NOTE] 
		if (dim_u_s01_feed_condition__defaultCondition (player, npc))
		{
			//-- NPC: Feeding Banthas of course!
			string_id message = new string_id (c_stringFile, "s_9");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: I guess.. What do I gotta do...
			boolean hasResponse0 = false;
			if (dim_u_s01_feed_condition__defaultCondition (player, npc))
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
					responses [responseIndex++] = new string_id (c_stringFile, "s_11");

				utils.setScriptVar (player, "conversation.dim_u_s01_feed.branchId", 4);

				npcSpeak (player, message);
				npcSetConversationResponses (player, responses);
			}
			else
			{
				utils.removeScriptVar (player, "conversation.dim_u_s01_feed.branchId");

				npcEndConversationWithMessage (player, message);
			}

			return SCRIPT_CONTINUE;
		}

	}

	return SCRIPT_DEFAULT;
}

// ----------------------------------------------------------------------

int dim_u_s01_feed_handleBranch4 (obj_id player, obj_id npc, string_id response) throws InterruptedException
{
	//-- [BRANCH NOTE] 
	//-- NPC: Feeding Banthas of course!

	//-- [RESPONSE NOTE] 
	//-- PLAYER: I guess.. What do I gotta do...
	if (response.equals("s_11"))
	{
		doAnimationAction (player, "giveup");

		//-- [NOTE] 
		if (dim_u_s01_feed_condition__defaultCondition (player, npc))
		{
			//-- NPC: There is some crates over by one of the shacks with some feed in them. Grab some feed from each and give it to the Banthas!
			string_id message = new string_id (c_stringFile, "s_12");
			utils.removeScriptVar (player, "conversation.dim_u_s01_feed.branchId");

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
		detachScript(self, "conversation.dim_u_s01_feed");
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
	detachScript (self, "conversation.dim_u_s01_feed");

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
	if (dim_u_s01_feed_condition__defaultCondition (player, npc))
	{
		//-- NPC: Ahh I got some bantha dung on my boots... Oh hi!
		string_id message = new string_id (c_stringFile, "s_3");
		int numberOfResponses = 0;

		boolean hasResponse = false;

		//-- PLAYER: Hello. I was told to find this place by a fellow follower of the Dim-U.
		boolean hasResponse0 = false;
		if (dim_u_s01_feed_condition__defaultCondition (player, npc))
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

			utils.setScriptVar (player, "conversation.dim_u_s01_feed.branchId", 1);

			npcStartConversation (player, npc, "dim_u_s01_feed", message, responses);
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
	if (!conversationId.equals("dim_u_s01_feed"))
		return SCRIPT_CONTINUE;

	obj_id npc = self;

	int branchId = utils.getIntScriptVar (player, "conversation.dim_u_s01_feed.branchId");

	if (branchId == 1 && dim_u_s01_feed_handleBranch1 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 2 && dim_u_s01_feed_handleBranch2 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 3 && dim_u_s01_feed_handleBranch3 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	if (branchId == 4 && dim_u_s01_feed_handleBranch4 (player, npc, response) == SCRIPT_CONTINUE)
		return SCRIPT_CONTINUE;

	chat.chat (npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

	utils.removeScriptVar (player, "conversation.dim_u_s01_feed.branchId");

	return SCRIPT_CONTINUE;
}

// ======================================================================

}