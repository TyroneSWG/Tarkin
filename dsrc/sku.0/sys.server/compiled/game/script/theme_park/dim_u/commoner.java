package script.theme_park.dim_u;

import script.*;
import script.library.ai_lib;
/**
 *
 * @author Roachie
 */
public class commoner extends script.base_script {
    public commoner() {
    }
    public static String[] NAMES = {
        "a Dim-U follower",
        "a Dim-U caretaker",
        "a commoner"
    };
    public int OnInitialize(obj_id self) throws InterruptedException {
        setInvulnerable(self, true);
        setName(self, NAMES[rand(0,2)]);
        ai_lib.setDefaultCalmBehavior(self, 1);
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException {
        setInvulnerable(self, true);
        setName(self, NAMES[rand(0,2)]);
        ai_lib.setDefaultCalmBehavior(self, 1);
        return SCRIPT_CONTINUE;
    }
}
