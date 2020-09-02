package script.npc.static_npc;

import script.library.ai_lib;
import script.library.hue;
import script.obj_id;

public class stella extends script.base_script
{
    public stella()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id dress = createObject("object/tangible/wearables/dress/dress_s06.iff", self, "");
        obj_id shoes = createObject("object/tangible/wearables/shoes/shoes_s02.iff", self, "");
        setName(self, "Stella");
        hue.setColor(dress, 1, 95);
        hue.setColor(dress, 2, 22);
        hue.setColor(shoes, 1, 11);
        hue.setColor(shoes, 1, 58);
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        debugSpeakMsg(self, "Welcome to the Third Sun!");
        attachScript(self, "npc.static_quest.quest_convo");
        setObjVar(self, "quest_table", "stella");
        return SCRIPT_CONTINUE;
    }
}
