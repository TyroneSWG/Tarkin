package script.systems.crafting.armor.component;

import script.library.craftinglib;
import script.library.utils;
import script.obj_id;
import script.obj_var;
import script.obj_var_list;

public class crafting_armor_component_attribute extends script.base_script
{
    public crafting_armor_component_attribute()
    {
    }
    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        int idx = utils.getValidAttributeIndex(names);
        if (idx == -1)
        {
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "armor_effectiveness"))
        {
            names[idx] = "baseeffectiveness";
            int attrib = (int)getFloatObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "armor_effectiveness");
            attribs[idx] = Integer.toString(attrib) + "%";
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        if (hasObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "armor_integrity"))
        {
            names[idx] = "baseintegrity";
            int attrib = (int)getFloatObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "armor_integrity");
            attribs[idx] = Integer.toString(attrib);
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        if (hasObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "armor_special_protection"))
        {
            obj_var_list prot_list = getObjVarList(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + "." + "armor_special_protection");
            if (prot_list != null)
            {
                for (int i = 0; i < prot_list.getNumItems(); i++)
                {
                    obj_var prot = prot_list.getObjVar(i);
                    int prot_type = utils.stringToInt(prot.getName());
                    switch (prot_type)
                    {
                        case 0x00000001:
                        names[idx] = "kineticeffectiveness";
                        break;
                        case 0x00000002:
                        names[idx] = "energyeffectiveness";
                        break;
                        case 0x00000004:
                        names[idx] = "blasteffectiveness";
                        break;
                        case 0x00000008:
                        names[idx] = "stuneffectiveness";
                        break;
                        case 0x00000010:
                        names[idx] = "restrainteffectiveness";
                        break;
                        case 0x00000020:
                        names[idx] = "heateffectiveness";
                        break;
                        case 0x00000040:
                        names[idx] = "coldeffectiveness";
                        break;
                        case 0x00000080:
                        names[idx] = "acideffectiveness";
                        break;
                        case 0x00000100:
                        names[idx] = "electricaleffectiveness";
                        break;
                        default:
                        continue;
                    }
                    int prot_eff = (int)prot.getFloatData();
                    attribs[idx] = Integer.toString(prot_eff) + "%";
                    idx++;
                    if (idx >= names.length)
                    {
                        return SCRIPT_CONTINUE;
                    }
                }
            }
        }
        if (hasObjVar(self, craftinglib.OBJVAR_RE_VALUE))
        {
            names[idx] = "@crafting:power_bit_power";
            attribs[idx] = "" + getFloatObjVar(self, craftinglib.OBJVAR_RE_VALUE);
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        if (hasObjVar(self, craftinglib.OBJVAR_RE_STAT_MODIFIED))
        {
            names[idx] = "@crafting:mod_bit_type";
            attribs[idx] = "@stat_n:" + getStringObjVar(self, craftinglib.OBJVAR_RE_STAT_MODIFIED);
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }
}
