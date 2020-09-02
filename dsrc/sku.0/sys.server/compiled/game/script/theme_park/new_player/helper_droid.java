package script.theme_park.new_player;

import script.*;
import script.library.*;

import java.util.Vector;

public class helper_droid extends script.base_script
{
    public helper_droid()
    {
    }
    public static final String NEW_PLAYER_SCRIPT = "theme_park.new_player.new_player";
    public static final string_id SID_DEFAULT_TEXT_MSG = new string_id("new_player", "default_text_msg");
    public static final string_id SID_DEFAULT_OK_BUTTON = new string_id("new_player", "default_ok_button");
    public static final string_id SID_DEFAULT_CANCEL_BUTTON = new string_id("new_player", "default_cancel_button");
    public static final string_id SID_QUEST_MENU = new string_id("new_player", "menu_quests");
    public static final string_id SID_BRAWLER_SUBMENU = new string_id("new_player", "submenu_brawler");
    public static final string_id SID_MARKSMAN_SUBMENU = new string_id("new_player", "submenu_marksman");
    public static final string_id SID_SCOUT_SUBMENU = new string_id("new_player", "submenu_scout");
    public static final string_id SID_ARTISAN_SUBMENU = new string_id("new_player", "submenu_artisan");
    public static final string_id SID_ENTERTAINER_SUBMENU = new string_id("new_player", "submenu_entertainer");
    public static final string_id SID_MEDIC_SUBMENU = new string_id("new_player", "submenu_medic");
    public static final string_id SID_OTHER_MENU = new string_id("new_player", "menu_other");
    public static final string_id SID_CLONING_SUBMENU = new string_id("new_player", "submenu_cloning");
    public static final string_id SID_TRAVEL_SUBMENU = new string_id("new_player", "submenu_travel");
    public static final string_id SID_VEHICLE_SUBMENU = new string_id("new_player", "submenu_vehicle");
    public static final string_id SID_SPACE_MENU = new string_id("new_player", "menu_space");
    public static final string_id SID_CANNOT_CALL_IN_SPACE = new string_id("new_player", "space_cannot_call");
    public static final String NEW_PLAYER_OBJVAR_BASE = "new_player.";
    public static final String QUEST_OBJVAR_BASE = NEW_PLAYER_OBJVAR_BASE + "quest.";
    public static final String BRAWLER_QUEST_OBJVAR = QUEST_OBJVAR_BASE + "brawler";
    public static final String MARKSMAN_QUEST_OBJVAR = QUEST_OBJVAR_BASE + "marksman";
    public static final String MEDIC_QUEST_OBJVAR = QUEST_OBJVAR_BASE + "medic";
    public static final String ARTISAN_QUEST_OBJVAR = QUEST_OBJVAR_BASE + "artisan";
    public static final String ENTERTAINER_QUEST_OBJVAR = QUEST_OBJVAR_BASE + "entertainer";
    public static final String SCOUT_QUEST_OBJVAR = QUEST_OBJVAR_BASE + "scout";
    public static final String CURRENT_PROFESSION_OBJVAR = NEW_PLAYER_OBJVAR_BASE + "currentProfession";
    public static final String QUEST_COMPLETE_OBJVAR = NEW_PLAYER_OBJVAR_BASE + "questComplete";
    public static final String PROFESSION_ADDED_OBJVAR = NEW_PLAYER_OBJVAR_BASE + "newProfessionAdded";
    public static final String BRAWLER = "brawler";
    public static final String MARKSMAN = "marksman";
    public static final String MEDIC = "medic";
    public static final String ARTISAN = "artisan";
    public static final String ENTERTAINER = "entertainer";
    public static final String SCOUT = "scout";
    public static final String CLONING_COUPON = "object/tangible/item/new_player/new_player_cloning_coupon.iff";
    public static final String TRAVEL_COUPON = "object/tangible/item/new_player/new_player_travel_coupon.iff";
    public static final String VEHICLE_COUPON = "object/tangible/item/new_player/new_player_vehicle_coupon.iff";
    public static final String NEW_PLAYER_LOG = "NEW_PLAYER_QUESTS";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        messageTo(self, "handleOnAttach", null, 1, false);
        return SCRIPT_CONTINUE;
    }
    public int OnDestroy(obj_id self) throws InterruptedException
    {
        obj_id master = getMaster(self);
        if (isIdValid(master))
        {
            messageTo(master, "handleHelperDroidDestroyed", null, 2, false);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (!newPlayerQuestsEnabled())
        {
            return SCRIPT_CONTINUE;
        }
        if (!isIdValid(self))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id master = getMaster(self);
        if (!isIdValid(master))
        {
            return SCRIPT_CONTINUE;
        }
        if (player != master)
        {
            return SCRIPT_CONTINUE;
        }
        if (features.isSpaceEdition(master))
        {
            int menuSpace = mi.addRootMenu(menu_info_types.SERVER_MENU10, SID_SPACE_MENU);
        }
        if (hasObjVar(master, BRAWLER_QUEST_OBJVAR) || hasObjVar(master, MARKSMAN_QUEST_OBJVAR) || hasObjVar(master, SCOUT_QUEST_OBJVAR) || hasObjVar(master, ARTISAN_QUEST_OBJVAR) || hasObjVar(master, ENTERTAINER_QUEST_OBJVAR) || hasObjVar(master, MEDIC_QUEST_OBJVAR))
        {
            int menuOption = mi.addRootMenu(menu_info_types.SERVER_ITEM_OPTIONS, SID_QUEST_MENU);
            if (hasObjVar(master, BRAWLER_QUEST_OBJVAR))
            {
                mi.addSubMenu(menuOption, menu_info_types.SERVER_MENU1, SID_BRAWLER_SUBMENU);
            }
            if (hasObjVar(master, MARKSMAN_QUEST_OBJVAR))
            {
                mi.addSubMenu(menuOption, menu_info_types.SERVER_MENU2, SID_MARKSMAN_SUBMENU);
            }
            if (hasObjVar(master, SCOUT_QUEST_OBJVAR))
            {
                mi.addSubMenu(menuOption, menu_info_types.SERVER_MENU3, SID_SCOUT_SUBMENU);
            }
            if (hasObjVar(master, ARTISAN_QUEST_OBJVAR))
            {
                mi.addSubMenu(menuOption, menu_info_types.SERVER_MENU4, SID_ARTISAN_SUBMENU);
            }
            if (hasObjVar(master, ENTERTAINER_QUEST_OBJVAR))
            {
                mi.addSubMenu(menuOption, menu_info_types.SERVER_MENU5, SID_ENTERTAINER_SUBMENU);
            }
            if (hasObjVar(master, MEDIC_QUEST_OBJVAR))
            {
                mi.addSubMenu(menuOption, menu_info_types.SERVER_MENU6, SID_MEDIC_SUBMENU);
            }
        }
        int menuExtras = mi.addRootMenu(menu_info_types.SERVER_MENU7, SID_OTHER_MENU);
        if (utils.playerHasItemByTemplate(master, CLONING_COUPON))
        {
            mi.addSubMenu(menuExtras, menu_info_types.SERVER_MENU8, SID_CLONING_SUBMENU);
        }
        if (utils.playerHasItemByTemplate(master, TRAVEL_COUPON))
        {
            mi.addSubMenu(menuExtras, menu_info_types.SERVER_MENU9, SID_TRAVEL_SUBMENU);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (!newPlayerQuestsEnabled())
        {
            return SCRIPT_CONTINUE;
        }
        if (!isIdValid(self))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id master = getMaster(self);
        if (!isIdValid(master))
        {
            return SCRIPT_CONTINUE;
        }
        if (player != master)
        {
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.SERVER_MENU10)
        {
            if (features.isSpaceEdition(player))
            {
                String custLogMsg = "New Player Quests: %TU used the helper droid radial menu for space info.";
                CustomerServiceLog(NEW_PLAYER_LOG, custLogMsg, player);
                messageTo(master, "handleSpaceIntroBegin", null, 1, false);
                return SCRIPT_CONTINUE;
            }
            else 
            {
                String custLogMsg = "New Player Quests: %TU tried to use the helper droid radial menu for space info, but does not have the space edition of the game.";
                CustomerServiceLog(NEW_PLAYER_LOG, custLogMsg, player);
                return SCRIPT_CONTINUE;
            }
        }
        if (item == menu_info_types.SERVER_MENU7)
        {
            if (rand(1, 9) == 9)
            {
                String custLogMsg = "New Player Quests: %TU used the helper droid radial menu for general help which opens the holocron.";
                CustomerServiceLog(NEW_PLAYER_LOG, custLogMsg, player);
                openHolocronToPage(player, "WelcomeToSWG.WhereDoIBegin.ThingsToDo.MissionTerminals");
                openHolocronToPage(player, "WelcomeToSWG.WhereDoIBegin.ThingsToDo");
            }
            else 
            {
                openHolocronToPage(player, "WelcomeToSWG");
            }
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.SERVER_MENU8)
        {
            String custLogMsg = "New Player Quests: %TU used the helper droid radial general help menu for cloning.";
            CustomerServiceLog(NEW_PLAYER_LOG, custLogMsg, player);
            openHolocronToPage(player, "ImportantBuildings.CloningFacility");
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.SERVER_MENU9)
        {
            String custLogMsg = "New Player Quests: %TU used the helper droid radial general help menu for traveling.";
            CustomerServiceLog(NEW_PLAYER_LOG, custLogMsg, player);
            openHolocronToPage(player, "Navigation.LongDistanceTravel");
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.SERVER_ITEM_OPTIONS)
        {
            sendSystemMessage(player, new string_id("new_player", "profession_quest_menus"));
            return SCRIPT_CONTINUE;
        }
        String profession = "";
        boolean submenuItemChosen = false;
        if (item == menu_info_types.SERVER_MENU1)
        {
            if (hasObjVar(player, BRAWLER_QUEST_OBJVAR))
            {
                profession = BRAWLER;
                submenuItemChosen = true;
            }
        }
        if (item == menu_info_types.SERVER_MENU2)
        {
            if (hasObjVar(player, MARKSMAN_QUEST_OBJVAR))
            {
                profession = MARKSMAN;
                submenuItemChosen = true;
            }
        }
        if (item == menu_info_types.SERVER_MENU3)
        {
            if (hasObjVar(player, SCOUT_QUEST_OBJVAR))
            {
                profession = SCOUT;
                submenuItemChosen = true;
            }
        }
        if (item == menu_info_types.SERVER_MENU4)
        {
            if (hasObjVar(player, ARTISAN_QUEST_OBJVAR))
            {
                profession = ARTISAN;
                submenuItemChosen = true;
            }
        }
        if (item == menu_info_types.SERVER_MENU5)
        {
            if (hasObjVar(player, ENTERTAINER_QUEST_OBJVAR))
            {
                profession = ENTERTAINER;
                submenuItemChosen = true;
            }
        }
        if (item == menu_info_types.SERVER_MENU6)
        {
            if (hasObjVar(player, MEDIC_QUEST_OBJVAR))
            {
                profession = MEDIC;
                submenuItemChosen = true;
            }
        }
        if (submenuItemChosen)
        {
            String custLogMsg = "New Player Helper Droid: %TU used the helper droid radial menu for help with profession " + profession + ".";
            CustomerServiceLog(NEW_PLAYER_LOG, custLogMsg, self);
            setObjVar(player, CURRENT_PROFESSION_OBJVAR, profession);
            String questObjVar = QUEST_OBJVAR_BASE + profession;
            int questNum = getIntObjVar(player, questObjVar);
            if (questNum > 0)
            {
                messageTo(player, "handleGiveQuestMessage", null, 1, false);
            }
            else 
            {
                messageTo(player, "handleGiveNewProfessionQuestPrompt", null, 1, false);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int handleOnAttach(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id master = getMaster(self);
        if (!isIdValid(master))
        {
            destroyObject(self);
            return SCRIPT_CONTINUE;
        }
        if (!newPlayerQuestsEnabled())
        {
            obj_id pcd = callable.getCallableCD(self);
            if (isIdValid(pcd))
            {
                if (isGod(master))
                {
                    sendSystemMessageTestingOnly(master, "GODMODE MSG: New player quests disabled - destroying helper droid pcd.");
                }
                destroyObject(pcd);
            }
            if (isGod(master))
            {
                sendSystemMessageTestingOnly(master, "GODMODE MSG: New player quests disabled - destroying helper droid.");
            }
            destroyObject(self);
            return SCRIPT_CONTINUE;
        }
        if (isSpaceScene())
        {
            if (isGod(master))
            {
                sendSystemMessageTestingOnly(master, "GODMODE MSG: Cannot call helper droid in space - destroying helper droid.");
            }
            sendSystemMessage(master, SID_CANNOT_CALL_IN_SPACE);
            destroyObject(self);
            return SCRIPT_CONTINUE;
        }
        if (!isInvulnerable(self))
        {
            setInvulnerable(self, true);
        }
        String masterName = getName(master);
        int msgNum = rand(1, 3);
        utils.setScriptVar(self, "msgNum", msgNum);
        prose_package msg = prose.getPackage(new string_id("new_player", "general_greeting_prose_0" + msgNum), masterName);
        chat.chat(self, master, null, null, chat.ChatFlag_targetOnly, msg);
        messageTo(self, "handleDroidInitiateGreeting", null, 4, false);
        return SCRIPT_CONTINUE;
    }
    public int handleDroidInitiateGreeting(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id master = getMaster(self);
        if (!isIdValid(master))
        {
            return SCRIPT_CONTINUE;
        }
        String droidName = getName(self);
        String masterName = getName(master);
        string_id textMsg = null;
        string_id okButton = new string_id("new_player", "yes_please_button");
        string_id cancelButton = new string_id("new_player", "not_now_button");
        if (hasObjVar(master, QUEST_COMPLETE_OBJVAR))
        {
            String profession = getStringObjVar(master, QUEST_COMPLETE_OBJVAR);
            dictionary webster = new dictionary();
            webster.put("profession", profession);
            removeObjVar(master, QUEST_COMPLETE_OBJVAR);
            messageTo(master, "handleDoQuestComplete", webster, 1, false);
            return SCRIPT_CONTINUE;
        }
        int numQuests = getNumProfessionQuests(master);
        if (utils.hasScriptVar(master, "new_player.showPeriodicPrompt"))
        {
            if (numQuests == 1)
            {
                utils.removeScriptVar(master, "new_player.showPeriodicPrompt");
                messageTo(master, "handleGiveQuestMessage", null, 1, false);
            }
            else if (numQuests > 1)
            {
                utils.removeScriptVar(master, "new_player.showPeriodicPrompt");
                string_id greetingEnd = new string_id("new_player", "droid_periodic_check_end");
                showProfessionsListbox(master, self, greetingEnd);
            }
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(master, CURRENT_PROFESSION_OBJVAR))
        {
            if (utils.hasScriptVar(master, "new_player.firstEverDroid"))
            {
                if (features.isSpaceEdition(master))
                {
                    String custLogMsg = "New Player Quests: %TU is receiving a new helper droid and being prompted about space.";
                    CustomerServiceLog(NEW_PLAYER_LOG, custLogMsg, master);
                    messageTo(master, "handleSpaceIntroBegin", null, 1, false);
                }
                else 
                {
                    int msgNum = utils.getIntScriptVar(self, "msgNum");
                    string_id greetingBegin = new string_id("new_player", "droid_greeting_begin_0" + msgNum);
                    string_id greetingEnd = new string_id("new_player", "droid_greeting_end");
                    String textMsgStr = utils.packStringId(greetingBegin) + " \\#pcontrast1 " + masterName + " \\#., " + utils.packStringId(greetingEnd);
                    utils.removeScriptVar(master, "new_player.firstEverDroid");
                    twoButtonSui(master, master, "handleNewProfessionGreetingResponse", droidName, textMsgStr, okButton, cancelButton);
                }
            }
            else 
            {
                String profession = getStringObjVar(master, CURRENT_PROFESSION_OBJVAR);
                String questObjVar = QUEST_OBJVAR_BASE + profession;
                if (hasObjVar(master, PROFESSION_ADDED_OBJVAR))
                {
                    messageTo(master, "handleGiveProfessionAddedMessage", null, 1, false);
                }
                else 
                {
                    if (utils.hasScriptVar(master, "new_player.giveDroidGreeting"))
                    {
                        if (numQuests == 1)
                        {
                            utils.removeScriptVar(master, "new_player.giveDroidGreeting");
                            messageTo(master, "handleGiveQuestMessage", null, 1, false);
                        }
                        else if (numQuests > 1)
                        {
                            utils.removeScriptVar(master, "new_player.giveDroidGreeting");
                            string_id greetingEnd = new string_id("new_player", "droid_prompt_end");
                            showProfessionsListbox(master, self, greetingEnd);
                        }
                    }
                    else 
                    {
                        messageTo(master, "handleGiveQuestMessage", null, 1, false);
                    }
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
    public boolean newPlayerQuestsEnabled() throws InterruptedException
    {
        return false;
    }
    public int twoButtonSui(obj_id controller, obj_id player, String handler, String title, string_id textMsg, string_id okButton, string_id cancelButton) throws InterruptedException
    {
        String TEXTMSG = utils.packStringId(textMsg);
        return twoButtonSui(controller, player, handler, title, TEXTMSG, okButton, cancelButton);
    }
    public int twoButtonSui(obj_id controller, obj_id player, String handler, String title, String textMsg, string_id okButton, string_id cancelButton) throws InterruptedException
    {
        if (utils.hasScriptVar(player, "new_player.openSui"))
        {
            int oldSui = utils.getIntScriptVar(player, "new_player.openSui");
            utils.removeScriptVar(player, "new_player.openSui");
            if (oldSui > -1)
            {
                forceCloseSUIPage(oldSui);
            }
        }
        String OK_BUTTON = utils.packStringId(okButton);
        String CANCEL_BUTTON = utils.packStringId(cancelButton);
        int pid = sui.createSUIPage(sui.SUI_MSGBOX, controller, player, handler);
        setSUIProperty(pid, "", "Location", "500,75");
        setSUIProperty(pid, "", "Size", "500,200");
        setSUIProperty(pid, sui.MSGBOX_TITLE, sui.PROP_TEXT, title);
        setSUIProperty(pid, sui.MSGBOX_PROMPT, sui.PROP_TEXT, textMsg);
        sui.msgboxButtonSetup(pid, sui.YES_NO);
        setSUIProperty(pid, sui.MSGBOX_BTN_OK, sui.PROP_TEXT, OK_BUTTON);
        setSUIProperty(pid, sui.MSGBOX_BTN_CANCEL, sui.PROP_TEXT, CANCEL_BUTTON);
        utils.setScriptVar(player, "new_player.openSui", pid);
        sui.showSUIPage(pid);
        return pid;
    }
    public void showProfessionsListbox(obj_id player, obj_id droid, string_id greetingEnd) throws InterruptedException
    {
        if (utils.hasScriptVar(player, "new_player.openSui"))
        {
            int oldSui = utils.getIntScriptVar(player, "new_player.openSui");
            utils.removeScriptVar(player, "new_player.openSui");
            if (oldSui > -1)
            {
                forceCloseSUIPage(oldSui);
            }
        }
        utils.removeScriptVar(player, "new_player.professionsList");
        String droidName = getName(droid);
        String masterName = getName(player);
        int msgNum = utils.getIntScriptVar(droid, "msgNum");
        string_id greetingBegin = new string_id("new_player", "droid_greeting_begin_0" + msgNum);
        String TEXT_MSG = utils.packStringId(greetingBegin) + " \\#pcontrast1 " + masterName + " \\#., " + utils.packStringId(greetingEnd);
        int numQuests = getNumProfessionQuests(player);
        Vector tempList = new Vector();
        tempList.setSize(numQuests);
        if (hasObjVar(player, BRAWLER_QUEST_OBJVAR))
        {
            tempList = utils.addElement(tempList, "Brawler");
        }
        if (hasObjVar(player, MARKSMAN_QUEST_OBJVAR))
        {
            tempList = utils.addElement(tempList, "Marksman");
        }
        if (hasObjVar(player, SCOUT_QUEST_OBJVAR))
        {
            tempList = utils.addElement(tempList, "Scout");
        }
        if (hasObjVar(player, ARTISAN_QUEST_OBJVAR))
        {
            tempList = utils.addElement(tempList, "Artisan");
        }
        if (hasObjVar(player, MEDIC_QUEST_OBJVAR))
        {
            tempList = utils.addElement(tempList, "Medic");
        }
        if (hasObjVar(player, ENTERTAINER_QUEST_OBJVAR))
        {
            tempList = utils.addElement(tempList, "Entertainer");
        }
        if (tempList != null || tempList.size() > 0)
        {
            String[] professionsList = utils.toStaticStringArray(tempList);
            int pid = sui.listbox(player, player, TEXT_MSG, sui.OK_CANCEL, droidName, professionsList, "handleDroidGreeting", false, false);
            if (pid > -1)
            {
                utils.setScriptVar(player, "new_player.professionsList", professionsList);
                utils.setScriptVar(player, "new_player.openSui", pid);
                string_id okButton = new string_id("new_player", "default_okay_button");
                String OK_BUTTON = utils.packStringId(okButton);
                setSUIProperty(pid, sui.MSGBOX_BTN_OK, sui.PROP_TEXT, OK_BUTTON);
                setSUIProperty(pid, "", "Location", "500,75");
                setSUIProperty(pid, "", "Size", "500,250");
                showSUIPage(pid);
            }
        }
        return;
    }
    public int getNumProfessionQuests(obj_id player) throws InterruptedException
    {
        int num = 0;
        if (hasObjVar(player, BRAWLER_QUEST_OBJVAR))
        {
            num++;
        }
        if (hasObjVar(player, MARKSMAN_QUEST_OBJVAR))
        {
            num++;
        }
        if (hasObjVar(player, SCOUT_QUEST_OBJVAR))
        {
            num++;
        }
        if (hasObjVar(player, ARTISAN_QUEST_OBJVAR))
        {
            num++;
        }
        if (hasObjVar(player, MEDIC_QUEST_OBJVAR))
        {
            num++;
        }
        if (hasObjVar(player, ENTERTAINER_QUEST_OBJVAR))
        {
            num++;
        }
        return num;
    }
}
