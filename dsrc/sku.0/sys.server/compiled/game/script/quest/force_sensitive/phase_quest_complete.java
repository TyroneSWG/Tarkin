package script.quest.force_sensitive;

import script.library.fs_quests;
import script.library.quests;
import script.obj_id;

public class phase_quest_complete extends script.base_script
{
    public phase_quest_complete()
    {
    }
    public int OnQuestActivated(obj_id self, int questRow) throws InterruptedException
    {
        if (quests.isMyQuest(questRow, "quest.force_sensitive.phase_quest_complete"))
        {
            String questName = quests.getDataEntry(questRow, "NAME");
            String objvarName = "quest." + questName + ".target";
            String target = null;
            if (hasObjVar(self, objvarName))
            {
                target = getStringObjVar(self, objvarName);
            }
            if (target == null)
            {
                target = quests.getDataEntry(questRow, "TARGET");
            }
            if (target == null)
            {
                target = new String("unspecified quest");
            }
            fs_quests.setQuestCompleted(self, target);
            quests.complete(questName, self, true);
        }
        return SCRIPT_CONTINUE;
    }
}
