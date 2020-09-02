package script.theme_park.dungeon.death_watch_bunker;

import script.*;
import script.library.create;
import script.library.permissions;
import script.library.utils;

public class loot_chest extends script.base_script
{
    public loot_chest()
    {
    }
    public static final string_id NO_PERMISSION = new string_id("dungeon/death_watch", "no_permission");
    public int OnAttach(obj_id self) throws InterruptedException
    {
        detachScript(self, "item.container.loot_crate");
        attachScript(self, "item.container.base.base_container");
        if (!hasObjVar(self, "spawnedLoot"))
        {
            spawnLoot(self);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_OPEN)
        {
            if (hasObjVar(self, "spawnedLoot"))
            {
                obj_id spawnedGuard = utils.getObjIdObjVar(self, "spawnedGuard", obj_id.NULL_ID);
                if (!isIdValid(spawnedGuard) || !exists(spawnedGuard) || isDead(spawnedGuard))
                {
                    spawnGuard(self);
                }
            }
            if (!hasObjVar(self, permissions.VAR_PERMISSION_BASE))
            {
                switch (getContainerType(self))
                {
                    case 0:
                    detachScript(self, "item.container.base.base_container");
                    break;
                    case 1:
                    utils.requestContainerOpen(player, self);
                    break;
                    default:
                    break;
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
    public String pickNewLoot() throws InterruptedException
    {
        String newLoot = "object/tangible/loot/tool/usable_datapad.iff";
        int pickNewLoot = getLootFromPercentage();
        switch (pickNewLoot)
        {
            case 1:
            newLoot = "object/tangible/loot/dungeon/death_watch_bunker/usable_datapad.iff";
            break;
            case 2:
            newLoot = "object/tangible/loot/dungeon/death_watch_bunker/usable_datapad.iff";
            break;
            case 3:
            newLoot = "object/tangible/wearables/goggles/rebreather.iff";
            break;
            case 4:
            newLoot = "object/tangible/loot/dungeon/death_watch_bunker/ducted_fan.iff";
            break;
            case 5:
            newLoot = "object/tangible/loot/dungeon/death_watch_bunker/fuel_dispersion_unit.iff";
            break;
            case 6:
            newLoot = "object/tangible/loot/dungeon/death_watch_bunker/fuel_injector_tank.iff";
            break;
            case 7:
            newLoot = "object/tangible/loot/dungeon/death_watch_bunker/usable_datapad.iff";
            break;
            case 8:
            newLoot = "object/tangible/dungeon/death_watch_bunker/gel_packet.iff";
            break;
            case 9:
            newLoot = "object/tangible/loot/dungeon/death_watch_bunker/art_crate.iff";
            break;
            case 10:
            newLoot = "object/tangible/loot/dungeon/death_watch_bunker/art_crate.iff";
            break;
            case 11:
            newLoot = "object/tangible/loot/loot_schematic/death_watch_executioners_hack_schematic.iff";
            break;
            case 12:
            newLoot = "object/tangible/loot/dungeon/death_watch_bunker/art_crate.iff";
            break;
            case 13:
            newLoot = "object/tangible/loot/loot_schematic/death_watch_pistol_de_10_schematic.iff";
            break;
            default:
            break;
        }
        return newLoot;
    }
    public int getLootFromPercentage() throws InterruptedException
    {
        int percent = rand(1, 100);
        debugSpeakMsg(getSelf(), "Picked " + percent + "%");
        if (percent >= 97)
        {
            return 13;
        }
        else if (percent >= 94 && percent < 97)
        {
            return 12;
        }
        else if (percent >= 91 && percent < 94)
        {
            return 11;
        }
        else if (percent >= 80 && percent < 91)
        {
            return 10;
        }
        else if (percent >= 71 && percent < 80)
        {
            return 9;
        }
        else if (percent >= 60 && percent < 71)
        {
            return 8;
        }
        else if (percent >= 55 && percent < 60)
        {
            return 7;
        }
        else if (percent >= 50 && percent < 55)
        {
            return 6;
        }
        else if (percent >= 43 && percent < 50)
        {
            return 5;
        }
        else if (percent >= 35 && percent < 43)
        {
            return 4;
        }
        else if (percent >= 25 && percent < 35)
        {
            return 3;
        }
        else if (percent >= 15 && percent < 25)
        {
            return 2;
        }
        else if (percent >= 1 && percent < 15)
        {
            return 1;
        }
        return 1;
    }
    public int OnAboutToLoseItem(obj_id self, obj_id destContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        obj_id player = utils.getContainingPlayer(destContainer);
        removeObjVar(self, "spawnedLoot");
        messageTo(self, "makeMoreLoot", null, 1200, true);
        return SCRIPT_CONTINUE;
    }
    public int makeMoreLoot(obj_id self, dictionary params) throws InterruptedException
    {
        if (!hasObjVar(self, "spawnedLoot"))
        {
            spawnLoot(self);
        }
        return SCRIPT_CONTINUE;
    }
    public obj_id spawnLoot(obj_id self) throws InterruptedException
    {
        String setting = getConfigSetting("Dungeon", "Death_Watch");
        if (setting == null || setting.equals("false") || setting.equals("0"))
        {
            return obj_id.NULL_ID;
        }
        String newLoot = pickNewLoot();
        obj_id cargo = createObject(newLoot, self, "");
        setObjVar(self, "spawnedLoot", cargo);
        return cargo;
    }
    public obj_id spawnGuard(obj_id self) throws InterruptedException
    {
        obj_id top = getTopMostContainer(self);
        location here = getLocation(self);
        obj_id cell = here.cell;
        String room = utils.getCellName(top, cell);
        location loc = getGoodLocation(top, room);
        obj_id guard = create.object("mand_bunker_super_battle_droid", loc);
        setObjVar(self, "spawnedGuard", guard);
        return guard;
    }
}
