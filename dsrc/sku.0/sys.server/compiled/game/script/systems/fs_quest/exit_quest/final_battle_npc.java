package script.systems.fs_quest.exit_quest;

import script.library.utils;
import script.location;
import script.obj_id;

import java.util.Vector;

public class final_battle_npc extends script.base_script
{
    public final_battle_npc()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id theater_object = getObjIdObjVar(self, "quest_spawner.theater");
        if (isIdValid(theater_object))
        {
            obj_id player = getObjIdObjVar(theater_object, "fs_quest.final_battle.player");
            setObjVar(self, "questOwner", player);
            setHomeLocation(self);
            final location anchorLocation = getLocation(theater_object);
            final float minDistance = 0.0f;
            final float maxDistance = 20.0f;
            final float minDelay = 3.0f;
            final float maxDelay = 8.0f;
            loiterLocation(self, anchorLocation, minDistance, maxDistance, minDelay, maxDelay);
            Vector existingNPCs = new Vector();
            if (hasObjVar(theater_object, "fs_quest.final_battle_npcs"))
            {
                existingNPCs = utils.getResizeableObjIdBatchObjVar(theater_object, "fs_quest.final_battle_npcs");
            }
            existingNPCs.add(self);
            utils.setResizeableBatchObjVar(theater_object, "fs_quest.final_battle_npcs", existingNPCs);
        }
        return SCRIPT_CONTINUE;
    }
}
