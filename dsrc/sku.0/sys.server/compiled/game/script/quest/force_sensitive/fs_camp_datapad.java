package script.quest.force_sensitive;

import script.library.fs_quests;
import script.library.quests;
import script.library.utils;
import script.*;

public class fs_camp_datapad extends script.base_script
{
    public fs_camp_datapad()
    {
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.SERVER_MENU1, new string_id("sui", "use"));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.SERVER_MENU1)
        {
            if (utils.isOwner(self, player))
            {
                if (hasObjVar(player, "fs_kickoff_stage"))
                {
                    int stage = getIntObjVar(player, "fs_kickoff_stage");
                    if (stage == 6)
                    {
                        sendSystemMessage(player, new string_id("quest/force_sensitive/intro", "loot_disk1"));
                        return SCRIPT_CONTINUE;
                    }
                    if (stage == 7)
                    {
                        location here = getLocation(player);
                        if (!fs_quests.isValidLocationForEncounter(here))
                        {
                            sendSystemMessage(player, new string_id("quest/force_sensitive/intro", "datapad_failure"));
                            return SCRIPT_CONTINUE;
                        }
                        fs_quests.advanceStage(player);
                        quests.activate("fs_village_elder", player, null);
                        sendSystemMessage(player, new string_id("quest/force_sensitive/intro", "read_disk2"));
                        destroyObject(self);
                        if (hasObjVar(player, "theaterRecoveryTarget"))
                        {
                            removeObjVar(player, "theaterRecoveryTarget");
                        }
                        return SCRIPT_CONTINUE;
                    }
                }
            }
            sendSystemMessage(player, new string_id("quest/force_sensitive/intro", "read_disk_error"));
        }
        return SCRIPT_CONTINUE;
    }
}
