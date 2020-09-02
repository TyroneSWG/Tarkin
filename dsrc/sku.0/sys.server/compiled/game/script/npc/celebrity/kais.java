package script.npc.celebrity;

import script.library.ai_lib;
import script.library.hue;
import script.obj_id;
import script.string_id;

public class kais extends script.base_script
{
    public kais()
    {
    }
    public static final String CONVO = "celebrity/kais";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id dress = createObject("object/tangible/wearables/pants/nightsister_pants_s01.iff", self, "");
        obj_id boots = createObject("object/tangible/wearables/boots/nightsister_boots.iff", self, "");
        obj_id shirt = createObject("object/tangible/wearables/shirt/nightsister_shirt_s02.iff", self, "");
        obj_id hat = createObject("object/tangible/wearables/hat/nightsister_hat_s02.iff", self, "");
        hue.setColor(dress, 1, 13);
        hue.setColor(dress, 2, 16);
        hue.setColor(dress, 3, 112);
        hue.setColor(boots, 1, 44);
        hue.setColor(hat, 1, 0);
        hue.setColor(hat, 2, 13);
        hue.setColor(shirt, 1, 18);
        hue.setColor(shirt, 2, 0);
        setName(self, "Kais");
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        debugSpeakMsg(self, "I'm the Nightsister Doctor.");
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
            npcEndConversation(player);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("player_2"))
        {
            string_id message = new string_id(CONVO, "npc_3");
            npcSpeak(player, message);
            npcEndConversation(player);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
