// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself 
// with honor and integrity at all times.
// I will not lie, cheat, or steal, 
// nor will I accept the actions of those who do.
// -- Daniel Almeida (adaniel1)
package elvtr;

import student.TestCase;

/**
 * Test class for Person class.
 * 
 * @author Daniel Almeida
 *
 */
public class PersonTest extends TestCase {
    private Person Daniel;
    private Person Mayuka;
    private Person Ryan;
    
    /**
     * Setup for tests
     */
    public void setUp() {
        Daniel = new Person("Daniel", 65, 2, 45, 4);
        Mayuka = new Person("Mayuka", 71, 1, 15, 3);
        Ryan = new Person("Ryan", 66, 0, 0, 1);
    }
    
    /**
     * Test getName()
     */
    public void testGetName() {
        assertEquals("Daniel", Daniel.getName());
        assertNotSame("Bob", Mayuka.getName());
    }
    
    /**
     * Test getWeight()
     */
    public void testGetWeight() {
        assertEquals(65, Daniel.getWeight());
        assertEquals(66, Ryan.getWeight());
    }
    
    /**
     * Test getCombinedWeight()
     */
    public void testGetCombinedWeight() {
        assertEquals(110, Daniel.getCombinedWeight());
    }
    
    /**
     * Test targetFloor()
     */
    public void testTargetFloor() {
        assertEquals(4, Daniel.targetFloor());
    }
    
    /**
     * Test luggage()
     */
    public void testLuggage() {
        Luggage luggage = Daniel.luggage();
        assertEquals(2, luggage.getNumLuggage());
        assertEquals(45, luggage.getTotalLuggageWeight());
        
        luggage = Ryan.luggage();
        assertEquals(0, luggage.getNumLuggage());
        assertEquals(0, luggage.getTotalLuggageWeight());
    }
}
