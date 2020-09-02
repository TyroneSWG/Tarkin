package script.systems.gcw;

import script.library.advanced_turret;
import script.library.factions;
import script.library.gcw;
import script.library.utils;
import script.location;
import script.obj_id;

public class gcw_city_kit_turret extends script.systems.gcw.gcw_city_kit
{
    public gcw_city_kit_turret()
    {
    }
    public static final int CONSTRUCTION_HP_TURRET = 5000;
    public static final String[] lowImperials = 
    {
        "gcw_city_imperial_turret_1"
    };
    public static final String[] normalImperials = 
    {
        "gcw_city_imperial_turret_2"
    };
    public static final String[] eliteImperials = 
    {
        "gcw_city_imperial_turret_3"
    };
    public static final String[] lowRebels = 
    {
        "gcw_city_rebel_turret_1"
    };
    public static final String[] normalRebels = 
    {
        "gcw_city_rebel_turret_2"
    };
    public static final String[] eliteRebels = 
    {
        "gcw_city_rebel_turret_3"
    };
    public void setupConstructionQuests(obj_id self, obj_id pylon) throws InterruptedException
    {
        setName(pylon, "Turret Construction Site");
        utils.setScriptVar(pylon, "gcw.name", "Turret Construction Site");
        attachScript(pylon, "systems.gcw.gcw_city_pylon_turret");
    }
    public void setupInvasionQuests(obj_id kit) throws InterruptedException
    {
    }
    public obj_id createFactionKit(int faction, location loc) throws InterruptedException
    {
        if (loc == null)
        {
            return null;
        }
        obj_id kit = null;
        obj_id self = getSelf();
        if (!isIdValid(self) || !exists(self))
        {
            return null;
        }
        int construction = getConstructionQuestsCompleted(self);
        String npcName = "";
        int turretMinDamage = 1;
        int turretMaxDamage = 2;
        int turretDisplayPower = 1;
        if (construction < 1)
        {
            return null;
        }
        else if (construction < gcw.GCW_CONSTRUCTION_MAXIMUM / 2)
        {
            turretMinDamage = 2000;
            turretMaxDamage = 4000;
            if (faction == factions.FACTION_FLAG_REBEL)
            {
                npcName = lowRebels[rand(0, lowRebels.length - 1)];
            }
            else if (faction == factions.FACTION_FLAG_IMPERIAL)
            {
                npcName = lowImperials[rand(0, lowImperials.length - 1)];
            }
            turretDisplayPower = 1;
        }
        else if (construction < gcw.GCW_CONSTRUCTION_MAXIMUM)
        {
            turretMinDamage = 4000;
            turretMaxDamage = 8000;
            if (faction == factions.FACTION_FLAG_REBEL)
            {
                npcName = normalRebels[rand(0, normalRebels.length - 1)];
            }
            else if (faction == factions.FACTION_FLAG_IMPERIAL)
            {
                npcName = normalImperials[rand(0, normalImperials.length - 1)];
            }
            turretDisplayPower = 2;
        }
        else if (construction >= gcw.GCW_CONSTRUCTION_MAXIMUM)
        {
            turretMinDamage = 6000;
            turretMaxDamage = 12000;
            if (faction == factions.FACTION_FLAG_REBEL)
            {
                npcName = eliteRebels[rand(0, eliteRebels.length - 1)];
            }
            else if (faction == factions.FACTION_FLAG_IMPERIAL)
            {
                npcName = eliteImperials[rand(0, eliteImperials.length - 1)];
            }
            turretDisplayPower = 3;
        }
        if (factions.FACTION_FLAG_IMPERIAL == faction)
        {
            kit = advanced_turret.createTurret(loc, getYaw(self), advanced_turret.TYPE_BLOCK, advanced_turret.SIZE_LARGE, advanced_turret.DAMAGE_ELEMENTAL_HEAT, turretMinDamage, turretMaxDamage, construction * getConstructionHP(), 65.0f, 4.0f, "Imperial");
            setName(kit, "Imperial Turret");
            attachScript(kit, "systems.gcw.gcw_turret");
            utils.setScriptVar(kit, "gcw.displayPower", turretDisplayPower);
        }
        else if (factions.FACTION_FLAG_REBEL == faction)
        {
            kit = advanced_turret.createTurret(loc, getYaw(self), advanced_turret.TYPE_DISH, advanced_turret.SIZE_LARGE, advanced_turret.DAMAGE_ELEMENTAL_HEAT, turretMinDamage, turretMaxDamage, construction * getConstructionHP(), 65.0f, 4.0f, "Rebel");
            setName(kit, "Rebel Turret");
            attachScript(kit, "systems.gcw.gcw_turret");
            utils.setScriptVar(kit, "gcw.displayPower", turretDisplayPower);
        }
        return kit;
    }
    public int getConstructionHP() throws InterruptedException
    {
        return CONSTRUCTION_HP_TURRET;
    }
}
