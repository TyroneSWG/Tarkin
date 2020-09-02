package script.theme_park.dungeon.death_watch_bunker;

import script.dictionary;
import script.library.ai_lib;
import script.library.create;
import script.library.utils;
import script.location;
import script.obj_id;
import script.string_id;

public class craft_droidengineer_droid extends script.base_script
{
    public craft_droidengineer_droid()
    {
    }
    public static final String TBL_JETPACK_WAVE = "datatables/dungeon/death_watch/jetpack_crafting_attack_wave.iff";
    public static final String MSGS = "dungeon/death_watch";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        setInvulnerable(self, true);
        createTerminal(self);
        obj_id thisDroid = getSelf();
        location here = getLocation(self);
        obj_id room = here.cell;
        dictionary droid = new dictionary();
        droid.put("droid", thisDroid);
        messageTo(room, "addEngineerDroid", droid, 5, false);
        return SCRIPT_CONTINUE;
    }
    public int OnGiveItem(obj_id self, obj_id item, obj_id giver) throws InterruptedException
    {
        CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU has put an item in the droidengineer droid");
        String setting = getConfigSetting("Dungeon", "Death_Watch");
        if (setting == null || setting.equals("false") || setting.equals("0"))
        {
            return SCRIPT_CONTINUE;
        }
        if (!hasSkill(giver, "class_engineering_phase4_master"))
        {
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU was not a crafting droidengineer master, so erroring out.");
            string_id master_droidengineer = new string_id(MSGS, "master_droidengineer_required");
            sendSystemMessage(giver, master_droidengineer);
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
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU has put an item into the droidengineer droid but wasn't the right player, someone else started the process.");
                string_id wrong = new string_id(MSGS, "same_user_only");
                sendSystemMessage(giver, wrong);
                return SCRIPT_CONTINUE;
            }
        }
        setObjVar(giver, "dw_craft.droid", self);
        String justGot = getTemplateName(item);
        if (justGot.equals("object/tangible/wearables/armor/bounty_hunter/armor_bounty_hunter_bracer_l.iff"))
        {
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a bounty hunter left bracer into the droid");
            if (hasObjVar(self, "have.bracer_l"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put a left bracer into the droid, but the droid already had one.");
                string_id already = new string_id(MSGS, "already_has_component");
                sendSystemMessage(giver, already);
                return SCRIPT_CONTINUE;
            }
            else if (hasObjVar(self, "have.bracer_r") || hasObjVar(self, "have.helmet"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put a left bracer into the droid, but the droid was already making something else.");
                string_id other = new string_id(MSGS, "making_something_else");
                sendSystemMessage(giver, other);
                return SCRIPT_CONTINUE;
            }
            else 
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a left bracer into the droid, and everything is functioning properly");
                setObjVar(self, "have.bracer_l", 1);
                setObjVar(self, "making.bracer_l", 1);
                destroyObject(item);
                checkForComplete(self, giver);
            }
        }
        if (justGot.equals("object/tangible/wearables/armor/bounty_hunter/armor_bounty_hunter_helmet.iff"))
        {
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a bounty hunter helmet into the droid");
            if (hasObjVar(self, "have.helmet"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put a helmet into the droid, but the droid already had one.");
                string_id already = new string_id(MSGS, "already_has_component");
                sendSystemMessage(giver, already);
                return SCRIPT_CONTINUE;
            }
            else if (hasObjVar(self, "have.bracer_r") || hasObjVar(self, "have.bracer_l"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put a left bracer into the droid, but the droid was already making something else.");
                string_id other = new string_id(MSGS, "making_something_else");
                sendSystemMessage(giver, other);
                return SCRIPT_CONTINUE;
            }
            else 
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a left bracer into the droid, and everything is functioning properly");
                setObjVar(self, "have.helmet", 1);
                setObjVar(self, "making.helmet", 1);
                destroyObject(item);
                checkForComplete(self, giver);
            }
        }
        if (justGot.equals("object/tangible/wearables/armor/bounty_hunter/armor_bounty_hunter_bracer_r.iff"))
        {
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a bounty hunter right bracer into the droid");
            if (hasObjVar(self, "have.bracer_r"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put a right bracer into the droid, but the droid already had one.");
                string_id already = new string_id(MSGS, "already_has_component");
                sendSystemMessage(giver, already);
                return SCRIPT_CONTINUE;
            }
            else if (hasObjVar(self, "have.helmet") || hasObjVar(self, "have.bracer_l"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put a right bracer into the droid, but the droid was already making something else.");
                string_id other = new string_id(MSGS, "making_something_else");
                sendSystemMessage(giver, other);
                return SCRIPT_CONTINUE;
            }
            
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a right bracer into the droid, and everything is functioning properly");
                setObjVar(self, "making.bracer_r", 1);
                setObjVar(self, "have.bracer_r", 1);
                destroyObject(item);
                checkForComplete(self, giver);
            }
        }
        if (justGot.equals("object/tangible/loot/dungeon/death_watch_bunker/mining_drill_reward.iff"))
        {
            if (hasObjVar(self, "have.alum"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put alum into the droid, but the droid already had that.");
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
        if (justGot.equals("object/tangible/loot/dungeon/death_watch_bunker/binary_liquid.iff"))
        {
            if (hasObjVar(self, "have.binary"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put binary liquid into the droid, but the droid already had that.");
                string_id already = new string_id(MSGS, "already_has_component");
                sendSystemMessage(giver, already);
                return SCRIPT_CONTINUE;
            }
            else 
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed binary liquid into the droid, and everything is functioning properly");
                setObjVar(self, "have.binary", 1);
                destroyObject(item);
                checkForComplete(self, giver);
            }
        }
        if (justGot.equals("object/tangible/loot/dungeon/death_watch_bunker/emulsion_protection.iff"))
        {
            if (hasObjVar(self, "have.emulsifier"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put emulsifier into the droid, but the droid already had that.");
                string_id already = new string_id(MSGS, "already_has_component");
                sendSystemMessage(giver, already);
                return SCRIPT_CONTINUE;
            }
            else 
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed emulsifier into the droid, and everything is functioning properly");
                setObjVar(self, "have.emulsifier", 1);
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
            if (hasObjVar(self, "have.alum") && hasObjVar(self, "have.binary") && hasObjVar(self, "have.emulsifier"))
            {
                string_id startCraft = new string_id(MSGS, "starting_to_craft_schematic");
                sendSystemMessage(player, startCraft);
                dictionary webster = new dictionary();
                webster.put("player", player);
                attachScript(player, "theme_park.dungeon.death_watch_bunker.mandalorian_crafting");
                messageTo(player, "firstBox", webster, 10, false);
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU is now waiting on the first button.");
                return;
            }
            else 
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU attempted to start the process but needed more ingredients.");
                ingredientMessages(self, player);
            }
        }
        else 
        {
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU attempted to start the process but needed more ingredients.");
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
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU appears to have died during crafting.");
            return SCRIPT_CONTINUE;
        }
        string_id done = new string_id(MSGS, "crafting_finished_schematic");
        sendSystemMessage(player, done);
        if (hasObjVar(self, "making.bracer_r"))
        {
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU is now having a right bracer made.");
            makeArmorPiece("object/tangible/loot/loot_schematic/death_watch_mandalorian_bracer_r_schematic.iff", player);
        }
        if (hasObjVar(self, "making.helmet"))
        {
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU is now having a helmet made.");
            makeArmorPiece("object/tangible/loot/loot_schematic/death_watch_mandalorian_helmet_schematic.iff", player);
        }
        if (hasObjVar(self, "making.bracer_l"))
        {
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU is now having a left bracer made.");
            makeArmorPiece("object/tangible/loot/loot_schematic/death_watch_mandalorian_bracer_l_schematic.iff", player);
        }
        removeObjVar(self, "making");
        removeObjVar(self, "have");
        removeObjVar(self, "giver");
        detachScript(player, "theme_park.dungeon.death_watch_bunker.mandalorian_crafting");
        dictionary web = new dictionary();
        web.put("player", player);
        messageTo(self, "removePlayer", web, 15, false);
        return SCRIPT_CONTINUE;
    }
    public void ingredientMessages(obj_id self, obj_id player) throws InterruptedException
    {
        string_id needs = new string_id(MSGS, "items_still_needed");
        sendSystemMessage(player, needs);
        if (!hasObjVar(self, "making"))
        {
            string_id droidengineer = new string_id(MSGS, "droid_engineer_items");
            sendSystemMessage(player, droidengineer);
            return;
        }
        if (!hasObjVar(self, "have.alum"))
        {
            string_id alum = new string_id(MSGS, "alum_mineral");
            sendSystemMessage(player, alum);
        }
        if (!hasObjVar(self, "have.binary"))
        {
            string_id binary = new string_id(MSGS, "binary_liquid");
            sendSystemMessage(player, binary);
        }
        if (!hasObjVar(self, "have.emulsifier"))
        {
            string_id emulsifier = new string_id(MSGS, "emulsifier");
            sendSystemMessage(player, emulsifier);
        }
        if (!hasObjVar(self, "have.bracer_r") && hasObjVar(self, "making.bracer_r"))
        {
            string_id bracer_r = new string_id(MSGS, "bounty_hunter_bracer_r");
            sendSystemMessage(player, bracer_r);
        }
        if (!hasObjVar(self, "have.helmet") && hasObjVar(self, "making.helmet"))
        {
            string_id helmet = new string_id(MSGS, "bounty_hunter_helmet");
            sendSystemMessage(player, helmet);
        }
        if (!hasObjVar(self, "have.bracer_l") && hasObjVar(self, "making.bracer_l"))
        {
            string_id bracer_l = new string_id(MSGS, "bounty_hunter_bracer_l");
            sendSystemMessage(player, bracer_l);
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
        CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU has created a" + type + ".", player);
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
    public int removePlayer(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = params.getObjId("player");
        location exitPoint = new location(-4648, 0, 4331, "endor", null);
        utils.warpPlayer(player, exitPoint);
        detachScript(player, "theme_park.dungeon.death_watch_bunker.death_script");
        return SCRIPT_CONTINUE;
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
    public int cleanUp(obj_id self, dictionary params) throws InterruptedException
    {
        if (hasObjVar(self, "giver"))
        {
            removeObjVar(self, "giver");
        }
        return SCRIPT_CONTINUE;
    }
}
