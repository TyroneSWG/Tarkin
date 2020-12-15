/**
 *
 * @author TyroneSWG
 */
package script.heroic.cloning;

import script.obj_id;
import script.library.create;
import script.library.utils;
import script.location;
import script.dictionary;

public class cloner extends script.base_script {

    public cloner()
    {
    }

    public static int OnInitialize(obj_id self) throws InterruptedException
    {
        setupCloneDroid(self);
        location cloneLoc = getWorldLocation(self);
        location cloneRespawn = getLocation(self);
        obj_id planet = getPlanetByName(cloneLoc.area);
        if (isIdValid(planet))
        {
            dictionary params = new dictionary();
            params.put("id", self);
            params.put("name", "Safe Zone");
            params.put("buildout", "");
            params.put("areaId", self);
            params.put("loc", cloneLoc);
            params.put("respawn", cloneRespawn);
            params.put("type", 0);
            messageTo(planet, "registerCloningFacility", params, 1.0f, false);
            LOG("HEROICS_NEW", "Heroic Cloning setup for " + self + ".");
        }
        return SCRIPT_CONTINUE;
    }

    public static int setupCloneDroid(obj_id self) throws InterruptedException
    {
        location goodSpot = getLocation(self);
        goodSpot.x = goodSpot.x - 4;
        goodSpot.y = goodSpot.y - 4;
        obj_id healyboi = create.object("clone_droid", goodSpot);
        setObjVar(healyboi, "heroics.childId", healyboi);
        return SCRIPT_CONTINUE;
    }
}
