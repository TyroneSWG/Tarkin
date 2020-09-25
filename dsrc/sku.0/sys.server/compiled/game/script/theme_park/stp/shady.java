package script.theme_park.stp;

import script.*;
import static script.base_class.SCRIPT_CONTINUE;
import static script.base_class.rand;
import static script.base_class.setInvulnerable;
import static script.base_class.setName;
import script.library.ai_lib;
/**
 *
 * @author Roachie
 */
public class shady extends script.base_script {
    public shady() {
    }
    public static String[] NAMES = {
        "a thief",
        "an unethical farmer",
        "a cheapskate",
        "a gambler",
        "a thug",
        "a shady commoner",
        "an uptight gambler",
        "an anti-farmer commoner"
    };
        
    public int OnInitialize(obj_id self) throws InterruptedException {
        setInvulnerable(self, true);
        setName(self, NAMES[rand(0,7)]);
        ai_lib.setDefaultCalmBehavior(self, 1);
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException {
        setInvulnerable(self, true);
        setName(self, NAMES[rand(0,7)]);
        ai_lib.setDefaultCalmBehavior(self, 1);
        return SCRIPT_CONTINUE;
    }
}
