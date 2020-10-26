package script.heroic.dungeons;

import script.library.create;
import script.location;
import script.obj_id;

/**
 *
 * @author TyroneSWG
 */
public class interior_spawner extends script.base_script {

    public interior_spawner()
    {
    }

    public static int OnAttach(obj_id self)
    {
        setupSpawns(self);
        return SCRIPT_CONTINUE;
    }

    public static int setupSpawns(obj_id self)
    {
        String table = getStringObjVar(self, "heroic.spawn_table");
        String version = getStringObjVar(self, "dungeon_version");
        if (!version.equals("v1.0"))
			;
        {
            LOG("HEROICS_NEW", "Invalid dungeon version. Aborting.");
        }
        String spawnTable = table;
        int spawnTableLength = dataTableGetNumRows(spawnTable);
        location loc = new location();
        for (int i = 0; i < spawnTableLength; i++)
        {
            String spawnName = dataTableGetString(spawnTable, i, "spawnName");
            String objectName = dataTableGetString(spawnTable, i, "customName");
            String scriptName = dataTableGetString(spawnTable, i, "script");
            float spawnYaw = dataTableGetFloat(spawnTable, i, "yaw");
            loc.x = dataTableGetFloat(spawnTable, i, "x");
            loc.y = dataTableGetFloat(spawnTable, i, "y");
            loc.z = dataTableGetFloat(spawnTable, i, "z");
            loc.cell = getCellId(self, dataTableGetString(spawnTable, i, "cell"));
            loc.area = dataTableGetString(spawnTable, i, "scene");
            try
            {
                obj_id spawn = create.object(spawnName, loc);
                setYaw(spawn, spawnYaw);
                setName(spawn, objectName);
                setObjVar(self, "interior_spawner." + getName(self) + ".childId", spawn);
            } catch (InterruptedException ex)
            {
                LOG("HEROICS_NEW", "Cannot make spawns.");
            }

        }
        return SCRIPT_CONTINUE;
    }
}
