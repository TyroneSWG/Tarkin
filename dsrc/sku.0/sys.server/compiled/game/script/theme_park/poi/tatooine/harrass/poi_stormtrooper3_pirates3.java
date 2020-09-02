package script.theme_park.poi.tatooine.harrass;

import script.dictionary;
import script.library.weapons;
import script.location;
import script.obj_id;

public class poi_stormtrooper3_pirates3 extends script.theme_park.poi.base
{
    public poi_stormtrooper3_pirates3()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        location here = new location(getLocation(self));
        obj_id st1 = poiCreateNpc("stormtrooper", 1, 0);
        obj_id st2 = poiCreateNpc("stormtrooper", 3, 0);
        obj_id st3 = poiCreateNpc("stormtrooper", 2, -1);
        obj_id pirate1 = poiCreateNpc("pirate", 1, 2);
        obj_id pirate2 = poiCreateNpc("pirate", 3, 2);
        obj_id pirate3 = poiCreateNpc("pirate", 2, 5);
        obj_id crate = poiCreateObject("object/tangible/container/drum/tatt_drum_1.iff", 2, 4);
        attachScript(pirate1, "theme_park.poi.tatooine.behavior.poi_harrass");
        attachScript(pirate2, "theme_park.poi.tatooine.behavior.poi_harrass");
        attachScript(pirate3, "theme_park.poi.tatooine.behavior.poi_waiting");
        attachScript(pirate1, "theme_park.poi.tatooine.behavior.pirate_crate_dialog");
        attachScript(pirate3, "theme_park.poi.tatooine.behavior.pirate_crate_dialog");
        attachScript(st1, "theme_park.poi.tatooine.behavior.poi_harrass");
        attachScript(st2, "theme_park.poi.tatooine.behavior.poi_harrass");
        attachScript(st3, "theme_park.poi.tatooine.behavior.poi_waiting");
        attachScript(st1, "theme_park.poi.tatooine.behavior.stormtrooper_crate_dialog");
        dictionary pirateObj1 = new dictionary();
        dictionary pirateObj2 = new dictionary();
        dictionary pirateObj3 = new dictionary();
        pirateObj1.put("target", pirate1);
        pirateObj2.put("target", pirate2);
        pirateObj3.put("target", pirate3);
        messageTo(st1, "killTarget", pirateObj1, 60, true);
        messageTo(st2, "killTarget", pirateObj2, 60, true);
        messageTo(st3, "killTarget", pirateObj3, 60, true);
        messageTo(st1, "doFacing", pirateObj1, 3, true);
        messageTo(st2, "doFacing", pirateObj2, 3, true);
        messageTo(st3, "doFacing", pirateObj3, 3, true);
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
