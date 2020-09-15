package script.theme_park.stp.wendle;

import script.*;
/**
 *
 * @author Roachie
 */
public class casino_building extends script.base_script {
    public casino_building() {
    }
    public int OnAboutToReceiveItem(obj_id self, obj_id destinationCell, obj_id transferrer, obj_id item) throws InterruptedException
    {
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        int gating = getIntObjVar(item, "stp.can_gamble");
        if (gating != 1)
        {
            sendSystemMessageTestingOnly(item, "It is probably best to ask around about this place before going in here.");
            return SCRIPT_OVERRIDE;
        }
        else 
        {
            return SCRIPT_CONTINUE;
        }
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setName(self, "Wendle's Casino");
        return SCRIPT_CONTINUE;
    }
}
