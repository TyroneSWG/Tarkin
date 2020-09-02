package script.working;

import script.deltadictionary;
import script.library.skill;
import script.obj_id;

public class difficultytest extends script.base_script
{
    public difficultytest()
    {
    }
    public int OnHearSpeech(obj_id self, obj_id objSpeaker, String strText) throws InterruptedException
    {
        if (objSpeaker != self)
        {
            return SCRIPT_CONTINUE;
        }
        if (strText.equals("getDifficulty"))
        {
            int intLevel = getLevel(self);
            sendSystemMessageTestingOnly(self, "My level is " + intLevel);
            debugServerConsoleMsg(self, "my level is " + intLevel);
        }
        if (strText.equals("reloadDifficulty"))
        {
            sendSystemMessageTestingOnly(self, "Reloaded skill level");
            recalculateLevel(self);
        }
        if (strText.equals("getGroupDifficulty"))
        {
            int intLevel = skill.getGroupLevel(self);
            sendSystemMessageTestingOnly(self, "My Group level is " + intLevel);
        }
        if (strText.equals("scriptVars"))
        {
            deltadictionary dctScriptVars = self.getScriptVars();
            if (dctScriptVars == null)
            {
                debugSpeakMsg(self, "null scriptvar");
            }
            debugSpeakMsg(self, "scriptvars are " + dctScriptVars.toString());
        }
        return SCRIPT_CONTINUE;
    }
}
