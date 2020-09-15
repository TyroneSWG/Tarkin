package script.theme_park.stp;

import script.*;
import script.library.chat;
/**
 *
 * @author Roachie
 */
public class spammer extends script.base_script {
    public spammer() {
    }
    public static int OnAttach(obj_id self) throws InterruptedException
    {
        createTriggerVolume("hub_spammer", 5.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id breacher) throws InterruptedException
    {
        if (!isPlayer(breacher))
        {
            return SCRIPT_CONTINUE;
        }
        if (volumeName.equals("hub_spammer"))
        {
            chat.chat(self, "Scanning Target....");
            chat.chat(self, "Target locked: " + getFirstName(breacher).toUpperCase());
            if (hasObjVar(breacher, "hub.generator_quest"))
            {
                chat.chat(self, "Hostile target determined. Intializing Combat Routine");
                startCombat(self, breacher);
            }
            else
            {
                chat.chat(self, "No hostile targets found. Recycling...");
            }
        }
        return SCRIPT_CONTINUE;
    }
}
