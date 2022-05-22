package dev.mvc.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberVO {
    private int memberno;
    
    private String id;
    
    private String pw;
    
    private String mname;
    
    private String email;
    
    private String phone;
    
    private int zipcode;
    
    private String address1;
    
    private String address2;
    
    private String mdate;

    public int getMemberno() {
        return memberno;
    }

    public MemberVO setMemberno(int memberno) {
        this.memberno = memberno;
        return this;
    }

    public String getId() {
        return id;
    }

    public MemberVO setId(String id) {
        this.id = id;
        return this;
    }

    public String getPw() {
        return pw;
    }

    public MemberVO setPw(String pw) {
        this.pw = pw;
        return this;
    }

    public String getMname() {
        return mname;
    }

    public MemberVO setMname(String mname) {
        this.mname = mname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public MemberVO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public MemberVO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public int getZipcode() {
        return zipcode;
    }

    public MemberVO setZipcode(int zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public String getAddress1() {
        return address1;
    }

    public MemberVO setAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public String getAddress2() {
        return address2;
    }

    public MemberVO setAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public String getMdate() {
        return mdate;
    }

    public MemberVO setMdate(String mdate) {
        this.mdate = mdate;
        return this;
    }
}
