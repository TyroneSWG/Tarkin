package script.systems.crafting.community_crafting.component;

import script.library.craftinglib;
import script.resource_weight;

public class refined_endrine extends script.systems.crafting.community_crafting.component.base_component
{
    public refined_endrine()
    {
    }
    public static final String VERSION = "v0.00.00";
    public static final String[] REQUIRED_SKILLS = 
    {
        "crafting_artisan_novice"
    };
    public static final String[] ASSEMBLY_SKILL_MODS = 
    {
        "general_assembly"
    };
    public static final String[] EXPERIMENT_SKILL_MODS = 
    {
        "general_experimentation"
    };
    public static final resource_weight[] OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("res_malleability", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_MALLEABILITY, 1)
        }),
        new resource_weight("res_quality", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
        }),
        new resource_weight("res_toughness", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 1)
        })
    };
    public static final resource_weight[] OBJ_MAX_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("res_malleability", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_MALLEABILITY, 1)
        }),
        new resource_weight("res_quality", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
        }),
        new resource_weight("res_toughness", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 1)
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
