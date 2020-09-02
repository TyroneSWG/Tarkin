package script.theme_park.dungeon.avatar_platform;

import script.obj_id;

public class avatar_controller extends script.base_script
{
    public avatar_controller()
    {
    }
    public int OnHearSpeech(obj_id self, obj_id speaker, String text) throws InterruptedException
    {
        if (isGod(speaker))
        {
            String[] parse = split(text, ' ');
            if (parse[0].equals("avatar"))
            {
                obj_id structure = getTopMostContainer(speaker);
                if (parse[1].equals("spawn"))
                {
                    messageTo(self, "beginSpawn", null, 5.0f, false);
                }
            }
            if (parse[1].equals("reset"))
            {
                messageTo(self, "msgSpaceDungeonCleanup", null, 5.0f, false);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
