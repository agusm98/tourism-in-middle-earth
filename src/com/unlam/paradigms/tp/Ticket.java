package com.unlam.paradigms.tp;

public class Ticket {
    private User user;
    private Offer offer;
    
    public Ticket(User user, Offer offer) {
        this.user = user;
        this.offer = offer;
        //this.setTotalAmount();
        //this.setTotalHours();
    }
    
    /*private void setTotalAmount() {
        totalAmount = 0;
        for(Offer offer : this.offers) {
            this.totalAmount += offer.getPrice();
        }
    }*/
    
    public User getUser() {
        return user;
    }
    
    public Offer getOffer() {
        return offer;
    }
    
    public void printDetail() {
        System.out.println("Usuario: "+user.getUserName());
        System.out.println("Compro: "+offer.getName());
        System.out.println("Al precio de: "+offer.getPrice());
        System.out.println("Debera tener disponible: "+offer.getHours()+" horas.");
    }
}
