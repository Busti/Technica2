package de.honeypot.technica.tile.conveyor;

import de.honeypot.technica.util.modenum.EnumConTurn;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * Created by Chloroplast on 15.09.2017.
 */

@SideOnly(Side.CLIENT)
public class ConveyorGeometry {

    private final static Vector2f MID = new Vector2f(0.5f, 0.5f);

    private final static ConveyorGeometry GEOM_STRAIGHT_NORTH = new ConveyorGeometry(0.5f, 0.5f, 1.0f, 0.0f);
    private final static ConveyorGeometry GEOM_STRAIGHT_EAST  = new ConveyorGeometry(0.0f, 1.0f, 0.5f, 0.5f);
    private final static ConveyorGeometry GEOM_STRAIGHT_SOUTH = new ConveyorGeometry(0.5f, 0.5f, 0.0f, 1.0f);
    private final static ConveyorGeometry GEOM_STRAIGHT_WEST  = new ConveyorGeometry(1.0f, 0.0f, 0.5f, 0.5f);

    private final static ConveyorGeometry GEOM_LEFT_NORTH = new ConveyorGeometry(0.0f, 0.5f, 0.5f, 0.0f);
    private final static ConveyorGeometry GEOM_LEFT_EAST  = new ConveyorGeometry(0.5f, 1.0f, 0.0f, 0.5f);
    private final static ConveyorGeometry GEOM_LEFT_SOUTH = new ConveyorGeometry(1.0f, 0.5f, 0.5f, 1.0f);
    private final static ConveyorGeometry GEOM_LEFT_WEST  = new ConveyorGeometry(0.5f, 0.0f, 1.0f, 0.5f);

    private final static ConveyorGeometry GEOM_RIGHT_NORTH = new ConveyorGeometry(1.0f, 0.5f, 0.5f, 0.0f);
    private final static ConveyorGeometry GEOM_RIGHT_EAST  = new ConveyorGeometry(0.5f, 1.0f, 1.0f, 0.5f);
    private final static ConveyorGeometry GEOM_RIGHT_SOUTH = new ConveyorGeometry(0.0f, 0.5f, 0.5f, 1.0f);
    private final static ConveyorGeometry GEOM_RIGHT_WEST  = new ConveyorGeometry(0.5f, 0.0f, 0.0f, 0.5f);

    public static ConveyorGeometry get(EnumConTurn turn, EnumFacing face){

        switch(turn){

            case STRAIGHT:
            case STRAIGHT_STILL:

                switch(face){
                    case DOWN:
                    case UP:
                    case NORTH: return GEOM_STRAIGHT_NORTH;
                    case SOUTH: return GEOM_STRAIGHT_SOUTH;
                    case WEST:  return GEOM_STRAIGHT_WEST;
                    case EAST:  return GEOM_STRAIGHT_EAST;
                }

            case LEFT:
            case LEFT_STILL:

                switch(face){
                    case DOWN:
                    case UP:
                    case NORTH: return GEOM_LEFT_NORTH;
                    case SOUTH: return GEOM_LEFT_SOUTH;
                    case WEST:  return GEOM_LEFT_WEST;
                    case EAST:  return GEOM_LEFT_EAST;
                }

            case RIGHT:
            case RIGHT_STILL:

                switch(face){
                    case DOWN:
                    case UP:
                    case NORTH: return GEOM_RIGHT_NORTH;
                    case SOUTH: return GEOM_RIGHT_SOUTH;
                    case WEST:  return GEOM_RIGHT_WEST;
                    case EAST:  return GEOM_RIGHT_EAST;
                }
        }

        return null;

    }



    private final Vector2f startPos;
    private final Vector2f endPos;

    private final Vector2f startDir;
    private final Vector2f endDir;

    private final Vector2f temp1 = new Vector2f();
    private final Vector2f temp2 = new Vector2f();

    private ConveyorGeometry(float x0, float x1, float y0, float y1){

        startPos = new Vector2f(x0, y0);
        endPos   = new Vector2f(x1, y1);

        startDir = new Vector2f();
        endDir   = new Vector2f();

        MID.sub(startPos, startDir);
        MID.sub(endPos,   endDir);
    }

    /**
     * !!NOT!! THREADSAFE
     *
     * @param target
     * @param pos
     * @return
     */

    public Vector3f getBlockPosition(Vector3f target, float pos){

        /*

        temp1 = startPos + x*startDir
        temp2 = endPos + (1-x)*endDir

        res = temp1 * (1-x) + temp2 * x


         */

        float neg1pos = 1-pos;

        startDir.mul(pos, temp1);
        endDir.mul(neg1pos, temp2);

        temp1.mul(neg1pos);
        temp2.mul(pos);

        temp1.add(temp2);

        target.set(temp1.x, target.y, temp2.y);

        return target;
    }


}