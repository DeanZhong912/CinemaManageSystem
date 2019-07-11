package entity;

import java.sql.Timestamp;
public class Movie {
    private int Movie_id;
    private String Movie_name;
    private double Movie_baseprice;
    private String Movie_director;
    private String Movie_type;
    private Timestamp Movie_starttime;
    private Timestamp Movie_endtime;
    private String Movie_loc;
    private int Last_time;

    public Movie(){}

    public Movie(int movie_id, String movie_name, double movie_baseprice, String movie_director, String movie_type, Timestamp movie_starttime, Timestamp movie_endtime, String movie_loc, int last_time) {
        Movie_id = movie_id;
        Movie_name = movie_name;
        Movie_baseprice = movie_baseprice;
        Movie_director = movie_director;
        Movie_type = movie_type;
        Movie_starttime = movie_starttime;
        Movie_endtime = movie_endtime;
        Movie_loc = movie_loc;
        Last_time = last_time;
    }


    public int getMovie_id() {
        return Movie_id;
    }

    public void setMovie_id(int movie_id) {
        Movie_id = movie_id;
    }

    public String getMovie_name() {
        return Movie_name;
    }

    public void setMovie_name(String movie_name) {
        Movie_name = movie_name;
    }

    public double getMovie_baseprice() {
        return Movie_baseprice;
    }

    public void setMovie_baseprice(double movie_baseprice) {
        Movie_baseprice = movie_baseprice;
    }

    public String getMovie_director() {
        return Movie_director;
    }

    public void setMovie_director(String movie_director) {
        Movie_director = movie_director;
    }

    public String getMovie_type() {
        return Movie_type;
    }

    public void setMovie_type(String movie_type) {
        Movie_type = movie_type;
    }

    public String getMovie_loc() {
        return Movie_loc;
    }

    public void setMovie_loc(String movie_loc) {
        Movie_loc = movie_loc;
    }

    public Timestamp getMovie_starttime() {
        return Movie_starttime;
    }

    public void setMovie_starttime(Timestamp movie_starttime) {
        Movie_starttime = movie_starttime;
    }

    public Timestamp getMovie_endtime() {
        return Movie_endtime;
    }

    public void setMovie_endtime(Timestamp movie_endtime) {
        Movie_endtime = movie_endtime;
    }

    public int getLast_time() {
        return Last_time;
    }

    public void setLast_time(int last_time) {
        Last_time = last_time;
    }
}
