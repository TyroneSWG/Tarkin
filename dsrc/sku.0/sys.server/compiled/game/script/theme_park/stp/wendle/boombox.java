package script.theme_park.stp.wendle;

import script.*;
/**
 *
 * @author Roachie
 */
public class boombox extends script.base_script {
    public boombox() {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
       setName(self, "a radio");
       return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
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
            String soundFile = "sound/mus_pirate_cantina.snd";
            String soundFile2 = "sound/mus_cantina_band_remix.snd";
            int whichOne = rand(1,100);
            if (whichOne <= 50)
            {
                playClientEffectObj(breacher, soundFile2, self, "root", new transform(), "");
            }
            if (whichOne >= 50)
            {
                playClientEffectObj(breacher, soundFile, self, "root", new transform(), "");
            }
        }
        else
        {
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}