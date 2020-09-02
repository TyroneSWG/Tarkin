package script.systems.crafting.item;

import script.library.craftinglib;
import script.library.utils;
import script.obj_id;

public class item_component_attribute extends script.base_script
{
    public item_component_attribute()
    {
    }
    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        int idx = utils.getValidAttributeIndex(names);
        if (idx == -1)
        {
            return SCRIPT_CONTINUE;
        }
        if (exists(self) && hasObjVar(self, "crafting.stationMod"))
        {
            names[idx] = "stationmod";
            float attrib = utils.roundFloatByDecimal(getFloatObjVar(self, "crafting.stationMod"));
            attribs[idx] = "" + attrib;
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        if (exists(self) && hasObjVar(self, "crafting.stationMod_1"))
        {
            names[idx] = "stationmod";
            float attrib = utils.roundFloatByDecimal(getFloatObjVar(self, "crafting.stationMod_1"));
            attribs[idx] = "" + attrib;
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        if (exists(self) && hasObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + ".useModifier"))
        {
            names[idx] = "usemodifier";
            float attrib = utils.roundFloatByDecimal(getFloatObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + ".useModifier"));
            attribs[idx] = "" + attrib;
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        if (exists(self) && hasObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + ".mechanism_quality"))
        {
            names[idx] = "mechanism_quality";
            float attrib = utils.roundFloatByDecimal(getFloatObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + ".mechanism_quality"));
            attribs[idx] = "" + attrib;
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        if (exists(self) && hasObjVar(self, "repair.quality"))
        {
            names[idx] = "repairquality";
            float attrib = utils.roundFloatByDecimal(getFloatObjVar(self, "repair.quality"));
            attribs[idx] = " " + attrib;
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        if (exists(self) && hasObjVar(self, "quality"))
        {
            names[idx] = "quality";
            float attrib = utils.roundFloatByDecimal(getFloatObjVar(self, "quality"));
            attribs[idx] = " " + attrib;
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        if (exists(self) && hasObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + ".enzyme_purity"))
        {
            names[idx] = "enzyme_purity";
            float attrib = utils.roundFloatByDecimal(getFloatObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + ".enzyme_purity"));
            attribs[idx] = " " + attrib;
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        if (exists(self) && hasObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + ".enzyme_mutagen"))
        {
            names[idx] = "enzyme_mutagen";
            float attrib = utils.roundFloatByDecimal(getFloatObjVar(self, craftinglib.COMPONENT_ATTRIBUTE_OBJVAR_NAME + ".enzyme_mutagen"));
            attribs[idx] = " " + attrib;
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        if (exists(self) && hasObjVar(self, "crafting.enzyme_purity"))
        {
            names[idx] = "enzyme_purity";
            float attrib = utils.roundFloatByDecimal(getFloatObjVar(self, "crafting.enzyme_purity"));
            attribs[idx] = " " + attrib;
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        if (exists(self) && hasObjVar(self, "crafting.enzyme_mutagen"))
        {
            names[idx] = "enzyme_mutagen";
            float attrib = utils.roundFloatByDecimal(getFloatObjVar(self, "crafting.enzyme_mutagen"));
            attribs[idx] = " " + attrib;
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        if (exists(self) && hasObjVar(self, "enzyme.enzyme_purity"))
        {
            names[idx] = "enzyme_purity";
            float attrib = utils.roundFloatByDecimal(getFloatObjVar(self, "enzyme.enzyme_purity"));
            attribs[idx] = " " + attrib;
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        if (exists(self) && hasObjVar(self, "enzyme.enzyme_mutagen"))
        {
            names[idx] = "enzyme_mutagen";
            float attrib = utils.roundFloatByDecimal(getFloatObjVar(self, "enzyme.enzyme_mutagen"));
            attribs[idx] = " " + attrib;
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        if (exists(self) && hasObjVar(self, "enzyme.enzyme_processed"))
        {
            names[idx] = "enzyme_processed";
            boolean attrib = getBooleanObjVar(self, "enzyme.enzyme_processed");
            attribs[idx] = " " + attrib;
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        if (exists(self) && hasObjVar(self, "enzyme.element_processed"))
        {
            names[idx] = "enzyme_processed";
            boolean attrib = getBooleanObjVar(self, "enzyme.element_processed");
            attribs[idx] = " " + attrib;
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }
}
