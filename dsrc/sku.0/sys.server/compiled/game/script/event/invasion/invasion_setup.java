package script.event.invasion;

import script.dictionary;
import script.library.create;
import script.library.sui;
import script.library.utils;
import script.location;
import script.obj_id;

public class invasion_setup extends script.base_script
{
    public invasion_setup()
    {
    }
    public static final String[] PROMPT_TEXT = 
    {
        "How many different types of creature will be spawned?",
        "Enter the spawn name of creature (e.g nightsister_elder, blood_razor_guard).",
        "How many waves of attacks will there be?",
        "How many minutes between individual attack waves?",
        "How many seconds in between individual spawns?",
        "Specify how many of each creature type will spawn per wave. ",
        "Type in the area broadcast that will announce the start of the event.",
        "Type in the area broadcast that will announce the end of the event.",
        "Would you like to specify a special area broadcast that will go off on a particular wave? Type yes or no.",
        "On which wave would you like the area broadcast to occur?",
        "Type in the special area broadcast that will occur on the wave you specified.",
        "How will the waves spawn: In a radius around the target or from spawn points. Type radius or spawn points.",
        "Enter the minimum spawn radius distance from the target.",
        "Enter the maximum spawn radius distance from the target.",
        "How many spawn points will their be?",
        "Move to want where you want creatures to spawn and type here in the box.",
        "Specify the destination offset. Recommended distance is at least 20m. This is to prevent the invaders clumping together or warping inside a building if that is the target.",
        "Does this event use a special object (e.g. a crashed shuttle, a camp)? Type yes or no.",
        "Type in the template name for the object (e.g. object/tangible/thingy/certain_thingy.iff).",
        "Enter a time in seconds for when the key object will expire. 7200 is 2 hours for example.",
        "Would you like to automatically award an Event Badge? Enter yes or no.",
        "Setup complete. Scroll up and review all your entries to ensure they are correct. If you need to make changes type BACK. Otherwise, move to the target location and type FINISHED to start the event."
    };
    public static final String[] TITLE = 
    {
        "Number of Creature Types",
        "Spawn Names",
        "Number of Attack Waves",
        "Minutes between Attack Wave",
        "Seconds between Individual Spawns",
        "Creature Types to Spawn Per Wave",
        "Area Broadcast: Start",
        "Area Broadcast: End",
        "Extra Area Broadcast",
        "Area Broadcast on Wave",
        "Area Broadcast: Special",
        "Spawning Method",
        "Min Spawn Radius",
        "Max Spawn Radius",
        "Number of Spawn Points",
        "Spawn Points",
        "Destination Offset",
        "Use a Key Object?",
        "Key Object Template Name",
        "Key Object Expiration",
        "Award Event Badge",
        "Confirm Data"
    };
    public int OnAttach(obj_id self) throws InterruptedException
    {
        int creatureTypePerWaveSubStep = 1;
        String promptExtra = "";
        setObjVar(self, "event.invasion.misc.promptExtra", promptExtra);
        setObjVar(self, "event.invasion.misc.creatureTypePerWaveSubStep", creatureTypePerWaveSubStep);
        int setupCompleted = 0;
        int setupStep = 0;
        int setupCurrentSubStep = 0;
        int setupMaxSubStep = 0;
        setObjVar(self, "event.invasion.setup.setupCompleted", setupCompleted);
        setObjVar(self, "event.invasion.setup.setupStep", setupStep);
        setObjVar(self, "event.invasion.setup.setupCurrentSubStep", setupCurrentSubStep);
        showInvasionUI(self, self);
        return SCRIPT_CONTINUE;
    }
    public int OnHearSpeech(obj_id self, obj_id objSpeaker, String strText) throws InterruptedException
    {
        if (!isGod(self))
        {
            detachScript(self, "event.invasion.invasion_setup");
            removeObjVar(self, "event.invasion");
            sendSystemMessage(self, "You must be in God Mode to use this script.", null);
            return SCRIPT_CONTINUE;
        }
        if (objSpeaker != self)
        {
            return SCRIPT_CONTINUE;
        }
        if (strText.equals("detach") || strText.equals("abort"))
        {
            sendSystemMessage(self, "Invasion setup aborted.", null);
            removeObjVar(self, "event.invasion");
            detachScript(self, "event.invasion.invasion_setup");
        }
        if (strText.equals("showInvasionUI"))
        {
            messageTo(self, "continueCollectingData", null, 2, false);
            sendSystemMessage(self, "You shouldn't need to use this unless you lost connection in the middle of setup. Unless you are sure, it may be safer to abort and restart.", null);
        }
        return SCRIPT_CONTINUE;
    }
    public int showInvasionUI(obj_id self, obj_id player) throws InterruptedException
    {
        String promptExtra = getStringObjVar(self, "event.invasion.misc.promptExtra");
        int current = getIntObjVar(self, "event.invasion.setup.setupStep");
        int pid = sui.inputbox(self, self, PROMPT_TEXT[current] + promptExtra, TITLE[current], "handleUIdata", 255, false, "");
        return pid;
    }
    public int handleUIdata(obj_id self, dictionary params) throws InterruptedException
    {
        int bp = sui.getIntButtonPressed(params);
        String text = sui.getInputBoxText(params);
        if (bp == sui.BP_CANCEL || text == null || text.equals(""))
        {
            sendSystemMessage(self, "There is no escape, you must type something! Try again! If you want to quit say abort out loud.", null);
            messageTo(self, "continueCollectingData", null, 2, false);
            return SCRIPT_CONTINUE;
        }
        setObjVar(self, "event.invasion.lastData", text);
        int current = getIntObjVar(self, "event.invasion.setup.setupStep");
        messageTo(self, "storeLastDataObjVar", null, 0, false);
        return SCRIPT_CONTINUE;
    }
    public int storeLastDataObjVar(obj_id self, dictionary params) throws InterruptedException
    {
        int setupStep = getIntObjVar(self, "event.invasion.setup.setupStep");
        int setupCurrentSubStep = getIntObjVar(self, "event.invasion.setup.setupCurrentSubStep");
        String lastDataStr = getStringObjVar(self, "event.invasion.lastData");
        if (lastDataStr.equals("BACK"))
        {
            sendSystemMessage(self, "Moving back one step...", null);
            if (setupStep == 0)
            {
                sendSystemMessage(self, "In order to go back, you must first go forward", null);
            }
            if (setupStep < 5 && setupStep != 0)
            {
                setupStep--;
                setObjVar(self, "event.invasion.setup.setupStep", setupStep);
                setObjVar(self, "event.invasion.setup.setupCurrentSubStep", 0);
                messageTo(self, "continueCollectingData", null, 2, false);
                return SCRIPT_CONTINUE;
            }
            if (setupStep == 5 || setupStep == 6)
            {
                setupStep--;
                setObjVar(self, "event.invasion.setup.setupStep", setupStep);
                setObjVar(self, "event.invasion.setup.setupCurrentSubStep", 1);
                setObjVar(self, "event.invasion.misc.creatureTypePerWaveSubStep", 1);
                setObjVar(self, "event.invasion.misc.promptExtra", "");
                removeObjVar(self, "event.invasion.broadcasts");
                messageTo(self, "continueCollectingData", null, 2, false);
                return SCRIPT_CONTINUE;
            }
            if (setupStep >= 7 && setupStep <= 11)
            {
                setObjVar(self, "event.invasion.setup.setupStep", 6);
                setObjVar(self, "event.invasion.setup.setupCurrentSubStep", 1);
                setObjVar(self, "event.invasion.misc.creatureTypePerWaveSubStep", 1);
                setObjVar(self, "event.invasion.misc.promptExtra", "");
                removeObjVar(self, "event.invasion.broadcasts");
                messageTo(self, "continueCollectingData", null, 2, false);
                return SCRIPT_CONTINUE;
            }
            if (setupStep > 11 && setupStep <= 16)
            {
                setObjVar(self, "event.invasion.setup.setupStep", 11);
                setObjVar(self, "event.invasion.setup.setupCurrentSubStep", 1);
                setObjVar(self, "event.invasion.misc.promptExtra", "");
                removeObjVar(self, "event.invasion.spawning");
                messageTo(self, "continueCollectingData", null, 2, false);
                return SCRIPT_CONTINUE;
            }
            if (setupStep > 16)
            {
                setObjVar(self, "event.invasion.setup.setupStep", 16);
                setObjVar(self, "event.invasion.setup.setupCurrentSubStep", 1);
                setObjVar(self, "event.invasion.misc.promptExtra", "");
                removeObjVar(self, "event.invasion.keyObject");
                messageTo(self, "continueCollectingData", null, 2, false);
                return SCRIPT_CONTINUE;
            }
        }
        if (setupStep == 0)
        {
            int lastDataInt = utils.stringToInt(lastDataStr);
            if (lastDataInt <= 0 || lastDataInt > 5)
            {
                sendSystemMessage(self, "You must specify between 1 and 5 creature types.", null);
                messageTo(self, "continueCollectingData", null, 2, false);
                return SCRIPT_CONTINUE;
            }
            setObjVar(self, "event.invasion.creature.numCreatureTypes", lastDataInt);
            setObjVar(self, "event.invasion.setup.setupStep", 1);
            sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
        }
        if (setupStep == 1)
        {
            int setupMaxSubStep = getIntObjVar(self, "event.invasion.creature.numCreatureTypes");
            setupCurrentSubStep = getIntObjVar(self, "event.invasion.setup.setupCurrentSubStep");
            setupCurrentSubStep++;
            if (setupCurrentSubStep < setupMaxSubStep)
            {
                setObjVar(self, "event.invasion.creature.creatureType" + setupCurrentSubStep, lastDataStr);
                setObjVar(self, "event.invasion.setup.setupCurrentSubStep", setupCurrentSubStep);
                int testInt = getIntObjVar(self, "event.invasion.setup.setupCurrentSubStep");
            }
            if (setupCurrentSubStep == setupMaxSubStep)
            {
                setObjVar(self, "event.invasion.creature.creatureType" + setupCurrentSubStep, lastDataStr);
                setObjVar(self, "event.invasion.setup.setupStep", 2);
                setObjVar(self, "event.invasion.setup.setupCurrentSubStep", 1);
                int testInt = getIntObjVar(self, "event.invasion.setup.setupCurrentSubStep");
            }
            if (setupCurrentSubStep > setupMaxSubStep)
            {
                sendSystemMessage(self, "Possible corrupt data. Aborting the whole thing. Please start over.", null);
                removeObjVar(self, "event.invasion");
                detachScript(self, "event.invasion.invasion_setup");
            }
            sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
        }
        if (setupStep == 2)
        {
            int lastDataInt = utils.stringToInt(lastDataStr);
            if (lastDataInt <= 0 || lastDataInt > 5)
            {
                sendSystemMessage(self, "You must specify between 1 and 5 waves.", null);
                messageTo(self, "continueCollectingData", null, 2, false);
                return SCRIPT_CONTINUE;
            }
            setObjVar(self, "event.invasion.waves.numWaves", lastDataInt);
            if (lastDataInt == 1)
            {
                setObjVar(self, "event.invasion.setup.setupStep", 4);
            }
            else 
            {
                setObjVar(self, "event.invasion.setup.setupStep", 3);
            }
            sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
        }
        if (setupStep == 3)
        {
            int lastDataInt = utils.stringToInt(lastDataStr);
            if (lastDataInt < 5 || lastDataInt > 240)
            {
                sendSystemMessage(self, "The time between waves must be at least 5 minutes and no more then 240 minutes.", null);
                messageTo(self, "continueCollectingData", null, 2, false);
                return SCRIPT_CONTINUE;
            }
            setObjVar(self, "event.invasion.waves.timeBetweenWaves", lastDataInt);
            setObjVar(self, "event.invasion.setup.setupStep", 4);
            sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
        }
        if (setupStep == 4)
        {
            int lastDataInt = utils.stringToInt(lastDataStr);
            if (lastDataInt <= 0 || lastDataInt > 120)
            {
                sendSystemMessage(self, "The time between spawns needs to be between 1 and 120 seconds.", null);
                messageTo(self, "continueCollectingData", null, 2, false);
                return SCRIPT_CONTINUE;
            }
            setObjVar(self, "event.invasion.waves.timeBetweenSpawns", lastDataInt);
            setObjVar(self, "event.invasion.setup.setupStep", 5);
            String firstCreatureType = getStringObjVar(self, "event.invasion.creature.creatureType1");
            String promptExtra = "How many (" + firstCreatureType + ")s will be spawned in wave 1?";
            setObjVar(self, "event.invasion.misc.promptExtra", promptExtra);
            setObjVar(self, "event.invasion.setup.setupCurrentSubStep", 1);
            sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
        }
        if (setupStep == 5)
        {
            int lastDataInt = utils.stringToInt(lastDataStr);
            if (lastDataInt < 0 || lastDataInt > 25)
            {
                sendSystemMessage(self, "Specify between 0 and 25 of this creature type to spawn for this wave.", null);
                messageTo(self, "continueCollectingData", null, 2, false);
                return SCRIPT_CONTINUE;
            }
            int setupMaxSubStep = getIntObjVar(self, "event.invasion.waves.numWaves");
            setupCurrentSubStep = getIntObjVar(self, "event.invasion.setup.setupCurrentSubStep");
            int maxCreatureTypePerWave = getIntObjVar(self, "event.invasion.creature.numCreatureTypes");
            setObjVar(self, "event.invasion.misc.maxCreatureTypePerWave", maxCreatureTypePerWave);
            int creatureTypePerWaveSubStep = getIntObjVar(self, "event.invasion.misc.creatureTypePerWaveSubStep");
            if (setupCurrentSubStep < setupMaxSubStep)
            {
                if (creatureTypePerWaveSubStep < maxCreatureTypePerWave)
                {
                    setObjVar(self, "event.invasion.waves.wave" + setupCurrentSubStep + ".creature" + creatureTypePerWaveSubStep, lastDataInt);
                    setObjVar(self, "event.invasion.misc.creatureTypePerWaveSubStep", creatureTypePerWaveSubStep + 1);
                    setNumCreatureTypePerWaveString(self);
                }
                if (creatureTypePerWaveSubStep == maxCreatureTypePerWave)
                {
                    setObjVar(self, "event.invasion.waves.wave" + setupCurrentSubStep + ".creature" + creatureTypePerWaveSubStep, lastDataInt);
                    setObjVar(self, "event.invasion.misc.creatureTypePerWaveSubStep", 1);
                    setObjVar(self, "event.invasion.setup.setupCurrentSubStep", setupCurrentSubStep + 1);
                    setNumCreatureTypePerWaveString(self);
                }
                if (creatureTypePerWaveSubStep > maxCreatureTypePerWave)
                {
                    sendSystemMessage(self, "Possible corrupt data. Aborting the whole thing. Please start over.", null);
                    removeObjVar(self, "event.invasion");
                    detachScript(self, "event.invasion.invasion_setup");
                }
            }
            if (setupCurrentSubStep == setupMaxSubStep)
            {
                if (creatureTypePerWaveSubStep < maxCreatureTypePerWave)
                {
                    setObjVar(self, "event.invasion.waves.wave" + setupCurrentSubStep + ".creature" + creatureTypePerWaveSubStep, lastDataInt);
                    setObjVar(self, "event.invasion.misc.creatureTypePerWaveSubStep", creatureTypePerWaveSubStep + 1);
                    setNumCreatureTypePerWaveString(self);
                }
                if (creatureTypePerWaveSubStep == maxCreatureTypePerWave)
                {
                    setObjVar(self, "event.invasion.waves.wave" + setupCurrentSubStep + ".creature" + creatureTypePerWaveSubStep, lastDataInt);
                    setObjVar(self, "event.invasion.misc.creatureTypePerWaveSubStep", 1);
                    setObjVar(self, "event.invasion.setup.setupStep", 6);
                    setObjVar(self, "event.invasion.setup.setupCurrentSubStep", 1);
                    String promptExtra = "";
                    setObjVar(self, "event.invasion.misc.promptExtra", promptExtra);
                }
                if (creatureTypePerWaveSubStep > maxCreatureTypePerWave)
                {
                    sendSystemMessage(self, "Possible corrupt data. Aborting the whole thing. Please start over.", null);
                    removeObjVar(self, "event.invasion");
                    detachScript(self, "event.invasion.invasion_setup");
                }
            }
            if (setupCurrentSubStep > setupMaxSubStep)
            {
                sendSystemMessage(self, "Possible corrupt data. Aborting the whole thing. Please start over.", null);
                removeObjVar(self, "event.invasion");
                detachScript(self, "event.invasion.invasion_setup");
            }
            sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
        }
        if (setupStep == 6)
        {
            setObjVar(self, "event.invasion.broadcasts.startBroadcast", lastDataStr);
            int numWaves = getIntObjVar(self, "event.invasion.waves.numWaves");
            if (numWaves == 1)
            {
                setObjVar(self, "event.invasion.setup.setupStep", 11);
            }
            else 
            {
                setObjVar(self, "event.invasion.setup.setupStep", 8);
            }
            sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
        }
        if (setupStep == 7)
        {
            setObjVar(self, "event.invasion.broadcasts.endBroadcast", lastDataStr);
            int numWaves = getIntObjVar(self, "event.invasion.waves.numWaves");
            if (numWaves == 1)
            {
                setObjVar(self, "event.invasion.setup.setupStep", 11);
            }
            else 
            {
                setObjVar(self, "event.invasion.setup.setupStep", 8);
            }
            sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
        }
        if (setupStep == 8)
        {
            if (!lastDataStr.equals("yes") && !lastDataStr.equals("no"))
            {
                sendSystemMessage(self, "You MUST type yes or no in response to this question. Try again.", null);
                messageTo(self, "continueCollectingData", null, 2, false);
                return SCRIPT_CONTINUE;
            }
            if (lastDataStr.equals("yes"))
            {
                setObjVar(self, "event.invasion.setup.setupStep", 9);
            }
            if (lastDataStr.equals("no"))
            {
                setObjVar(self, "event.invasion.setup.setupStep", 11);
                removeObjVar(self, "event.invasion.broadcasts.specialBroadcastWave");
                removeObjVar(self, "event.invasion.broadcasts.specialBroadcast");
            }
            sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
        }
        if (setupStep == 9)
        {
            int lastDataInt = utils.stringToInt(lastDataStr);
            int numWaves = getIntObjVar(self, "event.invasion.waves.numWaves");
            if (lastDataInt <= 1 || lastDataInt > numWaves)
            {
                sendSystemMessage(self, "You must specify an appropriate wave for when this area broadcast fires. This must be a number between 2 and " + numWaves, null);
                messageTo(self, "continueCollectingData", null, 2, false);
                return SCRIPT_CONTINUE;
            }
            setObjVar(self, "event.invasion.broadcasts.specialBroadcastWave", lastDataInt);
            setObjVar(self, "event.invasion.setup.setupStep", 10);
            sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
        }
        if (setupStep == 10)
        {
            setObjVar(self, "event.invasion.broadcasts.specialBroadcast", lastDataStr);
            setObjVar(self, "event.invasion.setup.setupStep", 11);
            sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
        }
        if (setupStep == 11)
        {
            if (!lastDataStr.equals("radius") && !lastDataStr.equals("spawn points"))
            {
                sendSystemMessage(self, "You MUST type either radius or spawn points in response to this question. Try again.", null);
                messageTo(self, "continueCollectingData", null, 2, false);
                return SCRIPT_CONTINUE;
            }
            if (lastDataStr.equals("radius"))
            {
                setObjVar(self, "event.invasion.spawning.method", 0);
                setObjVar(self, "event.invasion.setup.setupStep", 12);
            }
            if (lastDataStr.equals("spawn points"))
            {
                setObjVar(self, "event.invasion.spawning.method", 1);
                setObjVar(self, "event.invasion.setup.setupStep", 14);
            }
            sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
        }
        if (setupStep == 12)
        {
            float lastDataFloat = utils.stringToFloat(lastDataStr);
            if (lastDataFloat < 80 || lastDataFloat > 400)
            {
                sendSystemMessage(self, "You must specify a radius between 80 and 400", null);
                messageTo(self, "continueCollectingData", null, 2, false);
                return SCRIPT_CONTINUE;
            }
            setObjVar(self, "event.invasion.spawning.minRadius", lastDataFloat);
            setObjVar(self, "event.invasion.setup.setupStep", 13);
            sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
        }
        if (setupStep == 13)
        {
            float lastDataFloat = utils.stringToFloat(lastDataStr);
            float minRadius = getFloatObjVar(self, "event.invasion.spawning.minRadius");
            if (lastDataFloat <= minRadius || lastDataFloat > 500)
            {
                sendSystemMessage(self, "You must specify a radius between " + minRadius + " and 500.", null);
                messageTo(self, "continueCollectingData", null, 2, false);
                return SCRIPT_CONTINUE;
            }
            setObjVar(self, "event.invasion.spawning.maxRadius", lastDataFloat);
            setObjVar(self, "event.invasion.setup.setupStep", 16);
            sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
        }
        if (setupStep == 14)
        {
            int lastDataInt = utils.stringToInt(lastDataStr);
            if (lastDataInt <= 0 || lastDataInt > 8)
            {
                sendSystemMessage(self, "You must specify a number of spawn points between 1 and 8.", null);
                messageTo(self, "continueCollectingData", null, 2, false);
                return SCRIPT_CONTINUE;
            }
            setObjVar(self, "event.invasion.spawning.numSpawnPoints", lastDataInt);
            setObjVar(self, "event.invasion.setup.setupStep", 15);
            sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
        }
        if (setupStep == 15)
        {
            int setupMaxSubStep = getIntObjVar(self, "event.invasion.spawning.numSpawnPoints");
            setupCurrentSubStep = getIntObjVar(self, "event.invasion.setup.setupCurrentSubStep");
            if (lastDataStr.equals("here"))
            {
                location thisLocation = getLocation(self);
                obj_id myCell = thisLocation.cell;
                if (isIdValid(myCell))
                {
                    sendSystemMessage(self, "You may NOT set a spawn point within a cell. Please go outside", null);
                    messageTo(self, "continueCollectingData", null, 2, false);
                    return SCRIPT_CONTINUE;
                }
                if (setupCurrentSubStep < setupMaxSubStep)
                {
                    int spawnNumber = setupCurrentSubStep + 1;
                    setObjVar(self, "event.invasion.spawning.location" + setupCurrentSubStep, thisLocation);
                    setObjVar(self, "event.invasion.setup.setupCurrentSubStep", setupCurrentSubStep + 1);
                    String promptExtra = " This is for spawn point number " + spawnNumber + ".";
                    setObjVar(self, "event.invasion.misc.promptExtra", promptExtra);
                }
                if (setupCurrentSubStep == setupMaxSubStep)
                {
                    setObjVar(self, "event.invasion.spawning.location" + setupCurrentSubStep, thisLocation);
                    setObjVar(self, "event.invasion.setup.setupCurrentSubStep", 1);
                    setObjVar(self, "event.invasion.setup.setupStep", 16);
                    String promptExtra = "";
                    setObjVar(self, "event.invasion.misc.promptExtra", promptExtra);
                    setObjVar(self, "event.invasion.setup.setupCompleted", 0);
                }
                if (setupCurrentSubStep > setupMaxSubStep)
                {
                    sendSystemMessage(self, "Possible corrupt data. Aborting the whole thing. Please start over.", null);
                    removeObjVar(self, "event.invasion");
                    detachScript(self, "event.invasion.invasion_setup");
                }
            }
            if (!lastDataStr.equals("here"))
            {
                sendSystemMessage(self, "You MUST type the word here to set the spawn location. Try again.", null);
                messageTo(self, "continueCollectingData", null, 2, false);
                return SCRIPT_CONTINUE;
            }
            sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
        }
        if (setupStep == 16)
        {
            float lastDataFloat = utils.stringToFloat(lastDataStr);
            if (lastDataFloat < 10 || lastDataFloat > 250)
            {
                sendSystemMessage(self, "The destination offset must be between 10 and 250.", null);
                messageTo(self, "continueCollectingData", null, 2, false);
                return SCRIPT_CONTINUE;
            }
            setObjVar(self, "event.invasion.spawning.destinationOffset", lastDataFloat);
            setObjVar(self, "event.invasion.setup.setupStep", 17);
            sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
        }
        if (setupStep == 17)
        {
            if (!lastDataStr.equals("yes") && !lastDataStr.equals("no"))
            {
                sendSystemMessage(self, "You MUST type yes or no in response to this question. Try again.", null);
                messageTo(self, "continueCollectingData", null, 2, false);
                return SCRIPT_CONTINUE;
            }
            if (lastDataStr.equals("yes"))
            {
                setObjVar(self, "event.invasion.setup.setupStep", 18);
                setObjVar(self, "event.invasion.keyObject.useKeyObject", 1);
            }
            if (lastDataStr.equals("no"))
            {
                setObjVar(self, "event.invasion.setup.setupStep", 20);
                removeObjVar(self, "event.invasion.keyObject");
            }
            sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
        }
        if (setupStep == 18)
        {
            int useKeyObject = getIntObjVar(self, "event.invasion.keyObject.useKeyObject");
            if (useKeyObject == 1)
            {
                setObjVar(self, "event.invasion.keyObject.name", lastDataStr);
                setObjVar(self, "event.invasion.setup.setupStep", 19);
                sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
            }
            else 
            {
                setObjVar(self, "event.invasion.keyObject.useKeyObject", 0);
                setObjVar(self, "event.invasion.setup.setupStep", 20);
                removeObjVar(self, "event.invasion.keyObject");
            }
        }
        if (setupStep == 19)
        {
            float expirationTime = utils.stringToFloat(lastDataStr);
            int useKeyObject = getIntObjVar(self, "event.invasion.keyObject.useKeyObject");
            if (useKeyObject == 1)
            {
                if (expirationTime < 60 || expirationTime > 80000)
                {
                    sendSystemMessage(self, "You must specify an expiration time between 60 and 80000.", null);
                    messageTo(self, "continueCollectingData", null, 2, false);
                    return SCRIPT_CONTINUE;
                }
                setObjVar(self, "event.invasion.keyObject.expirationTime", expirationTime);
                setObjVar(self, "event.invasion.setup.setupStep", 20);
                sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
            }
            else 
            {
                removeObjVar(self, "event.invasion.keyObject");
                setObjVar(self, "event.invasion.setup.setupStep", 20);
                sendSystemMessage(self, "Something got borked.", null);
            }
        }
        if (setupStep == 20)
        {
            if (!lastDataStr.equals("yes") && !lastDataStr.equals("no"))
            {
                sendSystemMessage(self, "You MUST type yes or no in response to this question. Try again.", null);
                messageTo(self, "continueCollectingData", null, 2, false);
                return SCRIPT_CONTINUE;
            }
            if (lastDataStr.equals("yes"))
            {
                setObjVar(self, "event.invasion.setup.setupStep", 21);
                setObjVar(self, "event.invasion.misc.awardBadge", 1);
            }
            if (lastDataStr.equals("no"))
            {
                setObjVar(self, "event.invasion.setup.setupStep", 21);
                setObjVar(self, "event.invasion.misc.awardBadge", 0);
            }
            sendSystemMessage(self, TITLE[setupStep] + ": " + lastDataStr, null);
        }
        if (setupStep == 21)
        {
            if (lastDataStr.equals("FINISHED"))
            {
                location myLoc = getLocation(self);
                obj_id myCell = myLoc.cell;
                if (!isIdValid(myCell))
                {
                    setObjVar(self, "event.invasion.setup.setupCompleted", 1);
                    messageTo(self, "setupInvasionTarget", null, 0, false);
                }
                else 
                {
                    sendSystemMessage(self, "You may NOT start an invasion in a cell. Please go outside.", null);
                }
            }
            else 
            {
                sendSystemMessage(self, "You must type either FINISHED or BACK.", null);
            }
        }
        int setupCompleted = getIntObjVar(self, "event.invasion.setup.setupCompleted");
        removeObjVar(self, "event.invasion.lastData");
        if (setupCompleted == 0)
        {
            messageTo(self, "continueCollectingData", null, 2, false);
        }
        return SCRIPT_CONTINUE;
    }
    public int continueCollectingData(obj_id self, dictionary params) throws InterruptedException
    {
        showInvasionUI(self, self);
        return SCRIPT_CONTINUE;
    }
    public void setNumCreatureTypePerWaveString(obj_id self) throws InterruptedException
    {
        int setupCurrentSubStep = getIntObjVar(self, "event.invasion.setup.setupCurrentSubStep");
        int creatureTypePerWaveSubStep = getIntObjVar(self, "event.invasion.misc.creatureTypePerWaveSubStep");
        String currentCritter = getStringObjVar(self, "event.invasion.creature.creatureType" + creatureTypePerWaveSubStep);
        String promptExtra = "  How many (" + currentCritter + ")s will be spawned in wave " + setupCurrentSubStep;
        setObjVar(self, "event.invasion.misc.promptExtra", promptExtra);
        return;
    }
    public int setupInvasionTarget(obj_id self, dictionary params) throws InterruptedException
    {
        location thisSpot = getLocation(self);
        obj_id target = create.object("object/tangible/poi/base/poi_base.iff", thisSpot);
        sendSystemMessage(self, "Created invasion spawner with OID " + target, null);
        setObjVar(self, "event.invasion.target", target);
        setObjVar(target, "event.invasion.eventCoordinator", self);
        int specialBroadcastWave = getIntObjVar(self, "event.invasion.broadcasts.specialBroadcastWave");
        if (specialBroadcastWave > 0)
        {
            String specialBroadcast = getStringObjVar(self, "event.invasion.broadcasts.specialBroadcast");
            setObjVar(target, "event.invasion.broadcasts.specialBroadcastWave", specialBroadcastWave);
            setObjVar(target, "event.invasion.broadcasts.specialBroadcast", specialBroadcast);
        }
        String endBroadcast = getStringObjVar(self, "event.invasion.broadcasts.endBroadcast");
        String startBroadcast = getStringObjVar(self, "event.invasion.broadcasts.startBroadcast");
        setObjVar(target, "event.invasion.broadcasts.endBroadcast", endBroadcast);
        setObjVar(target, "event.invasion.broadcasts.startBroadcast", startBroadcast);
        int numCreatureTypes = getIntObjVar(self, "event.invasion.creature.numCreatureTypes");
        setObjVar(target, "event.invasion.creature.numCreatureTypes", numCreatureTypes);
        for (int i = 0; i < numCreatureTypes; i++)
        {
            String creatureType = getStringObjVar(self, "event.invasion.creature.creatureType" + (i + 1));
            setObjVar(target, "event.invasion.creature.creatureType" + (i + 1), creatureType);
        }
        int method = getIntObjVar(self, "event.invasion.spawning.method");
        setObjVar(target, "event.invasion.spawning.method", method);
        float destinationOffset = getFloatObjVar(self, "event.invasion.spawning.destinationOffset");
        setObjVar(target, "event.invasion.spawning.destinationOffset", destinationOffset);
        if (method == 0)
        {
            float maxRadius = getFloatObjVar(self, "event.invasion.spawning.maxRadius");
            setObjVar(target, "event.invasion.spawning.maxRadius", maxRadius);
            float minRadius = getFloatObjVar(self, "event.invasion.spawning.minRadius");
            setObjVar(target, "event.invasion.spawning.minRadius", minRadius);
        }
        if (method == 1)
        {
            int numSpawnPoints = getIntObjVar(self, "event.invasion.spawning.numSpawnPoints");
            setObjVar(target, "event.invasion.spawning.numSpawnPoints", numSpawnPoints);
            for (int i = 0; i < numSpawnPoints; i++)
            {
                location here = getLocationObjVar(self, "event.invasion.spawning.location" + (i + 1));
                setObjVar(target, "event.invasion.spawning.location" + (i + 1), here);
            }
        }
        int numWaves = getIntObjVar(self, "event.invasion.waves.numWaves");
        setObjVar(target, "event.invasion.waves.numWaves", numWaves);
        int timeBetweenWaves = getIntObjVar(self, "event.invasion.waves.timeBetweenWaves");
        int timeBetweenSpawns = getIntObjVar(self, "event.invasion.waves.timeBetweenSpawns");
        setObjVar(target, "event.invasion.waves.timeBetweenWaves", timeBetweenWaves);
        setObjVar(target, "event.invasion.waves.timeBetweenSpawns", timeBetweenSpawns);
        for (int i = 0; i < numWaves; i++)
        {
            for (int j = 0; j < numCreatureTypes; j++)
            {
                int numCreaturePerWave = getIntObjVar(self, "event.invasion.waves.wave" + (i + 1) + ".creature" + (j + 1));
                setObjVar(target, "event.invasion.waves.wave" + (i + 1) + ".creature" + (j + 1), numCreaturePerWave);
            }
        }
        int useKeyObject = getIntObjVar(self, "event.invasion.keyObject.useKeyObject");
        int awardBadge = getIntObjVar(self, "event.invasion.misc.awardBadge");
        String name = getStringObjVar(self, "event.invasion.keyObject.name");
        float expirationTime = getFloatObjVar(self, "event.invasion.keyObject.expirationTime");
        setObjVar(target, "event.invasion.keyObject.useKeyObject", useKeyObject);
        setObjVar(target, "event.invasion.keyObject.expirationTime", expirationTime);
        setObjVar(target, "event.invasion.keyObject.name", name);
        setObjVar(target, "event.invasion.misc.awardBadge", awardBadge);
        attachScript(target, "event.invasion.invasion_target");
        removeObjVar(self, "event.invasion");
        detachScript(self, "event.invasion.invasion_setup");
        return SCRIPT_CONTINUE;
    }
}
