package script.systems.crafting.structure.specific_item;

import script.draft_schematic;
import script.library.craftinglib;
import script.obj_id;
import script.resource_weight;

public class crafting_garden_medium extends script.systems.crafting.structure.crafting_base_structure
{
    public crafting_garden_medium()
    {
    }
    public static final String VERSION = "v0.00.00";
    public static final String[] REQUIRED_SKILLS = 
    {
        "crafting_architect_novice"
    };
    public static final String[] ASSEMBLY_SKILL_MODS = 
    {
        "structure_assembly"
    };
    public static final String[] EXPERIMENT_SKILL_MODS = 
    {
        "structure_experimentation"
    };
    public static final String[] APPEARANCES = 
    {
        "object/tangible/deed/city_deed/shared_garden_corellia_med_02_deed.iff",
        "object/tangible/deed/city_deed/shared_garden_corellia_med_03_deed.iff",
        "object/tangible/deed/city_deed/shared_garden_corellia_med_04_deed.iff",
        "object/tangible/deed/city_deed/shared_garden_corellia_med_05_deed.iff",
        "object/tangible/deed/city_deed/shared_garden_naboo_med_01_deed.iff",
        "object/tangible/deed/city_deed/shared_garden_naboo_med_02_deed.iff",
        "object/tangible/deed/city_deed/shared_garden_naboo_med_03_deed.iff",
        "object/tangible/deed/city_deed/shared_garden_naboo_med_04_deed.iff",
        "object/tangible/deed/city_deed/shared_garden_naboo_med_05_deed.iff",
        "object/tangible/deed/city_deed/shared_garden_tatooine_med_01_deed.iff",
        "object/tangible/deed/city_deed/shared_garden_tatooine_med_02_deed.iff",
        "object/tangible/deed/city_deed/shared_garden_tatooine_med_03_deed.iff",
        "object/tangible/deed/city_deed/shared_garden_tatooine_med_04_deed.iff",
        "object/tangible/deed/city_deed/shared_garden_tatooine_med_05_deed.iff"
    };
    public static final String[] ALT_TEMPLATES = 
    {
        "object/building/player/city/garden_corellia_med_02.iff",
        "object/building/player/city/garden_corellia_med_03.iff",
        "object/building/player/city/garden_corellia_med_04.iff",
        "object/building/player/city/garden_corellia_med_05.iff",
        "object/building/player/city/garden_naboo_med_01.iff",
        "object/building/player/city/garden_naboo_med_02.iff",
        "object/building/player/city/garden_naboo_med_03.iff",
        "object/building/player/city/garden_naboo_med_04.iff",
        "object/building/player/city/garden_naboo_med_05.iff",
        "object/building/player/city/garden_tatooine_med_01.iff",
        "object/building/player/city/garden_tatooine_med_02.iff",
        "object/building/player/city/garden_tatooine_med_03.iff",
        "object/building/player/city/garden_tatooine_med_04.iff",
        "object/building/player/city/garden_tatooine_med_05.iff"
    };
    public static final String[] ALT_PLANETS = 
    {
        "corellia,talus",
        "corellia,talus",
        "corellia,talus",
        "corellia,talus",
        "naboo,rori,dantooine",
        "naboo,rori,dantooine",
        "naboo,rori,dantooine",
        "naboo,rori,dantooine",
        "naboo,rori,dantooine",
        "tatooine,lok,dantooine",
        "tatooine,lok,dantooine",
        "tatooine,lok,dantooine",
        "tatooine,lok,dantooine",
        "tatooine,lok,dantooine"
    };
    public static final resource_weight[] OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("hitPoints", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_DECAY_RESIST, 1)
        })
    };
    public static final resource_weight[] OBJ_MAX_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("hitPoints", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_DECAY_RESIST, 1)
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
    public String[] getAppearances(obj_id player, draft_schematic.slot[] slots) throws InterruptedException
    {
        return APPEARANCES;
    }
    public void modifyForCustomAppearance(obj_id self, obj_id newObject) throws InterruptedException
    {
        String customAppearance = getStringObjVar(self, "customAppearance");
        for (int i = 0; i < APPEARANCES.length; i++)
        {
            if (customAppearance.equals(APPEARANCES[i]))
            {
                setObjVar(newObject, "player_structure.deed.template", ALT_TEMPLATES[i]);
                setObjVar(newObject, "player_structure.deed.scene", ALT_PLANETS[i]);
                return;
            }
        }
    }
}
