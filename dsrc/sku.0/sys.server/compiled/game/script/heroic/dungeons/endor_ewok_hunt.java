package script.heroic.dungeons;

import script.library.create;
import script.location;
import script.obj_id;

public class endor_ewok_hunt extends script.base_script {

    public endor_ewok_hunt()
    {
    }

    public static String AREA_SPAWN_TABLE = "datatable/theme_park/heroic/ewok_ewok_hunt.iff";
    public static String AREA_SCENE = "adventure3";

    public static int OnAddedToWorld(obj_id self) throws InterruptedException
    {
        setupSpawns(self);
        return SCRIPT_CONTINUE;
    }

    public static int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "SPAWNER | EWOK_HEROIC");
        return SCRIPT_CONTINUE;
    }

    public static int setupSpawns(obj_id self)
    {
        String version = getStringObjVar(self, "dungeon_version");
        if (!version.equals("v1.0"))
			;
        {
            LOG("HEROICS_NEW", "Invalid dungeon version. Aborting.");
        }
        String spawnTable = AREA_SPAWN_TABLE;
        int spawnTableLength = dataTableGetNumRows(spawnTable);
        location loc = new location();
        for (int i = 0; i < spawnTableLength; i++)
        {
            loc.area = AREA_SCENE;
            String spawnName = dataTableGetString(spawnTable, i, "spawnName");
            String objectName = dataTableGetString(spawnTable, i, "customName");
            String scriptName = dataTableGetString(spawnTable, i, "script");
            float spawnYaw = dataTableGetFloat(spawnTable, i, "yaw");
            loc.x = dataTableGetFloat(spawnTable, i, "x");
            loc.y = dataTableGetFloat(spawnTable, i, "y");
            loc.z = dataTableGetFloat(spawnTable, i, "z");
            try
            {
                obj_id spawn = create.object(spawnName, loc);
                setYaw(spawn, spawnYaw);
                setName(spawn, objectName);
                setObjVar(self, "heroic_ew.mob.childId", spawn);
            } catch (InterruptedException ex)
            {
                LOG("HEROICS_NEW", "Cannot make spawns.");
            }

        }
        return SCRIPT_CONTINUE;
    }
}
