package script.systems.collections.publish_gift;

import script.dictionary;
import script.location;
import script.obj_id;

public class publish_gift_shuttle extends script.base_script
{
    public publish_gift_shuttle()
    {
    }
    public int startLandingSequence(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id owner = params.getObjId("owner");
        queueCommand(self, (-1114832209), self, "", COMMAND_PRIORITY_FRONT);
        setPosture(self, POSTURE_PRONE);
        dictionary d = new dictionary();
        d.put("owner", owner);
        messageTo(self, "takeOff", d, 25.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int takeOff(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id owner = params.getObjId("owner");
        location loc = getLocation(self);
        dictionary d = new dictionary();
        d.put("owner", owner);
        messageTo(self, "warpPlayer", d, 5.0f, false);
        messageTo(self, "startTakeOffSequence", d, 3.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int startTakeOffSequence(obj_id self, dictionary params) throws InterruptedException
    {
        queueCommand(self, (-1465754503), self, "", COMMAND_PRIORITY_FRONT);
        setPosture(self, POSTURE_UPRIGHT);
        messageTo(self, "cleanUp", null, 20.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int cleanUp(obj_id self, dictionary params) throws InterruptedException
    {
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
    public int warpPlayer(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id owner = params.getObjId("owner");
        modifyCollectionSlotValue(owner, "publish_chapter7_click_activation_01", 1);
        warpPlayer(owner, "tatooine", 3278, 5, -4821, null, 0, 0, 0, "", false);
        setYaw(owner, 40);
        return SCRIPT_CONTINUE;
    }
}
