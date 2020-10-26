package script.heroic.mob;

import script.library.chat;
import script.library.groundquests;
import script.obj_id;
import script.location;
import script.library.ai_lib;
import script.library.buff;
import script.library.colors;
import script.library.utils;

/**
 *
 * @author TyroneSWG
 */
public class boss_mad_scientist extends script.base_script {

    public boss_mad_scientist()
    {

    }

    public static String BS_NEXT_SIGNAL = "bs_final_boss_killed";

    public static int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "Sir Duwo, a Mad Scientist");
        createTriggerVolume("boss_mad_scientist", 25.0f, true);
        return SCRIPT_CONTINUE;
    }

    public static int OnEnteredCombat(obj_id self) throws InterruptedException
    {
        obj_id attacker = ai_lib.getCombatTarget(self);
        if (isPlayer(attacker))
        {

            while (!isDead(self))
            {
                int currentHealth = getHealth(self);
                if (currentHealth >= 500000)
                {
                    splashBomb(self, attacker, 1000);
                }
                if (currentHealth <= 50000)
                {
                    location sp1 = getLocation(self);
                    location sp2 = getLocation(self);
                    sp1.x = sp1.x - 2;
                    sp1.z = sp1.z - 2;
                    sp2.x = sp2.x - 2;
                    sp2.z = sp2.z - 2;
                    ai_lib.spawnAdd(self, "heroic_black_sun_elite", sp1);
                    ai_lib.spawnAdd(self, "heroic_black_sun_elite", sp2);
                }
                if (currentHealth <= 25000)
                {
                    chat.chat(self, "You'll never stop me!!");
                    buff.applyBuff(attacker, "heroic_blacksun_poision");
                    setHealth(self, 11111);
                }
                if (currentHealth <= 10000)
                {
                    splashBomb(self, attacker, 1000);
                }
                if (currentHealth <= 500)
                {
                    String flytext1 = "SKETCHY IMPLEMENTATION";
                    showFlyText(self, utils.unpackString(flytext1), 4.0f, colors.RED);
                    chat.chat(self, "Uh oh.");
                    setHealth(self, 100);
                }
                break;
            }
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }

    public static int OnDeath(obj_id self, obj_id killer) throws InterruptedException
    {
        LOG("HEROICS_NEW", "Player " + getPlayerName(killer) + " has killed " + getName(self));
        if (!isPlayer(killer))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id[] getNearbyGroup = getPlayerCreaturesInRange(self, 120.0f);
        groundquests.sendSignal(getNearbyGroup, BS_NEXT_SIGNAL);
        removeTriggerVolume("bs_final_boss_killed");
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }

    public static int splashBomb(obj_id self, obj_id target, int maxDamage) throws InterruptedException
    {
        sendSystemMessageTestingOnly(target, "You smell something funny..");
        if (!isPlayer(target))
        {
            return SCRIPT_CONTINUE;
        }
        int comparableHP = getHealth(target);
        int percentage = rand(1, 999);
        maxDamage = maxDamage / comparableHP / 3 * 20;
        if (percentage >= comparableHP)
        {
            chat.chat(self, "Blast it! My formula must be wrong!");
            setHealth(target, maxDamage);
            sendSystemMessageTestingOnly(target,
                "The Mad Scientist's attempted to poison you has failed! However you're suffering from some side effects.");
            return SCRIPT_CONTINUE;
        }
        if (percentage <= comparableHP)
        {
            chat.chat(self, "Coward, I will show you true power of science!");
            sendSystemMessageTestingOnly(target, "The Mad Scientist's attempted to poison you has worked.");
            setHealth(target, maxDamage);
        }
        return SCRIPT_CONTINUE;
    }
}
