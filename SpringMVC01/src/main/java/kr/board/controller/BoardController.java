package kr.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller // anotation 으로 역할 규정
public class BoardController {
	
	@Autowired
	private BoardMapper mapper;
	
	// 핸들러맴핑 HandlerMapping
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		List<Board> list = mapper.getLists();
		model.addAttribute("list", list);
		return "boardList"; // 경로: viewResolver /WEB-INF/views/boardList.jsp ->forward 
	}
	
	@GetMapping("/boardForm.do")
	public String boardForm() {
		return "boardForm"; // /WEB-INF/views/boardForm.jsp ->forward
	}
	
	@PostMapping("/boardInsert.do")
	public String boardInsert(Board vo) { // title, conten, write => 파라미터수집(Board) :스프링 내성 단, 조건은 동일이름
		mapper.boardInsert(vo);
		return "redirect:/boardList.do"; // redirect
	}
	@GetMapping("/boardContent.do")
//	public String boardContent(@RequestParam("idx") int idx, Model model) { //?idx=6  ?idx=${vo.idx} 
//                            @RequestParam-받는변수명과 보낼변수명동일하면 ex)idx 이름 동일하면 생략가능
	public String boardContent(int idx, Model model) {	
		Board vo = mapper.boardContent(idx);
		// 조회수 증가
		mapper.boardCount(idx);
		model.addAttribute("vo", vo);
		return "boardContent"; // boardContent.jsp
	}
	
	@GetMapping("/boardDelete.do/{idx}")
	public String boardDelete(@PathVariable("idx") int idx) { 
		mapper.boardDelete(idx); // delete
		return "redirect:/boardList.do";
	}
	
	@GetMapping("/boardUpdateForm.do/{idx}")
	public String boardUpdateForm(@PathVariable("idx") int idx, Model model) {
		Board vo = mapper.boardContent(idx);
		model.addAttribute("vo", vo);
		return "boardUpdate"; // boardUpdate.jsp
	}
	
	@PostMapping("/boardUpdate.do")
	public String boardUpdate(Board vo) { // idx, title, content
		mapper.boardUpdate(vo); // 수정
		return "redirect:/boardList.do"; // redirect
	}
}
