public class Land {

    static class Cell {
        String icon;
        String level;

        public Cell(String icon, String level) {
            this.icon = icon;
            this.level = level;
        }
    }

    private final int landSize;
    private final Cell[][] land;

    public Land(int landSize) {
        this.landSize = landSize;
        land = new Cell[landSize][landSize];

        for (int i = 0; i < landSize; i++) {
            for (int j = 0; j < landSize; j++) {
                land[i][j] = new Cell(null, null);
            }
        }
    }

    public void setCell(int row, int col, String icon, String level) {
        land[row][col] = new Cell(icon, level);
    }

    public void clearCell(int row, int col) {
        land[row][col] = new Cell(null, null);
    }

    public void print() {
        for (int i = 0; i < landSize; i++) {
            // Línea superior
            for (int j = 0; j < landSize; j++) {
                System.out.print("+-------");
            }
            System.out.println("+");

            for (int j = 0; j < landSize; j++) {
                Cell cell = land[i][j];
                String icon = cell.icon != null ? padCenter(cell.icon) : "       ";
                System.out.print("|" + icon);
            }
            System.out.println("|");

            for (int j = 0; j < landSize; j++) {
                Cell cell = land[i][j];
                String level = cell.level != null ? padCenter("Lvl " + cell.level) : "       ";
                System.out.print("|" + level);
            }
            System.out.println("|");
        }

        for (int j = 0; j < landSize; j++) {
            System.out.print("+-------");
        }
        System.out.println("+");
    }

    private static String padCenter(String text) {
        int width = 7;
        int padding = width - text.length();
        int left = padding / 2;
        int right = padding - left;
        return " ".repeat(Math.max(0, left)) + text + " ".repeat(Math.max(0, right));
    }
}
