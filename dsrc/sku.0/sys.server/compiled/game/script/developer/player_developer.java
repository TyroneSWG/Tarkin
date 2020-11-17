package script.developer;

import java.util.StringTokenizer;
import script.*;
import script.library.sui;
import script.library.utils;
/**
 *
 * @author Roachie
 */
public class player_developer extends script.base_script {
    public player_developer() {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        if (!isGod(self))
        {
            detachScript(self, "developer.player_developer");
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public int cmdSetCount(obj_id self, obj_id target, String params, float defaultTime) throws InterruptedException
    {
        StringTokenizer st = new java.util.StringTokenizer(params);
        String command = "";
        if (st.hasMoreTokens())
        {
            command = st.nextToken();
        }
        if (command.equals("-target"))
        {
            int count = utils.stringToInt(st.nextToken());
            if (count > 1000)
            {
                systemMsg(self, "That number is too high, try again please.");
                return SCRIPT_CONTINUE;
            }
            setCount(getIntendedTarget(self), count);
            return SCRIPT_CONTINUE;
        }
        if (command.equals("-oid"))
        {
            setCount(utils.stringToObjId(st.nextToken()), utils.stringToInt(st.nextToken()));
        }
        if (command.equals("-maxstack"))
        {
            setCount(getIntendedTarget(self), 1000);
        }
        return SCRIPT_CONTINUE;
    }
    public int cmdServerShell(obj_id self, obj_id target, String params, float defaultTime) throws InterruptedException
    {
        String title = "Shell Process Output";
        StringTokenizer st = new java.util.StringTokenizer(params);
        String command = "";
        if (st.hasMoreTokens())
        {
            command = st.nextToken();
        }
        System.out.println("Developer " + getFirstName(self) + " has used the " + command + " shell!");
        if (command.equals(""))
        {
            systemMsg(self, "Compilers: [java, tpf, miff, tab, crc] | Git: [update_dsrc, update_data]");
            return SCRIPT_CONTINUE;
        }
        else if ((toLower(command)).equals("snd"))
        {
            play2dNonLoopingMusic(self, "sound/" + st.nextToken() + ".snd");
        }
        else if ((toLower(command)).equals("string_crc"))
        {
            systemMsg(self, Integer.toString(getStringCrc(st.nextToken())));
        }
        else if ((toLower(command)).equals("dsrc"))
        {
            String prompt = runShell("/home/swg/swg-main/update_dsrc.sh");
            sui.msgbox(self, self, prompt, sui.OK_CANCEL, title, "noHandler");
        }
        else if ((toLower(command)).equals("java"))
        {
            String prompt = runShell("/home/swg/swg-main/build_java.sh");
            sui.msgbox(self, self, prompt, sui.OK_CANCEL, title, "noHandler");
        }
        else if ((toLower(command)).equals("tab"))
        {
            String prompt = runShell("/home/swg/swg-main/build_tab.sh");
            sendConsoleCommand(command, target);
            sui.msgbox(self, self, prompt, sui.OK_CANCEL, title, "noHandler");
        }
        else if ((toLower(command)).equals("tpf"))
        {
            String prompt = runShell("/home/swg/swg-main/build_tpf.sh");
            sui.msgbox(self, self, prompt, sui.OK_CANCEL, title, "noHandler");
        }
        else if ((toLower(command)).equals("miff"))
        {
            String prompt = runShell("/home/swg/swg-main/build_miff.sh");
            sui.msgbox(self, self, prompt, sui.OK_CANCEL, title, "noHandler");
        }
        else if ((toLower(command)).equals("crc"))
        {
            String prompt = runShell("/home/swg/swg-main/build_crc.sh");
            sui.msgbox(self, self, prompt, sui.OK_CANCEL, title, "noHandler");
        }
        else if ((toLower(command)).equals("sql"))
        {
            String prompt = runShell("/home/swg/swg-main/proload_templates.sh");
            sui.msgbox(self, self, prompt, sui.OK_CANCEL, title, "noHandler");
        }
        else if ((toLower(command)).equals("data"))
        {
            String prompt = runShell("/home/swg/swg-main/update_data.sh");
            sui.msgbox(self, self, prompt, sui.OK_CANCEL, title, "noHandler");
        }
        return SCRIPT_CONTINUE;
    }
    public String runShell(String file) throws InterruptedException
    {
       return system_process.runAndGetOutput(file);
    }
    public int cmdCloneObj(obj_id self, obj_id target, String params, float defaultTime) throws InterruptedException
    {
        obj_id pInv = utils.getInventoryContainer(self);
        utils.cloneObject(target, pInv);
        systemMsg(self, "Attempting to clone object " + target);
        return SCRIPT_CONTINUE;
    }
}
