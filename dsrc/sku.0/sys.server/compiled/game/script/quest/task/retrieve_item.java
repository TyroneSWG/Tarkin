package script.quest.task;

import script.library.quests;
import script.library.utils;
import script.obj_id;

public class retrieve_item extends script.base_script
{
    public retrieve_item()
    {
    }
    public int OnContainerChildGainItem(obj_id self, obj_id item, obj_id source, obj_id transferer) throws InterruptedException
    {
        LOG("newquests", "received item(" + item + ", " + getTemplateName(item) + ") source(" + source + ", " + getTemplateName(source) + ") transferer(" + transferer + ", " + getTemplateName(transferer) + ")");
        LOG("newquests", "checking active retrieve_item tasks for a match!");
        String[] activeTasks = quests.getActiveQuestsWithScript("quest.task.retrieve_item", self);
        int i;
        for (i = 0; i < activeTasks.length; ++i)
        {
            LOG("newquests", "checking " + activeTasks[i]);
            boolean taskComplete = false;
            int count = 0;
            if (hasObjVar(self, "quest." + activeTasks[i] + ".current_count"))
            {
                count = getIntObjVar(self, "quest." + activeTasks[i] + ".current_count");
            }
            int targetCount = 1;
            if (hasObjVar(self, "quest." + activeTasks[i] + ".parameter"))
            {
                targetCount = getIntObjVar(self, "quest." + activeTasks[i] + ".parameter");
                LOG("newquests", "task objvar parameter overriding data table. parameter = " + targetCount);
            }
            else 
            {
                String countString = quests.getDataEntry(activeTasks[i], "PARAMETER");
                if (countString != null && countString.length() > 0)
                {
                    int t = utils.stringToInt(countString);
                    if (t > -1)
                    {
                        targetCount = t;
                        LOG("newquests", "target count is set in the PARAMETER column to " + t);
                    }
                }
            }
            if (hasObjVar(self, "quest." + activeTasks[i] + ".target_id"))
            {
                LOG("newquests", "a target_id is overriding the TARGET column");
                obj_id targetId = getObjIdObjVar(self, activeTasks[i] + ".target_id");
                if (targetId == item)
                {
                    LOG("newquests", "this item (" + getTemplateName(item) + ") is part of this retrieve_item task");
                    count++;
                }
                else 
                {
                    LOG("newquests", "this item(" + getTemplateName(item) + ") is not part of the retrieve_item task");
                }
            }
            else if (hasObjVar(self, "quest." + activeTasks[i] + ".target_object_template"))
            {
                String targetObjectTemplate = getStringObjVar(self, "quest." + activeTasks[i] + ".target_object_template");
                String wantItem = getTemplateName(item);
                LOG("newquests", "the TARGET column is overidden by target_object_template(" + targetObjectTemplate + ")");
                if (wantItem.equals(targetObjectTemplate))
                {
                    LOG("newquests", "this item(" + wantItem + ") is part of this retrieve_item task");
                    count++;
                }
            }
            else 
            {
                String questItem = quests.getDataEntry(activeTasks[i], "TARGET");
                String wantItem = getTemplateName(item);
                LOG("newquests", "the data table for " + activeTasks[i] + " specifies an object of type \"" + questItem + "\" to complete this task. The player has received \"" + wantItem + "\"");
                if (wantItem.equals(questItem))
                {
                    LOG("newquests", "increasing the item count for this task");
                    count++;
                }
            }
            LOG("newquests", "checking for objvar quest." + activeTasks[i] + ".quest_item_target");
            if (hasObjVar(item, "quest." + activeTasks[i] + ".quest_item_target"))
            {
                obj_id itemTarget = getObjIdObjVar(item, "quest." + activeTasks[i] + ".quest_item_target");
                LOG("newquests", "the item has a quest_item_target objvar, it might be part of this retrieve_item task");
                if (itemTarget == self)
                {
                    LOG("newquests", "the item has a quest_item_target objvar identifying this player. It is part of this retrieve_item task");
                    count++;
                }
            }
            if (count >= targetCount)
            {
                LOG("newquests", "This retrieve_item task wanted " + targetCount + " items and the player has retrieved " + count + " items. Completing the task");
                quests.complete(activeTasks[i], self, true);
                break;
            }
            else 
            {
                LOG("newquests", "This retrieve_item task wanted " + targetCount + " items and the player has retrieved " + count + " items. Continuing the task");
                setObjVar(self, "quest." + activeTasks[i] + ".current_count", count);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
