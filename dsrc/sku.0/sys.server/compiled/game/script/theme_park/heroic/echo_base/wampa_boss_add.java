package script.theme_park.heroic.echo_base;

import script.library.trial;
import script.obj_id;

public class wampa_boss_add extends script.base_script
{
    public wampa_boss_add()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        trial.setHp(self, rand(trial.HP_JOES_ADDS - 10000, trial.HP_JOES_ADDS + 20000));
        getRandomCombatTarget(self);
        return SCRIPT_CONTINUE;
    }
    public int OnExitedCombat(obj_id self) throws InterruptedException
    {
        getRandomCombatTarget(self);
        return SCRIPT_CONTINUE;
    }
    public void getRandomCombatTarget(obj_id self) throws InterruptedException
    {
        obj_id[] targets = trial.getValidTargetsInRadiusIgnoreLOS(self, 125.0f);
        if (targets == null || targets.length == 0)
        {
            trial.cleanupObject(self);
        }
        startCombat(self, targets[rand(0, targets.length - 1)]);
        return;
    }
}
