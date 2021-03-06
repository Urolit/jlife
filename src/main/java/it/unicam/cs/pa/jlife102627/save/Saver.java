package it.unicam.cs.pa.jlife102627.save;

import com.google.gson.Gson;
import it.unicam.cs.pa.jlife102627.ControllerInterface;
import it.unicam.cs.pa.jlife102627.Model.BoardInterface;
import it.unicam.cs.pa.jlife102627.Model.CellInterface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/** Ha la responsabilita' di  salvare la tabella attualmente presente nel controller. */
public class Saver implements SaveInterface{

    @Override
    public void save(ControllerInterface c, String path) throws IOException {
        String toAdd = "";
        Gson gson = new Gson();
        BoardInterface board = c.getBoard();
        for(int i = 0; i < board.getDim(); i++){
            for(int j = 0; j < board.getDim(); j++) {
                CellInterface cell = board.getCell(i, j);
                toAdd = toAdd.concat(gson.toJson(cell)+"\n");
            }
        }
        writeXtoY(toAdd,path.concat("/board.txt"));
    }

    /**
     * Responsabile di scrivere l'oggetto trasformato in stringa attraverso Gson nel file
     * @param s oggetto sotto forma di stringa
     * @param filename percorso del file in cui scrivere
     * @throws IOException se non riesce a scrivere sul file.
     */
    private void writeXtoY(String s, String filename) throws IOException {
        File file = new File(filename);
        FileWriter writer = new FileWriter(file,false);
        writer.append(s);
        writer.close();
    }

}
