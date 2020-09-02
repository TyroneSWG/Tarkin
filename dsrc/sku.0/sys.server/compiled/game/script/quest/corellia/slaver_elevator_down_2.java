package script.quest.corellia;

import script.library.groundquests;
import script.*;

public class slaver_elevator_down_2 extends script.base_script
{
    public slaver_elevator_down_2()
    {
    }
    public static final String STF_FILE = "elevator_text";
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        int mnu2 = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "down"));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        location here = getLocation(player);
        location term = getLocation(self);
        float range = getDistance(here, term);
        if (range > 3.0)
        {
            sendSystemMessage(player, new string_id(STF_FILE, "too_far"));
            return SCRIPT_OVERRIDE;
        }
        if (item == menu_info_types.ITEM_USE)
        {
            if (!groundquests.hasCompletedTask(player, "corellia_tyrena_slaver_access", "hack_term_2c"))
            {
                sendSystemMessage(player, new string_id(STF_FILE, "murmur_denied"));
                return SCRIPT_OVERRIDE;
            }
            playClientEffectObj(player, "clienteffect/elevator_descend.cef", player, null);
            if (elevatorMove(player, -1) == 0)
            {
                sendSystemMessage(player, new string_id(STF_FILE, "lowest_floor"));
                return SCRIPT_OVERRIDE;
            }
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
