package member.login;

/**
 * Created by User on 2017-04-29.
 */
public class AuthInfo {
    private Long id;
    private int level;
    private String email;
    private String name;

    public AuthInfo(Long id, int level, String email, String name) {
        this.id = id;
        this.level = level;
        this.email = email;
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AuthInfo{" +
                "id=" + id +
                ", level=" + level +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
