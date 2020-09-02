package script.theme_park.nym;

import script.library.groundquests;
import script.obj_id;
import script.string_id;

public class droid_cave_gate extends script.base_script
{
    public droid_cave_gate()
    {
    }
    public int OnAboutToReceiveItem(obj_id self, obj_id destinationCell, obj_id transferrer, obj_id item) throws InterruptedException
    {
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        if (!groundquests.isTaskActive(item, "u16_nym_themepark_pirate_hideout", "findDroidDisk") && !groundquests.isTaskActive(item, "u16_nym_themepark_pirate_hideout", "returnPirateComplete") && !groundquests.hasCompletedQuest(item, "u16_nym_themepark_pirate_hideout"))
        {
            string_id warning = new string_id("theme_park_nym/messages", "hack_computer");
            sendSystemMessage(item, warning);
            return SCRIPT_OVERRIDE;
        }
        else 
        {
            return SCRIPT_CONTINUE;
        }
    }
}
