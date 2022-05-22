package dev.mvc.cate;

import java.util.List;
import java.util.Map;

import dev.mvc.kestival.paging.Criteria;
import dev.mvc.qna.QnaVO;

public interface CateDAOInter {
    public int insert(CateVO cateVO);
    
    public int delete(int cateno);
    
    public int update(CateVO cateVO);
    
    public List<Map<String, Object>> listsps(Criteria criteria);
    
    public int count(Criteria criteria);
    
    public CateVO read(int cateno);
}
