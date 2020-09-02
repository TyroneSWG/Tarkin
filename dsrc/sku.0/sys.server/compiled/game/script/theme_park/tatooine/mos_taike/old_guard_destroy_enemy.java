package script.theme_park.tatooine.mos_taike;

import script.obj_id;

public class old_guard_destroy_enemy extends script.base_script
{
    public old_guard_destroy_enemy()
    {
    }
    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        String datatable = "datatables/theme_park/mos_taike.iff";
        obj_id player = getObjIdObjVar(self, "player");
        messageTo(player, "finishQuest", null, 0, true);
        return SCRIPT_CONTINUE;
    }
}
