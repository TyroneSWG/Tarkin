package script.heroic.mob;

import script.library.ai_lib;
import script.dictionary;
import script.location;
import script.obj_id;
import script.string_id;
import script.library.chat;
import script.library.colors;
import script.library.combat;
import script.library.group;
import script.library.buff;
import script.library.utils;

/**
 *
 * @author TyroneSWG
 */
public class boss_trando_lead_hunter extends script.base_script {

    public boss_trando_lead_hunter()
    {

    }

    public static int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "Raz'r");
        setHealth(self, 2600000);
        createTriggerVolume("boss_trando_lead_hunter", 25.0f, true);
        return SCRIPT_CONTINUE;
    }

    public static int OnEnteredCombat(obj_id self) throws InterruptedException
    {
        obj_id attacker = getHateTarget(self);
        if (!isPlayer(attacker))
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(self, "You dare challenge me?");
        while (combat.isInCombat(self))
        {
            if (getHealth(self) == 1400000)
            {
                buff.applyBuff(attacker, "armorBreak", 120.0f);
                chat.chat(self, "Welps!");
            }
            if (buff.hasBuff(attacker, "armorBreak"))
            {
                int damage = 20;
                int health = getHealth(attacker) - damage;
                setHealth(attacker, health);
            }
            if (getHealth(self) == 250000)
            {
                chat.chat(self, "A hunter who stalks his prey is the wisSessst..");
                playClientEffectObj(utils.getPlayerCreaturesInRange(getLocation(self), 24.0f),
                    "clienteffect/npe_smoke_bomb.cef", self, "head");
                hideFromClient(self, true);
                ai_lib.spawnAdd(self, "heroic_trando_boss_razr_adds", getLocation(self));
                ai_lib.spawnAdd(self, "heroic_trando_boss_razr_adds", getLocation(self));
            }
            if (!stillSpawnedAdds(self))
            {
                hideFromClient(self, false);
            }
            if (getHealth(self) == 100000)
            {
                chat.chat(self, "-Hiss-");
                buff.applyBuff(attacker, "bleeding", 30.0f);
            }
            if (buff.hasBuff(self, "doctor_stasis_other") || buff.hasBuff(self, "brawler_taunt"))
            {
                if (!utils.hasScriptVar(self, "chatted"))
                {
                    chat.chat(attacker, "Get em!");
                    utils.setScriptVar(self, "chatted", 1);
                }
            }
            if (getHealth(self) == 50000)
            {
                obj_id[] targets = getAllPlayers(getLocation(self), 24.0f);
                for (obj_id target : targets)
                {
                    int throwRange = rand(-5, 5);
                    throwTarget(self, target, throwRange, throwRange);
                    sendSystemMessageTestingOnly(target, "\\#FF4500Raz'r throws you back!\\#FF0000");
                }
            }
            if (getHealth(self) == 10000)
				;
            {
                chat.chat(self, "This can't be!");
                if (!utils.hasScriptVar(self, "boss.heal"))
                {
                    int currentHealth = getHealth(self);
                    int health = currentHealth * group.getGroupSize(attacker);
                    setHealth(self, health);
                    sendSystemMessageTestingOnly(attacker, "\\\\#FF4500Raz'r has applied a health stim!\\\\#FF0000");
                }

            }
            if (getHealth(self) >= 250)
            {
                chat.chat(self, "No!");
            }
        }
        return SCRIPT_CONTINUE;
    }

    public static boolean stillSpawnedAdds(obj_id self) throws InterruptedException
    {
        String template = "object/mobile/ep3/ep3_trando_slaver_04.iff";
        obj_id[] adds = utils.getCreaturesInRange(getLocation(self), 24.0f);
        for (obj_id add : adds)
        {
            if (getTemplateName(self).equals(template))
            {
                LOG("HEROICS", "Found mobs, not progressing.");
                String flytext = " - PROWL - ";
                string_id flyspam = utils.unpackString(flytext);
                showFlyText(self, flyspam, 2.0f, colors.RED);
                showFlyText(add, flyspam, 2.0f, colors.RED);
                return true;
            }
        }
        return false;
    }

    public static int throwTarget(obj_id self, obj_id target, int offsetX, int offsetZ) throws InterruptedException
    {
        if (!isPlayer(target))
        {
            return SCRIPT_CONTINUE;
        }
        location throwLoc = getLocation(self);
        throwLoc.x = throwLoc.x - offsetX;
        throwLoc.z = throwLoc.z - offsetX;
        setLocation(target, throwLoc);
        return SCRIPT_CONTINUE;
    }

    public static void OnExitedCombat(obj_id self, obj_id attacker) throws InterruptedException
    {
        if (getGalaxyName().equals("swg") || getGalaxyName().equals("Gally") || getGalaxyName().equals("Tarkin"))
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
        removeTriggerVolume("boss_trando_lead_hunter");
        dictionary params = new dictionary();
        params.put("player", killer);
        messageTo(killer, "awardPasskey", params, 2.0f, false);
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
}
