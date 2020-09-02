package script.theme_park.dungeon.corvette;

import script.dictionary;
import script.library.chat;
import script.obj_id;
import script.string_id;

public class pdroid extends script.base_script
{
    public pdroid()
    {
    }
    public static final String MSGS = "dungeon/corvette";
    public int woohoo(obj_id self, dictionary params) throws InterruptedException
    {
        string_id woohoo = new string_id(MSGS, "pdroid_congrats");
        chat.chat(self, woohoo);
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        attachScript(self, "npc.converse.npc_converse_menu");
        setInvulnerable(self, true);
        return SCRIPT_CONTINUE;
    }
    public int OnStartNpcConversation(obj_id self, obj_id speaker) throws InterruptedException
    {
        if (!hasObjVar(self, "fixed"))
        {
            string_id instructions = new string_id(MSGS, "pdroid_instructions");
            chat.chat(self, instructions);
        }
        else 
        {
            string_id excellent = new string_id(MSGS, "pdroid_excellent");
            chat.chat(self, excellent);
        }
        return SCRIPT_CONTINUE;
    }
}
