package com.example.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class BindService extends Service{

	private final IBinder binder = new ExamBinder();			//액티비티에 제공할 바인더객체 생성
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "onBind", Toast.LENGTH_SHORT).show();
		return binder;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "onStartCommand", Toast.LENGTH_SHORT).show();
		return super.onStartCommand(intent, flags, startId);
	}
	
	public String getMessage() {			//서비스의 메서드
		return "Method Call(Service)";
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "onUnbind", Toast.LENGTH_SHORT).show();
		return super.onUnbind(intent);
	}

	public class ExamBinder extends Binder {		//바인더 객체
		BindService getService() {
			return BindService.this;				//서비스 불러옴
		}
	}

}
