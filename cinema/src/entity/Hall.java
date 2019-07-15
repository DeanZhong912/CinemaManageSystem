package entity;

public class Hall {
    private int Hall_id;
    private String Hall_type;
    private int Hall_linesum;
    private int Hall_rowsum;
    private double Hall_addprice;

    public Hall(){}
    public Hall(int id,String type,int linesum,int rowsum,double addprice){
        this.Hall_id=id;
        this.Hall_linesum=linesum;
        this.Hall_rowsum=rowsum;
        this.Hall_addprice=addprice;
    }
    public int getHall_id() {
        return Hall_id;
    }

    public void setHall_id(int hall_id) {
        Hall_id = hall_id;
    }

    public String getHall_type() {
        return Hall_type;
    }

    public void setHall_type(String hall_type) {
        Hall_type = hall_type;
    }

    public int getHall_linesum() {
        return Hall_linesum;
    }

    public void setHall_linesum(int hall_linesum) {
        Hall_linesum = hall_linesum;
    }

    public int getHall_rowsum() {
        return Hall_rowsum;
    }

    public void setHall_rowsum(int hall_rowsum) {
        Hall_rowsum = hall_rowsum;
    }

    public double getHall_addprice() {
        return Hall_addprice;
    }

    public void setHall_addprice(double hall_addprice) {
        Hall_addprice = hall_addprice;
    }
}
