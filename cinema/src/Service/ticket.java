package Service;

import entity.Arrange;
import entity.Show;

import java.util.List;

/**
 * @author Bear 客户查询、订购、修改、退票*/
public interface ticket {
    public void order();
    public void refund();
    /**
     * 查询票务信息*/
    public List<Arrange> search(String movie_name);
    public void change();
    public void find();
}
