package DBopeartion.impl;

import DBopeartion.BaseDao;
import DBopeartion.HallDao;
import entity.Hall;
import entity.Show;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HallDaoimpl extends BaseDao implements HallDao{
    private Connection conn = null; // 保存数据库连接

    private PreparedStatement pstmt = null; // 用于执行SQL语句

    private ResultSet rs = null; // 用户保存查询到的结果集
    @Override
    public List<Hall> findHall() {

        return null;
    }

    @Override
    public List<Hall> getHall(String sql,String[] param) {
        List<Hall> hallList=new ArrayList<Hall>();
        try{
            conn=getConn();
            pstmt=conn.prepareStatement(sql);
            if(param!=null){
                for(int i=0;i<param.length;i++) {
                    pstmt.setString(i+1,param[i]);
                }
            }
            rs=pstmt.executeQuery();
            while(rs.next()){
                Hall hall=new Hall();
                hall.setHall_id(rs.getInt(1));
                hall.setHall_type(rs.getString(2));
                hall.setHall_rowsum(rs.getInt(3));
                hall.setHall_linesum(rs.getInt(4));
                hall.setHall_addprice(rs.getInt(5));
                hallList.add(hall);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            super.closeAll(conn,pstmt,rs);
        }
        return hallList;
    }
}
