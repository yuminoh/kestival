package dev.mvc.kestival.paging;

public class Criteria {
    private int page;
    private int pernum;
    private String word;
    
    public Criteria() {
        this.page = 1;
        this.pernum = 10;
    }
    
    public void setPage(int page) {
        if(page <= 0) {
            this.page = 1;
            return;
        }
        
        this.page = page;
    }
    
    public int getPage() {
        return page;
    }
    
    public void setPernum(int pernum) {
        if(pernum <= 0 || pernum >100) {
            this.pernum = 10;
            return;
        }
        
        this.pernum = pernum;
    }
    
    public int getPernum() {
        return this.pernum;
    }
    
    public int getStart() {
        return (this.page - 1) * pernum;
    }
    
    public String getWord() {
        return word;
    }
    
    public void setWord(String word) {
        this.word = word;
    }
}