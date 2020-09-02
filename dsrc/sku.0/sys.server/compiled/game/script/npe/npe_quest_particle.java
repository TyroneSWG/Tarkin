package script.npe;

import script.obj_id;

public class npe_quest_particle extends script.base_script
{
    public npe_quest_particle()
    {
    }
    public int OnGiveItem(obj_id self, obj_id item, obj_id player) throws InterruptedException
    {
        String itemTemplate = getTemplateName(item);
        if (hasObjVar(self, "task_name"))
        {
            String taskName = getStringObjVar(self, "task_name");
            if (taskName.equals("legacy_head_runaway3_e1"))
            {
                if (itemTemplate.equals("object/tangible/quest/legacy_head_pain3.iff"))
                {
                    playClientEffectObj(player, "clienteffect/npe_droid_static.cef", self, "");
                }
            }
            if (taskName.equals("legacy_head_runaway2_e1"))
            {
                if (itemTemplate.equals("object/tangible/quest/legacy_head_pain2.iff"))
                {
                    playClientEffectObj(player, "clienteffect/npe_droid_static.cef", self, "");
                }
            }
            if (taskName.equals("legacy_head_runaway1_e1"))
            {
                if (itemTemplate.equals("object/tangible/quest/legacy_head_pain1.iff"))
                {
                    playClientEffectObj(player, "clienteffect/npe_droid_static.cef", self, "");
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
