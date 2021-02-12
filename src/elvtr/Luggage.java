// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself 
// with honor and integrity at all times.
// I will not lie, cheat, or steal, 
// nor will I accept the actions of those who do.
// -- Daniel Almeida (adaniel1)
package elvtr;

/**
 * @author Daniel Almeida
 *
 * Luggage for person.
 */
public class Luggage {
    private int totalLuggageWeight;
    private int totalNumLuggage;
    
    /**
     * Constructor for Luggage.
     * 
     * @param tLW   The total luggage weight.
     * @param tNL   The total number of bags.
     */
    public Luggage(int tLW, int tNL) {
        this.totalLuggageWeight = tLW;
        this.totalNumLuggage = tNL;
    }

    /**
     * @return The total weight of all bags.
     */
    public int getTotalLuggageWeight() {
        return totalLuggageWeight;
    }
    
    /**
     * @return The total number of bags.
     */
    public int getNumLuggage() {
        return totalNumLuggage;
    }
}
