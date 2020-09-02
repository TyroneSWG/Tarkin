package script.npc.static_npc;

import script.library.ai_lib;
import script.library.colors;
import script.library.hue;
import script.obj_id;

public class capt_loftus extends script.base_script
{
    public capt_loftus()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id shirt = createObject("object/tangible/wearables/shirt/shirt_s07.iff", self, "");
        obj_id pants = createObject("object/tangible/wearables/pants/pants_s12.iff", self, "");
        obj_id shoes = createObject("object/tangible/wearables/shoes/shoes_s03.iff", self, "");
        obj_id vest = createObject("object/tangible/wearables/vest/vest_s05.iff", self, "");
        obj_id belt = createObject("object/tangible/wearables/armor/stormtrooper/armor_stormtrooper_utility_belt.iff", self, "");
        hue.setColor(shirt, 1, colors.BLACK);
        hue.setColor(shirt, 1, colors.BLUE);
        hue.setColor(vest, 1, colors.BLUE);
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        setName(self, "Captain Loftus");
        return SCRIPT_CONTINUE;
    }
}
