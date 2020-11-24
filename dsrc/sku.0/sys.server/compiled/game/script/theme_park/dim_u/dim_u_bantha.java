package script.theme_park.dim_u;

import script.*;
import script.library.ai_lib;
/**
 *
 * @author Roachie
 */
public class dim_u_bantha extends script.base_script {
    public dim_u_bantha() {
    }
    public int OnInitialize(obj_id self) throws InterruptedException {
        setInvulnerable(self, true);
        setName(self, "a bantha");
        ai_lib.setDefaultCalmBehavior(self, 1);
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException {
        setInvulnerable(self, true);
        setName(self, "a bantha");
        ai_lib.setDefaultCalmBehavior(self, 1);
        return SCRIPT_CONTINUE;
    }
}
