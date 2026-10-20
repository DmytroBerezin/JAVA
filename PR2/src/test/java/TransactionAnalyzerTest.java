import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TransactionAnalyzerTest {

    @Test
    public void testCalculateTotalBalance() {
        Transaction t1 = new Transaction("01-01-2023", 1000.0, "Дохід");
        Transaction t2 = new Transaction("02-01-2023", -300.0, "Продукти");

        double result = TransactionAnalyzer.calculateTotalBalance(List.of(t1, t2));
        Assertions.assertEquals(700.0, result, "Розрахунок балансу працює некоректно");
    }

    @Test
    public void testFindTopExpenses() {
        Transaction t1 = new Transaction("01-01-2023", -100.0, "Кафе");
        Transaction t2 = new Transaction("02-01-2023", 500.0, "Дохід");
        Transaction t3 = new Transaction("03-01-2023", -2000.0, "Оренда");

        List<Transaction> top = TransactionAnalyzer.findTopExpenses(List.of(t1, t2, t3));

        Assertions.assertEquals(2, top.size());
        Assertions.assertEquals(-2000.0, top.get(0).getAmount()); // Найбільша витрата іде першою
    }
}