package script.theme_park.stp;

import script.*;
/**
 *
 * @author Roachie
 */
public class stp_sign extends script.base_script {
    public stp_sign() {
    }
    
    public int OnAttach(obj_id self) throws InterruptedException
    {
        String sign_name = getStringObjVar(self, "stp.sign.name");
        if (sign_name.equals("city"))
        {
            setName(self, "a sign");
        }
        else
        {
            setName(self, sign_name);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        String sign_name = getStringObjVar(self, "stp.sign.name");
        if (sign_name.equals("city"))
        {
            setName(self, "a sign");
        }
        else
        {
            setName(self, sign_name);
        }
        return SCRIPT_CONTINUE;
    }
}
