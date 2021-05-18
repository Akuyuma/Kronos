package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

public class ChilliPepperPlant extends MaterialData implements Directional, Attachable {

  public enum ChilliPepperPlantSize {
        SMALL,
        MEDIUM,
        LARGE
    }

    public ChilliPepperPlant() {
        super(Material.CHILLI_PEPPER);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public ChilliPepperPlant(final int type) {
        super(type);
    }

    /**
     *
     * @deprecated Magic value
     */
    @Deprecated
    public ChilliPepperPlant(final int type, final byte data) {
        super(type, data);
    }

    public ChilliPepperPlant(ChilliPepperPlantSize sz) {
        this();
        setSize(sz);
    }

    public ChilliPepperPlant(ChilliPepperPlantSize sz, BlockFace dir) {
        this();
        setSize(sz);
        setFacingDirection(dir);
    }

    /**
     * Get size of plant
     *
     * @return size
     */
    public ChilliPepperPlantSize getSize() {
        switch (getData() & 0xC) {
            case 0:
                return ChilliPepperPlantSize.SMALL;
            case 4:
                return ChilliPepperPlantSize.MEDIUM;
            default:
                return ChilliPepperPlantSize.LARGE;
        }
    }

    /**
     * Set size of plant
     *
     * @param sz - size of plant
     */
    public void setSize(ChilliPepperPlantSize sz) {
        int dat = getData() & 0x3;
        switch (sz) {
            case SMALL:
                break;
            case MEDIUM:
                dat |= 0x4;
                break;
            case LARGE:
                dat |= 0x8;
                break;
        }
        setData((byte) dat);
    }

    public BlockFace getAttachedFace() {
        return getFacing().getOppositeFace();
    }

    public void setFacingDirection(BlockFace face) {
        int dat = getData() & 0xC;
        switch (face) {
            default:
            case SOUTH:
                break;
            case WEST:
                dat |= 0x1;
                break;
            case NORTH:
                dat |= 0x2;
                break;
            case EAST:
                dat |= 0x3;
                break;
        }
        setData((byte) dat);
    }

    public BlockFace getFacing() {
        switch (getData() & 0x3) {
            case 0:
                return BlockFace.SOUTH;
            case 1:
                return BlockFace.WEST;
            case 2:
                return BlockFace.NORTH;
            case 3:
                return BlockFace.EAST;
        }
        return null;
    }

    @Override
    public ChilliPepperPlant clone() {
        return (ChilliPepperPlant) super.clone();
    }

    @Override
    public String toString() {
        return super.toString() + " facing " + getFacing() + " " + getSize();
    }
	
}
