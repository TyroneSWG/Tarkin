package script.systems.fs_quest;

import script.dictionary;
import script.library.ai_lib;
import script.library.chat;
import script.library.prose;
import script.library.utils;
import script.obj_id;
import script.prose_package;
import script.string_id;

public class fs_village_puking extends script.base_script
{
    public fs_village_puking()
    {
    }
    public static final String PP_FILE_LOC = "quest/force_sensitive/fs_sick";
    public static final String RESPONSE_TEXT = "datatables/fs_quests/fs_sick.iff";
    public static int INITIAL_DELAY = 10;
    public int OnAttach(obj_id self) throws InterruptedException
    {
        if (hasScript(self, "ai.creature_combat"))
        {
            detachScript(self, "ai.creature_combat");
        }
        if (hasScript(self, "systems.combat.credit_for_kills"))
        {
            detachScript(self, "systems.combat.credit_for_kills");
        }
        if (hasScript(self, "systems.combat.combat_actions"))
        {
            detachScript(self, "systems.combat.combat_actions");
        }
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_STOP);
        messageTo(self, "fsOldSoup", null, INITIAL_DELAY, false);
        return SCRIPT_CONTINUE;
    }
    public int fsOldSoup(obj_id self, dictionary params) throws InterruptedException
    {
        int randSick = rand(0, 6);
        chat.publicChat(self, null, null, null, getRandSick(self, randSick));
        ai_lib.doAction(self, "heavy_cough_vomit");
        int randRepeatMsg = rand(90, 300);
        messageTo(self, "fsOldSoup", null, randRepeatMsg, false);
        return SCRIPT_CONTINUE;
    }
    public prose_package getRandSick(obj_id target, int sickRow) throws InterruptedException
    {
        string_id response = new string_id(PP_FILE_LOC, utils.dataTableGetString(RESPONSE_TEXT, sickRow, 1));
        prose_package pp = prose.getPackage(response, target);
        return pp;
    }
}
