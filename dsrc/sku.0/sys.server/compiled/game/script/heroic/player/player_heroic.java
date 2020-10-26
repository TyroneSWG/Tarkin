package script.heroic.player;

import script.library.group;
import script.library.pclib;
import script.library.ai_lib;
import script.library.chat;
import script.library.utils;
import script.location;
import script.obj_id;

public class player_heroic extends script.base_script {

    public player_heroic()
    {
    }

    public static int OnGroupDisband(obj_id self, obj_id groupId) throws InterruptedException
    {
        if (groupId == null)
        {
            return SCRIPT_CONTINUE;
        }
        location disbandLocation = getLocation(self);
        if (!disbandLocation.area.equals("adventure3") || (!disbandLocation.area.equals("dungeon2")))
        {
            return SCRIPT_CONTINUE;
        }
        setObjVar(self, "heroic.return_location.id_" + self, disbandLocation);
        return SCRIPT_CONTINUE;
    }

    public static int OnAddedToGroup(obj_id self)
    {
        int gSize = group.getGroupSize(self);
        setContentLock(self, gSize);
        return SCRIPT_CONTINUE;
    }

    public static int OnDeath(obj_id self, obj_id killer) throws InterruptedException
    {
        location buildout_loc = getLocation(self);
        location respawn_instance_s01 = new location(-4900.0f, 0.0f, 5038.0f);
        location respawn_instance_s05 = new location(30.87f, 0.0f, -80.75f);
        if (isPlayer(killer))
        {
            return SCRIPT_CONTINUE;
        }
        if (getBuildoutAreaName(buildout_loc.x, buildout_loc.z).equals("blacksun_s01"))
        {
            setLocation(self, respawn_instance_s01);
            playMusic(self, "sound/utinni.snd");
            pclib.playerRevive(self);
        }
        if (getBuildoutAreaName(buildout_loc.x, buildout_loc.z).equals("treehouse_s02"))
        {
            setLocation(self, respawn_instance_s05);
            playMusic(self, "sound/utinni.snd");
            pclib.playerRevive(self);
        }
        sendSystemMessageTestingOnly(self, "You have been revived.");
        utils.setScriptVar(self, "heroics.killerId", killer);
        utils.setScriptVar(self, "heroics.location", getCurrentSceneName());
        return SCRIPT_CONTINUE;
    }

    public static int setContentLock(obj_id self, int size)
    {
        if (size < 8)
        {
            if (size == 8)
            {
                setObjVar(self, "heroic.player.allowed_run", "heroics");
            }
            if (size >= 4)
            {
                setObjVar(self, "heroic.player.allowed_run", "dungeons");
            }
            if (size >= 2)
            {
                setObjVar(self, "heroic.player.allowed_run", "mini_dungeons");
            }
            if (size < 2)
            {
                setObjVar(self, "heroic.player.allowed_run", "too_small");
            }
        }
        return SCRIPT_CONTINUE;
    }

    public static int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id breacher)
        throws InterruptedException
    {
        if (isPlayer(breacher))
        {
            if (volumeName.equals("boss_elite_tyvek"))
            {
                obj_id[] enemies = getCreaturesInRange(self, 15.0f);
                for (obj_id creatures : enemies)
                {
                    chat.chat(creatures, "Form Up!");
                }
                obj_id[] boss_mob = getAllObjectsWithTemplate(getLocation(self), 120.0f,
                    "object/mobile/dressed_biologist_08.iff");
                if (!utils.hasScriptVar(self, "heroics_new.tyvek_breached"))
                {
                    utils.setScriptVar(self, "heroics_new.tyvek_breached", "true");
                    for (obj_id adds : boss_mob)
                    {
                        ai_lib.spawnAdd(adds, "heroic_black_sun_normal", getLocation(adds));
                        setName(adds, "a Black Sun Enforcer");
                    }
                }
            }
            if (volumeName.equals("boss_mad_scientist"))
            {
                // spooky implementation
                obj_id[] boss_mob = getAllObjectsWithTemplate(getLocation(self), 120.0f,
                    "object/mobile/dressed_biologist_08.iff");
                if (!utils.hasScriptVar(self, "heroics_new.mad_scientist_breached"))
                {
                    utils.setScriptVar(self, "heroics_new.mad_scientist_breached", "true");
                    for (obj_id adds : boss_mob)
                    {
                        ai_lib.spawnAdd(adds, "heroic_blacksun", getLocation(adds));
                        setName(adds, "a Black Sun Enforcer");
                    }
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
