public class Coin {
    private String color;

    /**
     * Initializes a newly created Coin object to represent coin
     */
    Coin() {
        this.color = " ";
    }

    /**
     * Constructs a new Coin object given the color
     * @param color - color of coin
     */
    Coin(String color) {
        this.color = color;
    }

    /**
     * Returns coin color
     * @return - color
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Sets coin color
     */
    public void setColor(String color) {
        this.color = color;
    }
}
