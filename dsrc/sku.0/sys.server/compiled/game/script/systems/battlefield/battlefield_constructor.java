package script.systems.battlefield;

import script.library.battlefield;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class battlefield_constructor extends script.base_script
{
    public battlefield_constructor()
    {
    }
    public static final string_id SID_BUILD_STRUCTURE = new string_id("battlefield", "build_structure");
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (battlefield.isSameBattlefieldFaction(player, self))
        {
            int battlefield_root = mi.addRootMenu(menu_info_types.SERVER_TRAVEL_OPTIONS, SID_BUILD_STRUCTURE);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.SERVER_TRAVEL_OPTIONS)
        {
            LOG("LOG_CHANNEL", "battlefield_constructor::OnBuildStructure");
            queueCommand(player, (1271146555), null, "", COMMAND_PRIORITY_DEFAULT);
        }
        return SCRIPT_CONTINUE;
    }
}
