package script.theme_park.dungeon.mustafar_trials.obiwan_finale;

import script.combat_engine.hit_result;
import script.dictionary;
import script.library.*;
import script.obj_id;
import script.prose_package;
import script.string_id;

public class obiwan_lair_boss extends script.base_script
{
    public obiwan_lair_boss()
    {
    }
    public static final String[] FORCE_ATTACK_ANIMS = 
    {
        "force_push",
        "force_strength",
        "force_choke"
    };
    public static final String[] FORCE_ATTACK_CEFS = 
    {
        "clienteffect/pl_force_tangle.cef",
        "clienteffect/pl_force_lightning_begin.cef",
        "clienteffect/pl_force_weaken.cef",
        "clienteffect/pl_force_blast.cef"
    };
    public static final String[] FORCE_ATTACK_NAMES = 
    {
        "singleStoneThrow",
        "doubleStoneThrow",
        "tripleStoneThrow",
        "multiStoneThrow"
    };
    public static final int FORCE_ATTACK_ABORT_DAMAGE_REQUIRED = 2000;
    public static final boolean CONST_FLAG_DO_LOGGING = false;
    public int OnAttach(obj_id self) throws InterruptedException
    {
        debugLogging("//***// OnAttach: ", "////>>>> calling mustafar.spawnContents for 'boss'.");
        setInvulnerable(self, true);
        trial.setHp(self, 155000);
        obj_id dungeon = getTopMostContainer(self);
        if (!isIdValid(dungeon))
        {
            debugLogging("//***// OnAttach: ", "////>>>> dungeon obj_id is invalid. BROKEN");
        }
        utils.setScriptVar(dungeon, "darkJedi", self);
        utils.setScriptVar(dungeon, "intermission", 0);
        messageTo(dungeon, "moveBossToPostureLoc", null, 1, false);
        messageTo(dungeon, "moveObiwanToPostureLocation", null, 2, false);
        messageTo(dungeon, "lightsCameraAction", null, 16, false);
        return SCRIPT_CONTINUE;
    }
    public int OnAboutToBeIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        if (utils.hasScriptVar(self, "inCombat"))
        {
            utils.removeScriptVar(self, "inCombat");
        }
        if (utils.hasScriptVar(self, "forcePowerAttack"))
        {
            utils.removeScriptVar(self, "forcePowerAttack");
        }
        utils.setScriptVar(self, "ignoreTaunt", 1);
        utils.setScriptVar(self, "ignoreForcePowerAttack", 1);
        obj_id dungeon = getTopMostContainer(self);
        if (!isIdValid(dungeon))
        {
            debugLogging("//***// OnAboutToBeIncapacitated: ", "////>>>> dungeon obj_id is invalid. BROKEN");
        }
        int intermission = utils.getIntScriptVar(dungeon, "intermission");
        if (utils.hasScriptVar(self, "incappedOnce") && intermission < 4)
        {
            return SCRIPT_OVERRIDE;
        }
        if (intermission > 3)
        {
            return SCRIPT_CONTINUE;
        }
        else 
        {
            stopFighting(self, dungeon);
            utils.setScriptVar(self, "incappedOnce", 1);
            messageTo(dungeon, "lightsCameraAction", null, 2, false);
            return SCRIPT_OVERRIDE;
        }
    }
    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        obj_id dungeon = getTopMostContainer(self);
        if (!isIdValid(dungeon))
        {
            debugLogging("//***// OnIncapacitated: ", "////>>>> dungeon obj_id is invalid.");
            return SCRIPT_CONTINUE;
        }
        debugLogging("//***// OnIncapacitated: ", "////>>>> messaging  'lightsCameraAction.'");
        messageTo(dungeon, "lightsCameraAction", null, 10, false);
        utils.removeScriptVar(dungeon, "darkJedi");
        return SCRIPT_CONTINUE;
    }
    public int OnCreatureDamaged(obj_id self, obj_id attacker, obj_id weapon, int[] damage) throws InterruptedException
    {
        if (!isIdValid(attacker))
        {
            debugLogging("OnCreatureDamaged", "Attacker obj_id invalid.");
            return SCRIPT_CONTINUE;
        }
        if ((damage == null) || (damage.length < NUM_ATTRIBUTES))
        {
            debugLogging("OnCreatureDamaged", "Malformed or null damage array.");
            return SCRIPT_CONTINUE;
        }
        long totalDamage = 0;
        long dam = 0;
        for (int i = 0; i < NUM_ATTRIBUTES; i += NUM_ATTRIBUTES_PER_GROUP)
        {
            dam = damage[i];
            totalDamage += dam;
        }
        if (totalDamage > Integer.MAX_VALUE)
        {
            totalDamage = Integer.MAX_VALUE;
        }
        if (totalDamage > FORCE_ATTACK_ABORT_DAMAGE_REQUIRED)
        {
            if (utils.hasScriptVar(self, "forcePowerAttack"))
            {
                utils.setScriptVar(self, "ignoreForcePowerAttack", 1);
                utils.removeScriptVar(self, "forcePowerAttack");
                doAnimationAction(self, "anims.HUMAN_REA_STAND_COMBAT_GET_HIT_HEAVY");
                obj_id dungeon = getTopMostContainer(self);
                if (!isIdValid(dungeon))
                {
                    debugLogging("//***// startFighting: ", "////>>>> dungeon obj_id is invalid. BROKEN");
                }
                messageTo(dungeon, "obiwanCongratsSpecialAttackBlock", null, 0, false);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnMovePathComplete(obj_id self) throws InterruptedException
    {
        obj_id dungeon = getTopMostContainer(self);
        if (!isIdValid(dungeon))
        {
            debugLogging("//***// OnMovePathComplete: ", "////>>>> dungeon obj_id is invalid. BROKEN");
        }
        obj_id player = utils.getObjIdScriptVar(dungeon, "player");
        if (!isIdValid(player))
        {
            debugLogging("//***// OnMovePathComplete: ", "////>>>> player obj_id is invalid. BROKEN");
            return SCRIPT_CONTINUE;
        }
        faceTo(self, player);
        return SCRIPT_CONTINUE;
    }
    public int OnAttackerCombatAction(obj_id self, obj_id weapon, obj_id defender) throws InterruptedException
    {
        if (buff.hasBuff(defender, "avoidIncapacitation"))
        {
            buff.removeBuff(defender, "avoidIncapacitation");
        }
        if (buff.hasBuff(defender, "avoidIncapacitation_1"))
        {
            buff.removeBuff(defender, "avoidIncapacitation_1");
        }
        if (buff.hasBuff(defender, "avoidIncapacitation_2"))
        {
            buff.removeBuff(defender, "avoidIncapacitation_2");
        }
        if (buff.hasBuff(defender, "avoidIncapacitation_3"))
        {
            buff.removeBuff(defender, "avoidIncapacitation_3");
        }
        if (buff.hasBuff(defender, "avoidIncapacitation_4"))
        {
            buff.removeBuff(defender, "avoidIncapacitation_4");
        }
        if (buff.hasBuff(defender, "avoidIncapacitation_5"))
        {
            buff.removeBuff(defender, "avoidIncapacitation_5");
        }
        return SCRIPT_CONTINUE;
    }
    public void debugLogging(String section, String message) throws InterruptedException
    {
        if (CONST_FLAG_DO_LOGGING)
        {
            LOG("debug/obiwan_lair_boss/" + section, message);
        }
    }
    public void stopFighting(obj_id self, obj_id dungeon) throws InterruptedException
    {
        utils.removeScriptVar(self, "inCombat");
        utils.setScriptVar(self, "ignoreTaunt", 1);
        utils.setScriptVar(self, "ignoreForcePowerAttack", 1);
        if (utils.hasScriptVar(self, "forcePowerAttack"))
        {
            utils.removeScriptVar(self, "forcePowerAttack");
        }
        stopCombat(self);
        String effect = "clienteffect/pl_force_healing.cef";
        playClientEffectObj(self, effect, self, "");
        addToHealth(self, 150000);
        setInvulnerable(self, true);
        return;
    }
    public int startFighting(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id dungeon = getTopMostContainer(self);
        if (!isIdValid(dungeon))
        {
            debugLogging("//***// startFighting: ", "////>>>> dungeon obj_id is invalid. BROKEN");
        }
        obj_id target = utils.getObjIdScriptVar(dungeon, "player");
        if (utils.hasScriptVar(self, "ignoreTaunt"))
        {
            utils.removeScriptVar(self, "ignoreTaunt");
        }
        if (utils.hasScriptVar(self, "ignoreForcePowerAttack"))
        {
            utils.removeScriptVar(self, "ignoreForcePowerAttack");
        }
        if (utils.hasScriptVar(self, "forcePowerAttack"))
        {
            utils.removeScriptVar(self, "forcePowerAttack");
        }
        setInvulnerable(self, false);
        startCombat(self, target);
        utils.setScriptVar(self, "inCombat", 1);
        messageTo(self, "randomTaunter", null, rand(10, 30), false);
        messageTo(self, "randomPraiser", null, rand(15, 30), false);
        messageTo(self, "specialForcePowerAttackWindup", null, rand(10, 20), false);
        return SCRIPT_CONTINUE;
    }
    public int randomTaunter(obj_id self, dictionary params) throws InterruptedException
    {
        if (utils.hasScriptVar(self, "inCombat"))
        {
            if (utils.getIntScriptVar(self, "inCombat") > 0)
            {
                if (!utils.hasScriptVar(self, "ignoreTaunt"))
                {
                    obj_id dungeon = getTopMostContainer(self);
                    if (!isIdValid(dungeon))
                    {
                        debugLogging("//***// randomTaunter: ", "////>>>> dungeon obj_id is invalid. BROKEN");
                    }
                    obj_id player = utils.getObjIdScriptVar(dungeon, "player");
                    if (!isIdValid(player))
                    {
                        return SCRIPT_CONTINUE;
                    }
                    if (!mustafar.stillWithinDungeonCheck(player, dungeon))
                    {
                        return SCRIPT_CONTINUE;
                    }
                    int laughChance = rand(1, 10);
                    if (laughChance > 5)
                    {
                        playClientEffectObj(player, "clienteffect/mustafar/som_dark_jedi_laugh.cef", self, "");
                    }
                    string_id strSpam = new string_id(mustafar.STF_OBI_MSGS, "som_dark_jedi_you_die_" + rand(1, 15));
                    prose_package pp = prose.getPackage(strSpam);
                    pp.target.set(player);
                    chat.chat(self, player, pp);
                    messageTo(self, "randomTaunter", null, rand(10, 30), false);
                }
                else 
                {
                    utils.removeScriptVar(self, "ignoreTaunt");
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int randomPraiser(obj_id self, dictionary params) throws InterruptedException
    {
        if (utils.hasScriptVar(self, "inCombat"))
        {
            if (!utils.hasScriptVar(self, "ignoreTaunt"))
            {
                obj_id dungeon = getTopMostContainer(self);
                if (!isIdValid(dungeon))
                {
                    debugLogging("//***// randomTaunter: ", "////>>>> dungeon obj_id is invalid. BROKEN");
                }
                obj_id player = utils.getObjIdScriptVar(dungeon, "player");
                if (!isIdValid(player))
                {
                    return SCRIPT_CONTINUE;
                }
                obj_id obiwan = utils.getObjIdScriptVar(dungeon, "obiwan");
                if (!isIdValid(obiwan))
                {
                    return SCRIPT_CONTINUE;
                }
                if (!mustafar.stillWithinDungeonCheck(player, dungeon))
                {
                    return SCRIPT_CONTINUE;
                }
                string_id strSpam = new string_id(mustafar.STF_OBI_MSGS, "som_obiwan_sayings_" + rand(1, 10));
                prose_package pp = prose.getPackage(strSpam);
                pp.target.set(player);
                chat.chat(obiwan, player, pp);
                messageTo(self, "randomPraiser", null, rand(15, 30), false);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int specialForcePowerAttackWindup(obj_id self, dictionary params) throws InterruptedException
    {
        debugLogging("//***// specialForcePowerAttackWindup: ", "////>>>> ENTERED");
        if (utils.hasScriptVar(self, "ignoreForcePowerAttack"))
        {
            utils.removeScriptVar(self, "ignoreForcePowerAttack");
            return SCRIPT_CONTINUE;
        }
        if (!utils.hasScriptVar(self, "inCombat"))
        {
            debugLogging("//***// specialForcePowerAttackWindup: ", "////>>>> outta combat. Stop trying to do special force attacks.");
            return SCRIPT_CONTINUE;
        }
        debugLogging("//***// specialForcePowerAttackWindup: ", "////>>>> STILL GOIN");
        if (utils.hasScriptVar(self, "forcePowerAttack"))
        {
            debugLogging("//***// specialForcePowerAttackWindup: ", "////>>>> already has a special force power attack queued up!");
            return SCRIPT_CONTINUE;
        }
        obj_id dungeon = getTopMostContainer(self);
        if (!isIdValid(dungeon))
        {
            debugLogging("//***// specialForcePowerAttackWindup: ", "////>>>> dungeon obj_id is invalid. BROKEN");
            return SCRIPT_CONTINUE;
        }
        obj_id player = utils.getObjIdScriptVar(dungeon, "player");
        if (!isIdValid(player))
        {
            debugLogging("//***// specialForcePowerAttackWindup: ", "////>>>> player obj_id is invalid. BROKEN");
            return SCRIPT_CONTINUE;
        }
        if (!utils.hasScriptVar(dungeon, "darkJedi"))
        {
            debugLogging("//***// specialForcePowerAttackWindup: ", "////>>>> no darkJedi scriptVar on the dungeon. We're dead.");
            return SCRIPT_CONTINUE;
        }
        debugLogging("//***// specialForcePowerAttackWindup: ", "////>>>> ALMOST THERE");
        utils.setScriptVar(self, "forcePowerAttack", FORCE_ATTACK_NAMES[(rand(0, FORCE_ATTACK_NAMES.length - 1))]);
        queueClear(self);
        doAnimationAction(self, FORCE_ATTACK_ANIMS[(rand(0, FORCE_ATTACK_ANIMS.length - 1))]);
        messageTo(self, "specialForcePowerAttackExecute", null, 15, false);
        playClientEffectObj(self, FORCE_ATTACK_CEFS[(rand(0, FORCE_ATTACK_CEFS.length - 1))], self, "");
        messageTo(dungeon, "obiwanWarnsOfSpecialAttack", null, 0, false);
        return SCRIPT_CONTINUE;
    }
    public int specialForcePowerAttackExecute(obj_id self, dictionary params) throws InterruptedException
    {
        debugLogging("//***// specialForcePowerAttackExecute: ", "////>>>> ENTERED");
        if (utils.hasScriptVar(self, "ignoreForcePowerAttack"))
        {
            debugLogging("//***// specialForcePowerAttackExecute: ", "////>>>> ignoring force power attack. Aborting attack.");
            utils.removeScriptVar(self, "ignoreForcePowerAttack");
            if (utils.hasScriptVar(self, "inCombat"))
            {
                messageTo(self, "specialForcePowerAttackWindup", null, rand(10, 20), false);
            }
            return SCRIPT_CONTINUE;
        }
        if (!utils.hasScriptVar(self, "forcePowerAttack"))
        {
            debugLogging("//***// specialForcePowerAttackExecute: ", "////>>>> no force power attack designated. Aborting attack.");
            return SCRIPT_CONTINUE;
        }
        obj_id dungeon = getTopMostContainer(self);
        if (!isIdValid(dungeon))
        {
            debugLogging("//***// specialForcePowerAttackExecute: ", "////>>>> dungeon obj_id is invalid. BROKEN");
        }
        obj_id player = utils.getObjIdScriptVar(dungeon, "player");
        if (!isIdValid(player))
        {
            debugLogging("//***// specialForcePowerAttackExecute: ", "////>>>> player obj_id is invalid. BROKEN");
            return SCRIPT_CONTINUE;
        }
        if (!utils.hasScriptVar(dungeon, "darkJedi"))
        {
            debugLogging("//***// specialForcePowerAttackWindup: ", "////>>>> no darkJedi scriptVar on the dungeon. We're dead.");
            return SCRIPT_CONTINUE;
        }
        String forcePowerAttackName = utils.getStringScriptVar(self, "forcePowerAttack");
        if (forcePowerAttackName == null || forcePowerAttackName.equals(""))
        {
            debugLogging("//***// specialForcePowerAttackExecute: ", "////>>>> problem with forcepower attack name");
            return SCRIPT_CONTINUE;
        }
        debugLogging("//***// specialForcePowerAttackExecute: ", "////>>>> grabbing data");
        String[] spawnTypeColumnData = dataTableGetStringColumnNoDefaults(mustafar.CONST_TBL_EVENT_DATA, "dataType");
        debugLogging("//***// specialForcePowerAttackExecute: ", "////>>>> got the data");
        if (spawnTypeColumnData.length == 0)
        {
            debugLogging("//***// specialForcePowerAttackExecute: ", "////>>>> spawnTypeColumnData length is zero");
            return SCRIPT_CONTINUE;
        }
        debugLogging("//***// specialForcePowerAttackExecute: ", "////>>>> spawnTypeColumnData.length is: " + spawnTypeColumnData.length);
        for (int i = 0; i < spawnTypeColumnData.length; i++)
        {
            debugLogging("specialForcePowerAttack: ", "Running the loop, looking for force attack data. Entry(" + i + ") is " + spawnTypeColumnData[i]);
            if (spawnTypeColumnData[i].equals("forcePowerAttack"))
            {
                dictionary dict = dataTableGetRow(mustafar.CONST_TBL_EVENT_DATA, i);
                debugLogging("specialForcePowerAttack: ", "Retrieving info for row(" + i + ")");
                debugLogging("specialForcePowerAttack: ", "forcePowerAttackName is: " + forcePowerAttackName);
                debugLogging("specialForcePowerAttack: ", "dict.getString('object') is: " + dict.getString("object"));
                if ((dict.getString("object")).equals(forcePowerAttackName))
                {
                    debugLogging("specialForcePowerAttack: ", "Doing the FORCE ATTACK!!!");
                    int minDamage = dict.getInt("minDamage");
                    int maxDamage = dict.getInt("maxDamage");
                    String clientEffectFile = dict.getString("clientEffect");
                    String animationName = dict.getString("animation");
                    obj_id weapon = getCurrentWeapon(self);
                    hit_result cbtHitData = new hit_result();
                    cbtHitData.success = true;
                    cbtHitData.baseRoll = 50;
                    cbtHitData.finalRoll = 100;
                    cbtHitData.hitLocation = rand(0, 5);
                    cbtHitData.canSee = true;
                    cbtHitData.damage = rand(minDamage, maxDamage);
                    cbtHitData.bleedingChance = 1;
                    doDamage(self, player, weapon, cbtHitData);
                    playClientEffectObj(player, clientEffectFile, player, "");
                    doAnimationAction(self, animationName);
                    messageTo(self, "specialForcePowerAttackWindup", null, rand(10, 20), false);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
