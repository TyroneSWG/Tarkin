package script.object;

import script.*;
import script.library.static_item;
import script.library.sui;
import script.library.utils;

public class ch5_powerup_unstack extends script.base_script
{
    public ch5_powerup_unstack()
    {
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (getCount(self) > 1)
        {
            mi.addRootMenu(menu_info_types.SERVER_MENU1, new string_id("autostack", "unstack"));
            menu_info_data mid = mi.getMenuItemByType(menu_info_types.SERVER_MENU1);
            if (mid == null)
            {
                return SCRIPT_CONTINUE;
            }
            else 
            {
                mid.setServerNotify(true);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (getCount(self) > 1)
        {
            if (item == menu_info_types.SERVER_MENU1)
            {
                unstackIt(self, player);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        int free = getFirstFreeIndex(names);
        if (free == -1)
        {
            return SCRIPT_CONTINUE;
        }
        int numInStack = getCount(self);
        names[free] = "num_in_stack";
        attribs[free] = "" + numInStack;
        return SCRIPT_CONTINUE;
    }
    public void unstackIt(obj_id self, obj_id player) throws InterruptedException
    {
        if (!isInSecureTrade(self))
        {
            if (getCount(self) == 2)
            {
                createNewStack(player, self, 1, 2);
            }
            else 
            {
                sui.inputbox(self, player, "@autostack:stacksize", "msgUnstackItem");
            }
        }
    }
    public int msgUnstackItem(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = sui.getPlayerId(params);
        String amount_str = sui.getInputBoxText(params);
        if (amount_str == null || amount_str.equals(""))
        {
            return SCRIPT_CONTINUE;
        }
        int oldStackSize = getCount(self);
        int newStackSize = utils.stringToInt(amount_str);
        if (newStackSize < 1)
        {
            sendSystemMessage(player, new string_id("autostack", "zero_size"));
            return SCRIPT_CONTINUE;
        }
        else if (newStackSize > oldStackSize)
        {
            sendSystemMessage(player, new string_id("autostack", "too_big"));
            return SCRIPT_CONTINUE;
        }
        else if (newStackSize == oldStackSize)
        {
            return SCRIPT_CONTINUE;
        }
        createNewStack(player, self, newStackSize, oldStackSize);
        return SCRIPT_CONTINUE;
    }
    public void createNewStack(obj_id player, obj_id item, int newStackSize, int oldStackSize) throws InterruptedException
    {
        obj_id container = getContainedBy(item);
        if (!isIdValid(container))
        {
            sendSystemMessage(player, new string_id("autostack", "bad_container"));
            return;
        }
        if (getVolumeFree(container) <= 0)
        {
            sendSystemMessage(player, new string_id("autostack", "full_container"));
            return;
        }
        utils.setScriptVar(player, "autostack.ignoreitems", true);
        obj_id newItem = null;
        if (static_item.isStaticItem(item))
        {
            newItem = static_item.createNewItemFunction(getStaticItemName(item), container);
        }
        else 
        {
            newItem = createObject(item, container, "");
        }
        if (!isIdValid(newItem))
        {
            sendSystemMessage(player, new string_id("autostack", "unknown_failure"));
            utils.removeScriptVar(player, "autostack.ignoreitems");
            return;
        }
        if (hasScript(item, "item.tool.reverse_engineering_powerup"))
        {
            int power = getIntObjVar(item, "reverse_engineering.reverse_engineering_power");
            String mod = getStringObjVar(item, "reverse_engineering.reverse_engineering_modifier");
            int ratio = getIntObjVar(item, "reverse_engineering.reverse_engineering_ratio");
            setObjVar(newItem, "reverse_engineering.reverse_engineering_power", power);
            setObjVar(newItem, "reverse_engineering.reverse_engineering_modifier", mod);
            setObjVar(newItem, "reverse_engineering.reverse_engineering_ratio", ratio);
            attachScript(newItem, "item.tool.reverse_engineering_powerup");
        }
        setCount(item, (oldStackSize - newStackSize));
        setCount(newItem, newStackSize);
        utils.removeScriptVar(player, "autostack.ignoreitems");
    }
}
