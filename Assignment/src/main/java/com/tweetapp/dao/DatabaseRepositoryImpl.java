package com.tweetapp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tweetapp.model.UserDetail;

public class DatabaseRepositoryImpl implements DatabaseRepository {

	private String USER_REGISTRATION_SUCCESS = "User is registered successfully !!";
	private String USER_REGISTRATION_FAILURE = "User Registration failed";
	private String PASSWORD_UPDATE = "Password Updated";
	private String PASSWORD_UPDATE_FAILURE = "Password updation failed";

	private JdbcTemplate jdbcTemplate;

	public DatabaseRepositoryImpl() {
		try {
			jdbcTemplate = DatabaseConnection.getJdbcTemplate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String registerUser(String username, String password) {
		int update = jdbcTemplate.update(DatabaseQuery.REGISTER_USER.getQuery(), username, password);
		if (update > 0)
			return USER_REGISTRATION_SUCCESS;
		else
			return USER_REGISTRATION_FAILURE;

	}

	@SuppressWarnings("unchecked")
	public UserDetail login(String username, String password) {
		UserDetail user;
		try {
			user = (UserDetail) jdbcTemplate.queryForObject(DatabaseQuery.GET_USER.getQuery(),
					new Object[] { username, password }, new BeanPropertyRowMapper(UserDetail.class));
		} catch (Exception e) {
			user = null;
		}

		return user;
	}

	public List<UserDetail> getAllUsers() {
		List<UserDetail> userList = new ArrayList<UserDetail>();
		List<Map<String, Object>> userMapList = jdbcTemplate.queryForList(DatabaseQuery.GET_ALL_USERS.getQuery());

		for (Map<String, Object> mapList : userMapList) {
			String username = (String) mapList.get("username");
			String password = (String) mapList.get("password");
			UserDetail userDetail = new UserDetail(username, password);
			userList.add(userDetail);

		}

		return userList;
	}

	public String changePassword(String username, String newPassword) {
		int update = jdbcTemplate.update(DatabaseQuery.UPDATE_PASSWORD.getQuery(), newPassword, username);
		if (update > 0) {
			return PASSWORD_UPDATE;
		} else
			return PASSWORD_UPDATE_FAILURE;
	}

}
