package script.terminal;

import script.*;
/**
 *
 * @author Roachie
 */
public class terminal_mechanic extends script.base_script {
    public terminal_mechanic() {
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        menu_info_data data = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("ui_terminal", "use"));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            sendSystemMessageTestingOnly(player, "pls compile");
        }
        return SCRIPT_CONTINUE;
    }
}
