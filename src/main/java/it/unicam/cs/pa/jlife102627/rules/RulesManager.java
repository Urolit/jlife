package it.unicam.cs.pa.jlife102627.rules;

import it.unicam.cs.pa.jlife102627.Model.CellInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/** Gestisce le leggi di vita e di morte delle SmartCell. */
public class RulesManager implements RulesManagerInterface{

    @Override
    public List<String> getAvailableRules() {
        List<String> available = new ArrayList<>();
        available.add("default");
        available.add("zombie");
        return available;
    }

    /** Default: se una cellula ha meno di 2 o piu di 3 vicini vivi, muore
     * se una cellula morta ha 3 vicini vivi, allora vive
     */
    @Override
    public HashMap<Predicate<Integer>, Consumer<CellInterface>> getDefaultRules() {
        HashMap<Predicate<Integer>, Consumer<CellInterface>> r = new HashMap<>();
        r.put(x -> x > 3 || x < 2, y -> y.setState(false));
        r.put( x -> x == 3, y -> y.setState(true));
        return r;
    }

    /** Zombie: se una cellula ha meno di 3 vicini,allora muore; se una cellula ha piu' di 3
     * vicini allora sopravvive/viene curato.
     * Creato per rendere un idea di come e' possibile personalizzare le regole
     * per le SmartCell
     */
    public HashMap<Predicate<Integer>, Consumer<CellInterface>> getZombieRules() {
        HashMap<Predicate<Integer>, Consumer<CellInterface>> r = new HashMap<>();
        //sopraffatto dagli zombie
        r.put( x -> x < 3 , y -> y.setState(false));
        //curato dai sopravvissuti
        r.put( x -> x > 3 , y -> y.setState(true));
        return r;
    }

    /**
     * In base all'input restituisce le regole da applicare alle SmartCell.
     * @param s nome delle regole
     * @return regole per le smartCell.
     */
    @Override
    public HashMap<Predicate<Integer>, Consumer<CellInterface>> getRules(String s) {
        switch (s.toLowerCase()){
            case "zombie": return getZombieRules();
            default: return getDefaultRules();
        }
    }

}
