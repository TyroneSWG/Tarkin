package script.systems.missions.dynamic;

import script.dictionary;
import script.location;
import script.obj_id;
import script.string_id;

public class mission_assasin extends script.systems.missions.base.mission_dynamic_base
{
    public mission_assasin()
    {
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int onArrivedAtLocation(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id objPlayer = getMissionHolder(self);
        dictionary dctParams = new dictionary();
        dctParams.put("objMission", self);
        messageTo(objPlayer, "assassin_Arrival", dctParams, 0, true);
        return SCRIPT_CONTINUE;
    }
    public int abortMission(obj_id self, dictionary params) throws InterruptedException
    {
        LOG("mission", "ABORT MISSION RECEIVED!");
        sendAssassinIncomplete(self);
        return SCRIPT_CONTINUE;
    }
    public int OnStartMission(obj_id self, dictionary params) throws InterruptedException
    {
        LOG("mission", "Received start mission message");
        String strContext;
        obj_id objMissionData = getMissionData(self);
        LOG("mission", "objMissionData is " + objMissionData);
        obj_id objPlayer = getMissionHolder(self);
        LOG("mission", "Right above AttachScript");
        LOG("mission", "Right above setupSpawn");
        setupSpawn(self, getMissionStartLocation(objMissionData));
        location locSpawnLocation = getMissionStartLocation(objMissionData);
        if (locSpawnLocation == null)
        {
            LOG("mission", "locSpawnLocaiton is NULL");
            return SCRIPT_CONTINUE;
        }
        updateMissionWaypoint(self, locSpawnLocation);
        dictionary dctParams = new dictionary();
        return SCRIPT_CONTINUE;
    }
    public int assassinIncomplete(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id objPlayer = getMissionHolder(self);
        string_id strMessage = new string_id(GENERIC_MISSION_MESSAGE_STRING_FILE, "incomplete");
        sendSystemMessage(objPlayer, strMessage);
        endMission(self);
        return SCRIPT_CONTINUE;
    }
    public int assassinFailure(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id objPlayer = getMissionHolder(self);
        string_id strMessage = new string_id(GENERIC_MISSION_MESSAGE_STRING_FILE, "failed");
        sendSystemMessage(objPlayer, strMessage);
        String strTitleString = MISSION_FAILURE_PERSISTENT_MESSAGE;
        obj_id objMissionData = getMissionData(self);
        int intStringId = getIntObjVar(objMissionData, "intStringId");
        String strMessageString = "m" + intStringId + "d";
        endMission(self);
        return SCRIPT_CONTINUE;
    }
    public int assassinSuccess(obj_id self, dictionary params) throws InterruptedException
    {
        if (!hasObjVar(self, "intFinished"))
        {
            setObjVar(self, "intFinished", 1);
            obj_id objMissionData = getMissionData(self);
            if (hasObjVar(objMissionData, "locCreatorLocation"))
            {
                setObjVar(self, "intNPC", 1);
                setObjVar(self, "intMissionComplete", 1);
                location locCreatorLocation = getLocationObjVar(objMissionData, "locCreatorLocation");
                removeObjVar(objMissionData, "locCreatorLocation");
                updateMissionWaypoint(self, locCreatorLocation);
                string_id strSpam = new string_id("mission/mission_generic", "destroy_return");
                sendSystemMessage(getMissionHolder(self), strSpam);
                return SCRIPT_CONTINUE;
            }
            if (!hasObjVar(self, "intNPC"))
            {
            }
            deliverReward(self);
            obj_id objPlayer = getMissionHolder(self);
            playMusic(objPlayer, "sound/music_mission_complete.snd");
            endMission(self);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnEndMission(obj_id self, dictionary params) throws InterruptedException
    {
        cleanupLocationTargets(self);
        cleanupObjects(self);
        return SCRIPT_CONTINUE;
    }
}
