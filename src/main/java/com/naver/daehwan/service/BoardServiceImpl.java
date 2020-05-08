package com.naver.daehwan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.naver.daehwan.model.Board;
import com.naver.daehwan.repo.BoardRepository;
import com.naver.daehwan.vo.PageRequestVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository repository;

	@Override
	public void register(Board board) throws Exception {
		repository.save(board);

	}

	//	/*
	//	 * @Override public List<Board> list() throws Exception { return
	//	 * repository.findAll(Sort.by(Direction.DESC,"boardNo")); }
	//	 */

	@Override
	public Board read(Long boardNo) throws Exception {
		return repository.getOne(boardNo);
	}

	@Override
	public void modify(Board board) throws Exception {
		Board boardEntity = repository.getOne(board.getBoardNo());
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());

		repository.save(boardEntity);
	}

	@Override
	public void remove(Long boardNo) throws Exception {
		repository.deleteById(boardNo);
	}

	@Override
	public Page<Board> list(PageRequestVO pageRequestVO) throws Exception {
		int pageNumber = pageRequestVO.getPage() - 1;
		int sizePerPage = pageRequestVO.getSizePerPage();
		Pageable pageRequest = PageRequest.of(pageNumber, sizePerPage, Sort.Direction.DESC, "boardNo");
		Page<Board> page = repository.findAll(pageRequest);
		return page;

	}
}
