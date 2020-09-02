package script.theme_park.poi.tatooine.tusken_raider;

import script.library.weapons;
import script.location;
import script.obj_id;

public class poi_tuskenraider_crate extends script.theme_park.poi.base
{
    public poi_tuskenraider_crate()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        String objective = poiGetObjective(self);
        if (objective.equals("tuskens"))
        {
            obj_id tr1 = poiCreateNpc("tusken", -1, 2);
            obj_id tr2 = poiCreateNpc("tusken", -1, 3);
            obj_id tr3 = poiCreateNpc("tusken", 1, 3);
            attachScript(tr1, "theme_park.poi.tatooine.behavior.tusken_stationary_poi");
            attachScript(tr2, "theme_park.poi.tatooine.behavior.tusken_stationary_poi");
            attachScript(tr3, "theme_park.poi.tatooine.behavior.tusken_stationary_poi");
            location here = getLocation(self);
            obj_id crate = poiCreateObject(self, "center", "object/tangible/container/drum/tatt_drum_1.iff", 1, 2);
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
        return SCRIPT_CONTINUE;
    }
}
