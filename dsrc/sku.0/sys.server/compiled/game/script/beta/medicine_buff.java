package script.beta;

import script.attrib_mod;
import script.library.consumable;
import script.library.healing;
import script.obj_id;

public class medicine_buff extends script.base_script
{
    public medicine_buff()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        if (hasObjVar(self, consumable.VAR_CONSUMABLE_MODS))
        {
            removeObjVar(self, consumable.VAR_CONSUMABLE_MODS);
        }
        String template_name = getTemplateName(self);
        int buff_type = -1;
        if (template_name.equals("object/tangible/medicine/medpack_enhance_health.iff"))
        {
            buff_type = 0;
        }
        if (template_name.equals("object/tangible/medicine/medpack_enhance_constitution.iff"))
        {
            buff_type = 2;
        }
        if (template_name.equals("object/tangible/medicine/medpack_enhance_action.iff"))
        {
            buff_type = 3;
        }
        if (template_name.equals("object/tangible/medicine/medpack_enhance_stamina.iff"))
        {
            buff_type = 5;
        }
        int power = rand(25, 150);
        int charges = rand(1, 3);
        int duration = rand(300, 1800);
        if (buff_type == -1)
        {
            buff_type = rand(0, NUM_ATTRIBUTES - NUM_ATTRIBUTES_PER_GROUP - 1);
        }
        attrib_mod[] am = new attrib_mod[1];
        attrib_mod tmp = new attrib_mod(buff_type, power, duration, healing.VAR_BUFF_MOD_ATTACK, healing.VAR_BUFF_MOD_DECAY);
        am[0] = tmp;
        setObjVar(self, consumable.VAR_CONSUMABLE_MODS, am);
        setObjVar(self, consumable.VAR_CONSUMABLE_MEDICINE, true);
        setCount(self, charges);
        int[] stomach = 
        {
            0,
            0,
            0
        };
        setObjVar(self, consumable.VAR_CONSUMABLE_STOMACH_VALUES, stomach);
        String[] skill_mod = 
        {
            "healing_ability"
        };
        setObjVar(self, consumable.VAR_SKILL_MOD_REQUIRED, skill_mod);
        int skill_req = rand(1, 25);
        int[] skill_value = 
        {
            skill_req
        };
        setObjVar(self, consumable.VAR_SKILL_MOD_MIN, skill_value);
        return SCRIPT_CONTINUE;
    }
}
