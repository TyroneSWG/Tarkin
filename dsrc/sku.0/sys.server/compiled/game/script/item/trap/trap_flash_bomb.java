package script.item.trap;

import script.dictionary;
import script.library.ai_lib;
import script.library.utils;
import script.obj_id;
import script.string_id;

public class trap_flash_bomb extends script.item.trap.trap_base
{
    public trap_flash_bomb()
    {
    }
    public static final int TRAP_DIFF = 40;
    public static final string_id SID_SYS_EFFECT = new string_id("trap/trap", "trap_flash_bomb_effect");
    public int OnAttach(obj_id self) throws InterruptedException
    {
        if (!hasObjVar(self, "droid_trap"))
        {
            setObjVar(self, "areaEffect", true);
            setObjVar(self, "trapDiff", TRAP_DIFF);
            setObjVar(self, "trapName", "flash_bomb");
            setCount(self, 5);
        }
        return SCRIPT_CONTINUE;
    }
    public int trapHit(obj_id self, dictionary params) throws InterruptedException
    {
        if (params == null)
        {
            return super.trapHit(self, params);
        }
        obj_id target = params.getObjId("target");
        obj_id player = params.getObjId("player");
        if ((target == null) || (target == obj_id.NULL_ID))
        {
            return super.trapHit(self, params);
        }
        if (ai_lib.isNpc(target) || ai_lib.isAndroid(target))
        {
            return super.trapHit(self, params);
        }
        int hits = utils.getIntScriptVar(self, "hits");
        if (hits == 0)
        {
            sendSystemMessage(player, SID_SYS_EFFECT);
            float chance = params.getFloat("chance");
            grantTrapXP(player, target, 1.6f);
        }
        hits++;
        utils.setScriptVar(self, "hits", hits);
        if (!hasObjVar(self, "droid_trap"))
        {
            if (!ai_lib.isInCombat(target))
            {
                startCombat(target, player);
            }
        }
        else 
        {
            if (!ai_lib.isInCombat(target))
            {
                startCombat(target, self);
            }
        }
        return super.trapHit(self, params);
    }
}
