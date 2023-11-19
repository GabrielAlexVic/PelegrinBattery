package com.example.batterylevel.domain.services;

public class BatteryService {

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


}
