package Service.impl;

import DBopeartion.impl.HallDaoimpl;
import DBopeartion.impl.ticketDaoimpl;
import Service.ticket;
import entity.Arrange;
import entity.Hall;
import entity.Show;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Customservice extends service implements ticket {
    public Customservice(int Aud_id) {
        super(Aud_id);
    }

    @Override
    /**
     * 订票实现
     * */

    public void order(){
        List<String> movieList= getArrange();
        Scanner input=new Scanner(System.in);
        System.out.println("请输入电影名：");
        String movie_name=input.next();
        int i;
        for(i=0;i<movieList.size();i++){
            if(movieList.get(i).equals(movie_name))
                break;
        }
        if(i==movieList.size()){
            System.out.println("没有该电影");
            return;
        }
        ticketDaoimpl ticket=new ticketDaoimpl();
        String sql="select Show.Movie_id ,show.Show_time,show.Hall_id\n" +
                "from Show join Movie on Movie.Movie_id =Show.Movie_id\n" +
                "where Movie_name like ?";
        String[] param={"%"+movie_name+"%"};
        List<Show> showList=ticket.getShow(sql,param);
        System.out.println("序号：\t电影名：\t厅号：\t时间：");
        for(i=0;i<showList.size();i++){
            Show show=showList.get(i);
            System.out.println((i+1)+"\t"+movie_name+"\t"+show.getHall_id()+"号厅\t"+show.getShow_time());
        }
        int choice;
        while (true) {
            System.out.println("请选择场次（填序号）：(0返回)");
            choice = input.nextInt();
            if (choice == 0)
                return;
            else if(choice>showList.size()){
                System.out.println("请输入列表中的序号！");
            }
            else
                break;
        }
        System.out.println("您已选择：\n"+choice+"\t"+movie_name+"\t"+showList.get(choice-1).getHall_id()+"号厅\t"+showList.get(choice-1).getShow_time());
        System.out.println("请选择预订的电影票数");
        int num = input.nextInt();
        System.out.println("是否手动选座：（y/n）");
        char c = input.next().charAt(0);
        if (c == 'y' || c == 'Y') {
            while(num!=0) {
                selfChooseSeat(showList.get(choice - 1));
                num--;
            }
        } else {
            autoChooseSeat(showList.get(choice - 1),num);
        }
    }
    /**
     * 自动选座
     * */
    public boolean autoChooseSeat(Show show,int num){
        //得到当前影厅行数和列数
        String sql = "select * from MovieHall where Hall_id = ?";
        String[] param = {show.getHall_id()+""};
        HallDaoimpl hallDaoimpl=new HallDaoimpl();
        List<Hall> hallList=hallDaoimpl.getHall(sql,param);
        int row=hallList.get(0).getHall_rowsum();
        int line=hallList.get(0).getHall_linesum();
        //得到当前选票数据
        sql = "select * from arrange where arrange.hall_id = ? and arrange.arrange_time = ?";
        String[] param1 = {show.getHall_id()+"",show.getShow_time()+""};
        ticketDaoimpl ticket= new ticketDaoimpl();
        List<Arrange> arrangeList=ticket.search(sql,param1);
        char[][] chars = new char[line][row];
        for(int i=0;i<line;i++){
            for(int j=0;j<row;j++){
                chars[i][j]='_';
            }
        }
        for(int i=0;i<arrangeList.size();i++){
            Arrange arrange=arrangeList.get(i);
            chars[arrange.getLine()-1][arrange.getRow()-1]='*';
        }
        //生成选座数组
        int autoline=0;
        int autorow=0;
        //缩小选座区域
        int font,back,left,right;
        font = line/4;
        back = line*3/4;
        left = row/4;
        right = row*3/4;

        int tempi=font;
        int tempj=left-1;
        boolean flag=false;

        for(int i=font;i<=back;i++){
            for(int j=left;j<=right;j++){
                if(chars[i][j]=='*'&&flag==false){//____*<-____*____
                    if(j-left>=num){//前面够否？
                        autoline=i;
                        autorow = left;
                        break;
                    }
                    tempi = i;
                    tempj = j;
                    flag=true;
                } else if(chars[i][j]=='*'&&flag==true){//_____*____*<-____
                    if(j-tempj-1>=num){//中间够否？
                        autoline = tempi;
                        autorow = tempj+1;
                        break;
                    }else{
                        tempi = i;
                        tempj = j;
                    }
                }
            }
            if(right-tempj-1>=num){//后面够否？整行空
                autoline = tempi;
                autorow = tempj+1;
                break;
            }else{
                tempi=i+1;
                tempj=left-1;
                flag=false;
            }
        }
        //同时订多张连坐的实现
        int number=0;
        String[] param2 = {super.getAud_id() + "", show.getHall_id() + "", show.getMovie_id() + "", autoline + "", (autorow+number) + "", show.getShow_time() + ""};
        while(number!=num) {
            sql = "insert into arrange(Aud_id,Hall_id,Movie_id,line,row,Arrange_time) values(?,?,?,?,?,?)";
            param2[4] = (autorow+number) + "";
            int count = ticket.insertArrange(sql, param2);
            if (count > 0) {
                System.out.println("购票成功！在第"+autoline+"排第"+(autorow+number)+"号");

            }
            number++;
        }
        return true;
    }
    /**
     * 手动选座
     * */
    public boolean selfChooseSeat(Show show){
        //显示座位
        //从数据库中得到厅的行列数
        String sql = "select * from MovieHall where Hall_id = ?";
        String[] param = {show.getHall_id()+""};
        HallDaoimpl hallDaoimpl=new HallDaoimpl();
        List<Hall> hallList=hallDaoimpl.getHall(sql,param);
        int row=hallList.get(0).getHall_rowsum();
        int line=hallList.get(0).getHall_linesum();
        char[][] chars = new char[line][row];
        for(int i=0;i<line;i++){
            for(int j=0;j<row;j++){
                chars[i][j]='_';
            }
        }
        //用arrange表中的数据进行安排
        sql = "select * from arrange where arrange.hall_id = ? and arrange.arrange_time = ?";
        String[] param1 = {show.getHall_id()+"",show.getShow_time()+""};
        ticketDaoimpl ticket= new ticketDaoimpl();
        List<Arrange> arrangeList=ticket.search(sql,param1);
        for(int i=0;i<arrangeList.size();i++){
            Arrange arrange=arrangeList.get(i);
            chars[arrange.getLine()-1][arrange.getRow()-1]='*';
        }
        printSeat(chars,line,row);
        System.out.println("\t\t\t _可选，*不可选");
        //输入列数和行数
        System.out.println("请选择您的座位(排，列)");
        Scanner input = new Scanner(System.in);
        int i = input.nextInt();
        int j = input.nextInt();
        while(i>line||i<0||j>row||j<0){
            System.out.println("选座非法，请重新选座");
            i =input.nextInt();
            j =input.nextInt();
        }
        while (chars[i-1][j-1]=='*'){
            System.out.println("已经有人选了哦~，请重新选座");
            i =input.nextInt();
            j =input.nextInt();
        }
        sql="insert into arrange(Aud_id,Hall_id,Movie_id,line,row,Arrange_time) values(?,?,?,?,?,?)";
        String[] param2={super.getAud_id()+"",show.getHall_id()+"",show.getMovie_id()+"",i+"",j+"",show.getShow_time()+""};
        int count=ticket.insertArrange(sql,param2);
        if(count>0){
            System.out.println("购票成功！");
            return true;
        }
        return false;
    }
    /**
     * 打印座位
    * */
    public void printSeat(char[][] p,int line,int row){
        System.out.print(" \t\t|");
        for (int i=0;i<row;i++){
            if((i+1)/10==0)
                System.out.print(" "+(i+1)+"|");
            else
                System.out.print((i+1)+"|");
        }
        System.out.print("\n");
        for(int i=0;i<line;i++){
            System.out.print("第"+(i+1)+"排\t|");
            for(int j=0;j<row;j++){
                System.out.print(" "+p[i][j]+"|");
            }
            System.out.print("\n");
        }
    }
    @Override
    /**
     * 退票实现
     * */
    public void refund(){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入电影名：");
        String movie_name=input.nextLine();
        ticketDaoimpl ticket = new ticketDaoimpl();
        List<Arrange> arrangeList=search(movie_name);
        if(arrangeList==null){
            System.out.println("没有可退的票哦~");
            return;
        }
        System.out.println("序号：\t电影名：\t厅号：\t排：\t列: \t时间：");
        for(int i=0;i<arrangeList.size();i++){
            Arrange arrange=arrangeList.get(i);
            System.out.println((i+1)+"\t"+movie_name+"\t"+arrange.getHall_id()+"号厅\t第"+arrange.getLine()+"排\t第"+arrange.getRow()+"列\t"+arrange.getArrange_time());
        }
        System.out.println("请输入退票序号");
        int c = input.nextInt();
        Arrange delarrange=arrangeList.get(c-1);
        String sql = "delete from arrange where movie_id= ? and hall_id=? and Aud_id=? and Line = ? and Row = ?";
        String[] param2 = {delarrange.getMovie_id()+"",delarrange.getHall_id()+"",super.getAud_id()+"",delarrange.getLine()+"",delarrange.getRow()+""};

        if(ticket.delArrange(sql,param2)>0)
            System.out.println("退票成功是否继续退票(y/n)?");
        else{
            System.out.println("退票失败");
            return;
        }
        char ct = '0';
        ct = input.next().charAt(0);
        while (true) {
            if (ct == 'y'||ct == 'Y') {
                System.out.println("请输入退票序号");
                c = input.nextInt();
                delarrange=arrangeList.get(c-1);
                param2[3] = delarrange.getLine()+"";
                param2[4] = delarrange.getRow()+"";
                if(ticket.delArrange(sql,param2)>0){
                    System.out.println("退票成功是否继续退票(y/n)?");
                    ct = input.next().charAt(0);
                }
                else{
                    System.out.println("退票失败");
                    return;
                }
            } else {
                break;
            }
        }

    }
    @Override
    /**
     *改票实现
     *  */

    public void change(){
        ticketDaoimpl ticket = new ticketDaoimpl();
        Scanner input = new Scanner(System.in);
        System.out.println("请输入电影名：");
        String movie_name =input.nextLine();
        List<Arrange> arrangeList = search(movie_name);
        if(arrangeList==null){
            System.out.println("没有可改的票哦~");
            return;
        }
        System.out.println("序号：\t电影名：\t厅号：\t排：\t列: \t时间：");
        for(int i=0;i<arrangeList.size();i++){
            Arrange arrange=arrangeList.get(i);
            System.out.println((i+1)+"\t"+movie_name+"\t"+arrange.getHall_id()+"号厅\t第"+arrange.getLine()+"排\t第"+arrange.getRow()+"列\t"+arrange.getArrange_time());
        }
        System.out.println("选择要改签的票序号");
        int c = input.nextInt();
        Arrange changearrange=arrangeList.get(c-1);
        String sql = "delete from arrange where movie_id= ? and hall_id=? and Aud_id=? and Line = ? and Row = ?";
        String[] param2 = {changearrange.getMovie_id()+"",changearrange.getHall_id()+"",super.getAud_id()+"",changearrange.getLine()+"",changearrange.getRow()+""};
        if(ticket.delArrange(sql,param2)>0) {
            //获得当前电影的其他场次
            sql="select Movie.Movie_id ,Show_time,Hall_id\n"+
                    "from Show join Movie on Movie.Movie_id =Show.Movie_id\n"+
                    "where Movie_name=?";
            String[] param={movie_name};
            List<Show> showList=ticket.getShow(sql,param);
            //预定电影票走一波
            System.out.println("序号：\t电影名：\t厅号：\t时间：");
            for(int i=0;i<showList.size();i++){
                Show show=showList.get(i);
                System.out.println((i+1)+"\t"+movie_name+"\t"+show.getHall_id()+"号厅\t"+show.getShow_time());
            }
            System.out.println("请选择场次（填序号）：");
            int choice=input.nextInt();
            System.out.println("您已选择：\n"+choice+"\t"+movie_name+"\t"+showList.get(choice-1).getHall_id()+"号厅\t"+showList.get(choice-1).getShow_time());
            selfChooseSeat(showList.get(choice - 1));
        } else{
            System.out.println("改票失败失败");
            return;
        }

    }
    @Override
    /**
     * 根据电影名进行查询
     * 返回订票记录
     * */
    public List<Arrange> search(String movie_name){
        //按电影名查找
        String sql="select Arrange.Aud_id,Arrange.Hall_id,Arrange.Movie_id,Line,Row,Arrange_time\n"
                +"from Arrange join Movie on Arrange.Movie_id=Movie.Movie_id\n" +
                "where Arrange_time>? and movie_name=? and Aud_id=?";
        Date date=new Date();
        Timestamp timestamp=new Timestamp(date.getTime());
        String[] param={timestamp.toString(),movie_name,super.getAud_id()+""};
        ticketDaoimpl ticket=new ticketDaoimpl();
        List<Arrange> arrangeList=ticket.search(sql,param);
        return arrangeList;
    }
    @Override
    /**
     * 1.查询当前电影
     * 2.查询订票记录
     * */
    public void find() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入查询类型 1.查询当前热映电影 2.查询订票记录");
        int c = input.nextInt();
        while(true){
            if(c==1){
                getArrange();
                break;
            }else if(c==2){
                getRecord();
                break;
            }else{
                System.out.println("请输入正确的数字(1-2)");
                c=input.nextInt();
            }
        }
        
    }


}
