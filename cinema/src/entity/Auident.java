package entity;

public class Auident {
    private int Aud_id;
    private String Aud_name;
    private String Aud_tel;
    private String Aud_type;
    private String Aid_password;

    public Auident(){}
    public Auident(int id,String name,String tel,String type,String password){
        this.Aud_id=id;
        this.Aud_name=name;
        this.Aud_tel=tel;
        this.Aud_type=type;
        this.Aid_password=password;
    }

    public String getAid_password() {
        return Aid_password;
    }

    public void setAid_password(String aid_password) {
        Aid_password = aid_password;
    }

    public int getAud_id() {
        return Aud_id;
    }

    public void setAud_id(int aud_id) {
        Aud_id = aud_id;
    }

    public String getAud_name() {
        return Aud_name;
    }

    public void setAud_name(String aud_name) {
        Aud_name = aud_name;
    }

    public String getAud_tel() {
        return Aud_tel;
    }

    public void setAud_tel(String aud_tel) {
        Aud_tel = aud_tel;
    }

    public String getAud_type() {
        return Aud_type;
    }

    public void setAud_type(String aud_type) {
        Aud_type = aud_type;
    }
}
