package dev.mvc.qna;

import java.util.List;
import java.util.Map;

import dev.mvc.kestival.paging.Criteria;
import dev.mvc.reply.ReplyVO;

public interface QnaProcInter {
    public int insert(QnaVO qnaVO);
    
    public int delete(int qnano);
    
    public int update(QnaVO qnaVO);
    
    public QnaVO update_read(int qnano);
    
    public QnaVO delete_read(int qnano);
    
    public QnaVO read(int qnano);
    
    public List<QnaVO> selectList();
    
    public List<Map<String, Object>> listsps(Criteria criteria);
    
    public int count(Criteria criteria);
    
    public List<ReplyVO> reselectList(int qnano);
    
    public int reinsert(ReplyVO replyVO);
    
    public int reupdate(ReplyVO replyVO);
    
    public int replydelete(int replyno);
    
    //public ReplyVO read(int replyno);
}
