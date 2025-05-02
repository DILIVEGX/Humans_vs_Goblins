import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int landSize = 10;
    static Scanner scanner = new Scanner(System.in);
    static Human human = new Human(landSize);
    static ArrayList<Goblin> goblins = new ArrayList<>();
    static Land land = new Land(landSize);
    static int goblinAmount = 7;
    static int[][] goblinPositions;

    static void update() {
        // Clear Screen
        for (int i = 0; i < 10; i++) System.out.println();

        // Save new temp positions
        int[][] newPositions = new int[goblins.size()][2];

        for (int i = 0; i < goblins.size(); i++) {
            Goblin goblin = goblins.get(i);
            // Human collide?
            if (goblin.getPosition()[0] == human.getPosition()[0] && goblin.getPosition()[1] == human.getPosition()[1]) {
                combat(i);
            }
            land.clearCell(goblin.getPosition()[0], goblin.getPosition()[1]);

            //Save actual positions if no valid
            int[] original = goblin.getPosition().clone();
            boolean moved = false;

            for (int attempts = 0; attempts < 10 && !moved; attempts++) {
                goblin.move();
                int[] newPos = goblin.getPosition();

                // Verify if repeats
                boolean occupied = false;
                for (int j = 0; j < i; j++) {
                    if (newPositions[j][0] == newPos[0] && newPositions[j][1] == newPos[1]) {
                        occupied = true;
                        break;
                    }
                }

                if (!occupied) {
                    newPositions[i] = newPos.clone();
                    moved = true;
                } else {
                    // Revert the occupied
                    goblin.setPosition(original.clone());
                }
            }

            // Update position
            land.setCell(goblin.getPosition()[0], goblin.getPosition()[1], goblin.getIcon(), Integer.toString(goblin.getLvl()));
        }

        // Set human position
        land.setCell(human.getPosition()[0], human.getPosition()[1], human.getIcon(), Integer.toString(human.getLvl()));

        // Print map
        land.print();
    }

    static void reset() {
        land = new Land(landSize);
        human.resetPosition();
        goblinPositions = generateGoblinsPositions(goblinAmount);
        goblins.clear();
        for (int i = 0; i < goblinAmount; i++) {
            Goblin goblin;
            goblins.add(goblin = new Goblin(landSize));
            goblin.setPosition(goblinPositions[i]);
            goblin.setLevel((int)(Math.random() * ((human.getLvl()+5) - human.getLvl())) + human.getLvl());
            land.setCell(goblin.getPosition()[0], goblin.getPosition()[1], goblin.getIcon(), Integer.toString(goblin.getLvl()));
        }
        land.setCell(human.getPosition()[0], human.getPosition()[1], human.getIcon(), Integer.toString(human.getLvl()));
        land.print();
    }

    static void combat(int goblin) {
        int goblinHealth = 100;
        int humanHealth = 100;
        int maxDamage = 60 - (goblins.get(goblin).getLvl() - human.getLvl())*10;
        String done = "";
        String recieved = "";
        int damage, defense;
        boolean combatEnd = false;
        while(!combatEnd) {
            // Clear Screen
            for (int i = 0; i < 10; i++) System.out.println();

            System.out.println("   ad88 88             88                  \n" +
                    "  d8\"   \"\"             88           ,d     \n" +
                    "  88                   88           88     \n" +
                    "MM88MMM 88  ,adPPYb,d8 88,dPPYba, MM88MMM  \n" +
                    "  88    88 a8\"    `Y88 88P'    \"8a  88     \n" +
                    "  88    88 8b       88 88       88  88     \n" +
                    "  88    88 \"8a,   ,d88 88       88  88,    \n" +
                    "  88    88  `\"YbbdP\"Y8 88       88  \"Y888  \n" +
                    "            aa,    ,88                     \n" +
                    "             \"Y8bbdP\"  ");
            System.out.println("                   _.--.    .--._                      ,      ,");
            System.out.println("                 .\"  .\"      \".  \".                   /(.-\"\"-.)\\");
            System.out.println("                ;  .\"    /\\    \".  ;             |\\  \\/      \\/  /|");
            System.out.println("                ;  '._,-/  \\-,_.`  ;             | \\ / =.  .= \\ / |");
            System.out.println("                \\  ,`  / /\\ \\  `,  /             \\( \\   o\\/o   / )/");
            System.out.println("                 \\/    \\/  \\/    \\/               \\_, '-/  \\-' ,_/");
            System.out.println("                 ,=_    \\/\\/    _=,                 /   \\__/   \\");
            System.out.println("                 |  \"_   \\/   _\"  |                 \\ \\__/\\__/ /");
            System.out.println("                 |_   '\"-..-\"'   _|               ___\\ \\|--|/ /___");
            System.out.println("                 | \"-.        .-\" |             /`    \\      /    `\\");
            System.out.println("                 |    \"\\    /\"    |            /       '----'       \\");
            System.out.println("                 |      |  |      |");
            System.out.println("         ___     |      |  |      |     ___");
            System.out.println("     _,-\",  \",   '_     |  |     _'   ,\"  ,\"-,_");
            System.out.println("   _(  \\  \\   \\\"=--\"-.  |  |  .-\"--=\"/   /  /  )_");
            System.out.println(" ,\"  \\  \\  \\   \\      \"-'--'-\"      /   /  /  /  \".");
            System.out.println("!     \\  \\  \\   \\                  /   /  /  /     !");
            System.out.println(":      \\  \\  \\   \\                /   /  /  /      :");
            System.out.println("Human    Health: " + humanHealth + " Lvl: " + human.getLvl() + "    Goblin    Health: " + goblinHealth + " Lvl: " + goblins.get(goblin).getLvl());
            System.out.println();
            System.out.println(done + " " + recieved);
            System.out.println("What do you want to do?");
            System.out.println("1) Attack dmg: 3-" + maxDamage);
            System.out.println("2) Defense dmg: 10-70");
            int choice = scanner.nextInt();
            if (choice == 1) {
                damage = (int)(Math.random() * (maxDamage - 3 + 1)) + 3;
                done = "Damage done + " + damage;
                goblinHealth -= damage;
                if (goblinHealth <= 0) {
                    System.out.println("You win! Leveling up...");
                    human.levelUp((goblins.get(goblin).getLvl() - human.getLvl())+1);
                    combatEnd = true;
                    reset();
                }else{
                    damage = (int)(Math.random() * (50 - 3 + 1)) + 3;
                    humanHealth -= damage;
                    recieved = "Damage recieved + " + damage;
                }
            }else if (choice == 2) {
                damage = (int)(Math.random() * (70 - 10 + 1)) + 10;
                done = "Damage defense + " + damage;
                if (goblinHealth <= 0) {
                    System.out.println("You win! Leveling up...");
                    human.levelUp((goblins.get(goblin).getLvl() - human.getLvl())+1);
                    combatEnd = true;
                    reset();
                }else{
                    damage = ((int)(Math.random() * (50 - 3 + 1)) + 3) - damage;
                    if (damage > 0) {
                        humanHealth -= damage;
                        recieved = "Damage recieved + " + damage;
                    }else{
                        recieved = "Damage recieved + 0";
                    }

                }
            }
            if (humanHealth <= 0) {
                System.out.println("You lose! Max Level: " + human.getLvl());
                System.exit(0);
            }
        }

    }

    static int[][] generateGoblinsPositions(int count) {
        int[][] positions = new int[count][2];
        int index = 0;

        while (index < count) {
            int x = (int)(Math.random() * 9) + 1;
            int y = (int)(Math.random() * 9) + 1;
            boolean tooClose = false;
            for (int i = 0; i < index; i++) {
                int dx = Math.abs(positions[i][0] - x);
                int dy = Math.abs(positions[i][1] - y);
                if (dx <= 1 && dy <= 1) {
                    tooClose = true;
                    break;
                }
            }
            if (!tooClose) {
                positions[index][0] = x;
                positions[index][1] = y;
                index++;
            }
        }
        return positions;
    }


    public static void main(String[] args) {
        reset();
        while(true){
            String input = scanner.nextLine().toUpperCase();
            switch (input) {
                case "W":
                    human.upForward();
                    land.clearCell(human.getPosition()[0]+1, human.getPosition()[1]);
                    update();
                    break;
                case "A":
                    human.leftForward();
                    land.clearCell(human.getPosition()[0], human.getPosition()[1]+1);
                    update();
                    break;
                case "S":
                    human.downForward();
                    land.clearCell(human.getPosition()[0]-1, human.getPosition()[1]);
                    update();
                    break;
                case "D":
                    human.rightForward();
                    land.clearCell(human.getPosition()[0], human.getPosition()[1]-1);
                    update();
                    break;
            }
        }


    }
}