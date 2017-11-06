public class King extends Character {
    private static final int minHp = 5;
    private static final int minPower = 5;
    private static final int maxHp = 15;
    private static final int maxPower = 15;
    public King() {
        super(Utils.generateNum(King.minPower, King.maxPower),
                Utils.generateNum(King.minHp, King.maxHp));
    }

    public King(int minHp, int maxHp, int minPower, int maxPower) {
        super(Utils.generateNum(minPower, maxPower), Utils.generateNum(minHp, maxHp));
    }

    public void kick(Character c) {
        c.hp -= Utils.generateNum(0, power);
    }
}
