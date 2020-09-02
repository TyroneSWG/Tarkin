package script.theme_park.heroic.echo_base;

import script.dictionary;
import script.library.buff;
import script.library.trial;
import script.library.utils;
import script.obj_id;

public class ice_block extends script.base_script
{
    public ice_block()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        messageTo(self, "cleanupSelf", null, 90.0f, false);
        trial.markAsTempObject(self, true);
        return SCRIPT_CONTINUE;
    }
    public int cleanupSelf(obj_id self, dictionary params) throws InterruptedException
    {
        String buffName = "";
        if (utils.hasScriptVar(self, "buffName"))
        {
            buffName = utils.getStringScriptVar(self, "buffName");
        }
        if (utils.hasScriptVar(self, "iceblocked"))
        {
            obj_id iceblocked = utils.getObjIdScriptVar(self, "iceblocked");
            if (isIdValid(iceblocked) && exists(iceblocked))
            {
                if (utils.hasScriptVar(iceblocked, "iceblock") && buffName.length() > 0)
                {
                    if (!buff.hasBuff(iceblocked, buffName))
                    {
                        utils.removeScriptVar(iceblocked, "iceblock");
                    }
                }
            }
        }
        trial.cleanupObject(self);
        return SCRIPT_CONTINUE;
    }
}
