package script.theme_park.stp.wendle;

import script.*;
import script.library.ai_lib;
/**
 *
 * @author Roachie
 */
public class instrument_player extends script.base_script {
    public instrument_player() {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "a dancer");
        ai_lib.setMood(self, "themepark_oola");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
}
