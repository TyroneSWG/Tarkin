package script.theme_park.stp;

import script.library.ai_lib;
import script.obj_id;
/**
 *
 * @author Roachie
 * @purpose Randomize commoners names dynamically on start up to give a less static feel to the city.
 */
public class commoner extends script.base_script {
    public static String[] NAMES = {
        "a farmer",
        "a smuggler",
        "an artisan",
        "a medic",
        "a farmhand",
        "a field worker",
        "a miner",
        "a herder",
        "a warden"
    };
    
    public commoner() {
    }
    
    public int OnInitialize(obj_id self) throws InterruptedException {
        setInvulnerable(self, true);
        setName(self, NAMES[rand(0,8)]);
        ai_lib.setDefaultCalmBehavior(self, 1);
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException {
        setInvulnerable(self, true);
        setName(self, NAMES[rand(0,8)]);
        ai_lib.setDefaultCalmBehavior(self, 1);
        return SCRIPT_CONTINUE;
    }
}
