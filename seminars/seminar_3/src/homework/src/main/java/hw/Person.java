package hw;

import org.json.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;
import javax.persistence.*;

public class Person {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    // Конструктор по умолчанию для JPA
    public Person() {}

    public void serializePerson(String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        }
    }
    
    public static Person deserializePerson(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Person) in.readObject();
        }
    }

    // Методы сериализации и десириализации в формате JSON
    public void serializePersonToJson(String filename) {
        JSONObject jsonPerson = new JSONObject();
        jsonPerson.put("name", this.name);
        jsonPerson.put("age", this.age);

        try (FileWriter file = new FileWriter(filename)) {
            file.write(jsonPerson.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Person deserializePersonFromJson(String filename) {
        Person person = new Person();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(filename)) {
            Object obj = parser.parse(reader);
            org.json.simple.JSONObject jsonPerson = (org.json.simple.JSONObject) obj;
            person.setName((String) jsonPerson.get("name"));
            person.setAge(((Long) jsonPerson.get("age")).intValue());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return person;
    }

    // Метод создания нового объекта Person в базе данных
    public static void addPerson(Person person) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(person);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }

    // Метод обновления объекта Person в базе данных
    public static void updatePerson(Person person) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(person);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }

    // Метод удаления объекта Person из базы данных
    public static void deletePerson(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Person person = em.find(Person.class, id);
            em.remove(person);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }
}

