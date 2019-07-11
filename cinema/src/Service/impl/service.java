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
        System.out.println("序号 \t电影名\t 影厅号\t 时间\t 影厅类型\t 电影时长\t 电影类型\t 导演\t 票价");
        for (int i = 0; i < showList.size(); i++) {
            List<String> show = showList.get(i);
            System.out.println((i + 1) + "\t" + show.get(0) + "\t" + show.get(1) + "号厅\t" + show.get(2) + "\t" + show.get(3) + "\t" + show.get(4) + "\t" + show.get(5) + "\t" + show.get(6) + "\t" + show.get(7));
            movieList.add(show.get(0));
        }
        return movieList;
    }
    public void getRecord(){
        //订票记录
        ticketDaoimpl ticket = new ticketDaoimpl();
        List<List<String>> showList=ticket.findticket(Aud_id);
                /* 电影名 厅号 厅类型 时间 用户名 座位 票价*/
        System.out.println("序号 \t电影名\t 影厅号\t 影厅类型\t 时间\t 用户名\t 排\t 列\t 票价");
        for(int i=0;i<showList.size();i++){
            List<String> show=showList.get(i);
            System.out.println((i+1)+"\t"+show.get(0)+"\t"+show.get(1)+"号厅\t"+show.get(2)+"\t"+show.get(3)+"\t"+show.get(4)+"\t"+show.get(5)+"\t"+show.get(6)+"\t"+show.get(7));
        }
    }
    public void printAllMovie(){
        System.out.println("序号\t电影名\t电影基本价\t分类\t地区\t导演\t上架时间\t下映时间\t电影时长");
        ChangeMovieDao MovieDao = new ChangeMovieDaoimpl();
        List<Movie> movieList= MovieDao.getAllMovie();
        for(int i=0;i<movieList.size();i++){
            Movie movie = new Movie();
            movie = movieList.get(i);
            System.out.println(movie.getMovie_id()+"\t"+movie.getMovie_name()+"\t"+movie.getMovie_baseprice()+"\t"+movie.getMovie_type()+"\t"+movie.getMovie_loc()+"\t"+movie.getMovie_director()+"\t"+movie.getMovie_starttime()+"\t"+movie.getMovie_endtime()+"\t"+movie.getLast_time()+"\t");
        }
    }
}
