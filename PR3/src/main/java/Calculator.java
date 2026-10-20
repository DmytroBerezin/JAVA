public class Calculator {

    // Додавання
    public double add(double a, double b) {
        return a + b;
    }

    // Віднімання
    public double subtract(double a, double b) {
        return a - b;
    }

    // Множення
    public double multiply(double a, double b) {
        return a * b;
    }

    // Ділення
    public double divide(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Помилка: Ділення на нуль неможливе!");
        }
        return a / b;
    }

    // Квадратний корінь (з перевіркою від'ємних чисел)
    public double sqrt(double a) throws InvalidInputException {
        if (a < 0) {
            throw new InvalidInputException("Помилка: Неможливо обчислити квадратний корінь з від'ємного числа (" + a + ")!");
        }
        return Math.sqrt(a);
    }
}