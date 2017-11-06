public class Elf extends Character {
    public Elf() {
        super(10, 10);
    }

    public void kick(Character c) {
        if (c.power < power) {
            c.hp = 0;
        }
        else {
            c.power--;
        }
    }
}