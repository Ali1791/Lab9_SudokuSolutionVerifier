package validators;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RowValidator  implements SudokuValidator {

    private final int rowIndex;

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


}
