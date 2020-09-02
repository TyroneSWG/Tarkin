package script.theme_park.poi.tatooine.harrass;

import script.dictionary;
import script.location;
import script.obj_id;

public class poi_2_stormtrooper_4_farmer extends script.theme_park.poi.base
{
    public poi_2_stormtrooper_4_farmer()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        location here = new location(getLocation(self));
        obj_id farm = poiCreateObject("object/static/structure/tatooine/stone_hovel_tatooine_style_01.iff", 12, -2);
        obj_id tr1 = poiCreateNpc("stormtrooper", 10, 5);
        obj_id tr2 = poiCreateNpc("stormtrooper", 9, 4);
        obj_id farmer1 = poiCreateNpc("townperson", 10, 6);
        obj_id farmer2 = poiCreateNpc("townperson", 9, 7);
        obj_id farmer3 = poiCreateNpc("townperson", 11, 4);
        obj_id farmer4 = poiCreateNpc("townperson", 11, 6);
        obj_id vaporator = poiCreateObject("object/installation/mining_liquid/mining_liquid_moisture_harvester.iff", 16, -8);
        attachScript(farmer1, "theme_park.poi.tatooine.behavior.poi_harrass");
        attachScript(farmer2, "theme_park.poi.tatooine.behavior.poi_harrass");
        attachScript(farmer3, "theme_park.poi.tatooine.behavior.poi_harrass");
        attachScript(farmer4, "theme_park.poi.tatooine.behavior.poi_waiting");
        attachScript(tr1, "theme_park.poi.tatooine.behavior.poi_harrass");
        attachScript(tr2, "theme_park.poi.tatooine.behavior.poi_waiting");
        dictionary trooperObj1 = new dictionary();
        dictionary trooperObj2 = new dictionary();
        trooperObj1.put("target", tr1);
        trooperObj2.put("target", tr2);
        messageTo(farmer1, "killTarget", trooperObj1, 60, true);
        messageTo(farmer2, "killTarget", trooperObj1, 60, true);
        messageTo(farmer3, "killTarget", trooperObj2, 60, true);
        messageTo(farmer4, "killTarget", trooperObj2, 60, true);
        messageTo(farmer1, "doFacing", trooperObj1, 3, true);
        messageTo(farmer2, "doFacing", trooperObj1, 3, true);
        messageTo(farmer3, "doFacing", trooperObj2, 3, true);
        messageTo(farmer4, "doFacing", trooperObj2, 3, true);
        return SCRIPT_CONTINUE;
    }
}
