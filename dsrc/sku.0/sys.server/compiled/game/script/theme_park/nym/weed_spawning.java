package script.theme_park.nym;

import script.dictionary;
import script.library.create;
import script.obj_id;

public class weed_spawning extends script.base_script
{
    public weed_spawning()
    {
    }
    public static final String[] WEEDS = 
    {
        "object/tangible/furniture/nym_themepark/nym_themepark_plant_01.iff",
        "object/tangible/furniture/nym_themepark/nym_themepark_plant_02.iff",
        "object/tangible/furniture/nym_themepark/nym_themepark_plant_03.iff",
        "object/tangible/furniture/nym_themepark/nym_themepark_plant_04.iff",
        "object/tangible/furniture/nym_themepark/nym_themepark_plant_05.iff",
        "object/tangible/furniture/nym_themepark/nym_themepark_plant_06.iff",
        "object/tangible/furniture/nym_themepark/nym_themepark_plant_07.iff",
        "object/tangible/furniture/nym_themepark/nym_themepark_plant_08.iff",
        "object/tangible/furniture/nym_themepark/nym_themepark_plant_09.iff",
        "object/tangible/furniture/nym_themepark/nym_themepark_plant_10.iff",
        "object/tangible/furniture/nym_themepark/nym_themepark_plant_11.iff"
    };
    public int OnAttach(obj_id self) throws InterruptedException
    {
        CustomerServiceLog("nyms_themepark", "weed_spawning.OnAttach() Initializing spawning functionality for spawner: " + self);
        messageTo(self, "spawnObject", null, 1, false);
        return SCRIPT_CONTINUE;
    }
    public int spawnObject(obj_id self, dictionary params) throws InterruptedException
    {
        if (hasObjVar(self, "child"))
        {
            return SCRIPT_CONTINUE;
        }
        CustomerServiceLog("nyms_themepark", "weed_spawning.spawnObject() Spawning functionality is initialized for spawner: " + self);
        obj_id parentObject = getObjIdObjVar(self, "objParent");
        if (!isValidId(parentObject))
        {
            CustomerServiceLog("nyms_themepark", "weed_spawning.spawnObject() Master Object does not exist");
            return SCRIPT_CONTINUE;
        }
        if (!hasObjVar(self, "strQuest") || !hasObjVar(self, "strSignal"))
        {
            CustomerServiceLog("nyms_themepark", "weed_spawning.spawnObject() Spawner Object does not have quest data");
            return SCRIPT_CONTINUE;
        }
        String questName = getStringObjVar(self, "strQuest");
        if (questName == null || questName.length() <= 0)
        {
            CustomerServiceLog("nyms_themepark", "weed_spawning.spawnObject() Spawner Object hase invalid quest string");
            return SCRIPT_CONTINUE;
        }
        String signalName = getStringObjVar(self, "strSignal");
        if (signalName == null || signalName.length() <= 0)
        {
            CustomerServiceLog("nyms_themepark", "weed_spawning.spawnObject() Spawner Object hase invalid quest signal string");
            return SCRIPT_CONTINUE;
        }
        int randomSpawnObject = rand(0, 10);
        if (randomSpawnObject < 0)
        {
            randomSpawnObject = 0;
        }
        obj_id object = obj_id.NULL_ID;
        switch (randomSpawnObject)
        {
            case 0:
            object = create.object(WEEDS[randomSpawnObject], getLocation(self));
            break;
            case 1:
            object = create.object(WEEDS[randomSpawnObject], getLocation(self));
            break;
            case 2:
            object = create.object(WEEDS[randomSpawnObject], getLocation(self));
            break;
            case 3:
            object = create.object(WEEDS[randomSpawnObject], getLocation(self));
            break;
            case 4:
            object = create.object(WEEDS[randomSpawnObject], getLocation(self));
            break;
            case 5:
            object = create.object(WEEDS[randomSpawnObject], getLocation(self));
            break;
            case 6:
            object = create.object(WEEDS[randomSpawnObject], getLocation(self));
            break;
            case 7:
            object = create.object(WEEDS[randomSpawnObject], getLocation(self));
            break;
            case 8:
            object = create.object(WEEDS[randomSpawnObject], getLocation(self));
            break;
            case 9:
            object = create.object(WEEDS[randomSpawnObject], getLocation(self));
            break;
            case 10:
            object = create.object(WEEDS[randomSpawnObject], getLocation(self));
            break;
            default:
            object = create.object(WEEDS[0], getLocation(self));
            break;
        }
        if (!isValidId(object) || !exists(object))
        {
            CustomerServiceLog("nyms_themepark", "weed_spawning.spawnObject() object: " + object + " could not be found. Critical failure. Aborting.");
            return SCRIPT_CONTINUE;
        }
        CustomerServiceLog("nyms_themepark", "weed_spawning.spawnObject() Mob: " + object + " WAS SUCCESSFULLY CREATED for spawner: " + self);
        setObjVar(object, "mySpawner", self);
        setObjVar(object, "parentNode", parentObject);
        setObjVar(object, "objParent", parentObject);
        setObjVar(object, "questName", questName);
        setObjVar(object, "signalName", signalName);
        setObjVar(self, "child", object);
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
