package com.skuniv.QuickPollSocketServer.dbdao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.skuniv.QuickPollSocketServer.connectdb.ConnectDB;

@SuppressWarnings("unchecked")
@Repository("QuickPollDao")
public class QuickPollDao extends ConnectDB {
	

	public int insertQuickPollQuestion(Map<String, Object> map) {
		return (Integer)insert("quickpoll.insertQuickPollQuestion", map);
	}
}
