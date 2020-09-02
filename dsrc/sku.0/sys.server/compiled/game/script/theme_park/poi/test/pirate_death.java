package script.theme_park.poi.test;

import script.dictionary;
import script.library.ai_lib;
import script.obj_id;

public class pirate_death extends script.base_script
{
    public pirate_death()
    {
    }
    public int OnEnteredCombat(obj_id self) throws InterruptedException
    {
        detachScript(self, "theme_park.tatooine.behavior.poi_waiting");
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        stop(self);
        setObjVar(self, "ai.defaultCalmBehavior", 1);
        setObjVar(self, "animsLeft", 1);
        messageTo(self, "playAnimation", null, 5, false);
        return SCRIPT_CONTINUE;
    }
    public int playAnimation(obj_id self, dictionary params) throws InterruptedException
    {
        if (!ai_lib.isInCombat(self))
        {
            int anims = getIntObjVar(self, "animsLeft");
            String action = "wave_hail";
            int move = rand(1, 5);
            switch (move)
            {
                case 1:
                action = "check_wrist_device";
                break;
                case 2:
                action = "cough_polite";
                break;
                case 3:
                action = "rub_belly";
                break;
                case 4:
                action = "scratch_head";
                break;
                case 5:
                action = "check_wrist_device";
                break;
            }
            doAnimationAction(self, action);
            if (anims < 10)
            {
                int speed = rand(5, 6);
                messageTo(self, "playAnimation", null, speed, false);
                anims = anims + 1;
                setObjVar(self, "animsLeft", anims);
            }
            else 
            {
                removeObjVar(self, "animsLeft");
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int doFacing(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id face = params.getObjId("target");
        faceTo(self, face);
        faceTo(face, self);
        return SCRIPT_CONTINUE;
    }
}
