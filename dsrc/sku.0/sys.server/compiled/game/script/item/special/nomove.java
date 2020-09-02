package script.item.special;

import script.library.jedi;
import script.library.utils;
import script.obj_id;

public class nomove extends script.item.special.nomove_base
{
    public nomove()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        if (hasObjVar(self, "notrade"))
        {
            removeObjVar(self, "notrade");
        }
        if (jedi.isLightsaber(self))
        {
            lightsaberLogging(self);
        }
        if (utils.isNestedWithinAPlayer(self))
        {
            setObjVar(self, "noTrade", true);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnDetach(obj_id self) throws InterruptedException
    {
        if (hasObjVar(self, "noTrade"))
        {
            removeObjVar(self, "noTrade");
        }
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if (jedi.isLightsaber(self))
        {
            lightsaberLogging(self);
        }
        if (hasObjVar(self, "notrade"))
        {
            removeObjVar(self, "notrade");
        }
        if (utils.isNestedWithinAPlayer(self))
        {
            setObjVar(self, "noTrade", true);
        }
        return SCRIPT_CONTINUE;
    }
    public void lightsaberLogging(obj_id self) throws InterruptedException
    {
        obj_id owner = getOwner(self);
        if (!isIdValid(owner))
        {
            return;
        }
        int minDam = getWeaponMinDamage(self);
        int maxDam = getWeaponMaxDamage(self);
        float attSpeed = getWeaponAttackSpeed(self);
        CustomerServiceLog("Lightsaber:", "%TU: Owns lightsaber: %TT: " + " minDamage: " + minDam + " maxDamage: " + maxDam + " attackSpeed: " + attSpeed, owner, self);
        return;
    }
}
