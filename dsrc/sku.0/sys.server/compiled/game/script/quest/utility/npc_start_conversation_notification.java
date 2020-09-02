package script.quest.utility;

import script.dictionary;
import script.location;
import script.obj_id;

public class npc_start_conversation_notification extends script.base_script
{
    public npc_start_conversation_notification()
    {
    }
    public int locationRequest(obj_id self, dictionary params) throws InterruptedException
    {
        if (params != null)
        {
            obj_id source = params.getObjId("source");
            if (source != null)
            {
                String questName = params.getString("quest_name");
                if (questName != null)
                {
                    location myLocation = getLocation(self);
                    if (myLocation != null)
                    {
                        dictionary locationResponseParameters = new dictionary();
                        locationResponseParameters.put("quest_name", questName);
                        locationResponseParameters.put("location", myLocation);
                        locationResponseParameters.put("source", self);
                        messageTo(source, "receiveLocationResponse", locationResponseParameters, 0.0f, false);
                        LOG("newquests", "npc_start_conversation - locationRequest() - sending location " + myLocation + " to " + source);
                    }
                    else 
                    {
                        LOG("newquests", "npc_start_conversation - locationRequest() - could not determine my location, no response will be sent");
                    }
                }
                else 
                {
                    LOG("newquests", "npc_start_conversation - locationRequest() - received a location request without a quest name, no response will be sent");
                }
            }
            else 
            {
                LOG("newquests", "npc_start_conversation - locationRequest() - received a location request without a source object id, no response will be sent");
            }
        }
        else 
        {
            LOG("newquests", "npc_start_conversation - locationRequest() - received a location request without parameters");
        }
        return SCRIPT_CONTINUE;
    }
    public int OnStartNpcConversation(obj_id self, obj_id player) throws InterruptedException
    {
        String objvarname = "quest." + player;
        if (hasObjVar(self, objvarname))
        {
            String playerQuestName = getStringObjVar(self, objvarname);
            removeObjVar(self, objvarname);
            dictionary npcStartConversationParameters = new dictionary();
            npcStartConversationParameters.put("source", self);
            npcStartConversationParameters.put("quest_name", playerQuestName);
            messageTo(player, "npcStartConversationNotification", npcStartConversationParameters, 0.0f, false);
            LOG("newquests", "npc_start_conversation - OnStartNpcConversation() - sending npcStartCnversationNotification message to " + player);
        }
        else 
        {
            LOG("newquests", "npc_start_conversation - OnNpcStartConversation() - could not retrieve obvjar " + objvarname);
        }
        return SCRIPT_CONTINUE;
    }
}
