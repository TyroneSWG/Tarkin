package script.library;

import script.obj_id;
import script.prose_package;
import script.string_id;

public class spam extends script.base_script
{
    public spam()
    {
    }
    public static final String STF_SPAM = "spam";
    public static final string_id PROSE_LOOT_ITEM_SELF = new string_id(STF_SPAM, "loot_item_self");
    public static final string_id PROSE_LOOT_ITEM_OTHER = new string_id(STF_SPAM, "loot_item_other");
    public static void lootItem(obj_id player, obj_id item, obj_id corpseId, boolean toGroup) throws InterruptedException
    {
        if (!isIdValid(player) || !isIdValid(item) || !isIdValid(corpseId))
        {
            return;
        }
        prose_package ppSelf = prose.getPackage(PROSE_LOOT_ITEM_SELF, item, corpseId);
        String oobPP = packOutOfBandProsePackage("", ppSelf);
        if (toGroup)
        {
            obj_id gid = getGroupObject(player);
            if (isIdValid(gid))
            {
                prose_package ppOther = prose.getPackage(PROSE_LOOT_ITEM_OTHER, player, item, corpseId);
                group.sendGroupChatMessage(gid, ppOther);
            }
        }
    }
    public static void lootItem(obj_id player, obj_id item, obj_id corpseId) throws InterruptedException
    {
        lootItem(player, item, corpseId, true);
    }
}
