package script.item.camp;

import script.obj_id;

public class camp_basic extends script.item.camp.camp_base
{
    public camp_basic()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setObjVar(self, "campPower", 1);
        setObjVar(self, "skillReq", 1);
        return SCRIPT_CONTINUE;
    }
}
