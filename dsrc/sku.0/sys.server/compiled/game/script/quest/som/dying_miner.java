package script.quest.som;

import script.library.groundquests;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class dying_miner extends script.base_script
{
    public dying_miner()
    {
    }
    public static final string_id MNU_INSPECT_MINER = new string_id("som/som_quest", "examine_miner");
    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (groundquests.isQuestActive(player, "som_obi_wan_signal_1"))
        {
            int mnuColor = mi.addRootMenu(menu_info_types.SERVER_MENU1, MNU_INSPECT_MINER);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.SERVER_MENU1)
        {
            groundquests.sendSignal(player, "dyingMiner");
            groundquests.grantQuest(player, "som_obi_wan_signal_2");
        }
        return SCRIPT_CONTINUE;
    }
}
