package script.theme_park.dungeon.mustafar_trials.decrepit_droid_factory;

import script.*;
import script.library.sui;
import script.library.trial;
import script.library.utils;

public class code_terminal_master extends script.base_script
{
    public code_terminal_master()
    {
    }
    public static final String DECREPIT_STF = "mustafar/decrepit_droid_factory";
    public static final string_id SID_ENTER_ACCESS_CODE = new string_id(DECREPIT_STF, "enter_second_floor_code");
    public static final string_id SID_CODE_RESET = new string_id(DECREPIT_STF, "reset_security_code");
    public static final string_id SID_INVALID_CODE = new string_id(DECREPIT_STF, "invalid_code");
    public static boolean LOGGING = false;
    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        boolean isUnlocked = trial.isCellPublic(self, trial.DECREPIT_ONE_TWO_STAIR);
        if (!isUnlocked)
        {
            mi.addRootMenu(menu_info_types.ITEM_USE, SID_ENTER_ACCESS_CODE);
        }
        else 
        {
            mi.addRootMenu(menu_info_types.ITEM_USE, trial.SID_NOT_LOCKED);
        }
        sendDirtyObjectMenuNotification(self);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (!trial.isCellPublic(self, trial.DECREPIT_ONE_TWO_STAIR))
            {
                showCodeEntrySui(self, player);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public void showCodeEntrySui(obj_id terminal, obj_id player) throws InterruptedException
    {
        string_id code_entry_title = new string_id(DECREPIT_STF, "code_entry_title");
        String title = getString(code_entry_title);
        string_id prompt_sid = new string_id(DECREPIT_STF, "code_entry_prompt");
        String prompt = getString(prompt_sid);
        obj_id top = getTopMostContainer(terminal);
        String code = "none";
        int resetTime = 0;
        if (utils.hasScriptVar(top, trial.ACCESS_CODE))
        {
            code = utils.getStringScriptVar(top, trial.ACCESS_CODE);
        }
        if (getGameTime() > trial.getResetTime(top))
        {
            trial.initializeCode(top);
            sendSystemMessage(player, SID_CODE_RESET);
            return;
        }
        int pid = sui.inputbox(terminal, player, prompt, title, "handleCodeEntry", 0, false, "");
        if (pid > -1)
        {
            utils.setScriptVar(player, "codeEntryPid", pid);
        }
    }
    public int handleCodeEntry(obj_id self, dictionary params) throws InterruptedException
    {
        utils.removeScriptVar(self, "codeEntryPid");
        int bp = sui.getIntButtonPressed(params);
        String text = sui.getInputBoxText(params);
        obj_id player = sui.getPlayerId(params);
        obj_id top = getTopMostContainer(self);
        if (bp == sui.BP_CANCEL || text == null || text.equals(""))
        {
            return SCRIPT_CONTINUE;
        }
        String code = utils.getStringScriptVar(top, trial.ACCESS_CODE);
        int resetTime = utils.getIntScriptVar(top, trial.RESET_CODE);
        if (getGameTime() > resetTime)
        {
            trial.initializeCode(top);
            utils.sendSystemMessagePob(trial.getTop(self), SID_CODE_RESET);
            return SCRIPT_CONTINUE;
        }
        if (text.equals(code))
        {
            trial.makeCellPublic(top, trial.DECREPIT_ONE_TWO_STAIR);
            utils.sendSystemMessagePob(trial.getTop(self), trial.SID_ACCESS_GRANTED);
        }
        else 
        {
            utils.sendSystemMessagePob(trial.getTop(self), SID_INVALID_CODE);
        }
        return SCRIPT_CONTINUE;
    }
    public int handleResetTimer(obj_id self, dictionary params) throws InterruptedException
    {
        dictionary dict = new dictionary();
        dict.put("timer", 10);
        messageTo(self, "showStatusFlyText", dict, 0, false);
        return SCRIPT_CONTINUE;
    }
    public int showStatusFlyText(obj_id self, dictionary params) throws InterruptedException
    {
        int timer = params.getInt("timer");
        if (timer == 0)
        {
            return SCRIPT_CONTINUE;
        }
        trial.doCountdownTimerFlyText(self, timer);
        return SCRIPT_CONTINUE;
    }
    public void doLogging(String section, String message) throws InterruptedException
    {
        if (LOGGING)
        {
            LOG("logging/code_terminal_master/" + section, message);
        }
    }
}
