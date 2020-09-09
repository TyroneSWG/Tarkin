package script.theme_park.stp;

import script.*;
/**
 *
 * @author Roachie
 */
public class stp_filler extends script.base_script {
    public stp_filler() {
    }
    
    public int OnAttach(obj_id self)
    {
        setInvulnerable(self, true);
        setName(self, "a Taanab structure");
        // They can't see these but this is more for visual clean up.
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setInvulnerable(self, true);
        setName(self, "a Taanab structure");
        // They can't see these but this is more for visual clean up.
        return SCRIPT_CONTINUE;
    }
}
