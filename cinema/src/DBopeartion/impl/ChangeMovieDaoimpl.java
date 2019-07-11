package DBopeartion.impl;

import DBopeartion.BaseDao;
import DBopeartion.ChangeMovieDao;
import entity.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ChangeMovieDaoimpl extends BaseDao implements ChangeMovieDao {
    private Connection conn = null; // 保存数据库连接

    private PreparedStatement pstmt = null; // 用于执行SQL语句

    private ResultSet rs = null; // 用户保存查询到的结果集

    @Override
    public int updateMovie(String sql, Object[] param) {
        int count=super.executeSQL(sql,param);
        return count;
    }

    @Override
    public List<Movie> getAllMovie() {
        List<Movie> movieList = new ArrayList<Movie>();

        String sql = "select * from movie";

        try{
            conn=getConn();
            pstmt=conn.prepareStatement(sql);

            rs=pstmt.executeQuery();
            while(rs.next()){
                Movie movie=new Movie();
                movie.setMovie_id(rs.getInt(1));
                movie.setMovie_name(rs.getString(2));
                movie.setMovie_baseprice(rs.getFloat(3));
                movie.setMovie_type(rs.getString(4));
                movie.setMovie_loc(rs.getString(5));
                movie.setMovie_director(rs.getString(6));
                movie.setMovie_starttime(rs.getTimestamp(7));
                movie.setMovie_endtime(rs.getTimestamp(8));
                movie.setLast_time(rs.getInt(9));
                movieList.add(movie);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            super.closeAll(conn,pstmt,rs);
        }
        return movieList;
    }
}
