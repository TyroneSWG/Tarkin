package script.theme_park.stp;

import script.*;
/**
 *
 * @author Roachie
 */
public class hologram extends script.base_script {
    public hologram() {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setState(self, STATE_GLOWING_JEDI, true);
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setState(self, STATE_GLOWING_JEDI, true);
        return SCRIPT_CONTINUE;
    }
}
