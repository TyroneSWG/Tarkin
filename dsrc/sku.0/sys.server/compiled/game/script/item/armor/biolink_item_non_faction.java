package script.item.armor;

import script.*;
import script.library.combat;
import script.library.sui;
import script.library.utils;

public class biolink_item_non_faction extends script.base_script
{
    public biolink_item_non_faction()
    {
    }
    public static final String VAR_TEMP_BASE = "biotemp";
    public static final string_id SID_ITEM_BIO_LINKED = new string_id("base_player", "item_bio_linked");
    public static final string_id SID_MUST_EQUIP_FROM_INVENTORY = new string_id("base_player", "must_equip_from_inventory");
    public static final string_id SID_MUST_BIO_LINK_FROM_INVENTORY = new string_id("base_player", "must_bio_link_from_inventory");
    public static final string_id SID_BIO_LINK = new string_id("ui_radial", "bio_link");
    public static final string_id SID_BIO_LINK_MSG_PROMPT = new string_id("sui", "bio_link_item_prompt");
    public static final string_id SID_BIO_LINK_MSG_TITLE = new string_id("sui", "bio_link_item_title");
    public static final string_id SID_SPECIES_CANNOT_EQUIP = new string_id("base_player", "species_cannot_equip");
    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id biolink = getBioLink(self);
        if (biolink == null || biolink == utils.OBJ_ID_BIO_LINK_PENDING)
        {
            setBioLink(self, null);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        obj_id biolink = getBioLink(self);
        if (biolink == null)
        {
            setBioLink(self, null);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAboutToBeTransferred(obj_id self, obj_id destContainer, obj_id transferer) throws InterruptedException
    {
        obj_id biolink = getBioLink(self);
        if (biolink == null)
        {
            setBioLink(self, null);
            biolink = utils.OBJ_ID_BIO_LINK_PENDING;
        }
        if (biolink == utils.OBJ_ID_BIO_LINK_PENDING && (isPlayer(destContainer) || isAPlayerAppearanceInventoryContainer(destContainer)))
        {
            obj_id containerPlayer = utils.getContainingPlayer(self);
            obj_id appearanceInv = getAppearanceInventory(containerPlayer);
            if (containerPlayer != destContainer && destContainer != appearanceInv)
            {
                sendSystemMessage(containerPlayer, SID_MUST_EQUIP_FROM_INVENTORY);
                return SCRIPT_OVERRIDE;
            }
            obj_id player = null;
            if (containerPlayer == destContainer)
            {
                player = containerPlayer;
            }
            else 
            {
                player = getContainedBy(destContainer);
            }
            utils.setScriptVar(self, "destContainer", destContainer);
            int pid = sui.msgbox(self, player, "@" + SID_BIO_LINK_MSG_PROMPT, sui.YES_NO, "@" + SID_BIO_LINK_MSG_TITLE, "handleEquipBioLinkSui");
            return SCRIPT_OVERRIDE;
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info item) throws InterruptedException
    {
        obj_id biolink = getBioLink(self);
        if (biolink == utils.OBJ_ID_BIO_LINK_PENDING)
        {
            if (isIdValid(player) && utils.getContainingPlayer(self) == player)
            {
                item.addRootMenu(menu_info_types.BIO_LINK, SID_BIO_LINK);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.BIO_LINK)
        {
            obj_id biolink = getBioLink(self);
            if (biolink == utils.OBJ_ID_BIO_LINK_PENDING)
            {
                if (isIdValid(player) && utils.getContainingPlayer(self) == player)
                {
                    _bioLinkItem(self, player, false, true);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int handleEquipBioLinkSui(obj_id self, dictionary params) throws InterruptedException
    {
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        obj_id player = sui.getPlayerId(params);
        _bioLinkItem(self, player, true, true);
        return SCRIPT_CONTINUE;
    }
    public boolean _bioLinkItem(obj_id self, obj_id player, boolean equipAfterLink, boolean queryPlayer) throws InterruptedException
    {
        if (!isIdValid(self) || !isIdValid(player))
        {
            CustomerServiceLog("NonFaction", "WARNING: biolink_item._bioLinkItem called with invalid player (%TU) or item (" + self + ")", player);
            return false;
        }
        obj_id biolink = getBioLink(self);
        if (biolink != utils.OBJ_ID_BIO_LINK_PENDING)
        {
            CustomerServiceLog("NonFaction", "WARNING: Player %TU trying to bio-link item " + self + " that has link id " + biolink, player);
            return false;
        }
        if (utils.getContainingPlayer(self) != player)
        {
            sendSystemMessage(player, SID_MUST_BIO_LINK_FROM_INVENTORY);
            return false;
        }
        String template = getTemplateName(self);
        if ((template.startsWith("object/tangible/wearables/")) && (!canEquipWearable(player, self)))
        {
            sendSystemMessage(player, SID_SPECIES_CANNOT_EQUIP);
            return false;
        }
        if (isWeapon(self))
        {
            if (!combat.hasCertification(player, self))
            {
                sendSystemMessage(player, SID_SPECIES_CANNOT_EQUIP);
                return false;
            }
        }
        setBioLink(self, player);
        prose_package pp = new prose_package();
        pp.stringId = SID_ITEM_BIO_LINKED;
        sendSystemMessageProse(player, pp);
        sendDirtyObjectMenuNotification(self);
        obj_id container = player;
        if (utils.hasScriptVar(self, "destContainer"))
        {
            container = utils.getObjIdScriptVar(self, "destContainer");
        }
        if (equipAfterLink)
        {
            equip(self, container);
        }
        return true;
    }
}
