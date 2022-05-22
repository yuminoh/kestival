package dev.mvc.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.member.MemberProc")
public class MemberProc implements MemberProcInter{
    @Autowired
    private MemberDAOInter memberDAO;
    
    public MemberProc(){
      System.out.println("-> MemberProc created.");
    }
    
    @Override
    public int insert(MemberVO memberVO) {
        int cnt = this.memberDAO.insert(memberVO);
        return cnt;
    }
    
    @Override
    public int delete(int memberno) {
        return memberDAO.delete(memberno);
    }
    
    @Override
    public int update(MemberVO memberVO) {
        return memberDAO.update(memberVO);
    }
    
    @Override
    public MemberVO read(int memberno) {
        return memberDAO.read(memberno);
    }
    
    @Override
    public MemberVO checkUser(MemberVO memberVO) {
        return memberDAO.checkUser(memberVO);
    }
    
}
