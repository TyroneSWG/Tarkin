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
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setName(self, "Transport Terminal");
        return SCRIPT_CONTINUE;
    }
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
            location hub = new location();
            hub.area = "dungeon_hub";
            hub.x = 0;
            hub.y = 0;
            hub.z = 0;
            obj_id[] targetLocs = getAllObjectsWithTemplate(hub, 16000.0f, "object/building/hub/space_station.iff");
            obj_id cell = getCellId(targetLocs[0], "hangarbay1");
            warpPlayer(player, "dungeon_hub", 0.0f, 0.0f, 0.0f, cell, 0.0f, 0.0f, 0.0f);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }    
}
