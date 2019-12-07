import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class EffectiveProductionSystem {

    private static List<User> users = new ArrayList<>();
    private static List<Request> requests = new ArrayList<>();
    private static List<Instrument> instruments = new ArrayList<>();
    private static User currentUser;

    public static void main(String[] args) {
        String choice = "";
        Scanner scan = new Scanner(System.in);
        String name, login, password, repeation;
        while (!choice.equals("0") && !choice.equals("1") && !choice.equals("2")) {
            System.out.println("1. Регистрация в системе\r\n" +
                    "2. Вход в систему\r\n" +
                    "0. Выход");

            choice = scan.nextLine();

            switch(choice) {

                case("1"):
                    System.out.println("Введите имя пользователя");
                    name = scan.nextLine();
                    System.out.println("Введите логин");
                    login = scan.nextLine();
                    System.out.println("Введите пароль");
                    password = scan.nextLine();
                    System.out.println("Повторите пароль");
                    repeation = scan.nextLine();
                    try {
                        addUser(name, login, password, repeation);
                        currentUser = findUser(login);
                        System.out.printf("Пользователь %s добавлен\r\n", name);
                        if (currentUser instanceof Admin) {
                            adminMenu();
                        }
                        else {
                            clientMenu();
                        }
                    } catch (InterruptedException e) {
                        choice = "";
                    } catch (Exception e) {
                        System.out.println(e.toString());
                        choice = "";
                    }
                    break;

                case("2"):
                    System.out.println("Введите логин");
                    login = scan.nextLine();
                    System.out.println("Введите пароль");
                    password = scan.nextLine();
                    try {
                        User temp = findUser(login);
                        if (temp.enter(login, password)) {
                            currentUser = temp;
                            if (currentUser instanceof Admin) {
                                adminMenu();
                            }
                            else {
                                clientMenu();
                            }
                        }
                    } catch (InterruptedException e) {
                        choice = "";
                    } catch (Exception e) {
                        System.out.println(e.toString());
                        choice = "";
                    }
                    break;

                case("0"):
                    break;

                default:
                    System.out.println("Введено неверное значение");
                    break;
            }
        }
    }

    private static void adminMenu() throws InterruptedException {
        String choice = "";
        Scanner scan = new Scanner(System.in);

        System.out.println("Добро пожаловать, администратор " + currentUser.getName());

        while (!choice.equals("0")) {
            System.out.println("1. Добавить оборудование\r\n" +
                    "2. Добавить заказ\r\n" +
                    "3. Составить план загрузки оборудования\r\n" +
                    "9. Сменить пользователя\r\n" +
                    "0. Выход");

            choice = scan.nextLine();

            switch (choice) {

                case("1"):
                    System.out.println("Введите название инструмента");
                    String instrName = scan.nextLine();
                    int count = 0;
                    while (count <= 0) {
                        System.out.println("Сколько инструментов вы хотите добавить?");
                        count = scan.nextInt();
                    }
                    instruments.add(new Instrument(instrName, count));
                    break;

                case("2"):
                    Request request = new Request();
                    int num = 0, time = 0, sel = 0;
                    while (num <= 0) {
                        System.out.println("Сколько частей будет в заказе?");
                        num = scan.nextInt();
                    }
                    for (int i = 0; i < num; i++) {
                        System.out.println("Cписок интрументов:");
                        for (int j = 0; j < instruments.size(); j++) {
                            System.out.printf("%d. %s. Количество: %d\r\n", j + 1, instruments.get(j).getName(), instruments.get(j).getCount());
                        }
                        while (sel <= 0 || sel > instruments.size()) {
                            System.out.println("Выберите инструмент");
                            sel = scan.nextInt();
                        }
                        Instrument instr = instruments.get(sel - 1);
                    }
                    break;

                case("3"):
                    break;

                case("9"):
                    throw new InterruptedException();

                case("0"):
                    break;

                default:
                    System.out.println("Введено неверное значение");
                    break;
            }
        }
    }

    private static void clientMenu() throws InterruptedException {
        String choice = "";
        Scanner scan = new Scanner(System.in);

        System.out.println("Добро пожаловать, " + currentUser.getName());

        while (!choice.equals("0")) {
            System.out.println("1. Добавить заказ\r\n" +
                    "2. Составить план загрузки оборудования\r\n" +
                    "9. Сменить пользователя\r\n" +
                    "0. Выход");

            choice = scan.nextLine();

            switch (choice) {

                case("1"):
                    break;

                case("2"):
                    break;

                case("9"):
                    throw new InterruptedException();

                case("0"):
                    break;

                default:
                    System.out.println("Введено неверное значение");
                    break;
            }
        }
    }

    private static void addUser(String name, String login, String password, String repeation) throws Exception {
        for (User user : users)
            if (user.getLogin().equals(login))
                throw new Exception("Пользователь с таким логином уже существует");
        if (!password.equals(repeation))
            throw new Exception("Пароли не совпадают");
        if (users.isEmpty())
            users.add(new Admin(name, login, password));
        else
            users.add(new Client(name, login, password));
    }

    private static User findUser(String login) throws Exception {
        for (User user : users)
            if (user.getLogin().equals(login))
                return user;
        throw new Exception("Пользователь с таким логином не найден");
    }

    private static void processRequests() {

    }
}
