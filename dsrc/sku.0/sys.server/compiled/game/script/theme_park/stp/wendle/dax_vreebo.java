package script.theme_park.stp.wendle;

import script.*;
import script.library.ai_lib;
import script.library.chat;
/**
 *
 * @author Roachie
 */
public class dax_vreebo extends script.base_script {
    public dax_vreebo() {
    }
    public int OnAttach(obj_id self) throws InterruptedException
    {
        grantSkill(self, "social_entertainer_novice");
        setName(self, "Dax Vreebo");
        setPalcolorCustomVarClosestColor(self, "/private/index_color_1", 183, 85, 85, 0);
        ai_lib.setMood(self, "themepark_music_2");
        chat.chat(self, "Feel the blues!");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        grantSkill(self, "social_entertainer_novice");
        setName(self, "Dax Vreebo");
        setPalcolorCustomVarClosestColor(self, "/private/index_color_1", 183, 85, 85, 0);
        ai_lib.setMood(self, "themepark_music_2");
        chat.chat(self, "Feel the blues!");
        return SCRIPT_CONTINUE;
    }
}
