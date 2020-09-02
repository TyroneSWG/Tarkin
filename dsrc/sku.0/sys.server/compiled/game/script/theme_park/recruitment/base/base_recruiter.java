package script.theme_park.recruitment.base;

import script.library.ai_lib;
import script.obj_id;
import script.string_id;

public class base_recruiter extends script.base_script
{
    public base_recruiter()
    {
    }
    public static final String DATA_ITEM_CONVO = "data_item";
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        if (!isObjectPersisted(self))
        {
            persistObject(self);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        stop(self);
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        return SCRIPT_CONTINUE;
    }
    public int OnStartNpcConversation(obj_id self, obj_id speaker) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String convo, obj_id player, string_id response) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
}
