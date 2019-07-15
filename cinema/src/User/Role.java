package User;

import Authority.Manage;
import Authority.Normal;

/**
 * Created by diandian on 2019/7/7.
 */

public class Role {
    private String description;// 角色名
    private Manage Mgr = null;//管理权限
    private Normal normal = null;//顾客权限

    //常用函数
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Manage getMange() {
        return Mgr;
    }
    public void setMange(Manage Mgr) {
        this.Mgr = Mgr;
    }
    public void setNormal(Normal normal) {
        this.normal = normal;
    }
    public Normal getNormal() {
        return normal;
    }

    /**
    * search
    * */
    public void search(){
        if(normal == null&&Mgr==null){
            System.out.println("您还没有登录，请登陆后再操作");
            return;
        }else if(normal!=null){
            normal.search();
        }else{
            Mgr.search();
        }
    }

    /**
     * addMovie
     * */
    public void addMovie(){
        if(Mgr==null){
            System.out.println("您没有管理员权限");
            return;
        }
        Mgr.addMovie();
    }

    /**
     * delMovie
     * */
    public void delMovie(){
        if(Mgr==null){
            System.out.println("您没有管理员权限");
            return;
        }
        Mgr.delMovie();
    }
    /**
     * addShow
     * */
    public void addShow(){
        if(Mgr==null){
            System.out.println("您没有管理员权限");
            return;
        }
        Mgr.addShow();
    }

    /**
     * delShow
     * */
    public void delShow(){
        if(Mgr==null){
            System.out.println("您没有管理员权限");
            return;
        }
        Mgr.delShow();
    }
    /**
     * change
     * */
    public void change(){
        if(normal == null){
            System.out.println("您还没有登录，请登陆后再操作");
            return;
        }
        normal.change();
    }

    /**
     * order
     * */
    public void order(){
        if(normal == null){
            System.out.println("您还没有登录，请登陆后再操作");
            return;
        }
        normal.order();
    }

    /**
     * refund
     * */
    public void refund(){
        if(normal == null){
            System.out.println("您还没有登录，请登陆后再操作");
            return;
        }
        normal.refund();
    }

}
