import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class UMarket {

    private final Collection<Thing> things;

    public UMarket() {
        things = new ArrayList<>();
        initializeThings();
    }

    private void initializeThings() {
        things.add(new Pen());
        things.add(new NoteBook());

        things.add(new Chicken());
        things.add(new Fruit());
        things.add(new OliveOil());

        things.add(new BalykCheese());
        things.add(new Crisps());
        things.add(new ChocolateBar());

        things.add(new DumplingsBerries());
        things.add(new DumplingsMeet());
        things.add(new Cheburek());
    }

    public <T extends Thing> T getThingByIndex(Class<T> clazz, int index) {
//        int counter = 1;
//        for (var thing : things) {
//            if (clazz.isAssignableFrom(thing.getClass())) {
//                if (index == counter++)
//                    return (T)thing;
//            }
//        }
//        return null;

        AtomicInteger counter = new AtomicInteger(1);
        return things.stream()
                .filter(clazz::isInstance)
                .filter(thing -> index == counter.getAndIncrement())
                .map(clazz::cast)
                .findFirst()
                .orElse(null);
    }

    public <T extends Thing> Collection<T> getThings(Class<T> clazz) {
//        Collection<T> list = new ArrayList<>();
//        for (var thing : things) {
//            if (clazz.isAssignableFrom(thing.getClass())) {
//                list.add((T)thing);
//            }
//        }
//        return list;

        return things.stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .collect(Collectors.toList());
    }

    public <T extends Thing> void printThings(Class<T> clazz) {

//        int index = 1;
//        for (var thing : things) {
//            if(clazz.isInstance(thing)) {
//                if (Food.class.isAssignableFrom(thing.getClass())) {
//                    System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n", index++, thing.getName(), ((Food)thing).getProteins(),
//                            ((Food)thing).getFats() ? "Да" : "Нет", ((Food)thing).getCarbohydrates() ? "Да" : "Нет");
//                } else {
//                    System.out.printf("[%d] %s\n", index++, thing.getName());
//                }
//            }
//        }
        int[] counter = {1};  // AtomicInteger index = new AtomicInteger(1); index.getAndIncrement()
        things.stream()
                .filter(clazz::isInstance)
                .forEach(thing -> {

                    System.out.printf(
                            Food.class.isAssignableFrom(thing.getClass()) ?
                                    "[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n" :
                                    "[%d] %s\n", counter[0]++, thing.getName(),
                            ((Food)thing).getProteins() ? "Да" : "Нет",
                            ((Food)thing).getFats() ? "Да" : "Нет",
                            ((Food)thing).getCarbohydrates() ? "Да" : "Нет"
                    );

//                    if (Food.class.isAssignableFrom(thing.getClass())) {
//                        System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n", counter[0]++, thing.getName(), ((Food)thing).getProteins(),
//                            ((Food)thing).getFats() ? "Да" : "Нет", ((Food)thing).getCarbohydrates() ? "Да" : "Нет");
//                    } else {
//                        System.out.printf("[%d] %s\n", counter[0]++, thing.getName());
//                    }
                });
    }
}
