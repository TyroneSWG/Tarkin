package script.theme_park.stp;

import script.*;
/**
 *
 * @author Roachie
 */
public class stp_cantina_access extends script.base_script {
    public stp_cantina_access() {
    }
    
    public int OnAboutToReceiveItem(obj_id self, obj_id destinationCell, obj_id transferrer, obj_id item) throws InterruptedException
    {
        
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id r1 = getCellId(self, "back_hallway");
        obj_id r2 = getCellId(self, "back_entrance");
        if (destinationCell != r1 || destinationCell != r2)
        {
            return SCRIPT_CONTINUE;
        }
        int access_var = getIntObjVar(item, "stp.cantina_access");
        if (access_var != 1)
        {
            sendSystemMessageTestingOnly(item, "You do not have access to this part of the cantina.");
            return SCRIPT_OVERRIDE;
        }
        else 
        {
            if (!hasObjVar(item, "stp.cantina_message_shown"))
            {
                sendSystemMessageTestingOnly(item, "You now have access to these rooms.");
                setObjVar(item, "stp.cantina_message_shown", 1);
            }
            return SCRIPT_CONTINUE;
        }
    }
}
