package script.theme_park.dungeon.geonosian_madbio_bunker;

import script.dictionary;
import script.location;
import script.obj_id;

public class boulder_trap extends script.base_script
{
    public boulder_trap()
    {
    }
    public int OnAboutToReceiveItem(obj_id self, obj_id destinationCell, obj_id transferrer, obj_id item) throws InterruptedException
    {
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id boulderRoom = getCellId(self, "hall6");
        location here = getLocation(self);
        String planet = here.area;
        location boulderLoc = new location(-62.66f, -21.80f, -169.70f, planet, boulderRoom);
        int numContents = 0;
        obj_id[] roomContents = getContents(boulderRoom);
        if (roomContents != null)
        {
            numContents = roomContents.length;
        }
        if (hasPlayer(numContents, roomContents) != true)
        {
            createObject("object/static/destructible/destructible_tato_cave_rock_lg.iff", boulderLoc);
        }
        if (!hasObjVar(getSelf(), "played"))
        {
            playClientEffectObj(item, "clienteffect/cave_in_roof.cef", item, "");
            messageTo(getSelf(), "playedAnim", null, 1, false);
        }
        return SCRIPT_CONTINUE;
    }
    public boolean hasPlayer(int numContents, obj_id[] roomContents) throws InterruptedException
    {
        int x = 0;
        boolean hadOne = false;
        while (x < numContents)
        {
            obj_id currentObject = roomContents[x];
            if (isPlayer(currentObject))
            {
                hadOne = true;
            }
            x = x + 1;
        }
        return hadOne;
    }
    public int playedAnim(obj_id self, dictionary params) throws InterruptedException
    {
        setObjVar(getSelf(), "played", 1);
        messageTo(getSelf(), "resetAnim", null, 120, false);
        return SCRIPT_CONTINUE;
    }
    public int resetAnim(obj_id self, dictionary params) throws InterruptedException
    {
        if (hasObjVar(getSelf(), "played"))
        {
            removeObjVar(getSelf(), "played");
        }
        return SCRIPT_CONTINUE;
    }
}
