import java.util.Scanner;

public class Main {
    private static StudentCommandHandler STUDENT_COMMAND_HANDLER = new StudentCommandHandler();
    public static void main(String[] args) {

        System.out.println("Hello world");
        while (true){
        printMessage();
        Command command = readCommand();
        if(command.getAction() == Action.EXIT){
            return;
        } else if(command.getAction() == Action.ERROR){
            continue;
        } else {
            STUDENT_COMMAND_HANDLER.processCommand(command);
        }
        }
    }

    private static Command readCommand(){
        Scanner scanner = new Scanner(System.in);
        try {
            String code = scanner.nextLine();
            Integer actionCode = Integer.valueOf(code);
            Action action = Action.UPDATE.fromCode(actionCode);
            if(action.isRequireAdditionalData()){
                String data = scanner.nextLine();
                return new Command(action, data);
            } else {
                return new Command(action);
            }
        } catch (Exception ex){
            System.out.println("Проблемма обработки ввода. " + ex.getMessage());
            return new Command(Action.ERROR);
        }
    }

    private static void printMessage(){
        System.out.println("---------------------");
        System.out.println("0. Выход");
        System.out.println("1. Создание данных");
        System.out.println("2. Обновлене данных");
        System.out.println("3. Удаление данных");
        System.out.println("4. Вывод статистики по курсам");
        System.out.println("5. Вывод студентов по городам");
        System.out.println("6. Поиск по фамилии");
        System.out.println("---------------------");
    }
}
