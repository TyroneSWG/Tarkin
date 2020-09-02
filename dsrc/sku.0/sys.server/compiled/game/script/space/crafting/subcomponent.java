package script.space.crafting;

import script.library.utils;
import script.obj_id;

public class subcomponent extends script.base_script
{
    public subcomponent()
    {
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        int idx = utils.getValidAttributeIndex(names);
        if (idx == -1)
        {
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(self, "crafting_components.hitPointsMax"))
        {
            float value = getFloatObjVar(self, "crafting_components.hitPointsMax");
            names[idx] = "hitPointsMax";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.armorHpMax"))
        {
            float value = getFloatObjVar(self, "crafting_components.armorHpMax");
            names[idx] = "armorHpMax";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.mass"))
        {
            float value = getFloatObjVar(self, "crafting_components.mass");
            names[idx] = "mass";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.booster_recharge"))
        {
            float value = getFloatObjVar(self, "crafting_components.booster_recharge");
            names[idx] = "booster_recharge";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.booster_energy"))
        {
            float value = getFloatObjVar(self, "crafting_components.booster_energy");
            names[idx] = "booster_energy";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.booster_speed"))
        {
            float value = getFloatObjVar(self, "crafting_components.booster_speed");
            names[idx] = "booster_speed";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.booster_acceleration"))
        {
            float value = getFloatObjVar(self, "crafting_components.booster_acceleration");
            names[idx] = "booster_acceleration";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.booster_consumption"))
        {
            float value = getFloatObjVar(self, "crafting_components.booster_consumption");
            names[idx] = "booster_consumption";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.cap_max_energy"))
        {
            float value = getFloatObjVar(self, "crafting_components.cap_max_energy");
            names[idx] = "cap_max_energy";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.cap_recharge_rate"))
        {
            float value = getFloatObjVar(self, "crafting_components.cap_recharge_rate");
            names[idx] = "cap_recharge_rate";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.droid_command_speed"))
        {
            float value = getFloatObjVar(self, "crafting_components.droid_command_speed");
            names[idx] = "droid_command_speed";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.energy_maintenance"))
        {
            float value = getFloatObjVar(self, "crafting_components.energy_maintenance");
            names[idx] = "energy_maintenance";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.engine_speed"))
        {
            float value = getFloatObjVar(self, "crafting_components.engine_speed");
            names[idx] = "engine_speed";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.engine_accel"))
        {
            float value = getFloatObjVar(self, "crafting_components.engine_accel");
            names[idx] = "engine_accel";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.engine_decel"))
        {
            float value = getFloatObjVar(self, "crafting_components.engine_decel");
            names[idx] = "engine_decel";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.energy_gen_rate"))
        {
            float value = getFloatObjVar(self, "crafting_components.energy_gen_rate");
            names[idx] = "energy_gen_rate";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.shield_max_back_hp"))
        {
            float value = getFloatObjVar(self, "crafting_components.shield_max_back_hp");
            names[idx] = "shield_max_back_hp";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.shield_max_front_hp"))
        {
            float value = getFloatObjVar(self, "crafting_components.shield_max_front_hp");
            names[idx] = "shield_max_front_hp";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.shield_recharge"))
        {
            float value = getFloatObjVar(self, "crafting_components.shield_recharge");
            names[idx] = "shield_recharge";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.effective_shields"))
        {
            float value = getFloatObjVar(self, "crafting_components.effective_shields");
            names[idx] = "effective_shields";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.effective_armor"))
        {
            float value = getFloatObjVar(self, "crafting_components.effective_armor");
            names[idx] = "effective_armor";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.damage_max"))
        {
            float value = getFloatObjVar(self, "crafting_components.damage_max");
            names[idx] = "damage_max";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.damage_min"))
        {
            float value = getFloatObjVar(self, "crafting_components.damage_min");
            names[idx] = "damage_min";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.refire_rate"))
        {
            float value = getFloatObjVar(self, "crafting_components.refire_rate");
            names[idx] = "refire_rate";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.energy_per_shot"))
        {
            float value = getFloatObjVar(self, "crafting_components.energy_per_shot");
            names[idx] = "energy_per_shot";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.engine_yaw"))
        {
            float value = getFloatObjVar(self, "crafting_components.engine_yaw");
            names[idx] = "engine_yaw";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.engine_pitch"))
        {
            float value = getFloatObjVar(self, "crafting_components.engine_pitch");
            names[idx] = "engine_pitch";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        if (hasObjVar(self, "crafting_components.engine_roll"))
        {
            float value = getFloatObjVar(self, "crafting_components.engine_roll");
            names[idx] = "engine_roll";
            attribs[idx] = Float.toString(value);
            idx++;
        }
        return SCRIPT_CONTINUE;
    }
}
