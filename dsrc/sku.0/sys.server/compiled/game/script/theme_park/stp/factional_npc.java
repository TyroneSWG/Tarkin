package script.theme_park.stp;

import script.*;
import static script.base_class.SCRIPT_CONTINUE;
import static script.base_class.rand;
import static script.base_class.setInvulnerable;
import static script.base_class.setName;
import script.library.ai_lib;
import script.library.factions;
/**
 *
 * @author Roachie
 */
public class factional_npc extends script.base_script {
    public factional_npc() {
    }
    public static String IMPERIAL_PREFIX = "TK-";
    public static String REBEL_SUFFIX = "(a Rebel Trooper)";
        
    public int OnInitialize(obj_id self) throws InterruptedException {
        if (getStringObjVar(self, "stp.faction").equals("imperial"))
        {
            factions.setFaction(self, "Imperial");
            setInvulnerable(self, true);
            setName(self, IMPERIAL_PREFIX + rand(1000, 9999));
            ai_lib.setDefaultCalmBehavior(self, 1);
        }
        if (getStringObjVar(self, "stp.faction").equals("rebel"))
        {
            factions.setFaction(self, "Rebel");
            setInvulnerable(self, true);
            setName(self, getName(self) + " " + REBEL_SUFFIX);
            ai_lib.setDefaultCalmBehavior(self, 1);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException {
        if (getStringObjVar(self, "stp.faction").equals("imperial"))
        {
            factions.setFaction(self, "Imperial");
            setInvulnerable(self, true);
            setName(self, IMPERIAL_PREFIX + rand(1000, 9999));
            ai_lib.setDefaultCalmBehavior(self, 1);
        }
        if (getStringObjVar(self, "stp.faction").equals("rebel"))
        {
            factions.setFaction(self, "Rebel");
            setInvulnerable(self, true);
            setName(self, getName(self) + " " + REBEL_SUFFIX);
            ai_lib.setDefaultCalmBehavior(self, 1);
        }
        return SCRIPT_CONTINUE;
    }
}
