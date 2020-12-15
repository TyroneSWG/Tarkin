package script.theme_park.stp.spawning;

import script.*;
import script.library.create;
import script.library.sui;
/**
 *
 * @author Roachie
 */
public class area extends script.base_script {
    public area() {
    }
    /*public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.SERVER_MENU10, new string_id("spawner", "setup"));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.SERVER_MENU10)
        {
            //setupSpawner();
            sui.inputbox(self, player, "template, invuln(bool)", "handleSetup");
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public int randomRange = rand(1,25);
    public int OnAttach(obj_id self) throws InterruptedException
    {
        if (!hasObjVar(self, "stp.spawner"))
        {
            setObjVar(self, "stp.spawner", true);
            setName(self, "Static Spawner");
        }
        if (hasObjVar(self, "stp.spawner.type"))
        {
           String template = getStringObjVar(self, "stp.spawner.type");
           boolean isInvuln = getBooleanObjVar(self, "stp.spawner.invuln");
           spawn(self, template, isInvuln);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        this.OnAttach(self);
        return SCRIPT_CONTINUE;
    }
    public void spawn(obj_id self, String template, boolean invuln) throws InterruptedException
    {
        location egg = getLocation(self);
        egg.x = egg.x + randomRange;
        egg.y = egg.y + randomRange;
        obj_id spawnedCreature = create.createCreature(template, egg, false);
        if (invuln)
        {
            setInvulnerable(spawnedCreature, true);
        }
    }
    public int handleTatooine(obj_id self, dictionary params) throws InterruptedException
    {
	if (params == null || params.isEmpty())
        {
            return SCRIPT_CONTINUE;
        }
	obj_id player = sui.getPlayerId(params))
           
        return SCRIPT_CONTINUE;
    }
    */
}
