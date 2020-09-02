package script.item.loot_kits.simple_kit_scripts;

import script.obj_id;

public class empty_datapad_fix extends script.base_script
{
    public empty_datapad_fix()
    {
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if (!hasScript(self, "item.loot_kits.simple_kit"))
        {
            attachScript(self, "item.loot_kits.simple_kit");
            detachScript(self, "item.loot_kits.simple_kit_scripts.empty_datapad_fix");
        }
        return SCRIPT_CONTINUE;
    }
}
