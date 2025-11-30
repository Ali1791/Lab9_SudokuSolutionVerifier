package validators;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author mariam
 */
public class BoxValidator implements SudokuValidator {

    private final Integer boxRow;
    private final Integer boxCol;

    public BoxValidator() {
        this.boxRow = null;
        this.boxCol = null;
    }

    public BoxValidator(int boxRow, int boxCol) {
        this.boxRow = boxRow;
        this.boxCol = boxCol;
    }

    @Override
    public boolean validate(int[][] board, List<String> errors) {
        if (boxRow != null && boxCol != null) {
            // Validate single box
            return validateSingleBox(board, errors, boxRow, boxCol);
        } else {
            // Validate all boxes
            return validateAllBoxes(board, errors);
        }
    }

    public boolean validateAllBoxes(int[][] board, List<String> errors) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (!validateSingleBox(board, errors, row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Public method for validating any box
    public boolean validateSingleBox(int[][] board, List<String> errors, int boxRow, int boxCol) {
        Set<Integer> seen = new HashSet<>();
        int startRow = boxRow * 3;
        int startCol = boxCol * 3;
        int boxNumber = boxRow * 3 + boxCol + 1;// to get the box number 

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = board[startRow + i][startCol + j];
                if (!seen.add(num)) {
                     errors.add("BOX " + boxNumber + ", #1, [" + getBoxAsString(board, boxRow, boxCol) + "]");
                return false;
                }
            }
        }
        return true;
    }

    private String getBoxAsString(int[][] board, int boxRow, int boxCol) {
        StringBuilder sb = new StringBuilder();
        int startRow = boxRow * 3;
        int startCol = boxCol * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[startRow + i][startCol + j]);
                if (!(i == 2 && j == 2)) { // Not the last element
                    sb.append(", ");
                }
            }
        }
        return sb.toString();
    }

}
