/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.communication;

import rs.ac.bg.fon.ps.PSCommon.communication.Operation;
import rs.ac.bg.fon.ps.PSCommon.communication.Response;
import rs.ac.bg.fon.ps.PSCommon.communication.Receiver;
import rs.ac.bg.fon.ps.PSCommon.communication.Request;
import rs.ac.bg.fon.ps.PSCommon.communication.Sender;
import java.net.Socket;
import java.util.List;
import rs.ac.bg.fon.ps.PSCommon.domain.Karta;
import rs.ac.bg.fon.ps.PSCommon.domain.Klijent;
import rs.ac.bg.fon.ps.PSCommon.domain.PlanGledanja;
import rs.ac.bg.fon.ps.PSCommon.domain.Predstava;
import rs.ac.bg.fon.ps.PSCommon.domain.Rezervacija;
import rs.ac.bg.fon.ps.PSCommon.domain.StavkaRezervacije;
import rs.ac.bg.fon.ps.PSCommon.domain.User;


/**
 * Klasa za komunikaciju koja ima i Sender i Receiver
 * znaci i salje i prima podatke od servera
 * uspostavlja komunikaciju sa serverom
 * i ima login metodu za korisnika
 * sve sistemske operacije koje su na raspolaganju korisniku su u njoj
 * @author Andjy
 */
public class Communication {
    /**
     * socket objekat klase Socket koji sluzi za uspostavljanje komunikacije sa serverom
     */
    Socket socket;
    /**
     * sender objekat klase Sender koja sluzi za slanje objekata
     */
    Sender sender;
    /**
     * receiver objekat klase Receiver koji sluzi za primanje objekata
     */
    Receiver receiver;
    /**
     * instanca date klase
     */
    private static Communication instance;
    /**
     * Konstruktor klase bez parametara
     * setuje vrednost za socket na localhost i port 9000
     * setuje novi sender i receiver i prosledjuje im socket
     * @throws Exception ukoliko nije moguce inicijalizovati nove objekte
     */
    private Communication() throws Exception{
        socket=new Socket("127.0.0.1", 9000);
        sender=new Sender(socket);
        receiver=new Receiver(socket);
    }
    /**
     * Metoda za vracanje instance date klase ukoliko nije inicijalizovana pravi novu
     * @return instance odnosno instancu date klase
     * @throws Exception ukoliko nije moguce napraviti novu instancu 
     */
    public static Communication getInstance() throws Exception{
        if(instance==null){
            instance=new Communication();
        }
        return instance;
    }
    /**
     * Metoda za prijavu korisnika
     * setuju se prosledjeni parametri salje se zahtev servisu za logovanje sa napravljenim userom
     * uzima se odgovor i gleda da li ima exception ukoliko nema prijavljuje se user
     * ukoliko ima ispisuje se greska
     * @param username parametar koji predstavlja korisnicko ime
     * @param password predstavlja lozinku korisnika
     * @return vraca usera
     * @throws Exception baca gresku do koje je doslo prilikom logovanja
     */
    public User login(String username, String password) throws Exception {
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        Request request=new Request(Operation.LOGIN, user);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (User)response.getResult();
        }else{
            throw response.getException();
        }
    }
 /**
     * Metoda za vracanje svih klijenta
     * salje se zahtev servisu za get all clients sa napravljenim klijentom
     * uzima se odgovor i gleda da li ima exception ukoliko nema vraca se lista klijenata
     * ukoliko ima ispisuje se greska
     * @return vraca listu klijenata iz baze
     * @throws Exception baca gresku do koje je doslo prilikom vracanja liste
     */
    public List<Klijent> getAllCLients() throws Exception{
        Request request=new Request(Operation.GET_ALL_CLIENTS, new Klijent());
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<Klijent>)response.getResult();
        }else{
            throw response.getException();
        }
    }
    /**
     * Metoda za vracanje svih stavki rezervacije
     * salje se zahtev servisu za get all items reservations sa napravljenom stavkom
     * uzima se odgovor i gleda da li ima exception ukoliko nema vraca se lista stavki rezervacija
     * ukoliko ima ispisuje se greska
     * @return vraca listu stavki rezervacija iz baze
     * @throws Exception baca gresku do koje je doslo prilikom vracanja liste
     */
        public List<StavkaRezervacije> getAllItemReservations() throws Exception{
        Request request=new Request(Operation.GET_ALL_ITEMS_RESERVATIONS, new StavkaRezervacije());
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<StavkaRezervacije>)response.getResult();
        }else{
            throw response.getException();
        }
    }
     /**
     * Metoda za vracanje svih rezervacije
     * salje se zahtev servisu za get all reservations sa napravljenom rezervacijom
     * uzima se odgovor i gleda da li ima exception ukoliko nema vraca se lista rezervacija
     * ukoliko ima ispisuje se greska
     * @return vraca listu  rezervacija iz baze
     * @throws Exception baca gresku do koje je doslo prilikom vracanja liste
     */
         public List<Rezervacija> getAllReservations() throws Exception{
        Request request=new Request(Operation.GET_ALL_RESERVATIONS, new Rezervacija());
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<Rezervacija>)response.getResult();
        }else{
            throw response.getException();
        }
    }
     /**
     * Metoda za vracanje svih predstavi
     * salje se zahtev servisu za get all shows sa napravljenom predstavom
     * uzima se odgovor i gleda da li ima exception ukoliko nema vraca se lista predstavi
     * ukoliko ima ispisuje se greska
     * @return vraca listu  predstavi iz baze
     * @throws Exception baca gresku do koje je doslo prilikom vracanja liste
     */
        public List<Predstava> getAllShows() throws Exception{
        Request request=new Request(Operation.GET_ALL_SHOWS, new Predstava());
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<Predstava>)response.getResult();
        }else{
            throw response.getException();
        }
    }
 /**
     * Metoda za dodavanje predstave
     * salje se zahtev servisu za add predstava sa predstavom za dodavanje
     * uzima se odgovor i gleda da li ima exception ukoliko nema ne vraca se nista
     * ukoliko ima ispisuje se greska
     * @param predstava je predstava koju zelimo da dodamo
     * @throws Exception baca gresku do koje je doslo prilikom dodavanja
     */
    public void addPredstava(Predstava predstava) throws Exception {
        Request request=new Request(Operation.ADD_PREDSTAVA, predstava);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }
     /**
     * Metoda za dodavanje klijenta
     * salje se zahtev servisu za add klijent sa klijentom za dodavanje
     * uzima se odgovor i gleda da li ima exception ukoliko nema ne vraca se nista
     * ukoliko ima ispisuje se greska
     * @param klijent je klijent koju zelimo da dodamo
     * @throws Exception baca gresku do koje je doslo prilikom dodavanja
     */
        public void addKlijent(Klijent klijent) throws Exception {
        Request request=new Request(Operation.ADD_KLIJENT, klijent);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }
     /**
     * Metoda za dodavanje karte
     * salje se zahtev servisu za add karta sa kartom za dodavanje
     * uzima se odgovor i gleda da li ima exception ukoliko nema ne vraca se nista
     * ukoliko ima ispisuje se greska
     * @param karta je karta koju zelimo da dodamo
     * @throws Exception baca gresku do koje je doslo prilikom dodavanja
     */
        public void addKarta(Karta karta) throws Exception {
        Request request=new Request(Operation.ADD_KARTA, karta);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }
     /**
     * Metoda za vracanje svih predstavi
     * salje se zahtev servisu za get all shows sa napravljenom predstavom
     * uzima se odgovor i gleda da li ima exception ukoliko nema vraca se lista predstavi
     * ukoliko ima ispisuje se greska
     * @return vraca listu  predstavi iz baze
     * @throws Exception baca gresku do koje je doslo prilikom vracanja liste
     */
    public List<Predstava> getAllProducts() throws Exception {
        Request request=new Request(Operation.GET_ALL_PREDSTAVE, new Predstava());
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<Predstava>)response.getResult();
        }else{
            System.out.println("Ispisi mi da ne zelis da mi vratis listu");
            throw response.getException();
        }
    }
    /**
     * Metoda za vracanje svih klijenta
     * salje se zahtev servisu za get all clients sa napravljenim klijentom
     * uzima se odgovor i gleda da li ima exception ukoliko nema vraca se lista klijenata
     * ukoliko ima ispisuje se greska
     * @return vraca listu klijenata iz baze
     * @throws Exception baca gresku do koje je doslo prilikom vracanja liste
     */
       public List<Klijent> getAllClients() throws Exception {
        Request request=new Request(Operation.GET_ALL_CLIENTS, new Klijent());
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<Klijent>)response.getResult();
        }else{
            throw response.getException();
        }
    }
 /**
     * Metoda za brisanje predstave
     * salje se zahtev servisu za delete predstava sa predstavom za brisanje
     * uzima se odgovor i gleda da li ima exception ukoliko nema ne vraca se nista
     * ukoliko ima ispisuje se greska
     * @param product je predstava koju zelimo da obrisemo
     * @throws Exception baca gresku do koje je doslo prilikom brisanja
     */
    public void deleteShow(Predstava product) throws Exception {
        Request request=new Request(Operation.DELETE_PREDSTAVA, product);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }
  /**
     * Metoda za auzuriranje rezervacije
     * salje se zahtev servisu za update rezervacija sa rezervacijom za menjanje
     * uzima se odgovor i gleda da li ima exception ukoliko nema ne vraca se nista
     * ukoliko ima ispisuje se greska
     * @param product je rezervacija koju zelimo da menjamo
     * @throws Exception baca gresku do koje je doslo prilikom menjanja
     */
    public void editProduct(Rezervacija product) throws Exception {
        Request request=new Request(Operation.EDIT_PRODUCT, product);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }
  /**
     * Metoda za dodavanje rezervacije
     * salje se zahtev servisu za add rezervacija sa rezervacijom za dodavanje
     * uzima se odgovor i gleda da li ima exception ukoliko nema ne vraca se nista
     * ukoliko ima ispisuje se greska
     * @param invoice je rezervacija koju zelimo da dodamo
     * @throws Exception baca gresku do koje je doslo prilikom dodavanja
     */
    public void addRezervacija(Rezervacija invoice) throws Exception {
        Request request=new Request(Operation.ADD_REZERVACIJA, invoice);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
           
             Rezervacija newInvoice=new Rezervacija();
             newInvoice.setId((Integer)response.getResult());
            invoice.setId(newInvoice.getRezervacijaId());
        }else{
            throw response.getException();
        }
    }
      /**
     * Metoda za auzuriranje  rezervacije
     * salje se zahtev servisu za update rezervacija sa rezervacijom za menjanje
     * uzima se odgovor i gleda da li ima exception ukoliko nema ne vraca se nista
     * ukoliko ima ispisuje se greska
     * @param invoice je rezervacija koju zelimo da menjamo
     * @throws Exception baca gresku do koje je doslo prilikom menjanja
     */
        public void updateRezervacija(Rezervacija invoice) throws Exception {
       Request request=new Request(Operation.UPDATE_REZERVACIJA, invoice);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }
 /**
     * Metoda za brisanje rezervacije
     * salje se zahtev servisu za delete rezervacija sa rezervacijom za brisanje
     * uzima se odgovor i gleda da li ima exception ukoliko nema ne vraca se nista
     * ukoliko ima ispisuje se greska
     * @param reserve je rezervacija koju zelimo da obrisemo
     * @throws Exception baca gresku do koje je doslo prilikom brisanja
     */
    public void deleteReserve(Rezervacija reserve) throws Exception {
        Request request=new Request(Operation.DELETE_REZERVACIJA, reserve);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }
   /**
     * Metoda za vracanje svih karata
     * salje se zahtev servisu za get all tickets sa napravljenom karatom
     * uzima se odgovor i gleda da li ima exception ukoliko nema vraca se lista karata
     * ukoliko ima ispisuje se greska
     * @return vraca listu  karata iz baze
     * @throws Exception baca gresku do koje je doslo prilikom vracanja liste
     */
    public List<Karta> getAllTickets() throws Exception {
        Request request=new Request(Operation.GET_ALL_TICKETS, new Karta());
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<Karta>)response.getResult();
        }else{
            throw response.getException();
        }
    }
  /**
     * Metoda za brisanje karte
     * salje se zahtev servisu za delete karta sa kartom za brisanje
     * uzima se odgovor i gleda da li ima exception ukoliko nema ne vraca se nista
     * ukoliko ima ispisuje se greska
     * @param pr je karta koju zelimo da obrisemo
     * @throws Exception baca gresku do koje je doslo prilikom brisanja
     */
    public void deleteTicket(Karta pr) throws Exception {
        Request request=new Request(Operation.DELETE_KARTA, pr);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }
     /**
     * Metoda za brisanje  stavke rezervacije
     * salje se zahtev servisu za delete stavka sa zeljenim stavkom za brisanje
     * uzima se odgovor i gleda da li ima exception ukoliko nema ne vraca se nista
     * ukoliko ima ispisuje se greska
     * @param st je stavka rezervacije koji zelimo da obrisemo
     * @throws Exception baca gresku do koje je doslo prilikom brisanja
     */
      public boolean deleteStavka(StavkaRezervacije st) throws Exception {
        Request request=new Request(Operation.DELETE_STAVKA, st);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (boolean)response.getResult();
        }else{
            throw response.getException();
        }
    }
  /**
     * Metoda za dodavanje plana gledanja
     * salje se zahtev servisu za add plan sa planom za dodavanje
     * uzima se odgovor i gleda da li ima exception ukoliko nema ne vraca se nista
     * ukoliko ima ispisuje se greska
     * @param pl je plan gledanja koju zelimo da dodamo
     * @throws Exception baca gresku do koje je doslo prilikom dodavanja
     */
    public void savePlan(PlanGledanja pl) throws Exception {
         Request request=new Request(Operation.ADD_PLAN, pl);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
           
        }else{
            throw response.getException();
        }
    }
  /**
     * Metoda za dodavanje stavke rezervacije
     * salje se zahtev servisu za add stavka sa stavkom za dodavanje
     * uzima se odgovor i gleda da li ima exception ukoliko nema ne vraca se nista
     * ukoliko ima ispisuje se greska
     * @param st je stavka koju zelimo da dodamo
     * @throws Exception baca gresku do koje je doslo prilikom dodavanja
     */
    public Object addStavke(StavkaRezervacije st) throws Exception {
         Request request=new Request(Operation.ADD_STAVKA, st);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
           return (boolean)response.getResult();
         
        }else{
            throw response.getException();
        }
    }
  /**
     * Metoda za auzuriranje  stavke
     * salje se zahtev servisu za update stavka sa stavkom za menjanje
     * uzima se odgovor i gleda da li ima exception ukoliko nema vraca se true ili false
     * ukoliko ima ispisuje se greska
     * @param zaIzmenu je stavka koju zelimo da menjamo
     * @return objekat klase boolean znaci vraca true ili false
     * @throws Exception baca gresku do koje je doslo prilikom menjanja
     */
    public Object updateRezervacija(StavkaRezervacije zaIzmenu) throws Exception {
        Request request=new Request(Operation.UPDATE_STAVKA, zaIzmenu);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (boolean)response.getResult();
        }else{
            throw response.getException();
        }
    }
      /**
     * Metoda za brisanje  plana
     * salje se zahtev servisu za delete plan sa zeljenim planom za brisanje
     * uzima se odgovor i gleda da li ima exception ukoliko nema ne vraca se nista
     * ukoliko ima ispisuje se greska
     * @param pl je plan gledanja koji zelimo da obrisemo
     * @throws Exception baca gresku do koje je doslo prilikom brisanja
     */
      public void deletePlan(PlanGledanja pl) throws Exception {
        Request request=new Request(Operation.DELETE_PLAN, pl);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
      }
   /**
     * Metoda za vracanje svih planova
     * salje se zahtev servisu za get all plan sa napravljenim planom
     * uzima se odgovor i gleda da li ima exception ukoliko nema vraca se lista planova
     * ukoliko ima ispisuje se greska
     * @return vraca listu  planova iz baze
     * @throws Exception baca gresku do koje je doslo prilikom vracanja liste
     */
    public List<PlanGledanja> getAllPlan() throws Exception {
        Request request=new Request(Operation.GET_ALL_PLAN, new PlanGledanja());
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<PlanGledanja>)response.getResult();
        }else{
            throw response.getException();
        }
    }

  
    

  

    


    
    
    
}
