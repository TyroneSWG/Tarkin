package script.theme_park.restuss_event;

import script.dictionary;
import script.library.ai_lib;
import script.library.movement;
import script.library.utils;
import script.location;
import script.obj_id;

public class move_manager extends script.base_script
{
    public move_manager()
    {
    }
    public static final int REST_IMP = 0;
    public static final int REST_REB = 1;
    public int marchRun(obj_id self, dictionary params) throws InterruptedException
    {
        int type = getIntObjVar(self, "restriction");
        switch (type)
        {
            case REST_IMP:
            doImperialMarch(self);
            break;
            case REST_REB:
            setMovementRun(self);
            movement.refresh(self);
            break;
        }
        return SCRIPT_CONTINUE;
    }
    public int setRun(obj_id self, dictionary params) throws InterruptedException
    {
        int type = getIntObjVar(self, "restriction");
        if (type == REST_REB)
        {
            setMovementRun(self);
            movement.refresh(self);
        }
        return SCRIPT_CONTINUE;
    }
    public void doImperialMarch(obj_id self) throws InterruptedException
    {
        location moveToLoc = utils.findLocInFrontOfTarget(self, 40.0f);
        ai_lib.aiPathTo(self, moveToLoc);
    }
}
