package io.yesez.android_dev_ni_sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

	String url = "http://jsonplaceholder.typicode.com/users/";
	ArrayList<User> modelList;
	ArrayAdapter<User> adapter;

	Button btnLoad;
	ListView lvData;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
	}

	private void init(){
		HttpManager.getInstance().init(getApplicationContext());
		modelList = new ArrayList<User>();
		adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, modelList);

		btnLoad = (Button)findViewById(R.id.btn_load);
		lvData = (ListView)findViewById(R.id.lv_data);

		lvData.setAdapter(adapter);

		btnLoad.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				callService();
			}
		});
	}

	private void callService(){
		setMessage(getString(R.string.loading));
		JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
			@Override
			public void onResponse(JSONArray response) {
				process(response);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				error.printStackTrace();
				setMessage("Algo salió mal :(");
			}
		});
		HttpManager.getInstance().getQueue().add(request);
	}

	private void process(JSONArray response){
		modelList.clear();
		modelList.addAll(parse(response));
		adapter.notifyDataSetChanged();
		setMessage(getString(R.string.load));
	}

	private ArrayList<User> parse(JSONArray jsonArray){
		ArrayList<User> list = new ArrayList<User>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonItem;
			try {
				jsonItem = jsonArray.getJSONObject(i);
				User model = new User(
						jsonItem.getInt(User.ID_FIELD),
						jsonItem.getString(User.NAME_FIELD),
						jsonItem.getString(User.EMAIL_FIELD),
						jsonItem.getString(User.PHONE_FIELD),
						jsonItem.getString(User.WEBSITE_FIELD),
						null
				);
				list.add(model);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	private void setMessage(String msg){
		btnLoad.setText(msg);
	}
}
