package script.theme_park.nym;

import script.dictionary;
import script.library.ai_lib;
import script.library.money;
import script.obj_id;
import script.string_id;

public class imperial_bribe extends script.base_script
{
    public imperial_bribe()
    {
    }
    public static final String CONVO = "celebrity/imperial_bribe";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "Sergeant Moore (Retired)");
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        attachScript(self, "npc.converse.npc_converse_menu");
        setInvulnerable(self, true);
        return SCRIPT_CONTINUE;
    }
    public int OnStartNpcConversation(obj_id self, obj_id speaker) throws InterruptedException
    {
        if (hasObjVar(speaker, "nym.bribe.hundred"))
        {
            string_id greeting = new string_id(CONVO, "already_paid");
            string_id response[] = new string_id[2];
            response[0] = new string_id(CONVO, "tell_me_base");
            response[1] = new string_id(CONVO, "tell_me_droideka");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        if (hasObjVar(speaker, "nym.bribe.fifty"))
        {
            string_id greeting = new string_id(CONVO, "already_paid");
            string_id response[] = new string_id[2];
            response[0] = new string_id(CONVO, "short_base_info");
            response[1] = new string_id(CONVO, "short_droideka_info");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        if (hasObjVar(speaker, "nym.bribe.who"))
        {
            string_id greeting = new string_id(CONVO, "leave_me_alone");
            string_id response[] = new string_id[1];
            response[0] = new string_id(CONVO, "perhaps_bribe");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        else 
        {
            string_id greeting = new string_id(CONVO, "leave_me_alone");
            string_id response[] = new string_id[2];
            response[0] = new string_id(CONVO, "who_are_you");
            response[1] = new string_id(CONVO, "perhaps_bribe");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String convo, obj_id speaker, string_id response) throws InterruptedException
    {
        if ((response.getAsciiId()).equals("who_are_you"))
        {
            string_id message = new string_id(CONVO, "nunya");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "who_are_you"));
            setObjVar(speaker, "nym.bribe.who", 1);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("give_100"))
        {
            string_id message = new string_id(CONVO, "the_good_stuff");
            int cash = getTotalMoney(speaker);
            if (cash >= 100)
            {
                dictionary params = new dictionary();
                money.requestPayment(speaker, self, 100, "pass_fail", params, true);
                message = new string_id(CONVO, "the_good_stuff");
                setObjVar(speaker, "nym.bribe.hundred", 1);
            }
            else 
            {
                message = new string_id(CONVO, "you_got_nothing");
                npcSpeak(speaker, message);
                npcEndConversation(speaker);
                return SCRIPT_OVERRIDE;
            }
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "give_50"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "give_100"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "tell_me_base"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "tell_me_droideka"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("give_50"))
        {
            string_id message = new string_id(CONVO, "medium_info");
            int cash = getTotalMoney(speaker);
            if (cash >= 50)
            {
                dictionary params = new dictionary();
                money.requestPayment(speaker, self, 50, "pass_fail", params, true);
                message = new string_id(CONVO, "medium_info");
                setObjVar(speaker, "nym.bribe.fifty", 1);
            }
            else 
            {
                message = new string_id(CONVO, "you_got_nothing");
                npcSpeak(speaker, message);
                npcEndConversation(speaker);
                return SCRIPT_OVERRIDE;
            }
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "give_50"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "give_100"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "short_base_info"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "short_droideka_info"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("perhaps_bribe"))
        {
            string_id message = new string_id(CONVO, "what_you_got");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "perhaps_bribe"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "give_50"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "give_100"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("short_base_info"))
        {
            string_id message = new string_id(CONVO, "short_base_story");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "short_base_info"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "tell_me_more_base"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("short_droideka_info"))
        {
            string_id message = new string_id(CONVO, "short_droideka_story");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "short_droideka_info"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "tell_me_more_droideka"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("tell_me_base"))
        {
            string_id message = new string_id(CONVO, "base_story_long");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "tell_me_base"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "end_convo"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("tell_me_droideka"))
        {
            string_id message = new string_id(CONVO, "droideka_story_long");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "tell_me_droideka"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "end_convo"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("tell_me_more_droideka"))
        {
            string_id message = new string_id(CONVO, "need_more_cash");
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "tell_me_more_droideka"));
            if (hasObjVar(speaker, "nym.bribe.fifty"))
            {
                int bribeFifty = getIntObjVar(speaker, "nym.bribe.fifty");
                if (bribeFifty != 0)
                {
                    if (bribeFifty > 1)
                    {
                        message = new string_id(CONVO, "end_droideka_info");
                        npcAddConversationResponse(speaker, new string_id(CONVO, "anymore_droideka"));
                    }
                    else 
                    {
                        npcAddConversationResponse(speaker, new string_id(CONVO, "add_100_bribe"));
                    }
                }
                else 
                {
                    npcAddConversationResponse(speaker, new string_id(CONVO, "add_100_bribe"));
                }
            }
            else 
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, "add_100_bribe"));
            }
            npcSpeak(speaker, message);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("tell_me_more_base"))
        {
            string_id message = new string_id(CONVO, "need_more_money");
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "tell_me_more_base"));
            if (hasObjVar(speaker, "nym.bribe.fifty"))
            {
                int bribeFifty = getIntObjVar(speaker, "nym.bribe.fifty");
                if (bribeFifty != 0)
                {
                    if (bribeFifty > 1)
                    {
                        message = new string_id(CONVO, "end_base_info");
                        npcAddConversationResponse(speaker, new string_id(CONVO, "anymore_base"));
                    }
                    else 
                    {
                        npcAddConversationResponse(speaker, new string_id(CONVO, "add_100_bribe"));
                    }
                }
                else 
                {
                    npcAddConversationResponse(speaker, new string_id(CONVO, "add_100_bribe"));
                }
            }
            else 
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, "add_100_bribe"));
            }
            npcSpeak(speaker, message);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("add_100_bribe"))
        {
            string_id message = new string_id(CONVO, "end_base_info");
            int cash = getTotalMoney(speaker);
            if (cash >= 100)
            {
                dictionary params = new dictionary();
                money.requestPayment(speaker, self, 100, "pass_fail", params, true);
                message = new string_id(CONVO, "end_base_info");
                setObjVar(speaker, "nym.bribe.hundred", 1);
            }
            else 
            {
                message = new string_id(CONVO, "you_got_nothing");
                npcSpeak(speaker, message);
                npcEndConversation(speaker);
                return SCRIPT_OVERRIDE;
            }
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "add_100_bribe"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "anymore_base"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "anymore_droideka"));
            setObjVar(speaker, "nym.bribe.fifty", 2);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("anymore_droideka"))
        {
            string_id message = new string_id(CONVO, "no_more_droideka");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "anymore_droideka"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "end_convo"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("anymore_base"))
        {
            string_id message = new string_id(CONVO, "no_more_base");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "anymore_base"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "end_convo"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("end_convo"))
        {
            string_id message = new string_id(CONVO, "thats_all");
            npcSpeak(speaker, message);
            npcEndConversation(speaker);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
