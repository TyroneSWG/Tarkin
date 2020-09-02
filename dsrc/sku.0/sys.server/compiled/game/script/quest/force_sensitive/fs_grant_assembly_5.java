package script.quest.force_sensitive;

import script.dictionary;
import script.library.utils;
import script.obj_id;

public class fs_grant_assembly_5 extends script.base_script
{
    public fs_grant_assembly_5()
    {
    }
    public static final String DATATABLE_SKILLS = "datatables/skill/skills.iff";
    public static final String SKILL = "force_sensitive_crafting_mastery_assembly_01";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        obj_id player = utils.getContainingPlayer(self);
        if (isIdValid(player))
        {
            int row = dataTableSearchColumnForString(SKILL, "NAME", DATATABLE_SKILLS);
            if (row >= 0)
            {
                float xp = dataTableGetInt(DATATABLE_SKILLS, row, "XP_COST");
                String xpType = dataTableGetString(DATATABLE_SKILLS, row, "XP_TYPE");
                if (xp > 0 && xpType != null)
                {
                    grantExperiencePoints(player, xpType, (int)(xp * 0.05f));
                }
                else 
                {
                    CustomerServiceLog("fs_village", "WARNING: fs_grant_assembly_5 could not find xp data for skill " + SKILL + " in the skills datatable");
                }
            }
            else 
            {
                CustomerServiceLog("fs_village", "WARNING: fs_grant_assembly_5 could not find skill " + SKILL + " in the skills datatable");
            }
        }
        else 
        {
            CustomerServiceLog("fs_village", "WARNING: fs_grant_assembly_5 could not find player containing object " + self);
        }
        messageTo(self, "handleDetachMessage", null, 1.0f, true);
        return SCRIPT_CONTINUE;
    }
    public int handleDetachMessage(obj_id self, dictionary params) throws InterruptedException
    {
        detachScript(self, "quest.force_sensitive.fs_grant_assembly_5");
        return SCRIPT_CONTINUE;
    }
}
