package script.theme_park.nym;

import script.dictionary;
import script.library.create;
import script.obj_id;

public class dumpster_spawner extends script.base_script
{
    public dumpster_spawner()
    {
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        CustomerServiceLog("nyms_themepark", "dumpster_spawner.OnAttach() Initializing spawning functionality for spawner: " + self);
        messageTo(self, "spawnObject", null, 1, false);
        return SCRIPT_CONTINUE;
    }
    public int spawnObject(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id object = create.object("object/tangible/furniture/nym_themepark/city_dumpster.iff", getLocation(self));
        if (!isValidId(object) || !exists(object))
        {
            CustomerServiceLog("nyms_themepark", "dumpster_spawner.spawnObject() object: " + object + " could not be found. Critical failure. Aborting.");
            return SCRIPT_CONTINUE;
        }
        attachScript(object, "theme_park.nym.dumpster_quest_object");
        setYaw(object, -180);
        CustomerServiceLog("nyms_themepark", "dumpster_spawner.spawnObject() Mob: " + object + " WAS SUCCESSFULLY CREATED for spawner: " + self);
        return SCRIPT_CONTINUE;
    }
}
