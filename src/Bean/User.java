package Bean;

import java.util.Objects;

/**
 * @author lrd
 * @date 2022-10-13 下午2:06
 */
public class User {
    private String id;
    private String password;
    private String img;

    public User() {
    }

    public String getId() {
        return id;
    }

    public User(String id, String password, String img) {
        this.id = id;
        this.password = password;
        this.img = img;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password);
    }
}
