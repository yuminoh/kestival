package dev.mvc.member;

public interface MemberProcInter {
    public int insert(MemberVO memberVO);

    public int delete(int memberno);
    
    public int update(MemberVO membervo);
    
    public MemberVO read(int memberno);
    
    public MemberVO checkUser(MemberVO memberVO);
}
