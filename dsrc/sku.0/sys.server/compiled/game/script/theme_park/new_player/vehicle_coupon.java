package script.theme_park.new_player;

import script.library.*;
import script.*;

public class vehicle_coupon extends script.base_script
{
    public vehicle_coupon()
    {
    }
    public static final String STF = "new_player";
    public static final String VEHICLE_TYPE = "spdrbike";
    public static final string_id MENU_CALL = new string_id(STF, "vehicle_coupon_menu_call");
    public static final string_id NO_OWNER = new string_id(STF, "vehicle_coupon_no_owner");
    public static final string_id IS_NOT_OWNER = new string_id(STF, "vehicle_coupon_is_not_owner");
    public static final int USES = 5;
    public static final int MAX_HP = 1500;
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.VEHICLE_GENERATE, MENU_CALL);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (!hasObjVar(self, "owner"))
        {
            String custLogMsg = "New Player Rewards: %TU tried to use a vehicle rental device but this device is invalid - does not have an owner.";
            CustomerServiceLog("NEW_PLAYER_QUESTS", custLogMsg, player);
            sendSystemMessage(player, NO_OWNER);
            return SCRIPT_CONTINUE;
        }
        obj_id owner = getObjIdObjVar(self, "owner");
        if (player != owner)
        {
            sendSystemMessage(player, IS_NOT_OWNER);
            String custLogMsg = "New Player Rewards: %TU tried to use a vehicle rental device but isn't the owner. Rightful owner is %TT";
            CustomerServiceLog("NEW_PLAYER_QUESTS", custLogMsg, player, owner);
            return SCRIPT_CONTINUE;
        }
        if (ai_lib.aiIsDead(player) || ai_lib.isInCombat(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.VEHICLE_GENERATE)
        {
            if (pet_lib.wasInCombat(player))
            {
                return SCRIPT_CONTINUE;
            }
            if (vehicle.hasMaxStoredVehicles(player))
            {
                sendSystemMessage(player, vehicle.SID_SYS_HAS_MAX_VEHICLE);
                return SCRIPT_CONTINUE;
            }
            if (!vehicle.isInValidUnpackLocation(player))
            {
                sendSystemMessage(player, vehicle.SID_SYS_CANT_CALL_LOC);
                return SCRIPT_CONTINUE;
            }
            obj_id pet = createCraftedCreatureDevice(player, self);
            if (isIdValid(pet))
            {
                String custLogMsg = "New Player Rewards: %TU sucessfully used a vehicle rental device and now has a rental vehicle.";
                CustomerServiceLog("NEW_PLAYER_QUESTS", custLogMsg, player);
                destroyObject(self);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public obj_id createCraftedCreatureDevice(obj_id player, obj_id deed) throws InterruptedException
    {
        if (!hasScript(player, "ai.pet_master"))
        {
            attachScript(player, "ai.pet_master");
        }
        String objectRef = VEHICLE_TYPE;
        obj_id datapad = utils.getPlayerDatapad(player);
        if (!isIdValid(datapad))
        {
            return null;
        }
        String pcdTemplate = utils.dataTableGetString(create.VEHICLE_TABLE, objectRef, "PCD_TEMPLATE");
        obj_id petControlDevice = createObject(pcdTemplate, datapad, "");
        if (!isIdValid(petControlDevice))
        {
            sendSystemMessageTestingOnly(player, "Failed to create pet control device for vehicle");
            return null;
        }
        else 
        {
            setObjVar(petControlDevice, "pet.crafted", true);
            setObjVar(petControlDevice, "pet.uses_left", USES);
            setObjVar(petControlDevice, "vehicle_attribs.object_ref", objectRef);
            attachScript(petControlDevice, "systems.vehicle_system.vehicle_control_device");
        }
        String objectTemplate = utils.dataTableGetString(create.VEHICLE_TABLE, objectRef, "OBJECT_TEMPLATE");
        obj_id pet = create.object(objectTemplate, getLocation(player));
        initCraftedStats(petControlDevice, deed, pet);
        if (!isIdValid(pet))
        {
            return petControlDevice;
        }
        callable.setCallableCD(pet, petControlDevice);
        setObjVar(pet, "vehicularTestBed", 1);
        setObjVar(pet, "ai.pet.masterName", getEncodedName(player));
        if (hasObjVar(petControlDevice, "pet.uses_left"))
        {
            int uses = getIntObjVar(petControlDevice, "pet.uses_left");
            uses--;
            prose_package pp = prose.getPackage(vehicle.SID_SYS_USES_LEFT_NEW, uses);
            sendSystemMessageProse(player, pp);
            setObjVar(petControlDevice, "pet.uses_left", uses);
        }
        if (couldPetBeMadeMountable(pet) != 0)
        {
            return petControlDevice;
        }
        if (!makePetMountable(pet))
        {
            return petControlDevice;
        }
        else 
        {
            setObjVar(petControlDevice, "ai.pet.trainedMount", 1);
        }
        messageTo(pet, "handleVehicleDecay", null, 100, false);
        callable.setCallableLinks(player, petControlDevice, pet);
        return petControlDevice;
    }
    public void initCraftedStats(obj_id device, obj_id deed, obj_id pet) throws InterruptedException
    {
        int hitPoints = MAX_HP;
        if (isIdValid(device))
        {
            setObjVar(device, "attrib.max_hp", hitPoints);
            setObjVar(device, "attrib.hit_points", hitPoints);
        }
        if (isIdValid(pet))
        {
            setMaxHitpoints(pet, hitPoints);
            setHitpoints(pet, hitPoints);
        }
    }
}
