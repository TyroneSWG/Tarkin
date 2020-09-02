package script.npe;

import script.library.npe;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class uniform_crate extends script.base_script
{
    public uniform_crate()
    {
    }
    public static final String STF_FILE = "npe";
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        int mnu2 = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "crate_use"));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            sendSystemMessage(player, new string_id(STF_FILE, "opened_crate"));
            obj_id[] allTheArmor = npe.grantNewbArmor(player);
            destroyObject(self);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
