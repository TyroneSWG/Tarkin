package script.terminal;

import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class travel extends script.terminal.base.base_terminal
{
    public travel()
    {
    }
    public static final string_id SID_TRAVEL_OPTIONS = new string_id("terminal_ui", "travel_options");
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        int mnu = mi.addRootMenu(menu_info_types.SERVER_TRAVEL_OPTIONS, SID_TRAVEL_OPTIONS);
        return super.OnObjectMenuRequest(self, player, mi);
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.SERVER_TRAVEL_OPTIONS)
        {
        }
        return SCRIPT_CONTINUE;
    }
}
