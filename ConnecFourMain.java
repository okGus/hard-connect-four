public class ConnecFourMain {
    public static void main(String[] args) {
        Board b = new Board();

        b.display();

        Coin c = new Coin("Red");
        b.insert(c, 3, 2);

        b.display();
    }
}