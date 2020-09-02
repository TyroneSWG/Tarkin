package script.theme_park.dungeon.death_watch_bunker;

import script.library.hue;
import script.obj_id;

public class emulsion_color extends script.base_script
{
    public emulsion_color()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        String item = getTemplateName(self);
        if (item.equals("object/tangible/loot/dungeon/death_watch_bunker/emulsion_protection.iff"))
        {
            hue.setColor(self, hue.INDEX_1, 9);
        }
        else 
        {
            hue.setColor(self, hue.INDEX_1, 29);
        }
        return SCRIPT_CONTINUE;
    }
}
