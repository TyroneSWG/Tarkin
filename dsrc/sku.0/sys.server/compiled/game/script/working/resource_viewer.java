package script.working;

import script.*;
import script.library.sui;
/**
 *
 * @author Roachie
 */
public class resource_viewer extends script.base_script {
    public resource_viewer() {
    }
    public int OnAttach(obj_id self) throws InterruptedException 
    {
        setObjVar(self, "noMove", 1);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        menu_info_data data = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("ui_terminal", "use"));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (!isIdValid(self))
            {
                return SCRIPT_CONTINUE;
            }
            String [] RV_LIST = {};
            String RV_PROMPT = "Currently Spawned Resources: " + getCurrentSceneName() + "";
            String RV_TITLE = "Galactic Crafting Association";
            sui.listbox(player, player, RV_PROMPT, RV_LIST, RV_TITLE);
            
        }
        return SCRIPT_CONTINUE;
    }
    public obj_id[] getAvailablePlanetResources(String planet, String resourceClass) throws InterruptedException
    {
        resource_density[] resources = requestResourceList(new location(0, 0, 0, toLower(planet)), 0.0f, 1.0f, resourceClass);
        obj_id[] resourceList = new obj_id[resources.length];
        for (int i = 0; i < resourceList.length; i++)
        {
            resourceList[i] = resources[i].getResourceType();
        }
        return resourceList;
    }
}
