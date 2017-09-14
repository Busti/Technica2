package de.honeypot.technica.tile.conveyor;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ConveyorGraph {


    /**
     *       CGE         CGE
     *
     *      *----*      *----*
     *      |    |      |    |
     *      |    |      |    |
     *      |    |      |    |
     *      |    |      |    |
     *      *----*      *----*
     *           |      ^____/
     *           \_____/  ELEMENT_LENGTH
     *             offset
     *
     */

    final static int    MAX_ELEMENTS_ON_BLOCK = 4;
    final static double ELEMENT_LENGTH = MAX_ELEMENTS_ON_BLOCK / 16d;

    private List<TileConveyorBase> nodes = new ArrayList<>();
    private LinkedList<ConveyorGraphElement> items = new LinkedList<>();

    /** DEBUG THREAD TEST **/
    private static LinkedList<Thread> DEBUG_THREADLIST = new LinkedList<>();

    private int  length;


    public void addItem(double position, ItemStack item){
        DEBUG();


        double offsetAbsoluteCurrent = 0;
        double offsetAbsoluteDesired = length - position;


        Iterator<ConveyorGraphElement> iter = items.descendingIterator();
        while(iter.hasNext()){
            ConveyorGraphElement cge = iter.next();
            //double offsetAbsoluteNext

        }
    }

    private static void DEBUG(){
        Thread current = Thread.currentThread();

        synchronized (DEBUG_THREADLIST) {
            if(!DEBUG_THREADLIST.contains(current)){
                DEBUG_THREADLIST.addFirst(current);
                System.out.println("\r\n\r\n\r\nnew THREAD!!!!!\r\n"+current+"\r\n\r\n\r\n");
            }

        }
    }

}
