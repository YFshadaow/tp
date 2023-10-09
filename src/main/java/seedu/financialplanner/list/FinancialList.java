package seedu.financialplanner.list;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class FinancialList {
    protected ArrayList<Cashflow> list = new ArrayList<>();

    private void printAddedCashflow(String line) {
        DecimalFormat decimalFormat = new DecimalFormat("####0.00");

        Cashflow cashflow = list.get(list.size() - 1);
        System.out.print("Added " + line + " of value: ");
        System.out.println(decimalFormat.format(round(cashflow.value, 2)) + " to the list.");
        System.out.println("type: " + cashflow.type);
        if (cashflow.recur != 0) {
            System.out.println("recurring every: " + cashflow.recur + " days");
        }
        System.out.println("balance: " + decimalFormat.format(round(Cashflow.balance, 2)));
    }

    //@author mhadidg-reused
    //Reused from https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    //@author mhadidg

    public void addIncome(double value, String type, int recur) {
        Income toAdd = new Income(value, type, recur);
        list.add(toAdd);
        printAddedCashflow("income");
    }

    public void addExpense(double value, String type, int recur) {
        Expense toAdd = new Expense(value, type, recur);
        list.add(toAdd);
        printAddedCashflow("expense");
    }
}