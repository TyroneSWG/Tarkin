package script.theme_park.poi.tatooine.stormtrooper;

import script.dictionary;
import script.library.weapons;
import script.obj_id;

public class poi_stormtrooper_crate extends script.theme_park.poi.base
{
    public poi_stormtrooper_crate()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        String objective = poiGetObjective(self);
        if (objective.equals("troopers"))
        {
            obj_id tr1 = poiCreateNpc("st1", "stormtrooper", 2, 0);
            poiSetDestroyMessage(tr1, "trooperKilled");
            obj_id tr2 = poiCreateNpc("st2", "stormtrooper", 1, 1);
            poiSetDestroyMessage(tr2, "trooperKilled");
            obj_id tr3 = poiCreateNpc("st3", "stormtrooper", 1, -1);
            poiSetDestroyMessage(tr3, "trooperKilled");
            attachScript(tr1, "theme_park.poi.tatooine.behavior.stormtrooper_stationary_poi");
            attachScript(tr2, "theme_park.poi.tatooine.behavior.stormtrooper_stationary_poi");
            attachScript(tr3, "theme_park.poi.tatooine.behavior.stormtrooper_stationary_poi");
            obj_id crate = poiCreateObject(self, "center", "object/tangible/container/drum/tatt_drum_1.iff", 1, 0);
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
    public int trooperKilled(obj_id self, dictionary params) throws InterruptedException
    {
        int numKilled = getIntObjVar(self, "numTroopersKilled");
        numKilled++;
        if (numKilled > 1)
        {
            poiComplete(POI_SUCCESS);
        }
        setObjVar(self, "numTroopersKilled", numKilled);
        return SCRIPT_CONTINUE;
    }
}
