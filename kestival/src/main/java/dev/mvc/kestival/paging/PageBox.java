package dev.mvc.kestival.paging;

public class PageBox extends Criteria{
    private Criteria criteria;
    private int totalCount;
    private int start;
    private int end;
    private boolean prev;
    private boolean next;
    private int displayPageNum = 5;
    
    public Criteria getCriteria() {
        return criteria;
    }
    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }
    
    private void calcData() {
        
        end = (int) (Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum);
 
        start = (end - displayPageNum) + 1;
        if(start <= 0) start = 1;
        
        int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPernum()));
        if (end > tempEndPage) {
            end = tempEndPage;
        }
 
        prev = start == 1 ? false : true;
        next = end * criteria.getPernum() < totalCount ? true : false;
        
    }
    
    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getEnd() {
        return end;
    }
    public void setEnd(int end) {
        this.end = end;
    }
    public boolean isPrev() {
        return prev;
    }
    public void setPrev(boolean prev) {
        this.prev = prev;
    }
    public boolean isNext() {
        return next;
    }
    public void setNext(boolean next) {
        this.next = next;
    }
    public int getDisplayPageNum() {
        return displayPageNum;
    }
    public void setDisplayPageNum(int displayPageNum) {
        this.displayPageNum = displayPageNum;
    }
    
   
}
