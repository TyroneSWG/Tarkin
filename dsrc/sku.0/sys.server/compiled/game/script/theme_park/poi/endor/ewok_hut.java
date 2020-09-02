package script.theme_park.poi.endor;

import script.obj_id;

public class ewok_hut extends script.theme_park.poi.base
{
    public ewok_hut()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        debugSpeakMsg(self, "First POI Test Attached");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        String objective = poiGetObjective(self);
        if (objective.equals("ewoks"))
        {
            debugSpeakMsg(self, "In Oninitialize");
            obj_id hutt = poiCreateObject(self, "hutt", "object/building/endor/endor_lake_hut.iff", 0, 0);
            obj_id ewok1 = poiCreateObject(self, "ewok1", "object/creature/npc/base/ewok_base_male.iff", 7, 5);
            obj_id ewok2 = poiCreateObject(self, "ewok2", "object/creature/npc/base/ewok_base_male.iff", 5, 7);
            obj_id ewok3 = poiCreateObject(self, "ewok3", "object/creature/npc/base/ewok_base_male.iff", 5, 5);
            obj_id ewok4 = poiCreateObject(self, "ewok4", "object/creature/npc/base/ewok_base_male.iff", 7, 7);
            obj_id ewok5 = poiCreateObject(self, "ewok5", "object/creature/npc/base/ewok_base_male.iff", -3, 5);
            obj_id ewok6 = poiCreateObject(self, "ewok6", "object/creature/npc/base/ewok_base_male.iff", -5, 7);
            obj_id ewok7 = poiCreateObject(self, "ewok7", "object/creature/npc/base/ewok_base_male.iff", -5, 5);
            obj_id ewok8 = poiCreateObject(self, "ewok8", "object/creature/npc/base/ewok_base_male.iff", -3, 7);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
