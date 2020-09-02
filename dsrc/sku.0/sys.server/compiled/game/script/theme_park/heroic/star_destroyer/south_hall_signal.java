package script.theme_park.heroic.star_destroyer;

import script.dictionary;
import script.library.trial;
import script.library.utils;
import script.obj_id;

public class south_hall_signal extends script.base_script
{
    public south_hall_signal()
    {
    }
    public int startSpawnLoop(obj_id self, dictionary params) throws InterruptedException
    {
        if (!isIdValid(self) || !exists(self))
        {
            return SCRIPT_OVERRIDE;
        }
        if (!hasObjVar(self, "first"))
        {
            setObjVar(self, "first", 1);
            messageTo(self, "doNextSpawn", null, 10.0f, false);
        }
        else 
        {
            messageTo(self, "doNextSpawn", null, 50.0f, false);
        }
        return SCRIPT_CONTINUE;
    }
    public int doNextSpawn(obj_id self, dictionary params) throws InterruptedException
    {
        if (!isIdValid(self) || !exists(self))
        {
            return SCRIPT_OVERRIDE;
        }
        String[] cell_list = 
        {
            "elevator00",
            "elevator01",
            "secondaryhangar",
            "room05",
            "hallway09",
            "hallway08",
            "hallway07",
            "hallway06",
            "hallway03",
            "room04",
            "room03",
            "room02",
            "room01",
            "navcomputer"
        };
        obj_id[] players = trial.getPlayersInCellList(trial.getTop(self), cell_list);
        if (players == null || players.length == 0)
        {
            dictionary dict = trial.getSessionDict(trial.getTop(self));
            dict.put("triggerType", "triggerId");
            dict.put("triggerName", "reset_event");
            messageTo(trial.getTop(self), "triggerFired", dict, 0.0f, false);
            return SCRIPT_CONTINUE;
        }
        int step = 0;
        if (utils.hasScriptVar(self, "step"))
        {
            step = utils.getIntScriptVar(self, "step");
        }
        dictionary dict = trial.getSessionDict(trial.getTop(self));
        dict.put("triggerType", "triggerId");
        if (step == 3)
        {
            dict.put("triggerName", "spawn_squad_leader");
            step = 0;
        }
        else 
        {
            dict.put("triggerName", "next_south_spawn");
            step++;
        }
        utils.setScriptVar(self, "step", step);
        messageTo(trial.getTop(self), "triggerFired", dict, 0.0f, false);
        return SCRIPT_CONTINUE;
    }
}
