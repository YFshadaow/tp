package seedu.financialplanner.commands;

import seedu.financialplanner.commands.utils.Command;
import seedu.financialplanner.commands.utils.RawCommand;
import seedu.financialplanner.goal.Goal;
import seedu.financialplanner.goal.WishList;
import seedu.financialplanner.utils.Ui;

@SuppressWarnings("unused")
public class SetGoalCommand extends Command {
    public static final String NAME = "set";

    public static final String USAGE =
            "set </g AMOUNT> </l LABEL>";
    private final String label;
    private final int amount;

    public SetGoalCommand(RawCommand rawCommand) throws IllegalArgumentException {
        String labelString = String.join(" ", rawCommand.args);
        if (!rawCommand.extraArgs.containsKey("g")) {
            throw new IllegalArgumentException("Goal must have an amount");
        }
        try {
            amount = Integer.parseInt(rawCommand.extraArgs.get("g"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Amount must be a number");
        }
        rawCommand.extraArgs.remove("g");
        if (!rawCommand.extraArgs.containsKey("l")) {
            throw new IllegalArgumentException("Please specify the content of the goal");
        }
        label = rawCommand.extraArgs.get("l");
        rawCommand.extraArgs.remove("l");
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new java.util.ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    @Override
    public void execute() {
        Goal goal = new Goal(label, amount);
        WishList.getInstance().list.add(goal);
        Ui.getInstance().showMessage("You have added " + goal);
    }
}
