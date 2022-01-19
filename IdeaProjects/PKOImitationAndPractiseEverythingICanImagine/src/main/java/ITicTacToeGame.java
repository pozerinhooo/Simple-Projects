public interface ITicTacToeGame {
    int check();
    int[] xWins(int x, int y, int z);
    int[] yWins(int x, int y, int z);
    void draw();
}
