package dev.mvc.contents;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.cate.CateProcInter;
import dev.mvc.cate.CateVO;
import dev.mvc.kestival.paging.Criteria;
import dev.mvc.kestival.paging.PageBox;
import dev.mvc.qna.QnaProcInter;
import dev.mvc.qna.QnaVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class ContentsCont {
    @Autowired
    @Qualifier("dev.mvc.contents.ContentsProc")
    ContentsProcInter contentsProc;
    
    @Autowired
    @Qualifier("dev.mvc.cate.CateProc")
    private CateProcInter cateProc;
    
    public ContentsCont() {
        System.out.println("-> ContentsCont created.");
      }
    
    @RequestMapping(value="/contents/insert.do", method=RequestMethod.GET)
    public ModelAndView insert(HttpSession session, int cateno) {
        ModelAndView mav = new ModelAndView();
        String id = (String) session.getAttribute("id");
        
        //CateVO cateVO = this.cateProc.read(cateno);
        //System.out.print(cateVO.getName());
        //mav.addObject("cateVO", cateVO);
        
        mav.setViewName("contents/insert"); 
        
        return mav;
    }
    
    @RequestMapping(value="/contents/insert.do", method=RequestMethod.POST)
    public ModelAndView insert(HttpSession session, ContentsVO contentsVO) {
        ModelAndView mav = new ModelAndView();
        int cateno = contentsVO.getCateno();
     // ------------------------------------------------------------------------------
        // ?????? ?????? ?????? ??????
        // ------------------------------------------------------------------------------
        String file1 = "";          // ?????? ????????? image
        String file1saved = "";  // ????????? ?????????, image
        String thumb1 = "";     // preview image

        // ?????? ?????? ??????
        String user_dir = System.getProperty("user.dir"); // ????????? ??????
        System.out.println("-> User dir: " + user_dir);
        //  --> User dir: C:\kd1\ws_java\escape_yum
        
        // ?????? ??????????????? ?????? ?????? ??????, static ?????? ??????
        // ????????? ?????? C:/kd1/ws_java/resort_v1sbm3c/src/main/resources/static/contents/storage
        String upDir =  user_dir + "/src/main/resources/static/contents/storage/"; // ?????? ??????
        // System.out.println("-> upDir: " + upDir);
        
        // ?????? ????????? ????????? file1MF ????????? ?????????.
        // <input type='file' class="form-control" name='file1MF' id='file1MF' 
        //           value='' placeholder="?????? ??????">
        MultipartFile mf = contentsVO.getFile1MF();
        file1 = Tool.getFname(mf.getOriginalFilename()); // ?????? ?????? ????????? ??????
        int size1 = (int) mf.getSize();  // ?????? ??????
        
        if (size1 > 0) { // ?????? ?????? ??????
            // ?????? ?????? ??? ???????????? ???????????? ?????????, spring.jsp, spring_1.jpg...
            file1saved = Upload.saveFileSpring(mf, upDir); 
            
            if (Tool.isImage(file1saved)) { // ??????????????? ??????
              // thumb ????????? ????????? ????????? ?????????, width: 200, height: 150
              thumb1 = Tool.preview(upDir, file1, 200, 150); 
            }
            
          } 
        
        contentsVO.setFile1(file1);
        contentsVO.setFile1saved(file1saved);
        contentsVO.setThumb1(thumb1);
        contentsVO.setSize1(size1);
        // ------------------------------------------------------------------------------
        // ?????? ?????? ?????? ??????
        // ------------------------------------------------------------------------------
        /*
        session.getAttribute("login");
        String id = (String) session.getAttribute("id");
        int adminno = (int) session.getAttribute("adminno");
        contentsVO.setAdminno(adminno);
        */
        int cnt = this.contentsProc.insert(contentsVO);
        //mav.addObject("cnt", cnt);
        mav.setViewName("redirect:/contents/list.do?cateno="+cateno);
        
        return mav;
    }
    /*
    @RequestMapping(value="/contents/list.do", method=RequestMethod.GET)
    public ModelAndView selectList(Criteria criteria, @RequestParam(value="word", required=false, defaultValue="") String word, @RequestParam(value="cateno", required=true)int cateno) {
        ModelAndView mav = new ModelAndView();
        System.out.print("word: "+word);
        System.out.print("cateno: "+cateno);
        PageBox pageb = new PageBox();
        pageb.setCriteria(criteria);
        pageb.setTotalCount(contentsProc.count(criteria, cateno));
        List<Map<String, Object>> list = this.contentsProc.listsps(cateno, criteria);
        mav.addObject("pageb", pageb);
        mav.addObject("word", word);
        mav.addObject("list", list);
        mav.setViewName("contents/list");
        return mav;
    }
    */
    @RequestMapping(value="/contents/delete.do", method=RequestMethod.GET)
    public ModelAndView delete_read(HttpSession session, int contentsno) {
        ModelAndView mav = new ModelAndView();
        
        String sessionid = (String) session.getAttribute("id");
       // logger.info("{}", sessionid);
        ContentsVO contentsVO = this.contentsProc.delete_read(contentsno);
        //String id = qnaVO.getQwriter();
        //logger.info("{}", id);
        /*if(sessionid == null) {
            mav.setViewName("redirect:board//member/login.do");
        } else if(!sessionid.equals(id)) {
            mav.setViewName("post/deleteFail");
        } else {*/
            mav.addObject("contentsVO", contentsVO);
            mav.addObject("contentsno", contentsVO.getContentsno());
            mav.setViewName("contents/delete");
        //}
        
        return mav;
    }
    
    @RequestMapping(value="/contents/delete.do", method=RequestMethod.POST)
    public ModelAndView delete(int contentsno) {
        ModelAndView mav = new ModelAndView();
        
     // -------------------------------------------------------------------
        // ?????? ?????? ?????? ??????
        // -------------------------------------------------------------------
        ContentsVO vo = contentsProc.read(contentsno);
      
        String file1saved = vo.getFile1saved();
        String thumb1 = vo.getThumb1();
        int size1 = 0;
        boolean sw = false;
        
        // ????????? ?????? F:/ai8/ws_frame/resort_v1sbm3a/src/main/resources/static/contents/storage/
        String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/contents/storage/"; // ?????? ??????

        sw = Tool.deleteFile(upDir, file1saved);  // Folder?????? 1?????? ?????? ??????  
        sw = Tool.deleteFile(upDir, thumb1);   
        // System.out.println("sw: " + sw);
        // -------------------------------------------------------------------
        // ?????? ?????? ?????? ??????
        // -------------------------------------------------------------------
       
        // -------------------------------------------------------------------------------------
        int cnt = this.contentsProc.delete(contentsno);
        mav.addObject("contentsno", contentsno);
        int cateno = vo.getCateno();
        mav.setViewName("redirect:/contents/list.do?cateno="+cateno);
        return mav;
    }
    
    @RequestMapping(value="/contents/update.do", method=RequestMethod.GET)
    public ModelAndView update_read(HttpSession session, int contentsno) {
        ModelAndView mav = new ModelAndView();
        
        session.getAttribute("login");
        String sessionid = (String) session.getAttribute("id");
        ContentsVO contentsVO = this.contentsProc.update_read(contentsno);
        //String id = contentsVO.getQwriter();
        /*if(sessionid == null) {
            mav.setViewName("redirect:/member/login.do");
        } else if(!sessionid.equals(id)){
            mav.setViewName("post/updateFail");
        } else {*/
            mav.addObject("contentsVO", contentsVO);
            mav.addObject("contentsno", contentsVO.getContentsno());
            mav.setViewName("contents/update");
        //}
        
        return mav;
    }
    
    @RequestMapping(value="/contents/update.do", method=RequestMethod.POST)
    public ModelAndView update(ContentsVO contentsVO) {
        ModelAndView mav = new ModelAndView();
        int cateno = contentsVO.getCateno();
        ContentsVO contentsVO_old = contentsProc.read(contentsVO.getContentsno());
        
     // -------------------------------------------------------------------
        // ?????? ?????? ?????? ??????
        // -------------------------------------------------------------------

        String file1saved = contentsVO_old.getFile1saved();
        String thumb1 = contentsVO_old.getThumb1();
        int size1 = 0;
        boolean sw = false;
        
        // ????????? ?????? F:/ai8/ws_frame/resort_v1sbm3a/src/main/resources/static/contents/storage/
        String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/contents/storage/"; // ?????? ??????

        sw = Tool.deleteFile(upDir, file1saved);  // Folder?????? 1?????? ?????? ??????
        sw = Tool.deleteFile(upDir, thumb1);   
        // System.out.println("sw: " + sw);
        // -------------------------------------------------------------------
        // ?????? ?????? ?????? ??????
        // -------------------------------------------------------------------
        
        // -------------------------------------------------------------------
        // ?????? ?????? ?????? ??????
        // -------------------------------------------------------------------
        String file1 = "";    
       
        MultipartFile mf = contentsVO.getFile1MF();
        
        file1 = mf.getOriginalFilename(); // ?????? ?????????
        size1 = (int)mf.getSize();  // ?????? ??????
        
        if (size1 > 0) { // ?????? ?????? ??????
          // ?????? ?????? ??? ???????????? ???????????? ?????????, spring.jsp, spring_1.jpg...
          file1saved = Upload.saveFileSpring(mf, upDir); 
          
          if (Tool.isImage(file1)) { // ??????????????? ??????
            // thumb ????????? ????????? ????????? ?????????, width: 250, height: 200
            thumb1 = Tool.preview(upDir, file1saved, 250, 200); 
          }
          
        } else { // ????????? ????????? ?????? ?????? ????????? ?????? ??????
            file1="";
            file1saved="";
            thumb1="";
            size1=0;
        }
        
        contentsVO.setFile1(file1);
        contentsVO.setFile1saved(file1saved);
        contentsVO.setThumb1(thumb1);
        contentsVO.setSize1(size1);
        // -------------------------------------------------------------------
        // ?????? ?????? ?????? ??????
        // -------------------------------------------------------------------
        
        int cnt = this.contentsProc.update(contentsVO);
        mav.addObject("contentsVO", contentsVO);
        mav.addObject("contentsno", contentsVO.getContentsno());
        mav.setViewName("redirect:/contents/list.do");
        mav.addObject("cateno", cateno);
        return mav;
    }
    
    @RequestMapping(value="/contents/read.do", method=RequestMethod.GET)
    public ModelAndView selectOne(int contentsno) {
        ModelAndView mav = new ModelAndView();
        ContentsVO contentsVO = this.contentsProc.read(contentsno);
        
        mav.addObject("contentsVO", contentsVO);
        mav.setViewName("contents/read");
        return mav;
    }
    
    @RequestMapping(value = "/contents/update_recom_ajax.do", method = RequestMethod.POST)
    @ResponseBody
    public String update_recom_ajax(int contentsno) {
      try {
        Thread.sleep(2000); 
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      int cnt = this.contentsProc.update_recom(contentsno);
      int recom = this.contentsProc.read(contentsno).getRecom(); 
      JSONObject json = new JSONObject();
      json.put("cnt", cnt);
      json.put("recom", recom);
      
      return json.toString();
    }
    
    @RequestMapping(value = "/contents/listAll.do", method = RequestMethod.GET)
    public ModelAndView listGrid( @RequestParam(value = "word", defaultValue = "") String word,
                                        @RequestParam(value = "now_page", defaultValue = "1") int now_page) {
      System.out.println("--> now_page: " + now_page);

      ModelAndView mav = new ModelAndView();

      // ????????? ????????? ????????? ????????????????????? Obejct ??????
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("word", word); // #{word}
      map.put("now_page", now_page); // ???????????? ????????? ???????????? ????????? ?????????????????? ??????

      // ?????? ??????
      List<ContentsVO> list = contentsProc.listGrid(map);
      mav.addObject("list", list);

      // ????????? ????????? ??????
      int search_countGrid = contentsProc.search_countGrid(map);
      mav.addObject("search_countGrid", search_countGrid);
      
      //CateVO cateVO = this.cateProc.read(cateno); 
      //mav.addObject("cateVO", cateVO);
      
      /*
       * SPAN????????? ????????? ?????? ????????? ??????, 1 ??????????????? ?????? ?????? ?????????: 11 / 22 [??????] 11 12 13
       * 18 19 20 [??????]
       * @param cateno ??????????????????
       * @param search_count ??????(??????) ????????????
       * @param now_page ?????? ?????????
       * @param word ?????????
       * @return ????????? ?????? ?????????
       */
      String paging = contentsProc.pagingB(search_countGrid, now_page, word);
      mav.addObject("paging", paging);

      mav.addObject("now_page", now_page);

      mav.setViewName("/contents/listAll");

      return mav;
    }
    
    @RequestMapping(value = "/contents/list.do", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam(value = "cateno", defaultValue = "1") int cateno,
                                                                           @RequestParam(value = "word", defaultValue = "") String word,
                                                                           @RequestParam(value = "now_page", defaultValue = "1") int now_page) {
      System.out.println("--> now_page: " + now_page);

      ModelAndView mav = new ModelAndView();

      // ????????? ????????? ????????? ????????????????????? Obejct ??????
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("cateno", cateno); // #{themagrpno}
      map.put("word", word); // #{word}
      map.put("now_page", now_page); // ???????????? ????????? ???????????? ????????? ?????????????????? ??????

      // ?????? ??????
      List<ContentsVO> list = contentsProc.list(map);
      mav.addObject("list", list);

      // ????????? ????????? ??????
      int search_count = contentsProc.search_count(map);
      mav.addObject("search_count", search_count);
      
      CateVO cateVO = this.cateProc.read(cateno); 
      System.out.print(cateno);
      mav.addObject("cateVO", cateVO);
      
      /*
       * SPAN????????? ????????? ?????? ????????? ??????, 1 ??????????????? ?????? ?????? ?????????: 11 / 22 [??????] 11 12 13
       * 18 19 20 [??????]
       * @param cateno ??????????????????
       * @param search_count ??????(??????) ????????????
       * @param now_page ?????? ?????????
       * @param word ?????????
       * @return ????????? ?????? ?????????
       */
      String paging = contentsProc.pagingBox(cateno, search_count, now_page, word);
      mav.addObject("paging", paging);

      mav.addObject("now_page", now_page);

      mav.setViewName("/contents/list");

      return mav;
    }
}
