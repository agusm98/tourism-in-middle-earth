package com.unlam.paradigms.tp;

import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;

public class Ticket {
    private User user;
    private List<TourismOption> tourOptions;
    
    public Ticket(User user) {
        this.user = user;
        this.tourOptions = new ArrayList<TourismOption>();
    }
    
    public void addTourOption(TourismOption tourOption) {
        this.tourOptions.add(tourOption);
    }
    
    public double getTotalAmount() {
        double totalAmount = 0;
        for(TourismOption tourOption : tourOptions) {
            totalAmount += tourOption.getAmountToPay();
        }
        
        return totalAmount;
    }
    
    public double getTotalDuration() {
        double totalDuration = 0;
        for(TourismOption tourOption : tourOptions) {
            totalDuration += tourOption.getDuration();
        }
        
        return totalDuration;
    }
    
    public User getUser() {
        return user;
    }
    
    public List<TourismOption> getOptions() {
        return tourOptions;
    }
    
    @Override
    public String toString() {
        StringBuilder detail = new StringBuilder();
        if(tourOptions.size()>0) {
            detail.append("Itinerario de usuario: "+user.getUserName()+"\n");
            for(TourismOption tourOption : tourOptions) {
                detail.append("Compro: "+tourOption.getName()+"\n");
                detail.append("Al precio de: "+tourOption.getAmountToPay()+"\n");
                detail.append("Debera tener disponible: "+tourOption.getDuration()+" horas.\n");
            }
            detail.append("\nEl tiempo total es: "+String.valueOf(getTotalDuration())+"\n\n");
            detail.append("El costo total es: "+String.valueOf(getTotalAmount())+"\n");
        } else {
        	detail.append("Usuario sin itinenario: "+user.getUserName()+"\n");
        }
        return detail.toString();
    }
}
