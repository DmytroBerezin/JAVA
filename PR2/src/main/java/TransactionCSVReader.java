import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionCSVReader {

    private static final String CSV_DATA =
            "Дата,Сума,Опис\n" +
                    "05-12-2023,-1500.00,Продукти\n" +
                    "10-12-2023,25000.00,Зарплата\n" +
                    "12-12-2023,-3500.00,Оренда\n" +
                    "15-12-2023,-200.00,Кафе\n" +
                    "20-12-2023,-800.00,Компослуги\n" +
                    "22-12-2023,-1200.00,Продукти\n" +
                    "25-12-2023,-4500.00,Одяг\n" +
                    "28-12-2023,-300.00,Транспорт\n" +
                    "10-01-2024,25000.00,Зарплата\n" +
                    "15-01-2024,-1800.00,Продукти";

    public static List<Transaction> readTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new StringReader(CSV_DATA))) {
            String line;
            br.readLine(); // Пропускаємо заголовок (перший рядок)
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] values = line.split(",");
                Transaction transaction = new Transaction(
                        values[0].trim(),
                        Double.parseDouble(values[1].trim()),
                        values[2].trim()
                );
                transactions.add(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactions;
    }
}