package script.theme_park.dungeon.geonosian_madbio_bunker;

import script.*;

public class shutoff extends script.base_script
{
    public shutoff()
    {
    }
    public static final String MSGS = "dungeon/geonosian_madbio";
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        if (mid == null)
        {
            return SCRIPT_CONTINUE;
        }
        mid.setServerNotify(true);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            obj_id trap = getObjIdObjVar(self, "trap");
            shutoffTrap(trap, player);
        }
        return SCRIPT_CONTINUE;
    }
    public void shutoffTrap(obj_id gas, obj_id player) throws InterruptedException
    {
        if (!hasObjVar(gas, "trap_off"))
        {
            dictionary test = new dictionary();
            test.put("player", player);
            messageTo(gas, "trapShutOff", test, 1, true);
            string_id gaseous = new string_id(MSGS, "gas_off");
            sendSystemMessage(player, gaseous);
        }
        else 
        {
            string_id alreadyOff = new string_id(MSGS, "gas_already_off");
            sendSystemMessage(player, alreadyOff);
        }
        return;
    }
}
