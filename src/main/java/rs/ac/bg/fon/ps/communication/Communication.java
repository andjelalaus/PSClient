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
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Karta;
import rs.ac.bg.fon.ps.domain.Predstava;
import rs.ac.bg.fon.ps.domain.PlanGledanja;
import rs.ac.bg.fon.ps.domain.Rezervacija;
import rs.ac.bg.fon.ps.domain.Klijent;
import rs.ac.bg.fon.ps.domain.StavkaRezervacije;
import rs.ac.bg.fon.ps.domain.User;

/**
 *
 * @author Cartman
 */
public class Communication {
    Socket socket;
    Sender sender;
    Receiver receiver;
    private static Communication instance;
    private Communication() throws Exception{
        socket=new Socket("127.0.0.1", 9000);
        sender=new Sender(socket);
        receiver=new Receiver(socket);
    }
    public static Communication getInstance() throws Exception{
        if(instance==null){
            instance=new Communication();
        }
        return instance;
    }
    
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

    public void addPredstava(Predstava predstava) throws Exception {
        Request request=new Request(Operation.ADD_PREDSTAVA, predstava);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }
        public void addKlijent(Klijent klijent) throws Exception {
        Request request=new Request(Operation.ADD_KLIJENT, klijent);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }
        public void addKarta(Karta karta) throws Exception {
        Request request=new Request(Operation.ADD_KARTA, karta);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }

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

    public void deleteShow(Predstava product) throws Exception {
        Request request=new Request(Operation.DELETE_PREDSTAVA, product);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }

    public void editProduct(Rezervacija product) throws Exception {
        Request request=new Request(Operation.EDIT_PRODUCT, product);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }

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
        public void updateRezervacija(Rezervacija invoice) throws Exception {
       Request request=new Request(Operation.UPDATE_REZERVACIJA, invoice);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }

    public void deleteReserve(Rezervacija reserve) throws Exception {
        Request request=new Request(Operation.DELETE_REZERVACIJA, reserve);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }

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

    public void deleteTicket(Karta pr) throws Exception {
        Request request=new Request(Operation.DELETE_KARTA, pr);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
    }
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

    public void savePlan(PlanGledanja pl) throws Exception {
         Request request=new Request(Operation.ADD_PLAN, pl);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
           
        }else{
            throw response.getException();
        }
    }

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
      public void deletePlan(PlanGledanja pl) throws Exception {
        Request request=new Request(Operation.DELETE_PLAN, pl);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            
        }else{
            throw response.getException();
        }
      }

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
