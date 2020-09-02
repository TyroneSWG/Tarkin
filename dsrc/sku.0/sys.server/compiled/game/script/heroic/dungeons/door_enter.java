package script.heroic.dungeons;

import script.menu_info;
import script.menu_info_data;
import script.menu_info_types;
import script.obj_id;
import script.dictionary;
import script.string_id;

import script.library.group;
import script.library.sui;

/**
 *
 * @author TyroneSWG
 */
public class door_enter extends script.base_script {
	public door_enter() {
	}

	public static String[] LOCS = { "\\#ed8d16(DEMO) Black Sun Bunker", "\\#dd1234(DEMO) Trandoshan Tumble",
			"\\#dd1234(DEMO) Raid on Jabba's Palace", "\\#dd1234(DEMO) Showdown on Endor" };
	public static final String STF = "city/city";

	public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException {
		menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
		int menu = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("heroics_new", "travel_to_instance"));
		return SCRIPT_CONTINUE;
	}

	public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException {
		if (item == menu_info_types.ITEM_USE) {
			if (!group.isGrouped(player) && (!isGod(player))) {
				sendSystemMessageTestingOnly(player, "You must be grouped to enter this instance.");
				return SCRIPT_CONTINUE;
			}
			String title = "Instance Selection";
			String prompt = "Please select an instance to travel to.";
			if (!hasScript(player, "heroic.player.player_heroic")) {
				attachScript(player, "heroic.player.player_heroic");
			}
			sui.listbox(self, player, prompt, sui.OK_CANCEL, title, LOCS, "handleWhichLoc", true, false);
		}
		return SCRIPT_CONTINUE;
	}

	public int handleWhichLoc(obj_id self, dictionary params) throws InterruptedException {
		if (params == null || params.isEmpty()) {
			return SCRIPT_CONTINUE;
		}
		obj_id player = sui.getPlayerId(params);
		int btn = sui.getIntButtonPressed(params);
		int idx = sui.getListboxSelectedRow(params);
		if (btn == sui.BP_CANCEL) {
			return SCRIPT_CONTINUE;
		}
		if (idx == -1 || idx > LOCS.length) {
			return SCRIPT_CONTINUE;
		}
		switch (idx) {
		case 0:
			warpPlayer(player, "adventure3", -5000.0f, 0.0f, 5000.0f, null, 0, 0, 0, "", true);
			break;
		case 1:
			sendSystemMessageTestingOnly(player, "Selection not available at this time.");
			// warpPlayer(player, "adventure3", 0.0f, 0.0f, 0.0f, null, 0, 0, 0, "", true);
			break;
		case 2:
			sendSystemMessageTestingOnly(player, "Selection not available at this time.");
			break;
		case 3:
			sendSystemMessageTestingOnly(player, "Selection not available at this time.");
			break;
		default:
			sendSystemMessageTestingOnly(player, "Something has occured...");
			return SCRIPT_CONTINUE;
		}
		return SCRIPT_CONTINUE;
	}
}