package script.item.slicing;

import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class salvage_kit extends script.base_script
{
    public salvage_kit()
    {
    }
    public static final string_id SID_SALVAGE = new string_id("slicing/slicing", "salvage");
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        obj_id inventory = getObjectInSlot(player, "inventory");
        if (!utils.isNestedWithin(self, player))
        {
            return SCRIPT_CONTINUE;
        }
        mi.addRootMenu(menu_info_types.SERVER_MENU1, SID_SALVAGE);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        obj_id inventory = getObjectInSlot(player, "inventory");
        if (!utils.isNestedWithin(self, player))
        {
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.SERVER_MENU1)
        {
            String new_obj = "";
            if (hasObjVar(self, "slicing.weapon_kit"))
            {
                int roll = rand(1, 10000);
                if (roll == 1)
                {
                    new_obj = "object/tangible/smuggler/delicate_trigger_assembly.iff";
                }
                else if (roll > 1 && roll < 8000)
                {
                    new_obj = "object/tangible/smuggler/simple_toolkit.iff";
                }
                else if (roll >= 8000 && roll < 9900)
                {
                    new_obj = "object/tangible/smuggler/finely_crafted_toolset.iff";
                }
                else 
                {
                    new_obj = "object/tangible/smuggler/illegal_core_booster.iff";
                }
            }
            else if (hasObjVar(self, "slicing.armor_kit"))
            {
                int roll = rand(1, 10000);
                if (roll == 1)
                {
                    new_obj = "object/tangible/smuggler/mandalorian_interframe.iff";
                }
                else if (roll > 1 && roll < 7000)
                {
                    new_obj = "object/tangible/smuggler/simple_toolkit.iff";
                }
                else if (roll >= 7000 && roll < 8000)
                {
                    new_obj = "object/tangible/smuggler/shock_absorbent_padding.iff";
                }
                else if (roll >= 8000 && roll < 9000)
                {
                    new_obj = "object/tangible/smuggler/powered_motivators.iff";
                }
                else if (roll >= 9000 && roll < 9500)
                {
                    new_obj = "object/tangible/smuggler/reactive_micro_plating.iff";
                }
                else 
                {
                    new_obj = "object/tangible/smuggler/trandoshan_interframe.iff";
                }
            }
            createObject(new_obj, inventory, "");
            destroyObject(self);
        }
        return SCRIPT_CONTINUE;
    }
}
