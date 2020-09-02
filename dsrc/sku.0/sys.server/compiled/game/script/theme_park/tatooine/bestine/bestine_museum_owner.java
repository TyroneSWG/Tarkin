package script.theme_park.tatooine.bestine;

import script.library.ai_lib;
import script.library.hue;
import script.obj_id;

public class bestine_museum_owner extends script.base_script
{
    public bestine_museum_owner()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id robe = createObject("object/tangible/wearables/jacket/jacket_s22.iff", self, "");
        obj_id shirt = createObject("object/tangible/wearables/shirt/shirt_s30.iff", self, "");
        obj_id boots = createObject("object/tangible/wearables/boots/boots_s03.iff", self, "");
        obj_id hair = createObject("object/tangible/hair/human/hair_human_male_s32.iff", self, "");
        setName(self, "Lilas Dinhint");
        hue.setColor(boots, 1, 0);
        hue.setColor(boots, 2, 3);
        hue.setColor(shirt, 1, 202);
        hue.setColor(shirt, 2, 0);
        hue.setColor(robe, 1, 205);
        hue.setColor(robe, 2, 132);
        hue.setColor(hair, 1, 14);
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        debugSpeakMsg(self, "Feel free to browse but don't carouse, ahaha!!");
        return SCRIPT_CONTINUE;
    }
}
