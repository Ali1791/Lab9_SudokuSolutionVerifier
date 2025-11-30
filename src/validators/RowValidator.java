package validators;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author mariam
 */
public class RowValidator implements SudokuValidator {

    private final Integer rowIndex;//to allow null

    public RowValidator() {
        this.rowIndex = null;
    }

    public RowValidator(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public boolean validate(int[][] board, List<String> errors) {
        if (rowIndex != null) {
            // Validate single row
            return validateSingleRow(board, errors, rowIndex);
        } else {
            // Validate all rows
            return validateAllRows(board, errors);
        }
    }

    public boolean validateAllRows(int[][] board, List<String> errors) {
        for (int i = 0; i < 9; i++) {
            if (!validateSingleRow(board, errors, i)) {
                return false;
            }
        }
        return true;
    }

    public boolean validateSingleRow(int[][] board, List<String> errors, int rowIndex) {
        Set<Integer> seen = new HashSet<>();
        for (int j = 0; j < 9; j++) {
            int num = board[rowIndex][j];
            if ( num != 0 && !seen.add(num)) {
                errors.add("ROW " + (rowIndex + 1) + ", #" + num + ", [" + getRowAsString(board, rowIndex) + "]");
                return false;
            }
        }
        return true;
    }

    private String getRowAsString(int[][] board, int rowIndex) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 9; j++) {
            sb.append(board[rowIndex][j]);
            if (j < 8) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

}
