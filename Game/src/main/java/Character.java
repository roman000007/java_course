public abstract class Character {
    protected int hp;
    protected int power;

    public Character(int power, int hp){
        this.hp = hp;
        this.power = power;
    }

    abstract public void kick(Character c);

    public boolean isAlive() {
        return hp > 0;
    }
}