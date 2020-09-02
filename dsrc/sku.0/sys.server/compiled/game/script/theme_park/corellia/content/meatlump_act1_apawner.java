package script.theme_park.corellia.content;

import script.dictionary;
import script.obj_id;

public class meatlump_act1_apawner extends script.base_script
{
    public meatlump_act1_apawner()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        messageTo(self, "attachSpawnerScript", null, 2, false);
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        messageTo(self, "attachSpawnerScript", null, 2, false);
        return SCRIPT_CONTINUE;
    }
    public int attachSpawnerScript(obj_id self, dictionary params) throws InterruptedException
    {
        if (!hasScript(self, "theme_park.corellia.content.meatlump_act1_spawner"))
        {
            attachScript(self, "theme_park.corellia.content.meatlump_act1_spawner");
        }
        return SCRIPT_CONTINUE;
    }
}
