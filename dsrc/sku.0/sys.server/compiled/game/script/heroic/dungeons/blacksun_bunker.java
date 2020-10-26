package script.heroic.dungeons;

import script.library.create;
import script.location;
import script.obj_id;

public class blacksun_bunker extends script.base_script {

    public blacksun_bunker()
    {
    }

    public static String AREA_NAME = "adventure3";
    public static String BUNKER_TEMPLATE = "object/tangible/heroic/blacksun_door.iff";
    public static String DUNGEON_SPAWN_TABLE = "datatable/theme_park/heroic/blacksun_bunker.iff";

    public static int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "SPAWNER | BS_HEROIC");
        return SCRIPT_CONTINUE;
    }

    public static int OnAddedToWorld(obj_id self) throws InterruptedException
    {
        setupSpawns(self);
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
        String spawnTable = DUNGEON_SPAWN_TABLE;
        int spawnTableLength = dataTableGetNumRows(spawnTable);
        location loc = new location();
        for (int i = 0; i < spawnTableLength; i++)
        {
            loc.area = AREA_NAME;
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
                setObjVar(self, "heroic_bs.mob.childId", spawn);
            } catch (InterruptedException ex)
            {
                LOG("HEROICS_NEW", "Cannot make spawns.");
            }

        }
        return SCRIPT_CONTINUE;
    }
}
