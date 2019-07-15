package Authority;

/**
 * Created by diandian on 2019/7/7.
 */
/**
 * 影院管理员权限
 * 需具有查询,上/下架影片，新增/取消上映场次
 */
public interface Manage {
    public void search();
    public void addMovie();
    public void delMovie();
    public void addShow();
    public void delShow();
}
