package script.npc.celebrity;

import script.library.ai_lib;
import script.library.hue;
import script.obj_id;
import script.string_id;

public class kardeer extends script.base_script
{
    public kardeer()
    {
    }
    public static final String CONVO = "celebrity/kardeer";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id pants = createObject("object/tangible/wearables/pants/pants_s27.iff", self, "");
        obj_id shirt = createObject("object/tangible/wearables/shirt/shirt_s16.iff", self, "");
        obj_id gloves = createObject("object/tangible/wearables/gloves/gloves_s07.iff", self, "");
        obj_id shoes = createObject("object/tangible/wearables/shoes/shoes_s03.iff", self, "");
        obj_id hat = createObject("object/tangible/wearables/hat/hat_s10.iff", self, "");
        hue.setColor(pants, 1, 2);
        hue.setColor(shirt, 1, 75);
        hue.setColor(shoes, 1, 0);
        hue.setColor(shoes, 2, 75);
        hue.setColor(gloves, 1, 95);
        setName(self, "Kar'deer");
        attachScript(self, "npc.converse.npc_converse_menu");
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        debugSpeakMsg(self, "I am Lady V's Gardener.");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        obj_id pants = createObject("object/tangible/wearables/pants/pants_s27.iff", self, "");
        obj_id shirt = createObject("object/tangible/wearables/shirt/shirt_s16.iff", self, "");
        obj_id gloves = createObject("object/tangible/wearables/gloves/gloves_s07.iff", self, "");
        obj_id shoes = createObject("object/tangible/wearables/shoes/shoes_s03.iff", self, "");
        obj_id hat = createObject("object/tangible/wearables/hat/hat_s10.iff", self, "");
        hue.setColor(pants, 1, 2);
        hue.setColor(shirt, 1, 75);
        hue.setColor(shoes, 1, 0);
        hue.setColor(shoes, 2, 75);
        hue.setColor(gloves, 1, 95);
        setName(self, "Kar'deer");
        attachScript(self, "npc.converse.npc_converse_menu");
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        debugSpeakMsg(self, "I am Lady V's Gardener.");
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
