package com.naver.daehwan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.naver.daehwan.model.Notice;
import com.naver.daehwan.repo.NoticeRepository;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeRepository repository;

	@Override
	public void register(Notice notice) throws Exception {
		repository.save(notice);

	}

	@Override
	public Notice read(Long noticeNo) throws Exception {
		return repository.getOne(noticeNo);
	}

	@Override
	public void modify(Notice notice) throws Exception {
		Notice noticeEntity = repository.getOne(notice.getNoticeNo());
		noticeEntity.setTitle(notice.getTitle());
		repository.save(noticeEntity);

	}

	@Override
	public void remove(Long noticeNo) throws Exception {
		repository.deleteById(noticeNo);
	}

	@Override
	public List<Notice> list() throws Exception {
		return repository.findAll(Sort.by(Direction.DESC, "noticeNo"));
	}

}
