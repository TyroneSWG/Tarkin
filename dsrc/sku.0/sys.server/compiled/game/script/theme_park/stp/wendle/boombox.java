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
       createTriggerVolume("boombox_cantina", 15.0f, false);
       setName(self, "a radio");
       return SCRIPT_CONTINUE;
    }
    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id breacher) throws InterruptedException
    {
        if (isPlayer(breacher) && (volumeName.equals("boombox_cantina")))
        {
            String soundFile = "music/mus_pirate_cantina.mp3";
            String soundFile2 = "music/mus_cantina_band_remix.mp3";
            setName(self, getName(self) + " (active)");
            play2dNonLoopingMusic(breacher, soundFile);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}