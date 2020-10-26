package script.heroic.item;

import script.obj_id;
import script.menu_info_types;
import script.menu_info_data;
import script.menu_info;
import script.string_id;
import script.library.chat;

/**
 *
 * @author TyroneSWG
 */
public class nuna_leg extends script.base_script {

    public nuna_leg()
    {
    }

    public static String YUM_MSG = "Delicious!";
    public static String YUCK_MSG = "I think I'm going to be sick... -pukes-";
    //TODO: refactor these!

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.getMenuItemByType(menu_info_types.ITEM_USE);
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("heroics_new", "nuna_leg"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            int idx = rand(0, 2);
            if (idx == 0)
            {
                chat.chat(player, YUCK_MSG);
                sendSystemMessageTestingOnly(player, "You have obtained food poisioning.");
                doAnimationAction(player, "heavy_cough_vomit");
            }
            if (idx == 1)
            {
                sendSystemMessageTestingOnly(player, "You take a bite of the Nuna Leg");
            }
            if (idx == 2)
            {
                chat.chat(player, YUM_MSG);
                sendSystemMessageTestingOnly(player, "You have been inspired by the quality of this food.");
                doAnimationAction(player, "back_flip");
            }

        }
        return SCRIPT_CONTINUE;
    }
}
