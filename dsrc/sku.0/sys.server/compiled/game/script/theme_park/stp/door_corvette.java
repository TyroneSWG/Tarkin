package script.theme_park.stp;

import script.menu_info;
import script.menu_info_data;
import script.menu_info_types;
import script.obj_id;
import script.dictionary;
import script.string_id;
import script.library.sui;
/**
 *
 * @author Roachie
 */
public class door_corvette extends script.base_script {
    public door_corvette() {
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException {
		menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
		int menu = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("item", "use"));
		return SCRIPT_CONTINUE;
	}

	public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
		if (item == menu_info_types.ITEM_USE) {
			String title = "Confirm Departure";
			String prompt = "Are you sure you want to head back to Pendath?";
			sui.msgbox(self, player, prompt, sui.OK_CANCEL, title, "handleConfirm");
		}
		return SCRIPT_CONTINUE;
	}

	public int handleConfirm(obj_id self, dictionary params) throws InterruptedException {
		if (params == null || params.isEmpty()) {
			return SCRIPT_CONTINUE;
		}
		obj_id player = sui.getPlayerId(params);
		int btn = sui.getIntButtonPressed(params);
		if (btn == sui.BP_OK) {
			warpPlayer(player, "taanab", 5277.0f, 75.0f, -4198.0f, null, 0, 0, 0, "", true);
			return SCRIPT_CONTINUE;
		}
		return SCRIPT_CONTINUE;
	}
}
