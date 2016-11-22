package com.company.my.dao;

import com.company.my.bom.Player;

public interface PlayerDao {

	public void save(Player player);
	public Player findById(Long id);
	public Player findByName(String name);
	
}
