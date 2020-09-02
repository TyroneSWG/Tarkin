package script.theme_park.tatooine.jabbaspawner;

import script.dictionary;
import script.library.ai_lib;
import script.obj_id;

public class oola extends script.base_script
{
    public oola()
    {
    }
    public int OnDestroy(obj_id self) throws InterruptedException
    {
        messageTo(getObjIdObjVar(self, "palace"), "oolaDied", null, 20, true);
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        grantSkill(self, "social_dancer_novice");
        messageTo(self, "startDancing", null, 10, true);
        return SCRIPT_CONTINUE;
    }
    public int startDancing(obj_id self, dictionary params) throws InterruptedException
    {
        ai_lib.setMood(self, "themepark_oola");
        return SCRIPT_CONTINUE;
    }
}
