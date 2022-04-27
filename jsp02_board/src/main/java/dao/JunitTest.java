package dao;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import dto.Board;

class JunitTest {
	BoardDAO bdao = new BoardDAO();
	
	@Test
	void testInsert() {
		Board board = new Board();
		board.setWriter("홍길동");
		board.setSubject("제목테스트");
		board.setContent("내용테스트");
		
		int cnt = bdao.insert(board);
		System.out.println(cnt +"건 추가");
	}

	@Test
	void testUpdate() {
		Board board = new Board();
		board.setSeq(2);
		board.setWriter("홍길동");
		board.setSubject("제목테스트");
		board.setContent("내용테스트");
		
		int cnt = bdao.update(board);
		System.out.println(cnt +"건 수정");
	}

	@Test
	void testDelete() {
		int cnt = bdao.delete(3);
		System.out.println(cnt +"건 삭제");
		
	}

	@Test
	void testSelectList() {
		List<Board> blist = bdao.selectList("테스트", null);
		System.out.println(blist);
	}

	@Test
	void testSelectOne() {
		Board board = bdao.selectOne(5);
		System.out.println(board);
	}

}
