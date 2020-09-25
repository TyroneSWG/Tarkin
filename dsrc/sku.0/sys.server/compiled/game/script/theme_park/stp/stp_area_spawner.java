package script.theme_park.stp;

import script.*;
/**
 *
 * @author Roachie
 */
public class stp_area_spawner extends script.base_script {
    public stp_area_spawner() {
    }
    
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setObjVar(self, "spawner_type", "string_change_me");
        setObjVar(self, "spawner_max_spawns", "int_change_me");
        setObjVar(self, "spawner_type", "int_change_me");
        setObjVar(self, "spawner_level", "int_change_me");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
}
