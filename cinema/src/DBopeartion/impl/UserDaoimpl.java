package DBopeartion.impl;

import DBopeartion.UserDao;
import DBopeartion.BaseDao;
import entity.Auident;
import entity.Hall;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by diandian on 2019/7/11.
 */
public class UserDaoimpl extends BaseDao implements UserDao  {
    private Connection conn = null; // 保存数据库连接

    private PreparedStatement pstmt = null; // 用于执行SQL语句

    private ResultSet rs = null; // 用户保存查询到的结果集

    @Override
    public List<Auident> getAllUser() {
        List<Auident> auidentsList=new ArrayList<Auident>();
        String sql = "select * from Audience";
        try{
            conn=getConn();
            pstmt=conn.prepareStatement(sql);

            rs=pstmt.executeQuery();
            while(rs.next()){
                Auident auident=new Auident();
                auident.setAud_id(rs.getInt(1));
                auident.setAud_name(rs.getString(2));
                auident.setAid_password(rs.getString(3));
                auident.setAud_tel(rs.getString(4));
                auident.setAud_type(rs.getString(5));
                auidentsList.add(auident);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            super.closeAll(conn,pstmt,rs);
        }


        return auidentsList;
    }

    @Override
    public int AddUser(Auident user) {
        String sql = "insert into audience values (?,?,?,?,?)";
        Object[] param={user.getAud_id(), user.getAud_name(),user.getAid_password(),user.getAud_tel(),user.getAud_type()};
        int count=executeSQL(sql,param);
        return count;

    }
}
