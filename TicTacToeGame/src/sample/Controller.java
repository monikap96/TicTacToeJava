package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.Arrays;

public class Controller {
    private String turn = "x";
    int whoseTurnInt = 1;
    private int sumOfTurns = 0;
    private int winX=0,winO=0;
    private final int [][] tictactoe = new int[3][3];
    private String winner="";

    int []sumRow = new int [3];
    int []sumColumn = new int [3];
    int []sumDiagonal = new int [2];


    @FXML public Label whoseTurn;   // <-- wystarczy nazwać tak samo zmienną jak id xDDDD
    @FXML public Label score;
    @FXML public Button newGameButton;
    @FXML public Button id00,id01,id02,id10,id11,id12,id20,id21,id22;
    public Button [] buttons = new Button[9];
    @FXML public void initialize(){
        initButtonArray();
    }

    private void initButtonArray() {
        buttons[0]=id00;
        buttons[1]=id01;
        buttons[2]=id02;
        buttons[3]=id10;
        buttons[4]=id11;
        buttons[5]=id12;
        buttons[6]=id20;
        buttons[7]=id21;
        buttons[8]=id22;
    }

    public void setValue(MouseEvent mouseEvent) {
        String id = ((Button)mouseEvent.getSource()).getId();
        String value = ((Button)mouseEvent.getSource()).getText();
        int x = Character.getNumericValue(id.charAt(2));
        int y = Character.getNumericValue(id.charAt(3));
        if(value.equals(" ") && isWinner().equals("")){
            Button button = (Button)mouseEvent.getSource();
            button.setText(turn);
            sumOfTurns++;
            if(turn.equals("x")){
                whoseTurnInt = 1;
                tictactoe[x][y]= whoseTurnInt;
                turn = "o";
            }else if(turn.equals("o")){
                whoseTurnInt = -1;
                tictactoe[x][y]= whoseTurnInt;
                turn = "x";
            }
            sumTurn(x, y, whoseTurnInt);
            winner = isWinner();
            if(winner.equals("x") || winner.equals("o")){
                whoseTurn.setText("The winner is: "+winner);
                if(winner.equals("x")){
                    winX++;
                }else {
                    winO++;
                }
                score.setText(winX+" - "+winO);
                newGameButton.setVisible(true);
            }else if(sumOfTurns==9){
                whoseTurn.setText("It's draw");
                newGameButton.setVisible(true);
            }else{
                whoseTurn.setText("Turn: "+turn);      // XDDDDDDDDDDDDDD
            }
        }

    }

    public void newGame() {
        sumOfTurns=0;
        clearTicTacToe();
        clearButtons();
        winner="";
        cleanArray(sumRow);
        cleanArray(sumColumn);
        cleanArray(sumDiagonal);
        whoseTurn.setText("Turn: "+turn);
        newGameButton.setVisible(false);
    }

    private void cleanArray(int[] array) {
        Arrays.fill(array, 0);
    }

    private void clearButtons() {
        for (Button b: buttons) {
            b.setText(" ");
        }
    }

    private void clearTicTacToe() {
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                tictactoe[i][j]=0;
            }
        }
    }

    private void sumTurn(int posX, int posY, int turnInt) {
        sumRow[posX]+=turnInt;
        sumColumn[posY]+=turnInt;
        if(posX==posY){
            sumDiagonal[0]+=turnInt;
        }
        if(posX==(3-posY-1)){
            sumDiagonal[1]+=turnInt;
        }
    }

    private String isWinner() {
        for (int val : sumRow) {
            if(val==3){
                return "x";
            }else if(val==-3){
                return "o";
            }
        }
        for (int val : sumColumn) {
            if(val==3){
                return "x";
            }else if(val==-3){
                return "o";
            }
        }
        for (int val : sumDiagonal) {
            if(val==3){
                return "x";
            }else if(val==-3){
                return "o";
            }
        }

        return "";
    }

    public String getTurn() {
        return turn;
    }
}
