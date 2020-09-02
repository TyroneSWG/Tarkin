package script.systems.missions.dynamic;

import script.dictionary;
import script.library.create;
import script.obj_id;

public class bounty_informant_spawner extends script.systems.missions.base.mission_dynamic_base
{
    public bounty_informant_spawner()
    {
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public obj_id makeInformant(obj_id self) throws InterruptedException
    {
        String strNPC = "spynet_operative";
        obj_id objInformant = create.object(strNPC, getLocation(self));
        setObjVar(objInformant, "objParent", self);
        if (objInformant != null)
        {
            attachScript(objInformant, "systems.missions.dynamic.mission_bounty_informant");
        }
        int intInformantLevel = getIntObjVar(self, "intInformantLevel");
        setObjVar(objInformant, "intInformantLevel", intInformantLevel);
        return objInformant;
    }
    public int remakeInformant(obj_id self, dictionary params) throws InterruptedException
    {
        debugSpeakMsg(self, "Makign informant again");
        obj_id objInformant = makeInformant(self);
        return SCRIPT_CONTINUE;
    }
}
