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
    
    public List<Ticket> offerAttractions(User usr) throws IOException {
        List<Ticket> tickets = new ArrayList<Ticket>();
        Manager manager = Manager.getInstance();
        
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
                Ticket ticket = manager.createTicket(usr, tourOption);
                tickets.add(ticket);
            }
        }
        return tickets;
    }
    
    public void showSchedule(List<Ticket> tickets){
        Double totalHours = .0;
        Double totalGold = .0;
        
        if(tickets.size() > 0){
            for(Integer i=0;i<tickets.size();++i) {
                totalHours += tickets.get(i).getOption().getDuration();
                totalGold += tickets.get(i).getOption().getAmountToPay();
                System.out.println("Excursion "+String.valueOf(i)+":");
                tickets.get(i).printDetail();
            }
            System.out.println("El tiempo total es: "+totalHours);
            System.out.println("El costo total es: "+totalGold);
        }
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
