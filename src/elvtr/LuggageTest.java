package elvtr;

import student.TestCase;

/**
 * Testing for Luggage class.
 * 
 * @author Daniel Almeida
 *
 */
public class LuggageTest extends TestCase {
    private Luggage equalsmc;
    private Luggage aimbotdotexe;
    
    /**
     * Setup class
     */
    public void setUp() {
        equalsmc = new Luggage(42, 2);
        aimbotdotexe = new Luggage(21, 1);
    }
    
    /**
     * Test constructor/getter methods
     */
    public void testGet() {
        assertEquals(42, equalsmc.getTotalLuggageWeight());
        assertEquals(2, equalsmc.getNumLuggage());
        
        assertEquals(21, aimbotdotexe.getTotalLuggageWeight());
        assertEquals(1, aimbotdotexe.getNumLuggage());
    }
}
