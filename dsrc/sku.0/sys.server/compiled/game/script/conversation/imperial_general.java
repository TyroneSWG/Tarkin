package script.conversation;

import script.*;
import script.library.*;

public class imperial_general extends script.base_script
{
    public imperial_general()
    {
    }
    public static String c_stringFile = "conversation/imperial_general";
    public boolean imperial_general_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
        return true;
    }
    public boolean imperial_general_condition_isEntertainerPlayer(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return utils.isProfession(player, utils.ENTERTAINER);
    }
    public boolean imperial_general_condition_isPlayerImperial(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return (factions.isImperial(player) || factions.isPlayerSameGcwFactionAsSchedulerObject(player, npc));
    }
    public boolean imperial_general_condition_isPlayerRebel(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return (factions.isRebel(player) || factions.isPlayerSameGcwFactionAsSchedulerObject(player, npc));
    }
    public boolean imperial_general_condition_isPlayerNeutral(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return (!factions.isImperial(player) && !factions.isRebel(player) && pvpNeutralGetMercenaryFaction(player) == 0);
    }
    public boolean imperial_general_condition_isTraderPlayer(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return utils.isProfession(player, utils.TRADER);
    }
    public boolean imperial_general_condition_isForceSensitivePlayer(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return utils.isProfession(player, utils.FORCE_SENSITIVE);
    }
    public boolean imperial_general_condition_isSmugglerPlayer(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return utils.isProfession(player, utils.SMUGGLER);
    }
    public boolean imperial_general_condition_isMedicPlayer(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return utils.isProfession(player, utils.MEDIC);
    }
    public boolean imperial_general_condition_isOfficerPlayer(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return utils.isProfession(player, utils.OFFICER);
    }
    public boolean imperial_general_condition_isSpyPlayer(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return utils.isProfession(player, utils.SPY);
    }
    public boolean imperial_general_condition_isBountyHunterPlayer(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return utils.isProfession(player, utils.BOUNTY_HUNTER);
    }
    public boolean imperial_general_condition_isCommandoPlayer(obj_id player, obj_id npc) throws InterruptedException
    {
        faceTo(npc, player);
        return utils.isProfession(player, utils.COMMANDO);
    }
    public boolean imperial_general_condition_generalIsOffensive(obj_id player, obj_id npc) throws InterruptedException
    {
        return (hasObjVar(npc, "offense") && getIntObjVar(npc, "offense") == 1);
    }
    public boolean imperial_general_condition_generalIsImperial(obj_id player, obj_id npc) throws InterruptedException
    {
        return (hasObjVar(npc, "factionFlag") && getIntObjVar(npc, "factionFlag") == factions.FACTION_FLAG_IMPERIAL);
    }
    public boolean imperial_general_condition_generalIsDefensive(obj_id player, obj_id npc) throws InterruptedException
    {
        return (hasObjVar(npc, "offense") && getIntObjVar(npc, "offense") == 0);
    }
    public boolean imperial_general_condition_generalIsRebel(obj_id player, obj_id npc) throws InterruptedException
    {
        return (hasObjVar(npc, "factionFlag") && getIntObjVar(npc, "factionFlag") == factions.FACTION_FLAG_REBEL);
    }
    public boolean imperial_general_condition_isGeneralRebAndOffense(obj_id player, obj_id npc) throws InterruptedException
    {
        return imperial_general_condition_isPlayerRebel(player, npc) && imperial_general_condition_generalIsRebel(player, npc) && imperial_general_condition_generalIsOffensive(player, npc);
    }
    public boolean imperial_general_condition_isConstructionPhase(obj_id player, obj_id npc) throws InterruptedException
    {
        return (gcw.getGcwCityInvasionPhase(npc) == gcw.GCW_CITY_PHASE_CONSTRUCTION);
    }
    public boolean imperial_general_condition_isCombatPhase(obj_id player, obj_id npc) throws InterruptedException
    {
        return (gcw.getGcwCityInvasionPhase(npc) == gcw.GCW_CITY_PHASE_COMBAT);
    }
    public boolean imperial_general_condition_isGeneralRebAndDefense(obj_id player, obj_id npc) throws InterruptedException
    {
        return imperial_general_condition_isPlayerRebel(player, npc) && imperial_general_condition_generalIsRebel(player, npc) && imperial_general_condition_generalIsDefensive(player, npc);
    }
    public boolean imperial_general_condition_isGeneralImpAndOffense(obj_id player, obj_id npc) throws InterruptedException
    {
        return imperial_general_condition_isPlayerImperial(player, npc) && imperial_general_condition_generalIsImperial(player, npc) && imperial_general_condition_generalIsOffensive(player, npc);
    }
    public boolean imperial_general_condition_isGeneralImpAndDefense(obj_id player, obj_id npc) throws InterruptedException
    {
        return imperial_general_condition_isPlayerImperial(player, npc) && imperial_general_condition_generalIsImperial(player, npc) && imperial_general_condition_generalIsDefensive(player, npc);
    }
    public boolean imperial_general_condition_hasImperialQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        return imperial_general_condition_isPlayerImperial(player, npc) && (imperial_general_condition_hasSpyScoutPatrolQuest(player, npc) || imperial_general_condition_hasSpyDestroyPatrolQuest(player, npc) || imperial_general_condition_hasSpyDestroyPatrolQuestNeedsExplosives(player, npc) || imperial_general_condition_hasDestroyBarricadeQuest(player, npc) || imperial_general_condition_hasDestroyTurretQuest(player, npc));
    }
    public boolean imperial_general_condition_hasSpyScoutPatrolQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        return imperial_general_condition_isSpyPlayer(player, npc) && groundquests.isQuestActive(player, gcw.GCW_SPY_PATROL_SCOUT_QUEST);
    }
    public boolean imperial_general_condition_hasSpyDestroyPatrolQuestNeedsExplosives(obj_id player, obj_id npc) throws InterruptedException
    {
        return imperial_general_condition_isSpyPlayer(player, npc) && groundquests.isQuestActive(player, gcw.GCW_SPY_PATROL_DESTROY_QUEST) && (buff.getBuffStackCount(player, "gcw_spy_destroy_patrol_explosive_stack") < 5);
    }
    public boolean imperial_general_condition_hasSpyDestroyPatrolQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        return imperial_general_condition_isSpyPlayer(player, npc) && groundquests.isQuestActive(player, gcw.GCW_SPY_PATROL_DESTROY_QUEST);
    }
    public boolean imperial_general_condition_hasDestroyTurretQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.isQuestActive(player, gcw.GCW_DESTROY_TURRET);
    }
    public boolean imperial_general_condition_hasDestroyBarricadeQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        return groundquests.isQuestActive(player, gcw.GCW_DESTROY_BARRICADE);
    }
    public boolean imperial_general_condition_hasDestroyQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        return imperial_general_condition_hasSpyDestroyPatrolQuest(player, npc) || imperial_general_condition_hasDestroyTurretQuest(player, npc) || imperial_general_condition_hasDestroyBarricadeQuest(player, npc);
    }
    public boolean imperial_general_condition_enemyPatrolPointsExist(obj_id player, obj_id npc) throws InterruptedException
    {
        obj_id parent = trial.getParent(npc);
        if (!isValidId(parent) || !exists(parent))
        {
            return false;
        }
        if (imperial_general_condition_generalIsDefensive(player, npc))
        {
            obj_id[] patrols = trial.getObjectsInInstanceByObjVar(parent, "gcw.offense_patrol_spawner");
            if (patrols == null || patrols.length <= 0)
            {
                return false;
            }
            return patrols.length > 0;
        }
        else if (imperial_general_condition_generalIsOffensive(player, npc))
        {
            obj_id[] patrols = trial.getObjectsInInstanceByObjVar(parent, "gcw.defense_patrol_spawner");
            if (patrols == null || patrols.length <= 0)
            {
                return false;
            }
            return patrols.length > 0;
        }
        return false;
    }
    public boolean imperial_general_condition_enemyPatrolsExistAndIsCombatPhase(obj_id player, obj_id npc) throws InterruptedException
    {
        return (imperial_general_condition_enemyPatrolPointsExist(player, npc) && imperial_general_condition_isCombatPhase(player, npc));
    }
    public boolean imperial_general_condition_enemyPatrolPointsDestroyed(obj_id player, obj_id npc) throws InterruptedException
    {
        return (imperial_general_condition_isCombatPhase(player, npc) && !imperial_general_condition_enemyPatrolPointsExist(player, npc));
    }
    public boolean imperial_general_condition_canBeMerc(obj_id player, obj_id npc) throws InterruptedException
    {
        int faction_id = pvpGetAlignedFaction(npc);
        String recruiterFaction = factions.getFactionNameByHashCode(faction_id);
        return factions.canChangeNeutralMercenaryStatus(player, recruiterFaction);
    }
    public boolean imperial_general_condition_patrolPointsExistNoScoutQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        return imperial_general_condition_enemyPatrolPointsExist(player, npc) && !groundquests.isQuestActive(player, gcw.GCW_SPY_PATROL_SCOUT_QUEST);
    }
    public boolean imperial_general_condition_patrolPointsExistNoDestroyQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        return imperial_general_condition_enemyPatrolPointsExist(player, npc) && !groundquests.isQuestActive(player, gcw.GCW_SPY_PATROL_DESTROY_QUEST);
    }
    public void imperial_general_action_spawnGuards(obj_id player, obj_id npc) throws InterruptedException
    {
        for (int i = 0; i < 3; i++)
        {
            location guardLocation = groundquests.getRandom2DLocationAroundLocation(npc, 1, 1, 5, 12);
            int mobLevel = getLevel(player);
            obj_id guard = create.object("rebel_emperorsday_ceremony_sentry", guardLocation, mobLevel);
            attachScript(guard, "event.emp_day.factional_guard_self_destruct");
            startCombat(guard, player);
        }
    }
    public void imperial_general_action_grantSpyScoutPatrol(obj_id player, obj_id npc) throws InterruptedException
    {
        if (groundquests.isQuestActive(player, gcw.GCW_SPY_PATROL_SCOUT_QUEST))
        {
            return;
        }
        if (groundquests.hasCompletedQuest(player, gcw.GCW_SPY_PATROL_SCOUT_QUEST))
        {
            groundquests.clearQuest(player, gcw.GCW_SPY_PATROL_SCOUT_QUEST);
        }
        groundquests.grantQuest(player, gcw.GCW_SPY_PATROL_SCOUT_QUEST);
    }
    public void imperial_general_action_grantSpyDestroyPatrol(obj_id player, obj_id npc) throws InterruptedException
    {
        if (groundquests.isQuestActive(player, gcw.GCW_SPY_PATROL_DESTROY_QUEST))
        {
            return;
        }
        if (groundquests.hasCompletedQuest(player, gcw.GCW_SPY_PATROL_DESTROY_QUEST))
        {
            groundquests.clearQuest(player, gcw.GCW_SPY_PATROL_DESTROY_QUEST);
        }
        buff.applyBuffWithStackCount(player, gcw.BUFF_SPY_EXPLOSIVES, 5);
        groundquests.grantQuest(player, gcw.GCW_SPY_PATROL_DESTROY_QUEST);
    }
    public void imperial_general_action_grantMoreSpyDestroyPatrolExplosives(obj_id player, obj_id npc) throws InterruptedException
    {
        buff.applyBuffWithStackCount(player, gcw.BUFF_SPY_EXPLOSIVES, 5);
    }
    public void imperial_general_action_grantCombatDestroyBarricade(obj_id player, obj_id npc) throws InterruptedException
    {
        if (groundquests.isQuestActive(player, gcw.GCW_DESTROY_BARRICADE))
        {
            return;
        }
        if (groundquests.hasCompletedQuest(player, gcw.GCW_DESTROY_BARRICADE))
        {
            groundquests.clearQuest(player, gcw.GCW_DESTROY_BARRICADE);
        }
        groundquests.grantQuest(player, gcw.GCW_DESTROY_BARRICADE);
    }
    public void imperial_general_action_grantCombatDestroyTurret(obj_id player, obj_id npc) throws InterruptedException
    {
        if (groundquests.isQuestActive(player, gcw.GCW_DESTROY_TURRET))
        {
            return;
        }
        if (groundquests.hasCompletedQuest(player, gcw.GCW_DESTROY_TURRET))
        {
            groundquests.clearQuest(player, gcw.GCW_DESTROY_TURRET);
        }
        groundquests.grantQuest(player, gcw.GCW_DESTROY_TURRET);
    }
    public void imperial_general_action_grantEntertainRallyQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        if (groundquests.isQuestActive(player, gcw.GCW_ENTERTAIN_RALLY))
        {
            return;
        }
        if (groundquests.hasCompletedQuest(player, gcw.GCW_ENTERTAIN_RALLY))
        {
            groundquests.clearQuest(player, gcw.GCW_ENTERTAIN_RALLY);
        }
        groundquests.grantQuest(player, gcw.GCW_ENTERTAIN_RALLY);
    }
    public void imperial_general_action_grantEntertainFatigueQuest(obj_id player, obj_id npc) throws InterruptedException
    {
        if (groundquests.isQuestActive(player, gcw.GCW_ENTERTAIN_FATIGUE))
        {
            return;
        }
        if (groundquests.hasCompletedQuest(player, gcw.GCW_ENTERTAIN_FATIGUE))
        {
            groundquests.clearQuest(player, gcw.GCW_ENTERTAIN_FATIGUE);
        }
        groundquests.grantQuest(player, gcw.GCW_ENTERTAIN_FATIGUE);
    }
    public void imperial_general_action_grantEntertainerQuests(obj_id player, obj_id npc) throws InterruptedException
    {
        if (!groundquests.isQuestActive(player, gcw.GCW_ENTERTAIN_FATIGUE))
        {
            if (groundquests.hasCompletedQuest(player, gcw.GCW_ENTERTAIN_FATIGUE))
            {
                groundquests.clearQuest(player, gcw.GCW_ENTERTAIN_FATIGUE);
            }
            groundquests.grantQuest(player, gcw.GCW_ENTERTAIN_FATIGUE);
        }
        if (!groundquests.isQuestActive(player, gcw.GCW_ENTERTAIN_RALLY))
        {
            if (groundquests.hasCompletedQuest(player, gcw.GCW_ENTERTAIN_RALLY))
            {
                groundquests.clearQuest(player, gcw.GCW_ENTERTAIN_RALLY);
            }
            groundquests.grantQuest(player, gcw.GCW_ENTERTAIN_RALLY);
        }
    }
    public void imperial_general_action_grantCombatQuests(obj_id player, obj_id npc) throws InterruptedException
    {
        if (!groundquests.isQuestActive(player, gcw.GCW_DESTROY_BARRICADE))
        {
            if (groundquests.hasCompletedQuest(player, gcw.GCW_DESTROY_BARRICADE))
            {
                groundquests.clearQuest(player, gcw.GCW_DESTROY_BARRICADE);
            }
            groundquests.grantQuest(player, gcw.GCW_DESTROY_BARRICADE);
        }
        if (!groundquests.isQuestActive(player, gcw.GCW_DESTROY_TURRET))
        {
            if (groundquests.hasCompletedQuest(player, gcw.GCW_DESTROY_TURRET))
            {
                groundquests.clearQuest(player, gcw.GCW_DESTROY_TURRET);
            }
            groundquests.grantQuest(player, gcw.GCW_DESTROY_TURRET);
        }
        if (!groundquests.isQuestActive(player, gcw.GCW_ELIMINATE_REBELS))
        {
            if (groundquests.hasCompletedQuest(player, gcw.GCW_ELIMINATE_REBELS))
            {
                groundquests.clearQuest(player, gcw.GCW_ELIMINATE_REBELS);
            }
            groundquests.grantQuest(player, gcw.GCW_ELIMINATE_REBELS);
        }
    }
    public void imperial_general_action_makeImpMercCombatant(obj_id player, obj_id npc) throws InterruptedException
    {
        factions.setNeturalMercenaryCovert(player, factions.FACTION_FLAG_IMPERIAL);
    }
    public void imperial_general_action_makeImpMercSF(obj_id player, obj_id npc) throws InterruptedException
    {
        factions.setNeturalMercenaryOvert(player, factions.FACTION_FLAG_IMPERIAL);
    }
    public void imperial_general_action_grantCombatSpyQuests(obj_id player, obj_id npc) throws InterruptedException
    {
        if (!groundquests.isQuestActive(player, gcw.GCW_DESTROY_BARRICADE))
        {
            if (groundquests.hasCompletedQuest(player, gcw.GCW_DESTROY_BARRICADE))
            {
                groundquests.clearQuest(player, gcw.GCW_DESTROY_BARRICADE);
            }
            groundquests.grantQuest(player, gcw.GCW_DESTROY_BARRICADE);
        }
        if (!groundquests.isQuestActive(player, gcw.GCW_DESTROY_TURRET))
        {
            if (groundquests.hasCompletedQuest(player, gcw.GCW_DESTROY_TURRET))
            {
                groundquests.clearQuest(player, gcw.GCW_DESTROY_TURRET);
            }
            groundquests.grantQuest(player, gcw.GCW_DESTROY_TURRET);
        }
        if (!groundquests.isQuestActive(player, gcw.GCW_ELIMINATE_REBELS))
        {
            if (groundquests.hasCompletedQuest(player, gcw.GCW_ELIMINATE_REBELS))
            {
                groundquests.clearQuest(player, gcw.GCW_ELIMINATE_REBELS);
            }
            groundquests.grantQuest(player, gcw.GCW_ELIMINATE_REBELS);
        }
        if (!groundquests.isQuestActive(player, gcw.GCW_SPY_PATROL_DESTROY_QUEST) && imperial_general_condition_enemyPatrolPointsExist(player, npc))
        {
            if (groundquests.hasCompletedQuest(player, gcw.GCW_SPY_PATROL_DESTROY_QUEST))
            {
                groundquests.clearQuest(player, gcw.GCW_SPY_PATROL_DESTROY_QUEST);
            }
            buff.applyBuffWithStackCount(player, gcw.BUFF_SPY_EXPLOSIVES, 5);
            groundquests.grantQuest(player, gcw.GCW_SPY_PATROL_DESTROY_QUEST);
        }
        if (!groundquests.isQuestActive(player, gcw.GCW_SPY_PATROL_SCOUT_QUEST) && imperial_general_condition_enemyPatrolPointsExist(player, npc))
        {
            if (groundquests.hasCompletedQuest(player, gcw.GCW_SPY_PATROL_SCOUT_QUEST))
            {
                groundquests.clearQuest(player, gcw.GCW_SPY_PATROL_SCOUT_QUEST);
            }
            groundquests.grantQuest(player, gcw.GCW_SPY_PATROL_SCOUT_QUEST);
        }
    }
    public void imperial_general_action_grantDefensiveSpyQuests(obj_id player, obj_id npc) throws InterruptedException
    {
        if (!groundquests.isQuestActive(player, gcw.GCW_ELIMINATE_REBELS))
        {
            if (groundquests.hasCompletedQuest(player, gcw.GCW_ELIMINATE_REBELS))
            {
                groundquests.clearQuest(player, gcw.GCW_ELIMINATE_REBELS);
            }
            groundquests.grantQuest(player, gcw.GCW_ELIMINATE_REBELS);
        }
        if (!groundquests.isQuestActive(player, gcw.GCW_SPY_PATROL_DESTROY_QUEST) && imperial_general_condition_enemyPatrolPointsExist(player, npc))
        {
            if (groundquests.hasCompletedQuest(player, gcw.GCW_SPY_PATROL_DESTROY_QUEST))
            {
                groundquests.clearQuest(player, gcw.GCW_SPY_PATROL_DESTROY_QUEST);
            }
            buff.applyBuffWithStackCount(player, gcw.BUFF_SPY_EXPLOSIVES, 5);
            groundquests.grantQuest(player, gcw.GCW_SPY_PATROL_DESTROY_QUEST);
        }
        if (!groundquests.isQuestActive(player, gcw.GCW_SPY_PATROL_SCOUT_QUEST) && imperial_general_condition_enemyPatrolPointsExist(player, npc))
        {
            if (groundquests.hasCompletedQuest(player, gcw.GCW_SPY_PATROL_SCOUT_QUEST))
            {
                groundquests.clearQuest(player, gcw.GCW_SPY_PATROL_SCOUT_QUEST);
            }
            groundquests.grantQuest(player, gcw.GCW_SPY_PATROL_SCOUT_QUEST);
        }
    }
    public int imperial_general_handleBranch1(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_28"))
        {
            if (imperial_general_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_30");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (imperial_general_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses[] = new string_id[numberOfResponses];
                    if (hasResponse0)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_111");
                    }
                    utils.setScriptVar(player, "conversation.imperial_general.branchId", 2);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int imperial_general_handleBranch2(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_111"))
        {
            if (imperial_general_condition_canBeMerc(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_112");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (imperial_general_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses[] = new string_id[numberOfResponses];
                    if (hasResponse0)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_113");
                    }
                    utils.setScriptVar(player, "conversation.imperial_general.branchId", 3);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_125");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int imperial_general_handleBranch3(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_113"))
        {
            if (imperial_general_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_115");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (imperial_general_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses[] = new string_id[numberOfResponses];
                    if (hasResponse0)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_116");
                    }
                    utils.setScriptVar(player, "conversation.imperial_general.branchId", 4);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int imperial_general_handleBranch4(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_116"))
        {
            if (imperial_general_condition_canBeMerc(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_118");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (imperial_general_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                boolean hasResponse1 = false;
                if (imperial_general_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse1 = true;
                }
                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses[] = new string_id[numberOfResponses];
                    if (hasResponse0)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_119");
                    }
                    if (hasResponse1)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_120");
                    }
                    utils.setScriptVar(player, "conversation.imperial_general.branchId", 5);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_130");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int imperial_general_handleBranch5(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_119"))
        {
            if (imperial_general_condition_canBeMerc(player, npc))
            {
                imperial_general_action_makeImpMercSF(player, npc);
                string_id message = new string_id(c_stringFile, "s_123");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_131");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_120"))
        {
            if (imperial_general_condition_canBeMerc(player, npc))
            {
                doAnimationAction(npc, "handshake_tandem");
                doAnimationAction(player, "handshake_tandem");
                imperial_general_action_makeImpMercCombatant(player, npc);
                string_id message = new string_id(c_stringFile, "s_122");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_132");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int imperial_general_handleBranch12(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_23"))
        {
            if (imperial_general_condition_isCombatPhase(player, npc))
            {
                imperial_general_action_grantEntertainerQuests(player, npc);
                string_id message = new string_id(c_stringFile, "s_26");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_isConstructionPhase(player, npc))
            {
                imperial_general_action_grantEntertainFatigueQuest(player, npc);
                string_id message = new string_id(c_stringFile, "s_185");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_34"))
        {
            if (imperial_general_condition_isConstructionPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_36");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_isCombatPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_183");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_39"))
        {
            if (imperial_general_condition_isCombatPhase(player, npc))
            {
                imperial_general_action_grantCombatQuests(player, npc);
                string_id message = new string_id(c_stringFile, "s_172");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_isConstructionPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_213");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_51"))
        {
            if (imperial_general_condition_isCombatPhase(player, npc))
            {
                imperial_general_action_grantCombatQuests(player, npc);
                string_id message = new string_id(c_stringFile, "s_187");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_isConstructionPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_215");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_61"))
        {
            if (imperial_general_condition_isCombatPhase(player, npc))
            {
                imperial_general_action_grantCombatQuests(player, npc);
                string_id message = new string_id(c_stringFile, "s_190");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_isConstructionPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_217");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_71"))
        {
            if (imperial_general_condition_isCombatPhase(player, npc))
            {
                imperial_general_action_grantCombatQuests(player, npc);
                string_id message = new string_id(c_stringFile, "s_193");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_isConstructionPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_219");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_81"))
        {
            if (imperial_general_condition_isCombatPhase(player, npc))
            {
                imperial_general_action_grantCombatSpyQuests(player, npc);
                string_id message = new string_id(c_stringFile, "s_196");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_isConstructionPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_221");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_102"))
        {
            if (imperial_general_condition_isCombatPhase(player, npc))
            {
                imperial_general_action_grantCombatQuests(player, npc);
                string_id message = new string_id(c_stringFile, "s_201");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_isConstructionPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_223");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_114"))
        {
            if (imperial_general_condition_isCombatPhase(player, npc))
            {
                imperial_general_action_grantCombatQuests(player, npc);
                string_id message = new string_id(c_stringFile, "s_204");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_isConstructionPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_225");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_312"))
        {
            if (imperial_general_condition_enemyPatrolPointsDestroyed(player, npc))
            {
                imperial_general_action_grantMoreSpyDestroyPatrolExplosives(player, npc);
                string_id message = new string_id(c_stringFile, "s_134");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition__defaultCondition(player, npc))
            {
                imperial_general_action_grantMoreSpyDestroyPatrolExplosives(player, npc);
                string_id message = new string_id(c_stringFile, "s_314");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_316"))
        {
            if (imperial_general_condition_enemyPatrolPointsDestroyed(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_135");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_318");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_320"))
        {
            if (imperial_general_condition_hasDestroyBarricadeQuest(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_324");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_hasDestroyTurretQuest(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_326");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_enemyPatrolPointsDestroyed(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_136");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_hasSpyDestroyPatrolQuest(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_322");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int imperial_general_handleBranch39(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
        if (response.equals("s_126"))
        {
            if (imperial_general_condition_isCombatPhase(player, npc))
            {
                imperial_general_action_grantEntertainerQuests(player, npc);
                string_id message = new string_id(c_stringFile, "s_157");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_isConstructionPhase(player, npc))
            {
                imperial_general_action_grantEntertainFatigueQuest(player, npc);
                string_id message = new string_id(c_stringFile, "s_159");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_146"))
        {
            if (imperial_general_condition_isConstructionPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_148");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_isCombatPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_181");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_151"))
        {
            if (imperial_general_condition_isCombatPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_153");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_isConstructionPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_209");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_156"))
        {
            if (imperial_general_condition_isCombatPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_158");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_isConstructionPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_211");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_161"))
        {
            if (imperial_general_condition_isCombatPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_163");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_isConstructionPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_227");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_166"))
        {
            if (imperial_general_condition_isCombatPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_168");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_isConstructionPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_229");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_276"))
        {
            if (imperial_general_condition_isCombatPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_286");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_isConstructionPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_288");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_139"))
        {
            if (imperial_general_condition_isCombatPhase(player, npc))
            {
                imperial_general_action_grantDefensiveSpyQuests(player, npc);
                string_id message = new string_id(c_stringFile, "s_140");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_isConstructionPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_141");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_299"))
        {
            if (imperial_general_condition_isCombatPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_301");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition_isConstructionPhase(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_308");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_142"))
        {
            if (imperial_general_condition_enemyPatrolPointsDestroyed(player, npc))
            {
                imperial_general_action_grantMoreSpyDestroyPatrolExplosives(player, npc);
                string_id message = new string_id(c_stringFile, "s_127");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition__defaultCondition(player, npc))
            {
                imperial_general_action_grantMoreSpyDestroyPatrolExplosives(player, npc);
                string_id message = new string_id(c_stringFile, "s_149");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_145"))
        {
            if (imperial_general_condition_enemyPatrolPointsDestroyed(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_133");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_150");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_147"))
        {
            if (imperial_general_condition_enemyPatrolPointsDestroyed(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_144");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (imperial_general_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_154");
                utils.removeScriptVar(player, "conversation.imperial_general.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if ((!isTangible(self)) || (isPlayer(self)))
        {
            detachScript(self, "conversation.imperial_general");
        }
        setCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setCondition(self, CONDITION_CONVERSABLE);
        dictionary params = new dictionary();
        location loc = getLocation(self);
        params.put("particleLoc", loc);
        int playIconTime = getGameTime();
        params.put("iconMessageTime", playIconTime);
        utils.setScriptVar(self, "iconMessageTime", playIconTime);
        messageTo(self, "playQuestIcon", params, 1.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info menuInfo) throws InterruptedException
    {
        int menu = menuInfo.addRootMenu(menu_info_types.CONVERSE_START, null);
        menu_info_data menuInfoData = menuInfo.getMenuItemById(menu);
        menuInfoData.setServerNotify(false);
        setCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        clearCondition(self, CONDITION_CONVERSABLE);
        detachScript(self, "conversation.imperial_general");
        return SCRIPT_CONTINUE;
    }
    public int playQuestIcon(obj_id self, dictionary params) throws InterruptedException
    {
        gcw.playQuestIconHandler(self, params);
        if (!utils.hasScriptVar(self, "faction"))
        {
            if (!hasObjVar(self, "factionFlag"))
            {
                return SCRIPT_CONTINUE;
            }
            int faction = getIntObjVar(self, "factionFlag");
            utils.setScriptVar(self, "faction", faction);
        }
        return SCRIPT_CONTINUE;
    }
    public boolean npcStartConversation(obj_id player, obj_id npc, String convoName, string_id greetingId, prose_package greetingProse, string_id[] responses) throws InterruptedException
    {
        Object[] objects = new Object[responses.length];
        System.arraycopy(responses, 0, objects, 0, responses.length);
        return npcStartConversation(player, npc, convoName, greetingId, greetingProse, objects);
    }
    public int OnStartNpcConversation(obj_id self, obj_id player) throws InterruptedException
    {
        obj_id npc = self;
        if (ai_lib.isInCombat(npc) || ai_lib.isInCombat(player))
        {
            return SCRIPT_OVERRIDE;
        }
        if (imperial_general_condition_isPlayerNeutral(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_25");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (imperial_general_condition__defaultCondition(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }
            if (hasResponse)
            {
                int responseIndex = 0;
                string_id responses[] = new string_id[numberOfResponses];
                if (hasResponse0)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_28");
                }
                utils.setScriptVar(player, "conversation.imperial_general.branchId", 1);
                npcStartConversation(player, npc, "imperial_general", message, responses);
            }
            else 
            {
                chat.chat(npc, player, message);
            }
            return SCRIPT_CONTINUE;
        }
        if (imperial_general_condition_isGeneralImpAndOffense(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_21");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (imperial_general_condition_isEntertainerPlayer(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }
            boolean hasResponse1 = false;
            if (imperial_general_condition_isTraderPlayer(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse1 = true;
            }
            boolean hasResponse2 = false;
            if (imperial_general_condition_isCommandoPlayer(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse2 = true;
            }
            boolean hasResponse3 = false;
            if (imperial_general_condition_isForceSensitivePlayer(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse3 = true;
            }
            boolean hasResponse4 = false;
            if (imperial_general_condition_isSmugglerPlayer(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse4 = true;
            }
            boolean hasResponse5 = false;
            if (imperial_general_condition_isBountyHunterPlayer(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse5 = true;
            }
            boolean hasResponse6 = false;
            if (imperial_general_condition_isSpyPlayer(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse6 = true;
            }
            boolean hasResponse7 = false;
            if (imperial_general_condition_isMedicPlayer(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse7 = true;
            }
            boolean hasResponse8 = false;
            if (imperial_general_condition_isOfficerPlayer(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse8 = true;
            }
            boolean hasResponse9 = false;
            if (imperial_general_condition_hasSpyDestroyPatrolQuestNeedsExplosives(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse9 = true;
            }
            boolean hasResponse10 = false;
            if (imperial_general_condition_hasSpyScoutPatrolQuest(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse10 = true;
            }
            boolean hasResponse11 = false;
            if (imperial_general_condition_hasDestroyQuest(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse11 = true;
            }
            if (hasResponse)
            {
                int responseIndex = 0;
                string_id responses[] = new string_id[numberOfResponses];
                if (hasResponse0)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_23");
                }
                if (hasResponse1)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_34");
                }
                if (hasResponse2)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_39");
                }
                if (hasResponse3)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_51");
                }
                if (hasResponse4)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_61");
                }
                if (hasResponse5)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_71");
                }
                if (hasResponse6)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_81");
                }
                if (hasResponse7)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_102");
                }
                if (hasResponse8)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_114");
                }
                if (hasResponse9)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_312");
                }
                if (hasResponse10)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_316");
                }
                if (hasResponse11)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_320");
                }
                utils.setScriptVar(player, "conversation.imperial_general.branchId", 12);
                npcStartConversation(player, npc, "imperial_general", message, responses);
            }
            else 
            {
                chat.chat(npc, player, message);
            }
            return SCRIPT_CONTINUE;
        }
        if (imperial_general_condition_isGeneralImpAndDefense(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_124");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (imperial_general_condition_isEntertainerPlayer(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }
            boolean hasResponse1 = false;
            if (imperial_general_condition_isTraderPlayer(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse1 = true;
            }
            boolean hasResponse2 = false;
            if (imperial_general_condition_isCommandoPlayer(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse2 = true;
            }
            boolean hasResponse3 = false;
            if (imperial_general_condition_isForceSensitivePlayer(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse3 = true;
            }
            boolean hasResponse4 = false;
            if (imperial_general_condition_isSmugglerPlayer(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse4 = true;
            }
            boolean hasResponse5 = false;
            if (imperial_general_condition_isBountyHunterPlayer(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse5 = true;
            }
            boolean hasResponse6 = false;
            if (imperial_general_condition_isMedicPlayer(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse6 = true;
            }
            boolean hasResponse7 = false;
            if (imperial_general_condition_isSpyPlayer(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse7 = true;
            }
            boolean hasResponse8 = false;
            if (imperial_general_condition_isOfficerPlayer(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse8 = true;
            }
            boolean hasResponse9 = false;
            if (imperial_general_condition_hasSpyDestroyPatrolQuestNeedsExplosives(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse9 = true;
            }
            boolean hasResponse10 = false;
            if (imperial_general_condition_hasSpyScoutPatrolQuest(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse10 = true;
            }
            boolean hasResponse11 = false;
            if (imperial_general_condition_hasSpyDestroyPatrolQuest(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse11 = true;
            }
            if (hasResponse)
            {
                int responseIndex = 0;
                string_id responses[] = new string_id[numberOfResponses];
                if (hasResponse0)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_126");
                }
                if (hasResponse1)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_146");
                }
                if (hasResponse2)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_151");
                }
                if (hasResponse3)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_156");
                }
                if (hasResponse4)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_161");
                }
                if (hasResponse5)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_166");
                }
                if (hasResponse6)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_276");
                }
                if (hasResponse7)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_139");
                }
                if (hasResponse8)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_299");
                }
                if (hasResponse9)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_142");
                }
                if (hasResponse10)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_145");
                }
                if (hasResponse11)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_147");
                }
                utils.setScriptVar(player, "conversation.imperial_general.branchId", 39);
                npcStartConversation(player, npc, "imperial_general", message, responses);
            }
            else 
            {
                chat.chat(npc, player, message);
            }
            return SCRIPT_CONTINUE;
        }
        if (imperial_general_condition__defaultCondition(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_310");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
    {
        if (!conversationId.equals("imperial_general"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.imperial_general.branchId");
        if (branchId == 1 && imperial_general_handleBranch1(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 2 && imperial_general_handleBranch2(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 3 && imperial_general_handleBranch3(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 4 && imperial_general_handleBranch4(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 5 && imperial_general_handleBranch5(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 12 && imperial_general_handleBranch12(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 39 && imperial_general_handleBranch39(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.imperial_general.branchId");
        return SCRIPT_CONTINUE;
    }
}
