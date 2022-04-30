package me.gamercoder215.mobchip.ai.behavior;

/**
 * Represents a temporary one-time behavior
 */
public abstract class Behavior implements Cloneable {

    protected Behavior() {}    

    /**
     * Gets the NMS Handle of this Behaivor.
     * @return Handle of this Behavior
     */
    @SuppressWarnings("rawtypes")
    public abstract net.minecraft.world.entity.ai.behavior.Behavior getHandle();

}