package io.yesez.android_dev_ni_sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;

public class MainActivity extends Activity {

	String url = "http://jsonplaceholder.typicode.com/users/";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
	}

	TextView tvResult;
	private void init(){
		HttpManager.getInstance().init(getApplicationContext());
		tvResult = (TextView)findViewById(R.id.tv_result);

		findViewById(R.id.btn_load).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				callService();
			}
		});
	}

	private void callService(){
		setMessage("CARGANDO");
		JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
			@Override
			public void onResponse(JSONArray response) {
				setMessage(response.toString());
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				setMessage("Algo salió mal :( \n" + error.getMessage());
			}
		});
		HttpManager.getInstance().getQueue().add(request);
	}

	private void setMessage(String msg){
		tvResult.setText(msg);
	}
}
