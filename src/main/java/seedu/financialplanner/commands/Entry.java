package seedu.financialplanner.commands;

import seedu.financialplanner.list.FinancialList;
import seedu.financialplanner.utils.Ui;

public class Entry extends Command{
    private static final String INCOME = "income";
    private static final String EXPENSE = "expense";
    protected String input;

    public Entry(String input) {
        this.input = input;
    }

    private int determineRecur(String parameters) {
        if (parameters.contains("r/")) {
            int indexOfRecur = parameters.indexOf("r/");
            String recur = parameters.substring(indexOfRecur + 2).trim();
            return Integer.parseInt(recur);
        }
        return 0;
    }

    @Override
    public void execute(Ui ui, FinancialList list) {
        String[] split = input.split(" ", 2);
        String entryType = split[0];
        String parameters = split[1];
        int recur = determineRecur(parameters);
        int indexOfAmount = parameters.indexOf("a/");
        int indexOfType = parameters.indexOf("t/");
        double value = Double.parseDouble(parameters.substring(indexOfAmount + 2, indexOfType).trim());
        String type;
        if (recur == 0) {
            type = parameters.substring(indexOfType + 2).trim();
        } else {
            int indexOfRecur = parameters.indexOf("r/");
            type = parameters.substring(indexOfType + 2, indexOfRecur).trim();
        }

        switch (entryType) {
        case INCOME:
            list.addIncome(value, type, recur);
            break;
        case EXPENSE:
            list.addExpense(value, type, recur);
            break;
        default:
            ui.showMessage("Unidentified entry.");
            break;
        }
    }
}