package com.itwillbs.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	private static final Logger log 
	    = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	// SqlSession객체 주입(DI)
	@Autowired
	private SqlSession sqlSession;

	private static final String NAMESPACE 
	                = "com.itwillbs.mapper.BoardMapper";
	
	@Override
	public void insertBoard(BoardVO vo) throws Exception {
		log.info(" insertBoard(vo) 호출 ");
		//log.info(vo+"");
		
		// SQL실행객체 - SqlSession객체(디비연결,Mybatis,매퍼,자원해제)
		// 글쓰기(insert)
		int result = sqlSession.insert(NAMESPACE+".create", vo);
		
		if(result >0)
			log.info(" 글쓰기 완료! ");
		
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		log.info("listAll() 호출 ");
		
		//DB - 모든 정보 가져오기(SQL/mapper 호출) 
		List<BoardVO> boardList = sqlSession.selectList(NAMESPACE+".listAll");
		
		//log.info(boardList+"");
		log.info(boardList.size()+"");
		
		
		return boardList;
	}
	
	

}
