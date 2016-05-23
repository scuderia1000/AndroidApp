package com.elenaganel.homepayments;

import com.elenaganel.homepayments.DB;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	private static final int CM_DELETE_ID = 2;
	private static final int CM_EDIT_ID = 1;
	ListView lvCounters;
	DB db;
	SimpleCursorAdapter scAdapter;
	Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//��������� ����������� � ��
		db = new DB(this);
		db.open();
		
		//�������� ������
		cursor = db.getAllData();
		startManagingCursor(cursor);
		
		// ��������� ������� �������������
	    String[] from = new String[] { DB.COLUMN_IMG, DB.COLUMN_COUNTER_NAME };
	    int[] to = new int[] { R.id.ivImg, R.id.tvText };
		
		// �������� ������� � ����������� ������
	    scAdapter = new SimpleCursorAdapter(this, R.layout.counters, cursor, from, to);
	    lvCounters = (ListView) findViewById(R.id.lvCounters);
	    lvCounters.setAdapter(scAdapter);
		
	    //��������� ������� �� ������ ������
	    lvCounters.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				// TODO Auto-generated method stub

				//����� ���� � ������� ���������� ��������
				Intent intent = new Intent(MainActivity.this, DataCountersActivity.class);
				intent.putExtra("counter_id", parent.getItemIdAtPosition(position));
				Toast.makeText(
						getApplicationContext(),
						"�� ������� "
								+ parent.getItemIdAtPosition(position),
						Toast.LENGTH_SHORT).show();
				startActivity(intent);
				
			}
		});
	    //��������� ����������� ���� � ������
	    registerForContextMenu(lvCounters);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/*���� ������� ������ � �������� ���� �������� ��������*/
	public void btnAddCounter_Click(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, CreateCounterActivity.class);
	    startActivity(intent);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, CM_EDIT_ID, 0, R.string.edit);
		menu.add(0, CM_DELETE_ID, 0, R.string.delete_record);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case CM_EDIT_ID:
			AdapterContextMenuInfo acmi0 = (AdapterContextMenuInfo) item.getMenuInfo();
			Intent intent = new Intent(this, CreateCounterActivity.class);
			intent.putExtra("counter_id", acmi0.id);
			startActivity(intent);
			break;

		case CM_DELETE_ID:
			//�������� �� ������ ������������ ���� ������ �� ������ ������
			AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item.getMenuInfo();
			//��������� id ������ � ������� ��������������� ������ � ��
			db.delRec(acmi.id);
			//��������� ������
			cursor.requery();
			break;
		}
		return super.onContextItemSelected(item);
	}
	
	protected void onDestroy() {
		super.onDestroy();
		//��������� ����������� ��� ������
		db.close();
	}
}
