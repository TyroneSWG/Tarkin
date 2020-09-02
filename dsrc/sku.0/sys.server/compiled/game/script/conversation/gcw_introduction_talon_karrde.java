package script.conversation;

import script.library.ai_lib;
import script.library.chat;
import script.library.groundquests;
import script.library.utils;
import script.*;

public class gcw_introduction_talon_karrde extends script.base_script
{
    public gcw_introduction_talon_karrde()
    {
    }
    public static String c_stringFile = "conversation/gcw_introduction_talon_karrde";
    public boolean gcw_introduction_talon_karrde_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }
    public boolean gcw_introduction_talon_karrde_condition_hasIntroTask(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return groundquests.isTaskActive(player, "gcw_introduction_quest", "contactSmuggler");
    }
    public boolean gcw_introduction_talon_karrde_condition_completedIntroNotNextStep(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return groundquests.hasCompletedQuest(player, "gcw_introduction_quest") && !groundquests.hasCompletedQuest(player, "gcw_introduction_rebel_tatooine") && !groundquests.hasCompletedQuest(player, "gcw_introduction_imperial_tatooine");
    }
    public boolean gcw_introduction_talon_karrde_condition_hasToken(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return true;
    }
    public void gcw_introduction_talon_karrde_action_grantImperialQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
    }
    public void gcw_introduction_talon_karrde_action_grantRebelQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
    }
    public void gcw_introduction_talon_karrde_action_giveBadge(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
    }
    public int gcw_introduction_talon_karrde_handleBranch1(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_50"))
        {
            if (gcw_introduction_talon_karrde_condition_hasToken(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_51");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_53");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 2);
                    prose_package pp = new prose_package();
                    pp.stringId = message;
                    pp.actor.set(player);
                    pp.target.set(npc);
                    npcSpeak(player, pp);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    prose_package pp = new prose_package();
                    pp.stringId = message;
                    pp.actor.set(player);
                    pp.target.set(npc);
                    npcEndConversationWithMessage(player, pp);
                }
                return SCRIPT_CONTINUE;
            }
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_52");
                utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int gcw_introduction_talon_karrde_handleBranch2(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_53"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "handshake_tandem");
                doAnimationAction(player, "handshake_tandem");
                gcw_introduction_talon_karrde_action_giveBadge(player, npc);
                string_id message = new string_id(c_stringFile, "s_54");
                utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                prose_package pp = new prose_package();
                pp.stringId = message;
                pp.actor.set(player);
                pp.target.set(npc);
                npcEndConversationWithMessage(player, pp);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int gcw_introduction_talon_karrde_handleBranch5(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_43"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_44");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                boolean hasResponse1 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse1 = true;
                }
                boolean hasResponse2 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse2 = true;
                }
                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses[] = new string_id[numberOfResponses];
                    if (hasResponse0)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_45");
                    }
                    if (hasResponse1)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_46");
                    }
                    if (hasResponse2)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_47");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 6);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int gcw_introduction_talon_karrde_handleBranch6(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_45"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_27");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_28");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 16);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_46"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_34");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                boolean hasResponse1 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_36");
                    }
                    if (hasResponse1)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_37");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 19);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_47"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_35");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                boolean hasResponse1 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_39");
                    }
                    if (hasResponse1)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_41");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 21);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int gcw_introduction_talon_karrde_handleBranch7(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_5"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_6");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                boolean hasResponse1 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_7");
                    }
                    if (hasResponse1)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_8");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 8);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int gcw_introduction_talon_karrde_handleBranch8(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_7"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_10");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_11");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 9);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_8"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_12");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_11");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 9);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int gcw_introduction_talon_karrde_handleBranch9(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_11"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_13");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_14");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 10);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int gcw_introduction_talon_karrde_handleBranch10(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_14"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_15");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_16");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 11);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int gcw_introduction_talon_karrde_handleBranch11(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_16"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_17");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_18");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 12);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int gcw_introduction_talon_karrde_handleBranch12(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_18"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_19");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_20");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 13);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int gcw_introduction_talon_karrde_handleBranch13(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_20"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_21");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_22");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 14);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int gcw_introduction_talon_karrde_handleBranch14(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_22"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_23");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                boolean hasResponse1 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse1 = true;
                }
                boolean hasResponse2 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse2 = true;
                }
                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses[] = new string_id[numberOfResponses];
                    if (hasResponse0)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_24");
                    }
                    if (hasResponse1)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_25");
                    }
                    if (hasResponse2)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_26");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 15);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int gcw_introduction_talon_karrde_handleBranch15(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_24"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_27");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_28");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 16);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_25"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_34");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                boolean hasResponse1 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_36");
                    }
                    if (hasResponse1)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_37");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 19);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_26"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_35");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                boolean hasResponse1 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_39");
                    }
                    if (hasResponse1)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_41");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 21);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int gcw_introduction_talon_karrde_handleBranch16(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_28"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_29");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_30");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 17);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int gcw_introduction_talon_karrde_handleBranch17(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_30"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_31");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                boolean hasResponse1 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_32");
                    }
                    if (hasResponse1)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_33");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 18);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int gcw_introduction_talon_karrde_handleBranch18(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_32"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_34");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                boolean hasResponse1 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_36");
                    }
                    if (hasResponse1)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_37");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 19);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_33"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_35");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                boolean hasResponse1 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_39");
                    }
                    if (hasResponse1)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_41");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 21);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int gcw_introduction_talon_karrde_handleBranch19(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_36"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_38");
                utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_37"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_31");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                boolean hasResponse1 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_32");
                    }
                    if (hasResponse1)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_33");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 18);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int gcw_introduction_talon_karrde_handleBranch21(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_39"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_40");
                utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_41"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_31");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                boolean hasResponse1 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_32");
                    }
                    if (hasResponse1)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_33");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 18);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int gcw_introduction_talon_karrde_handleBranch23(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_11"))
        {
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_13");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_14");
                    }
                    utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 10);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if ((!isTangible(self)) || (isPlayer(self)))
        {
            detachScript(self, "conversation.gcw_introduction_talon_karrde");
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
        detachScript(self, "conversation.gcw_introduction_talon_karrde");
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
        if (gcw_introduction_talon_karrde_condition_hasToken(player, npc))
        {
            doAnimationAction(npc, "nod");
            string_id message = new string_id(c_stringFile, "s_49");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_50");
                }
                utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 1);
                prose_package pp = new prose_package();
                pp.stringId = message;
                pp.actor.set(player);
                pp.target.set(npc);
                npcStartConversation(player, npc, "gcw_introduction_talon_karrde", null, pp, responses);
            }
            else 
            {
                prose_package pp = new prose_package();
                pp.stringId = message;
                pp.actor.set(player);
                pp.target.set(npc);
                chat.chat(npc, player, null, null, pp);
            }
            return SCRIPT_CONTINUE;
        }
        if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
        {
            doAnimationAction(player, "shake_head_disgust");
            string_id message = new string_id(c_stringFile, "s_42");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_43");
                }
                utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 5);
                npcStartConversation(player, npc, "gcw_introduction_talon_karrde", message, responses);
            }
            else 
            {
                chat.chat(npc, player, message);
            }
            return SCRIPT_CONTINUE;
        }
        if (gcw_introduction_talon_karrde_condition_hasIntroTask(player, npc))
        {
            doAnimationAction(npc, "nod");
            string_id message = new string_id(c_stringFile, "s_4");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (gcw_introduction_talon_karrde_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_5");
                }
                utils.setScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId", 7);
                npcStartConversation(player, npc, "gcw_introduction_talon_karrde", message, responses);
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
        if (!conversationId.equals("gcw_introduction_talon_karrde"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
        if (branchId == 1 && gcw_introduction_talon_karrde_handleBranch1(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 2 && gcw_introduction_talon_karrde_handleBranch2(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 5 && gcw_introduction_talon_karrde_handleBranch5(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 6 && gcw_introduction_talon_karrde_handleBranch6(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 7 && gcw_introduction_talon_karrde_handleBranch7(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 8 && gcw_introduction_talon_karrde_handleBranch8(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 9 && gcw_introduction_talon_karrde_handleBranch9(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 10 && gcw_introduction_talon_karrde_handleBranch10(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 11 && gcw_introduction_talon_karrde_handleBranch11(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 12 && gcw_introduction_talon_karrde_handleBranch12(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 13 && gcw_introduction_talon_karrde_handleBranch13(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 14 && gcw_introduction_talon_karrde_handleBranch14(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 15 && gcw_introduction_talon_karrde_handleBranch15(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 16 && gcw_introduction_talon_karrde_handleBranch16(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 17 && gcw_introduction_talon_karrde_handleBranch17(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 18 && gcw_introduction_talon_karrde_handleBranch18(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 19 && gcw_introduction_talon_karrde_handleBranch19(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 21 && gcw_introduction_talon_karrde_handleBranch21(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 23 && gcw_introduction_talon_karrde_handleBranch23(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.gcw_introduction_talon_karrde.branchId");
        return SCRIPT_CONTINUE;
    }
}
