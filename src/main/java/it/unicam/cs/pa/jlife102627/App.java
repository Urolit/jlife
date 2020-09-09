package it.unicam.cs.pa.jlife102627;

import it.unicam.cs.pa.jlife102627.rules.RulesManager;
import it.unicam.cs.pa.jlife102627.view.ViewCli;
import it.unicam.cs.pa.jlife102627.view.ViewInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.function.Consumer;

public class App {

    private final ControllerInterface controller;
    private final ViewInterface view;

    private HashMap<String, Consumer<ViewInterface>> commands;

    public App() {
        this.controller = new Controller();
        this.view = new ViewCli(this.controller);
    }

    public static void main(String[] args) throws IOException {
        App app = new App();
        app.generateCommands();
        app.start();
    }

    public void start() throws IOException {
        this.view.printHello();
        this.view.askLoad();
        generateCommands();
        while (true) {
            String command = this.view.getCommand();
            if(command.equals("exit")) {
                break;
            }
            Consumer<ViewInterface> action = this.commands.get(command);
            if(action == null){
                this.view.unknown();
                continue;
            }
            action.accept(this.view);
        }
        this.view.printGoodbye();
    }

    private void generateCommands(){
        this.commands = new HashMap<>();
        this.commands.put("", ViewInterface::nextTime);
        this.commands.put("next", ViewInterface::nextTime);
        this.commands.put("new", x-> {
            try {
                x.newBoard();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        /*
        TODO
        save - load -
         */
    }
}
