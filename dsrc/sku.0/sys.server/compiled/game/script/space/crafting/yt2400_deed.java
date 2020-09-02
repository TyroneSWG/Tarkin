package script.space.crafting;

import script.library.space_crafting;
import script.library.space_transition;
import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class yt2400_deed extends script.base_script
{
    public yt2400_deed()
    {
    }
    public static final string_id MNU_CREATE_VEHICLE = new string_id("sui", "create_vehicle");
    public static final String STF = "space/space_item";
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        int idx = utils.getValidAttributeIndex(names);
        if (idx == -1)
        {
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (!utils.isNestedWithin(self, player))
        {
            return SCRIPT_CONTINUE;
        }
        int mnuColor = mi.addRootMenu(menu_info_types.SERVER_MENU1, MNU_CREATE_VEHICLE);
        if (mnuColor > -1 && ((getContainedBy(self) != getOwner(self)) || isGod(player)))
        {
            String template = utils.getTemplateFilenameNoPath(self);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (!utils.isNestedWithin(self, player))
        {
            return SCRIPT_CONTINUE;
        }
        String template = utils.getTemplateFilenameNoPath(self);
        if (item == menu_info_types.SERVER_MENU1)
        {
            obj_id datapad = utils.getDatapad(player);
            if (isIdValid(datapad))
            {
                obj_id newShip = space_crafting.createChassisFromDeed(player, self, 5000, 5000, 5000000, "yt2400");
                return SCRIPT_CONTINUE;
            }
            space_transition.handlePotentialSceneChange(player);
        }
        return SCRIPT_CONTINUE;
    }
}
