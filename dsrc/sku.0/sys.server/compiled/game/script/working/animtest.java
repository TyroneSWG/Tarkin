package script.working;

import script.dictionary;
import script.library.ai_lib;
import script.library.anims;
import script.library.sui;
import script.library.utils;
import script.menu_info;
import script.obj_id;

public class animtest extends script.base_script
{
    public animtest()
    {
    }
    public static final String[] ANIMATIONS_ALL = 
    {
        anims.PLAYER_ACCEPT_AFFECTION,
        anims.PLAYER_BECKON,
        anims.PLAYER_BEG,
        anims.PLAYER_BELLY_LAUGH,
        anims.PLAYER_BOW2,
        anims.PLAYER_BOW3,
        anims.PLAYER_BOW4,
        anims.PLAYER_BOW5,
        anims.PLAYER_CELEBRATE,
        anims.PLAYER_CELEBRATE1,
        anims.PLAYER_CHECK_WRIST_DEVICE,
        anims.PLAYER_CLAP_ROUSING,
        anims.PLAYER_COUGH_POLITE,
        anims.PLAYER_COVER_EARS_MOCKING,
        anims.PLAYER_COVER_MOUTH,
        anims.PLAYER_CURTSEY,
        anims.PLAYER_CURTSEY1,
        anims.PLAYER_DOOR_KNOCK,
        anims.PLAYER_DOOR_POUND,
        anims.PLAYER_EMBARRASSED,
        anims.PLAYER_FLEX_BICEPS,
        anims.PLAYER_FORCE_ABSORB,
        anims.PLAYER_FORCE_BLAST,
        anims.PLAYER_FORCE_CHANNEL,
        anims.PLAYER_FORCE_CHOKE,
        anims.PLAYER_FORCE_DAMPEN,
        anims.PLAYER_FORCE_DESTRUCTION,
        anims.PLAYER_FORCE_ILLUSION,
        anims.PLAYER_FORCE_LIGHTNING,
        anims.PLAYER_FORCE_PERSUASION,
        anims.PLAYER_FORCE_PROTECTION,
        anims.PLAYER_FORCE_PULL,
        anims.PLAYER_FORCE_PUSH,
        anims.PLAYER_FORCE_STRENGTH,
        anims.PLAYER_FORCE_THROW,
        anims.PLAYER_GESTICULATE_WILDLY,
        anims.PLAYER_HAIR_FLIP,
        anims.PLAYER_HANDS_ABOVE_HEAD,
        anims.PLAYER_HANDS_BEHIND_HEAD,
        anims.PLAYER_HEAVY_COUGH_VOMIT,
        anims.PLAYER_HOLD_NOSE,
        anims.PLAYER_HUG_SELF,
        anims.PLAYER_IMPLORE,
        anims.PLAYER_KISS_BLOW_KISS,
        anims.PLAYER_LAUGH_CACKLE,
        anims.PLAYER_LAUGH_POINTING,
        anims.PLAYER_LAUGH_TITTER,
        anims.PLAYER_LISTEN,
        anims.PLAYER_NOD_HEAD_MULTIPLE,
        anims.PLAYER_NOD_HEAD_ONCE,
        anims.PLAYER_OFFER_AFFECTION,
        anims.PLAYER_PAT_ABDOMEN,
        anims.PLAYER_POINT_ACCUSINGLY,
        anims.PLAYER_POINT_AWAY,
        anims.PLAYER_POINT_DOWN,
        anims.PLAYER_POINT_FORWARD,
        anims.PLAYER_POINT_LEFT,
        anims.PLAYER_POINT_RIGHT,
        anims.PLAYER_POINT_TO_SELF,
        anims.PLAYER_POINT_UP,
        anims.PLAYER_POSE_PROUDLY,
        anims.PLAYER_POUND_FIST_CHEST,
        anims.PLAYER_POUND_FIST_PALM,
        anims.PLAYER_REFUSE_OFFER_AFFECTION,
        anims.PLAYER_REFUSE_OFFER_FORMAL,
        anims.PLAYER_RUB_BELLY,
        anims.PLAYER_RUB_CHIN_THOUGHTFUL,
        anims.PLAYER_SALUTE1,
        anims.PLAYER_SALUTE2,
        anims.PLAYER_SCRATCH_HEAD,
        anims.PLAYER_SEARCH,
        anims.PLAYER_SHAKE_HEAD_DISGUST,
        anims.PLAYER_SHAKE_HEAD_NO,
        anims.PLAYER_SHIVER,
        anims.PLAYER_SHRUG_HANDS,
        anims.PLAYER_SHRUG_SHOULDERS,
        anims.PLAYER_SHUSH,
        anims.PLAYER_SIGH_DEEPLY,
        anims.PLAYER_SLIT_THROAT,
        anims.PLAYER_SLOW_DOWN,
        anims.PLAYER_SLUMP_HEAD,
        anims.PLAYER_SMACK_SELF,
        anims.PLAYER_SMELL_AIR,
        anims.PLAYER_SMELL_ARMPIT,
        anims.PLAYER_SNAP_FINGER1,
        anims.PLAYER_SNAP_FINGER2,
        anims.PLAYER_SNEEZE,
        anims.PLAYER_STAMP_FEET,
        anims.PLAYER_STANDING_PLACATE,
        anims.PLAYER_STANDING_RAISE_FIST,
        anims.PLAYER_STOP,
        anims.PLAYER_TAP_HEAD,
        anims.PLAYER_TAUNT1,
        anims.PLAYER_TAUNT2,
        anims.PLAYER_TAUNT3,
        anims.PLAYER_WAVE_FINGER_WARNING,
        anims.PLAYER_WAVE_HAIL,
        anims.PLAYER_WAVE_ON_DIRECTING,
        anims.PLAYER_WAVE_ON_DISMISSING,
        anims.PLAYER_WAVE1,
        anims.PLAYER_WAVE2,
        anims.PLAYER_WEEPING,
        anims.PLAYER_WHISPER,
        anims.PLAYER_YAWN
    };
    public int OnAttach(obj_id self) throws InterruptedException
    {
        if (isPlayer(self))
        {
            debugSpeakMsg(self, "ERROR: This script must be attached to a humanoid NPC");
            return SCRIPT_OVERRIDE;
        }
        else 
        {
            String skeleton = dataTableGetString("datatables/ai/species.iff", ai_lib.aiGetSpecies(self), "Skeleton");
            if (!skeleton.equals("human"))
            {
                debugSpeakMsg(self, "ERROR: This script must be attached to a humanoid NPC");
                return SCRIPT_OVERRIDE;
            }
        }
        setName(self, "Animation Tester");
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        setInvulnerable(self, true);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (isPlayer(self))
        {
            return SCRIPT_OVERRIDE;
        }
        else 
        {
            String skeleton = dataTableGetString("datatables/ai/species.iff", ai_lib.aiGetSpecies(self), "Skeleton");
            if (!skeleton.equals("human"))
            {
                return SCRIPT_OVERRIDE;
            }
        }
        if (isIdValid(player))
        {
            if (utils.hasScriptVar(player, "anim_test.testing"))
            {
                int old_pid = utils.getIntScriptVar(player, "anim_test.testing");
                forceCloseSUIPage(old_pid);
                utils.removeScriptVar(player, "anim_test.testing");
            }
        }
        String title = "Animation Testing Menu";
        String prompt = "The following is a list of standing idle animations that can be performed by humanoid NPCs in a script.\n\n";
        prompt += "To have an NPC perform an animation, use this function:\n";
        prompt += "doAnimationAction(obj_id npc, string animation);\n\n";
        prompt += "Please choose an animation to test:";
        int pid = sui.listbox(self, player, prompt, sui.OK_CANCEL, title, ANIMATIONS_ALL, "animationAll", true, false);
        debugSpeakMsg(self, "PID is " + pid);
        if (pid > -1)
        {
            utils.setScriptVar(player, "anim_test.testing", pid);
        }
        return SCRIPT_CONTINUE;
    }
    public int animationAll(obj_id self, dictionary params) throws InterruptedException
    {
        if (params == null || params.isEmpty())
        {
            debugSpeakMsg(self, "<DEBUG> Message passed with no parameters");
            return SCRIPT_CONTINUE;
        }
        int idx = sui.getListboxSelectedRow(params);
        int btn = sui.getIntButtonPressed(params);
        obj_id player = sui.getPlayerId(params);
        if ((btn == sui.BP_CANCEL) || (btn == sui.BP_OK && idx < 0))
        {
            if (isIdValid(player))
            {
                if (utils.hasScriptVar(player, "anim_test.testing"))
                {
                    utils.removeScriptVar(player, "anim_test.testing");
                }
            }
            return SCRIPT_CONTINUE;
        }
        String title = "Animation Testing Menu";
        String prompt = "Now playing: " + ANIMATIONS_ALL[idx] + "\n\n";
        prompt += "To have an NPC perform this animation, use the function:\n";
        prompt += "doAnimationAction(npc, \"" + ANIMATIONS_ALL[idx] + "\");\n\n";
        prompt += "Please choose an animation to test:";
        int pid = sui.listbox(self, player, prompt, sui.OK_CANCEL, title, ANIMATIONS_ALL, "animationAll", true, false);
        debugSpeakMsg(self, "PID is " + pid);
        if (pid > -1)
        {
            utils.setScriptVar(player, "anim_test.testing", pid);
        }
        doAnimationAction(self, ANIMATIONS_ALL[idx]);
        return SCRIPT_CONTINUE;
    }
}
