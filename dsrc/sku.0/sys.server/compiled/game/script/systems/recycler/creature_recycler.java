package script.systems.recycler;

import script.*;
import script.library.utils;

public class creature_recycler extends script.base_script
{
    public creature_recycler()
    {
    }
    public static final String STF = "recycler_messages";
    public int OnAboutToReceiveItem(obj_id self, obj_id srcContainer, obj_id player, obj_id item) throws InterruptedException
    {
        if (!utils.isNestedWithin(self, player))
        {
            return SCRIPT_OVERRIDE;
        }
        if (isResourceContainer(item))
        {
            obj_id container = getResourceContainerResourceType(item);
            obj_id recycle = getRecycledVersionOfResourceType(container);
            if (recycle == container)
            {
                string_id alreadyRecycled = new string_id(STF, "already_recycled");
                sendSystemMessage(player, alreadyRecycled);
                return SCRIPT_OVERRIDE;
            }
            String name = getResourceClass(container);
            String typeName = getResourceName(container);
            String currentSet = getStringObjVar(self, "current_set");
            if (currentSet == null || currentSet.equals(""))
            {
                currentSet = "milk";
                setObjVar(self, "current_set", currentSet);
            }
            if (isResourceClassDerivedFrom(name, currentSet))
            {
                int stringCheck = name.indexOf("seafood");
                if ((stringCheck > -1) && (currentSet.equals("meat")))
                {
                    string_id hide = new string_id(STF, "only_" + currentSet);
                    sendSystemMessage(player, hide);
                    return SCRIPT_OVERRIDE;
                }
                obj_id type = getResourceTypeByName(typeName);
                int amount = getResourceContainerQuantity(item);
                obj_id inv = utils.getInventoryContainer(player);
                obj_id generic = createResourceCrate(recycle, amount, inv);
                if (generic == null)
                {
                    int emptySpace = getVolumeFree(inv);
                    if (emptySpace < 1)
                    {
                        string_id noRoom = new string_id(STF, "no_room_in_inventory");
                        sendSystemMessage(player, noRoom);
                        return SCRIPT_OVERRIDE;
                    }
                    string_id noRecycleType = new string_id(STF, "no_type");
                    sendSystemMessage(player, noRecycleType);
                    return SCRIPT_OVERRIDE;
                }
                else 
                {
                    string_id success = new string_id(STF, "success");
                    sendSystemMessage(player, success);
                    dictionary webster = new dictionary();
                    webster.put("item", item);
                    messageTo(self, "destroyResource", webster, 1.0f, false);
                }
                return SCRIPT_CONTINUE;
            }
            else 
            {
                string_id hide = new string_id(STF, "only_" + currentSet);
                sendSystemMessage(player, hide);
                return SCRIPT_OVERRIDE;
            }
        }
        else 
        {
            string_id noResource = new string_id(STF, "no_resource");
            sendSystemMessage(player, noResource);
            return SCRIPT_OVERRIDE;
        }
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        string_id choose = new string_id(STF, "choose_type");
        string_id bordens = new string_id(STF, "milk");
        string_id steak = new string_id(STF, "meat");
        string_id skull = new string_id(STF, "bone");
        string_id trumpet = new string_id(STF, "bone_horn");
        string_id skins = new string_id(STF, "hide");
        string_id lobster = new string_id(STF, "seafood");
        int mnuOptions = -1;
        menu_info_data mid = mi.getMenuItemByType(menu_info_types.SERVER_MENU1);
        if (mid == null)
        {
            mnuOptions = mi.addRootMenu(menu_info_types.SERVER_MENU1, choose);
        }
        else 
        {
            mnuOptions = mid.getId();
        }
        if (mnuOptions > 0)
        {
            int subClass = mi.addSubMenu(mnuOptions, menu_info_types.SERVER_MENU7, skins);
            int subClass2 = mi.addSubMenu(mnuOptions, menu_info_types.SERVER_MENU2, steak);
            int subClass3 = mi.addSubMenu(mnuOptions, menu_info_types.SERVER_MENU3, skull);
            int subClass4 = mi.addSubMenu(mnuOptions, menu_info_types.SERVER_MENU4, trumpet);
            int subClass5 = mi.addSubMenu(mnuOptions, menu_info_types.SERVER_MENU5, lobster);
            int subClass6 = mi.addSubMenu(mnuOptions, menu_info_types.SERVER_MENU6, bordens);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.SERVER_MENU7)
        {
            setObjVar(self, "current_set", "hide");
        }
        if (item == menu_info_types.SERVER_MENU2)
        {
            setObjVar(self, "current_set", "meat");
        }
        if (item == menu_info_types.SERVER_MENU3)
        {
            setObjVar(self, "current_set", "bone");
        }
        if (item == menu_info_types.SERVER_MENU4)
        {
            setObjVar(self, "current_set", "bone_horn");
        }
        if (item == menu_info_types.SERVER_MENU5)
        {
            setObjVar(self, "current_set", "seafood");
        }
        if (item == menu_info_types.SERVER_MENU6)
        {
            setObjVar(self, "current_set", "milk");
        }
        return SCRIPT_CONTINUE;
    }
    public int destroyResource(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id item = params.getObjId("item");
        destroyObject(item);
        return SCRIPT_CONTINUE;
    }
}
