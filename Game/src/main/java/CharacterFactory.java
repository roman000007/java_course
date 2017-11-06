public class CharacterFactory {
    private static final int CHARACTERS_AMOUNT = 4;
    public Character createCharacter(){
        int amount = Utils.generateNum(1, CHARACTERS_AMOUNT);
        switch (amount){
            case 1:
                return new Elf();
            case 2:
                return new King();
            case 3:
                return new Knight();
            default:
                return new Hobbit();
        }
    }
}
