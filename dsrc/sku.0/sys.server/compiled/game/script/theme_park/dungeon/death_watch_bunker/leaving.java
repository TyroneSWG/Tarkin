package script.theme_park.dungeon.death_watch_bunker;

import script.dictionary;
import script.obj_id;
import script.string_id;

public class leaving extends script.base_script
{
    public leaving()
    {
    }
    public static final String MSGS = "dungeon/death_watch";
    public int OnAboutToReceiveItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        obj_id top = getTopMostContainer(self);
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        if (!hasObjVar(item, "gm"))
        {
            String setting = getConfigSetting("Dungeon", "Death_Watch");
            if (setting == null || setting.equals("false") || setting.equals("0"))
            {
                string_id locked = new string_id(MSGS, "no_entry");
                sendSystemMessage(item, locked);
                return SCRIPT_OVERRIDE;
            }
            CustomerServiceLog("DUNGEON_DeathWatchBunker", "*Death Watch Entry: %TU has entered the Death Watch Bunker.", item);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAboutToLoseItem(obj_id self, obj_id destinationCell, obj_id transferrer, obj_id item) throws InterruptedException
    {
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        if (!isIdValid(destinationCell))
        {
            string_id relock = new string_id(MSGS, "relock");
            sendSystemMessage(item, relock);
            clearPlayer(item);
            reLockDoor(transferrer, item);
        }
        if (isIdValid(destinationCell))
        {
            clearPlayer(item);
            reLockDoor(transferrer, item);
        }
        return SCRIPT_CONTINUE;
    }
    public void reLockDoor(obj_id self, obj_id player) throws InterruptedException
    {
        String name = getFirstName(player);
        obj_id structure = getTopMostContainer(getSelf());
        obj_id hall = getCellId(structure, "hall12");
        obj_id storage = getCellId(structure, "storageroom24");
        obj_id mine = getCellId(structure, "medroom38");
        dictionary doorStuff = new dictionary();
        doorStuff.put("player", player);
        if (permissionsIsAllowed(hall, player) == true)
        {
            doorStuff.put("room", hall);
            messageTo(hall, "removeFromList", doorStuff, 3, false);
        }
        if (permissionsIsAllowed(storage, player) == true)
        {
            doorStuff.put("room", storage);
            messageTo(storage, "removeFromList", doorStuff, 3, false);
        }
        if (permissionsIsAllowed(mine, player) == true)
        {
            doorStuff.put("room", mine);
            messageTo(mine, "removeFromList", doorStuff, 3, false);
        }
        return;
    }
    public void clearPlayer(obj_id player) throws InterruptedException
    {
        if (hasObjVar(player, "death_watch.hallPass"))
        {
            removeObjVar(player, "death_watch.hallPass");
        }
        if (hasObjVar(player, "death_watch.storagePass"))
        {
            removeObjVar(player, "death_watch.storagePass");
        }
        if (hasObjVar(player, "death_watch.minePass"))
        {
            removeObjVar(player, "death_watch.minePass");
        }
        return;
    }
}
