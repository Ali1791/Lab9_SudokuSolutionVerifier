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

    Thread[] rowThreads = new Thread[9];
    Thread[] colThreads = new Thread[9];
    Thread[] boxThreads = new Thread[9];

    int index = 0;

    // ========  ROW THREADS ========
    for (int r = 0; r < 9; r++) {
        int row = r;
        rowThreads[row] = new Thread(() -> {
            new RowValidator(row).validate(board, errors);
        });
    }

    // Start rows
    for (Thread t : rowThreads) t.start();
    // Wait rows
    for (Thread t : rowThreads) {
        try { t.join(); } catch (InterruptedException e) {}
    }

    // ========  COLUMN THREADS ========
    for (int c = 0; c < 9; c++) {
        int col = c;
        colThreads[col] = new Thread(() -> {
            new ColumnValidator(col).validate(board, errors);
        });
    }

    // Start columns
    for (Thread t : colThreads) t.start();
    // Wait columns
    for (Thread t : colThreads) {
        try { t.join(); } catch (InterruptedException e) {}
    }

    // ========  BOX THREADS ========
    for (int b = 0; b < 9; b++) {
        int boxRow = b / 3;
        int boxCol = b % 3;
        boxThreads[b] = new Thread(() -> {
            new BoxValidator(boxRow, boxCol).validate(board, errors);
        });
    }

    // Start boxes
    for (Thread t : boxThreads) t.start();
    // Wait boxes
    for (Thread t : boxThreads) {
        try { t.join(); } catch (InterruptedException e) {}
    }

    return errors.isEmpty();
}


}
