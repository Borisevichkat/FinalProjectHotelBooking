package by.borisevich.hotel.controller.command;

import by.borisevich.hotel.controller.command.impl.Registration;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CommandProvider {

    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
       commands.put(CommandName.REGISTRATION, new Registration());
    }

    public Command takeCommand(String name){
        CommandName commandName;
        commandName = CommandName.valueOf(name.toUpperCase());
        return commands.get(commandName);
    }
}
