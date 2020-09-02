package script.player;

import script.library.furniture;
import script.obj_id;

public class player_seated extends script.base_script
{
    public player_seated()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        if (!hasObjVar(self, furniture.VAR_PLAYER_SEAT_ID))
        {
            detachScript(self, furniture.SCRIPT_PLAYER_SEATED);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnChangedPosture(obj_id self, int before, int after) throws InterruptedException
    {
        if (before == POSTURE_SITTING)
        {
            furniture.unseat(self);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnEnteredCombat(obj_id self) throws InterruptedException
    {
        queueCommand(self, (-1465754503), null, "", COMMAND_PRIORITY_FRONT);
        return SCRIPT_CONTINUE;
    }
    public int OnLogout(obj_id self) throws InterruptedException
    {
        furniture.unseat(self);
        return SCRIPT_CONTINUE;
    }
}
