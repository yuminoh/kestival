package dev.mvc.contents;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import dev.mvc.kestival.paging.Criteria;
import dev.mvc.qna.QnaVO;

public interface ContentsDAOInter {
    public int insert(ContentsVO contentsVO);
    
    //public int insertList(Map<String, String> listInfo);
    
    public int delete(int contentsno);
    
    public int update(ContentsVO contentsVO);
    
    public ContentsVO read(int contentsno);
    
    public List<Map<String, Object>> listsps(int cateno, Criteria criteria);
    
    public List<ContentsVO> listGrid(HashMap<String, Object> map);
    
    public int count(Criteria criteria, int cateno);
    
    public int update_recom(int contentsno);
    
    public List<ContentsVO> list(HashMap<String, Object> map);
    
    public int search_count(HashMap<String, Object> hashMap);
    public int search_countGrid(HashMap<String, Object> hashMap);
}
