/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validators;

import java.util.List;

/**
 *
 * @author mariam
 */
public class CombinedValidator implements SudokuValidator {

    private final SudokuValidator rowValidator;
    private final SudokuValidator colValidator;
    private final SudokuValidator boxValidator;

    public CombinedValidator(SudokuValidator rowValidator, SudokuValidator colValidator, SudokuValidator boxValidator) {
        this.rowValidator = rowValidator;
        this.colValidator = colValidator;
        this.boxValidator = boxValidator;
    }

    @Override
    public boolean validate(int[][] board, List<String> errors) {
        return rowValidator.validate(board, errors)
                && colValidator.validate(board, errors)
                && boxValidator.validate(board, errors);
    }

}
