package dev.mvc.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminCont {
    @Autowired
    @Qualifier("dev.mvc.admin.AdminProc")
    private AdminProcInter adminProc = null;
    
    public AdminCont(){
      System.out.println("-> AdminCont created.");
    }
    
    @RequestMapping(value="/admin/login.do", method=RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/admin/login");
        return mav;
    }
    
    @RequestMapping(value="/admin/login.do", method=RequestMethod.POST)
    public ModelAndView login(HttpSession session, AdminVO adminVO) {
        ModelAndView mav = new ModelAndView();
        
        adminVO = adminProc.checkUser(adminVO);
        
        if(adminVO != null) {
            String id = adminVO.getId();
            session.setAttribute("adminno", adminVO.getAdminno());
            session.setAttribute("id", adminVO.getId());
            session.setAttribute("mname", adminVO.getMname());
            session.setAttribute("login", adminVO);
            
            int adminno =(int) session.getAttribute("adminno");
            mav.setViewName("redirect:/");
        } else {
            mav.setViewName("member/error");
            System.out.println("-->로그인 실패");
        }
        
        return mav;
    }
    
    @RequestMapping(value="/admin/logout.do", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView logout(HttpSession session, AdminVO adminVO) {
        ModelAndView mav = new ModelAndView();
        String id="";
        if(session != null && session.getAttribute("login") != null) {
            adminVO = (AdminVO) session.getAttribute("login");
            id = adminVO.getId();
        }
        
        //mav.addObject("id", id);
        mav.setViewName("redirect:/");
        session.invalidate();
        
        return mav;
    }
    
    @RequestMapping(value="/member/error.do", method=RequestMethod.GET)
    public ModelAndView loginFail() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("member/error");
        
        return mav;
    }
}
