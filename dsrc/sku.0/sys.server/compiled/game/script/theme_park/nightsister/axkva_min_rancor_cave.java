package script.theme_park.nightsister;

import script.dictionary;
import script.library.chat;
import script.obj_id;
import script.string_id;

public class axkva_min_rancor_cave extends script.base_script
{
    public axkva_min_rancor_cave()
    {
    }
    public static final String MSGS = "dungeon/nightsister_rancor_cave";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        String weapon = "object/weapon/melee/polearm/lance_nightsister_npc_version.iff";
        obj_id inventory = getObjectInSlot(self, "inventory");
        obj_id creatureWeapon = createObject(weapon, inventory, "");
        return SCRIPT_CONTINUE;
    }
    public int OnEnteredCombat(obj_id self) throws InterruptedException
    {
        string_id protect = new string_id(MSGS, "protect");
        chat.chat(self, protect);
        obj_id cave = getObjIdObjVar(self, "cave");
        dictionary params = new dictionary();
        params.put("axkva", self);
        messageTo(cave, "spawnguards", params, 5, false);
        return SCRIPT_CONTINUE;
    }
}
