package script.theme_park.stp;

import script.*;
import script.library.utils;
import script.library.factions;
/**
 *
 * @author Roachie
 */

public class chapter_house extends script.base_script {
    public static String JEDI = "object/building/player/player_house_jedi_meditation_room.iff";
    public static String SITH = "object/building/player/player_house_sith_meditation_room.iff";
    public chapter_house() {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        if (utils.getTemplateName(self).equals(JEDI));
        {
            setName(self, "Jedi Chapter House");
            factions.setFaction(self, "Rebel");
        }
        if (utils.getTemplateName(self).equals(SITH));
        {
            setName(self, "Sith Chapter House");
            factions.setFaction(self, "Imperial");
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAboutToReceiveItem(obj_id self, obj_id destinationCell, obj_id transferrer, obj_id item) throws InterruptedException
    {
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        if (!hasSkill(item, "class_forcesensitive_phase1_novice"))
        {
            sendSystemMessageTestingOnly(item, "The Force has blocked your entry.");
            return SCRIPT_OVERRIDE;
        }
        else 
        {
            sendSystemMessageTestingOnly(item, "The Force has guided you in.");
            return SCRIPT_CONTINUE;
        }
    }
}
