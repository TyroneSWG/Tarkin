package script.theme_park.heroic.exar_kun;

import script.dictionary;
import script.library.instance;
import script.library.skill;
import script.library.trial;
import script.library.utils;
import script.obj_id;

public class exar_controller extends script.base_script
{
    public exar_controller()
    {
    }
    public int exarDied(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id[] players = trial.getPlayersInDungeon(self);
        dictionary dict = new dictionary();
        dict.put("tokenIndex", 4);
        utils.messageTo(players, "handleAwardtoken", dict, 0, false);
        obj_id group = getGroupObject(players[0]);
        int calendarTime = getCalendarTime();
        String realTime = getCalendarTimeStringLocal(calendarTime);
        CustomerServiceLog("instance-heroic_exar_kun", "Exar_Kun Defeated in instance (" + self + ") by group_id (" + group + ") at " + realTime);
        CustomerServiceLog("instance-heroic_exar_kun", "Group (" + group + ") consists of: ");
        for (int i = 0; i < players.length; ++i)
        {
            String strProfession = skill.getProfessionName(getSkillTemplate(players[i]));
            CustomerServiceLog("instance-heroic_exar_kun", "Group (" + group + ") member " + i + " " + getFirstName(players[i]) + "'s(" + players[i] + ") profession is " + strProfession + ".");
        }
        instance.setClock(self, 300);
        return SCRIPT_CONTINUE;
    }
}
