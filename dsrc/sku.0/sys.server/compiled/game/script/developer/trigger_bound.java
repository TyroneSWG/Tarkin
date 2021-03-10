package script.developer;

import script.*;
import script.library.factions;
/**
 *
 * @author skyyyr's java slave
 */
public class trigger_bound extends script.base_script {
    public trigger_bound() {
    }
    public static String TRIGGER_VOLUME_NAME = "boundary_test";
    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id breacher) throws InterruptedException {
        if (isMob(breacher)) {
            return SCRIPT_CONTINUE;
        }
        if (!volumeName.equals(TRIGGER_VOLUME_NAME))
        {
            return SCRIPT_CONTINUE;
        }
        if (isAllowedIn(self))
	{
            sendSystemMessageTestingOnly(breacher, "test: you are allowed in");
	}
	else 
	{
            warpToSafeZone(self);
            sendSystemMessageTestingOnly(breacher, "test: you cannot come in you scrub");
	}
        return SCRIPT_OVERRIDE;
    }
    public int OnTriggerVolumeExited(obj_id self, String triggerVolumeName, obj_id breacher) throws InterruptedException {
        return SCRIPT_OVERRIDE;
    }
    public boolean isAllowedIn(obj_id self) throws InterruptedException
    {
        //This is where the checks would be. Any boolean can be used. If you want to check for an int, i'd make a new function and compare it with objvars.
        //return hasSkill(self, "some_skill");
        return factions.isActiveImperial(self);
        //return factions.isActiveRebel(self);       
    }
    public void warpToSafeZone(obj_id self) throws InterruptedException
    {
        //this is a WIP, but to get the location of the "middle" you will need to have it "scan" for a spawnegg with the objvar below in the script. This script attaches to the player.
        obj_id[] ORIGIN_OBJECT = getAllObjectsWithObjVar(getLocation(self), 1600f, "arena_trigger_base");
        location rubberband = getLocation(ORIGIN_OBJECT[1]);
        //for futher warping, add in the radius by getting the objvar of the origin object. then replace rubberband.x with rubberband.x - XXX (x being the radius on both x and y
        warpPlayer(self, getCurrentSceneName(), rubberband.x, rubberband.y, rubberband.z, null, 0.0f, 0.0f, 0.0f);
    }
}
