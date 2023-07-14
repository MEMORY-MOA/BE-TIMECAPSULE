package com.moa.timecapsule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moa.timecapsule.entity.Item;
import com.moa.timecapsule.entity.ItemType;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	Item findItemByItemIdAndItemType(int ItemId, ItemType itemType);

	Boolean existsItemByItemId(int itemId);
}
