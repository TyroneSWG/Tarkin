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
    public int OnAddedToWorld(obj_id self) throws InterruptedException
    {
        messageTo(self, "setupNPC", null, 60.0f, true);
        detachScript(self, "systems.combat.credit_for_kills");
        return SCRIPT_CONTINUE;
    }
    public int setupNPC(obj_id self) throws InterruptedException
    {
        setName(self, "a dancer");
        setInvulnerable(self, true);
        ai_lib.setMood(self, "themepark_oola");
        return SCRIPT_CONTINUE;
    }
}
