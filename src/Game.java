import java.util.Arrays;

public class Game {
    private Player player1;
    private Player player2;
    private Player winner;
    private int[][] boardState;
    private int turns;

    public Game(String player1, String player2) {
        this.player1 = new Player(player1);
        this.player2 = new Player(player2);
        this.boardState = new int[3][];
        for (int i = 0;i < 3;i++){
            this.boardState[i] = new int[3];
        }
        this.turns = 0;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getWinner() {
        return winner;
    }

    public int[][] getBoardState() {
        return boardState;
    }

    public int getTurns() {
        return turns;
    }

    public void drawBoard(){
        for(int row = 0; row < 3;row++){
            for(int col = 0; col < 3;col++){
                switch (boardState[row][col]){
                    case 0:
                        System.out.print(" ");
                        break;
                    case 1:
                        System.out.print("X");
                        break;
                    case 2:
                        System.out.print("O");
                        break;

                }
                if(col < 2){
                    System.out.print("|");
                }
                else{
                    System.out.println();
                }
            }
            if(row < 2){
                System.out.println("-----");
            }
            else{
                System.out.println();
            }
        }
    }
    public class Player{
        private String name;

        public Player(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString(){
            return "Player " + name;
        }
    }
}
