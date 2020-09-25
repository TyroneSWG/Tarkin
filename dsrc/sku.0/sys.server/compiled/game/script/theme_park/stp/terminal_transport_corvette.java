package script.theme_park.stp;

import script.*;
/**
 *
 * @author Roachie
 */
public class terminal_transport_corvette extends script.base_script {
    public static String CORVETTE = "object/building/general/space_dungeon_corellian_corvette_base.iff";
    public terminal_transport_corvette() {
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
            obj_id[] targetLocs = getAllObjectsWithTemplate(getLocation(self), 16000.0f, CORVETTE);
            obj_id cell = getCellId(targetLocs[0], "hall2");
            warpPlayer(player, getCurrentSceneName(), -35.1f, 0.0f, 0.0f, cell, -35.1f, 0.0f, 0.0f);
        }
        return SCRIPT_CONTINUE;
    }
}

