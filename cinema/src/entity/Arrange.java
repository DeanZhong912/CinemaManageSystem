package entity;

import java.sql.Timestamp;

public class Arrange {
    private int Aud_id;
    private int Hall_id;
    private int Movie_id;
    private int Line;
    private int Row;
    private Timestamp Arrange_time;

    public Arrange() {
    }

    public Arrange(int aud_id, int hall_id, int movie_id, int line, int row, Timestamp arrange_time) {
        Aud_id = aud_id;
        Hall_id = hall_id;
        Movie_id = movie_id;
        Line = line;
        Row = row;
        Arrange_time = arrange_time;
    }

    public int getAud_id() {
        return Aud_id;
    }

    public void setAud_id(int aud_id) {
        Aud_id = aud_id;
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

    public int getLine() {
        return Line;
    }

    public void setLine(int line) {
        Line = line;
    }

    public int getRow() {
        return Row;
    }

    public void setRow(int row) {
        Row = row;
    }

    public Timestamp getArrange_time() {
        return Arrange_time;
    }

    public void setArrange_time(Timestamp arrange_time) {
        Arrange_time = arrange_time;
    }
}
