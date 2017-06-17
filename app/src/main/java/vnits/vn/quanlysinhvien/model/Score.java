package vnits.vn.quanlysinhvien.model;

/**
 * Created by phong on 5/9/2017.
 */

public class Score {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String monhoc,trangthai;
    private Double dtb;

    public Double getDtb() {
        return dtb;
    }

    public void setDtb(Double dtb) {
        this.dtb = dtb;
    }

    public String getMonhoc() {
        return monhoc;
    }

    public void setMonhoc(String monhoc) {
        this.monhoc = monhoc;
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
