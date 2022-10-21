package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardService {

	//글쓰기
	public void BoardWrite(BoardVO vo) throws Exception;
	
	//글 전체 목록 
	public List<BoardVO> getBoardListAll() throws Exception;
	
	//글 1개 정보 불러오기
	public BoardVO getBoard(Integer bno) throws Exception;
	
	//글 조회수 1증가
	public void updateReadCount(Integer bno) throws Exception;
	
	//글 수정하기
	public Integer updateBoard(BoardVO vo) throws Exception;
}
