package script.theme_park.alderaan.act3;

import script.dictionary;
import script.library.ai_lib;
import script.library.factions;
import script.location;
import script.obj_id;

public class rebel_coordinator extends script.base_script
{
    public rebel_coordinator()
    {
    }
    public static final String COMMANDER_TABLE = "datatables/convo/alderaan/act2/commander_locs.iff";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        setCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }
    public int messageStartMission(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = params.getObjId("player");
        obj_id npc = params.getObjId("npc");
        int missionNum = params.getInt("value");
        location deliveryLoc;
        String npcName;
        switch (missionNum)
        {
            case 1:
            deliveryLoc = getDeliveryLoc(player, 1500, 2000);
            npcName = "coa3_lookout";
            break;
            case 3:
            deliveryLoc = getDeliveryLoc(player, 1500, 2500);
            npcName = "object/building/theme_park/alderaan/act3/flora_warehouse.iff";
            break;
            case 4:
            if (!hasObjVar(player, "coa3.rebel.missionPlanet"))
            {
                deliveryLoc = getCommanderLoc(player);
            }
            else 
            {
                deliveryLoc = getLocationObjVar(player, "coa3.rebel.missionLoc");
            }
            npcName = "";
            break;
            default:
            LOG("CoA3_Rebel", "WARNING!!!  Rebel_Coordinator - Recieved messageStartMission for unknown mission number. (" + missionNum + ")");
            return SCRIPT_OVERRIDE;
        }
        location returnLoc = new location();
        obj_id building = getTopMostContainer(npc);
        if (building == npc)
        {
            returnLoc = getLocation(npc);
        }
        else 
        {
            returnLoc = getLocation(building);
        }
        setObjVar(player, "coa3.rebel.missionNum", missionNum);
        setObjVar(player, "coa3.rebel.missionNpc", npcName);
        setObjVar(player, "coa3.rebel.missionLoc", deliveryLoc);
        setObjVar(player, "coa3.rebel.returnLoc", returnLoc);
        if (hasScript(player, "theme_park.alderaan.act3.rebel_mission"))
        {
            messageTo(player, "handleRestartMission", params, 0, false);
        }
        else 
        {
            attachScript(player, "theme_park.alderaan.act3.rebel_mission");
        }
        return SCRIPT_CONTINUE;
    }
    public int messageAbortMission(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = params.getObjId("player");
        obj_id npc = params.getObjId("npc");
        int missionNum = params.getInt("value");
        switch (missionNum)
        {
            case 1:
            case 2:
            case 3:
            case 4:
            messageTo(player, "handleAbortMission", params, 0, false);
            break;
            default:
            LOG("CoA3_Rebel", "WARNING!!!  Rebel_Coordinator - Recieved messageAbortMission for unknown mission number. (" + missionNum + ")");
        }
        return SCRIPT_CONTINUE;
    }
    public location getDeliveryLoc(obj_id player, int minDistance, int maxDistance) throws InterruptedException
    {
        final String planetList[] = 
        {
            "dathomir",
            "endor",
            "yavin4"
        };
        int idx = rand(0, planetList.length - 1);
        location planetLoc = new location(8190, 500, 8190, planetList[idx]);
        setObjVar(player, "coa3.rebel.redirect.planet", planetList[idx]);
        setObjVar(player, "coa3.rebel.redirect.minDistance", minDistance);
        setObjVar(player, "coa3.rebel.redirect.maxDistance", maxDistance);
        return planetLoc;
    }
    public location getCommanderLoc(obj_id player) throws InterruptedException
    {
        String[] commanderFaction = dataTableGetStringColumn(COMMANDER_TABLE, 0);
        int rndCommander = rand(0, commanderFaction.length - 1);
        while (!commanderFaction[rndCommander].equals("rebel"))
        {
            rndCommander = rand(0, commanderFaction.length - 1);
        }
        int x = dataTableGetInt(COMMANDER_TABLE, rndCommander, "x");
        int y = dataTableGetInt(COMMANDER_TABLE, rndCommander, "y");
        int z = dataTableGetInt(COMMANDER_TABLE, rndCommander, "z");
        String planet = dataTableGetString(COMMANDER_TABLE, rndCommander, "planet");
        location commanderLoc = new location(x, y, z, planet);
        setObjVar(player, "coa3.rebel.missionPlanet", planet);
        return commanderLoc;
    }
    public int OnGiveItem(obj_id self, obj_id item, obj_id player) throws InterruptedException
    {
        if ((getTemplateName(item)).equals("object/tangible/theme_park/alderaan/act3/alderaan_flora.iff"))
        {
            int progress = getIntObjVar(player, "coa3.convTracker");
            if (progress < 0 || (progress > 199 && progress < 209))
            {
                factions.awardFactionStanding(player, "Rebel", 50);
                destroyObject(item);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
