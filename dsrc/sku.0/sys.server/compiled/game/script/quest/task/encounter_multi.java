package script.quest.task;

import script.dictionary;
import script.library.create;
import script.library.locations;
import script.library.quests;
import script.library.utils;
import script.location;
import script.obj_id;

import java.util.Vector;

public class encounter_multi extends script.base_script
{
    public encounter_multi()
    {
    }
    public int OnQuestActivated(obj_id self, int questRow) throws InterruptedException
    {
        if (quests.isMyQuest(questRow, "quest.task.encounter_multi"))
        {
            String questName = quests.getDataEntry(questRow, "NAME");
            String objvarName = "quest." + questName + ".target";
            String target = null;
            if (hasObjVar(self, objvarName))
            {
                target = getStringObjVar(self, objvarName);
            }
            else 
            {
                target = quests.getDataEntry(questRow, "TARGET");
            }
            if (target != null && target.length() > 0)
            {
                int rowCount = dataTableGetNumRows(target);
                if (rowCount > 0)
                {
                    int spawnCount = 0;
                    int iter = 0;
                    for (iter = 0; iter < rowCount; ++iter)
                    {
                        int count = dataTableGetInt(target, iter, "COUNT");
                        if (count > 0)
                        {
                            spawnCount += count;
                        }
                    }
                    if (spawnCount > 0)
                    {
                        objvarName = "quest." + questName + ".spawnCount";
                        setObjVar(self, objvarName, spawnCount);
                        objvarName = "quest." + questName + "spawned";
                        int spawned = 0;
                        setObjVar(self, objvarName, spawned);
                        dictionary spawnEventParameters = new dictionary();
                        spawnEventParameters.put("questName", questName);
                        spawnEventParameters.put("datatable", target);
                        int currentRow = 0;
                        spawnEventParameters.put("currentRow", currentRow);
                        int currentSpawn = 0;
                        spawnEventParameters.put("currentSpawn", currentSpawn);
                        spawnEventParameters.put("spawner", self);
                        float yaw = getYaw(self);
                        location spawnLocation = utils.getLocationInArc(self, yaw - 30, yaw + 30, 48.0f);
                        location l = locations.getGoodLocationAroundLocation(spawnLocation, 8, 8, 48, 48, false, true);
                        if (l != null)
                        {
                            spawnEventParameters.put("location", l);
                            messageTo(self, "encounterMultiSpawnEvent", spawnEventParameters, 1.0f, false);
                        }
                        else 
                        {
                            LOG("newquests", "encounter_multi - OnQuestActivate() - failed to find suitable location for " + target + " for encounter spawn, failing task");
                            quests.complete(questName, self, false);
                        }
                    }
                    else 
                    {
                        LOG("newquests", "encounter_multi - OnQuestActivate() - no spawns defined in data table " + target + ", failing task");
                        quests.complete(questName, self, false);
                    }
                }
                else 
                {
                    LOG("newquests", "encounter_multi - OnQuestActivate() - failed to retrieve data table information from " + target + " for encounter spawn, failing task");
                    quests.complete(questName, self, false);
                }
            }
            else 
            {
                LOG("newquests", "encounter_multi - OnQuestActivated() - could not determine datatable to spawn encounter, failing task");
                quests.complete(questName, self, false);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnLogout(obj_id self) throws InterruptedException
    {
        Vector spawnedCreatures = utils.getResizeableObjIdArrayScriptVar(self, "encounter_multi_creatures");
        if (spawnedCreatures != null)
        {
            int iter = 0;
            for (iter = 0; iter < spawnedCreatures.size(); ++iter)
            {
                messageTo(((obj_id)spawnedCreatures.get(iter)), "cleanup", null, 0.0f, false);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int encounterMultiSpawnEvent(obj_id self, dictionary params) throws InterruptedException
    {
        if (params != null)
        {
            obj_id spawner = params.getObjId("spawner");
            String questName = params.getString("questName");
            LOG("newquests", "encounterMultiSpawnEvent(spawner=" + params.getObjId("spawner") + ", questName=" + questName + ", currentRow=" + params.getInt("currentRow") + ", currentSpawn=" + params.getInt("currentSpawn") + ")");
            if (spawner == self && questName != null && questName.length() > 0 && quests.isMyQuest(quests.getQuestId(questName), "quest.task.encounter_multi") && quests.isActive(questName, self))
            {
                String datatable = params.getString("datatable");
                if (datatable != null && datatable.length() > 0)
                {
                    int currentRow = -1;
                    currentRow = params.getInt("currentRow");
                    if (currentRow >= 0 && currentRow <= dataTableGetNumRows(datatable))
                    {
                        int currentSpawn = -1;
                        currentSpawn = params.getInt("currentSpawn");
                        if (currentSpawn >= 0)
                        {
                            int totalSpawns = dataTableGetInt(datatable, currentRow, "COUNT");
                            if (currentSpawn < totalSpawns)
                            {
                                String spawnEntry = dataTableGetString(datatable, currentRow, "SPAWN");
                                if (spawnEntry != null && spawnEntry.length() > 0)
                                {
                                    location l = params.getLocation("location");
                                    l.x = l.x + rand(-2, 2);
                                    l.z = l.z + rand(-2, 2);
                                    obj_id creature = create.createCreature(spawnEntry, l, true);
                                    if (isIdValid(creature))
                                    {
                                        Vector spawnedCreatures = utils.getResizeableObjIdArrayScriptVar(self, "encounter_multi_creatures");
                                        if (spawnedCreatures == null)
                                        {
                                            spawnedCreatures = new Vector();
                                        }
                                        spawnedCreatures.add(creature);
                                        utils.setScriptVar(self, "encounter_multi_creatures", spawnedCreatures);
                                        attachScript(creature, "quest.utility.encounter_death_multi_notification");
                                        String scriptName = dataTableGetString(datatable, currentRow, "SCRIPT");
                                        if (scriptName != null && scriptName.length() > 0)
                                        {
                                            attachScript(creature, scriptName);
                                        }
                                        setObjVar(creature, "quest.owner", self);
                                        setObjVar(creature, "quest.name", questName);
                                        
                                        {
                                            startCombat(creature, self);
                                        }
                                        currentSpawn++;
                                        if (currentSpawn >= totalSpawns)
                                        {
                                            ++currentRow;
                                            currentSpawn = 0;
                                        }
                                        if (currentRow < dataTableGetNumRows(datatable))
                                        {
                                            dictionary eventParameters = new dictionary();
                                            eventParameters.put("questName", questName);
                                            eventParameters.put("datatable", datatable);
                                            eventParameters.put("currentRow", currentRow);
                                            eventParameters.put("currentSpawn", currentSpawn);
                                            eventParameters.put("spawner", spawner);
                                            eventParameters.put("location", l);
                                            messageTo(self, "encounterMultiSpawnEvent", eventParameters, 1.0f, false);
                                        }
                                        else 
                                        {
                                            LOG("newquests", "encounter_multi - encounterMultiSpawnEvent - currentRow(" + currentRow + ") >= numRows(" + dataTableGetNumRows(datatable) + ") stopping message pump");
                                        }
                                    }
                                    else 
                                    {
                                        LOG("newquests", "encounter_multu - encounterMultiSpawnEvent - failed to spawn creature " + spawnEntry + " in data table " + datatable + " failing task");
                                        quests.complete(questName, self, false);
                                    }
                                }
                                else 
                                {
                                    LOG("newquests", "failed to retrieve spawn entry for task " + questName + " failing task");
                                    quests.complete(questName, self, false);
                                }
                            }
                            else 
                            {
                                LOG("newquests", "if(currentSpawn(" + currentSpawn + ") < totalSpawns(" + totalSpawns + ")) for row(" + currentRow + ") failed to get row spawn, this shouldn't happen. Look at the script and insure everything is working properly");
                                quests.complete(questName, self, false);
                            }
                        }
                        else 
                        {
                            LOG("newquests", "can't determine current spawn entry. failing task");
                            quests.complete(questName, self, false);
                        }
                    }
                    else 
                    {
                        LOG("newquests", "can't determine current row(" + currentRow + ") . failing task");
                        quests.complete(questName, self, false);
                    }
                }
                else 
                {
                    LOG("newquests", "can't get data table name for quest " + questName + ", failing task");
                    quests.complete(questName, self, false);
                }
            }
            else 
            {
                LOG("newquests", "encounter_multi - encounterMultiSpawnEvent - spawn event isn't for me");
            }
        }
        else 
        {
            LOG("newquests", "encounter_multi - encounterMultiSpawnEvent() - received null parameters");
        }
        return SCRIPT_CONTINUE;
    }
    public void failTasks(obj_id self) throws InterruptedException
    {
        String[] activeTasks = quests.getActiveQuestsWithScript("quest.task.encounter_multi", self);
        int iter = 0;
        for (iter = 0; iter < activeTasks.length; ++iter)
        {
            quests.complete(activeTasks[iter], self, false);
        }
    }
    public int OnDeath(obj_id self, obj_id killer, obj_id corpseId) throws InterruptedException
    {
        failTasks(self);
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        failTasks(self);
        return SCRIPT_CONTINUE;
    }
    public int destroyNotification(obj_id self, dictionary params) throws InterruptedException
    {
        if (params != null)
        {
            String questName = params.getString("questName");
            quests.complete(questName, self, false);
            LOG("newquests", "encounter_multi - destroyNotification - received a destroy notification, meaning the player did not receive a death notification, the task will now fail");
        }
        return SCRIPT_CONTINUE;
    }
    public int deathNotification(obj_id self, dictionary params) throws InterruptedException
    {
        if (params != null)
        {
            obj_id source = params.getObjId("source");
            String questName = params.getString("questName");
            String objvarName = "quest." + questName + ".killCount";
            int killCount = 1;
            if (hasObjVar(self, objvarName))
            {
                killCount = getIntObjVar(self, objvarName);
                killCount++;
            }
            objvarName = "quest." + questName + ".spawnCount";
            int spawnCount = 0;
            if (hasObjVar(self, objvarName))
            {
                spawnCount = getIntObjVar(self, objvarName);
            }
            LOG("newquests", "encounter_multi - deathNotification() - killCount=" + killCount + " spawnCount=" + spawnCount);
            if (killCount >= spawnCount)
            {
                quests.complete(questName, self, true);
            }
            else 
            {
                objvarName = "quest." + questName + ".killCount";
                setObjVar(self, objvarName, killCount);
            }
        }
        else 
        {
            LOG("newquests", "encounter_multi - deathNotification() - received a message with null parameters");
        }
        return SCRIPT_CONTINUE;
    }
}
