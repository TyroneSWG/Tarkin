package script.theme_park.stp.items;

import script.*;
/**
 *
 * @author Roachie
 */
public class the_child extends script.base_script {
    public the_child() {
    }
    
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "a mysterious child");
    }
}
