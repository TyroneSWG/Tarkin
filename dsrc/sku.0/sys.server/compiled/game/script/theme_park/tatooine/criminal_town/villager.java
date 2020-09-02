package script.theme_park.tatooine.criminal_town;

import script.library.create;
import script.location;
import script.obj_id;

public class villager extends script.base_script
{
    public villager()
    {
    }
    public location getNewLocation(obj_id object, int x, int y, int z) throws InterruptedException
    {
        location newLoc = new location(getLocation(object));
        newLoc.x = x;
        newLoc.y = y;
        newLoc.z = z;
        return newLoc;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        location location1 = getNewLocation(self, -6426, 0, -7113);
        location location2 = getNewLocation(self, -6457, 0, -7064);
        location location3 = getNewLocation(self, -6512, 0, -6981);
        location location4 = getNewLocation(self, -6543, 0, -6932);
        location location5 = getNewLocation(self, -6544, 0, -6909);
        location location6 = getNewLocation(self, -6543, 0, -6834);
        location location7 = getNewLocation(self, -6588, 0, -6831);
        location location8 = getNewLocation(self, -6721, 0, -6763);
        reseed(getGameTime());
        obj_id npc1 = create.object("townsperson", location1);
        attachScript(npc1, "ai.npc.towns.townfolk");
        obj_id npc2 = create.object("townsperson", location2);
        attachScript(npc2, "ai.npc.towns.townfolk");
        obj_id npc3 = create.object("townsperson", location3);
        attachScript(npc3, "ai.npc.towns.townfolk");
        obj_id npc4 = create.object("townsperson", location4);
        attachScript(npc4, "ai.npc.towns.townfolk");
        obj_id npc5 = create.object("townsperson", location5);
        attachScript(npc5, "ai.npc.towns.townfolk");
        obj_id npc6 = create.object("townsperson", location6);
        attachScript(npc6, "ai.npc.towns.townfolk");
        obj_id npc7 = create.object("townsperson", location7);
        attachScript(npc7, "ai.npc.towns.townfolk");
        obj_id npc8 = create.object("townsperson", location8);
        attachScript(npc8, "ai.npc.towns.townfolk");
        return SCRIPT_CONTINUE;
    }
}
