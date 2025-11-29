/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modes;

import java.util.List;
import validators.BoxValidator;
import validators.ColumnValidator;
import validators.CombinedValidator;
import validators.RowValidator;
import validators.SudokuValidator;

/**
 *
 * @author AltAWKEl
 */
public class Mode0Validator implements ModeValidator{

  

    @Override
    public boolean validate(int[][] board, List<String> errors) {
        
        SudokuValidator row = new RowValidator();
        SudokuValidator col = new ColumnValidator();
        SudokuValidator box = new BoxValidator();

        CombinedValidator validator = new CombinedValidator(row, col, box);

        return validator.validate(board, errors);
    }

    
}
