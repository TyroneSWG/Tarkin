package script.test;

import script.library.static_item;
import script.library.utils;
import script.location;
import script.obj_id;

public class ai_rsitton extends script.base_script
{
    public ai_rsitton()
    {
    }
    public static final String s_logLabel = "ai_rsitton";
    public int OnSpeaking(obj_id self, String text) throws InterruptedException
    {
        if (isGod(self))
        {
            java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(text);
            if (tokenizer.hasMoreTokens())
            {
                String command = tokenizer.nextToken();
                LOG(s_logLabel, "fnord: " + command + "--------------");
                if (command.equalsIgnoreCase("fnord_waypoint"))
                {
                    location here = getWorldLocation(self);
                    createWaypointInDatapadInternal(self, here, "shared_starport_tatooine.iff", "arrivals1");
                }
                else if (command.equalsIgnoreCase("fnord_client_path"))
                {
                    obj_id target = getLookAtTarget(self);
                    createClientPathAdvanced(self, getLocation(self), getLocation(target), "default");
                }
                else if (command.equalsIgnoreCase("fnord_static_weapon"))
                {
                    obj_id objInventory = utils.getInventoryContainer(self);
                    obj_id lootItem = static_item.createNewItemFunction("weapon_carbine_02_03", objInventory);
                    sendSystemMessageTestingOnly(self, "made " + lootItem);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
