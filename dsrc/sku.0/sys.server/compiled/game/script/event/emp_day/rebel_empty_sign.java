package script.event.emp_day;

import script.*;
import script.library.*;

public class rebel_empty_sign extends script.base_script
{
    public rebel_empty_sign()
    {
    }
    public static final String EMPIREDAY = "event/empire_day";
    public static final String LIFEDAY = "event/life_day";
    public static final string_id SID_WRONG_FACTION = new string_id(EMPIREDAY, "wrong_faction");
    public static final string_id SID_MNU_DRESS = new string_id(EMPIREDAY, "hang_resistance_sign");
    public static final string_id SID_ZIP_DRESSING = new string_id(EMPIREDAY, "post_zip_bar_text");
    public static final string_id SID_PICK_PATH = new string_id(EMPIREDAY, "pick_path");
    public static final string_id SID_MOUNTED = new string_id(LIFEDAY, "mounted");
    public static final int COUNTDOWN_TIMER = 3;
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (isDead(player) || isIncapacitated(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (!(getTemplateName(self)).equals(holiday.REBEL_EMPTY_KIOSK))
        {
            return SCRIPT_CONTINUE;
        }
        if (factions.isImperial(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (!hasObjVar(player, holiday.PLAYER_EMPIRE_DAY_NEUTRAL + holiday.PLANET_VAR_PLAYER_FACTION_REB) && !factions.isRebel(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (!hasCompletedCollectionSlotPrereq(player, holiday.REBEL_RESISTANCE_COUNTER_SLOT))
        {
            return SCRIPT_CONTINUE;
        }
        if (!buff.hasBuff(player, holiday.BUFF_REB_EMPIREDAY_RESISTANCE_COMBATANT) && !buff.hasBuff(player, holiday.BUFF_REB_EMPIREDAY_RESISTANCE_SF))
        {
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(self, holiday.EMPIRE_DAY_RESISTANCE_LOCKED_OUT))
        {
            return SCRIPT_CONTINUE;
        }
        menu_info_data data = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        mi.addRootMenu(menu_info_types.ITEM_USE, SID_MNU_DRESS);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (isDead(player) || isIncapacitated(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (!(getTemplateName(self)).equals(holiday.REBEL_EMPTY_KIOSK))
        {
            return SCRIPT_CONTINUE;
        }
        if (!hasObjVar(player, holiday.PLAYER_EMPIRE_DAY_NEUTRAL + holiday.PLANET_VAR_PLAYER_FACTION_REB) && !factions.isRebel(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (!hasCompletedCollectionSlotPrereq(player, holiday.REBEL_RESISTANCE_COUNTER_SLOT))
        {
            return SCRIPT_CONTINUE;
        }
        if (!buff.hasBuff(player, holiday.BUFF_REB_EMPIREDAY_RESISTANCE_COMBATANT) && !buff.hasBuff(player, holiday.BUFF_REB_EMPIREDAY_RESISTANCE_SF))
        {
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(self, holiday.EMPIRE_DAY_RESISTANCE_LOCKED_OUT))
        {
            return SCRIPT_CONTINUE;
        }
        if (pet_lib.isMounted(player))
        {
            sendSystemMessage(player, holiday.SID_NOT_WHILE_MOUNTED);
            return SCRIPT_CONTINUE;
        }
        int startTime = 0;
        int range = 3;
        int flags = 0;
        flags |= sui.CD_EVENT_INCAPACITATE;
        flags |= sui.CD_EVENT_DAMAGED;
        stealth.testInvisNonCombatAction(player, self);
        int countdownSui = sui.smartCountdownTimerSUI(self, player, "quest_countdown_timer", SID_ZIP_DRESSING, startTime, COUNTDOWN_TIMER, "handleObjectSwapTimer", range, flags);
        doAnimationAction(player, "manipulate_high");
        return SCRIPT_CONTINUE;
    }
    public int handleObjectSwapTimer(obj_id self, dictionary params) throws InterruptedException
    {
        if (params == null)
        {
            return SCRIPT_CONTINUE;
        }
        int pid = params.getInt("id");
        obj_id player = params.getObjId("player");
        if (!isIdValid(player) || !exists(player))
        {
            return SCRIPT_CONTINUE;
        }
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            detachScript(player, sui.COUNTDOWNTIMER_PLAYER_SCRIPT);
            return SCRIPT_CONTINUE;
        }
        else if (bp == sui.BP_REVERT)
        {
            int event = params.getInt("event");
            if (event == sui.CD_EVENT_LOCOMOTION)
            {
                sendSystemMessage(player, new string_id("quest/groundquests", "countdown_interrupted_locomotion"));
            }
            else if (event == sui.CD_EVENT_INCAPACITATE)
            {
                sendSystemMessage(player, new string_id("quest/groundquests", "countdown_interrupted_incapacitated"));
            }
            else if (event == sui.CD_EVENT_DAMAGED)
            {
                sendSystemMessage(player, new string_id("quest/groundquests", "countdown_interrupted_damaged"));
            }
            return SCRIPT_CONTINUE;
        }
        if (!hasObjVar(player, sui.COUNTDOWNTIMER_SUI_VAR))
        {
            return SCRIPT_CONTINUE;
        }
        int test_pid = getIntObjVar(player, sui.COUNTDOWNTIMER_SUI_VAR);
        if (pid != test_pid)
        {
            return SCRIPT_CONTINUE;
        }
        forceCloseSUIPage(pid);
        detachScript(player, sui.COUNTDOWNTIMER_PLAYER_SCRIPT);
        if (buff.hasBuff(player, holiday.BUFF_REB_EMPIREDAY_RESISTANCE_SF) && !factions.isDeclared(player))
        {
            sendSystemMessage(player, holiday.SID_NOT_SF);
            return SCRIPT_CONTINUE;
        }
        if (!holiday.replaceEventObject(self, player, holiday.REBEL_PLAYER_RESISTANCE_KIOSK))
        {
            return SCRIPT_CONTINUE;
        }
        else 
        {
            String parentBuffName = "";
            if (buff.hasBuff(player, holiday.BUFF_REB_EMPIREDAY_RESISTANCE_COMBATANT))
            {
                parentBuffName = holiday.BUFF_REB_EMPIREDAY_RESISTANCE_COMBATANT;
            }
            else if (buff.hasBuff(player, holiday.BUFF_REB_EMPIREDAY_RESISTANCE_SF))
            {
                parentBuffName = holiday.BUFF_REB_EMPIREDAY_RESISTANCE_SF;
            }
            if (parentBuffName.equals(""))
            {
                return SCRIPT_CONTINUE;
            }
            if (!holiday.rewardEmpireDayPlayer(player, holiday.REBEL_PLAYER, parentBuffName, holiday.BUFF_REBEL_RESISTANCE_COUNTER, holiday.REBEL_RESISTANCE_COUNTER_SLOT, holiday.EMPIRE_DAY_RESISTANCE_LOCKED_OUT))
            {
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int destroySelf(obj_id self, dictionary params) throws InterruptedException
    {
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
}
