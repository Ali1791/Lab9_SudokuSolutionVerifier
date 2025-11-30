/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modes;

import java.util.ArrayList;
import java.util.List;
import validators.BoxValidator;
import validators.ColumnValidator;
import validators.RowValidator;
import validators.SudokuValidator;

/**
 *
 * @author AltAWKEl
 */
public class Mode3Validator implements ModeValidator {

    @Override
    public boolean validate(int[][] board, List<String> errors) {

        SudokuValidator[] validators = {
            new RowValidator(),
            new ColumnValidator(),
            new BoxValidator()
        };
        List<Thread> threads = new ArrayList<>();

        for (SudokuValidator v : validators) {
            Thread t = new Thread(() -> v.validate(board, errors));
            threads.add(t);
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException ignored) {
            }
        }

        return errors.isEmpty();
    }

}
