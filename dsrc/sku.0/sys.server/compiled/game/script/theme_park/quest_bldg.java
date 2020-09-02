package script.theme_park;

import script.dictionary;
import script.library.create;
import script.library.factions;
import script.library.quests;
import script.location;
import script.obj_id;

public class quest_bldg extends script.base_script
{
    public quest_bldg()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        String datatable = getStringObjVar(self, "quest_table");
        String gatingString = dataTableGetString(datatable, 2, 1);
        String questID = dataTableGetString(datatable, 3, 1);
        int questNum = getIntObjVar(self, questID + ".questNum");
        location questLoc = getLocationObjVar(self, questID + ".questLoc");
        quests.addQuestLocationTarget(self, null, "quest", questLoc, 15);
        addLocationTarget("spawnGuy", questLoc, 100);
        return SCRIPT_CONTINUE;
    }
    public int OnArrivedAtLocation(obj_id self, String name) throws InterruptedException
    {
        String datatable = getStringObjVar(self, "quest_table");
        String gatingString = dataTableGetString(datatable, 2, 1);
        int gating = getIntObjVar(self, gatingString);
        String questID = dataTableGetString(datatable, 3, 1);
        int questNum = getIntObjVar(self, questID + ".questNum");
        if (name.equals("quest"))
        {
            quests.clearQuestLocationTarget(self, null, name);
        }
        if (name.equals("spawnGuy"))
        {
            removeLocationTarget(name);
            location spawn = getLocationObjVar(self, questID + ".questLoc");
            String toSpawn = dataTableGetString(datatable, 7, questNum);
            String bldgName = dataTableGetString(datatable, 8, questNum);
            String bldgScript = dataTableGetString(datatable, 9, questNum);
            obj_id bldg = createObject(toSpawn, spawn);
            attachScript(bldg, bldgScript);
            setObjVar(bldg, "player", self);
            setObjVar(self, questID + ".bldg", bldg);
            setObjVar(bldg, "quest_table", datatable);
            setObjVar(bldg, "questNum", questNum);
            setName(bldg, bldgName);
            createGuards(bldg);
        }
        if (name.equals("home"))
        {
            quests.clearQuestLocationTarget(self, null, name);
        }
        return SCRIPT_CONTINUE;
    }
    public int finishQuest(obj_id self, dictionary params) throws InterruptedException
    {
        String datatable = getStringObjVar(self, "quest_table");
        String gatingString = dataTableGetString(datatable, 2, 1);
        int gating = getIntObjVar(self, gatingString);
        String questID = dataTableGetString(datatable, 3, 1);
        int questNum = getIntObjVar(self, questID + ".questNum");
        location home = getLocationObjVar(self, questID + ".home");
        setObjVar(self, questID + ".done", 1);
        quests.addQuestLocationTarget(self, null, "home", home, 10);
        return SCRIPT_CONTINUE;
    }
    public void createGuards(obj_id bldg) throws InterruptedException
    {
        location g1 = getLocation(bldg);
        g1.x = g1.x + 10;
        location g2 = getLocation(bldg);
        g2.x = g2.x - 10;
        location g3 = getLocation(bldg);
        g3.z = g3.z - 10;
        location g4 = getLocation(bldg);
        g4.z = g4.z + 10;
        obj_id guard1 = create.object("thug", g1);
        obj_id guard2 = create.object("thug", g2);
        obj_id guard3 = create.object("thug", g3);
        obj_id guard4 = create.object("thug", g4);
        setUpNpc(guard1);
        setUpNpc(guard2);
        setUpNpc(guard3);
        setUpNpc(guard4);
        factions.setFaction(guard1, "valarian");
        factions.setFaction(guard2, "valarian");
        factions.setFaction(guard3, "valarian");
        factions.setFaction(guard4, "valarian");
        setObjVar(bldg, "guard1", guard1);
        setObjVar(bldg, "guard2", guard2);
        setObjVar(bldg, "guard3", guard3);
        setObjVar(bldg, "guard4", guard4);
        return;
    }
    public void setUpNpc(obj_id newNpc) throws InterruptedException
    {
        return;
    }
}
