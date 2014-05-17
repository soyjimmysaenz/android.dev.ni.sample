package io.yesez.android_dev_ni_sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends Activity {

	ArrayList<User> modelList;
	ArrayAdapter<User> adapter;
	UsersApiService api;
	UsersCallback callback;

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

		callback = new UsersCallback() {
			@Override
			public void onSuccess(ArrayList<User> list) {
				process(list);
			}

			@Override
			public void onError(Throwable exception) {
				exception.printStackTrace();
				setMessage("algo salió mal :(");
			}
		};
	}

	private void callService(){
		setMessage(getString(R.string.loading));
		api.getAll(this.callback);
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
}
