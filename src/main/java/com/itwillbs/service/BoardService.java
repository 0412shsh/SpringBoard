package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardService {

	//글쓰기
	public void BoardWrite(BoardVO vo) throws Exception;
	
	//글 전체 목록 
	public List<BoardVO> getBoardListAll() throws Exception;
	
}
