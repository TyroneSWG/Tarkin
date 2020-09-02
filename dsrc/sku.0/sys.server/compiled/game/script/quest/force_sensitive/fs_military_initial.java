package script.quest.force_sensitive;

import script.dictionary;
import script.library.ai_lib;
import script.library.chat;
import script.library.prose;
import script.library.utils;
import script.obj_id;
import script.prose_package;
import script.string_id;

public class fs_military_initial extends script.base_script
{
    public fs_military_initial()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        createTriggerVolume("fs_two_military_range", 26, true);
        messageTo(self, "approach", null, 3, false);
        return SCRIPT_CONTINUE;
    }
    public int approach(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id owner = null;
        owner = getObjIdObjVar(self, "quest.owner");
        setMovementRun(self);
        if ((isIdValid(owner)) && (exists(owner)))
        {
            ai_lib.aiFollow(self, owner);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id target) throws InterruptedException
    {
        if (volumeName.equals("fs_two_military_range"))
        {
            obj_id owner = getObjIdObjVar(self, "quest.owner");
            if ((isIdValid(owner)) && (exists(owner)))
            {
                if (owner == target)
                {
                    if (!hasObjVar(self, "contact"))
                    {
                        setObjVar(self, "contact", 1);
                        prose_package pp = prose.getPackage(new string_id("quest/force_sensitive/intro", "military_threaten"), target);
                        chat.publicChat(self, target, null, null, pp);
                    }
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int aiCorpsePrepared(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = utils.getObjIdObjVar(self, "quest.owner", obj_id.NULL_ID);
        if (isIdValid(player))
        {
            dictionary d = new dictionary();
            d.put("corpse", self);
            d.put("questName", "two_military");
            messageTo(player, "addQuestLootToCorpse", d, 0, false);
        }
        return SCRIPT_CONTINUE;
    }
}
