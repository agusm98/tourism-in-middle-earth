package com.unlam.paradigms.tp;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Scanner;

public class Alfred {
    
    private Scanner inputScan;
    
    public Alfred() {
        this.inputScan = new Scanner(System.in);
    }
    
    public List<Ticket> offerAttractions(User usr) {
        List<Ticket> tickets = new ArrayList<Ticket>();
        Iterator<TourismOption> tourOptions = Manager.getOptions(usr); //Offer iterator

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
            
            String usrResp = inputScan.nextLine();
            if(usrResp.toUpperCase().contains("S")) {
                /*
                MANAGER JOB
                tourOption.reserve(usr.name/usr.id);
                usr.updateHours();
                usr.updateGold();
                Ticket ticket = new Ticket(usr, offer);
                */
                Ticket ticket = Manager.createTicket(usr, tourOption);
                tickets.add(ticket);
            }
        }
        return tickets;
    }
    
    public void showSchedule(List<Ticket> tickets){
        Integer totalHours = 0;
        Integer totalGold = 0;
        
        if(tickets.size() > 0){
            for(Integer i=0;i<tickets.size();++i) {
                totalHours += tickets.get(i).getDuration();
                totalGold += tickets.get(i).getTotalAmount();
                System.out.println("Excursion "+String.valueOf(i)+":/n");
                tickets.get(i).printDetail();
            }
            System.out.println("El tiempo total es: "+totalHours);
            System.out.println("El costo total es: "+totalGold);
        }
    }
    
}
