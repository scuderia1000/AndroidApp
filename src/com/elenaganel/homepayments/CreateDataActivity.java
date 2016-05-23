package com.elenaganel.homepayments;

import java.util.ArrayList;
import java.util.Calendar;

import com.elenaganel.homepayments.DB;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.text.InputType;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class CreateDataActivity extends Activity implements OnCheckedChangeListener {
	
	private Camera cam;

	final Calendar calendar = Calendar.getInstance();
    int mYear = calendar.get(Calendar.YEAR);
    int mMonth = calendar.get(Calendar.MONTH) + 1;
    int mDay = calendar.get(Calendar.DAY_OF_MONTH);
    ToggleButton toggleBtn;
	EditText etStat_1, etTarif_1, etStat_2, etTarif_2, etStat_3, etTarif_3,
		etStat_4, etTarif_4;
	TextView tvStatName_1, tvStatName_2, tvStatName_3, tvStatName_4;
	LinearLayout parentLayout;
	DB db;
	Cursor c, cursor;
	ArrayList<Double> data = new ArrayList<Double>();
	final String LOG_TAG = "myLogs";
	int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
	int matchParent = LinearLayout.LayoutParams.MATCH_PARENT;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_data);
		
		toggleBtn = (ToggleButton)findViewById(R.id.toggleBtn);
		toggleBtn.setOnCheckedChangeListener(this);
		
		//Открываем подключение к БД
	    db = new DB(this);
	  	db.open();
	    
	    //Получаем id из предидущего Activity
	    Intent intent = getIntent();
		long counterID = intent.getLongExtra("counter_id", 0);
	    
		//получаем курсор на позиции равной id
	  	cursor = db.getDataOnId(counterID);
	  	
	  	//Записываем в массив data кол-во тарифов
	  	data.add(logCursor(cursor));
	  	cursor.close();
	  	
	  	etStat_1 = (EditText)findViewById(100);
	  	etStat_1.requestFocus();
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
	  	
	}
	
	// вывод в лог данных из курсора
	public double logCursor(Cursor c) {
		int i = 1;
		String cntNameValue;
	    if (c != null) {
	      if (c.moveToFirst()) {
	        do {
	        	cntNameValue = "";
	          //Перебор колонок в БД, если встретится null выходим
	          for (String cn : c.getColumnNames()) {
	        	  
	        	  String colValue = c.getString(c.getColumnIndex(cn));
	        	  if (colValue == null) break;
	        	  String n = "tarif_" + String.valueOf(i);
	        	  String cntName = "tarif_name_" + String.valueOf(i);
	        	  if (cn.compareTo(cntName) == 0) {cntNameValue = colValue;}
	        	  //Если встретилась колонка с названием tarif_N, рисуем Layout
	        	  if (cn.compareTo(n) == 0) {
	        		  addLayout(i, colValue, cntNameValue);
	        		  i++;      		  
	        	}
	          }
	        } while (c.moveToNext());
	      }
	    } else Log.d(LOG_TAG, "Cursor is null");
	    return Double.valueOf(i - 1);
	  }
	
	//Создание Layout с полем ввода показаний счетчика и тарифа
	public void addLayout (int counter, String columnValue, String cntName ) {
		  parentLayout = (LinearLayout)findViewById(R.id.parentLayout);
		  // Создем новый Layout
		  LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
			          matchParent, wrapContent);
		  LinearLayout.LayoutParams lParams1 = new LinearLayout.LayoutParams(
					wrapContent, wrapContent);
		  
		  TextView tvCntName = new TextView(this);
		  tvCntName.setText(cntName);
		  tvCntName.setId(500 * counter);
		  parentLayout.addView(tvCntName, lParams1);
		  
		  for (int i = 0; i < 2; i++){
			  LinearLayout llNew = new LinearLayout(this);
			  llNew.setLayoutParams(lParams);
			  llNew.setOrientation(LinearLayout.HORIZONTAL);
			  parentLayout.addView(llNew);
			  
			  TextView tvNewPokaz = new TextView(this);
			  
			  EditText etNew = new EditText(this);
			  if (i == 1) {
				  
				  tvNewPokaz.setText("Тариф " + String.valueOf(counter));
				  etNew.setText(columnValue);
				  etNew.setId(100 * counter + i);
			  }
			  else {
				  
				  tvNewPokaz.setText("Показания " + String.valueOf(counter));
				  etNew.setId(100 * counter + i);
				  etNew.setMaxLines(1);
				  etNew.setRawInputType(InputType.TYPE_CLASS_NUMBER | 
				    		InputType.TYPE_NUMBER_FLAG_DECIMAL);
			  }
			  
			  llNew.addView(tvNewPokaz, lParams1);
			  llNew.addView(etNew, lParams);
			  
			  //Задать тип вводимых данных как десятичное число
			  /*etNew.setRawInputType(InputType.TYPE_CLASS_NUMBER | 
			    		InputType.TYPE_NUMBER_FLAG_DECIMAL);*/
		  }
	  }
	  
	public void onButtonClick(View v){
		//etStat_1 = (EditText)findViewById(100);
		etTarif_1 = (EditText)findViewById(101);
		tvStatName_1 = (TextView)findViewById(500);
		etStat_2 = (EditText)findViewById(200);
		etTarif_2 = (EditText)findViewById(201);
		tvStatName_2 = (TextView)findViewById(1000);
		etStat_3 = (EditText)findViewById(300);
		etTarif_3 = (EditText)findViewById(301);
		tvStatName_3 = (TextView)findViewById(1500);
		etStat_4 = (EditText)findViewById(400);
		etTarif_4 = (EditText)findViewById(401);
		tvStatName_4 = (TextView)findViewById(2000);
		//Получаем id из предидущего Activity
		Intent intent = getIntent();
		long parentID = intent.getLongExtra("counter_id", 0);		
			
		//получаем весь курсор в таблице № 2, отсортированный по убыванию
		c = db.getSortingDataByParentIdInTable_2(String.valueOf(parentID));
		c.moveToFirst();
		//Проверка заполнения данных, если где-то пусто выход		
		if(check(etStat_1, etTarif_1, etStat_2, etTarif_2, etStat_3, etTarif_3,
				etStat_4, etTarif_4) == 0) return;
		//Подготовка данных для вставки в БД
		dataPrepare(c, data.get(0).intValue());
		
		double itogo, roundItogo;
		String date = ""+mDay+"-"+mMonth+"-"+mYear;	
		int tmpStat_1 = data.get(1).intValue();//Убираем 0 после запятой в показаниях
		int tmpStat_2, tmpStat_3, tmpStat_4;
		//В завис-ти от кол-ва тарифов делаем запись в БД таблицу № 2
		switch (data.get(0).intValue()) {
		case 0:	
			break;
		case 1:
			itogo = data.get(3);
			roundItogo = roundMyData(itogo, 2);
			db.addMainRecTable_2(String.valueOf(parentID), String.valueOf(data.get(0)),
					date, tvStatName_1.getText().toString(), String.valueOf(tmpStat_1), 
					etStat_1.getText().toString(), String.valueOf(data.get(2)),
					etTarif_1.getText().toString(), String.valueOf(data.get(3)),
					null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, String.valueOf(roundItogo));
			c.requery();
			finish();
			break;
		case 2:
			tmpStat_2 = data.get(4).intValue();
			itogo = data.get(3) + data.get(6);
			roundItogo = roundMyData(itogo, 2);
			db.addMainRecTable_2(String.valueOf(parentID), String.valueOf(data.get(0)),
					date, tvStatName_1.getText().toString(), String.valueOf(tmpStat_1), 
					etStat_1.getText().toString(), String.valueOf(data.get(2)),
					etTarif_1.getText().toString(), String.valueOf(data.get(3)), 
					tvStatName_2.getText().toString(), 
					String.valueOf(tmpStat_2), 
					etStat_2.getText().toString(), String.valueOf(data.get(5)), 
					etTarif_2.getText().toString(), String.valueOf(data.get(6)),
					null, null, null, null, null, null, null, null, null, null, null, null,
					String.valueOf(roundItogo));
			c.requery();
			finish();
			break;
		case 3:
			tmpStat_2 = data.get(4).intValue();
			tmpStat_3 = data.get(7).intValue();
			itogo = data.get(3) + data.get(6) + data.get(9);
			roundItogo = roundMyData(itogo, 2);
			db.addMainRecTable_2(String.valueOf(parentID), String.valueOf(data.get(0)),
					date, tvStatName_1.getText().toString(), String.valueOf(tmpStat_1), 
					etStat_1.getText().toString(), String.valueOf(data.get(2)),
					etTarif_1.getText().toString(), String.valueOf(data.get(3)), 
					tvStatName_2.getText().toString(), 
					String.valueOf(tmpStat_2), 
					etStat_2.getText().toString(), String.valueOf(data.get(5)), 
					etTarif_2.getText().toString(), String.valueOf(data.get(6)), 
					tvStatName_3.getText().toString(),
					String.valueOf(tmpStat_3), 
					etStat_3.getText().toString(), String.valueOf(data.get(8)), 
					etTarif_3.getText().toString(), String.valueOf(data.get(9)), null,
					null, null, null, null, null,
					String.valueOf(roundItogo));
			c.requery();
			finish();
			break;	
		case 4:
			tmpStat_2 = data.get(4).intValue();
			tmpStat_3 = data.get(7).intValue();
			tmpStat_4 = data.get(10).intValue();
			itogo = data.get(3) + data.get(6) + data.get(9) + data.get(12);
			roundItogo = roundMyData(itogo, 2);
			db.addMainRecTable_2(String.valueOf(parentID), String.valueOf(data.get(0)),
					date, tvStatName_1.getText().toString(), String.valueOf(tmpStat_1), 
					etStat_1.getText().toString(), String.valueOf(data.get(2)),
					etTarif_1.getText().toString(), String.valueOf(data.get(3)), 
					tvStatName_2.getText().toString(), 
					String.valueOf(tmpStat_2), 
					etStat_2.getText().toString(), String.valueOf(data.get(5)), 
					etTarif_2.getText().toString(), String.valueOf(data.get(6)),
					tvStatName_3.getText().toString(),
					String.valueOf(tmpStat_3), 
					etStat_3.getText().toString(), String.valueOf(data.get(8)), 
					etTarif_3.getText().toString(), String.valueOf(data.get(9)),
					tvStatName_4.getText().toString(), 
					String.valueOf(tmpStat_4), 
					etStat_4.getText().toString(), String.valueOf(data.get(11)), 
					etTarif_4.getText().toString(), String.valueOf(data.get(12)), 
					String.valueOf(roundItogo));
			c.requery();
			finish();
			break;	
		}
	hideInputMethod();	
	}
		
	/**
	 * прячем программную клавиатуру
	 */
	protected void hideInputMethod() {
	    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
	    if (imm != null) {
	        imm.hideSoftInputFromWindow(etStat_1.getWindowToken(), 0);
	    }
	}
	
		//Запись предидущих показаний, расхода, суммы в массив
		public void dataPrepare (Cursor c, int numberOfTarif) {
			for (int i = 1; i <= numberOfTarif; i++) {
				String stat_1 = c.getString(c.getColumnIndex("stat_1"));
				String nameColLastStatement = "laststat_" + String.valueOf(i);
				String nameColStatement = "stat_" + String.valueOf(i);
				//Определяем предидущие показания счетчика и записываем в массив
				if (stat_1 == null) {
					data.add(Double.valueOf(c.getString(c.getColumnIndex(nameColLastStatement))));		
				} else {
					data.add(Double.valueOf(c.getString(c.getColumnIndex(nameColStatement))));
				}
				//Расчитываем id EditText-a показаний счетчика и передаем в процедуру
				//расчета, также передаем номер позиции последних показаний в массиве data
				if (i == 1) calculate(i * 100, 1);
				if (i == 2) calculate(i * 100, 4);
				if (i == 3) calculate(i * 100, 7);
				if (i == 4) calculate(i * 100, 10);
			}
		}
		
		
		//Расчитываем разницу между текущими и предидущими показания счетчика 
		//и умножаем на тариф
		public void calculate(int etId, int lastPokaz) {
			double raznitsa, summa;
			EditText etStatement = (EditText)findViewById(etId);
			EditText etTarif = (EditText)findViewById(etId + 1);
			//Расчет расхода и суммы по счетчику
			raznitsa = Double.valueOf(etStatement.getText().toString()) - data.get(lastPokaz);
			summa = raznitsa * Double.valueOf(etTarif.getText().toString());
			//Добавляем в массив разницу показаний
			data.add(raznitsa);
			//Округляем сумму которая получилась по счетчику до 2-х знаков		
			Double roundSumma = roundMyData(summa, 2);
			//Добавляем в массив округленную сумму
			data.add(roundSumma);
		}
		
		//Округление
		public static double roundMyData(double Rval, int numberOfDigitsAfterDecimal) {
	        double p = (float)Math.pow(10, numberOfDigitsAfterDecimal);
	        Rval = Rval * p;
	        double tmp = Math.floor(Rval);
	        return (double)tmp / p;
	    }
			
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_data, menu);
		return true;
	}
	
	//Проверки заполнения необходимых граф данными
	public int check (EditText etStat_1, EditText etTarif_1, EditText etStat_2,
			EditText etTarif_2,	EditText etStat_3, EditText etTarif_3,
			EditText etStat_4, EditText etTarif_4){
		int tmp = 0;
	  	if (etStat_1 != null) {
	  		if (etStat_1.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите показания 1", Toast.LENGTH_SHORT).show();
	  			etStat_1.requestFocus();
	  			return tmp = 0;
	  		}
	  		if (etTarif_1.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите тариф 1", Toast.LENGTH_SHORT).show();
	  			etTarif_1.requestFocus();
	  			return tmp = 0;
	  		}
	  		tmp = 1;
	  	}
	  	if (etStat_2 != null) {
	  		if (etStat_2.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите показания 2", Toast.LENGTH_SHORT).show();
	  			etStat_2.requestFocus();
	  			return tmp = 0;
	  		}
	  		if (etTarif_2.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите тариф № 2", Toast.LENGTH_SHORT).show();
	  			etTarif_2.requestFocus();
	  			return tmp = 0;
	  		}
	  		tmp = 2;
	  	}
	  	if (etStat_3 != null) {
	  		if (etStat_3.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите показания 3", Toast.LENGTH_SHORT).show();
	  			etStat_3.requestFocus();
	  			return tmp = 0;
	  		}
	  		if (etTarif_3.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите тариф № 3", Toast.LENGTH_SHORT).show();
	  			etTarif_3.requestFocus();
	  			return tmp = 0;
	  		}
	  		tmp = 3;
	  	}
	  	if (etStat_4 != null) {
	  		if (etStat_4.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите показания 4", Toast.LENGTH_SHORT).show();
	  			etStat_4.requestFocus();
	  			return tmp = 0;
	  		}
	  		if (etTarif_4.getText().toString().compareTo("") == 0) {
	  			Toast.makeText(getApplicationContext(),
	  					"Введите тариф № 4", Toast.LENGTH_SHORT).show();
	  			etTarif_4.requestFocus();
	  			return tmp = 0;
	  		}
	  		tmp = 4;
	  	}
	  	return tmp;
	}	
	
	public void logCursor_1 (Cursor c) {
	    if (c != null) {
	        if (c.moveToFirst()) {
	          String str;
	          do {
	            str = "";
	            for (String cn : c.getColumnNames()) {
	              str = str.concat(cn + " = "
	                  + c.getString(c.getColumnIndex(cn)) + "; ");
	            }
	            Log.d(LOG_TAG, str);

	          } while (c.moveToNext());
	        }
	        //c.close();
	      } else
	        Log.d(LOG_TAG, "Cursor is null");
	}

	public void onToggleClick(View v) {
	      
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
		// TODO Auto-generated method stub
		if (isChecked) {
			cam = Camera.open();     
		    Parameters p = cam.getParameters();
		    p.setFlashMode(Parameters.FLASH_MODE_TORCH);
		    cam.setParameters(p);
		    cam.startPreview();
		}
		else {
			Camera.Parameters params = cam.getParameters();
			params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
			cam.setParameters(params);
			cam.release();
			cam = null;
	}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (cam != null) {
			Camera.Parameters params = cam.getParameters();
			params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
			cam.setParameters(params);
			cam.release();
			cam = null;
		}
		
	}
}
