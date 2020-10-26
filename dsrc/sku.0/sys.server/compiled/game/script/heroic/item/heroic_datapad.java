package script.heroic.item;

import script.obj_id;
import script.menu_info_types;
import script.menu_info;
import script.string_id;
import script.library.sui;
import script.library.utils;

/**
 *
 * @author TyroneSWG
 */
public class heroic_datapad extends script.base_script {

    public heroic_datapad()
    {
    }

    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs)
        throws InterruptedException
    {
        int idx = utils.getValidAttributeIndex(names);
        if (idx == -1)
        {
            return SCRIPT_CONTINUE;
        }
        int count = getIntObjVar(player, "heroic.kills");
        names[idx] = "heroics_kills";
        attribs[idx] = Integer.toString(count);
        return SCRIPT_CONTINUE;
    }

    public static int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.getMenuItemByType(menu_info_types.ITEM_USE);
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("heroics_new", "heroic_datapad"));
        return SCRIPT_CONTINUE;
    }

    public static int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (!hasObjVar(player, "heroic.karma") || !hasObjVar(player, "heroic.completions"))
        {
            setObjVar(player, "heroic.karma", 0);
            setObjVar(player, "heroic.completions", 0);
        }
        String currentKarma = getStringObjVar(player, "heroic.karma");
        String currentCompleted = getStringObjVar(player, "heroic.completions");
        String[] datapad_options =
        {
            currentKarma, currentCompleted
        };
        if (item == menu_info_types.ITEM_USE)
        {
            sui.listbox(player, "Current Heroic Data:", datapad_options);
        }
        return SCRIPT_CONTINUE;
    }
}
