package script.library;

import script.*;
import script.location;
import script.library.*;

/**
 * @name space_station.java
 * @author Roachie
 * @purpose Library for new space station hub. Similar to that of the Imperial and Republic Fleet in SWTOR.
 * used for commerce, hanging out, some small quests, and overall new hangout area.
 */
public class space_station extends script.base_script {
    public space_station() {
    }
    public static String HUB_TEMPLATE = "object/building/hub/space_station.iff";
    public int moveToHub(obj_id player) throws InterruptedException
    {
        obj_id planet = getPlanetByName("dungeon_hub");
        if (isIdValid(planet))
        {
            return SCRIPT_CONTINUE;
        }
        location planetBase = getLocation(planet);
        obj_id[] stations = getAllObjectsWithTemplate(planetBase, 16000.0f, HUB_TEMPLATE);
        obj_id[] stationCells = getCellIds(stations[0]);
        warpPlayer(player, "dungeon_hub", 0.0f, 0.0f, 0.0f, stationCells[0], 0.0f, 0.0f, 0.0f);
        return SCRIPT_CONTINUE;
    }
}
