package architecture;

/**
 * Created by User on 2017-11-19.
 */

public class User {
    public String login;
    public int password;
    public int authorisation;  // 0 - rodzic, 1 - nauczyciel, 2 - przedszkole
    public String name;
    public int id;

    public User(String login, int password, int authorisation, String name, int id) {
        this.login = login;
        this.password = password;
        this.authorisation = authorisation;
        this.name = name;
        this.id = id;
    }


    public User() {
    }
}
