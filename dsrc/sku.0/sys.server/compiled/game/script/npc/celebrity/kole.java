package script.npc.celebrity;

import script.dictionary;
import script.library.ai_lib;
import script.library.chat;
import script.library.utils;
import script.obj_id;
import script.string_id;

public class kole extends script.base_script
{
    public kole()
    {
    }
    public static final String CONVO = "celebrity/kole";
    public static final String FACETO_VOLUME_NAME = "faceToTriggerVolume";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "Kole");
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        setInvulnerable(self, false);
        debugSpeakMsg(self, "I work for Nym...I might be a doctor...");
        attachScript(self, "npc.converse.npc_converse_menu");
        createTriggerVolume(FACETO_VOLUME_NAME, 8.0f, true);
        messageTo(self, "doISetup", null, 2, false);
        return SCRIPT_CONTINUE;
    }
    public int OnStartNpcConversation(obj_id self, obj_id speaker) throws InterruptedException
    {
        String questInfo = "tell_me_quest";
        faceTo(self, speaker);
        if (hasObjVar(speaker, "nym.nym.finished"))
        {
            string_id greeting = new string_id(CONVO, "good_work");
            chat.chat(self, greeting);
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(speaker, "nym.kole.done"))
        {
            string_id greeting = new string_id(CONVO, "good_work");
            string_id response[] = new string_id[2];
            response[0] = new string_id(CONVO, "what_now");
            response[1] = new string_id(CONVO, "see_ya");
            setObjVar(speaker, "nym.kole.finished", 1);
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        if (hasObjVar(speaker, "nym"))
        {
            if (hasObjVar(speaker, "nym.openedGas"))
            {
                obj_id gas = utils.getItemPlayerHasByTemplate(speaker, "object/tangible/loot/quest/nym_filtered_gas.iff");
                if (gas != null)
                {
                    destroyObject(gas);
                }
                setObjVar(speaker, "nym.kole.done", 1);
                string_id message = new string_id(CONVO, "gave_gas");
                chat.chat(self, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (hasObjVar(speaker, "nym.kole.gotgas"))
        {
            string_id greeting = new string_id(CONVO, "bring_gas");
            string_id response[] = new string_id[2];
            response[0] = new string_id(CONVO, "yes");
            response[1] = new string_id(CONVO, "no");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        if (hasObjVar(speaker, "nym.kole.officerPoint") || hasObjVar(speaker, "nym.kole.gasPoint"))
        {
            questInfo = "tell_me_again";
        }
        if (ai_lib.isInCombat(self) || ai_lib.isInCombat(speaker))
        {
            return SCRIPT_OVERRIDE;
        }
        if (hasObjVar(speaker, "nym.kole.everyone"))
        {
            string_id greeting = new string_id(CONVO, "hello_again");
            string_id response[] = new string_id[1];
            response[0] = new string_id(CONVO, questInfo);
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        else if (hasObjVar(speaker, "nym.kole.jinkins") && hasObjVar(speaker, "nym.kole.nym") && hasObjVar(speaker, "nym.kole.kole"))
        {
            setObjVar(speaker, "nym.kole.everyone", 1);
            removeObjVar(speaker, "nym.kole.kole");
            removeObjVar(speaker, "nym.kole.nym");
            removeObjVar(speaker, "nym.kole.jinkins");
            string_id greeting = new string_id(CONVO, "hello_again");
            string_id response[] = new string_id[1];
            response[0] = new string_id(CONVO, questInfo);
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        else if (hasObjVar(speaker, "nym.kole.jinkins"))
        {
            string_id greeting = new string_id(CONVO, "hello_again");
            string_id response[] = new string_id[2];
            response[0] = new string_id(CONVO, questInfo);
            response[1] = new string_id(CONVO, "who_is_nym");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        else if (hasObjVar(speaker, "nym.kole.nym"))
        {
            string_id greeting = new string_id(CONVO, "hello_again");
            string_id response[] = new string_id[2];
            response[0] = new string_id(CONVO, questInfo);
            response[1] = new string_id(CONVO, "who_is_jinkins");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        else if (hasObjVar(speaker, "nym.kole.kole"))
        {
            string_id greeting = new string_id(CONVO, "hello_again");
            string_id response[] = new string_id[3];
            response[0] = new string_id(CONVO, questInfo);
            response[1] = new string_id(CONVO, "who_is_nym");
            response[2] = new string_id(CONVO, "who_is_jinkins");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        else if (hasObjVar(speaker, "nym.kole.talked"))
        {
            string_id greeting = new string_id(CONVO, "hello_again");
            string_id response[] = new string_id[2];
            response[0] = new string_id(CONVO, questInfo);
            response[1] = new string_id(CONVO, "who_are_you");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        else 
        {
            string_id greeting = new string_id(CONVO, "first_time_hello");
            string_id response[] = new string_id[2];
            response[0] = new string_id(CONVO, questInfo);
            response[1] = new string_id(CONVO, "who_are_you");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String convo, obj_id player, string_id response) throws InterruptedException
    {
        setObjVar(player, "nym.kole.talked", 1);
        String questInfo = "tell_me_quest";
        if (hasObjVar(player, "nym.kole.officerPoint") || hasObjVar(player, "nym.kole.gasPoint"))
        {
            questInfo = "tell_me_again";
        }
        if ((response.getAsciiId()).equals("tell_me_quest"))
        {
            string_id message = new string_id(CONVO, "quest_info");
            if (!hasObjVar(player, "nym.jinkins.done"))
            {
                message = new string_id(CONVO, "quest_tease");
                npcRemoveConversationResponse(player, new string_id(CONVO, questInfo));
                npcSpeak(player, message);
                npcEndConversation(player);
                return SCRIPT_CONTINUE;
            }
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, questInfo));
            if (!hasObjVar(player, "nym.kole.gasPoint"))
            {
                npcAddConversationResponse(player, new string_id(CONVO, "mining_facility"));
            }
            if (!hasObjVar(player, "nym.kole.officerPoint"))
            {
                npcAddConversationResponse(player, new string_id(CONVO, "probe_droid"));
            }
            if (hasObjVar(player, "nym.kole.officerPoint") && hasObjVar(player, "nym.kole.gasPoint"))
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
            if (!hasObjVar(player, "nym.kole.gasPoint"))
            {
                npcAddConversationResponse(player, new string_id(CONVO, "mining_facility"));
            }
            if (!hasObjVar(player, "nym.kole.officerPoint"))
            {
                npcAddConversationResponse(player, new string_id(CONVO, "probe_droid"));
            }
            if (hasObjVar(player, "nym.kole.officerPoint") && hasObjVar(player, "nym.kole.gasPoint"))
            {
                npcAddConversationResponse(player, new string_id(CONVO, "anything_else"));
            }
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("who_are_you"))
        {
            string_id message = new string_id(CONVO, "im_kole");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "who_are_you"));
            npcAddConversationResponse(player, new string_id(CONVO, "who_is_nym"));
            npcAddConversationResponse(player, new string_id(CONVO, "who_is_jinkins"));
            setObjVar(player, "nym.kole.kole", 1);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("mining_facility"))
        {
            string_id message = new string_id(CONVO, "miners");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "mining_facility"));
            npcAddConversationResponse(player, new string_id(CONVO, "dangerous_gas"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("who_is_nym"))
        {
            string_id message = new string_id(CONVO, "nym_is_chief");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "who_is_nym"));
            setObjVar(player, "nym.kole.nym", 1);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("who_is_jinkins"))
        {
            string_id message = new string_id(CONVO, "jinkins_is_friend");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "who_is_jinkins"));
            setObjVar(player, "nym.kole.jinkins", 1);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("dangerous_gas"))
        {
            string_id message = new string_id(CONVO, "gas_info");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "dangerous_gas"));
            npcAddConversationResponse(player, new string_id(CONVO, "ill_get_gas"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("probe_droid"))
        {
            string_id message = new string_id(CONVO, "droid_info");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "probe_droid"));
            npcAddConversationResponse(player, new string_id(CONVO, "imperial_involvement"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("imperial_involvement"))
        {
            string_id message = new string_id(CONVO, "imperial_stuff");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "imperial_involvement"));
            npcAddConversationResponse(player, new string_id(CONVO, "resigned_officer"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("resigned_officer"))
        {
            string_id message = new string_id(CONVO, "officer_info");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "resigned_officer"));
            npcAddConversationResponse(player, new string_id(CONVO, "ill_see_imperial"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("anything_else"))
        {
            string_id message = new string_id(CONVO, "not_really");
            npcSpeak(player, message);
            npcEndConversation(player);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("ill_get_gas"))
        {
            string_id message = new string_id(CONVO, "here_is_gas");
            setObjVar(player, "nym.jinkins.guy", 1);
            if (!hasScript(player, "theme_park.nym.player"))
            {
                attachScript(player, "theme_park.nym.player");
            }
            messageTo(player, "goGetGas", null, 2, true);
            npcSpeak(player, message);
            npcEndConversation(player);
            setObjVar(player, "nym.kole.gasPoint", 1);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("ill_see_imperial"))
        {
            string_id message = new string_id(CONVO, "here_is_imperial");
            setObjVar(player, "nym.jinkins.guy", 1);
            if (!hasScript(player, "theme_park.nym.player"))
            {
                attachScript(player, "theme_park.nym.player");
            }
            messageTo(player, "goSeeImperial", null, 2, true);
            npcSpeak(player, message);
            npcEndConversation(player);
            setObjVar(player, "nym.kole.officerPoint", 1);
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
            npcSpeak(player, message);
            npcEndConversation(player);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public int OnGiveItem(obj_id self, obj_id item, obj_id player) throws InterruptedException
    {
        String gaveMe = getTemplateName(item);
        if (gaveMe.equals("object/tangible/loot/quest/nym_filtered_gas.iff"))
        {
            setObjVar(player, "nym.kole.done", 1);
            string_id message = new string_id(CONVO, "gave_gas");
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
