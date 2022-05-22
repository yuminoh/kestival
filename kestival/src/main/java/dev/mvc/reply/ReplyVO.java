package dev.mvc.reply;

public class ReplyVO {
    private int replyno;
    private int adminno;
    private int qnano;
    private String rwiter;
    private String rcontext;
    private String rdate;
    
    public int getReplyno() {
        return replyno;
    }
    public ReplyVO setReplyno(int replyno) {
        this.replyno = replyno;
        return this;
    }
    
    public int getAdminno() {
        return adminno;
    }
    public ReplyVO setAdminno(int adminno) {
        this.adminno = adminno;
        return this;
    }
    
    public int getQnano() {
        return qnano;
    }
    @Override
    public String toString() {
        return "ReplyVO [replyno=" + replyno + ", adminno=" + adminno + ", qnano=" + qnano + ", rwiter=" + rwiter
                + ", rcontext=" + rcontext + ", rdate=" + rdate + "]";
    }
    public ReplyVO setQnano(int qnano) {
        this.qnano = qnano;
        return this;
    }
    
    public String getRwiter() {
        return rwiter;
    }
    public ReplyVO setRwiter(String rwiter) {
        this.rwiter = rwiter;
        return this;
    }
    
    public String getRcontext() {
        return rcontext;
    }
    public ReplyVO setRcontxt(String rcontext) {
        this.rcontext = rcontext;
        return this;
    }
    
    public String getRdate() {
        return rdate;
    }
    public ReplyVO setRdate(String rdate) {
        this.rdate = rdate;
        return this;
    }
}