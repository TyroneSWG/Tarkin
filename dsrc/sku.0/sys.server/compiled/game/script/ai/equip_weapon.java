package script.ai;

import script.obj_id;

public class equip_weapon extends script.base_script
{
    public equip_weapon()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        if (!aiUsingPrimaryWeapon(self))
        {
            aiEquipPrimaryWeapon(self);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if (!aiUsingPrimaryWeapon(self))
        {
            aiEquipPrimaryWeapon(self);
        }
        return SCRIPT_CONTINUE;
    }
}
