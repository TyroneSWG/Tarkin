package script.systems.fs_quest.fs_quests_sad;

import script.dictionary;
import script.library.fs_quests_sad;
import script.library.geiger;
import script.library.quests;
import script.obj_id;
import script.string_id;

public class cleanup extends script.base_script
{
    public cleanup()
    {
    }
    public void cleanup(obj_id self) throws InterruptedException
    {
        clearWaypoint(self);
        if (!fs_quests_sad.hasCompletedAllTasks(self))
        {
            string_id sid = new string_id("quest/quest_journal/fs_quests_sad", "wrong_phase");
            sendSystemMessage(self, sid);
            int questId = quests.getQuestId("fs_quests_sad_tasks");
            if (questId < 0)
            {
                return;
            }
            clearCompletedQuest(self, questId);
            if (isQuestActive(self, questId))
            {
                quests.deactivate("fs_quests_sad_tasks", self);
            }
            for (int i = 1; i <= 8; i++)
            {
                questId = quests.getQuestId("fs_quests_sad_task" + i);
                clearCompletedQuest(self, questId);
                if (isQuestActive(self, questId))
                {
                    quests.deactivate("fs_quests_sad_task" + i, self);
                }
                questId = quests.getQuestId("fs_quests_sad_return" + i);
                clearCompletedQuest(self, questId);
                if (isQuestActive(self, questId))
                {
                    quests.deactivate("fs_quests_sad_return" + i, self);
                }
            }
            removeObjVar(self, fs_quests_sad.SAD_OBJVAR_TASK_ACTIVE);
            removeObjVar(self, fs_quests_sad.SAD_OBJVAR_TIMESTAMP);
            removeObjVar(self, fs_quests_sad.SAD_OBJVAR_TAKEN_SINCE_TIMESTAMP);
        }
        if (hasObjVar(self, geiger.OBJVAR_GEIGER_OBJECT))
        {
            if (getObjIdObjVar(self, geiger.OBJVAR_GEIGER_OBJECT) != null)
            {
                obj_id geigerID = getObjIdObjVar(self, geiger.OBJVAR_GEIGER_OBJECT);
                destroyObject(geigerID);
            }
            removeObjVar(self, geiger.OBJVAR_GEIGER_OBJECT);
            removeObjVar(self, geiger.OBJVAR_GEIGER_PLAYER);
            removeObjVar(self, geiger.OBJVAR_GEIGER_PID);
            geiger.clearGeiger(self);
        }
        detachScript(self, "systems.fs_quest.fs_quests_sad.cleanup");
    }
    public int msgQuestAbortPhaseChange(obj_id self, dictionary params) throws InterruptedException
    {
        cleanup(self);
        return SCRIPT_CONTINUE;
    }
    public void clearWaypoint(obj_id player) throws InterruptedException
    {
        String questName = "fs_quests_sad_return1";
        for (int i = 1; i <= 8; i++)
        {
            if (quests.isActive("fs_quests_sad_return" + i, player))
            {
                questName = "fs_quests_sad_return" + i;
            }
        }
        String waypointObjVar = "quest." + questName + ".waypoint";
        questName = "fs_quests_sad_task1";
        for (int x = 1; x <= 8; x++)
        {
            if (quests.isActive("fs_quests_sad_task" + x, player))
            {
                questName = "fs_quests_sad_task" + x;
            }
        }
        String waypointObjVar2 = "quest." + questName + ".waypoint";
        if (hasObjVar(player, waypointObjVar))
        {
            obj_id point = getObjIdObjVar(player, waypointObjVar);
            destroyWaypointInDatapad(point, player);
            removeObjVar(player, waypointObjVar);
        }
        if (hasObjVar(player, waypointObjVar2))
        {
            obj_id point2 = getObjIdObjVar(player, waypointObjVar);
            destroyWaypointInDatapad(point2, player);
            removeObjVar(player, waypointObjVar2);
        }
        return;
    }
}
