import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class StudentSurnameStorage {
    private TreeMap<String, Set<Long>> surnamesTreeMap = new TreeMap<>();

    public void studentCreated(Long id, String surname){
        Set<Long> existingIds = surnamesTreeMap.getOrDefault(surname, new HashSet<>());
        existingIds.add(id);
        surnamesTreeMap.put(surname, existingIds);
    }

    public void studentDeleted(Long id, String surname){
        surnamesTreeMap.get(surname).remove(id);
    }
    public void studentUpdate(Long id, String oldSurname, String newSurname){
        surnamesTreeMap.get(oldSurname).remove(id);
        studentDeleted(id, oldSurname);
        studentCreated(id, newSurname);
    }

    public Set<Long> getStudentBySurnamesLessOrEqualThan(String surname){
        Set<Long> res =  surnamesTreeMap.headMap(surname, true)
                .values()
                .stream()
                .flatMap(longs -> longs.stream())
                .collect(Collectors.toSet());
        String str = " ";
        if(str.isEmpty()){
            System.out.println("Полный список студентов");
            res.forEach(System.out::println);
        } else if (surname.contains(",")) {
            String[] surnames = surname.split(",");
            if(surnames.length == 2){
                String surname1 = surnames[0].trim();
                String surname2 = surnames[1].trim();
                System.out.println("Студенты с фамилиями от " + surname1 + " до " + surname2 + ":");
                res.stream()
                        .filter(s -> s.compareTo(Long.valueOf(surname1)) >= 0 && s.compareTo(Long.valueOf(surname2)) <= 0)
                        .forEach(System.out::println);
            } else {
                System.out.println("Ошибка: Введите две фамилии, разделенные запятой.");
            }
        } else {
            System.out.println("Студент(ы) с фамилией " + surname + ":");
            res.stream()
                    .filter(s -> s.equals(surname))
                    .forEach(System.out::println);
            }
        return res;
    }
}
