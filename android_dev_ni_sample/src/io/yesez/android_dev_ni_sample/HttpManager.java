package io.yesez.android_dev_ni_sample;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Jimmy on 05-16-14.
 */
public class HttpManager {

	private static HttpManager self = null;
	private static RequestQueue mRequestQueue;

	public static HttpManager getInstance() {
		if(self == null)
			self = new HttpManager();
		return self;
	}

	public void init(Context ctx){
		mRequestQueue = Volley.newRequestQueue(ctx);
	}

	public RequestQueue getQueue(){
		return mRequestQueue;
	}
}
