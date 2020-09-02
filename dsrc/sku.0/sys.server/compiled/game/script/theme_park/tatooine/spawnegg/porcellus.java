package script.theme_park.tatooine.spawnegg;

import script.dictionary;
import script.obj_id;

public class porcellus extends script.base_script
{
    public porcellus()
    {
    }
    public static final String SCRIPTPATH = "theme_park.tatooine.";
    public static final String TEMPLATE = "object/creature/npc/theme_park/generic_human_male.iff";
    public static final String[] SCRIPTS = 
    {
        "max_rebo_quest_1.porcellus"
    };
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if (!hasObjVar(self, "theme_park.spawn_egg_npc"))
        {
            messageTo(self, "makeNPC", null, 1, true);
        }
        return SCRIPT_CONTINUE;
    }
    public int NPCDestroyed(obj_id self, dictionary params) throws InterruptedException
    {
        messageTo(self, "makeNPC", null, 1, true);
        return SCRIPT_CONTINUE;
    }
    public int makeNPC(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id myNPC = createObjectAt(TEMPLATE, self);
        setObjVar(myNPC, "theme_park.spawn_egg", self);
        attachScript(myNPC, "theme_park.spawn_egg.npc_death");
        for (int i = 0; i <= SCRIPTS.length - 1; i++)
        {
            attachScript(myNPC, SCRIPTPATH + SCRIPTS[i]);
        }
        setObjVar(self, "theme_park.spawn_egg_npc", myNPC);
        return SCRIPT_CONTINUE;
    }
}
