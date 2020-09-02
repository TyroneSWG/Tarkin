package script.systems.missions.dynamic;

import script.dictionary;
import script.obj_id;

public class mission_recon_target extends script.systems.missions.base.mission_dynamic_base
{
    public mission_recon_target()
    {
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if (!hasScript(self, "systems.missions.base.mission_cleanup_tracker"))
        {
            attachScript(self, "systems.missions.base.mission_cleanup_tracker");
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnHearSpeech(obj_id self, obj_id objSpeaker, String strText) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnRemovingFromWorld(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnAddedToWorld(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnObjectDisabled(obj_id self, obj_id objKiller) throws InterruptedException
    {
        LOG("mission", "Killed by " + objKiller);
        setObjVar(self, "intKilled", 1);
        obj_id objDestroyer = getObjIdObjVar(self, "objDestroyer");
        LOG("mission", "objDestroyer is  " + objDestroyer);
        sendReconSuccess(getObjIdObjVar(self, "objMission"));
        setObjVar(self, "intKilled", 1);
        return SCRIPT_CONTINUE;
    }
    public int destroySelf(obj_id self, dictionary params) throws InterruptedException
    {
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
}
