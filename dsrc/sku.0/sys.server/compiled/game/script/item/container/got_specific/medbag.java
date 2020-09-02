package script.item.container.got_specific;

import script.obj_id;

public class medbag extends script.item.container.got_specific.base
{
    public medbag()
    {
    }
    public static final int MY_TYPE = GOT_misc_pharmaceutical;
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setObjVar(self, VAR_GOT, MY_TYPE);
        return SCRIPT_CONTINUE;
    }
    public int OnDetach(obj_id self) throws InterruptedException
    {
        removeObjVar(self, VAR_GOT);
        return SCRIPT_CONTINUE;
    }
}
