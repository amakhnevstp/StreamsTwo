import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }


        Stream<Person> stream = persons.stream();

        System.out.println("Людей младше 18 лет:");
        System.out.println(stream.filter(x -> x.getAge() < 18).count());


        Stream<Person> streamMans = persons.stream();
        System.out.println();
        System.out.println("Фамилии мужчин от 18 и до 27 лет");
        List<String> filteredPersons = streamMans
                .filter(x -> (x.getAge() >= 18 && x.getAge() <= 27))
                .filter(x -> x.getSex() == Sex.MAN)
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
        System.out.println(filteredPersons);



        Stream<Person> streamEduc = persons.stream();
        System.out.println();
        System.out.println("Люди с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин:");

        List<String> filteredEduc = streamEduc
                .filter(x -> (x.getSex() == Sex.WOMEN ? x.getAge() >= 18 && x.getAge() <= 60 : x.getAge() >= 18 && x.getAge() <= 65))
                .filter(x -> x.getEducation() == Education.HIGHER)
                .map(x -> x.getFamily())
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println(filteredEduc);

    }
}



