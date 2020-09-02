package script.quest.task;

import script.draft_schematic;
import script.library.quests;
import script.library.utils;
import script.obj_id;

public class craft extends script.base_script
{
    public craft()
    {
    }
    public int OnQuestActivated(obj_id self, int questRow) throws InterruptedException
    {
        LOG("newquests", "OnQUestActivated(" + quests.getDataEntry(questRow, "NAME"));
        String questName = quests.getDataEntry(questRow, "NAME");
        String craftedObjectTemplateName = null;
        String objvarname = "quest." + questName + ".target";
        if (hasObjVar(self, objvarname))
        {
            craftedObjectTemplateName = getStringObjVar(self, objvarname);
        }
        else 
        {
            craftedObjectTemplateName = quests.getDataEntry(questRow, "TARGET");
        }
        int craftedObjectCount = 1;
        objvarname = "quest." + questName + ".parameter";
        if (hasObjVar(self, objvarname))
        {
            craftedObjectCount = getIntObjVar(self, objvarname);
        }
        else 
        {
            String countString = quests.getDataEntry(questRow, "PARAMETER");
            craftedObjectCount = utils.stringToInt(countString);
            if (craftedObjectCount < 1)
            {
                craftedObjectCount = 1;
            }
        }
        if (craftedObjectTemplateName != null && craftedObjectTemplateName.length() > 0)
        {
            int objectTemplateCrc = getObjectTemplateCrc(craftedObjectTemplateName);
            objvarname = "quest." + questName + ".count";
            setObjVar(self, objvarname, craftedObjectCount);
            objvarname = "quest." + questName + ".current_count";
            int currentCount = 0;
            setObjVar(self, objvarname, currentCount);
            objvarname = "quest." + questName + ".objectTemplateCrc";
            setObjVar(self, objvarname, objectTemplateCrc);
        }
        else 
        {
            LOG("newquests", "craft - OnQuestActivated() - could not determine object to craft");
        }
        return SCRIPT_CONTINUE;
    }
    public int OnCraftedPrototype(obj_id self, obj_id prototypeObject, draft_schematic manufacturingSchematic) throws InterruptedException
    {
        LOG("newquests", "OnCraftedPrototype(" + prototypeObject + ", " + manufacturingSchematic + ")");
        LOG("newquests", "OnCraftedPrototype() player has crafted a " + getTemplateName(prototypeObject));
        String[] activeTasks = quests.getActiveQuestsWithScript("quest.task.craft", self);
        if (activeTasks != null && activeTasks.length > 0)
        {
            int iter = 0;
            int prototypeObjectTemplateCrc = getObjectTemplateCrc(getTemplateName(prototypeObject));
            for (iter = 0; iter < activeTasks.length; ++iter)
            {
                String objvarname = "quest." + activeTasks[iter] + ".objectTemplateCrc";
                if (hasObjVar(self, objvarname))
                {
                    int objectTemplateCrc = getIntObjVar(self, objvarname);
                    if (objectTemplateCrc == prototypeObjectTemplateCrc)
                    {
                        int count = 1;
                        objvarname = "quest." + activeTasks[iter] + ".count";
                        if (hasObjVar(self, objvarname))
                        {
                            count = getIntObjVar(self, objvarname);
                            if (count < 1)
                            {
                                count = 1;
                            }
                        }
                        int currentCount = 0;
                        objvarname = "quest." + activeTasks[iter] + ".current_count";
                        if (hasObjVar(self, objvarname))
                        {
                            currentCount = getIntObjVar(self, objvarname);
                        }
                        currentCount++;
                        setObjVar(self, objvarname, currentCount);
                        if (currentCount >= count)
                        {
                            quests.complete(activeTasks[iter], self, true);
                            break;
                        }
                        else 
                        {
                            LOG("newquests", "cract - OnCraftedPrototype - the quest requires " + count + " objects to be crafted. The player has crafted " + currentCount + " objects");
                        }
                    }
                    else 
                    {
                        LOG("newquests", "craft - OnCraftedPrototype - the crafted object template crc (" + prototypeObjectTemplateCrc + " does not match the required crafted object template crc " + objectTemplateCrc);
                    }
                }
                else 
                {
                    LOG("newquests", "craft - OnCraftedPrototype - could not determine what object needs to be crafted, " + objvarname + " was not found");
                }
            }
        }
        else 
        {
            LOG("newquests", "craft - OnCraftedPrototype - no active tasks for quest.task.craft");
        }
        return SCRIPT_CONTINUE;
    }
}
