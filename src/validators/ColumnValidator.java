package validators;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author mariam
 */
public class ColumnValidator implements SudokuValidator {

        private final Integer columnIndex;//to allow null

    public ColumnValidator() {
        this.columnIndex = null;
    }

    public ColumnValidator(int columnIndex) {
        this.columnIndex = columnIndex;
    }
    @Override
    public boolean validate(int[][] board, List<String> errors) {
         if (columnIndex != null) {
            // Validate single column
            return validateSingleColumn(board, errors, columnIndex);
        } else {
            // Validate all columns
            return validateAllColumns(board, errors);
        }
    }

        public boolean validateAllColumns(int[][] board, List<String> errors) {
        for (int i = 0; i < 9; i++) {
                if (!validateSingleColumn(board, errors,i)) {
                    return false;
                }
            }
        return true;
    }

      public boolean validateSingleColumn(int[][] board, List<String> errors,int columnIndex) {
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            int num = board[i][columnIndex];
            if (num != 0 && !seen.add(num)) {
                 errors.add("COL " + (columnIndex + 1) + ", #" + num + ", [" + getColumnAsString(board, columnIndex) + "]");
                 return false;
            }
        }
        return true;
    }
      
    private String getColumnAsString(int[][] board, int columnIndex) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 9; i++) {
        sb.append(board[i][columnIndex]);
        if (i < 8) {
            sb.append(", ");
        }
    }
    return sb.toString();
}

}
