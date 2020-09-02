package script.theme_park.dungeon;

import script.dictionary;
import script.library.locations;
import script.library.space_dungeon;
import script.library.utils;
import script.location;
import script.obj_id;
import script.string_id;

public class space_dungeon_controller extends script.base_script
{
    public space_dungeon_controller()
    {
    }
    public static final string_id SID_SESSION_UPDATE = new string_id("dungeon/space_dungeon", "session_time_update");
    public static final boolean LOGGING = true;
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        space_dungeon.intializeSpaceDungeon(self);
        if (!hasObjVar(self, "isControllerObject"))
        {
            setObjVar(self, "isControllerObject", 1);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnClusterWideDataResponse(obj_id self, String manage_name, String dungeon_name, int request_id, String[] element_name_list, dictionary[] dungeon_data, int lock_key) throws InterruptedException
    {
        String instanceName = space_dungeon.getDungeonInstanceName(self);
        String scene = getCurrentSceneName();
        if (dungeon_data != null && dungeon_data.length == 1 && dungeon_data[0].containsKey("scene"))
        {
            String oldScene = dungeon_data[0].getString("scene");
            if (oldScene != null && scene != null && !oldScene.equals(scene))
            {
                String name = space_dungeon.getDungeonName(self);
                LOG("space_dungeon", "space_dungeon_controller.OnClusterWideDataResponse -- !!! scene has changed from " + oldScene + " to " + scene + "! This is very bad! Somehow this space_dungeon has moved planets - expect bad things to happen because of it. In order to lessen the pain, this instance (" + manage_name + ":" + dungeon_name + ") will die an honorable death and kill itself now");
                removeClusterWideData(manage_name, instanceName, lock_key);
                releaseClusterWideDataLock(manage_name, lock_key);
                destroyObject(self);
                return SCRIPT_CONTINUE;
            }
        }
        dictionary dungeon_info = new dictionary();
        location loc = getLocation(self);
        dungeon_info.put("dungeon_id", self);
        dungeon_info.put("scene", scene);
        dungeon_info.put("session_id", -1);
        dungeon_info.put("position_x", loc.x);
        dungeon_info.put("position_y", loc.y);
        dungeon_info.put("position_z", loc.z);
        dungeon_info.put("buildout_area", locations.getBuildoutAreaName(self));
        dungeon_info.put("buildout_row", locations.getBuildoutAreaRow(self));
        LOG("space_dungeon", "dungeon_instance ->" + instanceName);
        replaceClusterWideData(manage_name, instanceName, dungeon_info, true, lock_key);
        releaseClusterWideDataLock(manage_name, lock_key);
        messageTo(self, "msgSpaceDungeonCleanup", null, 1.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int OnBuildoutObjectRegisterWithController(obj_id self, obj_id registeringObject) throws InterruptedException
    {
        if (!hasObjVar(registeringObject, "ignoreInBuildoutArray"))
        {
            space_dungeon.registerObjectWithDungeon(self, registeringObject);
        }
        setObjVar(registeringObject, "dungeonController", self);
        return SCRIPT_CONTINUE;
    }
    public String getDungeonRemainingTimeString(obj_id dungeon) throws InterruptedException
    {
        int currentTime = getGameTime();
        int sessionEnd = getIntObjVar(dungeon, space_dungeon.VAR_DUNGEON_END_TIME);
        int sessionTimeRemaining = sessionEnd - currentTime;
        doLogging("getDungeonRemainingTimeString", "sessionTimeRemaining: " + sessionTimeRemaining);
        if (sessionTimeRemaining < 1)
        {
            doLogging("getDungeonRemainingTimeString", "sessionTimeRemaining less than one, calling End Dungeon Session");
            space_dungeon.endDungeonSession(dungeon);
        }
        return utils.formatTimeVerbose(sessionTimeRemaining);
    }
    public int msgSetSessionId(obj_id self, dictionary params) throws InterruptedException
    {
        int request_id = params.getInt("request_id");
        int session_id = params.getInt("session_id");
        LOG("space_dungeon", "space_dungeon_controller.msgSetSessionId -- " + session_id + " / " + request_id);
        if (session_id < 0)
        {
            LOG("space_dungeon", "space_dungeon_controller.msgSetSessionId -- invalid session_id given for " + self);
            return SCRIPT_CONTINUE;
        }
        setObjVar(self, space_dungeon.VAR_SESSION_ID, session_id);
        if (params.containsKey("quest_type"))
        {
            String quest_type = params.getString("quest_type");
            if (quest_type != null && quest_type.length() > 0)
            {
                setObjVar(self, space_dungeon.VAR_QUEST_TYPE, quest_type);
            }
        }
        space_dungeon.setDungeonTimeObjVars(self);
        obj_id collector = params.getObjId("ticket_collector");
        if (isIdValid(collector))
        {
            dictionary d = new dictionary();
            d.put("dungeon_id", self);
            d.put("dungeon_name", space_dungeon.getDungeonName(self));
            d.put("dungeon_loc", getLocation(self));
            d.put("session_id", session_id);
            d.put("request_id", request_id);
            messageTo(collector, "msgStartDungeonTravel", d, 0.0f, false);
        }
        else 
        {
            LOG("space_dungeon", "space_dungeon_controller.msgSetSessionId -- collector is invalid for session id set on " + self + ".");
        }
        dictionary dict = new dictionary();
        dict.put("sessionId", session_id);
        messageTo(self, "handleSessionTimerUpdate", dict, 0.0f, false);
        messageTo(self, "handleLockoutTimerUpdate", dict, 300.0f, false);
        messageTo(self, "beginSpawn", null, 5.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int handleLockoutTimerUpdate(obj_id self, dictionary params) throws InterruptedException
    {
        space_dungeon.setDungeonLockoutTimer(self);
        return SCRIPT_CONTINUE;
    }
    public int msgManualDungeonReset(obj_id self, dictionary params) throws InterruptedException
    {
        LOG("space_dungeon", "space_dungeon_controller.msgManualDungeonReset received for " + self + ".");
        messageTo(self, "cleanOutDungeon", null, 0.0f, false);
        space_dungeon.endDungeonSession(self);
        return SCRIPT_CONTINUE;
    }
    public int handleSessionTimerUpdate(obj_id self, dictionary params) throws InterruptedException
    {
        int sessionId = space_dungeon.getDungeonSessionId(self);
        int passedSessionId = params.getInt("sessionId");
        doLogging("handleSessionTimerUpdate", "Session ID vs Passed ID: " + sessionId + ", " + passedSessionId);
        if (sessionId == passedSessionId && sessionId != -1)
        {
            String timeRemaining = getDungeonRemainingTimeString(self);
            String message = "This session will end in " + timeRemaining;
            int nextMessage = space_dungeon.calculateNextMessage(self);
            doLogging("handleSessionTimerUpdate", "nextMessage > -1 precheck: " + nextMessage);
            if (nextMessage > 0)
            {
                obj_id[] players = space_dungeon.getPlayersInInstance(self);
                params.put("message", message);
                if (players != null && players.length > 0)
                {
                    doLogging("handleSessionTimerUpdate", "Time remaining: " + timeRemaining + ", players to notify: " + players.length);
                    utils.messageTo(players, "handleInstanceTimeRemainingMessage", params, 0.0f, false);
                }
                dictionary dict = new dictionary();
                dict.put("sessionId", sessionId);
                doLogging("handleSessionTimerUpdate", "Sending next message in (" + nextMessage + ") seconds");
                messageTo(self, "handleSessionTimerUpdate", dict, nextMessage, false);
            }
            else 
            {
                doLogging("handleSessionTimerUpdate", "Next message was not greater than 0, calling for dungeon reset");
                space_dungeon.endDungeonSession(self);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int addPlayerToParticipantIdList(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = params.getObjId(space_dungeon.PLAYER_ID);
        space_dungeon.incrementDungeonParticipantCounter(self, player);
        return SCRIPT_CONTINUE;
    }
    public int removePlayerFromParticipantIdList(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = params.getObjId(space_dungeon.PLAYER_ID);
        space_dungeon.decrementDungeonParticipantCounter(self, player);
        String buildout_area = hasObjVar(self, space_dungeon.VAR_BUILDOUT_AREA) ? getStringObjVar(self, space_dungeon.VAR_BUILDOUT_AREA) : "invalid";
        int buildout_row = hasObjVar(self, space_dungeon.VAR_BUILDOUT_ROW) ? getIntObjVar(self, space_dungeon.VAR_BUILDOUT_ROW) : -1;
        params.put("buildout_row", buildout_row);
        params.put("buildout_area", buildout_area);
        messageTo(player, "removeDungeonArea", params, 0.0f, false);
        return SCRIPT_CONTINUE;
    }
    public void doLogging(String section, String message) throws InterruptedException
    {
        if (LOGGING)
        {
            LOG("logging/space_dungeon_controller/" + section, message);
        }
    }
}
