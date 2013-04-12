package com.example.bindservice;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private BindService service;
	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			// TODO Auto-generated method stub
			service = ((BindService.ExamBinder)binder).getService();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			service = null;
			
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void mOnClick(View view) {
		switch(view.getId()) {
		case R.id.button1 :					//StartService버튼을 눌렀을 경우
			onStartService(view);
			break;
		case R.id.button2 :					//StopService버튼을 눌렀을 경우
			onStopService(view);
			break;
		case R.id.button3 :					//BindService버튼을 눌렀을 경우
			onGetMessage(view);
			break;
		}
	}
	
	public void onStartService(View view) {		//서비스  바인드 실행
		if(service == null) {
			Intent intent = new Intent(this,BindService.class);
			bindService(intent,mConnection,Context.BIND_AUTO_CREATE);
		}
	}
	
	public void onStopService(View view) {		//서비스 언바인드
		Intent intent = new Intent(this,BindService.class);
		unbindService(mConnection);
	}
	
	public void onGetMessage(View view) {			//액티비티에 없는 서비스 함수를 호출함
		if(service != null) {
			String message = service.getMessage();
			Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
		}
	}
}
