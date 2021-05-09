public class ConnecFourMain {
    public static void main(String[] args) {
        Board b = new Board();

        b.display();

        RedCoin rc = new RedCoin();
        YellowCoin yc = new YellowCoin();

        b.insert(rc, 3, 4);
        //b.update(rc, 3, 4);

        b.insert(yc, 0, 0);
        //b.update(yc, 0, 0);

        b.display();
    }
}