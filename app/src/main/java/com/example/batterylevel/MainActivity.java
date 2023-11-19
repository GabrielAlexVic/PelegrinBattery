package com.example.batterylevel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.batterylevel.application.handler.BatteryHandler;
import com.example.batterylevel.domain.models.Battery;
import com.example.batterylevel.domain.services.BatteryService;

public class MainActivity extends AppCompatActivity {

    private TextView batteryLevel;
    private TextView batteryHealth;
    private TextView batteryStatus;
    private TextView chargerConnection;

    private BroadcastReceiver batterylevelReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Battery battery = new Battery();
            battery.setLevel(intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0));
            battery.setHealth(intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0));
            battery.setStatus(intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0));
            battery.setConnection(intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0));

            BatteryHandler.updateBatteryStatus(new Runnable() {
                @Override
                public void run() {
                    BatteryService _batteryService = new BatteryService();
                    batteryLevel.setText("Nível: " + battery.getLevel() + "%");
                    batteryHealth.setText("Saúde da bateria: " + _batteryService.checkHealth(battery.getHealth()));
                    batteryStatus.setText("Estado da bateria: " + _batteryService.chargerStatus(battery.getStatus()));
                    chargerConnection.setText("Conector de Energia: " + _batteryService.checkConector(battery.getConnection()));
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        batteryLevel = findViewById(R.id.batteryLevel);
        batteryHealth = findViewById(R.id.batteryHealth);
        batteryStatus = findViewById(R.id.batteryStatus);
        chargerConnection = findViewById(R.id.chargerConnection);
        this.registerReceiver(this.batterylevelReciever, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
    
}