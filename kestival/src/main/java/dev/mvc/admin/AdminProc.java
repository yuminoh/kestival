package dev.mvc.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.member.MemberVO;

@Component("dev.mvc.admin.AdminProc")
public class AdminProc implements AdminProcInter{
    @Autowired
    private AdminDAOInter adminDAO;
    
    public AdminProc(){
        System.out.println("-> AdminProc created.");
    }
    
    @Override
    public AdminVO checkUser(AdminVO adminVO) {
        return adminDAO.checkUser(adminVO);
    }
}
