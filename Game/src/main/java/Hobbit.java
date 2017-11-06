public class Hobbit extends Character {
    public Hobbit() {
        super(3, 3);
    }

    public void kick(Character c) {
        toCry();
    }
    public void toCry(){
        System.out.println("Hnik-hnik-hnik");
    }
}