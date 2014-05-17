package io.yesez.android_dev_ni_sample;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Jimmy on 05-17-14.
 */
public class UsersApiService {

	String url = "http://jsonplaceholder.typicode.com/users/";
	Gson parser;

	public UsersApiService(){
		parser = new Gson();
	}

	public void getAll(final UsersCallback callback){
		JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
			@Override
			public void onResponse(JSONArray response) {
				ArrayList<User> users = parse(response);
				callback.onSuccess(users);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				error.printStackTrace();
				callback.onError(error);
			}
		});
		HttpManager.getInstance().getQueue().add(request);
	}


	private ArrayList<User> parse(JSONArray jsonArray){
		return parser.fromJson(jsonArray.toString(),
				new TypeToken<ArrayList<User>>(){}.getType());
	}
}
