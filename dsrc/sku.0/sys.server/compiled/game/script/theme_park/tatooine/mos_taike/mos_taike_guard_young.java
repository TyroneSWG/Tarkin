package script.theme_park.tatooine.mos_taike;

import script.library.ai_lib;
import script.library.colors;
import script.library.hue;
import script.obj_id;

public class mos_taike_guard_young extends script.base_script
{
    public mos_taike_guard_young()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id shirt = createObject("object/tangible/wearables/armor/padded/armor_padded_s01_chest_plate.iff", self, "");
        obj_id pants = createObject("object/tangible/wearables/armor/padded/armor_padded_s01_leggings.iff", self, "");
        obj_id shoes = createObject("object/tangible/wearables/armor/padded/armor_padded_s01_boots.iff", self, "");
        obj_id arm_l = createObject("object/tangible/wearables/armor/padded/armor_padded_s01_bicep_l.iff", self, "");
        obj_id arm_r = createObject("object/tangible/wearables/armor/padded/armor_padded_s01_bicep_r.iff", self, "");
        obj_id brc_l = createObject("object/tangible/wearables/armor/padded/armor_padded_s01_bracer_l.iff", self, "");
        obj_id brc_r = createObject("object/tangible/wearables/armor/padded/armor_padded_s01_bracer_r.iff", self, "");
        obj_id gloves = createObject("object/tangible/wearables/armor/padded/armor_padded_s01_gloves.iff", self, "");
        obj_id belt = createObject("object/tangible/wearables/belt/belt_s02.iff", self, "");
        obj_id bandolier = createObject("object/tangible/wearables/bandolier/bandolier_s02.iff", self, "");
        obj_id hair = createObject("object/tangible/hair/human/hair_human_male_s18.iff", self, "");
        setName(self, "Bren Kingal");
        hue.setColor(shirt, 1, colors.BEIGE);
        hue.setColor(shirt, 2, colors.TAN);
        hue.setColor(pants, 1, colors.BEIGE);
        hue.setColor(pants, 2, colors.TAN);
        hue.setColor(arm_l, 1, colors.BEIGE);
        hue.setColor(arm_l, 2, colors.TAN);
        hue.setColor(arm_r, 1, colors.BEIGE);
        hue.setColor(arm_r, 2, colors.TAN);
        hue.setColor(brc_l, 1, colors.BEIGE);
        hue.setColor(brc_l, 2, colors.TAN);
        hue.setColor(brc_r, 1, colors.BEIGE);
        hue.setColor(brc_r, 2, colors.TAN);
        hue.setColor(gloves, 1, colors.BEIGE);
        hue.setColor(gloves, 2, colors.TAN);
        hue.setColor(belt, 1, colors.BROWN);
        hue.setColor(belt, 2, colors.SILVER);
        hue.setColor(bandolier, 1, colors.BROWN);
        hue.setColor(bandolier, 2, colors.SILVER);
        hue.setColor(hair, 1, 17);
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        attachScript(self, "npc.static_quest.quest_convo");
        setObjVar(self, "quest_table", "mos_taike_young_guard");
        return SCRIPT_CONTINUE;
    }
}
