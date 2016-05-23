package com.elenaganel.homepayments;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CounterType extends Activity {
	ListView listOfCounterType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counter_type);
		
		listOfCounterType = (ListView)findViewById(R.id.listView);
		
		/*Передаем названия счетчиков из string.xml*/
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
		        this, R.array.names, android.R.layout.simple_list_item_1);
		listOfCounterType.setAdapter(adapter);
		
		/*Ждем нажатия на пунт меню*/    
		listOfCounterType.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				
				/*Передаем в окно создания счетчика выбранный тип*/
				intent.putExtra("name", position);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.counter_type, menu);
		return true;
	}

}
