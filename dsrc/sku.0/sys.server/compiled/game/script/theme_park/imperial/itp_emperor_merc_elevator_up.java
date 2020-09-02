package script.theme_park.imperial;

import script.library.groundquests;
import script.*;

public class itp_emperor_merc_elevator_up extends script.base_script
{
    public itp_emperor_merc_elevator_up()
    {
    }
    public static final String STF_FILE = "elevator_text";
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        int mnu = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "up"));
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
            playClientEffectObj(player, "clienteffect/elevator_rise.cef", player, null);
            if (groundquests.isQuestActiveOrComplete(player, "itp_emperor_03"))
            {
                obj_id bunker = getTopMostContainer(self);
                if (isIdValid(bunker))
                {
                    obj_id targetCell = getCellId(bunker, "elevator1");
                    warpPlayer(player, "endor", 3640.2f, 289.3f, 7419.3f, targetCell, -6.8f, -12.0f, 59.1f, "nullCallback", false);
                }
            }
            else 
            {
                if (elevatorMove(player, 1) == 0)
                {
                    sendSystemMessage(player, new string_id(STF_FILE, "highest_floor"));
                    return SCRIPT_OVERRIDE;
                }
            }
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
