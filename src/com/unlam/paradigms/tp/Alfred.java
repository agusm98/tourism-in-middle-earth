package com.unlam.paradigms.tp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Alfred {
    
    private Scanner inputScan;
    
    public Alfred() {
        this.inputScan = new Scanner(System.in);
    }
    
    public ArrayList<Ticket> offerAttractions(User usr) {
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        Iterator<Offer> offers = Manager.getOffers(usr); //Offer iterator

        while(offers.hasNext()) {
            Offer offer = offers.next(); //if(offer.isValid(usr))
            //Mostrar data de oferta
            if(offer.isPackage()) {
                System.out.println("Promocion");
                System.out.println("Atracciones incluidas: "+offer.getName());
                System.out.println("Duracion: "+String.valueOf(offer.getHours()));
                System.out.println("Precio original: "+String.valueOf(offer.getPrice()));
                System.out.println("Precio con descuento: "
                +String.valueOf(offer.getPrice() - offer.getDiscount()));
            } else {
                System.out.println("Atraccion");
                System.out.println("Nombre: "+offer.getName());
                System.out.println("Duracion: "+String.valueOf(offer.getHours()));
                System.out.println("Precio: "+String.valueOf(offer.getPrice()));
            }
            
            String usrResp = inputScan.nextLine();
            if(usrResp.toUpperCase().contains("S")) {
                /*
                MANAGER JOB
                offer.reserve(usr.name/usr.id);
                usr.updateHours();
                usr.updateGold();
                Ticket ticket = new Ticket(usr, offer);
                */
                Ticket ticket = Manager.createTicket(usr, offer);
                tickets.add(ticket);
            }
        }
        return tickets;
    }
    
    public void showSchedule(ArrayList<Ticket> tickets){
        Integer totalHours = 0;
        Integer totalGold = 0;
        
        if(tickets.size() > 0){
            for(Integer i=0;i<tickets.size();++i) {
                totalHours += tickets.get(i).getHours();
                totalGold += tickets.get(i).getPrice();
                System.out.println("Excursion "+String.valueOf(i)+":/n");
                tickets.get(i).printDetail();
            }
            System.out.println("El tiempo total es: "+totalHours);
            System.out.println("El costo total es: "+totalGold);
        }
    }
    
}
