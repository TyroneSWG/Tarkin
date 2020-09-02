package script.npc.faction_recruiter;

import script.library.factions;
import script.library.utils;
import script.obj_id;

public class faction_item extends script.base_script
{
    public faction_item()
    {
    }
    public int OnAboutToBeTransferred(obj_id self, obj_id destContainer, obj_id transferer) throws InterruptedException
    {
        if (isPlayer(destContainer))
        {
            if (!factions.canUseFactionItem(destContainer, self))
            {
                return SCRIPT_OVERRIDE;
            }
        }
        if (isAPlayerAppearanceInventoryContainer(destContainer))
        {
            obj_id owner = getContainedBy(destContainer);
            if (!isIdValid(owner))
            {
                return SCRIPT_OVERRIDE;
            }
            if (!factions.canUseFactionItem(owner, self))
            {
                return SCRIPT_OVERRIDE;
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        if (isIdValid(self) && exists(self))
        {
            int idx = utils.getValidAttributeIndex(names);
            if (idx == -1)
            {
                return SCRIPT_CONTINUE;
            }
            if (hasObjVar(self, "faction_recruiter.faction"))
            {
                names[idx] = "faction_restriction";
                String attrib = getStringObjVar(self, "faction_recruiter.faction");
                attribs[idx] = attrib;
                idx++;
                if (idx >= names.length)
                {
                    return SCRIPT_CONTINUE;
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
