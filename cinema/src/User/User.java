package User;

import Authority.impl.DefaultCustomer;
import Authority.impl.DefaultManage;
import DBopeartion.UserDao;
import DBopeartion.impl.UserDaoimpl;
import entity.Auident;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by diandian on 2019/7/7.
 */
public class User {
    private Role role = null;
    HashMap<String,Auident> map=new HashMap<String,Auident>();
    public UserDao  uerdao= new UserDaoimpl();

    //数据库中读取观众信息
    public boolean init(){
        List <Auident> auidentList = uerdao.getAllUser();
        for(int i=0;i<auidentList.size();i++){
            Auident auident = new Auident();
            auident = auidentList.get(i);
            map.put(auident.getAud_name(),auident);
        }
        if(map.size()>0)
            return true;
        return false;
    }
    /**
    *注册函数
    * edited by dean
    */
    public boolean login(){
        role = new Role();
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = scanner.next();
        System.out.println("请输入密码");
        String password = scanner.next();
        //管理员登录
        if(!map.containsKey(username)) {
            System.out.println("用户名不存在！");
            return false;
        } else if(map.get(username).getAid_password().equals(password)&&map.get(username).getAud_type().equals("Manger")){
            role.setDescription("管理员");
            DefaultManage admin = new DefaultManage(map.get(username).getAud_id());
            role.setMange(admin);
            return true;
        }else if(map.get(username).getAud_type().equals("Manger")){
            System.out.println("管理员密码错误");
            return false;
        }
        //用户登录
        else if(!map.get(username).getAid_password().equals(password))
        {
            System.out.println("密码错误！重新输入");
            password=scanner.next();
            if(!map.get(username).getAid_password().equals(password)){
                System.out.println("密码错误！重新输入");
                password=scanner.next();
                if(!map.get(username).getAid_password().equals(password)) {
                    System.out.println("输入错误三次，本次登陆失败");
                    return false;
                }else {
                    role.setDescription("用户");
                    DefaultCustomer audience = new DefaultCustomer(map.get(username).getAud_id());
                    role.setNormal(audience);
                    return true;}
            }else {
                role.setDescription("用户");
                DefaultCustomer audience = new DefaultCustomer(map.get(username).getAud_id());
                role.setNormal(audience);
                return true;}
        }else {
            role.setDescription("用户");
            DefaultCustomer audience = new DefaultCustomer(map.get(username).getAud_id());
            role.setNormal(audience);
            return true;
        }
    }
    //注册函数
    //edited by dean
    //新增用户放到范型集合里
    public boolean register(){
        System.out.println("请输入要注册的用户名(用户名长度3~20)：");
        Scanner scanner=new Scanner(System.in);
        String name=scanner.nextLine();
        System.out.println("请输入密码");
        String password=scanner.nextLine();
        if(name.length()>=3&&name.length()<=20)
        {
            if(map.containsKey(name))
            {
                System.out.println("用户名已存在");
                return false;
            }
            else
            {
                System.out.println("请再次输入密码：");
                String password2=scanner.nextLine();
                if(password.equals(password2))
                {
                    System.out.println("请输入您的电话");
                    String tel = scanner.next();
                    save(map.size()+1,name,tel,"User",password);
                    map.put(name,new Auident(map.size()+1,name,tel,"User",password));
                    return true;
                }
                else
                    System.out.println("两次密码输入必须相同！");
                return false;
            }
        }
        else
            System.out.println("请输入正确长度的用户名！");
        return false;
    }

    public void save(int i,String name,String tel,String type, String password){
        Auident auident = new Auident(i,name,tel,type,password);
        if(uerdao.AddUser(auident)>0)
            System.out.println("用户信息保存成功");

    }
    //TODO 忘记密码
    public void forget(){

    }

    public void search() {
        role.search();
    }

    public void order() {
        role.order();
    }

    public void refund() {
        role.refund();
    }

    public void change() {
        role.change();
    }
    public void addMovie() {
        role.addMovie();
    }

    public void delMovie() {
        role.delMovie();
    }

    public void addShow() {
        role.addShow();
    }

    public void delShow() {
        role.delShow();
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}
