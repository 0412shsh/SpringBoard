package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardDAO {
	
	// 글쓰기 - insertBoard(vo)
	public void insertBoard(BoardVO vo) throws Exception;
	
	//글 전체목록 - listAll()
	public List<BoardVO> listAll() throws Exception;
	
}
