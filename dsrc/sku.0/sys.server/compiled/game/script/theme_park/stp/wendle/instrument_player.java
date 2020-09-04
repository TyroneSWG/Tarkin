package script.theme_park.stp.wendle;

import script.*;
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
        doAnimationAction(self, "all_f_dnc_f_gogo_loop");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
}
