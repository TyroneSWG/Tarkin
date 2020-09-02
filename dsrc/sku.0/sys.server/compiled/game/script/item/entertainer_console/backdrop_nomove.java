package script.item.entertainer_console;

import script.library.static_item;
import script.obj_id;
import script.string_id;

public class backdrop_nomove extends script.base_script
{
    public backdrop_nomove()
    {
    }
    public static final string_id MOVED_BACKDROP = new string_id("spam", "backdrop_moved");
    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnAboutToBeTransferred(obj_id self, obj_id destContainer, obj_id transferer) throws InterruptedException
    {
        if (!isGod(transferer))
        {
            String staticName = static_item.getStaticItemName(self);
            if (staticName.startsWith("item_generated_backdrop_"))
            {
                sendSystemMessage(transferer, MOVED_BACKDROP);
                return SCRIPT_OVERRIDE;
            }
        }
        sendSystemMessage(transferer, "God mode: Backdrop picked up - only allowed in god mode", null);
        return SCRIPT_CONTINUE;
    }
}
