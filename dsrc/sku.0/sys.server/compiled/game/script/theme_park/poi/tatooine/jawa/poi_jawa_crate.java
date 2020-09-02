package script.theme_park.poi.tatooine.jawa;

import script.library.weapons;
import script.location;
import script.obj_id;

public class poi_jawa_crate extends script.theme_park.poi.base
{
    public poi_jawa_crate()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        String objective = poiGetObjective(self);
        if (objective.equals("jawas"))
        {
            obj_id jawa1 = poiCreateNpc("jawa", -1, 2);
            obj_id jawa2 = poiCreateNpc("jawa", -1, 3);
            obj_id jawa3 = poiCreateNpc("jawa", 1, 3);
            attachScript(jawa1, "theme_park.poi.tatooine.behavior.tusken_stationary_poi");
            attachScript(jawa2, "theme_park.poi.tatooine.behavior.tusken_stationary_poi");
            attachScript(jawa3, "theme_park.poi.tatooine.behavior.tusken_stationary_poi");
            location here = getLocation(self);
            obj_id crate = poiCreateObject(self, "center", "object/tangible/container/drum/tatt_drum_1.iff", 1, 2);
            attachScript(crate, "theme_park.poi.tatooine.behavior.jawa_crate");
            setObjVar(crate, "jawa1", jawa1);
            setObjVar(crate, "jawa2", jawa2);
            setObjVar(crate, "jawa3", jawa3);
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
