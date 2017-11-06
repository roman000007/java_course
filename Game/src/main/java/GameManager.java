public class GameManager {
    void fight(Character c1, Character c2){
        System.out.println("Player 1: " + c1 + " (hp:" + c1.hp + ", power: " + c1.power + ")");
        System.out.println("Player 2: " + c2 + " (hp:" + c2.hp + ", power: " + c2.power + ")");
        int turn = 1;
        while (c1.isAlive() && c2.isAlive()){
            if(turn % 2 == 1){
                System.out.println("Player 1 turn! (Turn " + turn + ")");
                c1.kick(c2);
            } else{
                System.out.println("Player 2 turn! (Turn " + turn + ")");
                c2.kick(c1);
            }
            System.out.println("Player 1: hp = " + c1.hp + ", power = " + c1.power);
            System.out.println("Player 2: hp = " + c2.hp + ", power = " + c2.power);
            System.out.println("----------------------------------");
            turn++;
            if(turn > 100){
                System.out.println("Draw!");
                return;
            }
        }
        if (c1.isAlive()) {
            System.out.println("Player 1 win!!!");
        } else {
            System.out.println("Player 2 win!!!");
        }

    }
}
