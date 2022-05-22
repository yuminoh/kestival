package dev.mvc.cate;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.kestival.paging.Criteria;
import dev.mvc.qna.QnaDAOInter;
import dev.mvc.qna.QnaVO;

@Component("dev.mvc.cate.CateProc")
public class CateProc implements CateProcInter{
    @Autowired
    private CateDAOInter cateDAO;
    
    public CateProc(){
      System.out.println("-> CateProc created.");
    }
    
    @Override
    public int insert(CateVO cateVO) {
        int cnt = this.cateDAO.insert(cateVO);
        return cnt;
    }
    
    @Override
    public CateVO delete_read(int cateno) {
        CateVO cateVO = this.cateDAO.read(cateno);
        return cateVO;
    }
    
    @Override
    public int delete(int cateno) {
        int cnt = this.cateDAO.delete(cateno);
        return cnt;
    }
    
    @Override
    public int update(CateVO cateVO) {
        int cnt = this.cateDAO.update(cateVO);
        
        return cnt;
    }
    
    @Override
    public CateVO update_read(int cateno) {
        CateVO cateVO = this.cateDAO.read(cateno);
        
        return cateVO;
    }
    
    @Override
    public int count(Criteria criteria) {
        return cateDAO.count(criteria);
    }
    
    @Override
    public List<Map<String, Object>> listsps(Criteria criteria) {
        return cateDAO.listsps(criteria);
    }
    
    @Override
    public CateVO read(int cateno) {
        CateVO cateVO = this.cateDAO.read(cateno);
        
        String name = cateVO.getName();
        return cateDAO.read(cateno);
    }
}
