package io.yesez.android_dev_ni_sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

public class MainActivity extends Activity {

	ArrayList<User> modelList;
	ArrayAdapter<User> adapter;
	UsersApiService api;

	Button btnLoad;
	ListView lvData;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		BusProvider.getInstance().unregister(this);
	}

	private void init(){
		BusProvider.getInstance().register(this);
		HttpManager.getInstance().init(getApplicationContext());
		api = new UsersApiService();
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
		api.getAll();
	}

	private void process(ArrayList<User> list){
		modelList.clear();
		modelList.addAll(list);
		adapter.notifyDataSetChanged();
		setMessage(getString(R.string.load));
	}

	private void setMessage(String msg){
		btnLoad.setText(msg);
	}

	@Subscribe
	public void onUsersRetrieved(OnUsersRetrievedEvent event){
		if(event.Error == null){
			process(event.Users);
		}else{
			event.Error.printStackTrace();
			setMessage("Algo salió mal :(");
		}
	}
}
