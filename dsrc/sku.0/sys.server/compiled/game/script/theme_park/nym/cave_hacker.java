package script.theme_park.nym;

import script.library.ai_lib;
import script.obj_id;
import script.string_id;

public class cave_hacker extends script.base_script
{
    public cave_hacker()
    {
    }
    public static final String CONVO = "celebrity/lok_hacker";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "Hacker");
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        attachScript(self, "npc.converse.npc_converse_menu");
        setInvulnerable(self, true);
        return SCRIPT_CONTINUE;
    }
    public int OnStartNpcConversation(obj_id self, obj_id speaker) throws InterruptedException
    {
        if (hasObjVar(speaker, "nym.kole.hack"))
        {
            string_id greeting = new string_id(CONVO, "doors_open");
            string_id response[] = new string_id[1];
            response[0] = new string_id(CONVO, "right");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        else 
        {
            string_id greeting = new string_id(CONVO, "help_me_hack");
            string_id response[] = new string_id[2];
            response[0] = new string_id(CONVO, "what_are_you_doing");
            response[1] = new string_id(CONVO, "what_do_you_need");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String convo, obj_id speaker, string_id response) throws InterruptedException
    {
        if ((response.getAsciiId()).equals("what_are_you_doing"))
        {
            string_id message = new string_id(CONVO, "hacking");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "what_are_you_doing"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("what_do_you_need"))
        {
            string_id message = new string_id(CONVO, "cant_solve");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "what_do_you_need"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "what_are_you_doing"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "helpful"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "going_away"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("helpful"))
        {
            string_id message = new string_id(CONVO, "explain");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "helpful"));
            if (!hasObjVar(speaker, "nym.kole.codec"))
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, "codec"));
            }
            if (!hasObjVar(speaker, "nym.kole.rewire"))
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, "rewire"));
            }
            if (!hasObjVar(speaker, "nym.kole.defender"))
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, "defender"));
            }
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("going_away"))
        {
            string_id message = new string_id(CONVO, "jerk");
            npcSpeak(speaker, message);
            npcEndConversation(speaker);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("codec"))
        {
            string_id message = new string_id(CONVO, "color_codes");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "codec"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "rewire"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "defender"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "purple"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "brown"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "orange"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("rewire"))
        {
            string_id message = new string_id(CONVO, "math_problem");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "codec"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "rewire"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "defender"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "seven_twenty"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "two_twenty"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "nine"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("defender"))
        {
            string_id message = new string_id(CONVO, "joke");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "codec"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "rewire"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "defender"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "punchline"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "wrong_joke"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "bad_joke"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("purple"))
        {
            string_id message = new string_id(CONVO, "wrong_color");
            npcSpeak(speaker, message);
            npcEndConversation(speaker);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("brown"))
        {
            string_id message = new string_id(CONVO, "wrong_color");
            npcSpeak(speaker, message);
            npcEndConversation(speaker);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("wrong_joke"))
        {
            string_id message = new string_id(CONVO, "not_funny");
            npcSpeak(speaker, message);
            npcEndConversation(speaker);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("bad_joke"))
        {
            string_id message = new string_id(CONVO, "not_funny");
            npcSpeak(speaker, message);
            npcEndConversation(speaker);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("two_twenty"))
        {
            string_id message = new string_id(CONVO, "wrong_answer");
            npcSpeak(speaker, message);
            npcEndConversation(speaker);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("nine"))
        {
            string_id message = new string_id(CONVO, "wrong_answer");
            npcSpeak(speaker, message);
            npcEndConversation(speaker);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("rewire_2"))
        {
            string_id message = new string_id(CONVO, "math_problem");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "defender_2"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "codec_2"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "rewire_2"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "seven_twenty"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "two_twenty"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "nine"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("codec_2"))
        {
            string_id message = new string_id(CONVO, "color_codes");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "codec_2"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "rewire_2"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "defender_2"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "purple"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "brown"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "orange"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("defender_2"))
        {
            string_id message = new string_id(CONVO, "joke");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "codec_2"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "rewire_2"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "defender_2"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "punchline"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "wrong_joke"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "bad_joke"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("seven_twenty"))
        {
            string_id message = new string_id(CONVO, "right_answer");
            setObjVar(speaker, "nym.kole.rewire", 1);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "seven_twenty"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "two_twenty"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "nine"));
            if (!hasObjVar(speaker, "nym.kole.codec"))
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, "codec_2"));
            }
            if (!hasObjVar(speaker, "nym.kole.defender"))
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, "defender_2"));
            }
            if (hasObjVar(speaker, "nym.kole.defender") && hasObjVar(speaker, "nym.kole.codec"))
            {
                message = new string_id(CONVO, "success");
                setObjVar(speaker, "nym.kole.hack", 1);
                removeObjVar(speaker, "nym.kole.defender");
                removeObjVar(speaker, "nym.kole.rewire");
                removeObjVar(speaker, "nym.kole.codec");
                npcSpeak(speaker, message);
                npcEndConversation(speaker);
            }
            else 
            {
                npcSpeak(speaker, message);
            }
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("punchline"))
        {
            string_id message = new string_id(CONVO, "right_joke");
            setObjVar(speaker, "nym.kole.defender", 1);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "punchline"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "wrong_joke"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "bad_joke"));
            if (!hasObjVar(speaker, "nym.kole.rewire"))
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, "rewire_2"));
            }
            if (!hasObjVar(speaker, "nym.kole.codec"))
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, "codec_2"));
            }
            if (hasObjVar(speaker, "nym.kole.codec") && hasObjVar(speaker, "nym.kole.rewire"))
            {
                message = new string_id(CONVO, "success");
                setObjVar(speaker, "nym.kole.hack", 1);
                removeObjVar(speaker, "nym.kole.defender");
                removeObjVar(speaker, "nym.kole.rewire");
                removeObjVar(speaker, "nym.kole.codec");
                npcSpeak(speaker, message);
                npcEndConversation(speaker);
            }
            else 
            {
                npcSpeak(speaker, message);
            }
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("orange"))
        {
            string_id message = new string_id(CONVO, "right_color");
            setObjVar(speaker, "nym.kole.codec", 1);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "purple"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "orange"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "brown"));
            if (!hasObjVar(speaker, "nym.kole.rewire"))
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, "rewire_2"));
            }
            if (!hasObjVar(speaker, "nym.kole.defender"))
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, "defender_2"));
            }
            if (hasObjVar(speaker, "nym.kole.defender") && hasObjVar(speaker, "nym.kole.rewire"))
            {
                message = new string_id(CONVO, "success");
                setObjVar(speaker, "nym.kole.hack", 1);
                removeObjVar(speaker, "nym.kole.defender");
                removeObjVar(speaker, "nym.kole.rewire");
                removeObjVar(speaker, "nym.kole.codec");
                npcSpeak(speaker, message);
                npcEndConversation(speaker);
            }
            else 
            {
                npcSpeak(speaker, message);
            }
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("right"))
        {
            string_id message = new string_id(CONVO, "go_man_go");
            npcSpeak(speaker, message);
            npcEndConversation(speaker);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
