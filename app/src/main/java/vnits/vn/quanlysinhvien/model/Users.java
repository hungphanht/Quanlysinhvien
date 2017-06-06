package vnits.vn.quanlysinhvien.model;

/**
 * Created by Deleting on 5/31/2017.
 */

public class Users {
    private String Mssv;
    private String Password;
    private String Tokens;
    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    private String trangthai;

    public String getMssv() {
        return Mssv;
    }

    public void setMssv(String mssv) {
        Mssv = mssv;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getTokens() {
        return Tokens;
    }

    public void setTokens(String tokens) {
        Tokens = tokens;
    }


}
