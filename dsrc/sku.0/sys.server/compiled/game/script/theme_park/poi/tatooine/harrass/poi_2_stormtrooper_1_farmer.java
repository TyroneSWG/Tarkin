package script.theme_park.poi.tatooine.harrass;

import script.dictionary;
import script.location;
import script.obj_id;

public class poi_2_stormtrooper_1_farmer extends script.theme_park.poi.base
{
    public poi_2_stormtrooper_1_farmer()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        location here = new location(getLocation(self));
        obj_id farm = poiCreateObject("object/static/structure/tatooine/stone_hovel_tatooine_style_01.iff", 12, -2);
        obj_id tr1 = poiCreateNpc("stormtrooper", 10, 5);
        obj_id tr2 = poiCreateNpc("stormtrooper", 9, 4);
        obj_id farmer = poiCreateNpc("townperson", 11, 4);
        obj_id vaporator = poiCreateObject("object/installation/mining_liquid/mining_liquid_moisture_harvester.iff", 16, -8);
        attachScript(farmer, "theme_park.poi.tatooine.behavior.poi_victim");
        attachScript(tr1, "theme_park.poi.tatooine.behavior.poi_harrass");
        attachScript(tr2, "theme_park.poi.tatooine.behavior.poi_harrass");
        dictionary farmerObj = new dictionary();
        farmerObj.put("target", farmer);
        messageTo(tr1, "doFacing", farmerObj, 3, true);
        messageTo(tr2, "doFacing", farmerObj, 3, true);
        messageTo(tr1, "killTarget", farmerObj, 60, true);
        messageTo(tr2, "killTarget", farmerObj, 60, true);
        return SCRIPT_CONTINUE;
    }
}
