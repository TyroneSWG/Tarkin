package script.theme_park.dungeon.diant_zuy;

import script.*;
import script.library.chat;

public class destruct extends script.base_script
{
    public destruct()
    {
    }
    public static final string_id DESTRUCT_ENGAGED = new string_id("dungeon/diant_bunker", "destruct_engaged");
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        obj_id top = getTopMostContainer(self);
        int isExploding = getIntObjVar(top, "diant.isExploding");
        if (isExploding == 0)
        {
            int mnu = mi.addRootMenu(menu_info_types.SELF_DESTRUCT, new string_id("self_destruct", "self_destruct"));
            mi.addSubMenu(mnu, menu_info_types.THIRTY_SEC, new string_id("self_destruct", "thirty"));
            mi.addSubMenu(mnu, menu_info_types.FIFTEEN_SEC, new string_id("self_destruct", "fifteen"));
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        obj_id bldg = getTopMostContainer(self);
        int isExploding = getIntObjVar(bldg, "diant.isExploding");
        if (item == menu_info_types.THIRTY_SEC)
        {
            if (isExploding == 0)
            {
                playMusic(player, "sound/music_darth_vader_theme.snd");
                chat.chat(self, new string_id("self_destruct", "thirty_seconds"));
                setObjVar(bldg, "diant.isExploding", 1);
                dictionary destroyer = new dictionary();
                destroyer.put("player", player);
                destroyer.put("bldg", bldg);
                sendSystemMessage(player, DESTRUCT_ENGAGED);
                messageTo(self, "blowUp", destroyer, 30, true);
            }
            return SCRIPT_CONTINUE;
        }
        else if (item == menu_info_types.FIFTEEN_SEC)
        {
            if (isExploding == 0)
            {
                chat.chat(self, new string_id("self_destruct", "fifteen_seconds"));
                setObjVar(bldg, "diant.isExploding", 1);
                dictionary destroyer = new dictionary();
                destroyer.put("player", player);
                destroyer.put("bldg", bldg);
                sendSystemMessage(player, DESTRUCT_ENGAGED);
                messageTo(self, "blowUp", destroyer, 15, true);
            }
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public int blowUp(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id top = getTopMostContainer(self);
        messageTo(self, "blowUp", null, 30, true);
        messageTo(top, "selfDestruct", null, 0, false);
        return SCRIPT_CONTINUE;
    }
}
