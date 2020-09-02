package script.npc.celebrity;

import script.library.ai_lib;
import script.library.hue;
import script.obj_id;
import script.string_id;

public class gerrick_lond extends script.base_script
{
    public gerrick_lond()
    {
    }
    public static final String CONVO = "celebrity/gerrick_lond";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id robe = createObject("object/tangible/wearables/robe/robe_s12.iff", self, "");
        obj_id boots = createObject("object/tangible/wearables/boots/boots_s21.iff", self, "");
        hue.setColor(robe, 1, 167);
        hue.setColor(robe, 2, 3);
        attachScript(self, "npc.converse.npc_converse_menu");
        setName(self, "Gerrick Lond");
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        debugSpeakMsg(self, "I am the chamberlain of Jabba's townhouse.");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        obj_id robe = createObject("object/tangible/wearables/robe/robe_s12.iff", self, "");
        obj_id boots = createObject("object/tangible/wearables/boots/boots_s21.iff", self, "");
        hue.setColor(robe, 1, 167);
        hue.setColor(robe, 2, 3);
        attachScript(self, "npc.converse.npc_converse_menu");
        setName(self, "Gerrick Lond");
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        debugSpeakMsg(self, "I am the chamberlain of Jabba's townhouse.");
        return SCRIPT_CONTINUE;
    }
    public int OnStartNpcConversation(obj_id self, obj_id speaker) throws InterruptedException
    {
        if (ai_lib.isInCombat(self) || ai_lib.isInCombat(speaker))
        {
            return SCRIPT_OVERRIDE;
        }
        string_id greeting = new string_id(CONVO, "npc_1");
        string_id response[] = new string_id[2];
        response[0] = new string_id(CONVO, "player_1");
        response[1] = new string_id(CONVO, "player_2");
        npcStartConversation(speaker, self, "celebConvo", greeting, response);
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String convo, obj_id player, string_id response) throws InterruptedException
    {
        if ((response.getAsciiId()).equals("player_1"))
        {
            string_id message = new string_id(CONVO, "npc_2");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "player_1"));
            npcRemoveConversationResponse(player, new string_id(CONVO, "player_2"));
            npcAddConversationResponse(player, new string_id(CONVO, "player_3"));
            npcAddConversationResponse(player, new string_id(CONVO, "player_4"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("player_2"))
        {
            string_id message = new string_id(CONVO, "npc_3");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "player_1"));
            npcRemoveConversationResponse(player, new string_id(CONVO, "player_2"));
            npcAddConversationResponse(player, new string_id(CONVO, "player_5"));
            npcAddConversationResponse(player, new string_id(CONVO, "player_6"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("player_3"))
        {
            string_id message = new string_id(CONVO, "npc_6");
            npcSpeak(player, message);
            npcEndConversation(player);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("player_4"))
        {
            string_id message = new string_id(CONVO, "npc_7");
            npcSpeak(player, message);
            npcEndConversation(player);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("player_5"))
        {
            string_id message = new string_id(CONVO, "npc_4");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "player_5"));
            npcRemoveConversationResponse(player, new string_id(CONVO, "player_6"));
            npcAddConversationResponse(player, new string_id(CONVO, "player_7"));
            npcAddConversationResponse(player, new string_id(CONVO, "player_8"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("player_6"))
        {
            string_id message = new string_id(CONVO, "npc_5");
            npcSpeak(player, message);
            npcRemoveConversationResponse(player, new string_id(CONVO, "player_5"));
            npcRemoveConversationResponse(player, new string_id(CONVO, "player_6"));
            npcAddConversationResponse(player, new string_id(CONVO, "player_9"));
            npcAddConversationResponse(player, new string_id(CONVO, "player_10"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("player_7"))
        {
            string_id message = new string_id(CONVO, "npc_8");
            npcSpeak(player, message);
            npcEndConversation(player);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("player_8"))
        {
            string_id message = new string_id(CONVO, "npc_9");
            npcSpeak(player, message);
            npcEndConversation(player);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("player_9"))
        {
            string_id message = new string_id(CONVO, "npc_10");
            npcSpeak(player, message);
            npcEndConversation(player);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("player_10"))
        {
            string_id message = new string_id(CONVO, "npc_11");
            npcSpeak(player, message);
            npcEndConversation(player);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
