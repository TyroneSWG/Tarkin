package script.npc.celebrity;

import script.dictionary;
import script.library.ai_lib;
import script.library.chat;
import script.library.utils;
import script.obj_id;
import script.string_id;

public class jinkins extends script.base_script
{
    public jinkins()
    {
    }
    public static final String CONVO = "celebrity/jinkins";
    public static final String FACETO_VOLUME_NAME = "faceToTriggerVolume";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "Jinkins");
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        setInvulnerable(self, false);
        debugSpeakMsg(self, "I work for Nym...");
        attachScript(self, "npc.converse.npc_converse_menu");
        createTriggerVolume(FACETO_VOLUME_NAME, 8.0f, true);
        messageTo(self, "doISetup", null, 2, false);
        return SCRIPT_CONTINUE;
    }
    public int OnStartNpcConversation(obj_id self, obj_id player) throws InterruptedException
    {
        String questInfo = "tell_me_quest";
        String hello_line = "first_time_hello";
        faceTo(self, player);
        if (hasObjVar(player, "nym.nym.finished"))
        {
            string_id greeting = new string_id(CONVO, "good_work");
            chat.chat(self, greeting);
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(player, "nym.jinkins.finished") || hasObjVar(player, "nym.nym.finished"))
        {
            string_id greeting = new string_id(CONVO, "good_work");
            string_id response[] = new string_id[2];
            response[0] = new string_id(CONVO, "what_now");
            response[1] = new string_id(CONVO, "see_ya");
            setObjVar(player, "nym.jinkins.done", 1);
            npcStartConversation(player, self, "celebConvo", greeting, response);
        }
        if (hasObjVar(player, "nym"))
        {
            if (hasObjVar(player, "nym.openedDroid"))
            {
                obj_id brain = utils.getItemPlayerHasByTemplate(player, "object/tangible/loot/quest/nym_droid_memory_chip.iff");
                if (brain != null)
                {
                    destroyObject(brain);
                }
                setObjVar(player, "nym.jinkins.done", 1);
                string_id message = new string_id(CONVO, "gave_brain");
                chat.chat(self, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (hasObjVar(player, "nym.jinkins.droid"))
        {
            string_id greeting = new string_id(CONVO, "you_got_memory");
            string_id response[] = new string_id[2];
            response[0] = new string_id(CONVO, "yes");
            response[1] = new string_id(CONVO, "no");
            npcStartConversation(player, self, "celebConvo", greeting, response);
        }
        if (hasObjVar(player, "nym.jinkins.kole") || hasObjVar(player, "nym.jinkins.nym") || hasObjVar(player, "nym.jinkins.jinkins"))
        {
            hello_line = "hello_again";
        }
        if (hasObjVar(player, "nym.kole"))
        {
            hello_line = "hello_kole";
        }
        if (hasObjVar(player, "nym.nym"))
        {
            hello_line = "hello_nym";
        }
        if (hasObjVar(player, "nym.jinkins.guypoint") || hasObjVar(player, "nym.jinkins.cavepoint"))
        {
            questInfo = "tell_me_again";
        }
        if (hasObjVar(player, "nym.kole") && hasObjVar(player, "nym.nym"))
        {
            hello_line = "hello_all";
            setObjVar(player, "nym.jinkins.allmet", 1);
        }
        if (hasObjVar(player, "nym.jinkins.allmet"))
        {
            hello_line = "hello_again";
        }
        if (ai_lib.isInCombat(self) || ai_lib.isInCombat(player))
        {
            return SCRIPT_OVERRIDE;
        }
        if (hasObjVar(player, "nym.jinkins.everyone"))
        {
            string_id greeting = new string_id(CONVO, hello_line);
            string_id response[] = new string_id[1];
            response[0] = new string_id(CONVO, questInfo);
            npcStartConversation(player, self, "celebConvo", greeting, response);
        }
        if (hasObjVar(player, "nym.jinkins.jinkins") && hasObjVar(player, "nym.jinkins.kole") && hasObjVar(player, "nym.jinkins.nym"))
        {
            string_id greeting = new string_id(CONVO, hello_line);
            string_id response[] = new string_id[1];
            response[0] = new string_id(CONVO, questInfo);
            setObjVar(player, "nym.jinkins.everyone", 1);
            removeObjVar(player, "nym.jinkins.jinkins");
            removeObjVar(player, "nym.jinkins.kole");
            removeObjVar(player, "nym.jinkins.nym");
            npcStartConversation(player, self, "celebConvo", greeting, response);
        }
        if (hasObjVar(player, "nym.jinkins.jinkins") && hasObjVar(player, "nym.jinkins.kole"))
        {
            string_id greeting = new string_id(CONVO, hello_line);
            string_id response[] = new string_id[2];
            response[0] = new string_id(CONVO, "who_is_nym");
            response[1] = new string_id(CONVO, questInfo);
            npcStartConversation(player, self, "celebConvo", greeting, response);
        }
        if (hasObjVar(player, "nym.jinkins.jinkins") && hasObjVar(player, "nym.jinkins.nym"))
        {
            string_id greeting = new string_id(CONVO, hello_line);
            string_id response[] = new string_id[2];
            response[0] = new string_id(CONVO, "who_is_kole");
            response[1] = new string_id(CONVO, questInfo);
            npcStartConversation(player, self, "celebConvo", greeting, response);
        }
        if (hasObjVar(player, "nym.jinkins.jinkins"))
        {
            string_id greeting = new string_id(CONVO, hello_line);
            string_id response[] = new string_id[3];
            response[0] = new string_id(CONVO, "who_is_nym");
            response[1] = new string_id(CONVO, "who_is_kole");
            response[2] = new string_id(CONVO, questInfo);
            npcStartConversation(player, self, "celebConvo", greeting, response);
        }
        else if (hasObjVar(player, "nym.jinkins.talked"))
        {
            string_id greeting = new string_id(CONVO, hello_line);
            string_id response[] = new string_id[2];
            response[0] = new string_id(CONVO, "who_are_you");
            response[1] = new string_id(CONVO, questInfo);
            npcStartConversation(player, self, "celebConvo", greeting, response);
        }
        else 
        {
            string_id greeting = new string_id(CONVO, hello_line);
            string_id response[] = new string_id[2];
            response[0] = new string_id(CONVO, "who_are_you");
            response[1] = new string_id(CONVO, questInfo);
            npcStartConversation(player, self, "celebConvo", greeting, response);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String convo, obj_id player, string_id response) throws InterruptedException
    {
        String questInfo = "tell_me_quest";
        if (hasObjVar(player, "nym.jinkins.guypoint") || hasObjVar(player, "nym.jinkins.cavepoint"))
        {
            questInfo = "tell_me_again";
        }
        setObjVar(player, "nym.jinkins.talked", 1);
        if ((response.getAsciiId()).equals("who_are_you"))
        {
            string_id message = new string_id(CONVO, "i_am_jinkins");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "who_are_you"));
            npcAddConversationResponse(player, new string_id(CONVO, "who_is_nym"));
            npcAddConversationResponse(player, new string_id(CONVO, "who_is_kole"));
            setObjVar(player, "nym.jinkins.jinkins", 1);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("tell_me_quest"))
        {
            string_id message = new string_id(CONVO, "quest_info");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, questInfo));
            if (!hasObjVar(player, "nym.jinkins.cavepoint"))
            {
                npcAddConversationResponse(player, new string_id(CONVO, "distress_call"));
            }
            if (!hasObjVar(player, "nym.jinkins.guypoint"))
            {
                npcAddConversationResponse(player, new string_id(CONVO, "sulfur_lake_pirates"));
            }
            if (hasObjVar(player, "nym.jinkins.guypoint") && hasObjVar(player, "nym.jinkins.cavepoint"))
            {
                npcAddConversationResponse(player, new string_id(CONVO, "anything_else"));
            }
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("tell_me_again"))
        {
            string_id message = new string_id(CONVO, "quest_info_again");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, questInfo));
            if (!hasObjVar(player, "nym.jinkins.cavepoint"))
            {
                npcAddConversationResponse(player, new string_id(CONVO, "distress_call"));
            }
            if (!hasObjVar(player, "nym.jinkins.guypoint"))
            {
                npcAddConversationResponse(player, new string_id(CONVO, "sulfur_lake_pirates"));
            }
            if (hasObjVar(player, "nym.jinkins.guypoint") && hasObjVar(player, "nym.jinkins.cavepoint"))
            {
                npcAddConversationResponse(player, new string_id(CONVO, "anything_else"));
            }
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("see_ya"))
        {
            string_id message = new string_id(CONVO, "good_bye");
            npcSpeak(player, message);
            npcEndConversation(player);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("what_now"))
        {
            string_id message = new string_id(CONVO, "youre_done");
            if (!hasObjVar(player, "nym.nym.finished"))
            {
                message = new string_id(CONVO, "talk_to_nym");
            }
            if (!hasObjVar(player, "nym.kole.finished") || !hasObjVar(player, "nym.finished"))
            {
                message = new string_id(CONVO, "talk_to_kole");
            }
            npcSpeak(player, message);
            npcEndConversation(player);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("yes"))
        {
            string_id message = new string_id(CONVO, "hand_it_over");
            npcSpeak(player, message);
            npcEndConversation(player);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("no"))
        {
            string_id message = new string_id(CONVO, "get_back_to_work");
            npcSpeak(player, message);
            npcEndConversation(player);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("who_is_nym"))
        {
            string_id message = new string_id(CONVO, "nym_is_boss");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "who_is_nym"));
            setObjVar(player, "nym.jinkins.nym", 1);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("who_is_kole"))
        {
            string_id message = new string_id(CONVO, "kole_is_friend");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "who_is_kole"));
            setObjVar(player, "nym.jinkins.kole", 1);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("distress_call"))
        {
            string_id message = new string_id(CONVO, "crashed_ship");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "distress_call"));
            npcAddConversationResponse(player, new string_id(CONVO, "droid_memory"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("sulfur_lake_pirates"))
        {
            string_id message = new string_id(CONVO, "pirate_info");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "sulfur_lake_pirates"));
            npcAddConversationResponse(player, new string_id(CONVO, "whos_the_guy"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("distress_call"))
        {
            string_id message = new string_id(CONVO, "crashed_ship");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "distress_call"));
            npcAddConversationResponse(player, new string_id(CONVO, "droid_memory"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("sulfur_lake_pirates"))
        {
            string_id message = new string_id(CONVO, "pirate_info");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "sulfur_lake_pirates"));
            npcAddConversationResponse(player, new string_id(CONVO, "whos_the_guy"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("whos_the_guy"))
        {
            string_id message = new string_id(CONVO, "deals_with_pirates");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "whos_the_guy"));
            npcAddConversationResponse(player, new string_id(CONVO, "ill_see_the_guy"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("droid_memory"))
        {
            string_id message = new string_id(CONVO, "imperial_info");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "droid_memory"));
            npcAddConversationResponse(player, new string_id(CONVO, "ill_get_memory"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("anything_else"))
        {
            string_id message = new string_id(CONVO, "you_know_all");
            npcSpeak(player, message);
            npcEndConversation(player);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("ill_get_memory"))
        {
            string_id message = new string_id(CONVO, "heres_droid_memory");
            setObjVar(player, "nym.jinkins.cave", 1);
            if (!hasScript(player, "theme_park.nym.player"))
            {
                attachScript(player, "theme_park.nym.player");
            }
            messageTo(player, "goToCave", null, 2, true);
            npcSpeak(player, message);
            npcEndConversation(player);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("ill_see_the_guy"))
        {
            string_id message = new string_id(CONVO, "heres_the_guy");
            setObjVar(player, "nym.jinkins.guy", 1);
            if (!hasScript(player, "theme_park.nym.player"))
            {
                attachScript(player, "theme_park.nym.player");
            }
            messageTo(player, "goSeeGuy", null, 2, true);
            npcSpeak(player, message);
            npcEndConversation(player);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public int OnGiveItem(obj_id self, obj_id item, obj_id player) throws InterruptedException
    {
        String gaveMe = getTemplateName(item);
        if (gaveMe.equals("object/tangible/loot/quest/nym_droid_memory_chip.iff"))
        {
            setObjVar(player, "nym.jinkins.finished", 1);
            string_id message = new string_id(CONVO, "gave_brain");
            chat.chat(self, message);
            return SCRIPT_OVERRIDE;
        }
        else 
        {
            string_id message = new string_id(CONVO, "whats_this");
            chat.chat(self, message);
            return SCRIPT_CONTINUE;
        }
    }
    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id breacher) throws InterruptedException
    {
        if (!isPlayer(breacher))
        {
            return SCRIPT_CONTINUE;
        }
        if (!volumeName.equals(FACETO_VOLUME_NAME))
        {
            return SCRIPT_CONTINUE;
        }
        if (isInNpcConversation(self))
        {
            return SCRIPT_CONTINUE;
        }
        if (canSee(self, breacher))
        {
            faceTo(self, breacher);
        }
        return SCRIPT_CONTINUE;
    }
    public int doISetup(obj_id self, dictionary params) throws InterruptedException
    {
        setCondition(self, CONDITION_INTERESTING);
        messageTo(self, "unkillable", null, 2, false);
        return SCRIPT_CONTINUE;
    }
    public int unkillable(obj_id self, dictionary params) throws InterruptedException
    {
        setInvulnerable(self, true);
        return SCRIPT_CONTINUE;
    }
}
