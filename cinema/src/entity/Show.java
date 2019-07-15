package entity;

import java.sql.Timestamp;
public class Show {
    private int Hall_id;
    private int Movie_id;
    private Timestamp Show_time;

    public Show() {
    }

    public Show(int hall_id, int movie_id, Timestamp show_time) {
        Hall_id = hall_id;
        Movie_id = movie_id;
        Show_time = show_time;
    }

    public int getHall_id() {
        return Hall_id;
    }

    public void setHall_id(int hall_id) {
        Hall_id = hall_id;
    }

    public int getMovie_id() {
        return Movie_id;
    }

    public void setMovie_id(int movie_id) {
        Movie_id = movie_id;
    }

    public Timestamp getShow_time() {
        return Show_time;
    }

    public void setShow_time(Timestamp show_time) {
        Show_time = show_time;
    }
}
