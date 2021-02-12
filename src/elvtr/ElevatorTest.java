package elvtr;

import student.TestCase;

/**
 * Tests for Elevator class.
 * 
 * @author Daniel Almeida
 *
 */
public class ElevatorTest extends TestCase{
    private Elevator one;
    private Elevator two;
    
    private Person Daniel;
    private Person Mayuka;
    private Person Ryan;
    private Person Light;
    private Person Eric;
    
    /**
     * Test setUp for all tests.
     */
    public void setUp() {
        one = new Elevator(10);
        two = new Elevator(7);
        
        Daniel = new Person("Daniel", 65, 2, 45, 4);
        Mayuka = new Person("Mayuka", 71, 1, 15, 3);
        Eric = new Person("Eric", 0, 0, 0, 3);
        Ryan = new Person("Ryan", 66, 0, 0, 1);
        Light = new Person("Light", 0, 0, 0, 0);
    }
    
    /**
     * Test getUsage()
     */
    public void testGetUsage() {
        assertEquals(0, one.getUsage());
        one.add(Daniel);
        assertEquals(1, one.getUsage());
    }
    
    /**
     * Test isEmpty()
     */
    public void testIsEmpty() {
        assertTrue(one.isEmpty());
        one.add(Daniel);
        assertFalse(one.isEmpty());
    }
    
    /**
     * Test add()
     */
    public void testAdd() {
        one.add(Daniel);
        one.add(Daniel);
        
        assertEquals(65, Daniel.getWeight());
        assertEquals(110 * 2, one.getWeight());
        
        // Ok, so the Daniels in the array are all linked
        // to the original Daniel
        Daniel.setWeight(30);
        assertEquals(30, Daniel.getWeight());
        assertEquals(30, one.returnPerson(0).getWeight());
        Daniel.setWeight(65);
        assertEquals(65, one.returnPerson(1).getWeight());
        // End of that...
        
        assertEquals(2, one.getUsage());
        
        for (int i = 1; i <= 16; i++) {
            one.add(Daniel);
        }
        
        assertEquals(110 * 18, one.getWeight());
        assertEquals(18, one.getUsage());
        
        // Next addition should fail as weight will be beyond limit
        assertFalse(one.add(Daniel));
        assertEquals(18, one.getUsage());
        
        for (int i = 1; i <= 6; i++) {
            one.add(Light);
        }
        
        assertEquals(24, one.getUsage());
        assertFalse(one.add(Light));
        assertEquals(24, one.getUsage());
        assertEquals(110 * 18, one.getWeight());
        
        System.out.println(one.toString());
    }
    
    /**
     * Test setFloor()
     */
    public void testSetFloor() {
        assertEquals(0, one.getFloor());
        assertFalse(one.setFloor(-1));
        assertFalse(one.setFloor(11));
        one.setFloor(10);
        assertEquals(10, one.getFloor());
        one.setFloor(7);
        assertEquals(7, one.getFloor());
        one.setFloor(0);
        assertEquals(0, one.getFloor());
    }
    
    /**
     * Test remove()
     */
    public void testRemove() {
        one.add(Daniel);
        one.add(Ryan);
        one.add(Mayuka);
        one.add(Eric);
        one.add(Light);
        
        System.out.println(one.toString());
        
        assertEquals(15 + 45 + 65 + 66 + 71, one.getWeight());
        assertTrue(one.setFloor(3));
        assertEquals(3, one.getFloor());
        assertEquals(5, one.getUsage());
        assertEquals(2, one.remove());
        assertEquals(3, one.getUsage());
        assertEquals(45 + 65 + 66, one.getWeight());
        
        System.out.println(one.toString());
        
        one.add(Eric);
        assertEquals(4, one.getUsage());
        assertEquals("Eric", one.returnPerson(2).getName());
        
        System.out.println(one.toString());
        
        assertTrue(one.setFloor(1));
        assertEquals(1, one.remove());
        
        System.out.println(one.toString());
        
        assertEquals(45 + 65, one.getWeight());
        assertEquals(3, one.getUsage());
        
        assertEquals(1, one.getFloor());
        assertEquals(0, one.remove());
    }
    
    /**
     * Test getWeight()
     */
    public void testGetWeight() {
        assertEquals(0, two.getWeight());
        assertTrue(two.add(Daniel));
        assertEquals(110, two.getWeight());
        assertTrue(two.setFloor(4));
        assertEquals(1, two.remove());
        assertEquals(0, two.getWeight());
    }
    
    /**
     * Test getFloor()
     */
    public void testGetFloor() {
        assertEquals(0, one.getFloor());
        assertTrue(one.setFloor(5));
        assertEquals(5, one.getFloor());
    }
    
    /**
     * Test queueUsage()
     */
    public void testQueueUsage() {
        assertEquals(0, one.queueUsage());
        assertTrue(one.queueFloor(3));
        assertEquals(1, one.queueUsage());
    }
    
    /**
     * Test queueFloor()
     */
    public void testQueueFloor() {
        System.out.println(one.queueToString());
        assertFalse(one.queueFloor(-1)); // out of bounds
        assertFalse(one.queueFloor(99)); // doesn't exists
        assertTrue(one.queueFloor(0));
        System.out.println(one.queueToString());
        
        assertTrue(one.queueFloor(0)); // already queued
        assertTrue(one.queueFloor(1));
        assertTrue(one.queueFloor(2));
        System.out.println(one.queueToString());
        
        assertTrue(one.queueFloor(2)); // already queued
        
        for (int i = 3; i < one.getMaxFloor(); i++) {
            one.queueFloor(i);
        }
        
        System.out.println(one.queueToString());
        
        // shouldn't get an out of bounds error since
        // all floors are queued (assuming input is < mF)
        assertEquals(10, one.queueUsage());
        assertTrue(one.queueFloor(9));
        
        System.out.println(one.queueToString());
        
        // i just did 9 above so 0 to 8
        for (int i = 0; i < 9; i++) {
            assertTrue(one.queueFloor(i));
        }
        
        // the bounds actually protect a null pointer error
        // and this should return false
        assertFalse(one.queueFloor(10));
    }
    
    /**
     * Test dequeueFloor()
     */
    public void testDequeueFloor() {
        // queue some floors
        one.queueFloor(5);
        one.queueFloor(6);
        one.queueFloor(8);
        one.queueFloor(9);
        assertEquals(4, one.queueUsage()); // counter works?
        
        // print
        System.out.println(one.queueToString());
        
        //try to dequeue a floor that is not queued
        assertFalse(one.dequeueFloor(7));
        assertTrue(one.dequeueFloor(6)); // dequeue an existing floor
        assertEquals(3, one.queueUsage()); // counter works?
        
        // print
        System.out.println(one.queueToString());
        
        // dequeue what i just dequeued, false
        assertFalse(one.dequeueFloor(6));
        assertTrue(one.dequeueFloor(5)); // dequeue edge case
        assertEquals(2, one.queueUsage());
        
        // print
        System.out.println(one.queueToString());
        
        // dequeue last element case
        assertTrue(one.dequeueFloor(9));
        
        // print
        System.out.println(one.queueToString());
        
        // dequeue only one element case
        assertTrue(one.dequeueFloor(8));
        assertEquals(0, one.queueUsage());
        
        // print, nothing left, all -99
        System.out.println(one.queueToString());
        
        // try dequeue all floors
        for (int i = 0; i < one.getMaxFloor(); i++) {
            assertFalse(one.dequeueFloor(i));
        }
        
        // queue all floors except top floor
        for (int i = 0; i < one.getMaxFloor() - 1; i++) {
            assertTrue(one.queueFloor(i));
        }
        
        // print
        System.out.println(one.queueToString());
        
        // queue the last floor
        assertTrue(one.queueFloor(9));
        assertEquals(10, one.queueUsage());
        
        // print
        System.out.println(one.queueToString());
        
        // all floors are queued now, right edge case test?
        assertTrue(one.dequeueFloor(9));
        
        // print
        System.out.println(one.queueToString());
        
        // that worked nicely, let's try from the middle
        assertTrue(one.queueFloor(9));
        assertTrue(one.dequeueFloor(4));
        
        // print
        System.out.println(one.queueToString());
        
        assertTrue(one.queueFloor(9)); // already queued
        assertFalse(one.dequeueFloor(4)); // already dequeued
        
        assertTrue(one.queueFloor(4)); // [0,1,2,3,5,6,7,8,4] expected
        
        // print
        System.out.println(one.queueToString());
    }
    
    /**
     * Test toString()
     */
    public void testToString() {
        one.add(Daniel);
        assertEquals("[Daniel, null, null, null, null, null, null, null, "
            + "null, null, null, null, null, null, null, null, null, "
                + "null, null, null, null, null, null, null]", one.toString());
    }
    
    /**
     * Test queueToString()
     */
    public void testQueueToString() {
        one.queueFloor(8);
        one.queueFloor(9);
        assertEquals("[8, 9, -99, -99, -99, "
            + "-99, -99, -99, -99, -99]", one.queueToString());
    }
    
    /**
     * Test getCapacity() and getMaxFloor()
     */
    public void testGetCapacityAndMaxFloor() {
        assertEquals(24, one.getCapacity());
        assertEquals(7, two.getMaxFloor());
        assertEquals(10, one.getMaxFloor());
    }
    
    /**
     * Test returnPerson()
     */
    public void testReturnPerson() {
        one.add(Daniel);
        Person hold = one.returnPerson(0);
        assertEquals("Daniel", hold.getName());
        
        Exception exception = null;
        
        try {
            assertNull(one.returnPerson(-1));
        }
        catch (ArrayIndexOutOfBoundsException e){
            exception = e;
            System.out.println("This array"
                + " only holds " + one.getCapacity() + ". The index \""
                    + e.getMessage() + "\" is out of bounds.");
        }
        
        assertNotNull(exception);
        assertNull(one.returnPerson(5));
    }
    
    /**
     * Test returnPerson()
     */
    public void testReturnQueue() {
        one.queueFloor(5);
        int hold = one.returnQueue(0);
        assertEquals(5, hold);
        
        Exception exception = null;
        
        try {
            assertEquals(-99, one.returnQueue(-3));
        }
        catch (ArrayIndexOutOfBoundsException e){
            exception = e;
            System.out.println("Level input \"" + e.getMessage()
                + "\" does not exsist. There are only "
                + one.getMaxFloor() + " levels and no sub-zero levels.");
        }
        
        assertNotNull(exception);
        assertEquals(-99, one.returnQueue(5));
    }
    
    /**
     * Test a real scenario!
     */
    public void testReal() {
        one.add(Daniel);
        one.queueFloor(Daniel.targetFloor());
        one.setFloor(Daniel.targetFloor());
        one.dequeueFloor(Daniel.targetFloor());
        one.remove();
        
        assertEquals(0, one.getWeight());
        assertEquals(0, one.getUsage());
        assertEquals(4, one.getFloor());
    }
}
