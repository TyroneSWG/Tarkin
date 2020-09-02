package script.theme_park.dungeon.geonosian_madbio_bunker;

import script.*;

public class trash_container extends script.base_script
{
    public trash_container()
    {
    }
    public static final String MSGS = "dungeon/geonosian_madbio";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        if (hasObjVar(self, "shutoff"))
        {
            obj_id shutoffCheck = getObjIdObjVar(self, "shutoff1");
            if (!exists(shutoffCheck))
            {
                makeShutOffSwitch(self);
            }
        }
        else 
        {
            makeShutOffSwitch(self);
        }
        messageTo(self, "showTrap", null, 1, true);
        setObjVar(self, "present", 1);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_OPEN);
        if (mid == null)
        {
            return SCRIPT_CONTINUE;
        }
        mid.setServerNotify(true);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_OPEN)
        {
            if (!hasObjVar(self, "trap_off"))
            {
                int dmgLocation = 0;
                int dmgAmt = rand(300, 700);
                int hitLoc = rand(1, 4);
                switch (hitLoc)
                {
                    case 1:
                    dmgLocation = HIT_LOCATION_R_ARM;
                    break;
                    case 2:
                    dmgLocation = HIT_LOCATION_L_ARM;
                    break;
                    case 3:
                    dmgLocation = HIT_LOCATION_BODY;
                    break;
                    case 4:
                    dmgLocation = HIT_LOCATION_R_LEG;
                    break;
                    default:
                    dmgLocation = HIT_LOCATION_L_LEG;
                    break;
                }
                string_id electric = new string_id(MSGS, "electric_trash");
                sendSystemMessage(player, electric);
                damage(player, DAMAGE_ELEMENTAL_ELECTRICAL, dmgLocation, dmgAmt);
                playClientEffectLoc(player, "clienteffect/trap_electric_01.cef", getLocation(self), 0.0f);
                return SCRIPT_OVERRIDE;
            }
            else 
            {
                if (hasObjVar(self, "present"))
                {
                    String newLoot = pickNewLoot();
                    obj_id cargo = createObject(newLoot, self, "");
                    if (newLoot.equals("object/tangible/component/weapon/geonosian_power_cube.iff"))
                    {
                        setCraftedId(cargo, cargo);
                    }
                    string_id value = new string_id(MSGS, "trash_full");
                    sendSystemMessage(player, value);
                    removeObjVar(self, "present");
                    messageTo(self, "resetTrash", null, 600, false);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAboutToOpenContainer(obj_id self, obj_id opener) throws InterruptedException
    {
        if (!hasObjVar(self, "trap_off"))
        {
            return SCRIPT_OVERRIDE;
        }
        return SCRIPT_CONTINUE;
    }
    public void makeShutOffSwitch(obj_id self) throws InterruptedException
    {
        obj_id top = getTopMostContainer(self);
        obj_id room = getCellId(top, "largecavehall2");
        location here = getLocation(self);
        String planet = here.area;
        location valve = new location(-136.73f, -21.29f, -84.72f, planet, room);
        obj_id shutoff = createObject("object/tangible/dungeon/wall_terminal_s2.iff", valve);
        attachScript(shutoff, "theme_park.dungeon.geonosian_madbio_bunker.trash_shutoff");
        setObjVar(shutoff, "trap", self);
        setObjVar(self, "shutoff1", shutoff);
        setObjVar(self, "valve", valve);
        setYaw(shutoff, 90);
        return;
    }
    public int trapShutOff(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id trap = getObjIdObjVar(self, "trap");
        destroyObject(trap);
        setObjVar(self, "trap_off", 1);
        messageTo(self, "turntrapOn", null, 20, true);
        return SCRIPT_CONTINUE;
    }
    public int turntrapOn(obj_id self, dictionary params) throws InterruptedException
    {
        if (hasObjVar(self, "trap_off"))
        {
            removeObjVar(self, "trap_off");
        }
        messageTo(self, "showTrap", null, 2, true);
        return SCRIPT_CONTINUE;
    }
    public int showTrap(obj_id self, dictionary params) throws InterruptedException
    {
        if (!hasObjVar(self, "trap_off"))
        {
            obj_id trap = createObject("object/static/particle/pt_poi_electricity_2x2.iff", getLocation(self));
            setObjVar(self, "trap", trap);
        }
        return SCRIPT_CONTINUE;
    }
    public String pickNewLoot() throws InterruptedException
    {
        String newLoot = "object/tangible/loot/dungeon/geonosian_mad_bunker/passkey.iff";
        if (hasObjVar(getSelf(), "room"))
        {
            newLoot = "object/tangible/dungeon/poison_stabilizer.iff";
            return newLoot;
        }
        int pickNewLoot = rand(1, 5);
        switch (pickNewLoot)
        {
            case 1:
            newLoot = "object/tangible/loot/dungeon/geonosian_mad_bunker/passkey.iff";
            break;
            case 2:
            newLoot = "object/tangible/component/weapon/geonosian_power_cube.iff";
            break;
            case 3:
            newLoot = "object/tangible/loot/dungeon/geonosian_mad_bunker/relic_gbb_honey_carafe.iff";
            break;
            case 4:
            newLoot = "object/tangible/component/weapon/geonosian_solidifying_agent.iff";
            break;
            case 5:
            newLoot = "object/tangible/loot/dungeon/geonosian_mad_bunker/relic_gbb_small_ball.iff";
            break;
            default:
            break;
        }
        return newLoot;
    }
    public int resetTrash(obj_id self, dictionary params) throws InterruptedException
    {
        setObjVar(self, "present", 1);
        return SCRIPT_CONTINUE;
    }
}
