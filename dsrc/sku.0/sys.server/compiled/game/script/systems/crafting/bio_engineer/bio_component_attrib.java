package script.systems.crafting.bio_engineer;

import script.library.bio_engineer;
import script.library.craftinglib;
import script.library.utils;
import script.obj_id;
import script.obj_var;

public class bio_component_attrib extends script.base_script
{
    public bio_component_attrib()
    {
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if (hasObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + craftinglib.TISSUE_SKILL_MODS))
        {
            return SCRIPT_CONTINUE;
        }
        int[] effect_type = 
        {
            0,
            0,
            0,
            0,
            0,
            0
        };
        int[] effect_mod = 
        {
            0,
            0,
            0,
            0,
            0,
            0
        };
        if (hasObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_idx_one"))
        {
            effect_type[0] = getIntObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_idx_one");
            obj_var ov = getObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_val_one");
            Object data = ov.getData();
            if (data instanceof Integer)
            {
                effect_mod[0] = ov.getIntData();
            }
            else if (data instanceof Float)
            {
                effect_mod[0] = (int)ov.getFloatData();
            }
            removeObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_idx_one");
            removeObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_val_one");
        }
        if (hasObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_idx_two"))
        {
            effect_type[1] = getIntObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_idx_two");
            obj_var ov = getObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_val_two");
            Object data = ov.getData();
            if (data instanceof Integer)
            {
                effect_mod[1] = ov.getIntData();
            }
            else if (data instanceof Float)
            {
                effect_mod[1] = (int)ov.getFloatData();
            }
            removeObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_idx_one");
            removeObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_val_one");
        }
        if (hasObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_idx_three"))
        {
            effect_type[2] = getIntObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_idx_three");
            obj_var ov = getObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_val_three");
            Object data = ov.getData();
            if (data instanceof Integer)
            {
                effect_mod[2] = ov.getIntData();
            }
            else if (data instanceof Float)
            {
                effect_mod[2] = (int)ov.getFloatData();
            }
            removeObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_idx_one");
            removeObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_val_one");
        }
        if (hasObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_idx_four"))
        {
            effect_type[3] = getIntObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_idx_four");
            obj_var ov = getObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_val_four");
            Object data = ov.getData();
            if (data instanceof Integer)
            {
                effect_mod[3] = ov.getIntData();
            }
            else if (data instanceof Float)
            {
                effect_mod[3] = (int)ov.getFloatData();
            }
            removeObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_idx_one");
            removeObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_val_one");
        }
        if (hasObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_idx_five"))
        {
            effect_type[4] = getIntObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_idx_five");
            obj_var ov = getObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_val_five");
            Object data = ov.getData();
            if (data instanceof Integer)
            {
                effect_mod[4] = ov.getIntData();
            }
            else if (data instanceof Float)
            {
                effect_mod[4] = (int)ov.getFloatData();
            }
            removeObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_idx_one");
            removeObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_val_one");
        }
        if (hasObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_idx_six"))
        {
            effect_type[5] = getIntObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_idx_six");
            obj_var ov = getObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_val_six");
            Object data = ov.getData();
            if (data instanceof Integer)
            {
                effect_mod[5] = ov.getIntData();
            }
            else if (data instanceof Float)
            {
                effect_mod[5] = (int)ov.getFloatData();
            }
            removeObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_idx_one");
            removeObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "mod_val_one");
        }
        for (int i = 5; i >= 0; i--)
        {
            if (effect_type[i] != 0)
            {
                for (int j = 0; j < i; j++)
                {
                    if (effect_type[j] == 0)
                    {
                        effect_type[j] = effect_type[i];
                        effect_mod[j] = effect_mod[i];
                        effect_type[i] = 0;
                        effect_mod[i] = 0;
                    }
                }
            }
        }
        String root = craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + craftinglib.TISSUE_SKILL_MODS + ".";
        setObjVar(self, root + craftinglib.TISSUE_SKILL_INDEX, effect_type);
        setObjVar(self, root + craftinglib.TISSUE_SKILL_VALUE, effect_mod);
        return SCRIPT_CONTINUE;
    }
    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        int idx = utils.getValidAttributeIndex(names);
        if (idx == -1)
        {
            return SCRIPT_CONTINUE;
        }
        if (isIdValid(self) && exists(self) && hasObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + craftinglib.TISSUE_SKILL_MODS))
        {
            String root = craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + craftinglib.TISSUE_SKILL_MODS + ".";
            int[] mod_idx = getIntArrayObjVar(self, root + craftinglib.TISSUE_SKILL_INDEX);
            int[] mod_val = getIntArrayObjVar(self, root + craftinglib.TISSUE_SKILL_VALUE);
            for (int i = 0; i < mod_idx.length; i++)
            {
                if (mod_idx[i] == 0)
                {
                    break;
                }
                if (mod_idx[i] > bio_engineer.BIO_COMP_EFFECT_CLOTHING)
                {
                    names[idx] = "bio_comp_" + bio_engineer.BIO_COMP_EFFECT_SKILL_MODS[(mod_idx[i] - (bio_engineer.BIO_COMP_EFFECT_CLOTHING + 1))];
                }
                else 
                {
                    names[idx] = "bio_comp_" + bio_engineer.BIO_COMP_EFFECT_FOOD_MODS[(mod_idx[i] - 1)];
                }
                if (mod_val[i] >= 0)
                {
                    attribs[idx] = "+" + Integer.toString(mod_val[i]);
                }
                else 
                {
                    attribs[idx] = Integer.toString(mod_val[i]);
                }
                if (mod_idx[i] == bio_engineer.BIO_COMP_EFFECT_DURATION || mod_idx[i] == bio_engineer.BIO_COMP_EFFECT_FLAVOR)
                {
                    attribs[idx] += "%";
                }
                idx++;
                if (idx >= names.length)
                {
                    return SCRIPT_CONTINUE;
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
