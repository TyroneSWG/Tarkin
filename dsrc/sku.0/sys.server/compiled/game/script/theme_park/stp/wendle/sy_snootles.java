package script.theme_park.stp.wendle;

import script.*;
import script.library.ai_lib;
import script.library.chat;
/**
 *
 * @author Roachie
 */
public class sy_snootles extends script.base_script {
    public sy_snootles() {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        detachScript(self, "systems.combat.credit_for_kills");
        messageTo(self, "setupNPC", null, 60.0f, true);
        return SCRIPT_CONTINUE;
    }
    public void setupNPC(obj_id self) throws InterruptedException
    {
        setName(self, "Sy Snootles");
        ai_lib.setMood(self, "themepark_sy_snootles");
        chat.chat(self, "Play that track back Dax!");
    }
}
