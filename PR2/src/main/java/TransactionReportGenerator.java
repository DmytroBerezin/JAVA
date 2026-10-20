import java.util.List;
import java.util.stream.Collectors;

public abstract class TransactionReportGenerator {

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance + " грн");
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("\n--- 10 найбільших витрат ---");
        for (Transaction expense : topExpenses) {
            System.out.printf("%s: %.2f грн%n", expense.getDescription(), expense.getAmount());
        }
    }

    // Візуалізація з зірочками (*)
    public static void printExpensesByCategoryReport(List<Transaction> transactions) {
        System.out.println("\n--- Звіт по витратах за категоріями (1 * = 1000 грн) ---");

        transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        Transaction::getDescription,
                        Collectors.summingDouble(t -> Math.abs(t.getAmount()))
                ))
                .forEach((category, sum) -> {
                    int asterisks = (int) (sum / 1000);
                    String visual = "*".repeat(Math.max(1, asterisks));
                    System.out.printf("%-15s : %-10s (%.2f грн)%n", category, visual, sum);
                });
    }
}