package script.theme_park.alderaan.act2;

import script.obj_id;
import script.string_id;

public class relay_station_captain extends script.base_script
{
    public relay_station_captain()
    {
    }
    public int OnIncapacitated(obj_id self, obj_id attacker) throws InterruptedException
    {
        obj_id station = getObjIdObjVar(self, "coa2.rebel.relay_station");
        obj_id player = getObjIdObjVar(station, "coa2.rebel.playerId");
        setObjVar(player, "coa2.rebel.station_key", 1);
        string_id message = new string_id("theme_park/alderaan/act2/shared_rebel_missions", "access_key_received");
        sendSystemMessage(player, message);
        messageTo(station, "unlockStation", null, 0, false);
        return SCRIPT_CONTINUE;
    }
}
