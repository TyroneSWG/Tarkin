package script.theme_park.stp;

import script.*;
/**
 *
 * @author Roachie
 */
public class invuln extends script.base_script {
    public invuln() {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        if (hasObjVar(self, "stp.npc.name"))
        {
            setName(self, getStringObjVar(self, "stp.npc.name"));
        }
        detachScript(self, "systems.combat.credit_for_kills");
        setInvulnerable(self, true);
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if (hasObjVar(self, "stp.npc.name"))
        {
            setName(self, getStringObjVar(self, "stp.npc.name"));
        }
        detachScript(self, "systems.combat.credit_for_kills");
        setInvulnerable(self, true);
        return SCRIPT_CONTINUE;
    }
}
