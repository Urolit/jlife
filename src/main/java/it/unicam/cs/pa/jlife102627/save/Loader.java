package it.unicam.cs.pa.jlife102627.save;

import com.google.gson.Gson;
import it.unicam.cs.pa.jlife102627.ControllerInterface;
import it.unicam.cs.pa.jlife102627.Model.Board;
import it.unicam.cs.pa.jlife102627.Model.BoardInterface;
import it.unicam.cs.pa.jlife102627.Model.Cell;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * ha la responsabilita' di caricare da un file la tabella per il controller.
 */
public class Loader implements LoadInterface {

    @Override
    public void load(ControllerInterface c, String path) throws FileNotFoundException {
        File f = new File(path);
        if (!f.getAbsoluteFile().exists())
            return;
        int dim = getDim(f);
        BoardInterface board = new Board(dim);
        Scanner scanner = new Scanner(f);
        Gson gson = new Gson();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                board.setCell(gson.fromJson(scanner.nextLine(), Cell.class), i, j);
            }
        }
        c.loadBoard(board);
    }

    /**
     * calcola la dimensione della tabella da creare.
     * @param f file del salvataggio
     * @return dimensione tabella
     * @throws FileNotFoundException file non trovato
     */
    public int getDim(File f) throws FileNotFoundException {
        Scanner scanner = new Scanner(f);
        int dim = 0;
        while (scanner.hasNextLine()) {
            dim++;
            scanner.nextLine();
        }
        dim = (int) Math.sqrt(dim);
        return dim;
    }
}

