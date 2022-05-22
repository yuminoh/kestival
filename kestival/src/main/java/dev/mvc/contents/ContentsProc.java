package dev.mvc.contents;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dev.mvc.kestival.paging.Criteria;
import dev.mvc.qna.QnaDAOInter;
import dev.mvc.qna.QnaVO;
import dev.mvc.tool.Tool;

@Component("dev.mvc.contents.ContentsProc")
public class ContentsProc implements ContentsProcInter{
    @Autowired
    private ContentsDAOInter contentsDAO;
    
    
    public ContentsProc(){
      System.out.println("-> ContentsProc created.");
    }
    
    @Override
    public int insert(ContentsVO contentsVO) {
        int cnt = this.contentsDAO.insert(contentsVO);
        return cnt;
    }
    
    @Override
    public ContentsVO delete_read(int contentsno) {
        ContentsVO contentsVO = this.contentsDAO.read(contentsno);
        return contentsVO;
    }
    
    @Override
    public int delete(int contentsno) {
        //return postDAO.delete(postno);
        int cnt = this.contentsDAO.delete(contentsno);
        return cnt;
    }
    
    @Override
    public int update(ContentsVO contentsVO) {
        int cnt = this.contentsDAO.update(contentsVO);
        
        return cnt;
    }
    
    @Override
    public ContentsVO update_read(int contentsno) {
        ContentsVO contentsVO = this.contentsDAO.read(contentsno);
        
        return contentsVO;
    }
    
    @Override
    public List<Map<String, Object>> listsps(int cateno, Criteria criteria) {
        return contentsDAO.listsps(cateno, criteria);
    }
    
    
    @Override
    public ContentsVO read(int contentsno) {
        ContentsVO contentsVO = this.contentsDAO.read(contentsno);
        
        String title = contentsVO.getTitle();
        String context = contentsVO.getContext();
        title = Tool.convertChar(title);  // 특수 문자 처리
        
        contentsVO.setTitle(title);
        
        long size1 = contentsVO.getSize1();
        contentsVO.setSize1_label(Tool.unit(size1));
        
        return contentsDAO.read(contentsno);
    }
    
    @Override
    public int count(Criteria criteria, int cateno) {
        return contentsDAO.count(criteria, cateno);
    }
    
    @Override
    public int update_recom(int contentsno) {
      int cnt = this.contentsDAO.update_recom(contentsno);
      return cnt;
    }
    
    @Override
    public int search_count(HashMap<String, Object> hashMap) {
        int count = contentsDAO.search_count(hashMap);
        return count;
    }
    
    @Override
    public int search_countGrid(HashMap<String, Object> hashMap) {
        int count = contentsDAO.search_countGrid(hashMap);
        return count;
    }
    
    @Override
    public List<ContentsVO> list(HashMap<String, Object> map) {
     
      int begin_of_page = ((Integer)map.get("now_page") - 1) * Contents.RECORD_PER_PAGE;
      int start_num = begin_of_page + 1;
       int end_num = begin_of_page + Contents.RECORD_PER_PAGE;   
     
      map.put("start_num", start_num);
      map.put("end_num", end_num);
      System.out.print(start_num);

      System.out.print(end_num);
     
      List<ContentsVO> list = this.contentsDAO.list(map);
      
      return list;
    }
    
    @Override
    public List<ContentsVO> listGrid(HashMap<String, Object> map) {
     
      int begin_of_page = ((Integer)map.get("now_page") - 1) * Contents.RECORD_PER_PAGE;
      int start_num = begin_of_page + 1;
       int end_num = begin_of_page + Contents.RECORD_PER_PAGE;   
     
      map.put("start_num", start_num);
      map.put("end_num", end_num);
      System.out.print(start_num);
      System.out.print(end_num);
      List<ContentsVO> list = this.contentsDAO.listGrid(map);
      
      return list;
    }
    
    /*
     *
     * @param list_file 목록 파일명 
     * @param themagrpno 테마그룹번호 
     * @param search_count 검색(전체) 레코드수 
     * @param now_page     현재 페이지
     * @param word 검색어
     * @return 페이징 생성 문자열
     */ 
    @Override
    public String pagingBox(int cateno, int search_count, int now_page, String word){ 
      int total_page = (int)(Math.ceil((double)search_count/Contents.RECORD_PER_PAGE)); // 전체 페이지 수 
      //int total_grp = (int)(Math.ceil((double)total_page/Thema.PAGE_PER_BLOCK)); // 전체 그룹  수
      int now_grp = (int)(Math.ceil((double)now_page/Contents.PAGE_PER_BLOCK));  // 현재 그룹 번호
      
      int start_num = ((now_grp - 1) * Contents.PAGE_PER_BLOCK) + 1; // 특정 그룹의 시작  페이지  
      int end_num = (now_grp * Contents.PAGE_PER_BLOCK);               // 특정 그룹의 마지막 페이지   
       
      StringBuffer str = new StringBuffer(); 
      System.out.println("--> total_page: " + total_page);
     // System.out.println("--> total_grp: " + total_grp);
      System.out.println("--> now_grp: " + now_grp);
      
      str.append("<style type='text/css'>"); 
      str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
      str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
      str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
      str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
      str.append("  .span_box_1{"); 
      str.append("    text-align: center;");    
      str.append("    font-size: 1em;"); 
      str.append("    border: 1px;"); 
      str.append("    border-style: solid;"); 
      str.append("    border-color: #cccccc;"); 
      str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
      str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
      str.append("  }"); 
      str.append("  .span_box_2{"); 
      str.append("    text-align: center;");    
      str.append("    background-color: #668db4;"); 
      str.append("    color: #FFFFFF;"); 
      str.append("    font-size: 1em;"); 
      str.append("    border: 1px;"); 
      str.append("    border-style: solid;"); 
      str.append("    border-color: #cccccc;"); 
      str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
      str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
      str.append("  }"); 
      str.append("</style>"); 
      str.append("<DIV id='paging'>"); 
      //str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 
   
      // 이전 5개 페이지로 이동
      // now_grp: 1 (1 ~ 5 page)
      // now_grp: 2 (6 ~ 10 page)
      // now_grp: 3 (10 ~ 15 page) 
      // 현재 2그룹일 경우: (2 - 1) * 5 = 1그룹의 마지막 페이지 5
      // 현재 3그룹일 경우: (3 - 1) * 5 = 2그룹의 마지막 페이지 10
      int _now_page = (now_grp - 1) * Contents.PAGE_PER_BLOCK;  
      if (now_grp >= 2){ // 현재 그룹번호가 2이상이면 페이지수가 11페이 이상임으로 이전 그룹으로 갈수 있는 링크 생성 
        str.append("<span class='span_box_1'><A href='"+Contents.LIST_FILE+"?word="+word+"&now_page="+_now_page+"&cateno="+cateno+"'>이전</A></span>"); 
      } 
   
      // 중앙의 페이지 목록
      for(int i=start_num; i<=end_num; i++){ 
        if (i > total_page){ // 마지막 페이지를 넘어갔다면 페이 출력 종료
          break; 
        } 
    
        if (now_page == i){ // 목록에 출력하는 페이지가 현재페이지와 같다면 CSS 강조(차별을 둠)
          str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
        }else{
          // 현재 페이지가 아닌 페이지는 이동이 가능하도록 링크를 설정
          str.append("<span class='span_box_1'><A href='"+Contents.LIST_FILE+"?now_page="+i+"&cateno="+cateno+"'>"+i+"</A></span>");   
        } 
      } 
   
      // 5개 다음 페이지로 이동
      // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
      // 현재 페이지 5일경우 -> 현재 1그룹: (1 * 10) + 1 = 2그룹의 시작페이지 11
      // 현재 페이지 15일경우 -> 현재 2그룹: (2 * 10) + 1 = 3그룹의 시작페이지 21
      // 현재 페이지 25일경우 -> 현재 3그룹: (3 * 10) + 1 = 4그룹의 시작페이지 31
      _now_page = (now_grp * Contents.PAGE_PER_BLOCK)+1; //  최대 페이지수 + 1 
      if (now_grp < total_page){ 
        str.append("<span class='span_box_1'><A href='"+Contents.LIST_FILE+"?word="+word+"&now_page="+_now_page+"&cateno="+cateno+"'>다음</A></span>"); 
      } 
      str.append("</DIV>"); 
       
      return str.toString(); 
    }
    
    
    /*
    *
    * @param list_file 목록 파일명 
    * @param themagrpno 테마그룹번호 
    * @param search_count 검색(전체) 레코드수 
    * @param now_page     현재 페이지
    * @param word 검색어
    * @return 페이징 생성 문자열
    */ 
   @Override
   public String pagingB(int search_countGrid, int now_page, String word){ 
     int total_page = (int)(Math.ceil((double)search_countGrid/Contents.RECORD_PER_PAGE)); // 전체 페이지 수 
     //int total_grp = (int)(Math.ceil((double)total_page/Thema.PAGE_PER_BLOCK)); // 전체 그룹  수
     int now_grp = (int)(Math.ceil((double)now_page/Contents.PAGE_PER_BLOCK));  // 현재 그룹 번호
     
     int start_page = ((now_grp - 1) * Contents.PAGE_PER_BLOCK) + 1; // 특정 그룹의 시작  페이지  
     int end_page = (now_grp * Contents.PAGE_PER_BLOCK);               // 특정 그룹의 마지막 페이지   
      
     StringBuffer str = new StringBuffer(); 
     System.out.println("--> total_page: " + total_page);
    // System.out.println("--> total_grp: " + total_grp);
     System.out.println("--> now_grp: " + now_grp);
     
     str.append("<style type='text/css'>"); 
     str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
     str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
     str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
     str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
     str.append("  .span_box_1{"); 
     str.append("    text-align: center;");    
     str.append("    font-size: 1em;"); 
     str.append("    border: 1px;"); 
     str.append("    border-style: solid;"); 
     str.append("    border-color: #cccccc;"); 
     str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
     str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
     str.append("  }"); 
     str.append("  .span_box_2{"); 
     str.append("    text-align: center;");    
     str.append("    background-color: #668db4;"); 
     str.append("    color: #FFFFFF;"); 
     str.append("    font-size: 1em;"); 
     str.append("    border: 1px;"); 
     str.append("    border-style: solid;"); 
     str.append("    border-color: #cccccc;"); 
     str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
     str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
     str.append("  }"); 
     str.append("</style>"); 
     str.append("<DIV id='paging'>"); 
     //str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 
  
     // 이전 5개 페이지로 이동
     // now_grp: 1 (1 ~ 5 page)
     // now_grp: 2 (6 ~ 10 page)
     // now_grp: 3 (10 ~ 15 page) 
     // 현재 2그룹일 경우: (2 - 1) * 5 = 1그룹의 마지막 페이지 5
     // 현재 3그룹일 경우: (3 - 1) * 5 = 2그룹의 마지막 페이지 10
     int _now_page = (now_grp - 1) * Contents.PAGE_PER_BLOCK;  
     if (now_grp >= 2){ // 현재 그룹번호가 2이상이면 페이지수가 11페이 이상임으로 이전 그룹으로 갈수 있는 링크 생성 
       str.append("<span class='span_box_1'><A href=./listAll.do"+"?&word="+word+"&now_page="+_now_page+">이전</A></span>"); 
     } 
  
     // 중앙의 페이지 목록
     for(int i=start_page; i<=end_page; i++){ 
       if (i > total_page){ // 마지막 페이지를 넘어갔다면 페이 출력 종료
         break; 
       } 
   
       if (now_page == i){ // 목록에 출력하는 페이지가 현재페이지와 같다면 CSS 강조(차별을 둠)
         str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
       }else{
         // 현재 페이지가 아닌 페이지는 이동이 가능하도록 링크를 설정
         str.append("<span class='span_box_1'><A href=./listAll.do"+"?&now_page="+i +">"+i+"</A></span>");   
       } 
     } 
  
     // 5개 다음 페이지로 이동
     // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
     // 현재 페이지 5일경우 -> 현재 1그룹: (1 * 10) + 1 = 2그룹의 시작페이지 11
     // 현재 페이지 15일경우 -> 현재 2그룹: (2 * 10) + 1 = 3그룹의 시작페이지 21
     // 현재 페이지 25일경우 -> 현재 3그룹: (3 * 10) + 1 = 4그룹의 시작페이지 31
     _now_page = (now_grp * Contents.PAGE_PER_BLOCK)+1; //  최대 페이지수 + 1 
     if (now_grp < total_page){ 
       str.append("<span class='span_box_1'><A href=./listAll.do"+"?&word="+word+"&now_page="+_now_page+">다음</A></span>"); 
     } 
     str.append("</DIV>"); 
      
     return str.toString(); 
   }
   

}
