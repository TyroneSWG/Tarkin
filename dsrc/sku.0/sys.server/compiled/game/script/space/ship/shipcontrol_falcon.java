package script.space.ship;

import script.library.space_transition;
import script.library.space_utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class shipcontrol_falcon extends script.base_script
{
    public shipcontrol_falcon()
    {
    }
    public static final string_id SID_PILOT = new string_id("space/space_interaction", "pilot_ship");
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        obj_id objShip = space_transition.getContainingShip(player);
        if (space_utils.playerCanControlShipSlot(objShip, player, true))
        {
            mi.addRootMenu(menu_info_types.SERVER_MENU1, SID_PILOT);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.SERVER_MENU1)
        {
            obj_id objShip = space_transition.getContainingShip(player);
            if (space_utils.playerCanControlShipSlot(objShip, player, false))
            {
                queueCommand(player, (-185340673), null, "", COMMAND_PRIORITY_DEFAULT);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
