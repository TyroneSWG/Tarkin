package script.heroic.mob;

import script.library.ai_lib;
import script.library.money;
import script.obj_id;

public class heroic_mob extends script.base_script {

    public static int baseNumItemsHeroic = (rand(1, 3));
    public static String HEROIC_MOB_BASE_LOOT = "heroic_npc/blacksun_bunker";

    public heroic_mob()
    {
    }

    public static int OnAttach(obj_id self) throws InterruptedException
    {
        ai_lib.setAttackable(self, true);
        setObjVar(self, "loot.lootTable", HEROIC_MOB_BASE_LOOT);
        setObjVar(self, "loot.numItems", baseNumItemsHeroic);
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

    public static void OnEnteredCombat(obj_id self, obj_id attacker) throws InterruptedException
    {
        if (!isPlayer(attacker))
        {
            LOG("HEROIC_NEW", "Something freaky is happening, Attacker " + attacker + " is not a player!");
        }
    }

    public static void OnExitedCombat(obj_id self, obj_id attacker) throws InterruptedException
    {
        if (isDead(self))
        {
            int creditAmount = rand(1200, 4230);
            grantCredits(attacker, creditAmount);
        }
    }

    public static void grantCredits(obj_id player, int creditAmount) throws InterruptedException
    {
        money.deposit(player, creditAmount);
    }
}
