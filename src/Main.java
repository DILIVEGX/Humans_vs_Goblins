import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int landSize = 10;
    static Scanner scanner = new Scanner(System.in);
    static Human human = new Human(landSize);
    static ArrayList<Goblin> goblins = new ArrayList<>();
    static Land land = new Land(landSize);

    static void update(){

        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
        land.print();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            goblins.add(new Goblin(landSize));
        }
        land.setCell(human.getPosition()[0], human.getPosition()[1], human.getIcon(), Integer.toString(human.getLvl()));
        land.print();
        while(true){
            String input = scanner.nextLine().toUpperCase();
            switch (input) {
                case "W":
                    human.upForward();
                    land.clearCell(human.getPosition()[0]+1, human.getPosition()[1]);
                    land.setCell(human.getPosition()[0], human.getPosition()[1], human.getIcon(), Integer.toString(human.getLvl()));
                    update();
                    break;
                case "A":
                    human.leftForward();
                    land.clearCell(human.getPosition()[0], human.getPosition()[1]+1);
                    land.setCell(human.getPosition()[0], human.getPosition()[1], human.getIcon(), Integer.toString(human.getLvl()));
                    update();
                    break;
                case "S":
                    human.downForward();
                    land.clearCell(human.getPosition()[0]-1, human.getPosition()[1]);
                    land.setCell(human.getPosition()[0], human.getPosition()[1], human.getIcon(), Integer.toString(human.getLvl()));
                    update();
                    break;
                case "D":
                    human.rightForward();
                    land.clearCell(human.getPosition()[0], human.getPosition()[1]-1);
                    land.setCell(human.getPosition()[0], human.getPosition()[1], human.getIcon(), Integer.toString(human.getLvl()));
                    update();
                    break;
            }
        }


    }
}