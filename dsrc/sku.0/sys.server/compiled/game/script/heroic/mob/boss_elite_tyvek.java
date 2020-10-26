package script.heroic.mob;

import script.library.ai_lib;
import script.obj_id;
import script.library.chat;
import script.library.buff;
import script.library.utils;

/**
 *
 * @author TyroneSWG
 */
public class boss_elite_tyvek extends script.base_script {

    public boss_elite_tyvek()
    {

    }

    public static int OnAttach(obj_id self) throws InterruptedException
    {
        createTriggerVolume("boss_elite_tyvek", 25.0f, true);
        return SCRIPT_CONTINUE;
    }

    public static int OnEnteredCombat(obj_id self) throws InterruptedException
    {
        obj_id attacker = getHateTarget(self);
        if (!isPlayer(attacker))
        {
            return SCRIPT_CONTINUE;
        }
        int chance = rand(0, 4);
        switch (chance)
        {
            case 0:
                buff.applyBuff(attacker, "armorBreak");
                chat.chat(self, "How about that, the Doc's armor piercing rounds really work!");
                break;
            case 1:
                chat.chat(self, "Lets give the Doc's holoemitter a good 'ol test...");
                buff.applyBuff(attacker, "event_halloween_costume_skeleton1");
                break;
            case 2:
                chat.chat(self, "\\#DD1234Not so fast!\\#FF0000");
                buff.applyBuff(attacker, "charge_stun_debuff");
                buff.applyBuff(attacker, "sp_healing_reduction");
                break;
            case 3:
                int s_duration = rand(120, 180);
                float duration = utils.stringToFloat(Integer.toString(s_duration));
                chat.chat(self, "What! You will pay for this.");
                sendSystemMessageTestingOnly(attacker, "You have caught Tyvek off guard, giving you the upperhand");
                buff.applyBuff(attacker, attacker, "crystal_buff_drained", duration);
                break;
            case 4:
                int s_duration_boss = rand(120, 180);
                float duration_boss = utils.stringToFloat(Integer.toString(s_duration_boss));
                chat.chat(self, "Foolish gang of merry adventurers..");
                sendSystemMessageTestingOnly(attacker, "Your attempts to sneak in have failed.");
                buff.applyBuff(self, self, "crystal_buff_drained", duration_boss);
                break;
        }
        return SCRIPT_CONTINUE;
    }

    public static void OnExitedCombat(obj_id self, obj_id attacker) throws InterruptedException
    {
        if (getGalaxyName().equals("swg") || getGalaxyName().equals("Gally"))
        {
            if (!isDead(self))
            {
                sendSystemMessageTestingOnly(attacker, "DEBUG: ended combat with target alive");
            } else
            {
                sendSystemMessageTestingOnly(attacker, "DEBUG: ended combat with target dead");
            }
        }
    }

    public static int OnDeath(obj_id self, obj_id killer) throws InterruptedException
    {
        if (!isPlayer(killer))
        {
            return SCRIPT_CONTINUE;
        }
        LOG("HEROICS_NEW", "Player " + getPlayerName(killer) + " has killed " + self);
        removeTriggerVolume("boss_elite_tyvek");
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
}
