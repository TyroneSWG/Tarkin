package script.quest.task.fs_quest_cs;

import script.dictionary;
import script.library.quests;
import script.library.utils;
import script.library.xp;
import script.location;
import script.obj_id;

public class fs_cs_kill5_guards extends script.base_script
{
    public fs_cs_kill5_guards()
    {
    }
    public int receiveCreditForKill(obj_id self, dictionary params) throws InterruptedException
    {
        LOG("newquests", "fs_cs_kill5_guards: receiveCreditForKill()");
        if (params != null)
        {
            location creatureLoc = params.getLocation("location");
            if (getDistance(getLocation(self), creatureLoc) > xp.MAX_DISTANCE)
            {
                return SCRIPT_CONTINUE;
            }
            obj_id target = params.getObjId("target");
            if (isIdValid(target))
            {
                final String targetType = params.getString("creatureName");
                if (targetType != null && targetType.length() > 0)
                {
                    String objvarName = "quest.destroy_multi." + targetType;
                    if (hasObjVar(self, objvarName))
                    {
                        if (!hasObjVar(self, "fs_cs.currentCamp"))
                        {
                            return SCRIPT_CONTINUE;
                        }
                        obj_id myCamp = getObjIdObjVar(self, "fs_cs.currentCamp");
                        if (!isIdValid(myCamp) || !exists(myCamp))
                        {
                            return SCRIPT_CONTINUE;
                        }
                        obj_id spawner = params.getObjId("spawnedBy");
                        if (!isIdValid(spawner))
                        {
                            return SCRIPT_CONTINUE;
                        }
                        if (!hasObjVar(spawner, "quest_spawner.parent"))
                        {
                            return SCRIPT_CONTINUE;
                        }
                        obj_id spawnersCamp = getObjIdObjVar(spawner, "quest_spawner.parent");
                        if (!isIdValid(spawnersCamp) || !exists(spawnersCamp))
                        {
                            return SCRIPT_CONTINUE;
                        }
                        if (myCamp != spawnersCamp)
                        {
                            return SCRIPT_CONTINUE;
                        }
                        String[] questNames = getStringArrayObjVar(self, objvarName);
                        if (questNames != null && questNames.length > 0)
                        {
                            int iter = 0;
                            for (iter = 0; iter < questNames.length; ++iter)
                            {
                                String questName = questNames[iter];
                                if (quests.isActive(questName, self))
                                {
                                    objvarName = "quest." + questName + ".parameter";
                                    if (hasObjVar(self, objvarName))
                                    {
                                        int killsRemaining = getIntObjVar(self, objvarName);
                                        killsRemaining--;
                                        LOG("newquests", "destroy_multi: onReceiveCreditForKill: kills remaining = " + killsRemaining);
                                        if (killsRemaining < 1)
                                        {
                                            String[] newQuestNames = new String[questNames.length - 1];
                                            int ni = 0;
                                            int index = 0;
                                            for (ni = 0; ni < questNames.length; ++ni)
                                            {
                                                if (ni != iter)
                                                {
                                                    newQuestNames[index] = questNames[ni];
                                                    ++index;
                                                }
                                            }
                                            if (newQuestNames.length < 1)
                                            {
                                                objvarName = "quest.destroy_multi." + targetType;
                                                removeObjVar(self, objvarName);
                                            }
                                            quests.complete(questName, self, true);
                                        }
                                        else 
                                        {
                                            objvarName = "quest." + questName + ".parameter";
                                            setObjVar(self, objvarName, killsRemaining);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        else 
                        {
                            LOG("newquests", "destroy_multi: onReceiveCreditForKill: questNames objvar \"" + objvarName + "\" string array is empty");
                        }
                    }
                    else 
                    {
                        LOG("newquests", "destroy_multi: onReceiveCreditForKill: could not retrieve objvar " + objvarName);
                    }
                }
                else 
                {
                    LOG("newquests", "destroy_multi: onReceiveCreditForKill: could not determine target type name from target creature");
                }
            }
            else 
            {
                LOG("newquests", "destroy_multi: onReceiveCreditForKill: invalid target specified in messageTo parameters");
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnQuestActivated(obj_id self, int questRow) throws InterruptedException
    {
        if (quests.isMyQuest(questRow, "quest.task.destroy_multi"))
        {
            String questName = quests.getDataEntry(questRow, "NAME");
            LOG("newquests", "OnQuestActivated(" + questName + ")");
            String target = null;
            String objvarName = "quest." + questName + ".target";
            if (hasObjVar(self, objvarName))
            {
                target = getStringObjVar(self, objvarName);
            }
            else 
            {
                target = quests.getDataEntry(questRow, "TARGET");
            }
            int killCount = 0;
            if (target != null && target.length() > 0)
            {
                setObjVar(self, objvarName, target);
                objvarName = "quest." + questName + ".parameter";
                if (hasObjVar(self, objvarName))
                {
                    killCount = getIntObjVar(self, objvarName);
                }
                else 
                {
                    String countName = quests.getDataEntry(questRow, "PARAMETER");
                    if (countName != null && countName.length() > 0)
                    {
                        killCount = utils.stringToInt(countName);
                    }
                }
                if (killCount > 0)
                {
                    setObjVar(self, objvarName, killCount);
                    objvarName = "quest.destroy_multi." + target;
                    String[] questNames = getStringArrayObjVar(self, objvarName);
                    String[] newQuestNames = null;
                    if (questNames == null)
                    {
                        newQuestNames = new String[1];
                        newQuestNames[0] = questName;
                    }
                    else 
                    {
                        newQuestNames = new String[questNames.length + 1];
                        int iter = 0;
                        for (iter = 0; iter < questNames.length; ++iter)
                        {
                            newQuestNames[iter] = questNames[iter];
                        }
                        newQuestNames[questNames.length] = questName;
                    }
                    if (newQuestNames != null && newQuestNames.length > 0)
                    {
                        setObjVar(self, objvarName, newQuestNames);
                    }
                    else 
                    {
                        LOG("newquests", "destroy_multi: OnQuestActivated: Could not set questnames string array objvar");
                    }
                }
                else 
                {
                    LOG("newquests", "destroy_multi: OnQuestActivated: could not determine kill count");
                }
            }
            else 
            {
                LOG("newquests", "destroy_multi: OnQuestActivated: could not determine target creature type");
            }
        }
        return SCRIPT_CONTINUE;
    }
}
