package script.systems.elevator;

import script.dictionary;
import script.location;
import script.obj_id;

public class button_check_4 extends script.base_script
{
    public button_check_4()
    {
    }
    public int OnAboutToReceiveItem(obj_id self, obj_id destinationCell, obj_id transferrer, obj_id item) throws InterruptedException
    {
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id home = getTopMostContainer(self);
        String building = getTemplateName(home);
        if (building.equals(""))
        {
            return SCRIPT_OVERRIDE;
        }
        dictionary buttons = new dictionary();
        buttons.put("home", home);
        buttons.put("item", item);
        if (building.equals("object/building/tatooine/lucky_despot.iff"))
        {
            messageTo(self, "luckyDespot", buttons, 1, true);
        }
        return SCRIPT_CONTINUE;
    }
    public int luckyDespot(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id home = params.getObjId("home");
        obj_id item = params.getObjId("item");
        obj_id elevator = getObjIdObjVar(home, "terminal7");
        obj_id elevator2 = getObjIdObjVar(home, "terminal8");
        if (exists(elevator))
        {
            obj_id cell = getContainedBy(elevator);
            if (cell != self)
            {
                destroyObject(elevator);
                obj_id elevatorCell = getCellId(home, "elevator04");
                location here = getLocation(home);
                here.x = 12.04f;
                here.y = 7.01f;
                here.z = -16.44f;
                here.cell = elevatorCell;
                obj_id elevatorTerminalA = createObject("object/tangible/terminal/terminal_elevator_up.iff", here);
                setYaw(elevatorTerminalA, 90);
                setObjVar(home, "terminal7", elevatorTerminalA);
            }
        }
        else 
        {
            obj_id elevatorCell = getCellId(home, "elevator04");
            location here = getLocation(home);
            here.x = 12.04f;
            here.y = 7.01f;
            here.z = -16.44f;
            here.cell = elevatorCell;
            obj_id elevatorTerminalA = createObject("object/tangible/terminal/terminal_elevator_up.iff", here);
            setYaw(elevatorTerminalA, 90);
            setObjVar(home, "terminal7", elevatorTerminalA);
        }
        if (exists(elevator2))
        {
            obj_id cell = getContainedBy(elevator2);
            if (cell != self)
            {
                destroyObject(elevator2);
                obj_id elevatorCell = getCellId(home, "elevator04");
                location here = getLocation(home);
                here.x = 12.04f;
                here.y = 15.01f;
                here.z = -16.44f;
                here.cell = elevatorCell;
                obj_id elevatorTerminalB = createObject("object/tangible/terminal/terminal_elevator_down.iff", here);
                setYaw(elevatorTerminalB, 90);
                setObjVar(home, "terminal8", elevatorTerminalB);
            }
        }
        else 
        {
            obj_id elevatorCell = getCellId(home, "elevator04");
            location here = getLocation(home);
            here.x = 12.04f;
            here.y = 15.01f;
            here.z = -16.44f;
            here.cell = elevatorCell;
            obj_id elevatorTerminalB = createObject("object/tangible/terminal/terminal_elevator_down.iff", here);
            setYaw(elevatorTerminalB, 90);
            setObjVar(home, "terminal8", elevatorTerminalB);
        }
        return SCRIPT_CONTINUE;
    }
}
