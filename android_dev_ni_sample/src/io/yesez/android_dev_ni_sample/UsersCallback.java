package io.yesez.android_dev_ni_sample;

import java.util.ArrayList;

public interface UsersCallback {
	void onSuccess(ArrayList<User> list);
	void onError(Throwable exception);
}
