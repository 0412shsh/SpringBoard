package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger log 
				= LoggerFactory.getLogger(BoardController.class);
	
	
	// 서비스 객체 필요(의존적) 
	@Inject
	private BoardService service;
	
	
	
	// http://localhost:8088/controller/test (x) 
	// http://localhost:8088/controller/board/test (x) 
	// http://localhost:8088/board/test (o 서버 모듈 설정 후)  
	
	
//	@RequestMapping("/test")
//	public void test() {
//		log.info("test() 호출");
//	}
	
	// 글쓰기 - GET
	@RequestMapping(value="/regist", method =RequestMethod.GET )
	public void registerGET() throws Exception {
		log.info("registerGET() 호출");
		log.info(" /board/regist (get) -> /board/regist.jsp 로 연결");
		// servlet-context에 앞뒤 prefix 등등 붙음
		
	} 
	
	// 글쓰기 - POST 
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String registerPOST(BoardVO vo, RedirectAttributes rttr /*Model model*/ ) throws Exception {
		log.info("registerPOST() 호출");
		// 한글처리(필터 - 생략 web.xml)
		// 전달된 정보 저장 
		log.info(""+vo);
		
		// 서비스 - 글쓰기 동작 
		service.BoardWrite(vo);
		
		log.info("글쓰기 완료");
		
		//model.addAttribute("msg","OK");
		
		//RedirectAttributes 객체 => redirect 페이지 이동시에만 사용 가능
		rttr.addFlashAttribute("msg", "OK");
		// -> 1회성 데이터 (체크용) , URL에 표시 X
		
		// 페이지 이동(리스트) 화면, 주소 모두 변경 
		//return "/board/success";
		//return "redirect:/board/listAll?msg=OK";
		return "redirect:/board/listAll";
		
	}
	//http://localhost:8088/board/listAll 
	//주소 입력될때마다 메서드 호출 
	// 게시판 리스트 - 주소가 바뀜, 주소를 쳐서 엔터를 친다(get)
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAllGET(@ModelAttribute("msg") String msg, Model model) throws Exception {
		log.info("listAllGET() 호출");
		
		// 1) 글쓰기 -> 리스트페이지, 2) 리스트페이지 호출 
		log.info("msg : " +msg);
		///연결된 view페이지로 정보 전달
		model.addAttribute("msg",msg);
		
		//서비스 - 글전체 목록을 가져오는 메서드  
		List<BoardVO> boardList = service.getBoardListAll();
		
		model.addAttribute("boardList",boardList);
		//jsp뷰로 전달
		
		
		
		log.info("/board/listAll -> /board/listAll.jsp 로 이동");
	}
	
}
