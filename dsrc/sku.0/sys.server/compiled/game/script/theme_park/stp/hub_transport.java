package script.theme_park.stp;

import script.*;
import script.library.instance;
import script.library.space_station;
/**
 *
 * @author Roachie
 */
public class hub_transport extends script.base_script {
    public hub_transport() {
    }
    public static String SS = "object/building/hub/space_station.iff";
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        menu_info_data data = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        if (data != null)
        {
            data.setServerNotify(true);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            warpPlayer(player, "dungeon_hub", 0.0f, 0.0f, 0.0f, null, 0.0f, 0.0f, 0.0f, "doHubMove");
            //messageTo(player, "doHubMove", null, 2.0f, true);
        }
        return SCRIPT_CONTINUE;
    }
    public int doHubMove(obj_id self) throws InterruptedException
    {
            obj_id[] targetLocs = getAllObjectsWithTemplate(getLocation(self), 16000.0f, "object/building/hub/space_station.iff");
            obj_id cell = getCellId(targetLocs[0], "hangarbay1");
            warpPlayer(self, "dungeon_hub", 0.0f, 0.0f, 0.0f, cell, 0.0f, 0.0f, 0.0f);
            return SCRIPT_CONTINUE;
    }
    
}
