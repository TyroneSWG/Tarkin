package script.npc.celebrity;

import script.dictionary;
import script.library.ai_lib;
import script.library.colors;
import script.library.hue;
import script.obj_id;

public class klaatu extends script.base_script
{
    public klaatu()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id shirt = createObject("object/tangible/wearables/shirt/shirt_s09.iff", self, "");
        obj_id pants = createObject("object/tangible/wearables/pants/pants_s01.iff", self, "");
        obj_id shoes = createObject("object/tangible/wearables/shoes/shoes_s03.iff", self, "");
        obj_id vest = createObject("object/tangible/wearables/vest/vest_s05.iff", self, "");
        hue.setColor(shirt, 1, colors.DIMGREY);
        hue.setColor(vest, 1, colors.YELLOW);
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        debugSpeakMsg(self, "The day Tatooine Stood Still!");
        return SCRIPT_CONTINUE;
    }
    public int work(obj_id self, dictionary params) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
}
