package entity;

/**
 * Created by DiZi on 29.11.2015.
 */
public class User extends BaseEntity{

    /**
     *
     */
    int id;

    /**
     *
     */
    String login;

    /**
     *
     */
    String password;

    /**
     *
     * @return
     */
    public String getRole() {
        return role;
    }

    /**
     *
     */
    String role;

    /**
     *
     * @param login
     * @param role
     */
    public User(String login, String role) {
        this.role = role;
        this.login = login;
    }

    /**
     *
     */
    public User() {

    }

    /**
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getLogin() {
        return login;
    }

    /**
     *
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", id=" + id +
                '}';
    }

    /**
     *
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }
}
