package script.quest.task;

import script.dictionary;
import script.library.quests;
import script.library.utils;
import script.obj_id;

public class survey extends script.base_script
{
    public survey()
    {
    }
    public int surveyComplete(obj_id self, dictionary params) throws InterruptedException
    {
        LOG("newquests", "survey - surveyComplete()");
        String resourceType = params.getString("resource_type");
        String resourceClass = params.getString("resource_class");
        float efficiency = params.getFloat("efficiency");
        obj_id resource = params.getObjId("resource_type_object");
        String[] activeTasks = quests.getActiveQuestsWithScript("quest.task.survey", self);
        LOG("newquests", "checking " + activeTasks.length + " tasks for completion");
        if (activeTasks != null && activeTasks.length > 0)
        {
            int iter = 0;
            for (iter = 0; iter < activeTasks.length; ++iter)
            {
                LOG("newquests", "survey - Checking task " + activeTasks[iter] + " for completion");
                float efficiencyParameter = 0.0f;
                String objvarname = "quest." + activeTasks[iter] + ".parameter";
                if (hasObjVar(self, objvarname))
                {
                    efficiencyParameter = getFloatObjVar(self, objvarname);
                }
                else 
                {
                    String efficiencyParameterString = quests.getDataEntry(activeTasks[iter], "PARAMETER");
                    if (efficiencyParameterString != null && efficiencyParameterString.length() > 0)
                    {
                        efficiencyParameter = utils.stringToFloat(efficiencyParameterString);
                    }
                }
                LOG("newquests", "survey - " + activeTasks[iter] + " - efficiency=" + efficiency + " efficienty required=" + efficiencyParameter);
                if (efficiency > efficiencyParameter)
                {
                    String resourceTarget = null;
                    objvarname = "quest." + activeTasks[iter] + ".target";
                    if (hasObjVar(self, objvarname))
                    {
                        resourceTarget = getStringObjVar(self, objvarname);
                    }
                    else 
                    {
                        resourceTarget = quests.getDataEntry(activeTasks[iter], "TARGET");
                        LOG("newquests", "survey - " + activeTasks[iter] + " efficiency qualifies, validating resource type " + getResourceName(resource) + " against target " + resourceTarget);
                    }
                    if (resourceTarget != null && resourceTarget.length() > 0)
                    {
                        if (isResourceDerivedFrom(resource, resourceTarget))
                        {
                            LOG("newquests", "survey - " + activeTasks[iter] + " qualifies! Completing task!");
                            objvarname = "quest.survey.efficency";
                            setObjVar(self, objvarname, efficiency);
                            quests.complete(activeTasks[iter], self, true);
                            break;
                        }
                        else 
                        {
                            LOG("newquests", "survey - " + getResourceName(resource) + " does not match target " + resourceTarget);
                        }
                    }
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
