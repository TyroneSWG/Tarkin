package script.test;

import script.*;

public class jwatson_mining_asteroid_dynamic extends script.base_script
{
    public jwatson_mining_asteroid_dynamic()
    {
    }
    public int OnShipInternalDamageOverTimeRemoved(obj_id self, int chassisSlot, float damageRate, float damageThreshold) throws InterruptedException
    {
        obj_id pilot = getPilotId(self);
        if (pilot != null)
        {
            sendSystemMessageTestingOnly(pilot, "jwatson_ship IDOT removed slot=" + chassisSlot + ", damageRate=" + damageRate + ", threshold=" + damageThreshold);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnShipWasHit(obj_id self, obj_id attacker, int weaponIndex, boolean isMissile, int missileType, int chassisSlot, boolean isPlayerAutoTurret, float hitLocationX_o, float hitLocationY_o, float hitLocationZ_o) throws InterruptedException
    {
        obj_id attackingPilot = getPilotId(attacker);
        location attackingLocation = getLocation(attacker);
        vector attackingPosition_w = new vector(attackingLocation.x, attackingLocation.y, attackingLocation.z);
        transform selfTransform = getTransform_o2w(self);
        vector attackingLocation_o = selfTransform.rotateTranslate_p2l(attackingPosition_w);
        int weaponCrc = getShipComponentCrc(attacker, weaponIndex + ship_chassis_slot_type.SCST_weapon_first);
        if (getShipComponentDescriptorWeaponIsMissile(weaponCrc))
        {
            vector currentVelocity_w = getDynamicMiningAsteroidVelocity(self);
            vector directionToAttacker_w = new vector(attackingPosition_w);
            directionToAttacker_w = directionToAttacker_w.subtract(selfTransform.getPosition_p());
            directionToAttacker_w = directionToAttacker_w.normalize();
            directionToAttacker_w = directionToAttacker_w.multiply(20.0f);
            currentVelocity_w = currentVelocity_w.add(directionToAttacker_w);
            float velocityMagnitude = currentVelocity_w.magnitude();
            float MAX_VELOCITY_MAGNITUDE = 50.0f;
            if (velocityMagnitude > MAX_VELOCITY_MAGNITUDE)
            {
                float velocityMultiplier = MAX_VELOCITY_MAGNITUDE / velocityMagnitude;
                currentVelocity_w = currentVelocity_w.multiply(velocityMultiplier);
            }
            sendSystemMessageTestingOnly(attackingPilot, "*** asteroid dynamic TRACTOR PULSE add=" + directionToAttacker_w);
            setDynamicMiningAsteroidVelocity(self, currentVelocity_w);
            return SCRIPT_CONTINUE;
        }
        int maxHitpoints = getMaxHitpoints(self);
        int oldHitpoints = getHitpoints(self);
        setHitpoints(self, oldHitpoints - 10);
        int newHitpoints = getHitpoints(self);
        sendSystemMessageTestingOnly(attackingPilot, "hit asteroid dynamic " + newHitpoints + "/" + maxHitpoints);
        if (newHitpoints <= 0)
        {
            sendSystemMessageTestingOnly(attackingPilot, "*** asteroid dynamic DESTROYED");
            handleShipDestruction(self, 1.0f);
        }
        else 
        {
            vector hitLocation_o = new vector(hitLocationX_o, hitLocationY_o, hitLocationZ_o);
            notifyShipHit(self, attackingLocation_o, hitLocation_o, ship_hit_type.HT_chassis, 0.5f, 1.0f);
            if ((newHitpoints < (maxHitpoints / 2)) && (oldHitpoints >= (maxHitpoints / 2)))
            {
                if (random.rand() > 0.5f)
                {
                    location selfLocation = getLocation(self);
                    obj_id spawnDynamicAsteroid = createObject("object/ship/asteroid/mining_asteroid_dynamic_nugget.iff", selfLocation);
                    vector currentVelocity_w = getDynamicMiningAsteroidVelocity(self);
                    sendSystemMessageTestingOnly(attackingPilot, "*** uber-nugget-roid SPAWNED!");
                    vector spawnDirection_w = currentVelocity_w.cross(vector.randomUnit());
                    spawnDirection_w = spawnDirection_w.normalize();
                    spawnDirection_w = spawnDirection_w.multiply(15.0f);
                    sendSystemMessageTestingOnly(attackingPilot, "spawn dir =" + spawnDirection_w);
                    currentVelocity_w = currentVelocity_w.add(spawnDirection_w);
                    setDynamicMiningAsteroidVelocity(spawnDynamicAsteroid, currentVelocity_w);
                    currentVelocity_w = currentVelocity_w.subtract(spawnDirection_w);
                    currentVelocity_w = currentVelocity_w.subtract(spawnDirection_w);
                    setDynamicMiningAsteroidVelocity(self, currentVelocity_w);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
