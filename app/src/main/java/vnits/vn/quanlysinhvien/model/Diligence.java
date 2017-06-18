package vnits.vn.quanlysinhvien.model;

/**
 * Created by phong on 5/9/2017.
 */

public class Diligence {
    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getSang() {
        return sang;
    }

    public void setSang(String sang) {
        this.sang = sang;
    }

    public String getChieu() {
        return chieu;
    }

    public void setChieu(String chieu) {
        this.chieu = chieu;
    }

    public Diligence(String ngay, String sang, String chieu) {
        this.ngay = ngay;
        this.sang = sang;
        this.chieu = chieu;
    }

    @Override
    public String toString() {
        return super.toString();

    }

    public Diligence(){

    }
    String ngay,sang,chieu;
}
