package Bean;

import java.util.Objects;

/**
 * @author lrd
 * @date 2022-10-04 下午12:37
 */
public class Info {
    private String id;
    private String name;
    private String gender;
    private String iclass;
    private String mobile;
    private String email;

    public Info(String id, String name, String gender, String iclass, String mobile, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.iclass = iclass;
        this.mobile = mobile;
        this.email = email;
    }

    public Info() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIclass() {
        return iclass;
    }

    public void setIclass(String iclass) {
        this.iclass = iclass;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return id + ' ' +
                name + ' ' +
                gender + ' ' +
                iclass + ' ' +
                mobile + ' ' +
                email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return id.equals(info.id) && name.equals(info.name) && gender.equals(info.gender) && iclass.equals(info.iclass) && Objects.equals(mobile, info.mobile) && Objects.equals(email, info.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, iclass, mobile, email);
    }
}
