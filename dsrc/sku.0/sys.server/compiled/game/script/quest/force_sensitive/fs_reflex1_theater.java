package script.quest.force_sensitive;

import script.dictionary;
import script.library.quests;
import script.location;
import script.obj_id;

public class fs_reflex1_theater extends script.base_script
{
    public fs_reflex1_theater()
    {
    }
    public int OnTheaterCreated(obj_id self, obj_id[] objects, obj_id player, obj_id creator) throws InterruptedException
    {
        CustomerServiceLog("fs_quests", "DEBUG -- %TU has spawned the theater assigned to him/her.", player);
        String questName = "fs_reflex_rescue_quest_02";
        location loc = getLocation(self);
        obj_id wp = createWaypointInDatapad(player, loc);
        String summary = quests.getDataEntry(questName, "JOURNAL_ENTRY_SUMMARY");
        if (summary != null && summary.length() > 0)
        {
            setWaypointName(wp, summary);
        }
        else 
        {
            setWaypointName(wp, "missing task summary for " + questName);
        }
        setWaypointColor(wp, "yellow");
        setWaypointActive(wp, true);
        setObjVar(player, "quest." + questName + ".waypoint", wp);
        CustomerServiceLog("fs_quests", "DEBUG -- %TU should have a waypoint directing him/her to the theater.", player);
        dictionary d = new dictionary();
        d.put("player", player);
        d.put("retry", 0);
        messageTo(self, "handleSetupPrisoner", d, 35.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int handleSetupPrisoner(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = params.getObjId("player");
        int retry = params.getInt("retry");
        obj_id prisoner = getFirstObjectWithScript(getLocation(self), 32.0f, "conversation.fs_reflex1_prisoner");
        dictionary d = new dictionary();
        d.put("player", player);
        d.put("retry", (retry + 1));
        if (isIdValid(prisoner))
        {
            messageTo(prisoner, "handleSetupPrisoner", d, 1.0f, false);
        }
        else 
        {
            if (retry < 5)
            {
                messageTo(self, "handleSetupPrisoner", d, 15.0f, false);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
