public class Character {
    private final int lvl = 1;
    protected final int[] position = {0, 0};
    protected String icon;
    protected int landSize;


    int[] getPosition() {
        return position;
    }

    public int getLvl() {
        return lvl;
    }

    public void upForward() {
        if (position[0] > 0) {
            position[0] -= 1;
        }
    }

    public void downForward() {
        if (position[0] < landSize-1) {
         position[0] += 1;
        }
    }

    public void rightForward() {
        if (position[1] < landSize-1) {
            position[1] += 1;
        }
    }

    public void leftForward() {
        if (position[1] > 0) {
            position[1] -= 1;
        }
    }



    public String getIcon() {
        return icon;
    }

}
