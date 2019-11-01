public class User {

    private String name;
    private String login;
    private String password;

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public boolean enter(String login, String password) {
        return ((login.equals(this.login)) && (password.equals(this.password)));
    }

    public String getName() { return this.name; }

    public String getLogin() { return this.login; }
}
