package script.theme_park.imperial;

import script.dictionary;
import script.library.create;
import script.library.quests;
import script.location;
import script.obj_id;
import script.string_id;

public class quest_player extends script.base_script
{
    public quest_player()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        String datatable = getStringObjVar(self, "quest_table");
        String gatingString = "theme_park_imperial";
        String questID = "emperors_retreat";
        int questNum = getIntObjVar(self, questID + ".questNum");
        int totalQuestNum = getIntObjVar(self, gatingString);
        String target = dataTableGetString(datatable, questNum, "npc_name");
        if (target == null || target.equals(""))
        {
            target = dataTableGetString(datatable, questNum, "npc_type");
            if (target == null || target.equals(""))
            {
                target = "Quest Waypoint";
            }
        }
        String file = "theme_park_imperial/quest_details";
        String entry = "imperial_retreat_" + totalQuestNum;
        location questLoc = getLocationObjVar(self, questID + ".questLoc");
        obj_id waypoint = quests.addThemeParkWaypoint(self, questID, "quest", questLoc, 20, target, file, entry);
        if (questID != null && !questID.equals(""))
        {
            setObjVar(self, questID + ".waypoint", waypoint);
            addLocationTarget("spawnImpGuy", questLoc, 100);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnArrivedAtLocation(obj_id self, String name) throws InterruptedException
    {
        String datatable = getStringObjVar(self, "quest_table");
        String gatingString = dataTableGetString(datatable, 1, "overall_objvar");
        int gating = getIntObjVar(self, gatingString);
        String questID = dataTableGetString(datatable, 1, "temp_objvar");
        int questNum = getIntObjVar(self, questID + ".questNum");
        String type = dataTableGetString(datatable, questNum, "quest_type");
        if (name.equals("quest"))
        {
            obj_id vip = getObjIdObjVar(self, questID + ".vip");
            messageTo(vip, "saySomething", null, 0, true);
        }
        if (name.equals("spawnImpGuy"))
        {
            if (type.equals("go"))
            {
                messageTo(self, "finishImpQuest", null, 2, true);
                return SCRIPT_OVERRIDE;
            }
            location spawn = getLocationObjVar(self, questID + ".questLoc");
            String npcToSpawn = dataTableGetString(datatable, questNum, "npc_type");
            if (npcToSpawn == null)
            {
                string_id complete = new string_id("theme_park/messages", "imperial_completion_message");
                String completion = getString(complete);
                sendSystemMessage(self, completion, null);
                if (questID != null && !questID.equals(""))
                {
                    setObjVar(self, questID + ".noloc", 1);
                }
                return SCRIPT_OVERRIDE;
            }
            String npcName = dataTableGetString(datatable, questNum, "npc_name");
            String npcScript = dataTableGetString(datatable, questNum, "npc_script");
            obj_id npc = create.object(npcToSpawn, spawn);
            if (questID != null && !questID.equals(""))
            {
                setObjVar(npc, "player", self);
                setObjVar(self, questID + ".vip", npc);
                setObjVar(npc, "quest_table", datatable);
                setObjVar(npc, "questNum", questNum);
                setObjVar(self, questID + ".arrived", 1);
                attachScript(npc, npcScript);
            }
            if (npcName != null && !npcName.equals(""))
            {
                setName(npc, npcName);
            }
            spawnExtras(self, spawn, questID);
        }
        if (name.equals("home"))
        {
            obj_id waypoint = getObjIdObjVar(self, questID + ".waypointhome");
            if (waypoint != null)
            {
                destroyWaypointInDatapad(waypoint, self);
            }
            quests.clearQuestLocationTarget(self, null, name);
        }
        return SCRIPT_CONTINUE;
    }
    public int finishImpQuest(obj_id self, dictionary params) throws InterruptedException
    {
        String datatable = getStringObjVar(self, "quest_table");
        String file = "theme_park_imperial/quest_details";
        String questID = dataTableGetString(datatable, 1, "temp_objvar");
        String entry = questID + "_return";
        setObjVar(self, "GotFinishMessage", 1);
        String gatingString = dataTableGetString(datatable, 1, "overall_objvar");
        int gating = getIntObjVar(self, gatingString);
        int questNum = getIntObjVar(self, questID + ".questNum");
        obj_id waypoint = getObjIdObjVar(self, questID + ".waypoint");
        if (waypoint != null)
        {
            destroyWaypointInDatapad(waypoint, self);
        }
        location home = getLocationObjVar(self, questID + ".home");
        if (!hasObjVar(self, questID + ".failed"))
        {
            string_id complete = new string_id("theme_park/messages", "imperial_completion_message");
            String completion = getString(complete);
            sendSystemMessage(self, completion, null);
        }
        if (questID != null && !questID.equals(""))
        {
            if (!hasObjVar(self, questID + ".done"))
            {
                setObjVar(self, questID + ".done", 1);
                obj_id waypointhome = quests.addThemeParkWaypoint(self, questID, "quest_return", home, 15, "Finish Quest", file, entry);
                setObjVar(self, questID + ".waypointhome", waypointhome);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public void spawnExtras(obj_id self, location spawn, String questID) throws InterruptedException
    {
        String datatable = getStringObjVar(self, "quest_table");
        int questNum = getIntObjVar(self, "questNum");
        location here = getLocationObjVar(self, questID + ".questLoc");
        if (here == null)
        {
            here = getLocation(self);
        }
        String spawn1 = dataTableGetString(datatable, questNum, "extra_npc");
        String spawn2 = dataTableGetString(datatable, questNum, "extra_npc2");
        String spawn3 = dataTableGetString(datatable, questNum, "extra_npc3");
        String spawn4 = dataTableGetString(datatable, questNum, "extra_npc4");
        if (spawn1 != null && !spawn1.equals("none") && !spawn1.equals(""))
        {
            here.x = here.x + rand(3, 10);
            here.z = here.z + rand(3, 10);
            obj_id extra = create.object(spawn1, here);
            if (extra != null && isIdValid(extra))
            {
                String disposition = dataTableGetString(datatable, questNum, "extra_npc_disposition");
                if (disposition.equals("aggro"))
                {
                    dictionary enemies = new dictionary();
                    enemies.put("npc", extra);
                    enemies.put("player", self);
                    messageTo(self, "makeEnemyAttack", enemies, 3, true);
                }
            }
        }
        if (spawn2 != null && !spawn1.equals("none"))
        {
            here.x = here.x + rand(3, 10);
            here.z = here.z + rand(3, 10);
            obj_id extra2 = create.object(spawn2, here);
            if (extra2 != null && isIdValid(extra2))
            {
                String disposition2 = dataTableGetString(datatable, questNum, "extra_npc2_disposition");
                if (disposition2.equals("aggro"))
                {
                    startCombat(extra2, self);
                }
            }
        }
        if (spawn3 != null && !spawn1.equals("none"))
        {
            here.x = here.x + rand(3, 10);
            here.z = here.z + rand(3, 10);
            obj_id extra3 = create.object(spawn3, here);
            if (extra3 != null && isIdValid(extra3))
            {
                String disposition3 = dataTableGetString(datatable, questNum, "extra_npc3_disposition");
                if (disposition3.equals("aggro"))
                {
                    startCombat(extra3, self);
                }
            }
        }
        if (spawn4 != null && !spawn1.equals("none"))
        {
            here.x = here.x + rand(3, 10);
            here.z = here.z + rand(3, 10);
            obj_id extra4 = create.object(spawn4, here);
            if (extra4 != null && isIdValid(extra4))
            {
                String disposition4 = dataTableGetString(datatable, questNum, "extra_npc4_disposition");
                if (disposition4.equals("aggro"))
                {
                    startCombat(extra4, self);
                }
            }
        }
        return;
    }
    public int removeImperialQuestInfo(obj_id self, dictionary params) throws InterruptedException
    {
        String questID = params.getString("questID");
        if (questID == null)
        {
            questID = "none";
        }
        int questNum = getIntObjVar(self, "questNum");
        String datatable = getStringObjVar(self, "quest_table");
        String gatingString = dataTableGetString(datatable, questNum, "overall_objvar");
        String selfScript = dataTableGetString(datatable, questNum, "player_script");
        String type = dataTableGetString(datatable, questNum, "quest_type");
        obj_id waypoint = getObjIdObjVar(self, questID + ".waypoint");
        if (waypoint != null)
        {
            destroyObject(waypoint);
        }
        if (hasObjVar(self, questID + ".vip"))
        {
            return SCRIPT_CONTINUE;
        }
        else 
        {
            if (type.equals("rescue") || type.equals("escort") || type.equals("arrest"))
            {
                obj_id vip = getObjIdObjVar(self, questID + ".vip");
                messageTo(vip, "stopFollowing", null, 1, true);
            }
            removeObjVar(self, questID);
            removeObjVar(self, "quest_table");
            removeObjVar(self, "questNum");
            removeObjVar(self, "boss");
            if (hasScript(self, selfScript))
            {
                detachScript(self, selfScript);
            }
            return SCRIPT_CONTINUE;
        }
    }
    public int makeEnemyAttack(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id npc = params.getObjId("npc");
        obj_id player = params.getObjId("player");
        startCombat(npc, player);
        return SCRIPT_CONTINUE;
    }
    public int OnWaypointGetAttributes(obj_id self, obj_id waypoint, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        String questId = getStringObjVar(player, "questID");
        String file = getStringObjVar(player, questId + ".file");
        String entry = getStringObjVar(player, questId + ".entry");
        obj_id wp = getObjIdObjVar(player, questId + ".waypoint");
        obj_id wph = getObjIdObjVar(player, questId + ".waypointhome");
        if (waypoint != wp && waypoint != wph)
        {
            return SCRIPT_OVERRIDE;
        }
        string_id detail = new string_id(file, entry);
        String questDetails = "@" + detail.toString();
        int idx = 0;
        while (idx >= 0)
        {
            String currentName = names[idx];
            if (names[idx] == null)
            {
                names[idx] = "quest_details";
                attribs[idx] = questDetails;
                idx = -1;
            }
            else 
            {
                idx = idx + 1;
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnWaypointDestroyed(obj_id self, obj_id waypoint) throws InterruptedException
    {
        String questID = getStringObjVar(self, "questID");
        if (questID == null)
        {
            return SCRIPT_OVERRIDE;
        }
        else 
        {
            int stringCheck = questID.indexOf("emperors_retreat");
            if (stringCheck > -1)
            {
                dictionary questBook = new dictionary();
                questBook.put("questID", questID);
                messageTo(self, "removeImperialQuestInfo", questBook, 2, true);
                return SCRIPT_CONTINUE;
            }
            else 
            {
                return SCRIPT_OVERRIDE;
            }
        }
    }
}
