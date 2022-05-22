package dev.mvc.qna;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.kestival.paging.Criteria;
import dev.mvc.member.MemberDAOInter;
import dev.mvc.reply.ReplyVO;

@Component("dev.mvc.qna.QnaProc")
public class QnaProc implements QnaProcInter {
    @Autowired
    private QnaDAOInter qnaDAO;
    
    public QnaProc(){
      System.out.println("-> QnaProc created.");
    }
    
    @Override
    public int insert(QnaVO qnaVO) {
        int cnt = this.qnaDAO.insert(qnaVO);
        return cnt;
    }
    
    @Override
    public QnaVO delete_read(int qnano) {
        QnaVO qnaVO = this.qnaDAO.read(qnano);
        return qnaVO;
    }
    
    @Override
    public int delete(int qnano) {
        //return postDAO.delete(postno);
        int cnt = this.qnaDAO.delete(qnano);
        return cnt;
    }
    
    @Override
    public int update(QnaVO qnaVO) {
        int cnt = this.qnaDAO.update(qnaVO);
        
        return cnt;
    }
    
    @Override
    public QnaVO update_read(int qnano) {
        QnaVO qnaVO = this.qnaDAO.read(qnano);
        
        return qnaVO;
    }
    
    @Override
    public List<QnaVO> selectList() {
        List<QnaVO> list;
        list = this.qnaDAO.selectList();
        return list;
    }
    
    @Override
    public List<Map<String, Object>> listsps(Criteria criteria) {
        return qnaDAO.listsps(criteria);
    }
    
    @Override
    public QnaVO read(int qnano) {
        QnaVO qnaVO = this.qnaDAO.read(qnano);
        
        String title = qnaVO.getQtitle();
        String content = qnaVO.getQcontext();
        return qnaDAO.read(qnano);
    }
    
    @Override
    public int count(Criteria criteria) {
        return qnaDAO.count(criteria);
    }
    
    @Override
    public List<ReplyVO> reselectList(int qnano){
        List<ReplyVO> relist;
        relist = qnaDAO.reselectList(qnano);
        return relist;
    }
    
    @Override
    public int reinsert(ReplyVO replyVO) {
        return qnaDAO.reinsert(replyVO);
    }
    
    @Override
    public int reupdate(ReplyVO replyVO) {
        return qnaDAO.reupdate(replyVO);
    }
    
    @Override
    public int replydelete(int replyno) {
        return qnaDAO.replydelete(replyno);
    }
    /*
    @Override
    public ReplyVO read(int replyno) {
        ReplyVO replyVO = this.postDAO.read(replyno);
        
        return replyVO;
    } */
}
