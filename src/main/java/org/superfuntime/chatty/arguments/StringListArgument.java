package org.superfuntime.chatty.arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringListArgument implements ArgumentParser<List<String>> {
    StringArgument stringArgument = new StringArgument();

    @Override
    public List<String> parse(Scanner scanner) {
        List<String> items = new ArrayList<String>();
        while (scanner.hasNext()) {
            items.add(stringArgument.parse(scanner));
        }
        return items;
    }

    @Override
    public String getUsage() {
        return "a list of strings";
    }
}
