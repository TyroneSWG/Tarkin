package script.theme_park.stp.wendle;

import script.*;
import script.library.ai_lib;
/**
 *
 * @author Roachie
 */
public class sy_snootles extends script.base_script {
    public sy_snootles() {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "Sy Snootles");
        ai_lib.setMood(self, "themepark_sy_snootles");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
}
