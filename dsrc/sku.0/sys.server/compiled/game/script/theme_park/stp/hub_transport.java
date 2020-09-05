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
    public static int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        menu_info_data data = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        if (data != null)
        {
            data.setServerNotify(true);
        }
        return SCRIPT_CONTINUE;
    }
    public static int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            sendSystemMessageTestingOnly(player, "Attempting to move you to the hub");
            instance.requestInstanceMovement(player, "hub");
        }
        return SCRIPT_CONTINUE;
    }
}
