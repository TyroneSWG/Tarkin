package script.systems.fs_quest.patrol;

import script.dictionary;
import script.obj_id;

public class fs_killable_npc extends script.base_script
{
    public fs_killable_npc()
    {
    }
    public static final float DESTROY_TIME = 900.0f;
    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        if (hasObjVar(self, "quest.fs_patrol.spawned_for"))
        {
            obj_id player = getObjIdObjVar(self, "quest.fs_patrol.spawned_for");
            dictionary params = new dictionary();
            messageTo(player, "handleKillPatrolFSNpc", params, 0.0f, false);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        dictionary params = new dictionary();
        messageTo(self, "autoCleanup", params, DESTROY_TIME, false);
        return SCRIPT_CONTINUE;
    }
    public int autoCleanup(obj_id self, dictionary params) throws InterruptedException
    {
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
}
