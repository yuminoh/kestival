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
        // 파일 전송 코드 시작
        // ------------------------------------------------------------------------------
        String file1 = "";          // 원본 파일명 image
        String file1saved = "";  // 저장된 파일명, image
        String thumb1 = "";     // preview image

        // 기준 경로 확인
        String user_dir = System.getProperty("user.dir"); // 시스템 제공
        System.out.println("-> User dir: " + user_dir);
        //  --> User dir: C:\kd1\ws_java\escape_yum
        
        // 파일 접근임으로 절대 경로 지정, static 폴더 지정
        // 완성된 경로 C:/kd1/ws_java/resort_v1sbm3c/src/main/resources/static/contents/storage
        String upDir =  user_dir + "/src/main/resources/static/contents/storage/"; // 절대 경로
        // System.out.println("-> upDir: " + upDir);
        
        // 전송 파일이 없어도 file1MF 객체가 생성됨.
        // <input type='file' class="form-control" name='file1MF' id='file1MF' 
        //           value='' placeholder="파일 선택">
        MultipartFile mf = contentsVO.getFile1MF();
        file1 = Tool.getFname(mf.getOriginalFilename()); // 원본 순수 파일명 산출
        int size1 = (int) mf.getSize();  // 파일 크기
        
        if (size1 > 0) { // 파일 크기 체크
            // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
            file1saved = Upload.saveFileSpring(mf, upDir); 
            
            if (Tool.isImage(file1saved)) { // 이미지인지 검사
              // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
              thumb1 = Tool.preview(upDir, file1, 200, 150); 
            }
            
          } 
        
        contentsVO.setFile1(file1);
        contentsVO.setFile1saved(file1saved);
        contentsVO.setThumb1(thumb1);
        contentsVO.setSize1(size1);
        // ------------------------------------------------------------------------------
        // 파일 전송 코드 종료
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
        // 파일 삭제 코드 시작
        // -------------------------------------------------------------------
        ContentsVO vo = contentsProc.read(contentsno);
      
        String file1saved = vo.getFile1saved();
        String thumb1 = vo.getThumb1();
        int size1 = 0;
        boolean sw = false;
        
        // 완성된 경로 F:/ai8/ws_frame/resort_v1sbm3a/src/main/resources/static/contents/storage/
        String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/contents/storage/"; // 절대 경로

        sw = Tool.deleteFile(upDir, file1saved);  // Folder에서 1건의 파일 삭제  
        sw = Tool.deleteFile(upDir, thumb1);   
        // System.out.println("sw: " + sw);
        // -------------------------------------------------------------------
        // 파일 삭제 종료 시작
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
        // 파일 삭제 코드 시작
        // -------------------------------------------------------------------

        String file1saved = contentsVO_old.getFile1saved();
        String thumb1 = contentsVO_old.getThumb1();
        int size1 = 0;
        boolean sw = false;
        
        // 완성된 경로 F:/ai8/ws_frame/resort_v1sbm3a/src/main/resources/static/contents/storage/
        String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/contents/storage/"; // 절대 경로

        sw = Tool.deleteFile(upDir, file1saved);  // Folder에서 1건의 파일 삭제
        sw = Tool.deleteFile(upDir, thumb1);   
        // System.out.println("sw: " + sw);
        // -------------------------------------------------------------------
        // 파일 삭제 종료 시작
        // -------------------------------------------------------------------
        
        // -------------------------------------------------------------------
        // 파일 전송 코드 시작
        // -------------------------------------------------------------------
        String file1 = "";    
       
        MultipartFile mf = contentsVO.getFile1MF();
        
        file1 = mf.getOriginalFilename(); // 원본 파일명
        size1 = (int)mf.getSize();  // 파일 크기
        
        if (size1 > 0) { // 파일 크기 체크
          // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
          file1saved = Upload.saveFileSpring(mf, upDir); 
          
          if (Tool.isImage(file1)) { // 이미지인지 검사
            // thumb 이미지 생성후 파일명 리턴됨, width: 250, height: 200
            thumb1 = Tool.preview(upDir, file1saved, 250, 200); 
          }
          
        } else { // 파일이 삭제만 되고 새로 올리지 않는 경우
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
        // 파일 전송 코드 종료
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

      // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("word", word); // #{word}
      map.put("now_page", now_page); // 페이지에 출력할 레코드의 범위를 산출하기위해 사용

      // 검색 목록
      List<ContentsVO> list = contentsProc.listGrid(map);
      mav.addObject("list", list);

      // 검색된 레코드 갯수
      int search_countGrid = contentsProc.search_countGrid(map);
      mav.addObject("search_countGrid", search_countGrid);
      
      //CateVO cateVO = this.cateProc.read(cateno); 
      //mav.addObject("cateVO", cateVO);
      
      /*
       * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13
       * 18 19 20 [다음]
       * @param cateno 카테고리번호
       * @param search_count 검색(전체) 레코드수
       * @param now_page 현재 페이지
       * @param word 검색어
       * @return 페이징 생성 문자열
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

      // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("cateno", cateno); // #{themagrpno}
      map.put("word", word); // #{word}
      map.put("now_page", now_page); // 페이지에 출력할 레코드의 범위를 산출하기위해 사용

      // 검색 목록
      List<ContentsVO> list = contentsProc.list(map);
      mav.addObject("list", list);

      // 검색된 레코드 갯수
      int search_count = contentsProc.search_count(map);
      mav.addObject("search_count", search_count);
      
      CateVO cateVO = this.cateProc.read(cateno); 
      System.out.print(cateno);
      mav.addObject("cateVO", cateVO);
      
      /*
       * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13
       * 18 19 20 [다음]
       * @param cateno 카테고리번호
       * @param search_count 검색(전체) 레코드수
       * @param now_page 현재 페이지
       * @param word 검색어
       * @return 페이징 생성 문자열
       */
      String paging = contentsProc.pagingBox(cateno, search_count, now_page, word);
      mav.addObject("paging", paging);

      mav.addObject("now_page", now_page);

      mav.setViewName("/contents/list");

      return mav;
    }
}
