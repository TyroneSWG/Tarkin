package script.space.special_loot;

import script.obj_id;
import script.string_id;

public class imperial_only extends script.base_script
{
    public imperial_only()
    {
    }
    public static final string_id SID_ERROR = new string_id("space/space_loot", "imperial_only");
    public int OnAboutToBeTransferred(obj_id self, obj_id player, obj_id transferer) throws InterruptedException
    {
        if (!isPlayer(player))
        {
            obj_id appearInv = getAppearanceInventory(transferer);
            if (appearInv == player)
            {
                if (!hasSkill(transferer, "pilot_imperial_navy_novice"))
                {
                    sendSystemMessage(transferer, SID_ERROR);
                    return SCRIPT_OVERRIDE;
                }
            }
            return SCRIPT_CONTINUE;
        }
        if (!hasSkill(player, "pilot_imperial_navy_novice"))
        {
            sendSystemMessage(player, SID_ERROR);
            return SCRIPT_OVERRIDE;
        }
        return SCRIPT_CONTINUE;
    }
}
