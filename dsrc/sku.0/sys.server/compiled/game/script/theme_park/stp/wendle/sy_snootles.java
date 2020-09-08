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
        grantSkill(self, "social_entertainer_novice");
        detachScript(self, "systems.combat.credit_for_kills");
        setName(self, "Sy Snootles");
        ai_lib.setMood(self, "themepark_sy_snootles");
        chat.chat(self, "Play that track back Dax!");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        grantSkill(self, "social_entertainer_novice");
        detachScript(self, "systems.combat.credit_for_kills");
        setName(self, "Sy Snootles");
        ai_lib.setMood(self, "themepark_sy_snootles");
        chat.chat(self, "Play that track back Dax!");
        return SCRIPT_CONTINUE;
    }
}
