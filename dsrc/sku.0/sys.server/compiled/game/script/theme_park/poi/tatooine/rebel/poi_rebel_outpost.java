package script.theme_park.poi.tatooine.rebel;

import script.dictionary;
import script.library.ai_lib;
import script.library.factions;
import script.obj_id;

public class poi_rebel_outpost extends script.theme_park.poi.base
{
    public poi_rebel_outpost()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        String objective = poiGetObjective(self);
        if (objective.equals("rebels"))
        {
            obj_id outpost = poiCreateObject(self, "center", "object/installation/turret/turret_block_large.iff", 0, 0);
            factions.setFaction(outpost, factions.FACTION_REBEL);
            poiSetDestroyMessage(outpost, "outpostKilled");
            setRebelFaction(outpost);
            obj_id tr1 = poiCreateNpc("reb1", "rebel", 6, 6);
            setRebelFaction(tr1);
            obj_id tr2 = poiCreateNpc("reb2", "rebel", -10, 13);
            setRebelFaction(tr2);
            obj_id tr3 = poiCreateNpc("reb3", "rebel", -10, 11);
            setRebelFaction(tr3);
            obj_id tr4 = poiCreateNpc("reb4", "rebel", 5, 5);
            setRebelFaction(tr4);
            obj_id tr5 = poiCreateNpc("reb5", "rebel", 0, -5);
            setRebelFaction(tr5);
            obj_id tr6 = poiCreateNpc("reb6", "rebel", 10, 2);
            setRebelFaction(tr6);
            factions.setFaction(outpost, "Rebel");
            attachScript(tr1, "theme_park.poi.tatooine.behavior.stormtrooper_stationary_poi");
            attachScript(tr2, "theme_park.poi.tatooine.behavior.stormtrooper_stationary_poi");
            attachScript(tr3, "theme_park.poi.tatooine.behavior.poi_waiting");
            attachScript(tr4, "theme_park.poi.tatooine.behavior.poi_waiting");
            String[] patrolPoints = new String[4];
            patrolPoints[0] = "-20, 20";
            patrolPoints[1] = "-20, -20.3";
            patrolPoints[2] = "20, 20";
            patrolPoints[3] = "20, -20";
            ai_lib.setPatrolPath(tr5, patrolPoints);
            String[] patrolPoints2 = new String[5];
            patrolPoints2[0] = "15, 15";
            patrolPoints2[1] = "15, -15.3";
            patrolPoints2[2] = "-15, -15";
            patrolPoints2[3] = "-15, 15";
            patrolPoints2[4] = "-20, 20";
            ai_lib.setPatrolPath(tr6, patrolPoints2);
        }
        return SCRIPT_CONTINUE;
    }
    public void setRebelFaction(obj_id target) throws InterruptedException
    {
        factions.setFaction(target, "Rebel");
    }
    public int outpostKilled(obj_id self, dictionary params) throws InterruptedException
    {
        poiComplete(POI_SUCCESS);
        return SCRIPT_CONTINUE;
    }
}
