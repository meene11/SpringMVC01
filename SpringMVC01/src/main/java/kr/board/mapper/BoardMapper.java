package kr.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import kr.board.entity.Board;

@Mapper // Mybatis API 
public interface BoardMapper { // 구현체는 sqlSessionFactory가 구현 및 실행 한다(스프링내부)  
	public List<Board> getLists(); // 전체리스트 조회
	public void boardInsert(Board vo);
	public Board boardContent(int idx);
	public void boardDelete(int idx);
	public void boardUpdate(Board vo);
	
	// sql문을 xml 파일이 아닌 마이바티스에서 제공하는 어노테이션으로 기재가능. 단, xml에 동일id 중복해서 기재하면 안된다.
	@Update("update myboard set count=count+1 where idx=#{idx}")
	public void boardCount(int idx);
}
