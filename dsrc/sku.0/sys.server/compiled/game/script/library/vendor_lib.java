package script.library;

import script.dictionary;
import script.location;
import script.obj_id;
import script.string_id;

import java.util.Vector;

public class vendor_lib extends script.base_script
{
    public vendor_lib()
    {
    }
    public static final String VAR_MAINTENANCE_RATE = "vendor.maintanence.rate";
    public static final String VAR_DECAY_RATE = "vendor.maintanence.decay";
    public static final String VAR_LAST_MAINTANENCE = "vendor.last_maintanence";
    public static final String VAR_CONDITION = "vendor.condition";
    public static final String VAR_MAX_CONDITION = "vendor.max_condition";
    public static final String VAR_PACKUP_VERSION = "vendor.packup_version";
    public static final String SPECIAL_VENDOR_IDENTIFIER = "vendor.special_vendor";
    public static final int MAINTENANCE_HEARTBEAT = 3600;
    public static final int BASE_MAINT_RATE = 1;
    public static final int BASE_DECAY_RATE = 2;
    public static final boolean LOGGING_ON = true;
    public static final String LOGGING_CATEGORY = "greeter";
    public static final String STATIC_ITEM_DEED_NAME = "static_item_deed_name";
    public static final String OWNER_OBJVAR = "object.owner";
    public static final String CHILD_GREETER_NONVENDOR_ID_OBJVAR = "object.child_greeter_nonvendor_objid";
    public static final String CNTRLR_GREETER_NONVENDOR_ID_OBJVAR = "object.terminal_greeter_nonvendor_objid";
    public static final String LASTKNOWN_GREETER_TERMINAL_LOC = "object.terminal_last_location";
    public static final String NONVENDOR_VENDOR_SCRIPT = "terminal.nonvendor";
    public static final String NONVENDOR_VAR_PREFIX = "nonvendor";
    public static final String NONVENDOR_TYPE_PREFIX = "nonvendor_";
    public static final String NONTRADER_NONVENDOR_TYPE = NONVENDOR_VAR_PREFIX + ".give_nontrader";
    public static final String NONVENDOR_CREATURE_TYPE_SCRVAR = NONVENDOR_VAR_PREFIX + ".creature_type";
    public static final String NONVENDOR_NAMES_SCRVAR = NONVENDOR_VAR_PREFIX + ".unsplit_nonvendor_names";
    public static final String NONVENDOR_STRING_ID_SCRVAR = NONVENDOR_VAR_PREFIX + ".unsplit_nonvendor_string_ids";
    public static final String NONVENDOR_CREATURE_NAME_SCRVAR = NONVENDOR_VAR_PREFIX + ".selection_creature_name";
    public static final String NONVENDOR_CREATURE_TEMPLATE_SCRVAR = NONVENDOR_VAR_PREFIX + ".selection_nonvendor_template";
    public static final String NONVENDOR_CREATURE_TEMPLATE_LIST = NONVENDOR_VAR_PREFIX + ".selection_nonvendor_template_list";
    public static final String NONVENDOR_CREATURENAME_LIST_SCRVAR = NONVENDOR_VAR_PREFIX + ".selection_creature_name_list";
    public static final String NONVENDOR_SELECTION_SCRVAR = NONVENDOR_VAR_PREFIX + ".selection_string_id";
    public static final String NONVENDOR_CUSTOM_NAME_SCRVAR = NONVENDOR_VAR_PREFIX + ".creature_custom_name";
    public static final String NONVENDOR_APPEARANCE_LIST = NONVENDOR_VAR_PREFIX + ".appearance_list";
    public static final String TCG_OBJVAR_PREFIX = "tcg";
    public static final String GREETER_OBJVAR_TREE = TCG_OBJVAR_PREFIX + ".greeter";
    public static final String GREETER_TYPE_PREFIX = "greeter_";
    public static final String GREETER_VAR_PREFIX = "greeter_prefix";
    public static final String CREATING_GREETER = GREETER_VAR_PREFIX + ".creatingGreeter";
    public static final String GREETER_NOT_INIT_OBJVAR = GREETER_VAR_PREFIX + ".greeter_not_initialized";
    public static final String GREETER_INIT_OBJVAR = GREETER_VAR_PREFIX + ".greeter_initialized";
    public static final String GREETER_OWNER_OBJVAR = GREETER_VAR_PREFIX + ".greeter_owner";
    public static final String GREETER_TYPE_OBJVAR = GREETER_VAR_PREFIX + ".greeter_type";
    public static final String GREETER_ACTIVE_OBJVAR = GREETER_VAR_PREFIX + ".greeter_active";
    public static final String GREETER_IS_ACTIVATED_OBJVAR = GREETER_VAR_PREFIX + ".greeter_currently_activated";
    public static final String GREETER_ANIMATES_OBJVAR = GREETER_VAR_PREFIX + ".greeter_animates";
    public static final String GREETER_ANIMATING_OBJVAR = GREETER_VAR_PREFIX + ".greeter_currently_animating";
    public static final String GREETER_VOICES_OBJVAR = GREETER_VAR_PREFIX + ".greeter_voices";
    public static final String GREETER_VOICING_OBJVAR = GREETER_VAR_PREFIX + ".greeter_currently_voicing";
    public static final String GREETER_SOUNDING_OBJVAR = GREETER_VAR_PREFIX + ".greeter_currently_sounding";
    public static final String GREETER_SOUNDS_OBJVAR = GREETER_VAR_PREFIX + ".greeter_sounds";
    public static final String GREETER_MOODS_OBJVAR = GREETER_VAR_PREFIX + ".greeter_moods";
    public static final String GREETER_EFFECT_OBJVAR = GREETER_VAR_PREFIX + ".greeter_effects";
    public static final String GREETER_HAS_EFFECT_OBJVAR = GREETER_VAR_PREFIX + ".greeter_has_effect";
    public static final String GREETER_STATEMENT_OBJVAR = GREETER_VAR_PREFIX + ".greeter_statement";
    public static final String GREETER_HAS_STATEMENT_OBJVAR = GREETER_VAR_PREFIX + ".greeter_has_statement";
    public static final String GREETER_HAS_COLOR_OBJVAR = GREETER_VAR_PREFIX + ".greeter_has_color";
    public static final String GREETER_SOUNDS_VO_MENU_OBJVAR = GREETER_VAR_PREFIX + ".greeter_sound_vo_menu_selection";
    public static final String CREATURE_TYPE = GREETER_VAR_PREFIX + ".creature_type";
    public static final String CREATURE_LAST_CREATED = GREETER_VAR_PREFIX + ".creature_last_created";
    public static final String GREETER_DATA_OBTAINED = GREETER_VAR_PREFIX + ".greeter_data_obtained";
    public static final String GREETER_TYPE = GREETER_VAR_PREFIX + ".greeter_type";
    public static final String GREETER_CAN_DRESS_OBJVAR = GREETER_VAR_PREFIX + ".greeter_can_be_dressed";
    public static final String GREETER_TYPE_NICHE = GREETER_VAR_PREFIX + ".greeter_niche";
    public static final String GREETER_TYPE_HAS_FEMALE_OBJVAR = GREETER_VAR_PREFIX + ".greeter_has_female";
    public static final String GREETER_TYPE_SPEAKBASIC_OBJVAR = GREETER_VAR_PREFIX + ".greeter_speaks_basic";
    public static final String GREETER_LOCATION_OBJVAR = GREETER_VAR_PREFIX + ".greeter_location";
    public static final String GREETER_ACTUAL_ANIMATION = GREETER_VAR_PREFIX + ".greeter_actual_animation";
    public static final String GREETER_ACTUAL_VOICE = GREETER_VAR_PREFIX + ".greeter_actual_voice";
    public static final String GREETER_ACTUAL_MOOD = GREETER_VAR_PREFIX + ".greeter_actual_mood";
    public static final String GREETER_ACTUAL_SOUND = GREETER_VAR_PREFIX + ".greeter_actual_sound";
    public static final String GREETER_ACTUAL_EFFECT = GREETER_VAR_PREFIX + ".greeter_actual_effect";
    public static final String GREETER_ACTUAL_STATEMENT = GREETER_VAR_PREFIX + ".greeter_actual_statement";
    public static final String GREETER_ACTUAL_MOOD_STRING = GREETER_VAR_PREFIX + ".greeter_mood_string";
    public static final String GREETER_ACTUAL_ANIMATION_STRING = GREETER_VAR_PREFIX + ".greeter_anim_string";
    public static final String GREETER_CURRENTLY_RANDOMIZED_GREET = GREETER_VAR_PREFIX + ".greeter_is_randomized_greeting";
    public static final String GREETER_RANDOM_TEST_FIRE = GREETER_VAR_PREFIX + ".greeter_random_test_greeting";
    public static final String GREETER_CREATURE_TYPE_OBJVAR = GREETER_VAR_PREFIX + ".greeter_verified_creature_type";
    public static final String GREETER_CREATURE_NAME_OBJVAR = GREETER_VAR_PREFIX + ".selection_creature_name";
    public static final String GREETER_NAMES_OBJVAR = GREETER_VAR_PREFIX + ".unsplit_greeter_names";
    public static final String GREETER_STRING_ID_OBJVAR = GREETER_VAR_PREFIX + ".unsplit_greeter_string_ids";
    public static final String GREETER_CREATURENAME_LIST_OBJVAR = GREETER_VAR_PREFIX + ".selection_creature_name_list";
    public static final String GREETER_SELECTION_OBJVAR = GREETER_VAR_PREFIX + ".selection_string_id";
    public static final String GREETER_CUSTOM_NAME_OBJVAR = GREETER_VAR_PREFIX + ".creature_custom_name";
    public static final String GREETER_APPEARANCE_LIST = GREETER_VAR_PREFIX + ".appearance_list";
    public static final String GREETER_COLOR_OBJVAR = GREETER_VAR_PREFIX + ".color_setting";
    public static final String GREETER_ALREADY_BARKED_SCRVAR = GREETER_VAR_PREFIX + ".greeter_already_barked";
    public static final String GREETER_PLAYTERCOLOR_SCRVAR = GREETER_VAR_PREFIX + ".greeter_color_setting";
    public static final String GREETER_COLOR = GREETER_VAR_PREFIX + ".greeter_color_str_array";
    public static final String GREETER_CONVERSE = GREETER_VAR_PREFIX + ".greeter_conversable";
    public static final String GREETER_HAS_NICHE_OBJVAR = GREETER_VAR_PREFIX + ".greeter_niche";
    public static final String GREETER_IS_DRESSABLE = GREETER_VAR_PREFIX + ".greeter_is_dressable";
    public static final String GREETER_SPEAKS_BASIC = GREETER_VAR_PREFIX + ".greeter_speaks_basic";
    public static final String GREETER_HAS_ANIMS_OBJVAR = GREETER_VAR_PREFIX + ".greeter_has_anims";
    public static final String GREETER_HAS_VO_OBJVAR = GREETER_VAR_PREFIX + ".greeter_has_vo";
    public static final String GREETER_HAS_SOUND_OBJVAR = GREETER_VAR_PREFIX + ".greeter_has_sound";
    public static final String GREETER_HAS_MOOD_OBJVAR = GREETER_VAR_PREFIX + ".greeter_has_mood";
    public static final String GREETER_HAS_CHAT_OBJVAR = GREETER_VAR_PREFIX + ".greeter_has_chat";
    public static final String GREETER_SCRIPT = "terminal.greeter";
    public static final String GREETER_DEED_OBJVAR = "greeter.give_greeter";
    public static final String GREETER_OWNER_OID_OBJVAR = GREETER_VAR_PREFIX + ".greeter_owner";
    public static final String TBL_VENDOR_ROOT_TBL_DIR = "datatables/vendor/";
    public static final String TBL_GREETER_ANIMS = "datatables/vendor/vendor_areabark_anims.iff";
    public static final String TBL_GREETER_MOODS = "datatables/vendor/vendor_areabark_moods.iff";
    public static final String TBL_GREETER_SAY_CHAT = "datatables/vendor/greeter_say_chat.iff";
    public static final String TBL_GREETER_SOUND_VOICE_EFFECT = "datatables/vendor/greeter_sound_voice_effect.iff";
    public static final String TBL_GREETER_NONVENDOR_TABLE = "datatables/vendor/greeter_nonvendor_data.iff";
    public static final String COL_CREATURE_TYPE = "creature_type";
    public static final String COL_GREETER_CREATURE_NAME = "greeter_creature_name";
    public static final String COL_NONVENDOR_CREATURE_NAME = "nonvendor_creature_name";
    public static final String COL_NONVENDOR_CREATURE_TEMPLATE = "nonvendor_template_name";
    public static final String COL_NICHE = "greeter_niche";
    public static final String COL_GRTR_STRING_ID = "greeter_appearance_string_id";
    public static final String COL_NONVNDR_STRING_ID = "nonvendor_appearance_string_id";
    public static final String COL_DRESSED = "greeter_can_be_dressed";
    public static final String COL_SPEAK_BASIC = "greeter_speaks_basic";
    public static final String COL_SAY_CHAT = "greeter_say_chat";
    public static final String COL_ANIMATES = "greeter_animates";
    public static final String COL_VO = "greeter_voice_over";
    public static final String COL_SOUNDS = "greeter_sounds";
    public static final String COL_MOODS = "greeter_has_moods";
    public static final String COL_COLOR = "greeter_can_color";
    public static final int VAR_TRUE = 1;
    public static final int VAR_FALSE = 0;
    public static final string_id SID_OBSCENE = new string_id("player_structure", "obscene");
    public static final string_id SID_INVALID_GREETER_TABLE_DATA = new string_id("player_vendor", "greeter_data_table_corrupt");
    public static final string_id SID_SYS_CREATE_GREETER_SUCCESS = new string_id("player_structure", "create_greeter_success");
    public static final string_id SID_INVENTORY_FULL_GENERIC = new string_id("player_structure", "inventory_full_generic");
    public static final string_id SID_GREETER_PACK_UP_SUCCESS = new string_id("player_structure", "greeter_packup_success");
    public static final string_id SID_GREETER_PACK_UP_FAILURE = new string_id("player_structure", "greeter_packup_failure");
    public static final string_id SID_MUST_BE_IN_VALID_LOCATION = new string_id("player_structure", "must_be_valid_location");
    public static boolean payMaintenance(obj_id player, obj_id vendor, int amt) throws InterruptedException
    {
        if (player == null || player == obj_id.NULL_ID)
        {
            return false;
        }
        if (vendor == null || vendor == obj_id.NULL_ID)
        {
            return false;
        }
        if (amt < 1 || amt > 100000)
        {
            return false;
        }
        dictionary params = new dictionary();
        params.put(money.DICT_MSG_PAYER, "1");
        return money.requestPayment(player, vendor, amt, "handleVendorPayment", params, true);
    }
    public static boolean withdrawMaintenance(obj_id player, obj_id vendor, int amt) throws InterruptedException
    {
        if (player == null || player == obj_id.NULL_ID)
        {
            return false;
        }
        if (vendor == null || vendor == obj_id.NULL_ID)
        {
            return false;
        }
        if (amt < 1 || amt > 100000)
        {
            return false;
        }
        dictionary d = new dictionary();
        d.put("player", player);
        d.put("amount", amt);
        return transferBankCreditsTo(vendor, player, amt, "msgVendorWithdrawSuccess", "msgVendorWithdrawFail", d);
    }
    public static int decrementMaintenancePool(obj_id vendor, int amt) throws InterruptedException
    {
        if (vendor == null || vendor == obj_id.NULL_ID)
        {
            return -1;
        }
        if (amt < 1)
        {
            return -1;
        }
        int pool = getBankBalance(vendor);
        if (amt > pool)
        {
            return -2;
        }
        if (money.bankTo(vendor, money.ACCT_VENDOR, amt))
        {
            CustomerServiceLog("vendor", "Vendor decrement maintenance pool.  Vendor " + vendor + " Amount: " + amt);
            return pool - amt;
        }
        else 
        {
            return -1;
        }
    }
    public static int damageVendor(obj_id vendor, int amt) throws InterruptedException
    {
        if (vendor == null || vendor == obj_id.NULL_ID)
        {
            return -1;
        }
        if (amt < 1)
        {
            return -1;
        }
        int condition = getVendorCondition(vendor);
        condition = condition - amt;
        CustomerServiceLog("vendor", "VENDOR DAMAGE DUE TO NO EMPTY MAINTENANCE POOL!  Vendor " + vendor + " Damage: " + amt);
        if (condition < 1)
        {
            CustomerServiceLog("vendor", "VENDOR CONDITION ZERO! Disabling vendor due to nonpayment of maintenance. Vendor " + vendor);
            CustomerServiceLog("vendor", "Note: Vendor disable code is not finished.  Vendor will continue to operate normally. Vendor " + vendor);
            condition = 0;
            obj_id inv = utils.getInventoryContainer(vendor);
            if (inv == null)
            {
                inv = vendor;
            }
            setObjVar(inv, "vendor_deactivated", 1);
        }
        else 
        {
            setObjVar(vendor, VAR_CONDITION, condition);
        }
        return condition;
    }
    public static int repairVendor(obj_id vendor, int amt) throws InterruptedException
    {
        if (vendor == null || vendor == obj_id.NULL_ID)
        {
            return -1;
        }
        if (amt < 1)
        {
            return -1;
        }
        int condition = getVendorCondition(vendor);
        int max_condition = getMaxCondition(vendor);
        condition = condition + amt;
        if (condition > max_condition)
        {
            condition = max_condition;
        }
        setObjVar(vendor, VAR_CONDITION, condition);
        CustomerServiceLog("vendor", "Vendor repair damage.  Vendor " + vendor + " Amount: " + amt);
        if (condition == max_condition)
        {
            obj_id inv = utils.getInventoryContainer(vendor);
            if (inv == null)
            {
                inv = vendor;
            }
            removeObjVar(inv, "vendor_deactivated");
        }
        return condition;
    }
    public static int getMaintenanceRate(obj_id vendor) throws InterruptedException
    {
        return getIntObjVar(vendor, VAR_MAINTENANCE_RATE);
    }
    public static int getMaintenancePool(obj_id vendor) throws InterruptedException
    {
        return getBankBalance(vendor);
    }
    public static int getDecayRate(obj_id vendor) throws InterruptedException
    {
        return getIntObjVar(vendor, VAR_DECAY_RATE);
    }
    public static int getVendorCondition(obj_id vendor) throws InterruptedException
    {
        return getIntObjVar(vendor, VAR_CONDITION);
    }
    public static int getMaxCondition(obj_id vendor) throws InterruptedException
    {
        return getIntObjVar(vendor, VAR_MAX_CONDITION);
    }
    public static void finalizePackUp(obj_id player, obj_id vendor, obj_id packer, boolean isAbandoned) throws InterruptedException
    {
        obj_id vcd;
        obj_id datapad;
        final boolean isLoadedAndAuthoritative = player.isLoaded() && player.isAuthoritative();
        CustomerServiceLog("vendorpackup", "Player " + getPlayerName(packer) + " (" + packer + ") is attempting to pack vendor (" + vendor + ",abandoned=" + isAbandoned + ") owned by player " + getPlayerName(player) + " (" + player + ")");
        if (isLoadedAndAuthoritative)
        {
            datapad = utils.getPlayerDatapad(player);
        }
        else 
        {
            datapad = utils.getPlayerDatapad(packer);
        }
        if (!isIdValid(datapad))
        {
            return;
        }
        vcd = createObjectOverloaded("object/intangible/vendor/generic_vendor_control_device.iff", datapad);
        if (!isIdValid(vcd))
        {
            return;
        }
        if (isAbandoned)
        {
            setObjVar(vcd, "abandoned.packer", packer);
            setObjVar(vcd, "abandoned.owner", player);
            setObjVar(vcd, "abandoned.vendor", vendor);
        }
        attachScript(vcd, "vendor.vendor_control_device");
        String vendorName = getName(vendor);
        setObjVar(vendor, VAR_PACKUP_VERSION, 1);
        updateVendorStatus(vendor, VENDOR_STATUS_FLAG_PACKED);
        if (hasObjVar(vendor, "vendor.map_registered"))
        {
            removeObjVar(vendor, "vendor.map_registered");
            removePlanetaryMapLocation(vendor);
        }
        setName(vcd, vendorName);
        putIn(vendor, vcd, player);
        if (!isLoadedAndAuthoritative)
        {
            final int maxDepth = isNpcVendor(vendor) ? 1 : 0;
            moveToOfflinePlayerDatapadAndUnload(vcd, player, maxDepth + 1);
            fixLoadWith(vendor, player, maxDepth);
        }
        CustomerServiceLog("vendorpackup", "Player " + getPlayerName(packer) + " (" + packer + ") packed vendor (" + vendor + ",abandoned=" + isAbandoned + ") owned by player " + getPlayerName(player) + " (" + player + ") into device (" + vcd + ")");
    }
    public static boolean isVendorPackUpEnabled() throws InterruptedException
    {
        return utils.checkConfigFlag("GameServer", "allowPlayersToPackVendors");
    }
    public static obj_id getAuctionContainer(obj_id vendor) throws InterruptedException
    {
        obj_id container = utils.getInventoryContainer(vendor);
        if (container == null)
        {
            container = vendor;
        }
        return container;
    }
    public static boolean isNpcVendor(obj_id vendor) throws InterruptedException
    {
        return getAuctionContainer(vendor) != vendor;
    }
    public static String[] getAllGreeterDatatableColumnNames(obj_id object, String datatablePath) throws InterruptedException
    {
        blog("vendor_lib.getAllGreeterDatatableColumnNames:init");
        if (!isValidId(object) || !exists(object))
        {
            return null;
        }
        else if (datatablePath == null || datatablePath.equals(""))
        {
            return null;
        }
        else if (!datatablePath.startsWith(TBL_VENDOR_ROOT_TBL_DIR))
        {
            return null;
        }
        String[] preParsedColList = dataTableGetColumnNames(datatablePath);
        if (preParsedColList == null)
        {
            return null;
        }
        int preParsedColListLength = preParsedColList.length;
        if (preParsedColListLength < 0)
        {
            return null;
        }
        blog("vendor_lib.getAllGreeterDatatableColumnNames: prevalidation completed");
        Vector greeterColNames = new Vector();
        greeterColNames.setSize(0);
        for (String aPreParsedColList : preParsedColList) {
            if (aPreParsedColList.startsWith("greeter")) {
                greeterColNames = utils.addElement(greeterColNames, aPreParsedColList);
            }
        }
        if (greeterColNames.size() <= 0)
        {
            return null;
        }
        blog("vendor_lib.getAllGreeterDatatableColumnNames: greeter col name list being returned.");
        String[] _greeterColNames = new String[0];
        _greeterColNames = new String[greeterColNames.size()];
        greeterColNames.toArray(_greeterColNames);
        return _greeterColNames;
    }
    public static boolean buildNpcInPlayerStructure(obj_id controller, obj_id player, String npcType, boolean newGreeter) throws InterruptedException
    {
        if (!isValidId(controller) || !exists(controller))
        {
            blog("vendor_lib.buildNpcInPlayerStructure: Unable to create a nonvendor: deed invalid.");
            return false;
        }
        if (!isValidId(player) || !exists(player))
        {
            blog("vendor_lib.buildNpcInPlayerStructure: Unable to create a nonvendor: player invalid.");
            return false;
        }
        if (npcType == null || npcType.equals(""))
        {
            blog("vendor_lib.buildNpcInPlayerStructure: Unable to get NPC TYPE to create.");
            return false;
        }
        return buildNpcInPlayerStructure(controller, player, npcType, null, newGreeter);
    }
    public static boolean buildNpcInPlayerStructure(obj_id controller, obj_id owner, String npcType, location where, boolean newGreeter) throws InterruptedException
    {
        if (!isValidId(controller) || !exists(controller))
        {
            blog("vendor_lib.buildNpcInPlayerStructure: Unable to create a nonvendor: deed invalid.");
            CustomerServiceLog("vendor", "vendor_lib.buildNpcInPlayerStructure: Greeter/Non-Vendor could not be created. Reason: Greeter Controller Invalid.");
            return false;
        }
        if (!isValidId(owner))
        {
            CustomerServiceLog("vendor", "vendor_lib.buildNpcInPlayerStructure: Greeter/Non-Vendor could not be created. Reason: Greeter Controller Owner OID: " + owner + " is invalid.");
            blog("vendor_lib.buildNpcInPlayerStructure: Unable to create a nonvendor: player invalid.");
            return false;
        }
        if (npcType == null || npcType.length() <= 0)
        {
            CustomerServiceLog("vendor", "vendor_lib.buildNpcInPlayerStructure: Greeter/Non-Vendor could not be created. Reason: Greeter/Non-Vendor type not found. Owner OID: " + owner + "  Controller OID: " + controller);
            blog("vendor_lib.buildNpcInPlayerStructure: Unable to create a nonvendor: npcType invalid.");
            return false;
        }
        if (!npcType.startsWith(NONVENDOR_VAR_PREFIX) && !npcType.startsWith(GREETER_VAR_PREFIX))
        {
            blog("vendor_lib.buildNpcInPlayerStructure: Unable to create a nonvendor: npcType could not be found.");
            blog("vendor_lib.buildNpcInPlayerStructure: npcType received: " + npcType + " npcType needed:" + NONVENDOR_TYPE_PREFIX);
            return false;
        }
        if (newGreeter && !validateNpcPlacementInStructure(owner))
        {
            sendSystemMessage(owner, SID_MUST_BE_IN_VALID_LOCATION);
            blog("vendor_lib.buildNpcInPlayerStructure: Unable to create a nonvendor: validateNpcPlacementInStructure said NO.");
            return false;
        }
        location loc;
        if (where == null)
        {
            loc = getLocation(owner);
        }
        else 
        {
            loc = (location)where.clone();
        }
        if (loc == null)
        {
            blog("vendor_lib.buildNpcInPlayerStructure: Location could not be found");
            return false;
        }
        String itemName = getStaticItemName(controller);
        if (itemName == null || itemName.equals(""))
        {
            blog("vendor_lib.buildNpcInPlayerStructure: Static Item Name not found");
            return false;
        }
        if (npcType.startsWith(NONVENDOR_VAR_PREFIX))
        {
            blog("vendor_lib.buildNpcInPlayerStructure: I am a nonvendor vendor");
            String nonVendorSpawn = utils.getStringScriptVar(owner, NONVENDOR_CREATURE_TEMPLATE_SCRVAR);
            if (nonVendorSpawn == null || nonVendorSpawn.equals(""))
            {
                blog("vendor_lib.buildNpcInPlayerStructure: Creature Name not found");
                return false;
            }
            obj_id nonVendorCreature = createObject(nonVendorSpawn, loc);
            blog("vendor_lib.buildNpcInPlayerStructure: created mob staticObject: " + nonVendorCreature);
            String nonVendorName = utils.getStringScriptVar(owner, NONVENDOR_CUSTOM_NAME_SCRVAR);
            blog("vendor_lib.buildNpcInPlayerStructure: nonVendorName: " + nonVendorName);
            if (nonVendorName != null && !nonVendorName.equals(""))
            {
                setName(nonVendorCreature, nonVendorName);
            }
            setObjVar(nonVendorCreature, STATIC_ITEM_DEED_NAME, itemName);
            setObjVar(nonVendorCreature, GREETER_OWNER_OBJVAR, owner);
            persistObject(nonVendorCreature);
            setOwner(nonVendorCreature, owner);
            attachScript(nonVendorCreature, NONVENDOR_VENDOR_SCRIPT);
            CustomerServiceLog("tcg", "TCG NonVendor: " + nonVendorCreature + " of type: " + nonVendorSpawn + " was placed at location: " + loc + " by owner: " + owner + ".");
            return true;
        }
        blog("vendor_lib.buildNpcInPlayerStructure: I am a GREETER");
        String greeterSelection = utils.getStringObjVar(controller, vendor_lib.GREETER_CREATURE_NAME_OBJVAR);
        if (greeterSelection == null || greeterSelection.equals(""))
        {
            blog("vendor_lib.buildNpcInPlayerStructure: Greeter Spawn String Not Found");
            return false;
        }
        String customName = utils.getStringObjVar(controller, vendor_lib.GREETER_CUSTOM_NAME_OBJVAR);
        if (customName == null || customName.equals(""))
        {
            blog("vendor_lib.buildNpcInPlayerStructure: Greeter Custom Name Not Found");
            return false;
        }
        String creatureType = utils.getStringObjVar(controller, vendor_lib.GREETER_CREATURE_TYPE_OBJVAR);
        if (creatureType == null || creatureType.equals(""))
        {
            blog("vendor_lib.buildNpcInPlayerStructure: Creature Type Not Found");
            return false;
        }
        blog("vendor_lib.buildNpcInPlayerStructure: primary greeter build validation complete. About to spawn: " + greeterSelection);
        obj_id greeterObj = create.staticObject(greeterSelection, loc);
        if (!isValidId(greeterObj) || !exists(greeterObj))
        {
            blog("vendor_lib.buildNpcInPlayerStructure: Greeter Creature Could NOT BE CREATED");
            return false;
        }
        String greeterName = "Greeter: " + customName;
        setName(greeterObj, greeterName);
        setObjVar(controller, vendor_lib.CHILD_GREETER_NONVENDOR_ID_OBJVAR, greeterObj);
        setObjVar(greeterObj, vendor_lib.CNTRLR_GREETER_NONVENDOR_ID_OBJVAR, controller);
        setObjVar(controller, vendor_lib.GREETER_LOCATION_OBJVAR, getLocation(greeterObj));
        setInvulnerable(greeterObj, true);
        attachScript(greeterObj, GREETER_SCRIPT);
        setObjVar(greeterObj, STATIC_ITEM_DEED_NAME, itemName);
        ai_lib.setDefaultCalmBehavior(greeterObj, ai_lib.BEHAVIOR_SENTINEL);
        setOwner(greeterObj, owner);
        setObjVar(greeterObj, vendor_lib.GREETER_OWNER_OBJVAR, owner);
        setObjVar(greeterObj, vendor_lib.CREATURE_LAST_CREATED, getCalendarTimeStringLocal(getCalendarTime()));
        setObjVar(controller, vendor_lib.CREATURE_TYPE, creatureType);
        blog("vendor_lib.buildGreeter: Done setting up Greeter");
        setObjVar(controller, "unmoveable", 1);
        CustomerServiceLog("tcg", "TCG Greeter: " + greeterObj + " of type: " + greeterSelection + " was placed at location: " + loc + " by owner: " + owner + ".");
        return true;
    }
    public static boolean validateNpcPlacementInStructure(obj_id player) throws InterruptedException
    {
        if (!isValidId(player) || !exists(player))
        {
            return false;
        }
        obj_id structure = getTopMostContainer(player);
        if (!isValidId(structure) || !exists(structure))
        {
            return false;
        }
        if (!player_structure.isPlayerStructure(structure) || player_structure.isCivic(structure))
        {
            return false;
        }
        if (player_structure.isOwner(structure, player))
        {
            return true;
        }
        return player_structure.isAdmin(structure, player);
    }
    public static boolean validateNonVendorInStructure(obj_id nonVendor) throws InterruptedException
    {
        if (!isValidId(nonVendor) || !exists(nonVendor))
        {
            return false;
        }
        obj_id structure = getTopMostContainer(nonVendor);
        if (!isValidId(structure) || !exists(structure))
        {
            return false;
        }
        return !(!player_structure.isPlayerStructure(structure) || player_structure.isCivic(structure));
    }
    public static String getGreeterNonVendorCreatureType(obj_id object, String greeterOrNonVendorType) throws InterruptedException
    {
        blog("vendor_lib.getGreeterNonVendorCreatureType init");
        if (!isValidId(object) || !exists(object))
        {
            return null;
        }
        else if (greeterOrNonVendorType == null || greeterOrNonVendorType.equals(""))
        {
            return null;
        }
        blog("vendor_lib.getGreeterNonVendorCreatureType validation complete");
        String[] creatureTypes = dataTableGetStringColumnNoDefaults(TBL_GREETER_NONVENDOR_TABLE, COL_CREATURE_TYPE);
        if (creatureTypes == null)
        {
            if (isPlayer(object))
            {
                sendSystemMessage(object, SID_INVALID_GREETER_TABLE_DATA);
            }
            return null;
        }
        blog("vendor_lib.getGreeterNonVendorCreatureType creatureTypes received");
        for (int i = 0; i < creatureTypes.length; i++)
        {
            if (!greeterOrNonVendorType.contains(creatureTypes[i]))
            {
                blog("vendor_lib.getGreeterNonVendorCreatureType greeterOrNonVendorType: " + greeterOrNonVendorType + " " + creatureTypes[i]);
                continue;
            }
            blog("vendor_lib.getGreeterNonVendorCreatureType greeterOrNonVendorType FOUND: " + creatureTypes[i] + "row: " + i);
            return creatureTypes[i];
        }
        blog("vendor_lib.getGreeterNonVendorCreatureType greeterOrNonVendorType NOT found");
        return null;
    }
    public static boolean isSpecialVendor(obj_id object) throws InterruptedException
    {
        if (!isValidId(object) || !exists(object))
        {
            return false;
        }
        if (!hasObjVar(object, vendor_lib.SPECIAL_VENDOR_IDENTIFIER))
        {
            return false;
        }
        return getBooleanObjVar(object, vendor_lib.SPECIAL_VENDOR_IDENTIFIER);
    }
    public static obj_id setObjectOwner(obj_id controller) throws InterruptedException
    {
        if (!isValidId(controller) || !exists(controller))
        {
            return null;
        }
        obj_id owner = getOwner(controller);
        if (!isValidId(owner))
        {
            owner = utils.getContainingPlayer(controller);
            if (!isValidId(owner) && !exists(owner))
            {
                return null;
            }
            setOwner(controller, owner);
        }
        if (!hasObjVar(controller, vendor_lib.OWNER_OBJVAR))
        {
            setObjVar(controller, vendor_lib.OWNER_OBJVAR, owner);
        }
        return owner;
    }
    public static obj_id getObjectOwner(obj_id controller) throws InterruptedException
    {
        if (!isValidId(controller) || !exists(controller))
        {
            return null;
        }
        obj_id owner = getOwner(controller);
        if (!isValidId(owner))
        {
            owner = setObjectOwner(controller);
            if (!isValidId(owner))
            {
                return null;
            }
        }
        return owner;
    }
    public static boolean isControllerOrChildInValidLocation(obj_id controller) throws InterruptedException
    {
        if (!isValidId(controller) || !exists(controller))
        {
            return false;
        }
        obj_id structure = getTopMostContainer(controller);
        if (!isValidId(structure) || !exists(structure))
        {
            return false;
        }
        return player_structure.isPlayerStructure(structure);
    }
    public static boolean isObjectInSameCellAsController(obj_id controller, obj_id object) throws InterruptedException
    {
        if (!isValidId(controller) || !exists(controller))
        {
            return false;
        }
        if (!isValidId(object) || !exists(object))
        {
            return false;
        }
        obj_id controllerStructure = getTopMostContainer(controller);
        if (!isValidId(controllerStructure) || !exists(controllerStructure))
        {
            return false;
        }
        if (!controllerContainmentCheck(controller))
        {
            return false;
        }
        location controllerLoc = getLocation(controller);
        if (controllerLoc == null)
        {
            return false;
        }
        location objLocation = getLocation(object);
        if (objLocation == null)
        {
            return false;
        }
        return controllerLoc.cell == objLocation.cell;
    }
    public static boolean recreateObject(obj_id controller, obj_id player) throws InterruptedException
    {
        blog("vendor_lib.recreateObject init");
        if (!isValidId(controller) || !exists(controller))
        {
            return false;
        }
        if (!isValidId(player) || !exists(player))
        {
            return false;
        }
        blog("vendor_lib.recreateObject validation completed. Getting Controller Location");
        location where = getLocation(controller);
        if (where == null)
        {
            return false;
        }
        String npcType = "";
        if (hasObjVar(controller, NONVENDOR_VAR_PREFIX))
        {
            npcType = NONVENDOR_VAR_PREFIX;
        }
        else if (hasObjVar(controller, GREETER_VAR_PREFIX))
        {
            npcType = GREETER_VAR_PREFIX;
        }
        if (npcType.equals(""))
        {
            return false;
        }
        blog("vendor_lib.recreateObjectAtLocation placing greeter via buildNpcInPlayerStructure at CONTROLLER location");
        return buildNpcInPlayerStructure(controller, player, npcType, where, false);
    }
    public static boolean recreateObjectAtLocation(obj_id controller, obj_id owner, location loc) throws InterruptedException
    {
        blog("vendor_lib.recreateObjectAtLocation init");
        if (!isValidId(controller) || !exists(controller))
        {
            return false;
        }
        if (!isValidId(owner))
        {
            return false;
        }
        if (loc == null)
        {
            return false;
        }
        blog("vendor_lib.recreateObjectAtLocation Greeter Location Validated");
        blog("vendor_lib.recreateObjectAtLocation getting controller location for testing");
        location where = getLocation(controller);
        if (where == null)
        {
            CustomerServiceLog("vendor", "vendor_lib.recreateObjectAtLocation: GREETER CONTROLLER LOCATION INVALID: Controller: " + controller + ") Owner: " + getName(owner) + " (" + owner + ")");
            return false;
        }
        String npcType = "";
        if (hasObjVar(controller, NONVENDOR_VAR_PREFIX))
        {
            npcType = NONVENDOR_VAR_PREFIX;
        }
        else if (hasObjVar(controller, GREETER_VAR_PREFIX))
        {
            npcType = GREETER_VAR_PREFIX;
        }
        if (npcType.equals(""))
        {
            return false;
        }
        blog("vendor_lib.recreateObjectAtLocation placing greeter via buildNpcInPlayerStructure at greeter location");
        return buildNpcInPlayerStructure(controller, owner, npcType, loc, false);
    }
    public static boolean controllerContainmentCheck(obj_id controller) throws InterruptedException
    {
        if (!isValidId(controller) || !exists(controller))
        {
            return false;
        }
        if (utils.isNestedWithinAPlayer(controller))
        {
            return false;
        }
        obj_id building = getTopMostContainer(controller);
        if (!isGameObjectTypeOf(building, GOT_building_player) && (!isGameObjectTypeOf(building, GOT_ship_fighter) && !space_utils.isShipWithInterior(building)))
        {
            return false;
        }
        if (player_structure.isCivic(building))
        {
            return false;
        }
        location here = getLocation(controller);
        return getContainedBy(controller) == here.cell;
    }
    public static boolean removeObjectFromController(obj_id controller, obj_id greeter) throws InterruptedException
    {
        if (!isValidId(controller) || !exists(controller))
        {
            return false;
        }
        removeObjVar(controller, vendor_lib.CHILD_GREETER_NONVENDOR_ID_OBJVAR);
        removeObjVar(controller, vendor_lib.GREETER_IS_ACTIVATED_OBJVAR);
        removeObjVar(controller, vendor_lib.GREETER_ANIMATING_OBJVAR);
        removeObjVar(controller, vendor_lib.GREETER_VOICING_OBJVAR);
        removeObjVar(controller, vendor_lib.GREETER_SOUNDING_OBJVAR);
        removeObjVar(controller, vendor_lib.GREETER_HAS_MOOD_OBJVAR);
        removeObjVar(controller, vendor_lib.GREETER_HAS_STATEMENT_OBJVAR);
        removeObjVar(controller, vendor_lib.GREETER_CURRENTLY_RANDOMIZED_GREET);
        if (isValidId(greeter) && exists(greeter))
        {
            destroyObject(greeter);
        }
        removeObjVar(controller, "unmoveable");
        return true;
    }
    public static boolean updateGreeterFunctionality(obj_id greeter) throws InterruptedException
    {
        blog("vendor_lib.updateGreeterFunctionality init");
        if (!isValidId(greeter) || !exists(greeter))
        {
            return false;
        }
        obj_id controller = getObjIdObjVar(greeter, CNTRLR_GREETER_NONVENDOR_ID_OBJVAR);
        if (!isValidId(controller) || !exists(controller))
        {
            CustomerServiceLog("vendor", "vendor_lib.updateGreeterFunctionality: GREETER CONTROLLER INVALID: Greeter: " + getName(greeter) + " (" + greeter + ") GREETER UPDATE FAILED - NOTIFY DESIGN");
            return false;
        }
        if (!removeObjectFromController(controller, greeter))
        {
            CustomerServiceLog("vendor", "vendor_lib.updateGreeterFunctionality: GREETER NOT DESTROYED PROPERLY FOR UPDATE: Greeter: " + getName(greeter) + " (" + greeter + ") Owner: " + getOwner(controller));
            CustomerServiceLog("vendor", "vendor_lib.updateGreeterFunctionality: GREETER BEING MANUALLY DESTROYED: Greeter: " + getName(greeter) + " (" + greeter);
            destroyObject(greeter);
            return false;
        }
        return true;
    }
    public static boolean colorizeGreeterFromWidget(obj_id player, obj_id greeter, String params) throws InterruptedException
    {
        if (!isValidId(player) || !exists(player))
        {
            return false;
        }
        if (!isValidId(greeter) || !exists(greeter))
        {
            return false;
        }
        if (params == null || params.equals(""))
        {
            return false;
        }
        obj_id controller = getObjIdObjVar(greeter, vendor_lib.CNTRLR_GREETER_NONVENDOR_ID_OBJVAR);
        if (!isValidId(controller) || !exists(controller))
        {
            return false;
        }
        String[] colorArray = split(params, ' ');
        if (colorArray == null || colorArray.length <= 0)
        {
            return false;
        }
        setObjVar(controller, vendor_lib.GREETER_COLOR, colorArray);
        for (int i = 0; i < colorArray.length; i += 2)
        {
            if (colorArray[i] == null || colorArray[i].equals(""))
            {
                break;
            }
            hue.setColor(greeter, colorArray[i], utils.stringToInt(colorArray[i + 1]));
        }
        utils.removeScriptVar(player, vendor_lib.GREETER_PLAYTERCOLOR_SCRVAR);
        return true;
    }
    public static boolean colorizeGreeterFromController(obj_id controller, obj_id greeter) throws InterruptedException
    {
        if (!isValidId(controller) || !exists(controller))
        {
            return false;
        }
        if (!isValidId(greeter) || !exists(greeter))
        {
            return false;
        }
        String[] colorArray = getStringArrayObjVar(controller, vendor_lib.GREETER_COLOR);
        if (colorArray == null || colorArray.length <= 0)
        {
            return false;
        }
        for (int i = 0; i < colorArray.length; i += 2)
        {
            if (colorArray[i] == null || colorArray[i].equals(""))
            {
                break;
            }
            hue.setColor(greeter, colorArray[i], utils.stringToInt(colorArray[i + 1]));
        }
        return true;
    }
    public static boolean blog(String msg) throws InterruptedException
    {
        if (LOGGING_ON && msg != null && !msg.equals(""))
        {
            LOG(LOGGING_CATEGORY, msg);
        }
        return true;
    }
}
