import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
          Напишите программу, которая использует Stream API
          для обработки списка чисел. Программа должна вывести
          на экран среднее значение всех четных чисел в списке.
         */
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        double averageOfEvenNumbers = nums.stream()
                .filter(num -> num % 2 == 0)
                .mapToInt(Integer::intValue) // Преобразование в IntStream для вычисления среднего
                .average()
                .orElse(0.0); // Возврат 0, если списка четных чисел нет
        System.out.println("Среднее значение всех четных чисел: " + averageOfEvenNumbers);
    }
}