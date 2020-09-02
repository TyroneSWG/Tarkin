package script.theme_park.tatooine.mos_taike;

import script.dictionary;
import script.library.ai_lib;
import script.library.chat;
import script.library.utils;
import script.obj_id;
import script.string_id;

public class mayor_vip extends script.base_script
{
    public mayor_vip()
    {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        attachScript(self, "npc.converse.npc_converse_menu");
        return SCRIPT_CONTINUE;
    }
    public int OnStartNpcConversation(obj_id self, obj_id speaker) throws InterruptedException
    {
        String datatable = "datatables/theme_park/mos_taike_mayor.iff";
        String CONVO = "theme_park_mos_taike/mayor";
        int questNum = getIntObjVar(speaker, "mos_taike.mayor_quest");
        if (ai_lib.isInCombat(self) || ai_lib.isInCombat(speaker))
        {
            return SCRIPT_OVERRIDE;
        }
        if (hasObjVar(speaker, "mos_taike.mayor.vipObjId"))
        {
            obj_id courier = getObjIdObjVar(speaker, "mos_taike.mayor.vipObjId");
            if (courier == self)
            {
                String reward = "npc_takeme_" + (questNum);
                string_id message = new string_id(CONVO, reward);
                chat.chat(self, message);
                dictionary parms = new dictionary();
                parms.put("player", speaker);
                messageTo(self, "followPlayer", parms, 0, true);
                messageTo(speaker, "finishQuest", null, 0, true);
                return SCRIPT_OVERRIDE;
            }
            else 
            {
                string_id work = new string_id(CONVO, "otherescort");
                chat.chat(self, work);
                return SCRIPT_CONTINUE;
            }
        }
        else 
        {
            string_id blah = new string_id(CONVO, "dontknowyou");
            chat.chat(self, blah);
            return SCRIPT_CONTINUE;
        }
    }
    public int followPlayer(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = params.getObjId("player");
        ai_lib.aiFollow(self, player);
        return SCRIPT_CONTINUE;
    }
    public int stopFollowing(obj_id self, dictionary params) throws InterruptedException
    {
        debugSpeakMsg(self, "I'm not following you anymore.");
        obj_id player = params.getObjId("player");
        ai_lib.aiStopFollowing(self);
        String datatable = "datatables/theme_park/mos_taike_mayor.iff";
        int questNum = getIntObjVar(player, "mos_taike.mayor_quest");
        questNum = questNum + 1;
        String reward = dataTableGetString(datatable, 1, questNum - 1);
        if (reward.equals("credits"))
        {
            debugSpeakMsg(self, "Should give you money");
            removeObjVar(player, "mos_taike.mayor");
            detachScript(player, "theme_park.tatooine.mos_taike.player_escort");
            setObjVar(player, "mos_taike.mayor_quest", questNum);
            return SCRIPT_CONTINUE;
        }
        else 
        {
            obj_id playerInv = utils.getInventoryContainer(player);
            createObject(reward, playerInv, "");
            removeObjVar(player, "mos_taike.mayor");
            detachScript(player, "theme_park.tatooine.mos_taike.player_escort");
            setObjVar(player, "mos_taike.mayor_quest", questNum);
            return SCRIPT_CONTINUE;
        }
    }
}
