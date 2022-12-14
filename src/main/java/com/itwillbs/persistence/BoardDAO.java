package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardDAO {
	
	// 글쓰기 - insertBoard(vo)
	public void insertBoard(BoardVO vo) throws Exception;
	
	//글 전체목록 - listAll()
	public List<BoardVO> listAll() throws Exception;
	
	//글 1개 정보 가져오기 - getBoard(int)
	public BoardVO getBoard(Integer bno) throws Exception;
	
	//글 조회수 1증가
	public void updateReadCount(Integer bno) throws Exception;
	
	// 글 수정하기 
	public Integer updateBoard(BoardVO vo) throws Exception;
	
}
