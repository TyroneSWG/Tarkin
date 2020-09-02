package script.theme_park.nym;

import script.dictionary;
import script.library.ai_lib;
import script.library.money;
import script.obj_id;
import script.string_id;

public class nym_contact extends script.base_script
{
    public nym_contact()
    {
    }
    public static final String CONVO = "celebrity/lok_gambler";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "Berema");
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        attachScript(self, "npc.converse.npc_converse_menu");
        setInvulnerable(self, true);
        return SCRIPT_CONTINUE;
    }
    public int OnStartNpcConversation(obj_id self, obj_id speaker) throws InterruptedException
    {
        if (hasObjVar(speaker, "nym.nym.gambler"))
        {
            string_id greeting = new string_id(CONVO, "nym_sent_you");
            string_id response[] = new string_id[3];
            response[0] = new string_id(CONVO, "tell_me_something");
            response[1] = new string_id(CONVO, "play_a_game");
            response[2] = new string_id(CONVO, "talk_nym");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        else 
        {
            string_id greeting = new string_id(CONVO, "play_game");
            string_id response[] = new string_id[2];
            response[0] = new string_id(CONVO, "what_game");
            response[1] = new string_id(CONVO, "sure");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String convo, obj_id speaker, string_id response) throws InterruptedException
    {
        if ((response.getAsciiId()).equals("what_game"))
        {
            string_id message = new string_id(CONVO, "game_description");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "what_game"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "no_thanks"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("no_thanks"))
        {
            string_id message = new string_id(CONVO, "too_bad");
            npcSpeak(speaker, message);
            npcEndConversation(speaker);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("leaving"))
        {
            string_id message = new string_id(CONVO, "good_luck");
            npcSpeak(speaker, message);
            npcEndConversation(speaker);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("tell_me_something"))
        {
            string_id message = new string_id(CONVO, "lab_info");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "tell_me_something"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "hard_drive_where"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "imggcu_where"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("talk_nym"))
        {
            string_id message = new string_id(CONVO, "nym_rules");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "talk_nym"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("hard_drive_where"))
        {
            string_id message = new string_id(CONVO, "hard_drive_loc");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "hard_drive_where"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "leaving"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("imggcu_where"))
        {
            string_id message = new string_id(CONVO, "imggcu_loc");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "imggcu_where"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "leaving"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("play_a_game"))
        {
            string_id message = new string_id(CONVO, "explain_game");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "hard_drive_where"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "imggcu_where"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "talk_nym"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "play_a_game"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "tell_me_something"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "game_rules"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "sure_lets_play"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("game_rules"))
        {
            string_id message = new string_id(CONVO, "explain_rules");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "game_rules"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "no_games"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("sure_lets_play") || (response.getAsciiId()).equals("play_again") || (response.getAsciiId()).equals("sure"))
        {
            string_id message = new string_id(CONVO, "bet_how_much");
            npcSpeak(speaker, message);
            npcAddConversationResponse(speaker, new string_id(CONVO, "bet_made_5"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "bet_made_10"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "bet_made_50"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "sure"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "sure_lets_play"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "play_again"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "no_games"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "game_rules"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("bet_made_5"))
        {
            int cash = getTotalMoney(speaker);
            if (cash < 5)
            {
                string_id message = new string_id(CONVO, "you_got_nothing");
                npcSpeak(speaker, message);
                npcEndConversation(speaker);
                return SCRIPT_CONTINUE;
            }
            int amt = 5;
            dictionary params = new dictionary();
            money.requestPayment(speaker, self, amt, "pass_fail", params, true);
            setObjVar(speaker, "bet", 5);
            string_id message = new string_id(CONVO, "begin_game");
            pickCard(self);
            String cardPlayed = getStringObjVar(self, "card_picked");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "sure_lets_play"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "game_rules"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "play_again"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "no_games"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "sure"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "what_game"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "bet_made_5"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "bet_made_10"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "bet_made_50"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "thermal_detonator"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "sarlacc"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "bounty_hunter"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "game_rules"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("bet_made_10"))
        {
            int cash = getTotalMoney(speaker);
            if (cash < 10)
            {
                string_id message = new string_id(CONVO, "you_got_nothing");
                npcSpeak(speaker, message);
                npcEndConversation(speaker);
                return SCRIPT_CONTINUE;
            }
            int amt = 10;
            dictionary params = new dictionary();
            money.requestPayment(speaker, self, amt, "pass_fail", params, true);
            setObjVar(speaker, "bet", 10);
            string_id message = new string_id(CONVO, "begin_game");
            pickCard(self);
            String cardPlayed = getStringObjVar(self, "card_picked");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "sure_lets_play"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "game_rules"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "play_again"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "no_games"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "sure"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "what_game"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "bet_made_5"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "bet_made_10"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "bet_made_50"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "thermal_detonator"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "sarlacc"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "bounty_hunter"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "game_rules"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("bet_made_50"))
        {
            int cash = getTotalMoney(speaker);
            if (cash < 50)
            {
                string_id message = new string_id(CONVO, "you_got_nothing");
                npcSpeak(speaker, message);
                npcEndConversation(speaker);
                return SCRIPT_CONTINUE;
            }
            int amt = 50;
            dictionary params = new dictionary();
            money.requestPayment(speaker, self, amt, "pass_fail", params, true);
            setObjVar(speaker, "bet", 50);
            string_id message = new string_id(CONVO, "begin_game");
            pickCard(self);
            String cardPlayed = getStringObjVar(self, "card_picked");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "sure_lets_play"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "game_rules"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "play_again"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "no_games"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "sure"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "what_game"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "bet_made_5"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "bet_made_10"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "bet_made_50"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "thermal_detonator"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "sarlacc"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "bounty_hunter"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "game_rules"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("thermal_detonator"))
        {
            int amt = getIntObjVar(speaker, "bet");
            if (amt == 0)
            {
                amt = 5;
            }
            String cardPlayed = getStringObjVar(self, "card_picked");
            string_id message = new string_id(CONVO, "tie");
            if (cardPlayed == null || cardPlayed.equals(""))
            {
                cardPlayed = "sarlacc";
            }
            if (cardPlayed.equals("thermal_detonator"))
            {
                message = new string_id(CONVO, "tie");
                tieGame(self, speaker, amt);
            }
            if (cardPlayed.equals("sarlacc"))
            {
                message = new string_id(CONVO, "you_win_td");
                winner(self, speaker, amt);
            }
            if (cardPlayed.equals("bounty_hunter"))
            {
                message = new string_id(CONVO, "you_lose_td");
                loser(self, speaker, amt);
            }
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "thermal_detonator"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "sarlacc"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "bounty_hunter"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "play_again"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("bounty_hunter"))
        {
            int amt = getIntObjVar(speaker, "bet");
            if (amt == 0)
            {
                amt = 5;
            }
            String cardPlayed = getStringObjVar(self, "card_picked");
            string_id message = new string_id(CONVO, "tie");
            if (cardPlayed == null || cardPlayed.equals(""))
            {
                cardPlayed = "sarlacc";
            }
            if (cardPlayed.equals("thermal_detonator"))
            {
                message = new string_id(CONVO, "you_win_bh");
                winner(self, speaker, amt);
            }
            if (cardPlayed.equals("sarlacc"))
            {
                message = new string_id(CONVO, "you_lose_bh");
                loser(self, speaker, amt);
            }
            if (cardPlayed.equals("bounty_hunter"))
            {
                message = new string_id(CONVO, "tie");
                tieGame(self, speaker, amt);
            }
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "thermal_detonator"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "sarlacc"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "bounty_hunter"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "play_again"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("sarlacc"))
        {
            int amt = getIntObjVar(speaker, "bet");
            if (amt == 0)
            {
                amt = 5;
            }
            String cardPlayed = getStringObjVar(self, "card_picked");
            string_id message = new string_id(CONVO, "tie");
            if (cardPlayed == null || cardPlayed.equals(""))
            {
                cardPlayed = "sarlacc";
            }
            if (cardPlayed.equals("thermal_detonator"))
            {
                message = new string_id(CONVO, "you_lose_s");
                loser(self, speaker, amt);
            }
            if (cardPlayed.equals("sarlacc"))
            {
                message = new string_id(CONVO, "tie");
                tieGame(self, speaker, amt);
            }
            if (cardPlayed.equals("bounty_hunter"))
            {
                message = new string_id(CONVO, "you_win_s");
                winner(self, speaker, amt);
            }
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "thermal_detonator"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "sarlacc"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "bounty_hunter"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "play_again"));
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public void pickCard(obj_id self) throws InterruptedException
    {
        int cardChoice = rand(1, 3);
        if (cardChoice == 1)
        {
            setObjVar(self, "card_picked", "thermal_detonator");
        }
        if (cardChoice == 2)
        {
            setObjVar(self, "card_picked", "sarlacc");
        }
        if (cardChoice == 3)
        {
            setObjVar(self, "card_picked", "bounty_hunter");
        }
        return;
    }
    public void winner(obj_id self, obj_id speaker, int amt) throws InterruptedException
    {
        string_id winningString = new string_id("theme_park_nym/messages", "card_winner");
        amt = amt * 2;
        money.bankTo(money.ACCT_JABBA, speaker, amt);
        sendSystemMessage(speaker, winningString);
        removeObjVar(speaker, "bet");
        return;
    }
    public void tieGame(obj_id self, obj_id speaker, int amt) throws InterruptedException
    {
        string_id tieString = new string_id("theme_park_nym/messages", "card_tie");
        money.bankTo(money.ACCT_JABBA, speaker, amt);
        sendSystemMessage(speaker, tieString);
        removeObjVar(speaker, "bet");
        return;
    }
    public void loser(obj_id self, obj_id speaker, int amt) throws InterruptedException
    {
        string_id losingString = new string_id("theme_park_nym/messages", "card_loser");
        sendSystemMessage(speaker, losingString);
        removeObjVar(speaker, "bet");
        return;
    }
}
