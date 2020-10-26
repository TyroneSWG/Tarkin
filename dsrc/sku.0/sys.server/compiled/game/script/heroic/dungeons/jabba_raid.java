package script.heroic.dungeons;

import script.dictionary;
import script.library.create;
import script.library.utils;
import script.location;
import script.obj_id;

public class jabba_raid extends script.base_script {

    public jabba_raid()
    {
    }

    public static String AREA_SCENE = "adventure3";
    public static String SPAWN_TABLE = "datatable/theme_park/heroic/jabba_raid.iff";

    public static int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "SPAWNER | JABBA_HEROIC");
        return SCRIPT_CONTINUE;
    }

    public static int OnAddedToWorld(obj_id self) throws InterruptedException
    {
        setupSpawns(self);
        return SCRIPT_CONTINUE;
    }

    public static int setupSpawns(obj_id self)
    {
        String spawnTable = SPAWN_TABLE;
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
                setObjVar(self, "heroic_jb.mob.childId", spawn);
            } catch (InterruptedException ex)
            {
                LOG("HEROICS_NEW", "Cannot make spawns.");
            }

        }
        return SCRIPT_CONTINUE;
    }
}
