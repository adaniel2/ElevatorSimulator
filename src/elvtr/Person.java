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
 * This Person class represents an individual who wishes
 * to use the elevator.
 *
 */
public class Person {
    private String name;
    private int weight;
    private int targetFloor;
    private Luggage luggage;
    
    /**
     * Constructor for Person.
     * 
     * @param n     Name of person
     * @param w     Weight of person
     * @param nL    Number of bags
     * @param tLW   Total weight of bags
     * @param tF    Target floor
     */
    public Person(String n, int w, int nL, int tLW, int tF) {
        this.name = n;
        this.weight = w;
        this.targetFloor = tF;
        
        if (nL > 0) {
            this.luggage = new Luggage(tLW, nL);
        }
        else {
            this.luggage = new Luggage(0, 0);
        }
    }
    
    /**
     * @return for name of Person.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * @return Person's total weight (not including luggage).
     */
    public int getWeight() {
        return this.weight;
    }
    
    /**
     * @return Person's total weight (including luggage).
     */
    public int getCombinedWeight() {
        return this.getWeight() + this.luggage.getTotalLuggageWeight();
    }
    
    /**
     * @return Person's target floor.
     */
    public int targetFloor() {
        return this.targetFloor;
    }
    
    /**
     * @return Person's luggage.
     */
    public Luggage luggage() {
        return this.luggage;
    }
    
    /**
     * Set person's weight (just testing something)
     */
    public void setWeight(int w) {
        this.weight = w;
    }
}
