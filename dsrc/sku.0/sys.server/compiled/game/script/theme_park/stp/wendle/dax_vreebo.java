package script.theme_park.stp.wendle;

import script.*;
import script.library.ai_lib;
/**
 *
 * @author Roachie
 */
public class dax_vreebo extends script.base_script {
    public dax_vreebo() {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "Dax Vreebo");
        ai_lib.setMood(self, "themepark_music_3");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
}
