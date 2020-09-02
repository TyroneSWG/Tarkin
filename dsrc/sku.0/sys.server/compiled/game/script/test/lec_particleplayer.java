package script.test;

import script.obj_id;

import java.util.StringTokenizer;

public class lec_particleplayer extends script.base_script
{
    public lec_particleplayer()
    {
    }
    public int OnHearSpeech(obj_id self, obj_id speaker, String text) throws InterruptedException
    {
        if (speaker != self)
        {
            return SCRIPT_CONTINUE;
        }
        text = toLower(text);
        if (text.startsWith("playparticleme"))
        {
            StringTokenizer st = new StringTokenizer(text);
            int numArgs = st.countTokens();
            if (numArgs > 2)
            {
                sendSystemMessage(self, "Incorrect number of arguments. [Syntax] playParticleMe <particle name.prt>", null);
                return SCRIPT_CONTINUE;
            }
            st.nextToken();
            String particleName = st.nextToken();
            LOG("particleP", "Playing " + particleName + " on me");
            playClientEffectObj(self, "appearance/" + particleName, self, "root");
            return SCRIPT_CONTINUE;
        }
        else if (text.startsWith("playparticletarget"))
        {
            StringTokenizer st = new StringTokenizer(text);
            int numArgs = st.countTokens();
            if (numArgs > 2)
            {
                sendSystemMessage(self, "[Syntax] playParticleTarget <particle name.prt> -- Plays the specified particle at your current look at target", null);
                return SCRIPT_CONTINUE;
            }
            st.nextToken();
            String particleName = st.nextToken();
            obj_id myTarget = getLookAtTarget(self);
            if (!isIdValid(myTarget) || myTarget == null)
            {
                sendSystemMessage(self, "Invalid target.", null);
                return SCRIPT_CONTINUE;
            }
            else 
            {
                playClientEffectObj(self, "appearance/" + particleName, myTarget, "root");
            }
        }
        else if (text.startsWith("playparticleloc"))
        {
            StringTokenizer st = new StringTokenizer(text);
            int numArgs = st.countTokens();
            if (numArgs > 2)
            {
                sendSystemMessage(self, "[Syntax] playParticleLoc <particle name.prt> -- Plays the specified particle at your current location", null);
                return SCRIPT_CONTINUE;
            }
            st.nextToken();
            String particleName = st.nextToken();
            playClientEffectLoc(self, "appearance/" + particleName, getLocation(self), 0.0f);
        }
        return SCRIPT_CONTINUE;
    }
}
