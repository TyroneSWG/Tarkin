package script.theme_park.dungeon.mustafar_trials.mining_facility;

import script.library.sequencer;
import script.obj_id;

public class patrol_rt5 extends script.base_script
{
    public patrol_rt5()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        sequencer.registerSequenceObject(self, "patrol_rt5");
        return SCRIPT_CONTINUE;
    }
}
