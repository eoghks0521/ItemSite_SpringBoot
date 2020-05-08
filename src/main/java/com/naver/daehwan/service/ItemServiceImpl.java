package com.naver.daehwan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.naver.daehwan.model.Item;
import com.naver.daehwan.repo.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemRepository repository;

	@Override
	public void register(Item item) throws Exception {
		repository.save(item);
	}

	@Override
	public Item read(Long itemId) throws Exception {
		// TODO Auto-generated method stub
		return repository.getOne(itemId);
	}

	@Override
	public void modify(Item item) throws Exception {
		Item itemEntity = repository.getOne(item.getItemId());
		itemEntity.setItemName(item.getItemName());
		itemEntity.setPrice(item.getPrice());
		itemEntity.setDescription(item.getDescription());
		itemEntity.setPictureUrl(item.getPictureUrl());
		itemEntity.setPreviewUrl(item.getPreviewUrl());

		repository.save(itemEntity);
	}

	@Override
	public void remove(Long itemId) throws Exception {
		repository.deleteById(itemId);
	}

	@Override
	public List<Item> list() throws Exception {
		return repository.findAll(Sort.by(Direction.DESC, "itemId"));
	}

	@Override
	public String getPreview(Long itemId) throws Exception {
		Item item = repository.getOne(itemId);
		return item.getPreviewUrl();
	}

}
