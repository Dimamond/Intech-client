package com.test.client.view;

import com.test.client.exception.MyServerException;
import com.test.client.service.UserService;

import javax.annotation.PostConstruct;

import java.io.IOException;

import static com.test.client.util.ConsoleHelper.*;


public class MainMenuView  implements View {

    private Mediator mediator;
    private UserService userService;

    public MainMenuView( UserService userService) {
        this.userService = userService;
    }

    public void authorization() {
        boolean isSuccess = false;
        println("          &&&&&          ");
        println("Введите ваш логин:");
        try {
            userService.authorization(readCode());
            isSuccess = true;
        } catch (MyServerException e) {
            if(e.getStatusCode() == 404){
                println("Введен не верный логин.");
            }
        }
        mediator.notify(this, new Event("authorization",isSuccess));
    }



    public void menu(){
        println("          &&&&&          ");
        println("Выбрать раздел:");
        println("1 - для просмотра контента;");
        println("2 - для входа в личный кабинет;");
        mediator.notify(this, new Event("menu", readCode()));

    }

    private String readCode() {
        String code = "";
        try {
            code = read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }



}
