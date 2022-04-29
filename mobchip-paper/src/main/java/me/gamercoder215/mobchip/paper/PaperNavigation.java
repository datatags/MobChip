package me.gamercoder215.mobchip.paper;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import me.gamercoder215.mobchip.ai.navigation.EntityNavigation;
import me.gamercoder215.mobchip.ai.navigation.NavigationNode;
import me.gamercoder215.mobchip.ai.navigation.NavigationPath;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.navigation.PathNavigation;

/**
 * Paper/Purpur Implementation of Entity Navigation
 */
public final class PaperNavigation implements EntityNavigation {

    private final PathNavigation handle;
    
    private int speedMod;
    private int range;
    private List<NavigationNode> points;
    private BlockPos finalPos;

    protected PaperNavigation(PathNavigation handle) {
        this.handle = handle;
        this.points = new ArrayList<>();

        this.speedMod = 1;
        this.range = Integer.MAX_VALUE;
    }

    @Override
    public double getSpeedModifier() {
        return this.speedMod;
    }

    @Override
    public void setSpeedModifier(double mod) throws IllegalArgumentException {
        if (mod > Integer.MAX_VALUE) throw new IllegalArgumentException("Must be integer");
        this.speedMod = (int) Math.floor(mod);
    }

    @Override
    public EntityNavigation recompute() {
        this.handle.recomputePath();
        return this;
    }

    @Override
    public EntityNavigation addPoint(@NotNull NavigationNode point) {
        this.points.add(point);
        return this;
    }

    @Override
    public EntityNavigation addPoint(int index, @NotNull NavigationNode point) {
        this.points.add(index, point);
        return this;
    }

    @Override
    public EntityNavigation removePoint(@NotNull NavigationNode point) {
        this.points.remove(point);
        return this;
    }

    @Override
    public EntityNavigation removePoint(@NotNull int index) {
        this.points.remove(index);
        return this;
    }

    @Override
    @NotNull
    public NavigationPath buildPath() {
        return new PaperPath(handle.createPath(finalPos, range, speedMod));
    }

    @Override
    public EntityNavigation setFinalPoint(@NotNull NavigationNode node) {
        this.finalPos = new BlockPos(node.getX(), node.getY(), node.getZ());
        return this;
    }

    @Override
    public EntityNavigation setRange(int range) {
        this.range = range;
        return this;
    }
    
}
