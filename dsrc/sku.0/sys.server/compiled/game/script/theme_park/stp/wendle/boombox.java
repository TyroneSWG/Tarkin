package script.theme_park.stp.wendle;

import script.*;
import static script.base_class.SCRIPT_CONTINUE;
import static script.base_class.doAnimationAction;
import static script.base_class.getIntendedTarget;
import static script.base_class.sendSystemMessage;
import static script.base_class.toLower;
import static script.event.ewok_festival.chief.GIVE_GIFT;
import static script.event.ewok_festival.chief.HAVE_GIFT;
import static script.event.ewok_festival.chief.OBJ_BOUQUET_QUEST;
import script.library.groundquests;
import script.library.utils;
/**
 *
 * @author Roachie
 */
public class boombox extends script.base_script {
    public boombox() {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
       setName(self, "a speaker");
       createTriggerVolume("boombox_cantina", 25.0f, true);
       return SCRIPT_CONTINUE;
    }
    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id breacher) throws InterruptedException
    {
        if (!isPlayer(breacher))
        {
            return SCRIPT_CONTINUE;
        }
        if (volumeName.equals("boombox_cantina"))
        {
            play2dNonLoopingMusic(breacher, "sound/mus_pirate_cantina.snd");
        }
        else
        {
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
