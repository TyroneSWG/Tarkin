package script.systems.skills.stealth;

import script.*;
import script.library.prose;
import script.library.stealth;
import script.library.utils;

public class bioprobe extends script.base_script
{
    public bioprobe()
    {
    }
    public static final java.text.NumberFormat floatFormat = new java.text.DecimalFormat("###");
    public int msgDecaySample(obj_id self, dictionary params) throws InterruptedException
    {
        stealth.cleanComponentFromContainer(self, getContainedBy(self));
        setObjVar(self, stealth.BIO_PROBE_DECAYED, 1);
        removeObjVar(self, stealth.BIO_PROBE_TRAP_TARGET);
        obj_id player = utils.getContainingPlayer(self);
        if (!isIdValid(player))
        {
            return SCRIPT_CONTINUE;
        }
        String targetName = getStringObjVar(self, stealth.BIO_PROBE_TARGET_NAME);
        prose_package pp = prose.getPackage(new string_id("spam", "probe_decayed_target"));
        pp = prose.setTT(pp, targetName);
        sendSystemMessageProse(player, pp);
        return SCRIPT_CONTINUE;
    }
    public int OnDestroy(obj_id self) throws InterruptedException
    {
        stealth.cleanComponentFromContainer(self, getContainedBy(self));
        return SCRIPT_CONTINUE;
    }
    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs) throws InterruptedException
    {
        int free = getFirstFreeIndex(names);
        if (free == -1)
        {
            return SCRIPT_CONTINUE;
        }
        float effectiveness = getIntObjVar(self, stealth.DETECT_EFFECTIVENESS);
        float thisProbeStorage = getFloatObjVar(self, stealth.BIO_PROBE_STORAGE_TIME);
        names[free] = "sample_preserve";
        if (hasObjVar(self, stealth.BIO_PROBE_SAMPLE_TIME))
        {
            int when = getIntObjVar(self, stealth.BIO_PROBE_SAMPLE_TIME);
            int now = getGameTime();
            float timespan = thisProbeStorage - (now - when);
            if (timespan < 1)
            {
                attribs[free++] = "@spam:already_expired";
            }
            else 
            {
                if (timespan < 60)
                {
                    attribs[free++] = floatFormat.format(timespan) + " secs left";
                }
                else 
                {
                    attribs[free++] = floatFormat.format(timespan / 60) + " mins left";
                }
            }
        }
        else 
        {
            attribs[free++] = "" + floatFormat.format((thisProbeStorage / 60)) + " minutes";
        }
        names[free] = "sample_target";
        if (!hasObjVar(self, stealth.BIO_PROBE_TRAP_TARGET))
        {
            if (hasObjVar(self, stealth.BIO_PROBE_DECAYED))
            {
                attribs[free++] = "@spam:already_expired";
            }
            else 
            {
                attribs[free++] = "Not Yet Sampled";
            }
        }
        else 
        {
            attribs[free++] = "" + getStringObjVar(self, stealth.BIO_PROBE_TARGET_NAME);
        }
        utils.addClassRequirementAttributes(player, self, names, attribs, free, "");
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (!utils.isNestedWithin(self, player))
        {
            return SCRIPT_OVERRIDE;
        }
        if (!utils.testItemClassRequirements(player, self, true, ""))
        {
            return SCRIPT_CONTINUE;
        }
        if (!utils.testItemLevelRequirements(player, self, true, ""))
        {
            return SCRIPT_CONTINUE;
        }
        menu_info_data mid2 = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        if (mid2 != null)
        {
            mid2.setServerNotify(true);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (!utils.isNestedWithin(self, player))
            {
                return SCRIPT_OVERRIDE;
            }
            if (!utils.testItemClassRequirements(player, self, false, ""))
            {
                return SCRIPT_CONTINUE;
            }
            if (!utils.testItemLevelRequirements(player, self, false, ""))
            {
                return SCRIPT_CONTINUE;
            }
            obj_id target = getLookAtTarget(player);
            if (stealth.canBioProbe(player, self, target))
            {
                stealth.bioProbe(player, self, target);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
