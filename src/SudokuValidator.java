import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuValidator {

    public boolean validate(char[][] board, String boardName) {

    List<Character> tempList = new ArrayList<>();
    Set<Character> tempHashSet = new HashSet<>();


    //Check rows
    for(int i = 0; i < board.length; i++) {
        for(int j = 0; j < board.length; j++) {
            char c = board[i][j];
            if (Character.isDigit(c)) {
                tempList.add(board[i][j]);
                tempHashSet.add(board[i][j]);
            }
        }

        //Indicates duplication in row
        if (tempList.size() > tempHashSet.size()) {
            System.out.printf("%s is not valid:\n\t Error on Row %d\n",boardName, i + 1);
            return false;
        } else {
            tempList.clear();
            tempHashSet.clear();
        }
    }

    //Check columns
    for(int i = 0; i < board.length; i++) {
        for(int j = 0; j < board.length; j++) {
            char c = board[j][i];
            if (Character.isDigit(c)) {
                tempList.add(board[j][i]);
                tempHashSet.add(board[j][i]);
            }
        }
        //Indicates duplication in column
        if (tempList.size() > tempHashSet.size()) {
            System.out.printf("%s is not valid:\n\t Error on Column %d\n", boardName, i + 1);
            return false;
        } else {
            tempList.clear();
            tempHashSet.clear();
        }
    }


    //Check 3x3 box
    int rowStart = 0;
    int columnStart = 0;

    //Skips to next row start
    for (int i = rowStart; i < board.length; i += 3) {
        //Skips to next column start
        columnStart = 0;
        for (int j = columnStart; j < board.length; j += 3) {
            if  (!checkBoxes(board, i, j, boardName))
                return false;
        }
    }
        System.out.printf("%s is valid\n", boardName);
        return true;
    }

    //Checks one single box
    public static boolean checkBoxes(char[][] board, int rowStart, int columnStart, String boardName) {
        List<Character> tempList = new ArrayList<>();
        Set<Character> tempHashSet = new HashSet<>();
        int columnCounter = 0;
        int rowCounter = 0;
        for (int i = rowStart; rowCounter < board.length / 3; i++, rowCounter++) {
            columnCounter = 0;
            for (int j = columnStart; columnCounter < board.length / 3; j++, columnCounter++) {
                char c = board[i][j];
                if (Character.isDigit(c)) {
                    tempList.add(c);
                    tempHashSet.add(c);
                }
            }
        }

        //Indicates duplication in the inner box
        if (tempList.size() > tempHashSet.size()) {
            System.out.printf("%s is not valid:\n\t Error in box: %d. on Row - %d. on Column\n", boardName, (rowStart / 3) + 1, (columnStart / 3) + 1);
            return false;
        }

        return true;
    }
}
