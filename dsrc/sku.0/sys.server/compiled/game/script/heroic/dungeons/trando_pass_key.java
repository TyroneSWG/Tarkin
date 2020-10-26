package script.heroic.dungeons;

import script.obj_id;

public class trando_pass_key extends script.base_script {

    public trando_pass_key()
    {
    }

    public int OnAboutToReceiveItem(obj_id self, obj_id destinationCell, obj_id transferrer, obj_id item)
        throws InterruptedException
    {
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        String passKey = "heroic.passkey_trando_one";
        String status = getStringObjVar(item, passKey);
        if (status.equals("locked") || status.equals(null))
        {
            String warning = "You must have a passkey to enter this building.";
            sendSystemMessageTestingOnly(item, warning);
            return SCRIPT_OVERRIDE;
        } else
        {
            sendSystemMessageTestingOnly(item, "Passkey accepted.");
            return SCRIPT_CONTINUE;
        }
    }

}
