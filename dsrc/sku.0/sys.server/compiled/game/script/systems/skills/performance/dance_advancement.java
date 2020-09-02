package script.systems.skills.performance;

import script.*;
import script.library.sui;
import script.library.xp;

public class dance_advancement extends script.base_script
{
    public dance_advancement()
    {
    }
    public static final String ADVANCEMENT_OBJVAR = "dance_advancement";
    public static final string_id SUI_PROMPT0 = new string_id("dance_advancement", "sui_prompt0");
    public static final string_id SUI_PROMPT1 = new string_id("dance_advancement", "sui_prompt1");
    public int grantUnmodifiedExperienceOnSelf(obj_id self, dictionary params) throws InterruptedException
    {
        if (!hasObjVar(self, ADVANCEMENT_OBJVAR))
        {
            detachScript(self, "systems.skills.performance.dance_advancement");
            return SCRIPT_CONTINUE;
        }
        String xp_type = params.getString("xp_type");
        if (!xp_type.equals(xp.DANCE))
        {
            return SCRIPT_CONTINUE;
        }
        int type = getPerformanceType(self);
        if (type == 0)
        {
            return SCRIPT_CONTINUE;
        }
        obj_var_list ovl = getObjVarList(self, ADVANCEMENT_OBJVAR);
        if (ovl != null)
        {
            int numItem = ovl.getNumItems();
            for (int i = 0; i < numItem; i++)
            {
                obj_var ov = ovl.getObjVar(i);
                String var = ov.getName();
                int idx = getIntObjVar(self, ADVANCEMENT_OBJVAR + "." + var + ".index");
                if (idx == type)
                {
                    int amt = params.getInt("amt");
                    int remaining = getIntObjVar(self, ADVANCEMENT_OBJVAR + "." + var + ".remaining");
                    if (remaining > 0)
                    {
                        remaining -= amt;
                        if (remaining <= 0)
                        {
                            remaining = 0;
                            String prompt = localize(SUI_PROMPT0) + var + localize(SUI_PROMPT1);
                            sui.msgbox(self, prompt);
                        }
                        setObjVar(self, ADVANCEMENT_OBJVAR + "." + var + ".remaining", remaining);
                    }
                    break;
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
