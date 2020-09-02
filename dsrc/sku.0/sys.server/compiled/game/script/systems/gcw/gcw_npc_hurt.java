package script.systems.gcw;

import script.*;
import script.library.factions;
import script.library.gcw;
import script.library.groundquests;
import script.library.utils;

public class gcw_npc_hurt extends script.base_script
{
    public gcw_npc_hurt()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        messageTo(self, "handleSetup", null, 1.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int handleSetup(obj_id self, dictionary params) throws InterruptedException
    {
        gcw.playQuestIconParticle(self);
        utils.setObjVar(self, gcw.GCW_OBJECT_REPAIR_COUNT, 0);
        setPosture(self, POSTURE_KNOCKED_DOWN);
        setCreatureStatic(self, true);
        messageTo(self, "removeBarkingScripts", null, 4.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int removeBarkingScripts(obj_id self, dictionary params) throws InterruptedException
    {
        if (hasScript(self, "ai.soldier"))
        {
            detachScript(self, "ai.soldier");
        }
        if (hasScript(self, "npc.stormtrooper_respect.stormtrooper_respect"))
        {
            detachScript(self, "npc.stormtrooper_respect.stormtrooper_respect");
        }
        return SCRIPT_CONTINUE;
    }
    public int playQuestIcon(obj_id self, dictionary params) throws InterruptedException
    {
        if (!isIdValid(self) || !exists(self))
        {
            return SCRIPT_CONTINUE;
        }
        int repairs = getIntObjVar(self, gcw.GCW_OBJECT_REPAIR_COUNT);
        if (repairs < 1)
        {
            gcw.playQuestIconHandler(self, params);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        LOG("gcw_npc_hurt", "OnObjectMenuRequest");
        if (!utils.hasScriptVar(self, "faction"))
        {
            LOG("gcw_npc_hurt", "no faction on npc obj");
            return SCRIPT_CONTINUE;
        }
        if (!factions.isPlayerSameGcwFactionAsSchedulerObject(player, self))
        {
            LOG("gcw_npc_hurt", "faction invalid on turret obj");
            return SCRIPT_CONTINUE;
        }
        else 
        {
            if (!utils.isProfession(player, utils.MEDIC))
            {
                return SCRIPT_CONTINUE;
            }
            LOG("gcw_npc_hurt", "player is MEDIC");
            int repairs = getIntObjVar(self, gcw.GCW_OBJECT_REPAIR_COUNT);
            if (repairs > 0)
            {
                LOG("gcw_npc_hurt", "OnObjectMenuRequest no repair needed");
                sendSystemMessage(player, gcw.SID_DOESNT_NEED_HEALING);
                return SCRIPT_CONTINUE;
            }
            menu_info_data data = mi.getMenuItemByType(menu_info_types.ITEM_USE);
            int mnu = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("spam", "heal"));
            if (data != null)
            {
                data.setServerNotify(true);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        LOG("gcw_npc_hurt", "OnObjectMenuSelect");
        if (!isIdValid(player) || !exists(player) || isIncapacitated(player) || isDead(player) || factions.isOnLeave(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (!utils.hasScriptVar(self, "faction"))
        {
            LOG("gcw_npc_hurt", "no faction on patrol obj");
            return SCRIPT_CONTINUE;
        }
        int faction = utils.getIntScriptVar(self, "faction");
        if (faction < 0)
        {
            LOG("gcw_npc_hurt", "faction invalid on patrol obj");
            return SCRIPT_CONTINUE;
        }
        if (!factions.isPlayerSameGcwFactionAsSchedulerObject(player, self))
        {
            LOG("gcw_npc_hurt", "faction invalid on turret obj");
            return SCRIPT_CONTINUE;
        }
        else 
        {
            if (!utils.isProfession(player, utils.MEDIC))
            {
                return SCRIPT_CONTINUE;
            }
            LOG("gcw_npc_hurt", "player is MEDIC");
            int repairs = getIntObjVar(self, gcw.GCW_OBJECT_REPAIR_COUNT);
            if (repairs > 0)
            {
                sendSystemMessage(player, gcw.SID_DOESNT_NEED_HEALING);
                return SCRIPT_CONTINUE;
            }
            if (groundquests.isQuestActive(player, gcw.GCW_MEDIC_HEAL_QUEST))
            {
                LOG("gcw_npc_hurt", "player isQuestActive");
                gcw.useGcwObjectForQuest(player, self, gcw.GCW_MEDIC_HEAL_QUEST);
                return SCRIPT_CONTINUE;
            }
            if (groundquests.hasCompletedQuest(player, gcw.GCW_MEDIC_HEAL_QUEST))
            {
                LOG("gcw_npc_hurt", "player groundquests.hasCompletedQuest(player, gcw.GCW_MEDIC_HEAL_QUEST): " + groundquests.hasCompletedQuest(player, gcw.GCW_MEDIC_HEAL_QUEST));
                groundquests.clearQuest(player, gcw.GCW_MEDIC_HEAL_QUEST);
            }
            if (!groundquests.isQuestActive(player, gcw.GCW_MEDIC_HEAL_QUEST))
            {
                LOG("gcw_npc_hurt", "player !groundquests.isQuestActive(player, gcw.GCW_MEDIC_HEAL_QUEST): " + !groundquests.isQuestActive(player, gcw.GCW_MEDIC_HEAL_QUEST));
                groundquests.grantQuest(player, gcw.GCW_MEDIC_HEAL_QUEST);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int repairComplete(obj_id self, dictionary params) throws InterruptedException
    {
        if (!isIdValid(self))
        {
            return SCRIPT_CONTINUE;
        }
        int repairs = getIntObjVar(self, gcw.GCW_OBJECT_REPAIR_COUNT);
        LOG("gcw_npc_hurt", "repairComplete self: " + self + " repairs: " + repairs);
        if (repairs > 0)
        {
            LOG("gcw_npc_hurt", "repairComplete repairs > 0");
            obj_id creator = utils.getObjIdScriptVar(self, "creator");
            if (isIdValid(creator))
            {
                messageTo(creator, "createHealedNPC", null, 0.0f, false);
            }
            destroyObject(self);
        }
        return SCRIPT_CONTINUE;
    }
}
