import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Dima", 34);
        Person person2 = new Person("Vika", 33);
        Person person3 = new Person("Sonya", 2);
        DB.addPerson(person1);
        DB.addPerson(person2);
        DB.addPerson(person3);

        List<Person> personList = List.of(new Person[]{person1, person2, person3});
        for (Person person : personList) {
            System.out.println(person);
        }

    }
}
