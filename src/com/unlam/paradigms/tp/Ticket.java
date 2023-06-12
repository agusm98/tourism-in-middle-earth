package com.unlam.paradigms.tp;

public class Ticket {
    private User user;
    private TourismOption option;
    
    public Ticket(User user, TourismOption option) {
        this.user = user;
        this.option = option;
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
    
    public TourismOption getOption() {
        return option;
    }
    
    public void printDetail() {
        System.out.println("Usuario: "+user.getUserName());
        System.out.println("Compro: "+option.getName());
        System.out.println("Al precio de: "+option.getAmountToPay());
        System.out.println("Debera tener disponible: "+option.getDuration()+" horas.");
    }
}
