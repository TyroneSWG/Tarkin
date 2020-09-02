package script.quest.task.fs_quest_sad;

import script.dictionary;
import script.library.*;
import script.location;
import script.obj_id;

public class basic_task extends script.base_script
{
    public basic_task()
    {
    }
    public static final String FS_QUEST_SAD_QUANTITY_KILLABLE = "fs_quest_sad.quantity_killable";
    public static final String FS_QUEST_SAD_THEATER_LOCATION = "fs_quest_sad.theater_location";
    public static final location FS_VILLAGE_LOCATION = new location(5238.26f, 78.5f, -4189.03f);
    public void completeTask(obj_id self, String questName, boolean succeeded) throws InterruptedException
    {
        int killable = getIntObjVar(self, FS_QUEST_SAD_QUANTITY_KILLABLE);
        killable = 0;
        setObjVar(self, FS_QUEST_SAD_QUANTITY_KILLABLE, killable);
        geiger.clearGeiger(self);
        if (hasObjVar(self, "quest." + questName))
        {
            obj_id waypoint = getObjIdObjVar(self, "quest." + questName + ".waypoint");
            if (isIdValid(waypoint))
            {
                destroyWaypointInDatapad(waypoint, self);
            }
        }
        if (hasTheaterAssigned(self))
        {
            unassignTheaterFromPlayer(self);
        }
        if (utils.hasObjVar(self, fs_quests_sad.SAD_OBJVAR_MASTER_THEATER))
        {
            obj_id theater = utils.getObjIdObjVar(self, fs_quests_sad.SAD_OBJVAR_MASTER_THEATER);
            messageTo(theater, "destroySelf", null, 90.0f, false);
        }
        fs_quests_sad.recordTaskSuccess(self);
        LOG("newquests", "fs_quest_sad.basic_task - completing");
        quests.complete(questName, self, succeeded);
    }
    public void setupTheater(obj_id self, int questRow) throws InterruptedException
    {
        if (quests.isMyQuest(questRow, "quest.task.fs_quest_sad.basic_task"))
        {
            String questName = quests.getDataEntry(questRow, "NAME");
            if (questName == null || questName.equals("") || !quests.isActive(questName, self))
            {
                return;
            }
            String target = null;
            target = quests.getDataEntry(questRow, "TARGET");
            if (target == null || target.equals(""))
            {
                LOG("newquests", "fs_quest_sad.basic_task - no target");
                return;
            }
            boolean havePlanet = false;
            String[] tokens = split(target, ':');
            String datatable = null;
            if (tokens.length > 1)
            {
                havePlanet = true;
            }
            else 
            {
                datatable = tokens[0];
            }
            if (havePlanet)
            {
                if ((tokens[0].indexOf('/') <= 0) || (tokens[0].indexOf('\\') <= 0))
                {
                    datatable = tokens[0];
                }
                else 
                {
                    datatable = tokens[1];
                }
            }
            location theaterLoc = null;
            boolean hasLocInfo = false;
            boolean created = false;
            String parameterString = quests.getDataEntry(questRow, "PARAMETER");
            float radius = 0.0f;
            if (parameterString != null && !parameterString.equals(""))
            {
                radius = utils.stringToFloat(parameterString);
            }
            else 
            {
                LOG("newquests", "row has no PARAMETER field");
                radius = 1.0f;
            }
            int attempts = 0;
            float positiveRadius = (radius > 0.0f ? radius : -radius);
            float extension = 0.0f;
            while (theaterLoc == null && attempts < 150)
            {
                extension += 50.0f;
                ++attempts;
                theaterLoc = locations.getRandomGoodLocation(getLocation(self), positiveRadius - 200.0f, positiveRadius + 200.0f + extension, 32.0f);
            }
            if (theaterLoc != null)
            {
                LOG("newquests", "basic_task assigning theater to player");
                unassignTheaterFromPlayer(self);
                created = assignTheaterToPlayer(self, datatable, theaterLoc, "systems.fs_quest.fs_quests_sad.theater_controller", TLT_none);
            }
            else 
            {
                LOG("newquests", "getTheaterLocationTarget failed to return a location, marking the player as a big winner");
                quests.complete(questName, self, true);
            }
            if (created)
            {
                if (radius >= 0.0f)
                {
                    setupWaypoint(self, questRow, theaterLoc);
                }
                else 
                {
                    setupGeiger(self, questRow, theaterLoc);
                }
                utils.setScriptVar(self, FS_QUEST_SAD_THEATER_LOCATION, theaterLoc);
            }
            else 
            {
                setupTheater(self, questRow);
            }
        }
    }
    public void setupGeiger(obj_id self, int questRow, location loc) throws InterruptedException
    {
        sendSystemMessage(self, "Target Moved -- Radiation Sensor Updated", null);
        if (loc != null)
        {
            geiger.setGeiger(self, loc);
        }
    }
    public void setupWaypoint(obj_id self, int questRow, location loc) throws InterruptedException
    {
        String questName = quests.getDataEntry(questRow, "NAME");
        String waypointObjVar = "quest." + questName + ".waypoint";
        sendSystemMessage(self, "Waypoint Updated", null);
        if (hasObjVar(self, waypointObjVar))
        {
            obj_id point = getObjIdObjVar(self, waypointObjVar);
            destroyWaypointInDatapad(point, self);
            removeObjVar(self, waypointObjVar);
        }
        if (loc != null)
        {
            if (questName != null && questName.length() > 0)
            {
                obj_id wp = createWaypointInDatapad(self, loc);
                String summary = quests.getDataEntry(questName, "JOURNAL_ENTRY_SUMMARY");
                if (summary != null && summary.length() > 0)
                {
                    setWaypointName(wp, summary);
                }
                else 
                {
                    setWaypointName(wp, "missing task summary for " + questName);
                }
                setWaypointColor(wp, "yellow");
                setWaypointActive(wp, true);
                setObjVar(self, waypointObjVar, wp);
            }
        }
    }
    public int theaterFailed(obj_id self, dictionary params) throws InterruptedException
    {
        LOG("newquests", "inside theaterFailed");
        if (params == null)
        {
            return SCRIPT_CONTINUE;
        }
        String name = params.getString("name");
        String paramDatatable = params.getString("datatable");
        int rows = dataTableGetNumRows("datatables/player/quests.iff");
        int iter = 0;
        for (iter = 0; iter < rows; ++iter)
        {
            String questName = quests.getDataEntry(iter, "NAME");
            if (quests.isMyQuest(iter, "quest.task.fs_quest_sad.basic_task"))
            {
                if (quests.isActive(questName, self))
                {
                    String target = null;
                    target = quests.getDataEntry(iter, "TARGET");
                    if (target == null || target.equals(""))
                    {
                        LOG("newquests", "fs_quest_sad.basic_task - no target");
                        return SCRIPT_CONTINUE;
                    }
                    String[] tokens = split(target, ':');
                    String datatable = null;
                    datatable = tokens[0];
                    LOG("newquests", "fs_quest_sad.basic_task - quest is active");
                    LOG("newquests", "fs_quest_sad.basic_task, datatable, paramDatatable = '" + datatable + "' , '" + paramDatatable + "'");
                    unassignTheaterFromPlayer(self);
                    location theaterLoc = null;
                    boolean needUpdate = true;
                    if (utils.hasScriptVar(self, FS_QUEST_SAD_THEATER_LOCATION))
                    {
                        theaterLoc = utils.getLocationScriptVar(self, FS_QUEST_SAD_THEATER_LOCATION);
                        needUpdate = false;
                        utils.removeScriptVar(self, FS_QUEST_SAD_THEATER_LOCATION);
                    }
                    if (theaterLoc == null)
                    {
                        float distanceToVillage = (getLocation(self)).distance(FS_VILLAGE_LOCATION);
                        int attempts = 0;
                        float searchDelta = 0;
                        while ((theaterLoc == null) && (attempts < 32))
                        {
                            LOG("newquests", "theater is null, generating random good location");
                            theaterLoc = locations.getRandomGoodLocation(getLocation(self), 92.0f + searchDelta, 160.0f + searchDelta, 32.0f);
                            if (theaterLoc != null)
                            {
                                float newDistance = theaterLoc.distance(FS_VILLAGE_LOCATION);
                                if (newDistance < distanceToVillage)
                                {
                                    theaterLoc = null;
                                }
                                else if (!theater.canSpawnTheaterAtLocation(theaterLoc))
                                {
                                    theaterLoc = null;
                                    searchDelta += 256.0f;
                                }
                            }
                            attempts++;
                        }
                    }
                    if (theaterLoc == null)
                    {
                        float distanceToVillage = (getLocation(self)).distance(FS_VILLAGE_LOCATION);
                        LOG("newquests", "OnPlayerTheaterFail tried to getRandomGoodLocation and failed, moving directly away");
                        theaterLoc = locations.moveLocationTowardsLocation(FS_VILLAGE_LOCATION, getLocation(self), distanceToVillage + 256.0f);
                    }
                    boolean created = assignTheaterToPlayer(self, datatable, theaterLoc, "systems.fs_quest.fs_quests_sad.theater_controller", TLT_flatten);
                    if (needUpdate)
                    {
                        if (utils.hasObjVar(self, geiger.OBJVAR_GEIGER_LOCATION))
                        {
                            LOG("newquests", "updating geiger");
                            setupGeiger(self, iter, theaterLoc);
                        }
                        else 
                        {
                            LOG("newquests", "updating waypoint");
                            setupWaypoint(self, iter, theaterLoc);
                        }
                    }
                    if (created)
                    {
                        LOG("newquests", "OnPlayerTheaterFail created the flattened theater at " + theaterLoc);
                    }
                    else 
                    {
                        LOG("newquests", "OnPlayerTheaterFail couldn't create the flatten theater");
                    }
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnPlayerTheaterFail(obj_id self, String datatable, String name) throws InterruptedException
    {
        LOG("newquests", "fs_quest_sad.basic_task - OnPlayerTheaterFail");
        dictionary params = new dictionary();
        params.put("datatable", datatable);
        params.put("name", name);
        messageTo(self, "theaterFailed", params, 8.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int OnLogin(obj_id self) throws InterruptedException
    {
        LOG("newquests", "fs_quest_sad.basic_task - OnLogin");
        checkForPhaseChange(self);
        int rows = dataTableGetNumRows("datatables/player/quests.iff");
        int iter = 0;
        for (iter = 0; iter < rows; ++iter)
        {
            String questName = quests.getDataEntry(iter, "NAME");
            if (quests.isMyQuest(iter, "quest.task.fs_quest_sad.basic_task"))
            {
                if (quests.isActive(questName, self))
                {
                    LOG("newquests", "fs_quest_sad.basic_task - quest is active");
                    if (hasObjVar(self, FS_QUEST_SAD_QUANTITY_KILLABLE))
                    {
                        LOG("newquests", "fs_quest_sad.basic_task - removing objvar");
                        removeObjVar(self, FS_QUEST_SAD_QUANTITY_KILLABLE);
                    }
                    if (hasObjVar(self, "quest." + questName + ".generate"))
                    {
                        removeObjVar(self, "quest." + questName + ".generate");
                    }
                    LOG("newquests", "fs_quest_sad.basic_task - setting up theater");
                    setupTheater(self, iter);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnQuestActivated(obj_id self, int questRow) throws InterruptedException
    {
        LOG("newquests", "fs_quest_sad.basic_task - OnQuestActivated(+ " + questRow + ")");
        if (quests.isMyQuest(questRow, "quest.task.fs_quest_sad.basic_task"))
        {
            String questName = quests.getDataEntry(questRow, "NAME");
            boolean success = true;
            setupTheater(self, questRow);
            fs_quests_sad.recordTookTask(self);
            LOG("newquests", "fs_quest_sad.basic_task - success = " + success);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnCreatedKillableObject(obj_id self, dictionary params) throws InterruptedException
    {
        LOG("newquests", "fs_quest_sad.basic_task - OnCreatedKillableObject");
        int quantityKillable = utils.getIntObjVar(self, FS_QUEST_SAD_QUANTITY_KILLABLE);
        quantityKillable++;
        utils.setObjVar(self, FS_QUEST_SAD_QUANTITY_KILLABLE, quantityKillable);
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitatedKillableObject(obj_id self, dictionary params) throws InterruptedException
    {
        LOG("newquests", "fs_quest_sad.basic_task - OnIncapacitatedKillableObject");
        int quantityKillable = utils.getIntObjVar(self, FS_QUEST_SAD_QUANTITY_KILLABLE);
        quantityKillable--;
        if (quantityKillable < 0)
        {
            quantityKillable = 0;
        }
        utils.setObjVar(self, FS_QUEST_SAD_QUANTITY_KILLABLE, quantityKillable);
        if (quantityKillable <= 1)
        {
            quantityKillable = 0;
            setObjVar(self, FS_QUEST_SAD_QUANTITY_KILLABLE, quantityKillable);
            int rows = dataTableGetNumRows("datatables/player/quests.iff");
            int iter = 0;
            for (iter = 0; iter < rows; ++iter)
            {
                String questName = quests.getDataEntry(iter, "NAME");
                if (quests.isMyQuest(iter, "quest.task.fs_quest_sad.basic_task"))
                {
                    if (quests.isActive(questName, self))
                    {
                        completeTask(self, questName, true);
                    }
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
    public void checkForPhaseChange(obj_id self) throws InterruptedException
    {
        fs_dyn_village.getRegisteredIntegerFromClusterWideData(fs_dyn_village.CLUSTER_INT_KEY_PHASE_UID, "findCurrentPhase", self);
        return;
    }
    public int findCurrentPhase(obj_id self, dictionary params) throws InterruptedException
    {
        if (!params.containsKey(fs_dyn_village.CLUSTER_INT_KEY_PHASE_UID))
        {
            LOG(fs_dyn_village.LOG_CHAN, "fs_quest_player::msgValidateFSQuestPhase: -> Error getting current phase from cluster data.  Potentially on wrong quest phase.");
            return SCRIPT_CONTINUE;
        }
        int curUid = params.getInt(fs_dyn_village.CLUSTER_INT_KEY_PHASE_UID);
        int curPhase = fs_dyn_village.getPhaseFromVersionUid(curUid);
        String wpName = "waypoint";
        setObjVar(self, "current_phase", curPhase);
        int phase = getIntObjVar(self, "current_phase");
        String returnQuests = "fs_quests_sad_return_1";
        for (int i = 1; i <= 8; i++)
        {
            if (quests.isActive("fs_quests_sad_return_" + i, self))
            {
                returnQuests = "fs_quests_sad_return_" + i;
                wpName = "quest." + returnQuests + ".waypoint";
                if (hasObjVar(self, wpName))
                {
                    if (phase != 2)
                    {
                        obj_id point = getObjIdObjVar(self, wpName);
                        destroyWaypointInDatapad(point, self);
                        removeObjVar(self, wpName);
                        removeObjVar(self, "current_phase");
                    }
                }
            }
        }
        String taskQuests = "fs_quests_sad_task_1";
        for (int x = 1; x <= 8; x++)
        {
            if (quests.isActive("fs_quests_sad_task_" + x, self))
            {
                taskQuests = "fs_quests_sad_task_" + x;
                wpName = "quest." + taskQuests + ".waypoint";
                if (hasObjVar(self, wpName))
                {
                    if (phase != 2)
                    {
                        obj_id point = getObjIdObjVar(self, wpName);
                        destroyWaypointInDatapad(point, self);
                        removeObjVar(self, wpName);
                        removeObjVar(self, "current_phase");
                    }
                }
            }
        }
        String returnQuests2 = "fs_quests_sad2_return_1";
        for (int a = 1; a <= 8; a++)
        {
            if (quests.isActive("fs_quests_sad2_return_" + a, self))
            {
                returnQuests2 = "fs_quests_sad2_return_" + a;
                wpName = "quest." + returnQuests2 + ".waypoint";
                if (hasObjVar(self, wpName))
                {
                    if (phase != 3)
                    {
                        obj_id point = getObjIdObjVar(self, wpName);
                        destroyWaypointInDatapad(point, self);
                        removeObjVar(self, wpName);
                        removeObjVar(self, "current_phase");
                    }
                }
            }
        }
        String taskQuests2 = "fs_quests_sad2_task_1";
        for (int z = 1; z <= 8; z++)
        {
            if (quests.isActive("fs_quests_sad2_task_" + z, self))
            {
                taskQuests2 = "fs_quests_sad2_task_" + z;
                wpName = "quest." + taskQuests2 + ".waypoint";
                if (hasObjVar(self, wpName))
                {
                    if (phase != 3)
                    {
                        obj_id point = getObjIdObjVar(self, wpName);
                        destroyWaypointInDatapad(point, self);
                        removeObjVar(self, wpName);
                        removeObjVar(self, "current_phase");
                    }
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
