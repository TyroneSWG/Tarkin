package script.theme_park.dungeon.clone_relic;

import script.dictionary;
import script.obj_id;

public class spawned_creature_died extends script.base_script
{
    public spawned_creature_died()
    {
    }
    public int OnDestroy(obj_id self) throws InterruptedException
    {
        if (hasObjVar(self, "respawn_called"))
        {
            return SCRIPT_CONTINUE;
        }
        else 
        {
            setObjVar(self, "respawn_called", 1);
        }
        obj_id top = getTopMostContainer(self);
        String datatable = getStringObjVar(top, "spawn_table");
        obj_id mom = getObjIdObjVar(self, "mom");
        if (mom == null)
        {
            return SCRIPT_OVERRIDE;
        }
        int spawnNum = getIntObjVar(self, "spawn_number");
        int respawnTime = dataTableGetInt(datatable, spawnNum, "respawn_time");
        if (respawnTime == 0)
        {
            respawnTime = 30;
        }
        dictionary info = new dictionary();
        info.put("spawnNumber", spawnNum);
        info.put("spawnMob", self);
        messageTo(mom, "tellingMomIDied", info, respawnTime, false);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectDisabled(obj_id self, obj_id killer) throws InterruptedException
    {
        if (hasObjVar(self, "respawn_called"))
        {
            return SCRIPT_CONTINUE;
        }
        else 
        {
            setObjVar(self, "respawn_called", 1);
        }
        obj_id mom = getObjIdObjVar(self, "mom");
        if (mom == null)
        {
            return SCRIPT_OVERRIDE;
        }
        int spawnNum = getIntObjVar(self, "spawn_number");
        if (spawnNum == 0)
        {
            return SCRIPT_OVERRIDE;
        }
        dictionary info = new dictionary();
        info.put("spawnNumber", spawnNum);
        info.put("spawnMob", self);
        messageTo(mom, "tellingMomIDied", info, 180, false);
        return SCRIPT_CONTINUE;
    }
}
