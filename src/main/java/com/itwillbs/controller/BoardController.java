package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public void listAllGET(@ModelAttribute("msg") String msg, Model model, HttpSession session) throws Exception {
		log.info("listAllGET() 호출");
		
		// 1) 글쓰기 -> 리스트페이지, 2) 리스트페이지 호출 
		log.info("msg : " +msg);
		///연결된 view페이지로 정보 전달
		model.addAttribute("msg",msg);
		
		//서비스 - 글전체 목록을 가져오는 메서드  
		List<BoardVO> boardList = service.getBoardListAll();
		
		model.addAttribute("boardList",boardList);
		
		// 세션 객체 - isUpdate 정보전달 
		session.setAttribute("isUpdate", false);
		
		//jsp뷰로 전달
		
		log.info("/board/listAll -> /board/listAll.jsp 로 이동");
	}
	
	//http://localhost:8088/board/read?bno=12
	
	// 글 본문 보기 - GET (조회)
	@RequestMapping(value= "/read", method = RequestMethod.GET)
	public void readGET(HttpSession session,@RequestParam("bno") int bno, Model model /* @ModelAttribute("bno") int bno */) throws Exception {
		log.info("readGET() 호출 ");
		//전달정보(bno)
		log.info("bno: "+ bno);
		
		//boolean isUpdate = false;
		log.info(" isUpdate :" + session.getAttribute("isUpdate"));
		
		boolean isUpdate = (boolean)session.getAttribute("isUpdate");
		
		
			//if(isUpdate == false) {
			if(!isUpdate) {
			// 서비스 - updateReadCount(BNO) ==> 정보가 필요함 
			service.updateReadCount(bno);
			log.info("조회수 1증가");
			session.setAttribute("isUpdate", true);
			//조회수 1번 업데이트 되고, 다시 실행되었을때는 조회수 업데이트 안됨! 
		}
		
		
		
		// 서비스 - getBoard(int) bno에 있는 데이터 디비에서 꺼내오기 
		BoardVO vo = service.getBoard(bno);
		log.info(vo+"");
		model.addAttribute("vo", vo);
		
		log.info("/board/read -> /board/read.jsp");
	}
	
	// 글 수정하기 -  GET(기존의 정보 조회 출력 + 수정할 정보 입력)
	// @RequestMapping("/board/*") 라고 적어놨기 때문에 /board/modify 안해도됨 
	@RequestMapping(value = "/modify", method=RequestMethod.GET)
	public void modifyGET(/* @RequestParam("bno") int bno */int bno, Model model) throws Exception{
		
		//전달정보 저장 (bno)
		log.info("@@@@@@@@"+bno);
		
		//서비스 - 게시판글 정보를 가져오는 메서드
		// 	service.getBoard(bno);
		
		//연결된 뷰 정보 전달(Model객체)  
		model.addAttribute("vo",service.getBoard(bno));
		
		//페이지 이동(출력)   /board/modify 
		
	}
	
	// 글 수정하기 - POST(수정할데이터 처리)
	@RequestMapping(value ="/modify" , method=RequestMethod.POST )
	public String modifyPOST(BoardVO vo) throws Exception {
		log.info("modifyPOST() 호출 ");
		// 한글처리 => form태그를 post방식으로 보내 한글처리가 필요함 ㅡ 그런데 이미 web.xml에서 인코딩했기 때문에 필요 없음! 생략! 
		// 전달정보 저장(수정할 정보) VO
		log.info("@@수정할 정보@@"+vo);
		
		// 서비스 - 글 수정 메서드, 뷰페이지로부터 받아온 vo가 service로 이동💚
		int cnt = service.updateBoard(vo);
		
		// 수정성공시 /listAll 페이지 이동
		if(cnt == 1) {
			return "redirect:/board/listAll";
		} else { 
			//수정 실패
			//예외처리
			new NullPointerException();
			return "/board/modify?bno="+vo.getBno();
		}
		
		
	}
	
}
