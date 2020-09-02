package script.npc.celebrity;

import script.library.*;
import script.obj_id;
import script.string_id;

public class nym extends script.base_script
{
    public nym()
    {
    }
    public static final String CONVO = "celebrity/nym";
    public int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "Nym");
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        attachScript(self, "npc.converse.npc_converse_menu");
        return SCRIPT_CONTINUE;
    }
    public int OnStartNpcConversation(obj_id self, obj_id speaker) throws InterruptedException
    {
        String questInfo = "tell_me_quest";
        if (ai_lib.isInCombat(self) || ai_lib.isInCombat(speaker))
        {
            return SCRIPT_OVERRIDE;
        }
        if (hasObjVar(speaker, "nym.nym.finished"))
        {
            string_id message = new string_id(CONVO, "your_the_best");
            chat.chat(self, message);
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(speaker, "nym.needsPrize"))
        {
            giveNymsReward(self, speaker);
        }
        if (hasObjVar(speaker, "nym"))
        {
            if (hasObjVar(speaker, "nym.openedGrenade") && hasObjVar(speaker, "nym.openedComputer"))
            {
                obj_id drive = utils.getItemPlayerHasByTemplate(speaker, "object/tangible/loot/quest/nym_hard_drive.iff");
                obj_id imggc = utils.getItemPlayerHasByTemplate(speaker, "object/tangible/loot/quest/nym_imggc.iff");
                if (drive != null && imggc != null)
                {
                    destroyObject(drive);
                    destroyObject(imggc);
                }
                removeObjVar(speaker, "nym");
                setObjVar(speaker, "nym.nym.finished", 1);
                string_id message = new string_id(CONVO, "youre_done");
                chat.chat(self, message);
                giveNymsReward(self, speaker);
                return SCRIPT_OVERRIDE;
            }
        }
        if (hasObjVar(speaker, "nym.nym.retiredGuy") || hasObjVar(speaker, "nym.nym.researchlab"))
        {
            questInfo = "tell_me_again";
        }
        if (hasObjVar(speaker, "nym.nym.gavedrive"))
        {
            string_id greeting = new string_id(CONVO, "wheres_imggcu");
            string_id response[] = new string_id[1];
            response[0] = new string_id(CONVO, "working_on_it");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        if (hasObjVar(speaker, "nym.nym.gaveimggcu"))
        {
            string_id greeting = new string_id(CONVO, "wheres_drive");
            string_id response[] = new string_id[1];
            response[0] = new string_id(CONVO, "working_on_it");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        if (hasObjVar(speaker, "nym.nym.everyone"))
        {
            string_id greeting = new string_id(CONVO, "hello_again");
            string_id response[] = new string_id[1];
            response[0] = new string_id(CONVO, questInfo);
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        else if (hasObjVar(speaker, "nym.nym.kole") && hasObjVar(speaker, "nym.nym.jinkins"))
        {
            setObjVar(speaker, "nym.nym.everyone", 1);
            removeObjVar(speaker, "nym.nym.nym");
            removeObjVar(speaker, "nym.nym.jinkins");
            removeObjVar(speaker, "nym.nym.kole");
            string_id greeting = new string_id(CONVO, "hello_again");
            string_id response[] = new string_id[1];
            response[0] = new string_id(CONVO, questInfo);
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        else if (hasObjVar(speaker, "nym.nym.jinkins"))
        {
            string_id greeting = new string_id(CONVO, "hello_again");
            string_id response[] = new string_id[2];
            response[0] = new string_id(CONVO, questInfo);
            response[1] = new string_id(CONVO, "who_is_kole");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        else if (hasObjVar(speaker, "nym.nym.kole"))
        {
            string_id greeting = new string_id(CONVO, "hello_again");
            string_id response[] = new string_id[2];
            response[0] = new string_id(CONVO, questInfo);
            response[1] = new string_id(CONVO, "who_is_jinkins");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        else if (hasObjVar(speaker, "nym.nym.nym"))
        {
            string_id greeting = new string_id(CONVO, "hello_again");
            string_id response[] = new string_id[3];
            response[0] = new string_id(CONVO, questInfo);
            response[1] = new string_id(CONVO, "who_is_jinkins");
            response[2] = new string_id(CONVO, "who_is_kole");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        else 
        {
            string_id greeting = new string_id(CONVO, "first_time_hello");
            string_id response[] = new string_id[2];
            response[0] = new string_id(CONVO, questInfo);
            response[1] = new string_id(CONVO, "quienne_esta");
            npcStartConversation(speaker, self, "celebConvo", greeting, response);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String convo, obj_id speaker, string_id response) throws InterruptedException
    {
        String questInfo = "tell_me_quest";
        if (hasObjVar(speaker, "nym.nym.FIX_THIS") || hasObjVar(speaker, "nym.nym.FIX_THIS_TOO"))
        {
            questInfo = "tell_me_again";
        }
        setObjVar(speaker, "nym.nym.nym", 1);
        if ((response.getAsciiId()).equals("quienne_esta"))
        {
            string_id message = new string_id(CONVO, "yo_es_nym");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "quienne_esta"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "who_is_kole"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "who_is_jinkins"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("tell_me_quest"))
        {
            string_id message = new string_id(CONVO, "quest_info");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "tell_me_quest"));
            if (!hasObjVar(speaker, "nym.jinkins.done"))
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, "jinkins_story"));
            }
            if (!hasObjVar(speaker, "nym.kole.done"))
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, "koles_story"));
            }
            if (hasObjVar(speaker, "nym.jinkins.done"))
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, "imggcu"));
            }
            if (hasObjVar(speaker, "nym.kole.done"))
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, "imperial_bribe"));
            }
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("tell_me_again"))
        {
            string_id message = new string_id(CONVO, "quest_info_again");
            npcSpeak(speaker, message);
            if (!hasObjVar(speaker, "nym.jinkins.done"))
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, "jinkins_story"));
            }
            if (!hasObjVar(speaker, "nym.kole.done"))
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, "koles_story"));
            }
            if (hasObjVar(speaker, "nym.jinkins.done"))
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, "imggcu"));
            }
            if (hasObjVar(speaker, "nym.kole.done"))
            {
                npcAddConversationResponse(speaker, new string_id(CONVO, "imperial_bribe"));
            }
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "tell_me_again"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("who_is_kole"))
        {
            string_id message = new string_id(CONVO, "kole_is");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "who_is_kole"));
            setObjVar(speaker, "nym.nym.kole", 1);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("who_is_jinkins"))
        {
            string_id message = new string_id(CONVO, "jinkins_is");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "who_is_jinkins"));
            setObjVar(speaker, "nym.nym.jinkins", 1);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("koles_story"))
        {
            string_id message = new string_id(CONVO, "kole_quest_info");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "koles_story"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "ill_see_kole"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("jinkins_story"))
        {
            string_id message = new string_id(CONVO, "jinkins_quest_info");
            npcSpeak(speaker, message);
            npcRemoveConversationResponse(speaker, new string_id(CONVO, "jinkins_story"));
            npcAddConversationResponse(speaker, new string_id(CONVO, "ill_see_jinkins"));
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("ill_see_kole"))
        {
            string_id message = new string_id(CONVO, "something_funny_kole");
            npcSpeak(speaker, message);
            npcEndConversation(speaker);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("working_on_it"))
        {
            string_id message = new string_id(CONVO, "get_to_it_chief");
            npcSpeak(speaker, message);
            npcEndConversation(speaker);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("ill_see_jinkins"))
        {
            string_id message = new string_id(CONVO, "something_funny_jinkins");
            npcSpeak(speaker, message);
            npcEndConversation(speaker);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("imperial_bribe"))
        {
            if (hasObjVar(speaker, "nym.jinkins.done") && hasObjVar(speaker, "nym.kole.done"))
            {
                string_id message = new string_id(CONVO, "retired_officer");
                npcSpeak(speaker, message);
                npcRemoveConversationResponse(speaker, new string_id(CONVO, "imperial_bribe"));
                npcAddConversationResponse(speaker, new string_id(CONVO, "ill_see_officer"));
                return SCRIPT_CONTINUE;
            }
            else 
            {
                string_id message = new string_id(CONVO, "help_others");
                npcSpeak(speaker, message);
                npcEndConversation(speaker);
                return SCRIPT_CONTINUE;
            }
        }
        if ((response.getAsciiId()).equals("imggcu"))
        {
            if (hasObjVar(speaker, "nym.jinkins.done") && hasObjVar(speaker, "nym.kole.done"))
            {
                string_id message = new string_id(CONVO, "lab_info");
                npcSpeak(speaker, message);
                npcRemoveConversationResponse(speaker, new string_id(CONVO, "imggcu"));
                npcAddConversationResponse(speaker, new string_id(CONVO, "ill_go_lab"));
                return SCRIPT_CONTINUE;
            }
            else 
            {
                string_id message = new string_id(CONVO, "help_others");
                npcSpeak(speaker, message);
                npcEndConversation(speaker);
                return SCRIPT_CONTINUE;
            }
        }
        if ((response.getAsciiId()).equals("ill_see_officer"))
        {
            string_id message = new string_id(CONVO, "go_to_officer");
            npcSpeak(speaker, message);
            setObjVar(speaker, "nym.nym.retired", 1);
            if (!hasScript(speaker, "theme_park.nym.player"))
            {
                attachScript(speaker, "theme_park.nym.player");
            }
            messageTo(speaker, "goSeeRetired", null, 2, true);
            npcSpeak(speaker, message);
            npcEndConversation(speaker);
            setObjVar(speaker, "nym.nym.retiredGuy", 1);
            npcEndConversation(speaker);
            return SCRIPT_CONTINUE;
        }
        if ((response.getAsciiId()).equals("ill_go_lab"))
        {
            string_id message = new string_id(CONVO, "go_lab");
            npcSpeak(speaker, message);
            setObjVar(speaker, "nym.nym.lab", 1);
            if (!hasScript(speaker, "theme_park.nym.player"))
            {
                attachScript(speaker, "theme_park.nym.player");
            }
            messageTo(speaker, "goResearchLab", null, 2, true);
            npcSpeak(speaker, message);
            npcEndConversation(speaker);
            setObjVar(speaker, "nym.nym.researchlab", 1);
            npcEndConversation(speaker);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public int OnGiveItem(obj_id self, obj_id item, obj_id player) throws InterruptedException
    {
        if (hasObjVar(player, "nym.nym.finished"))
        {
            return SCRIPT_OVERRIDE;
        }
        String gaveMe = getTemplateName(item);
        if (gaveMe.equals("object/tangible/loot/quest/nym_imggc.iff"))
        {
            if (hasObjVar(player, "nym.nym.gavedrive"))
            {
                removeObjVar(player, "nym");
                setObjVar(player, "nym.nym.finished", 1);
                string_id message = new string_id(CONVO, "youre_done");
                giveNymsReward(self, player);
                chat.chat(self, message);
                return SCRIPT_OVERRIDE;
            }
            else 
            {
                setObjVar(player, "nym.nym.gaveimggcu", 1);
                string_id message = new string_id(CONVO, "now_drive");
                chat.chat(self, message);
                return SCRIPT_OVERRIDE;
            }
        }
        else if (gaveMe.equals("object/tangible/loot/quest/nym_hard_drive.iff"))
        {
            if (hasObjVar(player, "nym.nym.gaveimggcu"))
            {
                removeObjVar(player, "nym");
                setObjVar(player, "nym.nym.finished", 1);
                string_id message = new string_id(CONVO, "youre_done");
                chat.chat(self, message);
                giveNymsReward(self, player);
                return SCRIPT_OVERRIDE;
            }
            else 
            {
                setObjVar(player, "nym.nym.gavedrive", 1);
                string_id message = new string_id(CONVO, "now_imggcu");
                chat.chat(self, message);
                return SCRIPT_OVERRIDE;
            }
        }
        else 
        {
            string_id message = new string_id(CONVO, "whats_this");
            chat.chat(self, message);
            return SCRIPT_CONTINUE;
        }
    }
    public void giveNymsReward(obj_id self, obj_id player) throws InterruptedException
    {
        obj_id playerInv = utils.getInventoryContainer(player);
        obj_id prize = weapons.createWeapon("object/weapon/ranged/carbine/carbine_nym_slugthrower.iff", playerInv, rand(0.8f, 1.1f));
        if (!badge.hasBadge(player, "bdg_thm_park_nym_badge"))
        {
            badge.grantBadge(player, "bdg_thm_park_nym_badge");
            return;
        }
        if (canPutIn(prize, playerInv) != CEC_SUCCESS)
        {
            destroyObject(prize);
            string_id come_back = new string_id("generic_convo", "inv_full");
            npcSpeak(player, come_back);
            npcEndConversation(player);
            setObjVar(player, "nym.needsPrize", 1);
            return;
        }
        else 
        {
            if (hasObjVar(player, "nym.needsPrize"))
            {
                removeObjVar(player, "nym.needsPrize");
            }
            setObjVar(player, "nym.nym.finished", 1);
            sendSystemMessage(player, "A gift has been placed in your backpack.", null);
        }
        return;
    }
}
