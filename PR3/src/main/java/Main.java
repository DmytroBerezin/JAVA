import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.println("=== КАЛЬКУЛЯТОР (Практична робота №3) ===");
        System.out.println("Доступні операції: +, -, *, /, sqrt");

        System.out.print("Введіть операцію (+, -, *, /, sqrt): ");
        String operation = scanner.nextLine().trim();

        try {
            if (operation.equalsIgnoreCase("sqrt")) {
                System.out.print("Введіть число: ");
                double num = Double.parseDouble(scanner.nextLine());
                double result = calculator.sqrt(num);
                System.out.println("Результат √" + num + " = " + result);
            } else if (operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/")) {
                System.out.print("Введіть перше число: ");
                double num1 = Double.parseDouble(scanner.nextLine());

                System.out.print("Введіть друге число: ");
                double num2 = Double.parseDouble(scanner.nextLine());

                double result = 0;
                switch (operation) {
                    case "+":
                        result = calculator.add(num1, num2);
                        break;
                    case "-":
                        result = calculator.subtract(num1, num2);
                        break;
                    case "*":
                        result = calculator.multiply(num1, num2);
                        break;
                    case "/":
                        result = calculator.divide(num1, num2);
                        break;
                }
                System.out.println("Результат: " + result);
            } else {
                throw new InvalidInputException("Невідома операція: " + operation);
            }
        } catch (NumberFormatException e) {
            System.err.println("Помилка: Введено некоректні дані (очікувалося число)!");
        } catch (ArithmeticException e) {
            System.err.println(e.getMessage());
        } catch (InvalidInputException e) {
            System.err.println("Власне виключення: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Виникла непередбачена помилка: " + e.getMessage());
        } finally {
            System.out.println("----------------------------------------");
            System.out.println("Блок finally: Обробку запиту завершено.");
            scanner.close();
        }
    }
}