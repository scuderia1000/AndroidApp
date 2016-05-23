package com.elenaganel.homepayments;

import com.elenaganel.homepayments.DB;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.TextView;

public class CreateCounterActivity extends Activity {
	final String LOG_TAG = "myLogs";
	Intent intent, intent_1;
	LinearLayout llcnt_Name_2, llayout1, ll_Tarif_0, ll_Tarif_N;
	Button btnAddCnt, btnAddTarif, btnCreateCnt;
	TextView tv_CntName_2;
	EditText etCounterName, etName, et_Ls, et_Adres, etEmail, etCntName_1, etTarif_1,
			etCntName_2, etTarif_2, etCntName_3, etTarif_3, etCntName_4, etTarif_4,
			etStartStat_1, etStartStat_2, etStartStat_3, etStartStat_4, etOwnerName,
			etCntName_i, etTarif_i, etStartStat_i;
	long rowId, counterID;
	int saveState = 0;
	DB db;
	Cursor cursor;
	
	int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
	int matchParent = LinearLayout.LayoutParams.MATCH_PARENT;
	int i = 3; //Счетчик нажатий кнопки Добавить тариф
	int j = 2; //Названия новых тарифоф
	//int rl_matchParent = RelativeLayout.LayoutParams.MATCH_PARENT;
	//int rl_wrapContent = RelativeLayout.LayoutParams.WRAP_CONTENT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_counter);
		if (savedInstanceState != null) {
			saveState = savedInstanceState.getInt("saveState");
		}
		
		//Открываем подключение к БД
	    db = new DB(this);
	  	db.open();
		
		llcnt_Name_2 = (LinearLayout)findViewById(R.id.llcnt_Name_2);
		etCounterName = (EditText)findViewById(R.id.etCounterName);
		ll_Tarif_N = (LinearLayout)findViewById(R.id.ll_Tarif_N);
		btnAddCnt = (Button)findViewById(R.id.btnAddCounter);
		btnAddTarif = (Button)findViewById(R.id.btnAddTarif);
		btnCreateCnt = (Button)findViewById(R.id.btnCreateCnt);
		et_Ls = (EditText)findViewById(R.id.et_Ls);
		etOwnerName = (EditText)findViewById(R.id.etOwnerName);
	    et_Adres = (EditText)findViewById(R.id.et_Adres);
	    etEmail = (EditText)findViewById(R.id.etEmail);
	    
	    etCntName_1 = (EditText)findViewById(R.id.etCntName_1);
	    etTarif_1 = (EditText)findViewById(R.id.etTarif_1);
	    etStartStat_1 = (EditText)findViewById(R.id.etStartStat_1);
		
	    intent = getIntent();
		counterID = intent.getLongExtra("counter_id", 0);
		
		if (counterID == 0){
			if (saveState == 0) {
				//Вызываем список с названиями счетчиков
				Intent intent = new Intent(this, CounterType.class);
		    	startActivityForResult(intent, 1);
			}
		} else {
			btnAddTarif.setClickable(false);
			btnAddTarif.setEnabled(false);
			etStartStat_1.setClickable(false);
			etStartStat_1.setEnabled(false);
			cursor = db.getDataOnId(counterID);
			//EditText etCntName = new EditText(this);
			//LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
			//		matchParent, wrapContent);
			etCounterName.setText(cursor.getString(cursor.getColumnIndex("count_name")));
			//llcnt_Name_2.addView(etCntName, lParams);
			//etCntName.setId(1000);	
			et_Ls.setText(cursor.getString(cursor.getColumnIndex("l_schet")));
			etOwnerName.setText(cursor.getString(cursor.getColumnIndex("owner_name")));
			et_Adres.setText(cursor.getString(cursor.getColumnIndex("home_adress")));
			etEmail.setText(cursor.getString(cursor.getColumnIndex("e_mail")));
			etCntName_1.setText(cursor.getString(cursor.getColumnIndex("tarif_name_1")));
			etTarif_1.setText(cursor.getString(cursor.getColumnIndex("tarif_1")));
			etStartStat_1.setText(cursor.getString(cursor.getColumnIndex("startstat_1")));
			if (cursor.getString(cursor.getColumnIndex("tarif_name_2")) != null) {
				editPokaz(counterID, cursor);
			}
			etTarif_1.requestFocus();
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
		}
	   
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_counter, menu);
		return true;
	}
	
	public void editPokaz(long counterID, Cursor cursor) {
		int tarifNum = 2;
	    if (cursor != null) {
	    	if (cursor.moveToFirst()) {
	    		do {
	    			//Перебор колонок в БД, если встретится null выходим
	    			for (String cn : cursor.getColumnNames()) {
	    				String colValue = cursor.getString(cursor.getColumnIndex(cn));
	    				if (colValue == null) break;
	    				String cntName = "tarif_name_" + String.valueOf(tarifNum);
	    				String tarif = "tarif_" + String.valueOf(tarifNum);
	    				String startStat = "startstat_" + String.valueOf(tarifNum);
	    				if (cn.compareTo(cntName) == 0) {
	    					addLayout(tarifNum);
	    					etCntName_i = (EditText)findViewById(200 + tarifNum);
	  	        		  	etCntName_i.setText(colValue);
	    				}
	    				if (cn.compareTo(tarif) == 0) {
	    					etTarif_i = (EditText)findViewById(400 + tarifNum);
	    					etTarif_i.setText(colValue);
	    				}
	    				if (cn.compareTo(startStat) == 0) {
	    					etStartStat_i = (EditText)findViewById(500 + tarifNum);
	    					etStartStat_i.setText(colValue);
	    					etStartStat_i.setClickable(false);
	    					etStartStat_i.setEnabled(false);
	    					tarifNum++;
	    				}
	    			}
	    		} while (cursor.moveToNext());
	    	}
	    } else Log.d(LOG_TAG, "Cursor is null");
	}
	
	public void addLayout(int i) {
		//j++;
		/* Создем новый Layout из файла шаблона activity_tarif_2.xml */
		LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
		          matchParent, wrapContent);
		LinearLayout llNew = new LinearLayout(this);
		llNew.setLayoutParams(lParams);
		ll_Tarif_N.addView(llNew);
		
		LayoutInflater ltInflater = getLayoutInflater();
        View view = ltInflater.inflate(R.layout.activity_tarif_2, llNew, false);
		llNew.addView(view);
		
		//Находим элементы шаблона activity_tarif_2.xml
		TextView tv_CntName_2, tv_Tarif_2;
		EditText et_CntName_2, et_Tarif_2, etStartStat;
		
		tv_CntName_2 = (TextView)findViewById(R.id.tv_CntName_2);
		tv_Tarif_2 = (TextView)findViewById(R.id.tv_Tarif_2);
		et_CntName_2 = (EditText)findViewById(R.id.et_CntName_2);
		et_Tarif_2 = (EditText)findViewById(R.id.et_Tarif_2);
		etStartStat = (EditText)findViewById(R.id.etStartStat);
		
		//Меняем id элементов нового тарифа
		tv_CntName_2.setId(i+100);
		et_CntName_2.setId(i+200);
		tv_Tarif_2.setId(i+300);
		et_Tarif_2.setId(i+400);
		etStartStat.setId(i+500);
		
		tv_CntName_2.setText("Название тарифа " + i);
		tv_Tarif_2.setText("Тариф " + i);

	}
	
	public void btnAddCnt_Click (View v) {
		
		/* При нажатии на кнопку "+" удалаем надпись рядом с кнопкой
		 * и вызываем экран выбора типа счетчика */
		etCounterName.setText(null);		
		Intent intent = new Intent(this, CounterType.class);
    	startActivityForResult(intent, 1);
    	
	}
	
	/* При нажатии на кнопку "Добавить тариф" создаем Layout с тарифом № 2, 3, 4 */
	public void btnAddTarif_Click (View v) {
		/* Проверяем сколько раз нажата кнопка */
		if (j <= 4) {
			if (j == 4) {
				btnAddTarif.setClickable(false);
				btnAddTarif.setEnabled(false);
			}
			addLayout(j);
			j++;
		}			
	}		

	//Создаем БД с Названием счетчика, лицевым счетом, адресом и т.д.
	public void btnCreateCnt_Click (View v) {	    
	    // получаем данные из полей ввода
	    //etName = (EditText)findViewById(1000);	    
	    etCntName_2 = (EditText)findViewById(202);
	    etTarif_2 = (EditText)findViewById(402);
	    etStartStat_2  =(EditText)findViewById(502);
	    etCntName_3 = (EditText)findViewById(203);
	    etTarif_3 = (EditText)findViewById(403);
	    etStartStat_3  =(EditText)findViewById(503);
	    etCntName_4 = (EditText)findViewById(204);
	    etTarif_4 = (EditText)findViewById(404);
	    etStartStat_4  =(EditText)findViewById(504);
	    
	    //Проверки заполнения необходимых граф данными
	    int numberOfTarif = check(etCounterName, et_Ls, etOwnerName, et_Adres, etEmail,
	    		etCntName_1, etTarif_1, etStartStat_1,
	    		etCntName_2, etTarif_2, etStartStat_2,
	    		etCntName_3, etTarif_3, etStartStat_3,
	    		etCntName_4, etTarif_4, etStartStat_4);
	    if (counterID != 0) {
	    	saveEdit(counterID, numberOfTarif);
	    	return;
	    }
	    
	    //Добавление данных в БД, запрос составляется в зав-ти от кол-ва тарифов
	    switch (numberOfTarif) {
		case 0: 
			break;
		case 1:
			rowId = db.addRecTable_1(R.drawable.ic_launcher, etCounterName.getText().toString(),
		    		et_Ls.getText().toString(), etOwnerName.getText().toString(),
		    		et_Adres.getText().toString(), etEmail.getText().toString(), 
		    		etCntName_1.getText().toString(),
		    		etTarif_1.getText().toString(), etStartStat_1.getText().toString(),
		    		null, null, null, null, null, null, null, null, null);
	    	db.addMainRecTable_2(String.valueOf(rowId), "", null, null,
	    			etStartStat_1.getText().toString(),
	    			null, null, null, null, null, null, null, null, null, null,
	    			null, null, null, null, null, null, null, null, null, null,
	    			null, null, null);
			finish();
			break;
		case 2:
			rowId = db.addRecTable_1(R.drawable.ic_launcher, etCounterName.getText().toString(),
		    		et_Ls.getText().toString(), etOwnerName.getText().toString(),
		    		et_Adres.getText().toString(), etEmail.getText().toString(), 
		    		etCntName_1.getText().toString(),
		    		etTarif_1.getText().toString(), etStartStat_1.getText().toString(),
		    		
		    		etCntName_2.getText().toString(),
		    		etTarif_2.getText().toString(), etStartStat_2.getText().toString(),
		    		null, null, null, null, null, null);
	    	db.addMainRecTable_2(String.valueOf(rowId), "", null, null,
	    			etStartStat_1.getText().toString(),	null, null, null, null, null,
	    			etStartStat_2.getText().toString(), null, null, null, null, null,
	    			null, null, null, null, null, null, null, null, null, null, null, null);
			finish();
			break;
		case 3:
			rowId = db.addRecTable_1(R.drawable.ic_launcher, etCounterName.getText().toString(),
		    		et_Ls.getText().toString(), etOwnerName.getText().toString(),
		    		et_Adres.getText().toString(), etEmail.getText().toString(), 
		    		etCntName_1.getText().toString(),
		    		etTarif_1.getText().toString(), etStartStat_1.getText().toString(),
		    		
		    		etCntName_2.getText().toString(),
		    		etTarif_2.getText().toString(), etStartStat_2.getText().toString(),
		    		etCntName_3.getText().toString(),
		    		etTarif_3.getText().toString(), etStartStat_3.getText().toString(),
		    		null, null, null);
	    	db.addMainRecTable_2(String.valueOf(rowId), "", null, null,
	    			etStartStat_1.getText().toString(),	null, null, null, null, null, 
	    			etStartStat_2.getText().toString(), null, null, null, null, null,
	    			etStartStat_3.getText().toString(),	null, null, null, null, null,
	    			null, null, null, null, null, null);
			finish();
			break;
		case 4:
			rowId = db.addRecTable_1(R.drawable.ic_launcher, etCounterName.getText().toString(),
		    		et_Ls.getText().toString(), etOwnerName.getText().toString(),
		    		et_Adres.getText().toString(),	etEmail.getText().toString(), 
		    		etCntName_1.getText().toString(),
		    		etTarif_1.getText().toString(), etStartStat_1.getText().toString(),
		    		etCntName_2.getText().toString(),
		    		etTarif_2.getText().toString(), etStartStat_2.getText().toString(),
		    		etCntName_3.getText().toString(),
		    		etTarif_3.getText().toString(), etStartStat_3.getText().toString(),
		    		etCntName_4.getText().toString(),
		    		etTarif_4.getText().toString(), etStartStat_4.getText().toString());
		    	db.addMainRecTable_2(String.valueOf(rowId), "", null, null,
		    			etStartStat_1.getText().toString(),	null, null, null, null, null,
		    			etStartStat_2.getText().toString(), null, null, null, null, null,
		    			etStartStat_3.getText().toString(),	null, null, null, null, null,
		    			etStartStat_4.getText().toString(), null, null, null, null, null);
			finish();
			break;
		}
	    hideInputMethod();
	}
	
	public void saveEdit(long counterId, int numberOfTarif) {
		switch (numberOfTarif) {
		case 0: 
			break;
		case 1:
			db.updateTable_1(String.valueOf(counterId), etCounterName.getText().toString(),
		    		et_Ls.getText().toString(), etOwnerName.getText().toString(),
		    		et_Adres.getText().toString(), etEmail.getText().toString(), 
		    		etCntName_1.getText().toString(),
		    		etTarif_1.getText().toString(), etStartStat_1.getText().toString(),
		    		null, null, null, null, null, null, null, null, null);
			finish();
			break;
		case 2:
			db.updateTable_1(String.valueOf(counterId), etCounterName.getText().toString(),
		    		et_Ls.getText().toString(), etOwnerName.getText().toString(),
		    		et_Adres.getText().toString(), etEmail.getText().toString(), 
		    		etCntName_1.getText().toString(),
		    		etTarif_1.getText().toString(), etStartStat_1.getText().toString(),
		    		
		    		etCntName_2.getText().toString(),
		    		etTarif_2.getText().toString(), etStartStat_2.getText().toString(),
		    		null, null, null, null, null, null);
			finish();
			break;
		case 3: 
			db.updateTable_1(String.valueOf(counterId), etCounterName.getText().toString(),
		    		et_Ls.getText().toString(), etOwnerName.getText().toString(),
		    		et_Adres.getText().toString(), etEmail.getText().toString(), 
		    		etCntName_1.getText().toString(),
		    		etTarif_1.getText().toString(), etStartStat_1.getText().toString(),
		    		
		    		etCntName_2.getText().toString(),
		    		etTarif_2.getText().toString(), etStartStat_2.getText().toString(),
		    		etCntName_3.getText().toString(),
		    		etTarif_3.getText().toString(), etStartStat_3.getText().toString(),
		    		null, null, null);
			finish();
			break;
		case 4: 
			db.updateTable_1(String.valueOf(counterId), etCounterName.getText().toString(),
		    		et_Ls.getText().toString(), etOwnerName.getText().toString(),
		    		et_Adres.getText().toString(),	etEmail.getText().toString(), 
		    		etCntName_1.getText().toString(),
		    		etTarif_1.getText().toString(), etStartStat_1.getText().toString(),
		    		etCntName_2.getText().toString(),
		    		etTarif_2.getText().toString(), etStartStat_2.getText().toString(),
		    		etCntName_3.getText().toString(),
		    		etTarif_3.getText().toString(), etStartStat_3.getText().toString(),
		    		etCntName_4.getText().toString(),
		    		etTarif_4.getText().toString(), etStartStat_4.getText().toString());
			finish();
			break;
		}
		cursor.close();
		hideInputMethod();
	}
	
	// прячем программную клавиатуру
	protected void hideInputMethod() {
	    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
	    if (imm != null) {
	        imm.hideSoftInputFromWindow(et_Ls.getWindowToken(), 0);
	    }
	}
	 
		  
	@Override
	/*Получаем из окна выбора типа счетчика его id*/
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		/*В поле названия счетчика пишем выбранное название*/
		if (resultCode == RESULT_OK) {
			saveState = 1;
			int name = data.getIntExtra("name", 0);
		
			
			//EditText et_counterName = new EditText(this);
		
			/*Делаем текст крупным*/
			//et_counterName.setTextAppearance(this, android.R.style.TextAppearance_Large);
		
			//LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
			//		matchParent, wrapContent);
		
			/* По полученному id определяем что за счетчик создать*/
			switch (name) {
			case 0:
				etCounterName.setText(R.string.water_counter_text);
				//llcnt_Name_2.addView(et_counterName, lParams);
				//et_counterName.setId(1000);
				etCntName_1.setText(R.string.voda_name);
				//et_counterName.getResources().getColor();
				break;
			case 1:
				etCounterName.setText(R.string.electro_counter_text);
				//llcnt_Name_2.addView(et_counterName, lParams);
				//et_counterName.setId(1000);
				etCntName_1.setText(R.string.electro_name_1);
				break;
			case 2:
				etCounterName.setText(R.string.gas_counter_text);
				//llcnt_Name_2.addView(et_counterName, lParams);
				//et_counterName.setId(1000);
				etCntName_1.setText(R.string.gas_name);
				break;
			case 3:
				etCounterName.setText(R.string.hot_counter_text);
				//llcnt_Name_2.addView(et_counterName, lParams);
				//et_counterName.setId(1000);
				etCntName_1.setText(R.string.otoplenie_name);
				break;
			case 4:
				etCounterName.requestFocus();
				etCounterName.setSingleLine();
				etCounterName.setText(R.string.my_counter_text);
				//llcnt_Name_2.addView(et_counterName, lParams);
				//et_counterName.setId(1000);
				//Toast.makeText(getApplicationContext(), "ID= " + et_counterName.getId(), Toast.LENGTH_LONG).show();
				break;
			}
			et_Ls.requestFocus();
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
		}
	}
	
	//Проверки заполнения необходимых граф данными
	public int check (EditText etName, EditText et_Ls, EditText etOwnerName,
			EditText et_Adres, EditText etEmail, 
			EditText etCntName_1, EditText etTarif_1, EditText etStartStat_1,
			EditText etCntName_2, EditText etTarif_2, EditText etStartStat_2,
			EditText etCntName_3, EditText etTarif_3, EditText etStartStat_3, 
			EditText etCntName_4, EditText etTarif_4, EditText etStartStat_4){
		int tmp = 0;
		if (etName != null) {
	  		//Если EditText не заполнен выводим сообщение об этом
	  		//ставим фокус на EditText и выходим из процедуры нажатия кнопки
	  		if (etName.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите название счетчика", Toast.LENGTH_SHORT).show();
	  			etName.requestFocus();
	  			return tmp = 0;
	  		}		
	  	}
	  	if (et_Ls != null) {
	  		if (et_Ls.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите номер лицевого счета", Toast.LENGTH_SHORT).show();
	  			et_Ls.requestFocus();
	  			return tmp = 0;
	  		}		
	  	}
	  	if (etOwnerName != null) {
	  		if (etOwnerName.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите номер лицевого счета", Toast.LENGTH_SHORT).show();
	  			etOwnerName.requestFocus();
	  			return tmp = 0;
	  		}		
	  	}
	  	if (et_Adres != null) {
	  		if (et_Adres.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите адрес", Toast.LENGTH_SHORT).show();
	  			et_Adres.requestFocus();
	  			return tmp = 0;
	  		}		
	  	}
	  	if (etEmail != null) {
	  		if (etEmail.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите email", Toast.LENGTH_SHORT).show();
	  			etEmail.requestFocus();
	  			return tmp = 0;
	  		}		
	  	}
	  	if (etCntName_1 != null) {
	  		if (etCntName_1.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите название тарифа № 1", Toast.LENGTH_SHORT).show();
	  			etCntName_1.requestFocus();
	  			return tmp = 0;
	  		}
	  		if (etTarif_1.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите тариф № 1", Toast.LENGTH_SHORT).show();
	  			etTarif_1.requestFocus();
	  			return tmp = 0;
	  		}
	  		if (etStartStat_1.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите текущее показание", Toast.LENGTH_SHORT).show();
	  			etStartStat_1.requestFocus();
	  			return tmp = 0;
	  		}
	  		tmp = 1;
	  	}
	  	if (etCntName_2 != null) {
	  		if (etCntName_2.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите название тарифа № 2", Toast.LENGTH_SHORT).show();
	  			etCntName_2.requestFocus();
	  			return tmp = 0;
	  		}
	  		if (etTarif_2.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите тариф № 2", Toast.LENGTH_SHORT).show();
	  			etTarif_2.requestFocus();
	  			return tmp = 0;
	  		}
	  		if (etStartStat_2.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите текущее показание", Toast.LENGTH_SHORT).show();
	  			etStartStat_2.requestFocus();
	  			return tmp = 0;
	  		}
	  		tmp = 2;
	  	}
	  	if (etCntName_3 != null) {
	  		if (etCntName_3.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите название тарифа № 3", Toast.LENGTH_SHORT).show();
	  			etCntName_3.requestFocus();
	  			return tmp = 0;
	  		}
	  		if (etTarif_3.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите тариф № 3", Toast.LENGTH_SHORT).show();
	  			etTarif_3.requestFocus();
	  			return tmp = 0;
	  		}
	  		if (etStartStat_3.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите текущее показание", Toast.LENGTH_SHORT).show();
	  			etStartStat_3.requestFocus();
	  			return tmp = 0;
	  		}
	  		tmp = 3;
	  	}
	  	if (etCntName_4 != null) {
	  		if (etCntName_4.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите название тарифа № 4", Toast.LENGTH_SHORT).show();
	  			etCntName_4.requestFocus();
	  			return tmp = 0;
	  		}
	  		if (etTarif_4.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите тариф № 4", Toast.LENGTH_SHORT).show();
	  			etTarif_4.requestFocus();
	  			return tmp = 0;
	  		}
	  		if (etStartStat_4.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите текущее показание", Toast.LENGTH_SHORT).show();
	  			etStartStat_4.requestFocus();
	  			return tmp = 0;
	  		}
	  		tmp = 4;
	  	}
	  	return tmp;
	  	//if (tmp = true) return true; else return false;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putInt("saveState", saveState);
	}

/*	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		saveState = savedInstanceState.getInt("saveState");
	}*/

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	
}
