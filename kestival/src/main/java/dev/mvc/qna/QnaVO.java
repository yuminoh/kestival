package dev.mvc.qna;

public class QnaVO {
    private int qnano;
    
    private int memberno;
    
    private String qtitle;
    
    private String qwriter;
    
    private String qcontext;
    
    private String qdate;

    public int getQnano() {
        return qnano;
    }

    public QnaVO setQnano(int qnano) {
        this.qnano = qnano;
        return this;
    }

    public int getMemberno() {
        return memberno;
    }

    public QnaVO setMemberno(int memberno) {
        this.memberno = memberno;
        return this;
    }
    
    public String getQtitle() {
        return qtitle;
    }
    
    public QnaVO setQtitle(String qtitle) {
        this.qtitle = qtitle;
        return this;
    }

    public String getQwriter() {
        return qwriter;
    }

    public QnaVO setQwriter(String qwriter) {
        this.qwriter = qwriter;
        return this;
    }

    public String getQcontext() {
        return qcontext;
    }

    public QnaVO setQcontext(String qcontext) {
        this.qcontext = qcontext;
        return this;
    }

    public String getQdate() {
        return qdate;
    }

    public QnaVO setQdate(String qdate) {
        this.qdate = qdate;
        return this;
    }
}
