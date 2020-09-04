package script.theme_park.stp.wendle;

import script.*;
/**
 *
 * @author Roachie
 */
public class sy_snootles extends script.base_script {
    public sy_snootles() {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "Sy Snootles");
        doAnimationAction(self, "sy_snootles_emt_dance_1");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
}
