// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself 
// with honor and integrity at all times.
// I will not lie, cheat, or steal, 
// nor will I accept the actions of those who do.
// -- Daniel Almeida (adaniel1)
package elvtr;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author Daniel Almeida
 * 
 * This is an elevator, a simple one for now...
 * 
 * Notes:
 *          I wasn't sure how to make it generic and be able
 *          to use different class's functions i.e. Person has
 *          targetFloor() but say an elevator for something else
 *          may have tarFloor() as their function.
 *          
 *          Started with the BagInterface but dropped it because
 *          I wasn't really dealing with a generic case anymore;
 *          I am going to focus on an elevator, for people, at
 *          an airport.
 *
 */
public class Elevator {
    private final int CAPACITY = 24;
    private final int MAX_WEIGHT = 2000;
    
    private Person[] currPassengers;
    private int[] floorQueue;
    private int queueUsage;
    private int currUsage;
    private int currWeight; // not accounting actual elevator mass
    private int currFloor;
    private int maxFloor;
    
    /**
     * Create an elevator, basically first installation.
     * 
     * @param mF    Building's max floor.
     */
    public Elevator(int mF) {
        // Can be unsafe to do this (client side calls)
            // this.currPassengers = (E[]) new Object[CAPACITY];
        // Would like to learn more!
        
        this.currPassengers = new Person[CAPACITY];
        this.floorQueue = new int[mF];
        
        Arrays.fill(floorQueue, -99); // I'll use -99 to mean empty
        this.queueUsage = 0;
        
        this.currUsage = 0;
        this.currWeight = 0;
        this.currFloor = 0; // ground floor
        this.maxFloor = mF; // building design dependent
    }

    /**
     * Determines how many passengers currently in this elevator.
     * 
     * @return  Number of passengers currently in elevator.
     */
    public int getUsage() {
        return this.currUsage;
    }

    /**
     * Determine if the elevator is empty.
     * 
     * @return  True if the elevator is empty.
     */
    public boolean isEmpty() {
        return this.currUsage == 0;
    }

    /**
     * This add() method will find an empty
     * spot in the array and add a new entry (add person to elevator).
     * 
     * This will return true if the addition is successful,
     * else it will return false if no space is found.
     * 
     * The addition of a person will also depend on the max weight
     * the elevator may support. Addition of a person may fail if
     * the resultant weight surpasses the elevator's limit.
     * 
     * @param newEntry  New addition to the array.
     * 
     * @return True if successful addition, else false.
     */
    public boolean add(Person newEntry) {
        if ((this.getWeight() + newEntry.getCombinedWeight()) <= MAX_WEIGHT) {
            for (int i = 0; i < CAPACITY; i++) {
                if (currPassengers[i] == null) {
                    currPassengers[i] = newEntry;
                    
                    currWeight += newEntry.getCombinedWeight();
                    currUsage++;
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Set the elevator's floor.
     * 
     * @param f     Floor number
     * 
     * @return True if set was successful
     */
    public boolean setFloor(int f) {
        if (f >= 0 && f <= this.maxFloor) {
            this.currFloor = f;
        }
        
        return this.currFloor == f;
    }

    /**
     * This remove() method will remove a person
     * from the elevator based on their target floor.
     * 
     * This WILL remove several people with the same target floor.
     * 
     * @return Number of passengers removed.
     */
    public int remove() {
        int passRemoved = 0;
        
        for (int i = passRemoved; i < CAPACITY; i++) {
            if (currPassengers[i] != null
                    && currPassengers[i].targetFloor() == currFloor) {
                currWeight -= currPassengers[i].getCombinedWeight();
                currPassengers[i] = null;
                
                passRemoved++;
                currUsage--;
            }
        }
        
        return passRemoved;
    }
    
    /**
     * Return this elevator's current weight.
     * 
     * @return  Elevator's current weight.
     */
    public int getWeight() {
        return this.currWeight;
    }
    
    /**
     * Return this elevator's current floor.
     * 
     * @return  Elevator's current floor.
     */
    public int getFloor() {
        return this.currFloor;
    }
    
    /**
     * Returns the number of queued floors.
     * 
     * @return  number of queued floors.
     */
    public int queueUsage() {
        return this.queueUsage;
    }
    
    /**
     * Queue a floor.
     * 
     * @param floor     Floor to be queued.
     * 
     * @return  True if floor has been (or is already) queued, else false.
     */
    public boolean queueFloor(int floor) {
        boolean queued = false;
        
        // if the queue is full, then this will evaluate to true
        // since all levels will be in the queue (some kid clownin')
        for (int i = 0; i < this.queueUsage(); i++) {
            if (this.floorQueue[i] == floor) {
                queued = true;
            }
        }
        
        // if all are queued, this if statement will be false
        if (queued == false) {
            if (floor >= 0 && floor < this.maxFloor) {
                this.floorQueue[this.queueUsage()] = floor;
                queueUsage++;
                queued = true;
            }
        }
        
        return queued;
    }
    
    /**
     * Dequeue floor.
     * 
     * @param floor     Floor to be dequeued.
     * 
     * @return  True if dequeue is successful (elevator arrived), else false.
     */
    public boolean dequeueFloor(int floor) {
        for (int i = 0; i < this.queueUsage(); i++) {
            if (this.floorQueue[i] == floor) {
                int lastIndex = i;
                
                // shift items down
                for (int x = i; x < this.queueUsage() - 1; x++) {
                    this.floorQueue[x] = this.floorQueue[x + 1];
                    lastIndex = x + 1;
                }
                
                // replace unused with -99
                for (int y = lastIndex; y < this.floorQueue.length; y++) {
                    this.floorQueue[y] = -99;
                }
                
                this.queueUsage--;
                return true;
            }
        }
        
        return false;
    }

    /**
     * Need this for testing.
     * 
     * @param index     Index of person
     * 
     * @return  Person requested.
     */
    public Person returnPerson(int index) {
        if (index < 0 && index >= this.maxFloor) {
            throw new ArrayIndexOutOfBoundsException();
        }
        else if (currPassengers[index] != null) {
            return currPassengers[index];
        }
        else {
            return null;
        }
    }
    
    /**
     * Return this elevator's capacity.
     * 
     * @return  This elevator's capacity.
     */
    public int getCapacity() {
        return this.CAPACITY;
    }
    
    /**
     * Return this elevator's max floor.
     * 
     * @return  Max floor.
     */
    public int getMaxFloor() {
        return this.maxFloor;
    }
    
    /**
     * Return an element from the floor queue.
     * 
     * @param   Index to be returned.
     * 
     * @return  Queue requested.
     */
    public int returnQueue(int index) {
        if (index < 0 && index >= this.maxFloor) {
            throw new ArrayIndexOutOfBoundsException();
        }
        else {
            return floorQueue[index];
        }
    }
    
    /**
     * Are the queue's hot?
     * 
     * @return  String of queues.
     */
    public String queueToString() {
        String returnString = "[";
        
        for (int i = 0; i < this.maxFloor; i++) {
            if (this.floorQueue[i] != -99) {
                returnString += ", " + Integer.toString(this.floorQueue[i]);
            }
            else {
                returnString += ", " + "-99";
            }
        }
        
        returnString = returnString.replaceFirst(Pattern.quote(", "), "");
        returnString += "]";
        
        return returnString;
    }
    
    /**
     * The TSA lookin' for someone, let's print them all
     * in a string array.
     * 
     * @return  String of elevator passengers.
     */
    public String toString() {
        String returnString = "[";
        
        for (int i = 0; i < CAPACITY; i++) {
            if (currPassengers[i] != null) {
                returnString += ", " + currPassengers[i].getName();
            }
            else {
                returnString += ", " + "null";
            }
        }
        
        
        returnString = returnString.replaceFirst(Pattern.quote(", "), "");
        returnString += "]";
        
        return returnString;
    }
}
