package de.honeypot.technica.util;

import net.minecraft.util.math.AxisAlignedBB;

/**
 * Created by gast2 on 04.09.17.
 */
public class AABBHelper {

    public static AxisAlignedBB turnRight(AxisAlignedBB box){

        return new AxisAlignedBB(1-box.maxZ, box.minY, box.minX, 1-box.minZ, box.maxY, box.maxX);

    }

}
