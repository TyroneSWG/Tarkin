package script.theme_park.restuss_event;

import script.dictionary;
import script.library.factions;
import script.library.groundquests;
import script.library.utils;
import script.location;
import script.obj_id;
import script.string_id;

public class destroy_boss_imperial extends script.base_script
{
    public destroy_boss_imperial()
    {
    }
    public static final String VOLUME_NAME = "quest_area";
    public static final string_id AREA_FOUND = new string_id("restuss_event/object", "right_area");
    public static final string_id AREA_FOUND_NO_SPAWN = new string_id("restuss_event/object", "right_area_wrong_time");
    public int OnAttach(obj_id self) throws InterruptedException
    {
        createTriggerVolume(VOLUME_NAME, 10.0f, true);
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        createTriggerVolume(VOLUME_NAME, 10.0f, true);
        return SCRIPT_CONTINUE;
    }
    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id breacher) throws InterruptedException
    {
        location spawnerLocation = getLocation(self);
        obj_id spawnerObject = getFirstObjectWithTemplate(spawnerLocation, 2.0f, "object/tangible/theme_park/invisible_object.iff");
        if (!factions.isRebel(breacher))
        {
            return SCRIPT_CONTINUE;
        }
        if (groundquests.isTaskActive(breacher, "restuss_rebel_st3_destroy_boss", "killImperialsBoss") && (!utils.hasScriptVar(self, "spawnedAlready")))
        {
            if (isIdNull(spawnerObject))
            {
                sendSystemMessage(breacher, AREA_FOUND);
                obj_id waveSpawner = createObject("object/tangible/theme_park/invisible_object.iff", getLocation(self));
                setObjVar(waveSpawner, "wave_spawner.data_table", "datatables/spawning/restuss_event/st3_rebel_destroy_wave.iff");
                attachScript(waveSpawner, "theme_park.wave_spawner");
                utils.setScriptVar(self, "spawnedAlready", 1);
                messageTo(self, "readyToSpawn", null, 300.0f, false);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int test(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id waveSpawner = createObject("object/tangible/theme_park/invisible_object.iff", getLocation(self));
        setObjVar(waveSpawner, "wave_spawner.data_table", "datatables/spawning/restuss_event/st3_rebel_destroy_wave.iff");
        attachScript(waveSpawner, "theme_park.wave_spawner");
        utils.setScriptVar(self, "spawnedAlready", 1);
        messageTo(self, "readyToSpawn", null, 300.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int readyToSpawn(obj_id self, dictionary params) throws InterruptedException
    {
        utils.removeScriptVar(self, "spawnedAlready");
        return SCRIPT_CONTINUE;
    }
}
