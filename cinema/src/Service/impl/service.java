package Service.impl;


import DBopeartion.ChangeMovieDao;
import DBopeartion.impl.ChangeMovieDaoimpl;
import DBopeartion.impl.ticketDaoimpl;
import entity.Movie;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Bear 影院服务
 * */
public abstract class service {
    java.text.SimpleDateFormat format =
            new SimpleDateFormat("yyyy-MM-dd");
    java.text.SimpleDateFormat format2 =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private int Aud_id ;

    public service() {
    }

    public service(int Aud_id) {
        this.Aud_id = Aud_id;
    }

    public int getAud_id() {
        return Aud_id;
    }

    public void setAud_id(int aud_id) {
        Aud_id = aud_id;
    }

    public List<String> getArrange() {
        ticketDaoimpl ticket = new ticketDaoimpl();
        List<List<String>> showList = ticket.findShow(Aud_id);
        List<String> movieList =new ArrayList<String>();
                /*
                * 序号 电影名 影厅号 时间 影厅类型 电影时长 电影类型 导演 票价(base price+ add price)*/
        System.out.println("序号\t\t\t电影名\t\t\t\t影厅号\t\t时间\t\t\t\t\t\t\t\t影厅类型\t\t\t\t电影时长\t\t电影类型\t\t\t导演\t\t\t\t\t票价");
        for (int i = 0; i < showList.size(); i++) {
            List<String> show = showList.get(i);
            String str=String.format("%-8d\t%-14s\t%-8s\t%-30s\t%-16s\t%-10s\t%-10s\t%-15s\t%-10s\n",i+1,show.get(0),show.get(1) + "号厅",show.get(2),show.get(3),show.get(4)+"分钟",show.get(5),show.get(6),show.get(7)+"元");
            System.out.print(str);
            //System.out.println((i + 1) + "\t" + show.get(0) + "\t" + show.get(1) + "号厅\t" + show.get(2) + "\t" + show.get(3) + "\t" + show.get(4) + "\t" + show.get(5) + "\t" + show.get(6) + "\t" + show.get(7));
            movieList.add(show.get(0));
        }
        return movieList;
    }
    public void getRecord(){
        //订票记录
        ticketDaoimpl ticket = new ticketDaoimpl();
        List<List<String>> showList=ticket.findticket(Aud_id);
                /* 电影名 厅号 厅类型 时间 用户名 座位 票价*/
        System.out.println("序号 \t\t电影名\t\t\t\t影厅号\t影厅类型\t\t\t时间\t\t\t\t\t\t\t用户名\t\t\t\t\t排\t\t列\t\t票价");
        for(int i=0;i<showList.size();i++){
            List<String> show=showList.get(i);
            System.out.format("%-8d\t%-14s\t%-3s\t%-10s\t%-26s\t%-20s\t%-5s\t%-5s\t%-8s\n",(i+1),show.get(0),show.get(1)+"号厅",show.get(2),show.get(3),show.get(4),show.get(5),show.get(6),show.get(7)+"元");
        }
    }
    public void printAllMovie(){
        System.out.println("序号\t\t\t电影名\t\t\t\t电影基本价\t分类\t\t\t\t地区\t\t导演\t\t\t\t上架时间\t\t\t\t\t\t下映时间\t\t\t\t\t\t电影时长");
        ChangeMovieDao MovieDao = new ChangeMovieDaoimpl();
        List<Movie> movieList= MovieDao.getAllMovie();
        for(int i=0;i<movieList.size();i++){
            Movie movie = new Movie();
            movie = movieList.get(i);
            System.out.format("%-8d\t%-14s\t%-8s\t%-10s\t%-5s\t%-12s\t%-26s\t%-26s\t%-8s\n",movie.getMovie_id(),movie.getMovie_name(),movie.getMovie_baseprice()+"元",movie.getMovie_type(),movie.getMovie_loc(),movie.getMovie_director(),movie.getMovie_starttime(),movie.getMovie_endtime(),movie.getLast_time()+"分钟");
        }
    }
}
