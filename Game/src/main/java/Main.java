public class Main {
    public static void main(String[] args) {
        GameManager gm = new GameManager();
        CharacterFactory cf = new CharacterFactory();
        Character c1 = cf.createCharacter();
        Character c2 = cf.createCharacter();
        gm.fight(c1, c2);
    }
}
