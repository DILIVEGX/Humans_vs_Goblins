public class Human extends Character {

    Human(int landSize) {
        super.icon = "(ò_ó)";
        super.landSize = landSize;
    }

    void levelUp(int levels) {
        super.lvl+=levels;
    }
}
