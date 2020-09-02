package script.theme_park.outbreak;

import script.library.groundquests;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class epsilon_shuttle extends script.base_script
{
    public epsilon_shuttle()
    {
    }
    public static final String STF_FILE = "theme_park/outbreak/outbreak";
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (!groundquests.isTaskActive(player, "outbreak_quest_facility_05_imperial", "seeHX138") && !groundquests.isTaskActive(player, "outbreak_quest_facility_05_rebel", "seeHX138") && !groundquests.isTaskActive(player, "outbreak_quest_facility_05_neutral", "seeHX138"))
        {
            return SCRIPT_CONTINUE;
        }
        int mnu = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(STF_FILE, "board_shuttle"));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (!groundquests.isTaskActive(player, "outbreak_quest_facility_05_imperial", "seeHX138") && !groundquests.isTaskActive(player, "outbreak_quest_facility_05_rebel", "seeHX138") && !groundquests.isTaskActive(player, "outbreak_quest_facility_05_neutral", "seeHX138"))
        {
            return SCRIPT_CONTINUE;
        }
        if (groundquests.isTaskActive(player, "outbreak_quest_facility_05_imperial", "seeHX138") || groundquests.isTaskActive(player, "outbreak_quest_facility_05_rebel", "seeHX138") || groundquests.isTaskActive(player, "outbreak_quest_facility_05_neutral", "seeHX138"))
        {
            groundquests.sendSignal(player, "spokeToHX138Escape");
        }
        warpPlayer(player, "dathomir", -5693, 511, -6471, null, -5693, 511, -6471, null, true);
        return SCRIPT_CONTINUE;
    }
}
