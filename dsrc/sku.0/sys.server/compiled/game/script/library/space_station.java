package script.library;

import script.*;
import script.library.*;

/**
 *
 * @author Roachie
 */
public class space_station extends script.base_script {
    public space_station() {
    }
    public static String HUB_TEMPLATE = "object/building/hub/space_station.iff";
    public int moveToHub(obj_id player) throws InterruptedException
    {
        obj_id planet = getPlanetByName("dungeon_hub");
        loc planetBase = getLocation(planet);
        obj_id[] stations = getAllObjectsWithTemplate(planetBase, 16000.0f, HUB_TEMPLATE);
        obj_id[] stationCells = getCellIds(stations[0]);
        warpPlayer(player, "dungeon_hub", 0.0f, 0.0f, 0.0f, stationCells[0], 0.0f, 0.0f, 0.0f);
        return SCRIPT_CONTINUE;
    }
}
