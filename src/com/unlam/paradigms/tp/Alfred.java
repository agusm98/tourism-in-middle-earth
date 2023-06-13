package com.unlam.paradigms.tp;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Iterator;
import java.util.Scanner;

public class Alfred {
    
    private Scanner inputScan;
    
    public Alfred() {
        this.inputScan = new Scanner(System.in);
    }
    
    public Ticket offerAttractions(User usr) throws IOException {
        Manager manager = Manager.getInstance();
        Ticket userTicket = new Ticket(usr);
        
        Iterator<TourismOption> tourOptions = manager.getOptions(usr); //Offer iterator

        while(tourOptions.hasNext()) {
            TourismOption tourOption = tourOptions.next();
            //SUG: tourOption.showDescription()
            if(tourOption.isOffer()) {
                System.out.println("Promocion");
                System.out.println("Atracciones incluidas: "+tourOption.getName());
                System.out.println("Duracion: "+String.valueOf(tourOption.getDuration()));
                System.out.println("Precio original: "+String.valueOf(tourOption.getBaseAmount()));
                System.out.println("Precio con descuento: "+String.valueOf(tourOption.getAmountToPay()));
            } else {
                System.out.println("Atraccion");
                System.out.println("Nombre: "+tourOption.getName());
                System.out.println("Duracion: "+String.valueOf(tourOption.getDuration()));
                System.out.println("Precio: "+String.valueOf(tourOption.getBaseAmount()));
            }
            
            if(getResponse().equals("S")) {
                userTicket.addTourOption(tourOption);
                //manager.createTicket(usr, tourOption);
                manager.update(usr, tourOption);
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
    
}
