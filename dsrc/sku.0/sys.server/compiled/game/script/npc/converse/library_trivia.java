package script.npc.converse;

import script.library.ai_lib;
import script.library.badge;
import script.library.utils;
import script.obj_id;
import script.string_id;

public class library_trivia extends script.base_script
{
    public library_trivia()
    {
    }
    public static final String CONVO = "celebrity/librarian";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        String name = getFirstName(self);
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        setInvulnerable(self, true);
        attachScript(self, "npc.converse.npc_converse_menu");
        return SCRIPT_CONTINUE;
    }
    public int OnStartNpcConversation(obj_id self, obj_id speaker) throws InterruptedException
    {
        string_id message = new string_id(CONVO, "want_trivia");
        string_id response[] = new string_id[2];
        response[0] = new string_id(CONVO, "yes");
        response[1] = new string_id(CONVO, "no");
        npcStartConversation(speaker, self, "Convo", message, response);
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String convo, obj_id speaker, string_id response) throws InterruptedException
    {
        int intQuestion = utils.getIntScriptVar(speaker, "libTrivQuestion");
        if (intQuestion == 0)
        {
            intQuestion = 1;
        }
        if ((response.getAsciiId()).equals("yes"))
        {
            int questionNumber = intQuestion;
            utils.setScriptVar(speaker, "libTriviaQuestion", intQuestion);
            String strQuestion = "question_" + questionNumber;
            String right_answer = "right_" + questionNumber;
            String wrong_one = "wrong_one_" + questionNumber;
            String wrong_two = "wrong_two_" + questionNumber;
            String wrong_three = "wrong_three_" + questionNumber;
            if (intQuestion > 20)
            {
                string_id done = new string_id(CONVO, "done");
                npcRemoveConversationResponse(speaker, new string_id(CONVO, "yes"));
                npcRemoveConversationResponse(speaker, new string_id(CONVO, "no"));
                npcAddConversationResponse(speaker, new string_id(CONVO, "thanks"));
                npcSpeak(speaker, done);
                return SCRIPT_CONTINUE;
            }
            string_id question = new string_id(CONVO, strQuestion);
            int randomizer = rand(1, 4);
            npcSpeak(speaker, question);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "yes"));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "no"));
            if (randomizer == 1)
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, right_answer));
                npcAddConversationResponse(speaker, new string_id(CONVO, wrong_one));
                npcAddConversationResponse(speaker, new string_id(CONVO, wrong_two));
                npcAddConversationResponse(speaker, new string_id(CONVO, wrong_three));
            }
            else if (randomizer == 2)
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, wrong_two));
                npcAddConversationResponse(speaker, new string_id(CONVO, right_answer));
                npcAddConversationResponse(speaker, new string_id(CONVO, wrong_three));
                npcAddConversationResponse(speaker, new string_id(CONVO, wrong_one));
            }
            else if (randomizer == 3)
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, wrong_one));
                npcAddConversationResponse(speaker, new string_id(CONVO, wrong_two));
                npcAddConversationResponse(speaker, new string_id(CONVO, right_answer));
                npcAddConversationResponse(speaker, new string_id(CONVO, wrong_three));
            }
            else 
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, wrong_two));
                npcAddConversationResponse(speaker, new string_id(CONVO, wrong_one));
                npcAddConversationResponse(speaker, new string_id(CONVO, wrong_three));
                npcAddConversationResponse(speaker, new string_id(CONVO, right_answer));
            }
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("no"))
        {
            string_id toobad = new string_id(CONVO, "good_bye");
            npcSpeak(speaker, toobad);
            npcEndConversation(speaker);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("right_" + intQuestion))
        {
            string_id veryGood = new string_id(CONVO, "winner_is_you");
            int randomCorrect = rand(1, 5);
            switch (randomCorrect)
            {
                case 1:
                veryGood = new string_id(CONVO, "you_are_right");
                break;
                case 2:
                veryGood = new string_id(CONVO, "good_answer");
                break;
                case 3:
                veryGood = new string_id(CONVO, "correct");
                break;
                case 4:
                veryGood = new string_id(CONVO, "correctamundo");
                break;
                case 5:
                veryGood = new string_id(CONVO, "you_got_it");
                break;
            }
            npcSpeak(speaker, veryGood);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "wrong_one_" + intQuestion));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "wrong_two_" + intQuestion));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "wrong_three_" + intQuestion));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "right_" + intQuestion));
            npcAddConversationResponse(speaker, new string_id(CONVO, "yes"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "no"));
            int trivQuestion = utils.getIntScriptVar(speaker, "libTrivQuestion");
            if (trivQuestion == 0)
            {
                trivQuestion = 1;
            }
            utils.setScriptVar(speaker, "libTrivQuestion", trivQuestion + 1);
        }
        if ((response.getAsciiId()).equals("wrong_one_" + intQuestion))
        {
            string_id veryGood = new string_id(CONVO, "too_bad_so_sad");
            int randomCorrect = rand(1, 5);
            switch (randomCorrect)
            {
                case 1:
                veryGood = new string_id(CONVO, "thats_not_it");
                break;
                case 2:
                veryGood = new string_id(CONVO, "no_sir");
                break;
                case 3:
                veryGood = new string_id(CONVO, "you_are_wrong");
                break;
                case 4:
                veryGood = new string_id(CONVO, "incorrect");
                break;
                case 5:
                veryGood = new string_id(CONVO, "buzz_wrong_answer");
                break;
            }
            npcSpeak(speaker, veryGood);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "wrong_one_" + intQuestion));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "wrong_two_" + intQuestion));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "wrong_three_" + intQuestion));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "right_" + intQuestion));
            npcAddConversationResponse(speaker, new string_id(CONVO, "yes"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "no"));
        }
        if ((response.getAsciiId()).equals("wrong_two_" + intQuestion))
        {
            string_id veryGood = new string_id(CONVO, "too_bad_so_sad");
            int randomCorrect = rand(1, 5);
            switch (randomCorrect)
            {
                case 1:
                veryGood = new string_id(CONVO, "couldnt_be_wronger");
                break;
                case 2:
                veryGood = new string_id(CONVO, "most_wrong");
                break;
                case 3:
                veryGood = new string_id(CONVO, "most_unfortunate");
                break;
                case 4:
                veryGood = new string_id(CONVO, "most_incorrect");
                break;
                case 5:
                veryGood = new string_id(CONVO, "bad_answer");
                break;
            }
            npcSpeak(speaker, veryGood);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "wrong_one_" + intQuestion));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "wrong_two_" + intQuestion));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "wrong_three_" + intQuestion));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "right_" + intQuestion));
            npcAddConversationResponse(speaker, new string_id(CONVO, "yes"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "no"));
        }
        if ((response.getAsciiId()).equals("wrong_three_" + intQuestion))
        {
            string_id veryGood = new string_id(CONVO, "worst_ever_guesser");
            int randomCorrect = rand(1, 5);
            switch (randomCorrect)
            {
                case 1:
                veryGood = new string_id(CONVO, "worst_answer_ever");
                break;
                case 2:
                veryGood = new string_id(CONVO, "wrongest");
                break;
                case 3:
                veryGood = new string_id(CONVO, "wrong_squared");
                break;
                case 4:
                veryGood = new string_id(CONVO, "you_are_weakest_link");
                break;
                case 5:
                veryGood = new string_id(CONVO, "not_even_trying");
                break;
            }
            npcSpeak(speaker, veryGood);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "wrong_one_" + intQuestion));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "wrong_two_" + intQuestion));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "wrong_three_" + intQuestion));
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "right_" + intQuestion));
            npcAddConversationResponse(speaker, new string_id(CONVO, "yes"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "no"));
        }
        if ((response.getAsciiId()).equals("thanks"))
        {
            string_id winner = new string_id(CONVO, "answered_all");
            npcSpeak(speaker, winner);
            if (!badge.hasBadge(speaker, "bdg_library_trivia"))
            {
                badge.grantBadge(speaker, "bdg_library_trivia");
            }
            npcEndConversation(speaker);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnEndNpcConversation(obj_id self, obj_id speaker) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
}
