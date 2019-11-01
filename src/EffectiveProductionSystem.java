import java.util.List;
import java.util.ArrayList;

public class EffectiveProductionSystem {

    private List<User> users = new ArrayList<>();
    private List<Request> requests = new ArrayList<>();
    private List<Instrument> instruments = new ArrayList<>();
    private User currentUser;

    public static void main(String[] args) {

    }

    private void addUser(String name, String login, String password, String repeation) throws Exception {
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

    private User findUser(String login) throws Exception {
        for (User user : users)
            if (user.getLogin().equals(login))
                return user;
        throw new Exception("Пользователь с таким логином не найден");
    }

    private void processRequests() {

    }
}
