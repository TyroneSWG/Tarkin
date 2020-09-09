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
        createTriggerVolume("hub_spammer", 15.0f, true);
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
            chat.chat(self, "Target Locked... Scanning " + getPlayerName(breacher));
        }
        return SCRIPT_CONTINUE;
    }
}
