package script.theme_park.poi.tatooine.tusken_raider;

import script.location;
import script.obj_id;

public class poi_tuskenraider_camp1 extends script.theme_park.poi.base
{
    public poi_tuskenraider_camp1()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        String objective = poiGetObjective(self);
        if (objective.equals("tuskens"))
        {
            obj_id tr1 = poiCreateNpc("tusken", -1, -3);
            obj_id tr2 = poiCreateNpc("tusken", -1, 3);
            obj_id tr3 = poiCreateNpc("tusken", 2, 2);
            attachScript(tr1, "theme_park.poi.tatooine.behavior.tusken_stationary_poi");
            attachScript(tr2, "theme_park.poi.tatooine.behavior.tusken_stationary_poi");
            attachScript(tr3, "theme_park.poi.tatooine.behavior.tusken_stationary_poi");
            location here = getLocation(self);
            obj_id fire = poiCreateObject(self, "campfire", "object/static/particle/particle_campfire_style_1.iff", 1, 2);
            obj_id center = poiCreateObject(self, "center", "object/tangible/camp/campfire_logs_burnt.iff", 1, 2);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
