package com.tweetapp.dao;

public enum DatabaseQuery {

	REGISTER_USER("insert into UserDetails(username, password) values(?,?)"),
	GET_USER("select * from UserDetails where username = ? and password = ?"),
	GET_ALL_USERS("select * from UserDetails"),
	UPDATE_PASSWORD("update UserDetails set password = ? where username = ?");

	private final String databaseQuery;

	private DatabaseQuery(String databaseQuery) {
		this.databaseQuery = databaseQuery;
	}

	public String getQuery() {
		return this.databaseQuery;
	}

}
