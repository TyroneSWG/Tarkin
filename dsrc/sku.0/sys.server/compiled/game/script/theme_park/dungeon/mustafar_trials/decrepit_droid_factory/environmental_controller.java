package script.theme_park.dungeon.mustafar_trials.decrepit_droid_factory;

import script.library.trial;
import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class environmental_controller extends script.base_script
{
    public environmental_controller()
    {
    }
    public static final String DECREPIT_STF = "mustafar/decrepit_droid_factory";
    public static final string_id SID_DISABLE_HEAT_EFFECT = new string_id(DECREPIT_STF, "disable_heat_effect");
    public static final string_id SID_HEAT_OFF_LINE = new string_id(DECREPIT_STF, "heat_effect_disabled");
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
        boolean isUnlocked = trial.isCellPublic(self, trial.DECREPIT_FIRE_CELL_EXIT);
        if (!isUnlocked)
        {
            mi.addRootMenu(menu_info_types.ITEM_USE, SID_DISABLE_HEAT_EFFECT);
        }
        else 
        {
            mi.addRootMenu(menu_info_types.ITEM_USE, SID_HEAT_OFF_LINE);
        }
        sendDirtyObjectMenuNotification(self);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (!trial.isCellPublic(self, trial.DECREPIT_FIRE_CELL_EXIT))
            {
                trial.makeCellPublic(self, trial.DECREPIT_FIRE_CELL_EXIT);
                utils.sendSystemMessagePob(trial.getTop(self), trial.SID_ACCESS_GRANTED);
                trial.setFireCellState(self, false);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
