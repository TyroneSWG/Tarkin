package script.item.publish_gift;

import script.library.static_item;
import script.obj_id;
import script.string_id;

public class magic_painting_nomove extends script.base_script
{
    public magic_painting_nomove()
    {
    }
    public static final string_id MOVED_PAINTING = new string_id("spam", "magic_painting_moved");
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
            String paintingName = static_item.getStaticItemName(self);
            if (paintingName.startsWith("item_publish_gift_magic_painting_"))
            {
                sendSystemMessage(transferer, MOVED_PAINTING);
                return SCRIPT_OVERRIDE;
            }
        }
        return SCRIPT_CONTINUE;
    }
}
