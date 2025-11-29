/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modes;

import java.util.List;
import validators.BoxValidator;
import validators.ColumnValidator;
import validators.RowValidator;

/**
 *
 * @author AltAWKEl
 */
public class Mode27Validator implements ModeValidator {

    @Override
    public boolean validate(int[][] board, List<String> errors) {

        Thread[] tasks = new Thread[27];
        
        int index = 0;

        // create threads for 9 rows
        for (int r = 0; r < 9; r++) {
            int row = r;
            tasks[index++] = new Thread(() -> {
                new RowValidator(row).validate(board, errors);
            });
        }

        // create threads for 9 columns
        for (int c = 0; c < 9; c++) {
            int col = c;
            tasks[index++] = new Thread(() -> {
                new ColumnValidator(col).validate(board, errors);
            });
        }

        // create threads for 9 boxes
        for (int b = 0; b < 9; b++) {
            int boxRow = b / 3;
            int boxCol = b % 3;
            tasks[index++] = new Thread(() -> {
                new BoxValidator(boxRow, boxCol).validate(board, errors);
            });
        }

        // Start for each thread
        for (Thread t : tasks) {
            t.start();
        }

        // Join for each thread
        for (Thread t : tasks) {
            try {
                t.join();
            } catch (InterruptedException e) {
            }
        }

        return errors.isEmpty();
    }

}
