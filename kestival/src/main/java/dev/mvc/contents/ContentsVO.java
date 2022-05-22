package dev.mvc.contents;

import org.springframework.web.multipart.MultipartFile;

public class ContentsVO {
    private int contentsno;
    
    private int cateno;
    
    private String title;
    
    private String context;
    
    private int recom;
    
    private String rdate;
    
    private String file1;
    
    private String file1saved;
    
    private String thumb1;
    
    private int size1;
    
    private String size1_label;
    
    private MultipartFile file1MF;
    
    private String perDate;
    
    private String location;
    
    public int getContentsno() {
        return contentsno;
    }

    public ContentsVO setContentsno(int contentsno) {
        this.contentsno = contentsno;
        return this;
    }

    public int getCateno() {
        return cateno;
    }

    public ContentsVO setCateno(int cateno) {
        this.cateno = cateno;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ContentsVO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContext() {
        return context;
    }

    public ContentsVO setContext(String context) {
        this.context = context;
        return this;
    }

    public int getRecom() {
        return recom;
    }

    public ContentsVO setRecom(int recom) {
        this.recom = recom;
        return this;
    }

    public String getRdate() {
        return rdate;
    }

    public ContentsVO setRdate(String rdate) {
        this.rdate = rdate;
        return this;
    }

    public String getFile1() {
        return file1;
    }

    public ContentsVO setFile1(String file1) {
        this.file1 = file1;
        return this;
    }

    public String getFile1saved() {
        return file1saved;
    }

    public ContentsVO setFile1saved(String file1saved) {
        this.file1saved = file1saved;
        return this;
    }

    public String getThumb1() {
        return thumb1;
    }

    public ContentsVO setThumb1(String thumb1) {
        this.thumb1 = thumb1;
        return this;
    }

    public int getSize1() {
        return size1;
    }

    public ContentsVO setSize1(int size1) {
        this.size1 = size1;
        return this;
    }
    
    public String getSize1_label() {
        return size1_label;
    }
    
    public ContentsVO setSize1_label(String size1_label) {
        this.size1_label = size1_label;
        return this;
    }
    
    public MultipartFile getFile1MF() {
        return file1MF;
    }
    
    public ContentsVO setFile1MF(MultipartFile file1MF) {
        this.file1MF = file1MF;
        return this;
    }
    
    public String getPerDate() {
        return perDate;
    }
    
    public ContentsVO setPerDate(String perDate) {
        this.perDate = perDate;
        return this;
    }
    
    public String getLocation() {
        return location;
    }
    
    public ContentsVO setLocation(String location) {
        this.location= location;
        return this;
    }
}
