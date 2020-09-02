package script.working;

import script.*;
import script.library.hue;
import script.library.sui;
import script.library.utils;

public class color_index_testing extends script.base_script
{
    public color_index_testing()
    {
    }
    public static final string_id PCOLOR = new string_id("sui", "set_primary_color");
    public static final string_id SCOLOR = new string_id("sui", "set_secondary_color");
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (!isGod(player))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id hueObject = self;
        if (!utils.isNestedWithin(hueObject, player))
        {
            return SCRIPT_CONTINUE;
        }
        int mnuPrimaryColor = mi.addRootMenu(menu_info_types.SERVER_MENU9, PCOLOR);
        int mnuSecondaryColor = mi.addRootMenu(menu_info_types.SERVER_MENU10, SCOLOR);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (!isGod(player))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id hueObject = self;
        if (!utils.isNestedWithin(hueObject, player))
        {
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.SERVER_MENU9)
        {
            sui.colorize(hueObject, player, hueObject, hue.INDEX_1, "handlePrimaryColorize");
        }
        if (item == menu_info_types.SERVER_MENU10)
        {
            sui.colorize(hueObject, player, hueObject, hue.INDEX_2, "handleSecondaryColorize");
        }
        return SCRIPT_CONTINUE;
    }
    public int handlePrimaryColorize(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id hueObject = self;
        int idx = sui.getColorPickerIndex(params);
        int bp = sui.getIntButtonPressed(params);
        obj_id player = sui.getPlayerId(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        if (idx > -1)
        {
            custom_var myVar = getCustomVarByName(hueObject, hue.INDEX_1);
            if (myVar != null && myVar.isPalColor())
            {
                palcolor_custom_var pcVar = (palcolor_custom_var)myVar;
                pcVar.setValue(idx);
                debugConsoleMsg(player, "index_color_1: " + idx);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int handleSecondaryColorize(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id hueObject = self;
        int idx = sui.getColorPickerIndex(params);
        int bp = sui.getIntButtonPressed(params);
        obj_id player = sui.getPlayerId(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        if (idx > -1)
        {
            custom_var myVar = getCustomVarByName(hueObject, hue.INDEX_2);
            if (myVar != null && myVar.isPalColor())
            {
                palcolor_custom_var pcVar = (palcolor_custom_var)myVar;
                pcVar.setValue(idx);
                debugConsoleMsg(player, "index_color_2: " + idx);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
