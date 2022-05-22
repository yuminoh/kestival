package dev.mvc.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberCont {
    @Autowired
    @Qualifier("dev.mvc.member.MemberProc")
    private MemberProcInter memberProc = null;
    
    public MemberCont(){
      System.out.println("-> MemberCont created.");
    }
    
    @RequestMapping(value="/member/insert.do", method=RequestMethod.GET )
    public ModelAndView insert() {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/member/insert"); // webapp/member/create.jsp
     
      return mav; // forward
    }
    
    @RequestMapping(value="/member/insert.do", method=RequestMethod.POST)
    public ModelAndView insert(MemberVO memberVO) {
        ModelAndView mav = new ModelAndView();
        
        int cnt= memberProc.insert(memberVO);
        
        if(cnt == 1) {
            mav.setViewName("/member/login");
        } else {
            mav.setViewName("redirect:/member/insert.do");
        }
        return mav;
    }
    

    @RequestMapping(value="/member/update_read.do", method=RequestMethod.GET)
    public ModelAndView update_read(int memberno) {
        ModelAndView mav = new ModelAndView();
        
        MemberVO memberVO = this.memberProc.read(memberno);
        mav.addObject("memberVO", memberVO);
        mav.setViewName("member/update_read");
        
        return mav;
    }
    
    @RequestMapping(value="/member/update.do", method=RequestMethod.GET)
    public ModelAndView update(int memberno) {
        ModelAndView mav = new ModelAndView();
        
        MemberVO memberVO = this.memberProc.read(memberno);
        mav.addObject("memberVO", memberVO);
        mav.setViewName("member/update");
        
        return mav;
    }
    
    @RequestMapping(value="/member/update.do", method=RequestMethod.POST)
    public ModelAndView update(MemberVO memberVO) {
        ModelAndView mav = new ModelAndView();
        
        int cnt = this.memberProc.update(memberVO);
        int memberno = memberVO.getMemberno();
        mav.addObject("memberno", memberno);
        if(cnt == 1) {
            mav.addObject("memberVO", memberVO);
            mav.setViewName("member/update_read.do");
            System.out.println(memberVO.getAddress1());
        }
        
        return mav;
    }

    @RequestMapping(value="/member/login.do", method=RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/member/login");
        return mav;
    }
    
    @RequestMapping(value="/member/login.do", method=RequestMethod.POST)
    public ModelAndView login(HttpSession session, MemberVO memberVO) {
        ModelAndView mav = new ModelAndView();
        
        memberVO = memberProc.checkUser(memberVO);
        
        if(memberVO != null) {
            String id = memberVO.getId();
            session.setAttribute("memberno", memberVO.getMemberno());
            session.setAttribute("id", memberVO.getId());
            session.setAttribute("mname", memberVO.getMname());
            session.setAttribute("login", memberVO);
            int memberno =(int) session.getAttribute("memberno");
            System.out.print("memberno: " +memberno);
            mav.setViewName("redirect:/");
        } else {
            mav.setViewName("member/loginFail");
            System.out.println("-->로그인 실패");
        }
        
        return mav;
    }
    
    @RequestMapping(value="/member/logout.do", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView logout(HttpSession session, MemberVO memberVO) {
        ModelAndView mav = new ModelAndView();
        String id="";
        if(session != null && session.getAttribute("login") != null) {
            memberVO = (MemberVO) session.getAttribute("login");
            id = memberVO.getId();
        }
        
        //mav.addObject("id", id);
        mav.setViewName("redirect:/");
        session.invalidate();
        
        return mav;
    }
    /*
    @RequestMapping(value="/member/loginFail.do", method=RequestMethod.GET)
    public ModelAndView loginFail() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("member/loginFail");
        
        return mav;
    } */
}
