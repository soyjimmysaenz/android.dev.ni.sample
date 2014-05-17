package io.yesez.android_dev_ni_sample;

import java.util.ArrayList;

/**
 * Created by Jimmy on 05-17-14.
 */
public class OnUsersRetrievedEvent {
	public ArrayList<User> Users;
	public Throwable Error;

	public OnUsersRetrievedEvent(ArrayList<User> users, Throwable error){
		this.Users = users;
		this.Error = error;
	}
}
