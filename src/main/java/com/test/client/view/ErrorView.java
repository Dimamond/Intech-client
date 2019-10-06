package com.test.client.view;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import static com.test.client.util.ConsoleHelper.println;



public class ErrorView implements View {


    private Mediator mediator;


    public void printError(String error){
        println("          XXXXX          ");
        println(error);
        mediator.notify(this, new Event("error"));
    }

    public Mediator getMediator() {
        return mediator;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

}
