package script.quest.force_sensitive;

import script.dictionary;
import script.library.ai_lib;
import script.library.chat;
import script.library.fs_quests;
import script.library.prose;
import script.obj_id;
import script.prose_package;
import script.string_id;

public class fs_oldman_initial extends script.base_script
{
    public fs_oldman_initial()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        createTriggerVolume("fs_oldman_messagerange", 12, true);
        messageTo(self, "approach", null, 3, false);
        return SCRIPT_CONTINUE;
    }
    public int approach(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id owner = null;
        owner = getObjIdObjVar(self, "old_man_initial.holder");
        setMovementRun(self);
        if ((isIdValid(owner)) && (exists(owner)))
        {
            ai_lib.aiFollow(self, owner);
        }
        messageTo(self, "approachFail", null, 180, false);
        return SCRIPT_CONTINUE;
    }
    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id target) throws InterruptedException
    {
        if (volumeName.equals("fs_oldman_messagerange"))
        {
            obj_id owner = getObjIdObjVar(self, "old_man_initial.holder");
            if (owner == target)
            {
                if (!hasObjVar(self, "contact"))
                {
                    setObjVar(self, "contact", 1);
                    messageTo(self, "timeout", null, 180, false);
                    prose_package pp = prose.getPackage(new string_id("quest/force_sensitive/intro", "oldman_greeting"), target);
                    chat.publicChat(self, target, null, null, pp);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int approachFail(obj_id self, dictionary params) throws InterruptedException
    {
        if (hasObjVar(self, "contact"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id owner = null;
        owner = getObjIdObjVar(self, "old_man_initial.holder");
        if ((isIdValid(owner)) && (exists(owner)))
        {
            fs_quests.oldManDepart(owner, self, 1);
        }
        return SCRIPT_CONTINUE;
    }
    public int timeout(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id owner = null;
        owner = getObjIdObjVar(self, "old_man_initial.holder");
        if ((isIdValid(owner)) && (exists(owner)))
        {
            sendSystemMessage(owner, new string_id("quest/force_sensitive/intro", "leave"));
            fs_quests.oldManDepart(owner, self, 1);
        }
        else 
        {
            messageTo(self, "cleanUp", null, 1, false);
        }
        return SCRIPT_CONTINUE;
    }
    public int cleanUp(obj_id self, dictionary params) throws InterruptedException
    {
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
}
