package script.npc.celebrity;

import script.library.ai_lib;
import script.library.create;
import script.obj_id;

public class corpse_spawner extends script.base_script
{
    public corpse_spawner()
    {
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        String spawn = getStringObjVar(self, "spawns");
        obj_id corpse = create.object(spawn, getLocation(self), false);
        setCreatureStatic(corpse, true);
        setInvulnerable(corpse, true);
        ai_lib.setDefaultCalmBehavior(corpse, ai_lib.BEHAVIOR_SENTINEL);
        attachScript(corpse, "npc.static_npc.npc_dead");
        if (hasObjVar(self, "npc_name"))
        {
            String name = getStringObjVar(self, "npc_name");
            if (name != null)
            {
                if (!name.equals(""))
                {
                    setName(corpse, name);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
