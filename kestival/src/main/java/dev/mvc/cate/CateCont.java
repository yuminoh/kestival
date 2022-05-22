package dev.mvc.cate;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.kestival.paging.Criteria;
import dev.mvc.kestival.paging.PageBox;
import dev.mvc.qna.QnaProcInter;
import dev.mvc.qna.QnaVO;

@Controller
public class CateCont {
    @Autowired
    @Qualifier("dev.mvc.cate.CateProc")
    CateProcInter cateProc;
    
    @RequestMapping(value="/cate/insert.do", method=RequestMethod.GET)
    public ModelAndView insert(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        String id = (String) session.getAttribute("id");
        session.getAttribute("login");
        int adminno = (int) session.getAttribute("adminno");
        System.out.print("adminno: " +adminno);
        if(id == null) {
            mav.setViewName("redirect:/admin/login.do");
        } else {
            mav.setViewName("cate/insert"); 
        }
        
        return mav;
    }
    
    @RequestMapping(value="/cate/insert.do", method=RequestMethod.POST)
    public ModelAndView insert(HttpSession session, CateVO cateVO) {
        ModelAndView mav = new ModelAndView();
        session.getAttribute("login");
        //String id = (String) session.getAttribute("id");
        //cateVO.setQwriter(id);
        
        int adminno = (int) session.getAttribute("adminno");
        cateVO.setAdminno(adminno);
        int cnt = this.cateProc.insert(cateVO);
        //mav.addObject("cnt", cnt);
        mav.setViewName("redirect:/cate/list.do");
        
        return mav;
    }
    
    @RequestMapping(value="/cate/list.do", method=RequestMethod.GET)
    public ModelAndView selectList(Criteria criteria, @RequestParam(value="word", required=false, defaultValue="") String word) {
        ModelAndView mav = new ModelAndView();
        
        PageBox pageb = new PageBox();
        pageb.setCriteria(criteria);
        pageb.setTotalCount(cateProc.count(criteria));
        List<Map<String, Object>> list = this.cateProc.listsps(criteria);
        
        mav.addObject("pageb", pageb);
        mav.addObject("word", word);
        mav.addObject("list", list);
        mav.setViewName("cate/list");
        return mav;
        
    }
    
    @RequestMapping(value="/cate/delete.do", method=RequestMethod.GET)
    public ModelAndView delete_read(HttpSession session, int cateno) {
        ModelAndView mav = new ModelAndView();
        
        String sessionid = (String) session.getAttribute("id");
       // logger.info("{}", sessionid);
        CateVO cateVO = this.cateProc.delete_read(cateno);
        //String id = cateVO.getQwriter();
        //logger.info("{}", id);
        /*if(sessionid == null) {
            mav.setViewName("redirect:board//member/login.do");
        } else if(!sessionid.equals(id)) {
            mav.setViewName("post/deleteFail");
        } else {*/
            mav.addObject("cateVO", cateVO);
            mav.addObject("cateno", cateVO.getCateno());
            mav.setViewName("cate/delete");
        //}
        
        return mav;
    }
    
    @RequestMapping(value="/cate/delete.do", method=RequestMethod.POST)
    public ModelAndView delete(int cateno) {
        ModelAndView mav = new ModelAndView();
        int cnt = this.cateProc.delete(cateno);
        mav.addObject("cateno", cateno);
        
        mav.setViewName("redirect:/cate/list.do");
        return mav;
    }
    
    @RequestMapping(value="/cate/update.do", method=RequestMethod.GET)
    public ModelAndView update_read(HttpSession session, int cateno) {
        ModelAndView mav = new ModelAndView();
        session.getAttribute("login");
        String sessionid = (String) session.getAttribute("id");
        CateVO cateVO = this.cateProc.update_read(cateno);
        //String id = qnaVO.getQwriter();
        /*if(sessionid == null) {
            mav.setViewName("redirect:/member/login.do");
        } else if(!sessionid.equals(id)){
            mav.setViewName("post/updateFail");
        } else {*/
            mav.addObject("cateVO", cateVO);
            mav.addObject("cateno", cateVO.getCateno());
            mav.setViewName("cate/update");
        //}
        
        return mav;
    }
    
    @RequestMapping(value="/cate/update.do", method=RequestMethod.POST)
    public ModelAndView update(CateVO cateVO) {
        ModelAndView mav = new ModelAndView();
    
        int cnt = this.cateProc.update(cateVO);
        mav.addObject("cateVO", cateVO);
        mav.addObject("cateno", cateVO.getCateno());
        mav.setViewName("redirect:/cate/list.do");
        
        return mav;
    }
    
    @RequestMapping(value="/cate/read.do", method=RequestMethod.GET)
    public ModelAndView selectOne(int cateno) {
        ModelAndView mav = new ModelAndView();
        CateVO cateVO = this.cateProc.read(cateno);
       // logger.info("{}", postno);
        
        mav.addObject("cateVO", cateVO);
        
        mav.setViewName("cate/read");
        return mav;
    }
}
