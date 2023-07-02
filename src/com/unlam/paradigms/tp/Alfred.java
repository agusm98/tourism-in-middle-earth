package com.unlam.paradigms.tp;

import java.util.Iterator;
import java.util.Scanner;

public class Alfred {
    
    private Scanner inputScan;
    
    public Alfred() {
        this.inputScan = new Scanner(System.in);
    }
    
    public Ticket offerAttractions(User usr) throws Exception {
        Manager manager = Manager.getInstance();
        Ticket userTicket = new Ticket(usr);
        
        Iterator<TourismOption> tourOptions = manager.getOptions(usr); //Offer iterator

        while(tourOptions.hasNext()) {
            TourismOption tourOption = tourOptions.next();
            tourOption.showDescription();
            
            if(getResponse().equals("S")) {
                manager.checkout(userTicket, tourOption);
            }
        }
        return userTicket;
    }
    
    public void showSchedule(Ticket userTicket){
        System.out.println(userTicket.toString());
    }
    
    private String getResponse(){
        String usrResp = inputScan.nextLine().toUpperCase();
        while(!usrResp.equals("S") && !usrResp.equals("N")) {
            System.out.println("Respuesta invalida, solo se acepta S o N: ");
            usrResp = inputScan.nextLine().toUpperCase();
        }
        return usrResp;
    }
    
    @Override
    protected void finalize() {
    	this.inputScan.close();
    }
}
