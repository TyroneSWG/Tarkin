package script.hub.building;

import script.*;
import script.library.create;

public class hub_main extends script.base_script {

	public hub_main() {
	}

	public static String HUB_SPAWN_TABLE = "datatable/hub/spawns.iff";
	public static String HUB_WLC_MSG = "";
	public static int TERMINATE = 1;
	public static int MAX_PLAYERS = 300;
	public static int MIN_PLAYERS_PER_BUILDOUT = 10;
	public static String HUB_SCENE = "dungeon2";

	public static int OnAttach(obj_id self) throws InterruptedException {

		//DISCORD_LOG("HUB", "Hub script attached");
		return SCRIPT_CONTINUE;
	}

	public static int OnInitialize(obj_id self) throws InterruptedException {
		if (!getConfigSetting("Hub", "enabled").equals("true")) {
			return SCRIPT_CONTINUE;
		}
		//DISCORD_LOG("HUB", "Hub Online");
		return SCRIPT_CONTINUE;
	}

	public int OnAboutToReceiveItem(obj_id self, obj_id destinationCell, obj_id transferrer, obj_id item)
			throws InterruptedException {
		if (!isPlayer(item)) {
			return SCRIPT_CONTINUE;
		}
		if (!hasObjVar(self, "hub")) {
			setObjVar(self, "hub.population", 0);
		}
		int new_count = 0;
		setObjVar(self, "hub.population", new_count++);
		attachScript(item, "hub.player.player_hub");
		return SCRIPT_CONTINUE;
	}

	public int OnAboutToLoseItem(obj_id self, obj_id destContainer, obj_id transferer, obj_id item) {
		if (!isPlayer(item)) {
			return SCRIPT_CONTINUE;
		}
		if (!hasObjVar(self, "hub")) {
			setObjVar(self, "hub.population", 0);
		}
		int lose_count = getIntObjVar(self, "hub.population");
		int count = lose_count--;
		setObjVar(self, "hub.population", count);
		detachScript(item, "hub.player.player_hub");
		return SCRIPT_CONTINUE;
	}
}
