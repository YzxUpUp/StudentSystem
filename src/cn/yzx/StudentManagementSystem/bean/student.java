package cn.yzx.StudentManagementSystem.bean;

public class student {
    private int sno;
    private String sname;
    private String ssex;
    private int sage;
    private String saddress;
    private String sqq;
    private String semail;

    @Override
    public String toString() {
        return "student{" +
                "sno=" + sno +
                ", sname='" + sname + '\'' +
                ", ssex='" + ssex + '\'' +
                ", sage=" + sage +
                ", saddress='" + saddress + '\'' +
                ", sqq='" + sqq + '\'' +
                ", semail='" + semail + '\'' +
                '}';
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public int getSage() {
        return sage;
    }

    public void setSage(int sage) {
        this.sage = sage;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public String getSqq() {
        return sqq;
    }

    public void setSqq(String sqq) {
        this.sqq = sqq;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }
}
