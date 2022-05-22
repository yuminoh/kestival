package dev.mvc.qna;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import dev.mvc.kestival.paging.Criteria;
import dev.mvc.kestival.paging.PageBox;
import dev.mvc.member.MemberVO;
import dev.mvc.reply.ReplyVO;

@Controller
public class QnaCont {
    @Autowired
    @Qualifier("dev.mvc.qna.QnaProc")
    QnaProcInter qnaProc;
    
    //@Autowired
    //ReplyService replyService;
    public QnaCont() {
        System.out.println("-> QnaCont created.");
      }
    //
    //MemberVO memberVO;
    
    //private Logger logger = LoggerFactory.getLogger(PostCont.class);
    
    @RequestMapping(value="/qna/insert.do", method=RequestMethod.GET)
    public ModelAndView insert(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        String id = (String) session.getAttribute("id");
        session.getAttribute("login");
        int memberno = (int) session.getAttribute("memberno");
        System.out.print("memberno: " +memberno);
        if(id == null) {
            mav.setViewName("redirect:/member/login.do");
        } else {
            mav.setViewName("qna/insert"); 
        }
        
        return mav;
    }
    
    @RequestMapping(value="/qna/insert.do", method=RequestMethod.POST)
    public ModelAndView insert(HttpSession session, QnaVO qnaVO) {
        ModelAndView mav = new ModelAndView();
        session.getAttribute("login");
        String id = (String) session.getAttribute("id");
        int memberno = (int) session.getAttribute("memberno");
        qnaVO.setQwriter(id);
        qnaVO.setMemberno(memberno);
        int cnt = this.qnaProc.insert(qnaVO);
        //mav.addObject("cnt", cnt);
        mav.setViewName("redirect:/qna/list.do");
        
        return mav;
    }
    
    @RequestMapping(value="/qna/list.do", method=RequestMethod.GET)
    public ModelAndView selectList(Criteria criteria, @RequestParam(value="word", required=false, defaultValue="") String word) {
        ModelAndView mav = new ModelAndView();
        
        PageBox pageb = new PageBox();
        pageb.setCriteria(criteria);
        pageb.setTotalCount(qnaProc.count(criteria));
        List<Map<String, Object>> list = this.qnaProc.listsps(criteria);
        
        mav.addObject("pageb", pageb);
        mav.addObject("word", word);
        mav.addObject("list", list);
        mav.setViewName("qna/list");
        return mav;
    }
    
    @RequestMapping(value="/qna/delete.do", method=RequestMethod.GET)
    public ModelAndView delete_read(HttpSession session, int qnano) {
        ModelAndView mav = new ModelAndView();
        
        String sessionid = (String) session.getAttribute("id");
       // logger.info("{}", sessionid);
        QnaVO qnaVO = this.qnaProc.delete_read(qnano);
        String id = qnaVO.getQwriter();
        //logger.info("{}", id);
        /*if(sessionid == null) {
            mav.setViewName("redirect:board//member/login.do");
        } else if(!sessionid.equals(id)) {
            mav.setViewName("post/deleteFail");
        } else {*/
            mav.addObject("qnaVO", qnaVO);
            mav.addObject("qnano", qnaVO.getQnano());
            mav.setViewName("qna/delete");
        //}
        
        return mav;
    }
    
    @RequestMapping(value="/qna/delete.do", method=RequestMethod.POST)
    public ModelAndView delete(int qnano) {
        ModelAndView mav = new ModelAndView();
        int cnt = this.qnaProc.delete(qnano);
        mav.addObject("qnano", qnano);
        
        mav.setViewName("redirect:/qna/list.do");
        return mav;
    }
    
    @RequestMapping(value="/qna/deleteFail.do", method=RequestMethod.GET)
    public ModelAndView deleteFail() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("qna/deleteFail");
        
        return mav;
    }
    
    @RequestMapping(value="/qna/update.do", method=RequestMethod.GET)
    public ModelAndView update_read(HttpSession session, int qnano) {
        ModelAndView mav = new ModelAndView();
        session.getAttribute("login");
        String sessionid = (String) session.getAttribute("id");
        QnaVO qnaVO = this.qnaProc.update_read(qnano);
        String id = qnaVO.getQwriter();
        /*if(sessionid == null) {
            mav.setViewName("redirect:/member/login.do");
        } else if(!sessionid.equals(id)){
            mav.setViewName("post/updateFail");
        } else {*/
            mav.addObject("qnaVO", qnaVO);
            mav.addObject("qnano", qnaVO.getQnano());
            mav.setViewName("qna/update");
        //}
        
        return mav;
    }
    
    @RequestMapping(value="/qna/update.do", method=RequestMethod.POST)
    public ModelAndView update(QnaVO qnaVO) {
        ModelAndView mav = new ModelAndView();
    
        int cnt = this.qnaProc.update(qnaVO);
        mav.addObject("qnaVO", qnaVO);
        mav.addObject("qnano", qnaVO.getQnano());
        mav.setViewName("redirect:/qna/list.do");
        
        return mav;
    }
    
    @RequestMapping(value="/qna/updateFail.do", method=RequestMethod.GET)
    public ModelAndView updateFail() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("qna/updateFail");
        
        return mav;
    }
    
    @RequestMapping(value="/qna/read.do", method=RequestMethod.GET)
    public ModelAndView selectOne(int qnano) {
        ModelAndView mav = new ModelAndView();
        QnaVO qnaVO = this.qnaProc.read(qnano);
       // logger.info("{}", postno);
        
        mav.addObject("qnaVO", qnaVO);
        
        mav.setViewName("qna/read");
        return mav;
    }
    
    @ResponseBody
    @RequestMapping(value = "/qna/replylist.do",produces = "application/json", method = RequestMethod.GET)
    public String ajax_replylist(@RequestParam(value="qnano")int qnano, HttpSession session) {
        
        List<ReplyVO> replylist = this.qnaProc.reselectList(qnano);
        Gson gson = new Gson();
        String json = gson.toJson(replylist);
        
        return json;
    }
    
    @ResponseBody
    @RequestMapping(value="/qna/reinsert.do",produces = "application/json", method=RequestMethod.POST)
    public String insert(ReplyVO replyVO, HttpSession session){
        
        session.getAttribute("login");
        int adminno = (int) session.getAttribute("adminno");
        String rwiter = (String) session.getAttribute("id");
        //logger.info("/post/reinsert.do memberno: {}", memberno);
        replyVO.setAdminno(adminno);
        replyVO.setRwiter(rwiter);
        qnaProc.reinsert(replyVO);
        Gson gson = new Gson();
        String json = gson.toJson(replyVO);
        
        return json;
    }
 /*
    @ResponseBody
    @RequestMapping(value="/qna/reupdate.do",produces = "application/json", method=RequestMethod.POST)
    public String update(ReplyVO replyVO, HttpSession session){
        
        session.getAttribute("login");
        int adminno = (int) session.getAttribute("adminno");
        int replyno = replyVO.getReplyno();
        String id = replyVO.getRwiter();
        replyVO.setAdminno(adminno);
        qnaProc.update(replyVO);
        Gson gson = new Gson();
        String json = gson.toJson(replyVO);
        
        return json;
    }
    */
    @ResponseBody
    @RequestMapping(value="/qna/reupdate.do",produces = "application/json", method=RequestMethod.POST)
    public String update(ReplyVO replyVO, HttpSession session){
        
        session.getAttribute("login");
        String sessionid = (String) session.getAttribute("id");
        int adminno = (int) session.getAttribute("adminno");
        int replyno = replyVO.getReplyno();
        String id = replyVO.getRwiter();
        
        replyVO.setAdminno(adminno);
        qnaProc.reupdate(replyVO);
        Gson gson = new Gson();
        String json = gson.toJson(replyVO);
        
        return json;
    }
    
    @ResponseBody
    @RequestMapping(value="/qna/redelete.do", produces="application/json", method=RequestMethod.POST)
    public String replydelete(@RequestParam(value="replyno")int replyno, HttpSession session, ModelAndView mav) {
        int cnt = qnaProc.replydelete(replyno);
        Gson gson = new Gson();
        String json = gson.toJson(cnt);
        
        return json;
    } 
}
