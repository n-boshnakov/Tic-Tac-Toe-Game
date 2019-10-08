import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        this.winner = null;
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
    public void play()throws IOException {
        while(!isFull() && winner == null){
            int playersTurn = turns % 2;
            Player player;
            if(playersTurn == 0){
                player = player1;
            }
            else{
                player = player2;
            }
            System.out.println(player + "'s turn: ");
            drawBoard();
            selectCell(playersTurn);
            checkForWinner(player);
            turns++;
        }
    }


    private boolean isFull() {
        for (int[] array : boardState) {
            if (Arrays.stream(array).anyMatch(e -> e == 0)) {
                return false;
            }
        }
        return true;
    }


    private void checkForWinner(Player player) {
        for(int num = 0; num < 3;num++) {
            if (boardState[num][0] != 0){
                if(boardState[num][0] == boardState[num][1] && boardState[num][1] == boardState[num][2]) {
                    printWinner(player);
                }
            }
            if (boardState[0][num] != 0){
                if(boardState[0][num] == boardState[1][num] && boardState[1][num] == boardState[2][num]){
                    printWinner(player);
                }
            }
            if(boardState[1][1] != 0){
                if (boardState[0][0] == boardState[1][1] && boardState[1][1] == boardState[2][2]){
                    printWinner(player);
                }
                if (boardState[0][2] != 0 && boardState[0][2] == boardState[1][1] && boardState[1][1] == boardState[2][0]){
                    printWinner(player);
                }
            }
        }
    }

    private void printWinner(Player player) {
        winner = player;
        drawBoard();
        System.out.println("Winner is " + player);
    }

    private void selectCell(int playersTurn) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int row, col;
        do {
            System.out.println("Please choose an empty place on which to place your symbol: ");
           do {
               System.out.print("Row(1, 2 or 3): ");
               String text = reader.readLine();
               row = Integer.parseInt(text) - 1;
           } while(row > 2 || row < 0);
           do{
               System.out.print("Col(1, 2 or 3): ");
               String text1 = reader.readLine();
               col = Integer.parseInt(text1) - 1;
           } while(col > 2 || col < 0);
        } while(boardState[row][col] != 0);
        boardState[row][col] = playersTurn + 1;
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
