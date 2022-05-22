package dev.mvc.admin;

public class AdminVO {
    private int adminno;
    
    private String id;
    
    private String pw;
    
    private String mname;
    
    private String email;
    
    private String phone;
    
    private int zipcode;
    
    private String address1;
    
    private String address2;
    
    private String adate;

    public int getAdminno() {
        return adminno;
    }

    public AdminVO setAdminno(int adminno) {
        this.adminno = adminno;
        return this;
    }

    public String getId() {
        return id;
    }

    public AdminVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getPw() {
        return pw;
    }

    public AdminVO setPw(String pw) {
        this.pw = pw;
        return this;
    }

    public String getMname() {
        return mname;
    }

    public AdminVO setMname(String mname) {
        this.mname = mname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AdminVO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public AdminVO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public int getZipcode() {
        return zipcode;
    }

    public AdminVO setZipcode(int zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public String getAddress1() {
        return address1;
    }

    public AdminVO setAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public String getAddress2() {
        return address2;
    }

    public AdminVO setAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public String getAdate() {
        return adate;
    }

    public AdminVO setAdate(String adate) {
        this.adate = adate;
        return this;
    }

}
