package script.working.cmayer;

import script.obj_id;

public class lostest extends script.base_script
{
    public lostest()
    {
    }
    public int OnSpeaking(obj_id self, String text) throws InterruptedException
    {
        if (text.equals("test"))
        {
            int times = 50;
            if (hasObjVar(self, "lostest"))
            {
                times = getIntObjVar(self, "lostest");
            }
            obj_id target = getLookAtTarget(self);
            boolean test = canSee(self, target);
            if (test)
            {
                sendSystemMessageTestingOnly(self, "Can See!");
            }
            else 
            {
                sendSystemMessageTestingOnly(self, "NOOOO Can See!");
            }
            for (int i = 0; i < times; ++i)
            {
                test = canSee(self, target);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
