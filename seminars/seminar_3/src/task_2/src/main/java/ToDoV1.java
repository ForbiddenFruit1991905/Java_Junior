import java.io.Serializable;

public class ToDoV1 implements Serializable {
    private String title;
    private boolean isDone;

    public ToDoV1(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return isDone;
    }
}
