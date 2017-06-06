package vnits.vn.quanlysinhvien.model;

/**
 * Created by phong on 5/9/2017.
 */

public class Score {
    private String monhoc,dtb,trangthai;

    public Score(){

    }

    public Score(String monhoc, String dtb, String trangthai) {
        this.monhoc = monhoc;
        this.dtb = dtb;
        this.trangthai = trangthai;
    }

    public String getMonhoc() {
        return monhoc;
    }

    public void setMonhoc(String monhoc) {
        this.monhoc = monhoc;
    }

    public String getDtb() {
        return dtb;
    }

    public void setDtb(String dtb) {
        this.dtb = dtb;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    @Override
    public String toString() {
        return super.toString();

    }
}
