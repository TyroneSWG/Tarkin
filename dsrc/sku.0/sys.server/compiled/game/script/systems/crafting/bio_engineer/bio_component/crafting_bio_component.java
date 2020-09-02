package script.systems.crafting.bio_engineer.bio_component;

import script.library.craftinglib;
import script.resource_weight;

public class crafting_bio_component extends script.systems.crafting.bio_engineer.crafting_base_bio_component
{
    public crafting_bio_component()
    {
    }
    public static final String[] REQUIRED_SKILLS = 
    {
        "outdoors_bio_engineer_novice"
    };
    public static final String[] ASSEMBLY_SKILL_MODS = 
    {
        "medicine_assembly"
    };
    public static final String[] EXPERIMENT_SKILL_MODS = 
    {
        "medicine_experimentation",
        "medicine_complexity"
    };
    public static final String[] CUSTOMIZATION_SKILL_MODS = 
    {
        "bio_engineer_customization"
    };
    public static final resource_weight[] OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("mod_val_one", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_FLAVOR, 1),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 1)
        }),
        new resource_weight("mod_val_two", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_FLAVOR, 1),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 1)
        }),
        new resource_weight("mod_val_three", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_FLAVOR, 1),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 1)
        }),
        new resource_weight("mod_val_four", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_FLAVOR, 1),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 1)
        }),
        new resource_weight("mod_val_five", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_FLAVOR, 1),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 1)
        }),
        new resource_weight("mod_val_six", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_FLAVOR, 1),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 1)
        }),
        new resource_weight("hitPoints", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 1)
        })
    };
    public static final resource_weight[] OBJ_MAX_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("mod_val_one", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_FLAVOR, 2),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 5),
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 3)
        }),
        new resource_weight("mod_val_two", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_FLAVOR, 2),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 5),
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 3)
        }),
        new resource_weight("mod_val_three", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_FLAVOR, 2),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 5),
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 3)
        }),
        new resource_weight("mod_val_four", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_FLAVOR, 2),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 5),
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 3)
        }),
        new resource_weight("mod_val_five", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_FLAVOR, 2),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 5),
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 3)
        }),
        new resource_weight("mod_val_six", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_FLAVOR, 2),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 5),
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 3)
        }),
        new resource_weight("hitPoints", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 5),
            new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 5)
        })
    };
    public String[] getRequiredSkills() throws InterruptedException
    {
        return REQUIRED_SKILLS;
    }
    public String[] getAssemblySkillMods() throws InterruptedException
    {
        return ASSEMBLY_SKILL_MODS;
    }
    public String[] getExperimentSkillMods() throws InterruptedException
    {
        return EXPERIMENT_SKILL_MODS;
    }
    public resource_weight[] getResourceMaxResourceWeights() throws InterruptedException
    {
        return OBJ_MAX_ATTRIBUTE_RESOURCES;
    }
    public resource_weight[] getAssemblyResourceWeights() throws InterruptedException
    {
        return OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES;
    }
}
