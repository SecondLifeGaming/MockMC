package org.mockmc.mockmc.block.data;

import com.google.common.base.Preconditions;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.CalibratedSculkSensor;
import org.jetbrains.annotations.NotNull;
import java.util.Set;

// 1. Remove 'implements Cloneable' here if possible.
// SculkSensorDataMock likely already implements it via the Bukkit BlockData interface.
public class CalibratedSculkSensorDataMock extends SculkSensorDataMock implements CalibratedSculkSensor {

    public CalibratedSculkSensorDataMock(@NotNull Material material) {
        super(material);
    }

    /**
     * This is your Copy Constructor.
     * The linter prefers this over the clone() mechanism.
     */
    protected CalibratedSculkSensorDataMock(@NotNull CalibratedSculkSensorDataMock other) {
        super(other);
    }

    @Override
    public @NotNull BlockFace getFacing() {
        return this.get(BlockDataKey.FACING);
    }

    @Override
    public void setFacing(@NotNull BlockFace blockFace) {
        Preconditions.checkArgument(blockFace != null, "blockFace cannot be null!");
        Preconditions.checkArgument(blockFace.isCartesian() && blockFace.getModY() == 0,
                "Invalid face, only cartesian horizontal face are allowed for this property!");
        this.set(BlockDataKey.FACING, blockFace);
    }

    @Override
    public @NotNull Set<BlockFace> getFaces() {
        return this.getLimitationValue(BlockDataLimitation.Type.FACES);
    }

    /**
     * If the linter still warns about the 'clone' implementation,
     * it is because it wants you to use the constructor directly.
     * We keep this ONLY because the Bukkit API requires it.
     */
    @Override
    @SuppressWarnings({ "squid:S2975", "java:S1182" }) // Suppress: "clone" should be replaced by copy constructor
    public @NotNull CalibratedSculkSensorDataMock clone() {
        return new CalibratedSculkSensorDataMock(this);
    }
}
