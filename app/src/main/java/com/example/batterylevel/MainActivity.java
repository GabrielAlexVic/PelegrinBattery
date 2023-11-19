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
import com.example.batterylevel.models.Battery;

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
                    batteryLevel.setText("Nível: " + battery.getLevel() + "%");
                    batteryHealth.setText("Saude da bateria: " + checkHealth(battery.getHealth()));
                    batteryStatus.setText("Estado da bateria: " + chargerStatus(battery.getStatus()));
                    chargerConnection.setText("Conector de Energia: " + checkConector(battery.getConnection()));
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

    public String checkHealth(int health) {
        switch(health) {
            case 1:
                return "Unknown";
            case 2:
                return "Good";
            case 3:
                return "Overheated";
            case 4:
                return "Dead";
            case 5:
                return "Overvoltage";
            case 6:
                return "Failed";
            default:
                return "Unknown";
        }
    }

    public String chargerStatus(int status) {
        switch(status) {
            case 1:
                return "Unknown";
            case 2:
                return "Charging";
            case 3:
                return "Discharging";
            case 4:
                return "Not Charging";
            case 5:
                return "Full";
            default:
                return "Unknown";
        }
    }

    public String checkConector(int connectorStatus) {
        if(connectorStatus == 0)
            return "Nenhum";
        else
            return "AC Charger";
    }
    
}