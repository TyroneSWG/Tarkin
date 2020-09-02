package script.theme_park.outbreak;

import script.*;
import script.library.*;

public class blackwing_canister_collection extends script.base_script
{
    public blackwing_canister_collection()
    {
    }
    public static final String OUTBREAK = "theme_park/outbreak/outbreak";
    public static final string_id SID_YOU_DIED = new string_id(OUTBREAK, "canister_death");
    public static final string_id SID_NOT_WHILE_MOUNTED = new string_id(OUTBREAK, "canister_not_while_mounted");
    public static final string_id SID_ZIP_BAR = new string_id(OUTBREAK, "canister_zip_bar");
    public static final string_id SID_MNU_DESTROY = new string_id(OUTBREAK, "canister_destroy_menu");
    public static final string_id SID_NOT_SURE_HOW_DESTROY = new string_id(OUTBREAK, "canister_no_collection_prerequisite");
    public static final string_id SID_ALRDY_COMPLETED_COLLECTION = new string_id(OUTBREAK, "canister_already_completed_collection");
    public static final int COUNTDOWN_TIMER = 3;
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (isDead(player) || isIncapacitated(player))
        {
            return SCRIPT_CONTINUE;
        }
        menu_info_data data = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        mi.addRootMenu(menu_info_types.ITEM_USE, SID_MNU_DESTROY);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (isDead(player) || isIncapacitated(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (!groundquests.hasCompletedQuest(player, "outbreak_quest_01_imperial") && !groundquests.hasCompletedQuest(player, "outbreak_quest_01_rebel") && !groundquests.hasCompletedQuest(player, "outbreak_quest_01_neutral"))
        {
            CustomerServiceLog("outbreak_themepark", "blackwing_canister_collection.OnObjectMenuSelect() Player: " + player + " was killed becasue they messed with a blackwing canister before getting antivirus. Canister: " + self);
            sendSystemMessage(player, SID_YOU_DIED);
            setPosture(player, POSTURE_INCAPACITATED);
            pclib.coupDeGrace(player, player, true, true);
        }
        if (pet_lib.isMounted(player))
        {
            sendSystemMessage(player, SID_NOT_WHILE_MOUNTED);
            return SCRIPT_CONTINUE;
        }
        if (hasCompletedCollection(player, "deathtrooper_virus_canister_demo"))
        {
            sendSystemMessage(player, SID_ALRDY_COMPLETED_COLLECTION);
            CustomerServiceLog("outbreak_themepark", "blackwing_canister_collection.OnObjectMenuSelect() Player completed collection. Aborting for canister: (" + self + "). Player: " + player);
            return SCRIPT_CONTINUE;
        }
        obj_id parentObj = getObjIdObjVar(self, "objParent");
        if (!isValidId(parentObj))
        {
            CustomerServiceLog("outbreak_themepark", "blackwing_canister_collection.OnObjectMenuSelect() Could not find the parent spawner object for canister: (" + self + "). The operation was aborted for Player: " + player);
            return SCRIPT_CONTINUE;
        }
        String quest = getStringObjVar(self, "strQuest");
        if (quest == null || quest.length() <= 0)
        {
            CustomerServiceLog("outbreak_themepark", "blackwing_canister_collection.OnObjectMenuSelect() Could not find the parent spawner quest for participating in the canister quest for canister: (" + self + "). The operation was aborted for Player: " + player);
            return SCRIPT_CONTINUE;
        }
        if (!groundquests.isTaskActive(player, quest, "canisterWaitingForSignal"))
        {
            sendSystemMessage(player, SID_NOT_SURE_HOW_DESTROY);
            CustomerServiceLog("outbreak_themepark", "blackwing_canister_collection.OnObjectMenuSelect() Player: " + player + " did not have the quest " + quest + " needed for this collection. canister: (" + self);
            return SCRIPT_CONTINUE;
        }
        String questSignal = getStringObjVar(self, "strSignal");
        if (questSignal == null || questSignal.length() <= 0)
        {
            CustomerServiceLog("outbreak_themepark", "blackwing_canister_collection.OnObjectMenuSelect() Could not find the parent spawner quest SIGNAL for participating in the canister quest for canister: (" + self + "). The operation was aborted for Player: " + player);
            return SCRIPT_CONTINUE;
        }
        String collectionSlot = getStringObjVar(self, "strCollection");
        if (collectionSlot == null || collectionSlot.length() <= 0)
        {
            CustomerServiceLog("outbreak_themepark", "blackwing_canister_collection.OnObjectMenuSelect() Could not find the parent spawner collection objvar for canister: (" + self + "). The operation was aborted for Player: " + player);
            return SCRIPT_CONTINUE;
        }
        if (!hasCompletedCollectionSlotPrereq(player, collectionSlot))
        {
            sendSystemMessage(player, SID_NOT_SURE_HOW_DESTROY);
            CustomerServiceLog("outbreak_themepark", "blackwing_canister_collection.OnObjectMenuSelect() Player: " + player + " does not have the collection prerequisite for canisters. Aborting. Canister: " + self);
            return SCRIPT_CONTINUE;
        }
        if (hasCompletedCollectionSlot(player, collectionSlot))
        {
            sendSystemMessage(player, SID_ALRDY_COMPLETED_COLLECTION);
            CustomerServiceLog("outbreak_themepark", "blackwing_canister_collection.OnObjectMenuSelect() Player completed collection SLOT. Aborting for canister: (" + self + "). Player: " + player);
            return SCRIPT_CONTINUE;
        }
        int startTime = 0;
        int range = 3;
        int flags = 0;
        flags |= sui.CD_EVENT_INCAPACITATE;
        flags |= sui.CD_EVENT_DAMAGED;
        stealth.testInvisNonCombatAction(player, self);
        int countdownSui = sui.smartCountdownTimerSUI(self, player, "quest_countdown_timer", SID_ZIP_BAR, startTime, COUNTDOWN_TIMER, "handleObjectSwapTimer", range, flags);
        doAnimationAction(player, "manipulate_low");
        CustomerServiceLog("outbreak_themepark", "blackwing_canister_collection.OnObjectMenuSelect() Player: " + player + " is destroying blackwing canister: " + self);
        return SCRIPT_CONTINUE;
    }
    public int handleObjectSwapTimer(obj_id self, dictionary params) throws InterruptedException
    {
        if (params == null || params.equals(""))
        {
            return SCRIPT_CONTINUE;
        }
        int pid = params.getInt("id");
        obj_id player = params.getObjId("player");
        if (!isIdValid(player) || !exists(player))
        {
            return SCRIPT_CONTINUE;
        }
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            detachScript(player, sui.COUNTDOWNTIMER_PLAYER_SCRIPT);
            return SCRIPT_CONTINUE;
        }
        else if (bp == sui.BP_REVERT)
        {
            CustomerServiceLog("outbreak_themepark", "blackwing_canister_collection.handleObjectSwapTimer() Player: " + player + " failed to destroy the canister because the player moved or was disrupted. Canister: " + self);
            int event = params.getInt("event");
            if (event == sui.CD_EVENT_LOCOMOTION)
            {
                sendSystemMessage(player, new string_id("quest/groundquests", "countdown_interrupted_locomotion"));
            }
            else if (event == sui.CD_EVENT_INCAPACITATE)
            {
                sendSystemMessage(player, new string_id("quest/groundquests", "countdown_interrupted_incapacitated"));
            }
            else if (event == sui.CD_EVENT_DAMAGED)
            {
                sendSystemMessage(player, new string_id("quest/groundquests", "countdown_interrupted_damaged"));
            }
            return SCRIPT_CONTINUE;
        }
        if (!hasObjVar(player, sui.COUNTDOWNTIMER_SUI_VAR))
        {
            return SCRIPT_CONTINUE;
        }
        int test_pid = getIntObjVar(player, sui.COUNTDOWNTIMER_SUI_VAR);
        if (pid != test_pid)
        {
            return SCRIPT_CONTINUE;
        }
        forceCloseSUIPage(pid);
        detachScript(player, sui.COUNTDOWNTIMER_PLAYER_SCRIPT);
        obj_id parentObj = getObjIdObjVar(self, "objParent");
        if (!isValidId(parentObj))
        {
            CustomerServiceLog("outbreak_themepark", "blackwing_canister_collection.handleObjectSwapTimer() Could not find the parent spawner object for canister: (" + self + "). The replacement object creation was aborted for Player: " + player);
            return SCRIPT_CONTINUE;
        }
        String quest = getStringObjVar(self, "strQuest");
        if (quest == null || quest.length() <= 0)
        {
            CustomerServiceLog("outbreak_themepark", "blackwing_canister_collection.handleObjectSwapTimer() Could not find the parent spawner quest for participating in the canister quest for canister: (" + self + "). The operation was aborted for Player: " + player);
            return SCRIPT_CONTINUE;
        }
        if (!groundquests.isTaskActive(player, quest, "canisterWaitingForSignal"))
        {
            CustomerServiceLog("outbreak_themepark", "blackwing_canister_collection.handleObjectSwapTimer() Player: " + player + " did not have the quest " + quest + " needed for this collection. canister: (" + self);
            return SCRIPT_CONTINUE;
        }
        String questSignal = getStringObjVar(self, "strSignal");
        if (questSignal == null || questSignal.length() <= 0)
        {
            CustomerServiceLog("outbreak_themepark", "blackwing_canister_collection.handleObjectSwapTimer() Could not find the parent spawner quest SIGNAL for participating in the canister quest for canister: (" + self + "). The operation was aborted for Player: " + player);
            return SCRIPT_CONTINUE;
        }
        String collectionSlot = getStringObjVar(self, "strCollection");
        if (collectionSlot == null || collectionSlot.length() <= 0)
        {
            CustomerServiceLog("outbreak_themepark", "blackwing_canister_collection.handleObjectSwapTimer() Could not find the parent spawner collection objvar for canister: (" + self + "). The operation was aborted for Player: " + player);
            return SCRIPT_CONTINUE;
        }
        CustomerServiceLog("outbreak_themepark", "blackwing_canister_collection.handleObjectSwapTimer() Replacing canister (" + self + ") with a new canister object with detonator blinking for Player: " + player);
        obj_id replacementEventObject = createObject("object/tangible/item/item_blackwing_canister_with_detonator.iff", getLocation(self));
        if (!isValidId(replacementEventObject) || !exists(replacementEventObject))
        {
            CustomerServiceLog("outbreak_themepark", "blackwing_canister_collection.handleObjectSwapTimer() Replacing canister (" + self + ") FAILED. The replacement object was never created for Player: " + player);
            return SCRIPT_CONTINUE;
        }
        attachScript(replacementEventObject, "theme_park.outbreak.blackwing_canister_destroy");
        setObjVar(replacementEventObject, "owner", player);
        setObjVar(replacementEventObject, "objParent", parentObj);
        setObjVar(replacementEventObject, "collectionSlot", collectionSlot);
        setObjVar(replacementEventObject, "questSignal", questSignal);
        setObjVar(replacementEventObject, "quest", quest);
        messageTo(self, "destroySelf", null, 0, false);
        return SCRIPT_CONTINUE;
    }
    public int destroySelf(obj_id self, dictionary params) throws InterruptedException
    {
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
}
