package DBopeartion;

import entity.Hall;

import java.util.List;

public interface HallDao {
    /**
     * 查找大厅*/
    public abstract List<Hall> findHall();
    /**
     * 特定条件查找大厅*/
    public abstract List<Hall> getHall(String sql,String[] param);
}
