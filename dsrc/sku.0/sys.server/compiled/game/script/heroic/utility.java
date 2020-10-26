package script.heroic;

import script.obj_id;

public class utility extends script.base_script {

    public utility()
    {
    }

    public static int GROUP_SIZE_MINI_DUNGEON = 2;
    public static int GROUP_SIZE_DUNGEON = 4;
    public static int GROUP_SIZE_HEROIC = 8;
    public static String INSTANCE_ERROR_MSG = "There are currently no Instances available at the moment. Please try again later.";
    public static String INSTANCE_MIN_VERSION = "v1.0";
    public static int baseCreditAmount = 1200;
    public static int baseNumItems = rand(1, 3);
    public static int baseNumItemsElite = rand(2, 4);
    public static int baseNumItemsHeroic = rand(3, 5);
    public static String NORMAL_MOB_BASE_LOOT = "heroic_npc/blacksun_bunker:normal";
    public static String ELITE_MOB_BASE_LOOT = "heroic_npc/blacksun_bunker:elite";
    public static String HEROIC_MOB_BASE_LOOT = "heroic_npc/blacksun_bunker:heroic";

    public static int awardPayout(obj_id player) throws InterruptedException
    {
        if (!isIdValid(player))
        {
            debugConsoleMsg(player, "TODO: Add in payout.");
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
