package script.theme_park.dungeon.mustafar_trials.old_republic_facility;

import script.dictionary;
import script.obj_id;

public class orf_initialize extends script.base_script
{
    public orf_initialize()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id entrance = getCellId(self, "entrance");
        if (!hasScript(entrance, "theme_park.dungeon.mustafar_trials.old_republic_facility.foyer"))
        {
            attachScript(entrance, "theme_park.dungeon.mustafar_trials.old_republic_facility.foyer");
        }
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        obj_id entrance = getCellId(self, "entrance");
        if (!hasScript(entrance, "theme_park.dungeon.mustafar_trials.old_republic_facility.foyer"))
        {
            attachScript(entrance, "theme_park.dungeon.mustafar_trials.old_republic_facility.foyer");
        }
        return SCRIPT_CONTINUE;
    }
    public void setOrfDoorLocks(obj_id self) throws InterruptedException
    {
        obj_id core_tower8 = getCellId(self, "core_tower8");
        obj_id hall7 = getCellId(self, "hall7");
        obj_id mediumroom28 = getCellId(self, "mediumroom28");
        obj_id smallroom21 = getCellId(self, "smallroom21");
        obj_id smallroom20 = getCellId(self, "smallroom20");
        obj_id smallroom31 = getCellId(self, "smallroom31");
        permissionsRemoveAllAllowed(core_tower8);
        permissionsRemoveAllAllowed(hall7);
        permissionsRemoveAllAllowed(mediumroom28);
        permissionsRemoveAllAllowed(smallroom21);
        permissionsRemoveAllAllowed(smallroom20);
        permissionsRemoveAllAllowed(smallroom31);
        permissionsMakePrivate(core_tower8);
        permissionsMakePrivate(hall7);
        permissionsMakePrivate(mediumroom28);
        permissionsMakePrivate(smallroom21);
        permissionsMakePrivate(smallroom20);
        permissionsMakePrivate(smallroom31);
    }
    public void initializeOrfObjVars(obj_id self) throws InterruptedException
    {
        removeObjVar(self, "status");
    }
    public int beginSpawn(obj_id self, dictionary params) throws InterruptedException
    {
        for (int x = 0; x < 200; x++)
        {
            String variable = "spawned" + x;
            if (hasObjVar(self, variable))
            {
                obj_id thing = getObjIdObjVar(self, variable);
                destroyObject(thing);
            }
        }
        setOrfDoorLocks(self);
        initializeOrfObjVars(self);
        return SCRIPT_CONTINUE;
    }
}
