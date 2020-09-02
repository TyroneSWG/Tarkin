package script.working;

import script.library.buff;
import script.library.create;
import script.library.utils;
import script.location;
import script.obj_id;

import java.util.StringTokenizer;

public class jmichener_test extends script.base_script
{
    public jmichener_test()
    {
    }
    public int OnSpeaking(obj_id self, String text) throws InterruptedException
    {
        obj_id player = self;
        if (isGod(player))
        {
            StringTokenizer st = new java.util.StringTokenizer(text);
            int tokens = st.countTokens();
            String command = null;
            if (st.hasMoreTokens())
            {
                command = st.nextToken();
            }
            if (command.equals("list_player_buffs"))
            {
                int[] playerBuffs = buff.getAllBuffs(player);
                String[] strPlayerBuffs = new String[playerBuffs.length];
                for (int i = 0; i < playerBuffs.length; i++)
                {
                    strPlayerBuffs[i] = buff.getBuffNameFromCrc(playerBuffs[i]);
                    sendSystemMessageTestingOnly(player, strPlayerBuffs[i]);
                }
                return SCRIPT_CONTINUE;
            }
            if (command.equals("remove_player_buffs"))
            {
                buff.removeAllBuffs(player);
                sendSystemMessageTestingOnly(player, "All buffs have been removed from player");
                return SCRIPT_CONTINUE;
            }
            if (command.equals("remove_entertainer_invis_buff"))
            {
                buff.removeBuff(player, "col_ent_invis_buff_tracker");
                sendSystemMessageTestingOnly(player, "Entertainer Timer Buff Removed(Build-A-Buff collection)");
                return SCRIPT_CONTINUE;
            }
            if (command.equals("check_has_entertainer_invis_buff"))
            {
                if (buff.hasBuff(player, "col_ent_invis_buff_tracker"))
                {
                    sendSystemMessageTestingOnly(player, "player has buff: 'col_ent_invis_buff_tracker'.");
                }
                else 
                {
                    sendSystemMessageTestingOnly(player, "player does NOT have buff: 'col_ent_invis_buff_tracker'.");
                }
                return SCRIPT_CONTINUE;
            }
            if (command.equals("remove_heroic_sd_invis_buff"))
            {
                buff.removeBuff(player, "col_sd_invis_buff_tracker");
                sendSystemMessageTestingOnly(player, "Heroic SD Taxi Buff Removed");
                return SCRIPT_CONTINUE;
            }
            if (command.equals("check_has_heroic_sd_invis_buff"))
            {
                if (buff.hasBuff(player, "col_sd_invis_buff_tracker"))
                {
                    sendSystemMessageTestingOnly(player, "player has buff: 'col_sd_invis_buff_tracker'.");
                }
                else 
                {
                    sendSystemMessageTestingOnly(player, "player does NOT have buff: 'col_sd_invis_buff_tracker'.");
                }
                return SCRIPT_CONTINUE;
            }
            if (command.equals("bypass_harvest_roll"))
            {
                utils.setScriptVar(player, "qa.resource_roll_bypass", "testing");
                sendSystemMessageTestingOnly(player, "resource roll has been bypassed - drop rate for exceptionals is 100%");
            }
            if (command.equals("remove_bypass_harvest_roll"))
            {
                utils.removeScriptVar(player, "qa.resource_roll_bypass");
                sendSystemMessageTestingOnly(player, "resource roll has been negated - drop rate for exceptionals is no longer bypassed");
            }
            if (command.equals("durni"))
            {
                location myLoc = getLocation(self);
                create.object("durni", myLoc, true);
                sendSystemMessageTestingOnly(player, "you created a durni");
            }
            if (command.equals("stop_regen"))
            {
                float QARegenRate = getActionRegenRate(player);
                setObjVar(player, "QARegenRate", QARegenRate);
                getActionRegenRate(player, 0);
                sendSystemMessageTestingOnly(player, "Your Regen Rate has been set to Zero!");
            }
            if (command.equals("restore_regen"))
            {
                float myStoredRegen = getFloatObjVar(player, "QARegenRate");
                getActionRegenRate(player, myStoredRegen);
                removeObjVar(self, "QARegenRate");
                if (!hasObjVar(player, "QARegenRate"))
                {
                    sendSystemMessageTestingOnly(player, "Your Action Regen Rate has been restored");
                }
                else 
                {
                    sendSystemMessageTestingOnly(player, "Problem - Regen Rate was not restored!");
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
