package com.elenaganel.homepayments;

import java.util.ArrayList;

import com.elenaganel.homepayments.DB;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.text.Html;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

public class DataCountersActivity extends Activity {
	ListView lvView;
	DB db;
	Cursor cursor, c, c_1;
	long counterID;
	Intent intent;
	//ArrayList<Double> data = new ArrayList<Double>();
	SimpleCursorAdapter scAdapter;
	final String LOG_TAG = "myLogs";
	private static final int CM_SEND_ID = 1;
	private static final int CM_DELETE_ID = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_counters);
		
		lvView = (ListView) findViewById(R.id.lvView);
		//добавляем контекстное меню к списку
		registerForContextMenu(lvView);
		//Обработка нажатия на пункте списка
	    lvView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//Открываем подключение к БД
		db = new DB(this);
		db.open();
		
		//Получаем Intent и извлекаем из него объект с именем counter_id
		intent = getIntent();
		counterID = intent.getLongExtra("counter_id", 0);
		
		//получаем курсор
		cursor = db.getSortingDataInTableWhitoutNull(String.valueOf(counterID));
		myAdapter(cursor);
		startManagingCursor(cursor);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.data_counters, menu);
		return true;
	}
	
	//Адаптер для показаний; в зав-ти от кол-ва тарифов создаем адаптер из разных лайоутов
	public void myAdapter(Cursor cursor) {
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				Double numberOfTarif = Double.valueOf(cursor.getString
						(cursor.getColumnIndex("tarif_number")));
				String[] from;
		  		int[] to;
		  		switch (numberOfTarif.intValue()) {
		  		case 0:
		  			break;		  		
		  		case 1:
		  			// формируем столбцы сопоставления
		  		    from = new String[] { DB.DATA, DB.NAME_1, DB.COLUMN_STATEMENT_1,
		  		    		DB.COLUMN_RASHOD_1,	DB.COLUMN_TARIF_STATEMENT_1,
		  		    		DB.COLUMN_SUMMA_1, DB.COLUMN_ITOGO };
		  		    to = new int[] { R.id.tvData, R.id.tvData1, R.id.tvData2, R.id.tvData3,
		  		    		R.id.tvData4, R.id.tvData5, R.id.tvItogo};
		  			
		  			// создааем адаптер и настраиваем список
		  		    scAdapter = new SimpleCursorAdapter(this, R.layout.table_1, cursor, from, to);
		  		    //lvView = (ListView) findViewById(R.id.listView1);
		  		    lvView.setAdapter(scAdapter);
		  			break;
		  		case 2:
		  			// формируем столбцы сопоставления
		  		    from = new String[] { DB.DATA, DB.NAME_1, DB.COLUMN_STATEMENT_1,
		  		    		DB.COLUMN_RASHOD_1,	DB.COLUMN_TARIF_STATEMENT_1, DB.COLUMN_SUMMA_1,
		  		    		DB.NAME_2,
		  		    		DB.COLUMN_STATEMENT_2, DB.COLUMN_RASHOD_2, DB.COLUMN_TARIF_STATEMENT_2,
		  		    		DB.COLUMN_SUMMA_2, DB.COLUMN_ITOGO };
		  		    to = new int[] { R.id.tvData, R.id.tvData1, R.id.tvData2, R.id.tvData3,
		  		    		R.id.tvData4, R.id.tvData5,
		  		    		R.id.tvData6, R.id.tvData7, R.id.tvData8, R.id.tvData9,
		  		    		R.id.tvData10, R.id.tvItogo};
		  			// создааем адаптер и настраиваем список
		  		    scAdapter = new SimpleCursorAdapter(this, R.layout.table_2, cursor, from, to);
		  		    //lvView = (ListView) findViewById(R.id.listView1);
		  		    lvView.setAdapter(scAdapter);
		  			break;
		  		case 3:
		  			// формируем столбцы сопоставления
		  		    from = new String[] { DB.DATA, DB.NAME_1, DB.COLUMN_STATEMENT_1,
		  		    		DB.COLUMN_RASHOD_1,	DB.COLUMN_TARIF_STATEMENT_1, DB.COLUMN_SUMMA_1,
		  		    		DB.NAME_2,
		  		    		DB.COLUMN_STATEMENT_2, DB.COLUMN_RASHOD_2, DB.COLUMN_TARIF_STATEMENT_2,
		  		    		DB.COLUMN_SUMMA_2, DB.NAME_3,
		  		    		DB.COLUMN_STATEMENT_3, DB.COLUMN_RASHOD_3,
		  		    		DB.COLUMN_TARIF_STATEMENT_3, DB.COLUMN_SUMMA_3, 
		  		    		DB.COLUMN_ITOGO };
		  		    to = new int[] { R.id.tvData, R.id.tvData1, R.id.tvData2, R.id.tvData3,
		  		    		R.id.tvData4, R.id.tvData5,
		  		    		R.id.tvData6, R.id.tvData7, R.id.tvData8, R.id.tvData9,
		  		    		R.id.tvData10,
		  		    		R.id.tvData11, R.id.tvData12, R.id.tvData13, R.id.tvData14,
		  		    		R.id.tvData15, R.id.tvItogo};
		  			// создааем адаптер и настраиваем список
		  		    scAdapter = new SimpleCursorAdapter(this, R.layout.table_3, cursor, from, to);
		  		    //lvView = (ListView) findViewById(R.id.listView1);
		  		    lvView.setAdapter(scAdapter);
		  			break;
		  		case 4:
		  			// формируем столбцы сопоставления
		  		    from = new String[] { DB.DATA, DB.NAME_1, DB.COLUMN_STATEMENT_1,
		  		    		DB.COLUMN_RASHOD_1,	DB.COLUMN_TARIF_STATEMENT_1, DB.COLUMN_SUMMA_1,
		  		    		DB.NAME_2,
		  		    		DB.COLUMN_STATEMENT_2, DB.COLUMN_RASHOD_2, DB.COLUMN_TARIF_STATEMENT_2,
		  		    		DB.COLUMN_SUMMA_2, DB.NAME_3,
		  		    		DB.COLUMN_STATEMENT_3, DB.COLUMN_RASHOD_3,
		  		    		DB.COLUMN_TARIF_STATEMENT_3, DB.COLUMN_SUMMA_3,
		  		    		DB.NAME_4,
		  		    		DB.COLUMN_STATEMENT_4, DB.COLUMN_RASHOD_4,
		  		    		DB.COLUMN_TARIF_STATEMENT_4, DB.COLUMN_SUMMA_4,
		  		    		DB.COLUMN_ITOGO };
		  		    to = new int[] { R.id.tvData, R.id.tvData1, R.id.tvData2, R.id.tvData3,
		  		    		R.id.tvData4, R.id.tvData5,
		  		    		R.id.tvData6, R.id.tvData7, R.id.tvData8, R.id.tvData9,
		  		    		R.id.tvData10,
		  		    		R.id.tvData11, R.id.tvData12, R.id.tvData13, R.id.tvData14,
		  		    		R.id.tvData15,
		  		    		R.id.tvData16, R.id.tvData17, R.id.tvData18, R.id.tvData19,
		  		    		R.id.tvData20, R.id.tvItogo};
		  			// создааем адаптер и настраиваем список
		  		    scAdapter = new SimpleCursorAdapter(this, R.layout.table_4, cursor, from, to);
		  		    //lvView = (ListView) findViewById(R.id.listView1);
		  		    lvView.setAdapter(scAdapter);
		  			break;		  		
		  		}
		      }
		}
	}
	
	protected void onResume(){
		super.onResume();
		myAdapter(cursor);
	}
	
	public void onBtnClick(View v){
		Intent intent = getIntent();
		long counterID = intent.getLongExtra("counter_id", 0);
		Intent intent1 = new Intent(this, CreateDataActivity.class);
		intent1.putExtra("counter_id", counterID);
		Toast.makeText(
				getApplicationContext(),
				"Parent ID = "
						+ counterID,
				Toast.LENGTH_SHORT).show();
		startActivity(intent1);
		
	}	

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
		ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, CM_SEND_ID, 0, R.string.data_send);
		menu.add(0, CM_DELETE_ID, 0, R.string.delete_record);
	}
	
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case CM_SEND_ID:
			
			
			AdapterContextMenuInfo acmi0 = (AdapterContextMenuInfo) item.getMenuInfo();
			
			c = db.getSortingDataByIdInTable_2(String.valueOf(acmi0.id));
			c.moveToFirst();
			String parentId = c.getString(c.getColumnIndex("parent_id"));
			double numOfTarif = Double.valueOf(cursor.getString(cursor.getColumnIndex("tarif_number")));
			
			prepareEmail(numOfTarif, c, Long.valueOf(parentId));
			
			Toast.makeText(
					getApplicationContext(), 
					"выбрали 'отправить' "
							+ acmi0.id,
					Toast.LENGTH_SHORT).show();
			
			
			
			break;

		case CM_DELETE_ID:
			//получаем из пункта контекстного меню данные по пункту списка
			AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item.getMenuInfo();
			//извлекаем id записи и удаляем соответствующую запись в БД
			db.delRecTable_2(acmi.id);
			//обновляем курсор
			cursor.requery();
			return true;
		}	
	return super.onContextItemSelected(item);
	}
	
	public void prepareEmail(double tarifNumber, Cursor c, long parentId) {
		c_1 = db.getDataOnId(parentId);
		c_1.moveToFirst();
		String lastStat_2, stat_2, lastStat_3, stat_3, lastStat_4, stat_4,
				tName_2, tName_3, tName_4;
		String lSchet = c_1.getString(c_1.getColumnIndex("l_schet"));
		
		String ownerName = c_1.getString(c_1.getColumnIndex("owner_name"));
		
		String homeAdres = c_1.getString(c_1.getColumnIndex("home_adress"));
		String emailAdress = c_1.getString(c_1.getColumnIndex("e_mail"));
		String tName_1 = c_1.getString(c_1.getColumnIndex("tarif_name_1"));
		String lastStat_1 = c.getString(c.getColumnIndex("laststat_1"));
		String stat_1 = c.getString(c.getColumnIndex("stat_1"));
		String eMail[] = {emailAdress};
		final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		switch ((int)tarifNumber) {
		case 1:
			
			/* Заполняем данными: тип текста, адрес, сабж и собственно текст письма */
			emailIntent.setType("application/octet-stream");
			emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, eMail);
			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Показания");
			emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(
					"Лицевой счет №: " + lSchet + "<br>" +
					"Фамилия: " + ownerName + "<br>" +
					"Адрес: " + homeAdres + "<br>" +
					tName_1 + ": Пред. показания: " + lastStat_1 + ", Текущие: " + stat_1));
			
			/* Отправляем на выбор!*/
			startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			break;
		case 2:
			tName_2 = c_1.getString(c_1.getColumnIndex("tarif_name_2"));
			lastStat_2 = c.getString(c.getColumnIndex("laststat_2"));
			stat_2 = c.getString(c.getColumnIndex("stat_2"));
			emailIntent.setType("application/octet-stream");
			emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, eMail);
			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Показания");
			emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(
					"Лицевой счет №: " + lSchet + "<br>" +
					"Фамилия: " + ownerName + "<br>" +
					"Адрес: " + homeAdres + "<br>" +
					tName_1 + ": Пред. показания: " + lastStat_1 + ", Текущие: " + stat_1 + "<br>" +
					tName_2 + ": Пред. показания: " + lastStat_2 + ", Текущие: " + stat_2));
			startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			break;
		case 3:
			tName_2 = c_1.getString(c_1.getColumnIndex("tarif_name_2"));
			lastStat_2 = c.getString(c.getColumnIndex("laststat_2"));
			stat_2 = c.getString(c.getColumnIndex("stat_2"));
			tName_3 = c_1.getString(c_1.getColumnIndex("tarif_name_3"));
			lastStat_3 = c.getString(c.getColumnIndex("laststat_3"));
			stat_3 = c.getString(c.getColumnIndex("stat_3"));
			emailIntent.setType("application/octet-stream");
			emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, eMail);
			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Показания");
			emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(
					"Лицевой счет №: " + lSchet + "<br>" +
					"Фамилия: " + ownerName + "<br>" +
					"Адрес: " + homeAdres + "<br>" +
					tName_1 + ": Пред. показания: " + lastStat_1 + ", Текущие: " + stat_1 + "<br>" +
					tName_2 + ": Пред. показания: " + lastStat_2 + ", Текущие: " + stat_2 + "<br>" +
					tName_3 + ": Пред. показания: " + lastStat_3 + ", Текущие: " + stat_3));
			startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			break;
		case 4:
			tName_2 = c_1.getString(c_1.getColumnIndex("tarif_name_2"));
			lastStat_2 = c.getString(c.getColumnIndex("laststat_2"));
			stat_2 = c.getString(c.getColumnIndex("stat_2"));
			tName_3 = c_1.getString(c_1.getColumnIndex("tarif_name_3"));
			lastStat_3 = c.getString(c.getColumnIndex("laststat_3"));
			stat_3 = c.getString(c.getColumnIndex("stat_3"));
			tName_4 = c_1.getString(c_1.getColumnIndex("tarif_name_4"));
			lastStat_4 = c.getString(c.getColumnIndex("laststat_4"));
			stat_4 = c.getString(c.getColumnIndex("stat_4"));
			emailIntent.setType("application/octet-stream");
			emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, eMail);
			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Показания");
			emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(
					"Лицевой счет №: " + lSchet + "<br>" +
					"Фамилия: " + ownerName + "<br>" +
					"Адрес: " + homeAdres + "<br>" +
					tName_1 + ": Пред. показания: " + lastStat_1 + ", Текущие: " + stat_1 + "<br>" +
					tName_2 + ": Пред. показания: " + lastStat_2 + ", Текущие: " + stat_2 + "<br>" +
					tName_3 + ": Пред. показания: " + lastStat_3 + ", Текущие: " + stat_3 + "<br>" +
					tName_4 + ": Пред. показания: " + lastStat_4 + ", Текущие: " + stat_4));
			startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			break;
		}
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		cursor.close();	
		//c.close();
	}
	
}
