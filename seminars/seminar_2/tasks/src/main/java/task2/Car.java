package task2;

public class Car {
    public String model;
    public String color;
    public int release;

    public Car(String model, String color, int release) {
        this.model = model;
        this.color = color;
        this.release = release;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", release=" + release +
                '}';
    }
}
