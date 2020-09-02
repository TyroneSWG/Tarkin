package script.theme_park.outbreak;

import script.dictionary;
import script.library.*;
import script.location;
import script.obj_id;

public class camp_survivor_spawning extends script.base_script
{
    public camp_survivor_spawning()
    {
    }
    public static final int RADIUS = 1000;
    public int OnAttach(obj_id self) throws InterruptedException
    {
        CustomerServiceLog("outbreak_themepark", "camp_survivor_spawning.OnAttach() Initializing spawning functionality for spawner: " + self);
        if (!hasScript(self, holiday.EVENT_TRACKER_SCRIPT))
        {
            attachScript(self, holiday.EVENT_TRACKER_SCRIPT);
        }
        int randomTime = rand(3, 25);
        if (randomTime < 0 || randomTime > 25)
        {
            randomTime = 3;
        }
        CustomerServiceLog("outbreak_themepark", "camp_survivor_spawning.OnAttach() Spawning functionality beginning in: " + randomTime + " frames for spawner: " + self);
        messageTo(self, "spawnSurvivor", null, randomTime, false);
        messageTo(self, "destroySelf", null, 600, false);
        return SCRIPT_CONTINUE;
    }
    public int spawnSurvivor(obj_id self, dictionary params) throws InterruptedException
    {
        if (hasObjVar(self, "child"))
        {
            return SCRIPT_CONTINUE;
        }
        CustomerServiceLog("outbreak_themepark", "camp_survivor_spawning.spawnSurvivor() Spawning functionality is initialized for spawner: " + self);
        obj_id parentObject = getObjIdObjVar(self, "objParent");
        if (!isValidId(parentObject))
        {
            CustomerServiceLog("outbreak_themepark", "camp_survivor_spawning.spawnSurvivor() Master Object does not exist");
            return SCRIPT_CONTINUE;
        }
        String questName = getStringObjVar(self, "strQuest");
        if (questName == null || questName.length() <= 0)
        {
            CustomerServiceLog("outbreak_themepark", "camp_survivor_spawning.spawnSurvivor() Object: " + self + " does not have a valid questName.");
            return SCRIPT_CONTINUE;
        }
        location locationObjParent = getLocationObjVar(self, "locationObjParent");
        if (locationObjParent == null)
        {
            CustomerServiceLog("outbreak_themepark", "camp_survivor_spawning.spawnSurvivor() Object: " + self + " does not have a valid parent object location.");
            return SCRIPT_CONTINUE;
        }
        String failMessage = getStringObjVar(self, "strFailSignal");
        if (failMessage == null || failMessage.length() <= 0)
        {
            CustomerServiceLog("outbreak_themepark", "camp_survivor_spawning.spawnSurvivor() Object: " + self + " does not have a valid failMessage.");
            return SCRIPT_CONTINUE;
        }
        String creatureType = getStringObjVar(self, "mob");
        if (creatureType == null || creatureType.length() <= 0)
        {
            CustomerServiceLog("outbreak_themepark", "camp_survivor_spawning.spawnSurvivor() Spawning failed because mob var was null for spawner: " + self);
            return SCRIPT_CONTINUE;
        }
        CustomerServiceLog("outbreak_themepark", "camp_survivor_spawning.spawnSurvivor() Preparing to spawn mob: " + creatureType + " at: " + getLocation(self) + " for spawner: " + self);
        obj_id mob = create.object(creatureType, getLocation(self));
        if (!isValidId(mob) || !exists(mob))
        {
            CustomerServiceLog("outbreak_themepark", "camp_survivor_spawning.spawnSurvivor() Mob: " + mob + " could not be created!");
            return SCRIPT_CONTINUE;
        }
        CustomerServiceLog("outbreak_themepark", "camp_survivor_spawning.spawnSurvivor() Mob: " + mob + " WAS SUCCESSFULLY CREATED for spawner: " + self);
        setObjVar(mob, "mySpawner", self);
        setObjVar(mob, "parentNode", parentObject);
        setObjVar(mob, "objParent", parentObject);
        setObjVar(mob, "questName", questName);
        setObjVar(mob, "failMessage", failMessage);
        setObjVar(mob, "locationObjParent", locationObjParent);
        setObjVar(self, "child", mob);
        utils.setScriptVar(mob, ai_lib.SCRIPTVAR_CACHED_PATROL_TYPE, 0);
        setAttributeAttained(mob, attrib.OUTBREAK_SURVIVOR);
        pathTo(mob, locationObjParent);
        CustomerServiceLog("outbreak_themepark", "camp_survivor_spawning.spawnSurvivor() the mob is now set to run path.");
        return SCRIPT_CONTINUE;
    }
    public int destroySelf(obj_id self, dictionary params) throws InterruptedException
    {
        if (hasObjVar(self, "child"))
        {
            obj_id child = getObjIdObjVar(self, "child");
            if (isValidId(child) && exists(child))
            {
                destroyObject(child);
            }
        }
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
}
