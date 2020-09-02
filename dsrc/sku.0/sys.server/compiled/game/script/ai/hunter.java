package script.ai;

import script.library.ai_lib;
import script.library.attrib;
import script.obj_id;

public class hunter extends script.base_script
{
    public hunter()
    {
    }
    public static final String ALERT_VOLUME_NAME = "alertTriggerVolume";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setAttributeInterested(self, attrib.HERBIVORE);
        setAttributeInterested(self, attrib.CARNIVORE);
        if (getConfigSetting("GameServer", "disableAITriggerVolumes") == null)
        {
            createTriggerVolume(ALERT_VOLUME_NAME, ai_lib.aiGetApproachTriggerRange(self), true);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id breacher) throws InterruptedException
    {
        if (hasObjVar(breacher, "gm"))
        {
            return SCRIPT_CONTINUE;
        }
        if (!isMob(breacher))
        {
            return SCRIPT_CONTINUE;
        }
        if (ai_lib.isInCombat(self))
        {
            return SCRIPT_CONTINUE;
        }
        if (breacher == self)
        {
            return SCRIPT_CONTINUE;
        }
        if (ai_lib.isAiDead(self) || ai_lib.isAiDead(breacher))
        {
            return SCRIPT_CONTINUE;
        }
        if (!ai_lib.isMonster(breacher))
        {
            return SCRIPT_CONTINUE;
        }
        startCombat(self, breacher);
        return SCRIPT_CONTINUE;
    }
}
