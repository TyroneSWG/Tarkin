package script.theme_park.poi.tatooine.city;

import script.dictionary;
import script.library.create;
import script.location;
import script.obj_id;

public class npc_mission_01_create extends script.base_script
{
    public npc_mission_01_create()
    {
    }
    public static final String npcTable = "datatables/poi/city/convo_npc.iff";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        spawnGuyOne(self);
        spawnGuyTwo(self);
        messageTo(self, "handleChatting", null, 10, false);
        messageTo(self, "checkForScripts", null, 5, false);
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if (hasObjVar(self, "alreadySpawned"))
        {
            removeObjVar(self, "alreadySpawned");
        }
        spawnGuyOne(self);
        spawnGuyTwo(self);
        messageTo(self, "handleChatting", null, 10, false);
        messageTo(self, "checkForScripts", null, 5, false);
        return SCRIPT_CONTINUE;
    }
    public int handleChatting(obj_id self, dictionary params) throws InterruptedException
    {
        messageTo(self, "handleChatting", null, 600, false);
        obj_id guy1 = getObjIdObjVar(self, "guy1");
        obj_id guy2 = getObjIdObjVar(self, "guy2");
        if (!exists(guy1) || !exists(guy2))
        {
            return SCRIPT_CONTINUE;
        }
        faceTo(guy1, guy2);
        faceTo(guy2, guy1);
        setAnimationMood(guy1, "conversation");
        setAnimationMood(guy2, "conversation");
        return SCRIPT_CONTINUE;
    }
    public int handleGuyOneKilled(obj_id self, dictionary params) throws InterruptedException
    {
        spawnGuyOne(self);
        return SCRIPT_CONTINUE;
    }
    public int handleGuyTwoKilled(obj_id self, dictionary params) throws InterruptedException
    {
        spawnGuyTwo(self);
        return SCRIPT_CONTINUE;
    }
    public void spawnGuyOne(obj_id baseObject) throws InterruptedException
    {
        obj_id guy1 = create.themeParkObject(getRandomGuy(), 1, 0, "handleGuyOneKilled", 0);
        attachScript(guy1, "theme_park.poi.tatooine.city.npc_mission_01_convo");
        setInvulnerable(guy1, true);
        setObjVar(baseObject, "guy1", guy1);
    }
    public void spawnGuyTwo(obj_id baseObject) throws InterruptedException
    {
        obj_id guy2 = create.themeParkObject(getRandomGuy(), 1, 1, "handleGuyTwoKilled", 0);
        attachScript(guy2, "theme_park.poi.tatooine.city.npc_mission_01_friend_convo");
        setInvulnerable(guy2, true);
        setObjVar(baseObject, "guy2", guy2);
    }
    public String getRandomGuy() throws InterruptedException
    {
        location here = getLocation(getSelf());
        String planet = here.area;
        String[] npcList = dataTableGetStringColumnNoDefaults(npcTable, planet);
        int npcNum = rand(0, npcList.length - 1);
        String npc = npcList[npcNum];
        return npc;
    }
    public int checkForScripts(obj_id self, dictionary params) throws InterruptedException
    {
        if (hasScript(self, "theme_park.poi.launch"))
        {
            detachScript(self, "theme_park.poi.launch");
        }
        return SCRIPT_CONTINUE;
    }
}
