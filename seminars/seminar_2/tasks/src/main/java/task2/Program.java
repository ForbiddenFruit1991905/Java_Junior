package task2;

import java.lang.reflect.Field;

public class Program {
    /*
    * Задача 2: Применение Reflection API в разработке.
    * Реализуйте обобщенный метод, который принимает объект и выводит
    * значения всех его полей.
    * Создайте класс "Car" с различными полями (н-р, модель, цвет, год выпуска).
    * Применение Reflection API для вывода значений полей созданного объекта
    * класса "Car" с исп-ем ранее созданного метода.
     */

    public static void main(String[] args) throws IllegalAccessException {
        Car car = new Car("Toyota", "silver", 2022);
        task2(car);

    }

    private static  <T> void task2 (T obj) throws IllegalAccessException {
        Class<?> objClass = obj.getClass();

        Field[] fields = objClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.printf("%s: %s\n", field.getName(), field.get(obj));
        }
    }
}
