package script.theme_park.dungeon.death_watch_bunker;

import script.dictionary;
import script.library.ai_lib;
import script.library.create;
import script.library.utils;
import script.location;
import script.obj_id;
import script.string_id;

public class craft_tailor_droid extends script.base_script
{
    public craft_tailor_droid()
    {
    }
    public static final String TBL_TAILOR_WAVE = "datatables/dungeon/death_watch/tailor_crafting_attack_wave.iff";
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
        messageTo(room, "addTailorDroid", droid, 5, false);
        return SCRIPT_CONTINUE;
    }
    public int OnGiveItem(obj_id self, obj_id item, obj_id giver) throws InterruptedException
    {
        CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU has put an item in the tailor droid", giver);
        String setting = getConfigSetting("Dungeon", "Death_Watch");
        if (setting == null || setting.equals("false") || setting.equals("0"))
        {
            return SCRIPT_CONTINUE;
        }
        if (!hasSkill(giver, "class_domestics_phase4_master"))
        {
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU was not a crafting tailor master, so erroring out.");
            string_id master_tailor = new string_id(MSGS, "master_tailor_required");
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
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU has put an item into the tailor droid but wasn't the right player, someone else started the process.", giver);
                string_id wrong = new string_id(MSGS, "same_user_only");
                sendSystemMessage(giver, wrong);
                return SCRIPT_CONTINUE;
            }
        }
        setObjVar(giver, "dw_craft.droid", self);
        String justGot = getTemplateName(item);
        if (justGot.equals("object/tangible/wearables/armor/bounty_hunter/armor_bounty_hunter_belt.iff"))
        {
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a bounty hunter belt into the droid");
            if (hasObjVar(self, "have.belt"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put a bounty hunter belt into the droid, but the droid already had one.");
                string_id already = new string_id(MSGS, "already_has_component");
                sendSystemMessage(giver, already);
                return SCRIPT_CONTINUE;
            }
            else if (hasObjVar(self, "have.gloves") || hasObjVar(self, "have.leggings"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put a bounty hunter belt into the droid, but the droid was already working on something else.");
                string_id other = new string_id(MSGS, "making_something_else");
                sendSystemMessage(giver, other);
                return SCRIPT_CONTINUE;
            }
            else 
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a bounty hunter belt into the droid, and everything is functioning properly");
                setObjVar(self, "have.belt", 1);
                setObjVar(self, "making.belt", 1);
                destroyObject(item);
                checkForComplete(self, giver);
            }
        }
        if (justGot.equals("object/tangible/wearables/armor/bounty_hunter/armor_bounty_hunter_leggings.iff"))
        {
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a bounty hunter leggings into the droid");
            if (hasObjVar(self, "have.leggings"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put a bounty hunter leggings into the droid, but the droid already had one.");
                string_id already = new string_id(MSGS, "already_has_component");
                sendSystemMessage(giver, already);
                return SCRIPT_CONTINUE;
            }
            else if (hasObjVar(self, "have.gloves") || hasObjVar(self, "have.belt"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put a bounty hunter leggings into the droid, but the droid was working on something else.");
                string_id other = new string_id(MSGS, "making_something_else");
                sendSystemMessage(giver, other);
                return SCRIPT_CONTINUE;
            }
            else 
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a bounty hunter leggings into the droid, and everything is functioning properly");
                setObjVar(self, "have.leggings", 1);
                setObjVar(self, "making.leggings", 1);
                destroyObject(item);
                checkForComplete(self, giver);
            }
        }
        if (justGot.equals("object/tangible/wearables/armor/bounty_hunter/armor_bounty_hunter_gloves.iff"))
        {
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a bounty hunter gloves into the droid");
            if (hasObjVar(self, "have.gloves"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put a bounty hunter gloves into the droid, but the droid already had one.");
                string_id already = new string_id(MSGS, "already_has_component");
                sendSystemMessage(giver, already);
                return SCRIPT_CONTINUE;
            }
            else if (hasObjVar(self, "have.leggings") || hasObjVar(self, "have.belt"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put a bounty hunter gloves into the droid, but the droid was already working on something else.");
                string_id other = new string_id(MSGS, "making_something_else");
                sendSystemMessage(giver, other);
                return SCRIPT_CONTINUE;
            }
            
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed a bounty hunter gloves into the droid, and everything is functioning properly");
                setObjVar(self, "making.gloves", 1);
                setObjVar(self, "have.gloves", 1);
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
        if (justGot.equals("object/tangible/loot/dungeon/death_watch_bunker/binary_liquid.iff"))
        {
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed binary liquid into the droid");
            if (hasObjVar(self, "have.binary"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put binary liquid, but the droid already had one.");
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
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU placed emulsifier into the droid");
            if (hasObjVar(self, "have.emulsifier"))
            {
                CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Mandalorian Armor: Player %TU put emulsifier into the droid, but the droid already had one.");
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
        string_id done = new string_id(MSGS, "crafting_finished_schematic");
        sendSystemMessage(player, done);
        if (hasObjVar(self, "making.gloves"))
        {
            makeArmorPiece("object/tangible/loot/loot_schematic/death_watch_mandalorian_gloves_schematic.iff", player);
        }
        if (hasObjVar(self, "making.leggings"))
        {
            makeArmorPiece("object/tangible/loot/loot_schematic/death_watch_mandalorian_leggings_schematic.iff", player);
        }
        if (hasObjVar(self, "making.belt"))
        {
            makeArmorPiece("object/tangible/loot/loot_schematic/death_watch_mandalorian_belt_schematic.iff", player);
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
            string_id tailor = new string_id(MSGS, "tailored_items");
            sendSystemMessage(player, tailor);
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
        if (!hasObjVar(self, "have.gloves") && hasObjVar(self, "making.gloves"))
        {
            string_id gloves = new string_id(MSGS, "bounty_hunter_gloves");
            sendSystemMessage(player, gloves);
        }
        if (!hasObjVar(self, "have.leggings") && hasObjVar(self, "making.leggings"))
        {
            string_id leggings = new string_id(MSGS, "bounty_hunter_leggings");
            sendSystemMessage(player, leggings);
        }
        if (!hasObjVar(self, "have.belt") && hasObjVar(self, "making.belt"))
        {
            string_id belt = new string_id(MSGS, "bounty_hunter_belt");
            sendSystemMessage(player, belt);
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
    public void spawnGuards(obj_id self) throws InterruptedException
    {
        obj_id structure = getTopMostContainer(self);
        int defenderCreatures = dataTableGetNumRows(TBL_TAILOR_WAVE);
        int x = 0;
        while (x < defenderCreatures)
        {
            String spawn = dataTableGetString(TBL_TAILOR_WAVE, x, "spawns");
            float xCoord = dataTableGetFloat(TBL_TAILOR_WAVE, x, "loc_x");
            float yCoord = dataTableGetFloat(TBL_TAILOR_WAVE, x, "loc_y");
            float zCoord = dataTableGetFloat(TBL_TAILOR_WAVE, x, "loc_z");
            location myself = getLocation(self);
            String planet = myself.area;
            obj_id top = getTopMostContainer(self);
            String spawnRoom = dataTableGetString(TBL_TAILOR_WAVE, x, "room");
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
