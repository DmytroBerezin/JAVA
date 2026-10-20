import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Transaction> transactions = TransactionCSVReader.readTransactions("");

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "12-2023";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        TransactionReportGenerator.printExpensesByCategoryReport(transactions);
    }
}