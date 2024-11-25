package homework.src.main.java.hw;

import hw.Person;

import javax.persistence.EntityManager;
import java.io.IOException;

/*
Задание 1: Создайте класс Person с полями name и age.
Реализуйте сериализацию и десериализацию этого класса в файл.
Задание 2: Используя JPA, создайте базу данных для хранения
объектов класса Person. Реализуйте методы для добавления,
обновления и удаления объектов Person.
 */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Person person = new Person();
        person.setName("Vika");
        person.setAge(33);

        // Сериализация объекта в файл
        try {
            person.serializePerson("person.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Десериализация объекта из файла
        try {
            Person deserializedPerson = Person.deserializePerson("person.ser");
            System.out.println("Десериализованный объект: " + deserializedPerson.getName() + ", " + deserializedPerson.getAge());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Добавление объекта Person в базу данных
        Person.addPerson(person);

        // Обновление объекта Person в базе данных (если нужно)
         person.setAge(21);
         Person.updatePerson(person);

        // Удаление объекта Person из базы данных (если нужно)
         Long personId = person.getId();
         Person.deletePerson(personId);
    }
}
