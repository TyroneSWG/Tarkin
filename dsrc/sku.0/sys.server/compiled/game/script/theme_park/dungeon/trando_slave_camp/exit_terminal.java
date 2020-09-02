package script.theme_park.dungeon.trando_slave_camp;

import script.library.sui;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class exit_terminal extends script.base_script
{
    public exit_terminal()
    {
    }
    public static final String STF = "dungeon/space_dungeon";
    public static final string_id SID_EJECT = new string_id(STF, "trando_camp_exit");
    public static final string_id SID_EJECT_CONFIRM = new string_id(STF, "trando_camp_exit_confirm");
    public int OnAttach(obj_id self) throws InterruptedException
    {
        if (hasScript(self, "item.microphone_and_speaker.speaker"))
        {
            detachScript(self, "item.microphone_and_speaker.speaker");
        }
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if (hasScript(self, "item.microphone_and_speaker.speaker"))
        {
            detachScript(self, "item.microphone_and_speaker.speaker");
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, SID_EJECT);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        LOG("space_dungeon", "theme_park.dungeon.avatar_platform.terminal_launch.OnObjectMenuSelect()");
        if (item == menu_info_types.ITEM_USE)
        {
            confirmEject(player, self);
        }
        return SCRIPT_CONTINUE;
    }
    public void confirmEject(obj_id player, obj_id terminal) throws InterruptedException
    {
        LOG("space_dungeon", "theme_park.dungeon.avatar_platform.terminal_launch.confirmEject()");
        String confirm = "@" + SID_EJECT_CONFIRM;
        int pid = sui.msgbox(player, player, confirm, sui.YES_NO, "msgDungeonEjectConfirmed");
    }
}
