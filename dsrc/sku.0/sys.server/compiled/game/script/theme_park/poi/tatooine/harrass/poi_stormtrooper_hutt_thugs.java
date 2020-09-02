package script.theme_park.poi.tatooine.harrass;

import script.dictionary;
import script.library.weapons;
import script.location;
import script.obj_id;

public class poi_stormtrooper_hutt_thugs extends script.theme_park.poi.base
{
    public poi_stormtrooper_hutt_thugs()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        location here = new location(getLocation(self));
        obj_id st1 = poiCreateNpc("stormtrooper", 1, 0);
        obj_id st2 = poiCreateNpc("stormtrooper", 3, 0);
        obj_id thug1 = poiCreateNpc("hutt", 1, 2);
        obj_id thug2 = poiCreateNpc("hutt", 3, 2);
        obj_id crate = poiCreateObject("object/tangible/container/drum/tatt_drum_1.iff", 2, 4);
        attachScript(thug1, "theme_park.poi.tatooine.behavior.poi_harrass");
        attachScript(thug2, "theme_park.poi.tatooine.behavior.poi_harrass");
        attachScript(st1, "theme_park.poi.tatooine.behavior.poi_harrass");
        attachScript(st2, "theme_park.poi.tatooine.behavior.poi_harrass");
        dictionary thugObj1 = new dictionary();
        dictionary thugObj2 = new dictionary();
        thugObj1.put("target", thug1);
        thugObj2.put("target", thug2);
        messageTo(st1, "killTarget", thugObj1, 60, true);
        messageTo(st2, "killTarget", thugObj2, 60, true);
        messageTo(st1, "doFacing", thugObj1, 3, true);
        messageTo(st2, "doFacing", thugObj2, 3, true);
        int treasureItems = rand(1, 3);
        int treasureValue = rand(1, 2);
        treasureValue = (treasureValue - 1);
        int intX = 1;
        int treasureRows = dataTableGetNumRows("datatables/poi/treasure.iff");
        while (intX <= treasureItems)
        {
            int randItem = rand(1, treasureRows);
            randItem = (randItem - 1);
            String treasure = dataTableGetString("datatables/poi/treasure.iff", randItem, treasureValue);
            obj_id treasureItem = weapons.createPossibleWeapon(treasure, crate, rand(0.5f, 0.85f));
            intX = intX + 1;
        }
        return SCRIPT_CONTINUE;
    }
}
