package dev.mvc.cate;

public class CateVO {
    private int cateno;
    
    private int adminno;
    
    private String name;
    
    private String cdate;

    public int getCateno() {
        return cateno;
    }

    public CateVO setCateno(int cateno) {
        this.cateno = cateno;
        return this;
    }
    
    public int getAdminno() {
        return adminno;
    }
    
    public CateVO setAdminno(int adminno) {
        this.adminno = adminno;
        return this;
    }

    public String getName() {
        return name;
    }

    public CateVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getCdate() {
        return cdate;
    }

    public CateVO setCdate(String cdate) {
        this.cdate = cdate;
        return this;
    }
    
    
}
