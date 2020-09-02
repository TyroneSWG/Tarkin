package script.theme_park.dungeon.death_watch_bunker;

import script.dictionary;
import script.library.ai_lib;
import script.library.create;
import script.library.utils;
import script.location;
import script.obj_id;
import script.string_id;

public class craft_jetpack_droid extends script.base_script
{
    public craft_jetpack_droid()
    {
    }
    public static final String TBL_JETPACK_WAVE = "datatables/dungeon/death_watch/jetpack_crafting_attack_wave.iff";
    public static final String MSGS = "dungeon/death_watch";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        setInvulnerable(self, true);
        createTerminal(self);
        setName(self, "Jetpack Crafting Droid");
        obj_id thisDroid = getSelf();
        location here = getLocation(self);
        obj_id room = here.cell;
        dictionary droid = new dictionary();
        droid.put("droid", thisDroid);
        messageTo(room, "addJetPackDroid", droid, 5, false);
        return SCRIPT_CONTINUE;
    }
    public int OnGiveItem(obj_id self, obj_id item, obj_id giver) throws InterruptedException
    {
        CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU has put an item in the jetpack droid");
        String setting = getConfigSetting("Dungeon", "Death_Watch");
        if (setting == null || setting.equals("false") || setting.equals("0"))
        {
            return SCRIPT_CONTINUE;
        }
        if (!hasSkill(giver, "class_engineering_phase1_master"))
        {
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU was not a crafting artisan master, so erroring out.");
            string_id master_tailor = new string_id(MSGS, "master_artisan_required");
            sendSystemMessage(giver, master_tailor);
            return SCRIPT_CONTINUE;
        }
        if (!hasObjVar(self, "giver"))
        {
            spawnGuards(self);
            setObjVar(self, "giver", giver);
            messageTo(self, "cleanUp", null, 300, false);
        }
        else 
        {
            obj_id user = getObjIdObjVar(self, "giver");
            if (user != giver)
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU has put an item into the jetpack droid but wasn't the right player, someone else started the process.");
                string_id wrong = new string_id(MSGS, "same_user_only");
                sendSystemMessage(giver, wrong);
                return SCRIPT_CONTINUE;
            }
        }
        setObjVar(giver, "dw_craft.droid", self);
        String justGot = getTemplateName(item);
        if (justGot.equals("object/tangible/loot/dungeon/death_watch_bunker/jetpack_base.iff"))
        {
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a jetpack base into the droid");
            if (hasObjVar(self, "have.base"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put a jetpack base into the droid, but the droid already had one.");
                string_id already = new string_id(MSGS, "already_has_component");
                sendSystemMessage(giver, already);
                return SCRIPT_CONTINUE;
            }
            else 
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a jetpack base into the droid, and everything is functioning properly");
                setObjVar(self, "have.base", 1);
                setObjVar(self, "making.jetpack", 1);
                destroyObject(item);
                checkForComplete(self, giver);
            }
        }
        if (justGot.equals("object/tangible/loot/dungeon/death_watch_bunker/jetpack_stabilizer.iff"))
        {
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a jetpack stabilizer into the droid");
            if (hasObjVar(self, "have.stabilizer"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put a jetpack stabilizer into the droid, but the droid already had one.");
                string_id already = new string_id(MSGS, "already_has_component");
                sendSystemMessage(giver, already);
                return SCRIPT_CONTINUE;
            }
            else 
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a jetpack stabilizer into the droid, and everything is functioning properly");
                setObjVar(self, "have.stabilizer", 1);
                destroyObject(item);
                checkForComplete(self, giver);
            }
        }
        if (justGot.equals("object/tangible/loot/dungeon/death_watch_bunker/mining_drill_reward.iff"))
        {
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed alum into the droid");
            if (hasObjVar(self, "have.alum"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put alum into the droid, but the droid already had one.");
                string_id already = new string_id(MSGS, "already_has_component");
                sendSystemMessage(giver, already);
                return SCRIPT_CONTINUE;
            }
            else 
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed alum into the droid, and everything is functioning properly");
                setObjVar(self, "have.alum", 1);
                destroyObject(item);
                checkForComplete(self, giver);
            }
        }
        if (justGot.equals("object/tangible/loot/dungeon/death_watch_bunker/fuel_dispersion_unit.iff"))
        {
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a fuel dispersion unit into the droid");
            if (hasObjVar(self, "have.dispersion"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put a fuel dispersion unit into the droid, but the droid already had one.");
                string_id already = new string_id(MSGS, "already_has_component");
                sendSystemMessage(giver, already);
                return SCRIPT_CONTINUE;
            }
            else 
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a fuel dispersion unit into the droid, and everything is functioning properly");
                setObjVar(self, "have.dispersion", 1);
                destroyObject(item);
                checkForComplete(self, giver);
            }
        }
        if (justGot.equals("object/tangible/loot/dungeon/death_watch_bunker/fuel_injector_tank.iff"))
        {
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a fuel injection tank into the droid");
            if (hasObjVar(self, "have.injector"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put a fuel injection tank into the droid, but the droid already had one.");
                string_id already = new string_id(MSGS, "already_has_component");
                sendSystemMessage(giver, already);
                return SCRIPT_CONTINUE;
            }
            else 
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a fuel injector tank into the droid, and everything is functioning properly");
                setObjVar(self, "have.injector", 1);
                destroyObject(item);
                checkForComplete(self, giver);
            }
        }
        if (justGot.equals("object/tangible/loot/dungeon/death_watch_bunker/ducted_fan.iff"))
        {
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a ducted fan into the droid");
            if (hasObjVar(self, "have.fan"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put a ducted fan into the droid, but the droid already had one.");
                string_id already = new string_id(MSGS, "already_has_component");
                sendSystemMessage(giver, already);
                return SCRIPT_CONTINUE;
            }
            else 
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a ducted fan into the droid, and everything is functioning properly");
                setObjVar(self, "have.fan", 1);
                destroyObject(item);
                checkForComplete(self, giver);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public void checkForComplete(obj_id self, obj_id player) throws InterruptedException
    {
        if (hasObjVar(self, "making"))
        {
            if (hasObjVar(self, "have.alum") && hasObjVar(self, "have.stabilizer") && hasObjVar(self, "have.dispersion") && hasObjVar(self, "have.injector") && hasObjVar(self, "have.fan"))
            {
                string_id startCraft = new string_id(MSGS, "starting_to_craft");
                sendSystemMessage(player, startCraft);
                dictionary webster = new dictionary();
                webster.put("player", player);
                attachScript(player, "theme_park.dungeon.death_watch_bunker.mandalorian_crafting");
                messageTo(player, "firstBox", webster, 30, false);
                return;
            }
            else 
            {
                ingredientMessages(self, player);
            }
        }
        else 
        {
            ingredientMessages(self, player);
        }
        return;
    }
    public int craftItem(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = params.getObjId("player");
        obj_id playerInv = utils.getInventoryContainer(player);
        if (!hasScript(player, "theme_park.dungeon.death_watch_bunker.mandalorian_crafting"))
        {
            string_id died = new string_id(MSGS, "died_during_crafting");
            sendSystemMessage(player, died);
            return SCRIPT_CONTINUE;
        }
        string_id done = new string_id(MSGS, "crafting_finished");
        sendSystemMessage(player, done);
        if (hasObjVar(self, "making.jetpack"))
        {
            makeArmorPiece("object/tangible/deed/vehicle_deed/jetpack_deed.iff", player);
        }
        removeObjVar(self, "making");
        removeObjVar(self, "have");
        removeObjVar(self, "giver");
        detachScript(player, "theme_park.dungeon.death_watch_bunker.mandalorian_crafting");
        location here = getLocation(self);
        obj_id room = here.cell;
        dictionary webster = new dictionary();
        webster.put("player", player);
        messageTo(room, "cleanOutRoom", webster, 1, false);
        return SCRIPT_CONTINUE;
    }
    public void ingredientMessages(obj_id self, obj_id player) throws InterruptedException
    {
        string_id needs = new string_id(MSGS, "items_still_needed");
        sendSystemMessage(player, needs);
        if (!hasObjVar(self, "making"))
        {
            string_id tailor = new string_id(MSGS, "jetpack_base");
            sendSystemMessage(player, tailor);
            return;
        }
        if (!hasObjVar(self, "have.alum"))
        {
            string_id alum = new string_id(MSGS, "alum_mineral");
            sendSystemMessage(player, alum);
        }
        if (!hasObjVar(self, "have.dispersion"))
        {
            string_id binary = new string_id(MSGS, "dispersion");
            sendSystemMessage(player, binary);
        }
        if (!hasObjVar(self, "have.injector"))
        {
            string_id emulsifier = new string_id(MSGS, "fuel_injector");
            sendSystemMessage(player, emulsifier);
        }
        if (!hasObjVar(self, "have.fan"))
        {
            string_id gloves = new string_id(MSGS, "ducted_fan");
            sendSystemMessage(player, gloves);
        }
        if (!hasObjVar(self, "have.stabilizer"))
        {
            string_id leggings = new string_id(MSGS, "stabilizer");
            sendSystemMessage(player, leggings);
        }
        return;
    }
    public void createTerminal(obj_id self) throws InterruptedException
    {
        location here = getLocation(self);
        here.x = here.x + 1;
        obj_id term = createObject("object/tangible/dungeon/terminal_free_s1.iff", here);
        setObjVar(self, "terminal", term);
        setObjVar(term, "droid", self);
        attachScript(term, "theme_park.dungeon.death_watch_bunker.mandalorian_crafting");
        setName(term, "Mandalorian Engineering Unit");
        return;
    }
    public void makeArmorPiece(String type, obj_id player) throws InterruptedException
    {
        obj_id playerInv = utils.getInventoryContainer(player);
        obj_id armor = createObject(type, playerInv, null);
        CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU has created a jetpack.", player);
        return;
    }
    public void spawnGuards(obj_id self) throws InterruptedException
    {
        obj_id structure = getTopMostContainer(self);
        int defenderCreatures = dataTableGetNumRows(TBL_JETPACK_WAVE);
        int x = 0;
        while (x < defenderCreatures)
        {
            String spawn = dataTableGetString(TBL_JETPACK_WAVE, x, "spawns");
            float xCoord = dataTableGetFloat(TBL_JETPACK_WAVE, x, "loc_x");
            float yCoord = dataTableGetFloat(TBL_JETPACK_WAVE, x, "loc_y");
            float zCoord = dataTableGetFloat(TBL_JETPACK_WAVE, x, "loc_z");
            location myself = getLocation(self);
            String planet = myself.area;
            obj_id top = getTopMostContainer(self);
            String spawnRoom = dataTableGetString(TBL_JETPACK_WAVE, x, "room");
            obj_id room = getCellId(structure, spawnRoom);
            location spawnPoint = new location(xCoord, yCoord, zCoord, planet, room);
            obj_id spawnedCreature = create.object(spawn, spawnPoint);
            attachScript(spawnedCreature, "theme_park.dungeon.death_watch_bunker.attacker_wave");
            x = x + 1;
        }
        return;
    }
    public int roomReset(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id droid = getSelf();
        removeObjVar(droid, "have");
        removeObjVar(droid, "making");
        if (hasObjVar(droid, "giver"))
        {
            removeObjVar(droid, "giver");
        }
        return SCRIPT_CONTINUE;
    }
    public int cleanUp(obj_id self, dictionary params) throws InterruptedException
    {
        if (hasObjVar(self, "giver"))
        {
            removeObjVar(self, "giver");
        }
        return SCRIPT_CONTINUE;
    }
}
