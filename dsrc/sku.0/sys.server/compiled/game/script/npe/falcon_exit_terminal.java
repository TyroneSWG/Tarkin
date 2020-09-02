package script.npe;

import script.library.groundquests;
import script.library.npe;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class falcon_exit_terminal extends script.base_script
{
    public falcon_exit_terminal()
    {
    }
    public static final string_id HYPERSPACE = new string_id("npe", "hyperspace");
    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, HYPERSPACE);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (checkGod(player))
            {
                return SCRIPT_CONTINUE;
            }
            groundquests.grantQuest(player, "quest/npe_solo_profession");
            messageTo(getTopMostContainer(player), "endEncounter", null, 0, false);
            npe.movePlayerFromFalconToSharedStation(player);
        }
        return SCRIPT_CONTINUE;
    }
    public boolean checkGod(obj_id self) throws InterruptedException
    {
        if (isGod(self))
        {
            sendSystemMessageTestingOnly(self, "Please turn off god mode when moving between npe locations. God mode and instances do not get along");
            return true;
        }
        return false;
    }
}
