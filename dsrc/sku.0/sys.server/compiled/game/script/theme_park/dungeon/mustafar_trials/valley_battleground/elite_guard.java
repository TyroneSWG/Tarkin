package script.theme_park.dungeon.mustafar_trials.valley_battleground;

import script.dictionary;
import script.library.trial;
import script.obj_id;

public class elite_guard extends script.base_script
{
    public elite_guard()
    {
    }
    public static final boolean LOGGING = false;
    public int OnAttach(obj_id self) throws InterruptedException
    {
        trial.setInterest(self);
        trial.markAsDroidArmy(self);
        setHibernationDelay(self, 7200);
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        messageTo(self, "handleDeath", null, 5, false);
        return SCRIPT_CONTINUE;
    }
    public int OnDestroy(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int handleDeath(obj_id self, dictionary params) throws InterruptedException
    {
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
    public void doLogging(String section, String message) throws InterruptedException
    {
        if (LOGGING || trial.VALLEY_LOGGING)
        {
            LOG("logging/elite_guard/" + section, message);
        }
    }
}
