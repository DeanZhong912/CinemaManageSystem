package Service.impl;

import DBopeartion.ChangeMovieDao;
import DBopeartion.ChangeShowDao;
import DBopeartion.UserDao;
import DBopeartion.impl.ChangeMovieDaoimpl;
import DBopeartion.impl.ChangeShowDaoimpl;
import DBopeartion.impl.UserDaoimpl;
import DBopeartion.impl.ticketDaoimpl;
import DBopeartion.ticketDao;
import Service.ChangeMovie;
import Service.ChangeShow;
import entity.Auident;
import entity.Movie;
import entity.Show;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Manageservice extends service implements ChangeShow, ChangeMovie{
    public Manageservice(int Aud_id) {
        super(Aud_id);
    }

    @Override
    public void addMovie(){
        Scanner input=new Scanner(System.in);
        System.out.println("请输入电影id：");
        int movie_id=input.nextInt();
        System.out.println("请输入电影名：");
        String movie_name=input.next();
        System.out.println("请输入电影票价：");
        double movie_price=input.nextDouble();
        System.out.println("请输入影片类型：");
        String movie_type=input.next();
        System.out.println("请输入影片产地：");
        String movie_loc=input.next();
        System.out.println("请输入导演：");
        String movie_director=input.next();
        try {
            System.out.println("请输入上映时间：");
            String movie_sta = input.next();
            System.out.println("请输入下架时间：");
            String movie_en = input.next();
            Date movie_start = format.parse(movie_sta);
            Timestamp timeStamp_start = new Timestamp(movie_start.getTime());
            Date movie_end=format.parse(movie_en);
            Timestamp timeStamp_end = new Timestamp(movie_end.getTime());
            System.out.println("请输入影片时长：");
            int last=input.nextInt();
            Movie temp = new Movie(movie_id, movie_name, movie_price, movie_director,
                    movie_type, timeStamp_start, timeStamp_end, movie_loc,last);
            String sql = "insert into Movie(movie_id,movie_name,Base_price," +
                    "Category,Loc,Director,Start_time,End_time,last_time) values(?,?,?,?,?,?,?,?,?)";
            Object[] param={temp.getMovie_id(),temp.getMovie_name(),temp.getMovie_baseprice()
                    ,temp.getMovie_type(),temp.getMovie_loc(),temp.getMovie_director()
                    ,temp.getMovie_starttime(),temp.getMovie_endtime(),temp.getLast_time()};
            ChangeMovieDaoimpl changeMovieDaoimpl=new ChangeMovieDaoimpl();
            int count=changeMovieDaoimpl.updateMovie(sql,param);
            if(count>0)
                System.out.println("成功添加："+temp.getMovie_name()+"影片");
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delMovie(){
        Scanner input=new Scanner(System.in);
        System.out.println("请输入电影id：");
        int movie_id=input.nextInt();
        Movie temp=new Movie();
        temp.setMovie_id(movie_id);
        ChangeMovieDao changeMovieDaoimpl=new ChangeMovieDaoimpl();
        Object[] param={temp.getMovie_id()};
        temp=changeMovieDaoimpl.getMovie(param);
        String sql = "delete from Movie where Movie_id=?";
        int count=changeMovieDaoimpl.updateMovie(sql,param);
        if(count>0)
            System.out.println("成功删除："+temp.getMovie_name()+"影片");
    }
    @Override
    public void addShow(){
        Scanner input=new Scanner(System.in);
        System.out.println("请输入电影id：");
        int movie_id=input.nextInt();
        System.out.println("请输入放映厅id：");
        int hall_id=input.nextInt();
        input.nextLine();
        try {
            System.out.println("请输入播放时间：");
            String show = input.nextLine();
            Date shows = format2.parse(show);
            Timestamp show_time = new Timestamp(shows.getTime());
            Show temp=new Show(hall_id,movie_id,show_time);
            String str="insert into Show(Hall_id,Movie_id,Show_time) values(?,?,?)";
            Object[] param={temp.getHall_id(),temp.getMovie_id(),temp.getShow_time()};
            ChangeShowDaoimpl changeShowDaoimpl=new ChangeShowDaoimpl();
            int count=changeShowDaoimpl.updateShow(str,param);
            if(count>0){
                System.out.println("成功添加："+temp.getHall_id()+"号大厅"+temp.getShow_time()+"场次电影");
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delShow(){
        Scanner input=new Scanner(System.in);
        System.out.println("请输入电影id：");
        int movie_id=input.nextInt();
        System.out.println("请输入放映厅id：");
        int hall_id=input.nextInt();
        try {
            input.nextLine();
            System.out.println("请输入播放时间：");
            String show = input.nextLine();
            Date shows = format2.parse(show);
            Timestamp show_time = new Timestamp(shows.getTime());
            Show temp=new Show(hall_id,movie_id,show_time);
            String str="delete from Show where Hall_id=? and Movie_id=? and Show_time=?";
            Object[] param={temp.getHall_id(),temp.getMovie_id(),temp.getShow_time()};
            ChangeShowDaoimpl changeShowDaoimpl=new ChangeShowDaoimpl();
            int count=changeShowDaoimpl.updateShow(str,param);
            if(count>0){
                System.out.println("成功删除："+temp.getHall_id()+"号大厅"+temp.getShow_time()+"场次电影");
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void changeShow(){
        Scanner input=new Scanner(System.in);
        System.out.println("请输入原电影id：");
        int movie_id_old=input.nextInt();
        System.out.println("请输入原放映厅id：");
        int hall_id_old=input.nextInt();
        try {
            input.nextLine();
            System.out.println("请输入原播放时间：");
            String show = input.nextLine();
            Date shows = format2.parse(show);
            Timestamp show_time_old = new Timestamp(shows.getTime());
            Show temp=new Show(movie_id_old,hall_id_old,show_time_old);
            //TODO find；
            String [] param1={movie_id_old+"",hall_id_old+"",show_time_old+""};
            if(!find(param1)){
                System.out.println("没有该放映场次！");
                return ;
            }
            System.out.println("请输入现电影id：");
            int movie_id_new=input.nextInt();
            System.out.println("请输入现放映厅id：");
            int hall_id_new=input.nextInt();
            input.nextLine();
            System.out.println("请输入现播放时间：");
            show = input.nextLine();
            shows = format2.parse(show);
            Timestamp show_time_new = new Timestamp(shows.getTime());
            Show temp2=new Show(movie_id_new,hall_id_new,show_time_new);
            String str="update Show set Hall_id=?,Movie_id=?,Show_time=? where Hall_id=? and Movie_id=? and Show_time=?";
            Object[] param={temp2.getHall_id(),temp2.getMovie_id(),temp2.getShow_time(),temp.getHall_id(),temp.getMovie_id(),temp.getShow_time()};
            ChangeShowDaoimpl changeShowDaoimpl=new ChangeShowDaoimpl();
            int count=changeShowDaoimpl.updateShow(str,param);
            if(count>0){
                System.out.println("成功修改为："+temp2.getHall_id()+"号大厅"+temp2.getShow_time()+"场次电影");
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }

    }
    public boolean find(String[] param){
        String sql="select * from show where Hall_id=? and Movie_id=? and Show_time=?";
        ticketDao ticket=new ticketDaoimpl();
        List<Show> showList=ticket.getShow(sql,param);
        if(showList.size()>0)
            return true;
        return false;
    }
    //管理员查询
    public void ManageFind(){
        Scanner input = new Scanner(System.in);

        while(true){
            System.out.println("请输入查询类型 1.查询所有放映记录 2.查询所有电影 3.查询所有用户4.查询所有订票记录5.返回");
            //设计
            int i = input.nextInt();
            switch (i){
                case 1:
                    getArrange();
                    break;
                case 2:
                    printAllMovie();
                    break;
                case 3:
                    printAllUser();
                    break;
                case 4:
                    getRecord();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("请输入正确数字(1-5)");
            }
        }
    }
    //按照电影名查询放映记录
    //输入电影名
    //打印 序号 电影名 影厅号 时间 影厅类型 电影时长 电影类型 导演 票价


    public void printAllUser(){
        System.out.println("序号\t\t\t用户名\t\t\t\t\t密码\t\t\t\t\t\t手机号\t\t\t用户类型");
        UserDao userDao = new UserDaoimpl();
        List<Auident> auidentsList = userDao.getAllUser();
        for(int i=0;i<auidentsList.size();i++){
            Auident auident = new Auident();
            auident = auidentsList.get(i);
            System.out.format("%-8d\t%-20s\t%-20s\t%-15s\t%-10s\n",auident.getAud_id(),auident.getAud_name(),auident.getAid_password(),auident.getAud_tel(),auident.getAud_type());
        }

    }
}
