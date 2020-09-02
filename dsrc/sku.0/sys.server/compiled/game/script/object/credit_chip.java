package script.object;

import script.library.money;
import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class credit_chip extends script.base_script
{
    public credit_chip()
    {
    }
    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        int intCredits = getIntObjVar(self, "loot.intCredits");
        int idx = utils.getValidAttributeIndex(names);
        if (idx == -1)
        {
            return SCRIPT_CONTINUE;
        }
        names[idx] = "amount";
        attribs[idx] = Integer.toString(intCredits);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        obj_id objTopmostContainer = utils.getContainingPlayer(self);
        if (objTopmostContainer != player)
        {
            LOG("space", "container is " + objTopmostContainer);
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.ITEM_USE)
        {
            int intCredits = getIntObjVar(self, "loot.intCredits");
            money.bankTo("space_loot", player, intCredits);
            destroyObject(self);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id objPlayer, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("space/space_loot", "use_credit_chip"));
        return SCRIPT_CONTINUE;
    }
}
