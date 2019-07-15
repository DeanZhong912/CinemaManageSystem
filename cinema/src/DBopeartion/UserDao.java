package DBopeartion;

import java.util.HashMap;
import java.util.List;
import entity.Auident;
/**
 * Created by diandian on 2019/7/11.
 */
public interface UserDao {
    public abstract List<Auident> getAllUser();
    public abstract int AddUser(Auident user);
}
