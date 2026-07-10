package com.zeus.domin;

import lombok.ToString;

@ToString(exclude = "userName")
public class Member {
	private String userId;
	private String userName;
	private String password;
	private String birthDay;
}
