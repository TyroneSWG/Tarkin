package script.event.lost_squadron;

import script.dictionary;
import script.library.utils;
import script.location;
import script.obj_id;

public class spawner extends script.base_script
{
    public spawner()
    {
    }
    public static final float FIFTEEN_MINUTES = 60 * 15;
    public static final String[] FIGHTER = 
    {
        "object/tangible/quest/lost_squadron_xwing.iff",
        "object/tangible/quest/lost_squadron_tie_bomber.iff"
    };
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        String setting = getConfigSetting("EventTeam", "lostSquadron");
        if (setting == null || setting.equals("") || !setting.equals("true"))
        {
            return SCRIPT_CONTINUE;
        }
        if (!hasObjVar(self, "event.lost_squadron.type"))
        {
            location here = getLocation(self);
            CustomerServiceLog("EventPerk", "[Lost Squadron Event] WARNING: event.lost_squadron.type objvar missing on spawner " + self + " located at " + here);
            return SCRIPT_CONTINUE;
        }
        setCurrentSpawnRect(self);
        if (!hasObjVar(self, "event.lost_squadron.fighter"))
        {
            messageTo(self, "spawnNextFighter", null, 20, false);
        }
        utils.setScriptVar(self, "startupValidateTries", 0);
        messageTo(self, "startupValidateFighter", null, FIFTEEN_MINUTES, false);
        return SCRIPT_CONTINUE;
    }
    public int startupValidateFighter(obj_id self, dictionary params) throws InterruptedException
    {
        int startupValidateTries = utils.getIntScriptVar(self, "startupValidateTries");
        obj_id fighter = getObjIdObjVar(self, "event.lost_squadron.fighter");
        if (!exists(fighter))
        {
            if (startupValidateTries > 4)
            {
                CustomerServiceLog("EventPerk", "[Lost Squadron Event] WARNING: Tracked fighter [" + fighter + "] never loaded (waited >1 hour) and probably does not exist anymore. Spawning a new one.");
                messageTo(self, "spawnNextFighter", null, 1, false);
                return SCRIPT_CONTINUE;
            }
            startupValidateTries++;
            utils.setScriptVar(self, "startupValidateTries", startupValidateTries);
            messageTo(self, "startupValidateFighter", null, FIFTEEN_MINUTES, false);
        }
        return SCRIPT_CONTINUE;
    }
    public int fighterRequestsDeletion(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id fighter = params.getObjId("fighter");
        messageTo(fighter, "goDie", null, 1, false);
        return SCRIPT_CONTINUE;
    }
    public int fighterDeletedTimeOut(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id fighter = params.getObjId("fighter");
        if (exists(fighter))
        {
            CustomerServiceLog("EventPerk", "[Lost Squadron Event] WARNING: Fighter said it deleted but it still exists. Spawner = " + self + ". Fighter = " + fighter);
            return SCRIPT_CONTINUE;
        }
        else 
        {
            messageTo(self, "spawnNextFighter", null, 1, false);
        }
        return SCRIPT_CONTINUE;
    }
    public int fighterStillAlive(obj_id self, dictionary params) throws InterruptedException
    {
        float rightNow = getGameTime();
        obj_id fighter = params.getObjId("fighter");
        setObjVar(self, "event.lost_squadron.last_checkin", rightNow);
        return SCRIPT_CONTINUE;
    }
    public int spawnNextFighter(obj_id self, dictionary params) throws InterruptedException
    {
        location here = getLocation(self);
        location searchRectLL = getLocationObjVar(self, "event.lost_squadron.searchRectLL");
        location searchRectUR = getLocationObjVar(self, "event.lost_squadron.searchRectUR");
        location fighterLocation = getGoodLocation(8, 8, searchRectLL, searchRectUR, false, false);
        int coinFlip = rand(0, 1);
        String fighterTemplate = FIGHTER[coinFlip];
        obj_id fighter = createObject(fighterTemplate, fighterLocation);
        if (!exists(fighter) && !isIdValid(fighter))
        {
            CustomerServiceLog("EventPerk", "[Lost Squadron Event] WARNING: Failed to create a new fighter object. Spawner = " + self + " at location " + here);
            return SCRIPT_CONTINUE;
        }
        float rightNow = getGameTime();
        String type = getStringObjVar(self, "event.lost_squadron.type");
        setObjVar(fighter, "event.lost_squadron.type", type);
        setObjVar(fighter, "event.lost_squadron.timeStamp", rightNow);
        setObjVar(fighter, "event.lost_squadron.mom", self);
        setObjVar(fighter, "event.lost_squadron.style", fighterTemplate);
        persistObject(fighter);
        setObjVar(self, "event.lost_squadron.fighter", fighter);
        setObjVar(self, "event.lost_squadron.fighterLocation", fighterLocation);
        CustomerServiceLog("EventPerk", "[Lost Squadron Event] A new fighter was spawned by " + self + " of type " + type + " at location " + fighterLocation + " fighter ID is " + fighter);
        return SCRIPT_CONTINUE;
    }
    public void setCurrentSpawnRect(obj_id self) throws InterruptedException
    {
        location here = getLocation(self);
        String type = getStringObjVar(self, "event.lost_squadron.type");
        float spawnRectLLx = 0;
        float spawnRectURx = 0;
        float spawnRectLLz = 0;
        float spawnRectURz = 0;
        if (type.equals("dath_north"))
        {
            spawnRectLLx = -7500;
            spawnRectLLz = 4000;
            spawnRectURx = 7500;
            spawnRectURz = 7500;
        }
        if (type.equals("dath_south"))
        {
            spawnRectLLx = -7500;
            spawnRectLLz = -7500;
            spawnRectURx = 0;
            spawnRectURz = -3000;
        }
        if (type.equals("endor_north"))
        {
            spawnRectLLx = -3000;
            spawnRectLLz = 2000;
            spawnRectURx = 2000;
            spawnRectURz = 7500;
        }
        if (type.equals("endor_south"))
        {
            spawnRectLLx = 4000;
            spawnRectLLz = -7500;
            spawnRectURx = 7500;
            spawnRectURz = 0;
        }
        if (type.equals("yavin_north"))
        {
            spawnRectLLx = -7500;
            spawnRectLLz = 5000;
            spawnRectURx = 7500;
            spawnRectURz = 7500;
        }
        if (type.equals("yavin_south"))
        {
            spawnRectLLx = -2000;
            spawnRectLLz = -6000;
            spawnRectURx = 7500;
            spawnRectURz = 0;
        }
        if (spawnRectLLx == 0 && spawnRectLLz == 0 && spawnRectURx == 0 & spawnRectURz == 0)
        {
            CustomerServiceLog("EventPerk", "[Lost Squadron Event] WARNING: Spawn location rectangle did not initialize for spawner " + self + " at location " + here);
            return;
        }
        location searchRectLL = getLocation(self);
        location searchRectUR = getLocation(self);
        searchRectLL.x = spawnRectLLx;
        searchRectLL.z = spawnRectLLz;
        searchRectUR.x = spawnRectURx;
        searchRectUR.z = spawnRectURz;
        setObjVar(self, "event.lost_squadron.searchRectLL", searchRectLL);
        setObjVar(self, "event.lost_squadron.searchRectUR", searchRectUR);
        return;
    }
}
