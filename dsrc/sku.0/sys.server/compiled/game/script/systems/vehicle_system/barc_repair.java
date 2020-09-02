package script.systems.vehicle_system;

import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class barc_repair extends script.base_script
{
    public barc_repair()
    {
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (isDisabled(self) && utils.playerHasItemByTemplate(player, "object/tangible/item/ep3/barc_repair_tool.iff"))
        {
            int lootList = mi.addRootMenu(menu_info_types.SERVER_MENU1, new string_id("barc_repair", "refurbish_barc"));
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.SERVER_MENU1)
        {
            if (isDisabled(self))
            {
                obj_id tool = utils.getItemPlayerHasByTemplate(player, "object/tangible/item/ep3/barc_repair_tool.iff");
                clearCondition(self, CONDITION_DISABLED);
                int maxHp = getMaxHitpoints(self);
                setHitpoints(self, maxHp);
                destroyObject(tool);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
