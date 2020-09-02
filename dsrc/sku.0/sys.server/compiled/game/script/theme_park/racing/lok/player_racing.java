package script.theme_park.racing.lok;

import script.dictionary;
import script.library.colors;
import script.library.utils;
import script.location;
import script.obj_id;
import script.string_id;

public class player_racing extends script.base_script
{
    public player_racing()
    {
    }
    public static final String RACING_STF = "theme_park/racing/racing";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        startRace(self);
        setObjVar(self, "racing.lok.suppressMusic", 0);
        setObjVar(self, "racing.lok.currentMusic", 1);
        return SCRIPT_CONTINUE;
    }
    public int OnLogin(obj_id self) throws InterruptedException
    {
        messageTo(self, "handleCleanUp", null, 1, false);
        return SCRIPT_CONTINUE;
    }
    public int OnEnteredCombat(obj_id self) throws InterruptedException
    {
        setObjVar(self, "racing.lok.suppressMusic", 1);
        playMusic(self, "sound/music_combat_bfield_def.snd");
        return SCRIPT_CONTINUE;
    }
    public int OnExitedCombat(obj_id self) throws InterruptedException
    {
        setObjVar(self, "racing.lok.suppressMusic", 0);
        messageTo(self, "playRaceMusic", null, 0, false);
        return SCRIPT_CONTINUE;
    }
    public int OnArrivedAtLocation(obj_id self, String name) throws InterruptedException
    {
        float startTime = getFloatObjVar(self, "racing.lok.startTime");
        float deltaTime = getGameTime();
        float lapTime = (deltaTime - startTime);
        float currentTime = getFloatObjVar(self, "racing.lok.currentTime");
        string_id waypointName = new string_id(RACING_STF, "lok_waypoint_name_" + name);
        string_id waypointDesc = new string_id(RACING_STF, "waypoint_desc_1");
        string_id checkPointMessage = new string_id(RACING_STF, "lok_laptime_" + name);
        string_id finishMessage = new string_id(RACING_STF, "finish_message");
        string_id waypointHalfFly = new string_id(RACING_STF, "waypoint_half_fly");
        string_id waypointThreeToGo = new string_id(RACING_STF, "waypoint_3_to_go_fly");
        string_id waypointFinalLapFly = new string_id(RACING_STF, "final_lap_fly");
        if (name.equals("checkpoint1"))
        {
            setObjVar(self, "racing.lok.currentTime", lapTime);
            removeMissionWaypoint(self);
            removeLocationTarget(name);
            location nextLocation = getLocationObjVar(self, "racing.lok.checkpoint2");
            obj_id waypoint = createMissionWaypoint(self, nextLocation, 30, getString(waypointName), "checkpoint2");
            sendSystemMessage(self, checkPointMessage);
            sendSystemMessage(self, "Time: " + lapTime, null);
        }
        if (name.equals("checkpoint2"))
        {
            setObjVar(self, "racing.lok.currentTime", lapTime);
            removeMissionWaypoint(self);
            removeLocationTarget(name);
            location nextLocation = getLocationObjVar(self, "racing.lok.checkpoint3");
            obj_id waypoint = createMissionWaypoint(self, nextLocation, 5, getString(waypointName), "checkpoint3");
            sendSystemMessage(self, checkPointMessage);
            sendSystemMessage(self, "Time: " + lapTime, null);
            setObjVar(self, "racing.lok.currentMusic", 2);
            messageTo(self, "playRaceMusic", null, 35, false);
        }
        if (name.equals("checkpoint3"))
        {
            setObjVar(self, "racing.lok.currentTime", lapTime);
            removeMissionWaypoint(self);
            removeLocationTarget(name);
            location nextLocation = getLocationObjVar(self, "racing.lok.checkpoint4");
            obj_id waypoint = createMissionWaypoint(self, nextLocation, 30, getString(waypointName), "checkpoint4");
            sendSystemMessage(self, checkPointMessage);
            sendSystemMessage(self, "Time: " + lapTime, null);
            setObjVar(self, "racing.lok.currentMusic", 3);
            messageTo(self, "playRaceMusic", null, 0, false);
            messageTo(self, "playRaceMusic", null, 60, false);
            messageTo(self, "playRaceMusic", null, 120, false);
        }
        if (name.equals("checkpoint4"))
        {
            setObjVar(self, "racing.lok.currentTime", lapTime);
            removeMissionWaypoint(self);
            removeLocationTarget(name);
            location nextLocation = getLocationObjVar(self, "racing.lok.checkpoint5");
            obj_id waypoint = createMissionWaypoint(self, nextLocation, 30, getString(waypointName), "checkpoint5");
            sendSystemMessage(self, checkPointMessage);
            sendSystemMessage(self, "Time: " + lapTime, null);
            messageTo(self, "playRaceMusic", null, 0, false);
            messageTo(self, "playRaceMusic", null, 60, false);
        }
        if (name.equals("checkpoint5"))
        {
            setObjVar(self, "racing.lok.currentTime", lapTime);
            removeMissionWaypoint(self);
            removeLocationTarget(name);
            location nextLocation = getLocationObjVar(self, "racing.lok.checkpoint6");
            obj_id waypoint = createMissionWaypoint(self, nextLocation, 30, getString(waypointName), "checkpoint6");
            sendSystemMessage(self, checkPointMessage);
            sendSystemMessage(self, "Time: " + lapTime, null);
            setObjVar(self, "racing.lok.currentMusic", 4);
            messageTo(self, "playRaceMusic", null, 0, false);
        }
        if (name.equals("checkpoint6"))
        {
            setObjVar(self, "racing.lok.currentTime", lapTime);
            removeMissionWaypoint(self);
            removeLocationTarget(name);
            location nextLocation = getLocationObjVar(self, "racing.lok.checkpoint7");
            obj_id waypoint = createMissionWaypoint(self, nextLocation, 15, getString(waypointName), "checkpoint7");
            sendSystemMessage(self, checkPointMessage);
            sendSystemMessage(self, "Time: " + lapTime, null);
            setObjVar(self, "racing.lok.currentMusic", 1);
            messageTo(self, "playRaceMusic", null, 0, false);
        }
        if (name.equals("checkpoint7"))
        {
            setObjVar(self, "racing.lok.currentTime", lapTime);
            removeMissionWaypoint(self);
            removeLocationTarget(name);
            location nextLocation = getLocationObjVar(self, "racing.lok.checkpoint8");
            obj_id waypoint = createMissionWaypoint(self, nextLocation, 15, getString(waypointName), "checkpoint8");
            sendSystemMessage(self, checkPointMessage);
            sendSystemMessage(self, "Time: " + lapTime, null);
        }
        if (name.equals("checkpoint8"))
        {
            setObjVar(self, "racing.lok.currentTime", lapTime);
            removeMissionWaypoint(self);
            removeLocationTarget(name);
            location nextLocation = getLocationObjVar(self, "racing.lok.checkpoint9");
            obj_id waypoint = createMissionWaypoint(self, nextLocation, 15, getString(waypointName), "checkpoint9");
            sendSystemMessage(self, checkPointMessage);
            sendSystemMessage(self, "Time: " + lapTime, null);
            showFlyText(self, waypointHalfFly, 1.5f, colors.RED);
        }
        if (name.equals("checkpoint9"))
        {
            setObjVar(self, "racing.lok.currentTime", lapTime);
            removeMissionWaypoint(self);
            removeLocationTarget(name);
            location nextLocation = getLocationObjVar(self, "racing.lok.checkpoint10");
            obj_id waypoint = createMissionWaypoint(self, nextLocation, 30, getString(waypointName), "checkpoint10");
            sendSystemMessage(self, checkPointMessage);
            sendSystemMessage(self, "Time: " + lapTime, null);
            messageTo(self, "playRaceMusic", null, 0, false);
        }
        if (name.equals("checkpoint10"))
        {
            setObjVar(self, "racing.lok.currentTime", lapTime);
            removeMissionWaypoint(self);
            removeLocationTarget(name);
            location nextLocation = getLocationObjVar(self, "racing.lok.checkpoint11");
            obj_id waypoint = createMissionWaypoint(self, nextLocation, 30, getString(waypointName), "checkpoint11");
            sendSystemMessage(self, checkPointMessage);
            sendSystemMessage(self, "Time: " + lapTime, null);
            setObjVar(self, "racing.lok.currentMusic", 2);
            messageTo(self, "playRaceMusic", null, 0, false);
        }
        if (name.equals("checkpoint11"))
        {
            setObjVar(self, "racing.lok.currentTime", lapTime);
            removeMissionWaypoint(self);
            removeLocationTarget(name);
            location nextLocation = getLocationObjVar(self, "racing.lok.checkpoint12");
            obj_id waypoint = createMissionWaypoint(self, nextLocation, 30, getString(waypointName), "checkpoint12");
            sendSystemMessage(self, checkPointMessage);
            sendSystemMessage(self, "Time: " + lapTime, null);
            messageTo(self, "playRaceMusic", null, 0, false);
        }
        if (name.equals("checkpoint12"))
        {
            setObjVar(self, "racing.lok.currentTime", lapTime);
            removeMissionWaypoint(self);
            removeLocationTarget(name);
            location nextLocation = getLocationObjVar(self, "racing.lok.checkpoint13");
            obj_id waypoint = createMissionWaypoint(self, nextLocation, 30, getString(waypointName), "checkpoint13");
            sendSystemMessage(self, checkPointMessage);
            sendSystemMessage(self, "Time: " + lapTime, null);
            setObjVar(self, "racing.lok.currentMusic", 3);
            messageTo(self, "playRaceMusic", null, 0, false);
            messageTo(self, "playRaceMusic", null, 60, false);
        }
        if (name.equals("checkpoint13"))
        {
            setObjVar(self, "racing.lok.currentTime", lapTime);
            removeMissionWaypoint(self);
            removeLocationTarget(name);
            location nextLocation = getLocationObjVar(self, "racing.lok.checkpoint14");
            obj_id waypoint = createMissionWaypoint(self, nextLocation, 30, getString(waypointName), "checkpoint14");
            sendSystemMessage(self, checkPointMessage);
            sendSystemMessage(self, "Time: " + lapTime, null);
            messageTo(self, "playRaceMusic", null, 0, false);
            messageTo(self, "playRaceMusic", null, 60, false);
            messageTo(self, "playRaceMusic", null, 120, false);
        }
        if (name.equals("checkpoint14"))
        {
            setObjVar(self, "racing.lok.currentTime", lapTime);
            removeMissionWaypoint(self);
            removeLocationTarget(name);
            location nextLocation = getLocationObjVar(self, "racing.lok.checkpoint15");
            obj_id waypoint = createMissionWaypoint(self, nextLocation, 30, getString(waypointName), "checkpoint15");
            sendSystemMessage(self, checkPointMessage);
            sendSystemMessage(self, "Time: " + lapTime, null);
            showFlyText(self, waypointThreeToGo, 1.5f, colors.RED);
            messageTo(self, "playRaceMusic", null, 0, false);
            messageTo(self, "playRaceMusic", null, 60, false);
        }
        if (name.equals("checkpoint15"))
        {
            setObjVar(self, "racing.lok.currentTime", lapTime);
            removeMissionWaypoint(self);
            removeLocationTarget(name);
            location nextLocation = getLocationObjVar(self, "racing.lok.checkpoint16");
            obj_id waypoint = createMissionWaypoint(self, nextLocation, 30, getString(waypointName), "checkpoint16");
            sendSystemMessage(self, checkPointMessage);
            sendSystemMessage(self, "Time: " + lapTime, null);
            setObjVar(self, "racing.lok.currentMusic", 1);
            messageTo(self, "playRaceMusic", null, 0, false);
        }
        if (name.equals("checkpoint16"))
        {
            setObjVar(self, "racing.lok.currentTime", lapTime);
            removeMissionWaypoint(self);
            removeLocationTarget(name);
            location nextLocation = getLocationObjVar(self, "racing.lok.checkpoint17");
            obj_id waypoint = createMissionWaypoint(self, nextLocation, 30, getString(waypointName), "checkpoint17");
            sendSystemMessage(self, checkPointMessage);
            sendSystemMessage(self, "Time: " + lapTime, null);
            messageTo(self, "playRaceMusic", null, 0, false);
        }
        if (name.equals("checkpoint17"))
        {
            setObjVar(self, "racing.lok.currentTime", lapTime);
            removeMissionWaypoint(self);
            removeLocationTarget(name);
            location nextLocation = getLocationObjVar(self, "racing.lok.returnLoc");
            obj_id waypoint = createMissionWaypoint(self, nextLocation, 15, getString(waypointName), "returndest");
            sendSystemMessage(self, checkPointMessage);
            sendSystemMessage(self, "Time: " + lapTime, null);
            showFlyText(self, waypointFinalLapFly, 1.5f, colors.RED);
            messageTo(self, "playRaceMusic", null, 0, false);
        }
        if (name.equals("returndest"))
        {
            setObjVar(self, "racing.lok.currentTime", lapTime);
            setObjVar(self, "racing.lok.success", 1);
            removeMissionWaypoint(self);
            removeLocationTarget(name);
            sendSystemMessage(self, finishMessage);
            sendSystemMessage(self, "Total Race Time: " + lapTime, null);
            messageTo(self, "handleBestTime", null, 1, false);
            messageTo(self, "handleCleanUp", null, 1, false);
            removeObjVar(self, "racing.lok.returnLoc");
            removeObjVar(self, "racing.lok.isRacing");
            playMusic(self, "sound/music_lando_theme.snd");
        }
        return SCRIPT_CONTINUE;
    }
    public int playRaceMusic(obj_id self, dictionary params) throws InterruptedException
    {
        String music = "sound/music_combat_bfield_lp.snd";
        int suppressMusic = getIntObjVar(self, "racing.lok.suppressMusic");
        int currentMusic = getIntObjVar(self, "racing.lok.currentMusic");
        if (suppressMusic == 0)
        {
            if (currentMusic == 1)
            {
                music = "sound/music_combat_bfield_lp.snd";
            }
            if (currentMusic == 2)
            {
                music = "sound/music_autorun_lp.snd";
            }
            if (currentMusic == 3)
            {
                music = "sound/music_explore.snd";
            }
            if (currentMusic == 4)
            {
                music = "sound/music_amb_underwater_b.snd";
            }
            playMusic(self, music);
        }
        return SCRIPT_CONTINUE;
    }
    public int handleCleanUp(obj_id self, dictionary params) throws InterruptedException
    {
        removeMissionWaypoint(self);
        removeObjVar(self, "racing.lok");
        detachScript(self, "theme_park.racing.lok.player_racing");
        return SCRIPT_CONTINUE;
    }
    public int handleBestTime(obj_id self, dictionary params) throws InterruptedException
    {
        string_id newRecord = new string_id(RACING_STF, "new_record");
        float currentTime = getFloatObjVar(self, "racing.lok.currentTime");
        float bestTime = getFloatObjVar(self, "racing.bestTime.lok");
        setObjVar(self, "racing.lastTime.lok", currentTime);
        if (currentTime < bestTime)
        {
            setObjVar(self, "racing.bestTime.lok", currentTime);
            showFlyText(self, newRecord, 1.5f, colors.GREENYELLOW);
        }
        return SCRIPT_CONTINUE;
    }
    public int handleRestartMission(obj_id self, dictionary params) throws InterruptedException
    {
        setObjVar(self, "racing.lok.success", 1);
        removeMissionWaypoint(self);
        startRace(self);
        return SCRIPT_CONTINUE;
    }
    public int createReturnMission(obj_id self, dictionary params) throws InterruptedException
    {
        if (hasObjVar(self, "racing.lok.waypoint"))
        {
            removeMissionWaypoint(self);
        }
        int missionNum = getIntObjVar(self, "racing.lok.missionNum");
        location returnLoc = getLocationObjVar(self, "racing.lok.returnLoc");
        string_id waypointName = new string_id(RACING_STF, "waypoint_name_returndest");
        string_id waypointDesc = new string_id(RACING_STF, "waypoint_return_desc_1");
        obj_id waypoint = createMissionWaypoint(self, returnLoc, 20, getString(waypointName), "returndest");
        if (hasObjVar(self, "racing.lok.finalMission"))
        {
            removeObjVar(self, "race");
        }
        return SCRIPT_CONTINUE;
    }
    public void startRace(obj_id player) throws InterruptedException
    {
        int missionNum = getIntObjVar(player, "racing.lok.missionNum");
        location checkPointOne = getLocationObjVar(player, "racing.lok.checkpoint1");
        string_id waypointName = new string_id(RACING_STF, "lok_waypoint_name_checkpoint0");
        string_id waypointDesc = new string_id(RACING_STF, "waypoint_desc_1");
        string_id goFly = new string_id(RACING_STF, "go_fly");
        obj_id waypoint = createMissionWaypoint(player, checkPointOne, 30, getString(waypointName), "checkpoint1");
        showFlyText(player, goFly, 1.5f, colors.BLUE);
        playMusic(player, "sound/music_combat_bfield_lp.snd");
        if (isIdValid(waypoint))
        {
            setObjVar(player, "racing.lok.waypointDesc", waypointDesc);
            addLocationTarget("checkPointOne", checkPointOne, 100);
        }
        else 
        {
            string_id error = new string_id(RACING_STF, "waypoint_failure");
            sendSystemMessage(player, error);
        }
    }
    public obj_id createMissionWaypoint(obj_id player, location loc, float radius, String waypointName, String volumeName) throws InterruptedException
    {
        addLocationTarget(volumeName, loc, radius);
        obj_id waypoint = createWaypointInDatapad(player, loc);
        if (isIdValid(waypoint))
        {
            setWaypointActive(waypoint, true);
            setWaypointName(waypoint, waypointName);
            setObjVar(player, "racing.lok.waypoint", waypoint);
            setObjVar(player, "racing.lok.volume", volumeName);
        }
        return waypoint;
    }
    public void removeMissionWaypoint(obj_id player) throws InterruptedException
    {
        obj_id waypoint = getObjIdObjVar(player, "racing.lok.waypoint");
        if (hasObjVar(player, "racing.lok.volume"))
        {
            String name = getStringObjVar(player, "racing.lok.volume");
            removeLocationTarget(name);
            if (name.equals("missionDest"))
            {
                removeLocationTarget("missionSpawner");
            }
            removeObjVar(player, "racing.lok.volume");
        }
        setWaypointVisible(waypoint, false);
        setWaypointActive(waypoint, false);
        destroyWaypointInDatapad(waypoint, player);
        removeObjVar(player, "racing.lok.waypoint");
    }
    public int OnWaypointGetAttributes(obj_id self, obj_id waypoint, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        obj_id wp = getObjIdObjVar(player, "racing.lok.waypoint");
        if (waypoint != wp)
        {
            return SCRIPT_CONTINUE;
        }
        int idx = utils.getValidAttributeIndex(names);
        if (idx == -1)
        {
            return SCRIPT_CONTINUE;
        }
        string_id waypointDesc = getStringIdObjVar(player, "racing.lok.waypointDesc");
        names[idx] = "mission_details";
        attribs[idx] = "@" + waypointDesc.toString();
        return SCRIPT_CONTINUE;
    }
}
