import lombok.Data;

import java.util.Objects;

/**
 * @Author: Zhou YingTao
 * @Date: 2018/9/14
 * @Description:
 */

@Data
public class User {
    Integer id;
    String name;
    String jsonStr;

    public static final User EMPTY = new User();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(jsonStr, user.jsonStr);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, name, jsonStr);
    }
}
