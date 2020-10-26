package script.heroic.mob;

import script.library.ai_lib;
import script.obj_id;

public class elite_mob extends script.base_script {

    public static int baseNumItemsElite = (rand(1, 3));
    public static String ELITE_MOB_BASE_LOOT = "heroic/blacksun_bunker";

    public elite_mob()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        ai_lib.setAttackable(self, true);
        setObjVar(self, "loot.lootTable", ELITE_MOB_BASE_LOOT);
        setObjVar(self, "loot.numItems", baseNumItemsElite);
        return SCRIPT_CONTINUE;
    }

    public int OnDeath(obj_id self, obj_id killer) throws InterruptedException
    {
        if (!isPlayer(killer))
        {
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
