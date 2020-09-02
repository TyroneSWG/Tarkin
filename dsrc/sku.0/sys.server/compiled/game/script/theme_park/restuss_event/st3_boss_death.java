package script.theme_park.restuss_event;

import script.library.groundquests;
import script.obj_id;

public class st3_boss_death extends script.base_script
{
    public st3_boss_death()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        obj_id[] playersInRange = getPlayerCreaturesInRange(self, 40.0f);
        String myTemplate = getTemplateName(self);
        if (myTemplate.equals("object/mobile/dressed_rebel_restuss_captain_vrinko.iff"))
        {
            groundquests.sendSignal(playersInRange, "killedRebelsBoss");
            return SCRIPT_CONTINUE;
        }
        else if (myTemplate.equals("object/mobile/dressed_imperial_restuss_admiral_grot.iff"))
        {
            groundquests.sendSignal(playersInRange, "killedImperialsBoss");
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
