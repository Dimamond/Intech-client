package com.test.client.view;

import com.test.client.dto.ContentDTO;
import com.test.client.service.UserService;
import java.io.IOException;
import java.util.Iterator;

import static com.test.client.util.ConsoleHelper.println;
import static com.test.client.util.ConsoleHelper.read;


public class PersonalAreaView implements View {


    private Mediator mediator;
    private UserService userService;

    private Iterator<ContentDTO> contentIterator;
    private ContentDTO currentContent;

    public PersonalAreaView(UserService userService) {
        this.userService = userService;
    }

    public void menu(){
        loadContent();
        printMenu();
        printContent();
        mediator.notify(this, new Event("menu", readCode()));
    }

    public void nextContent(){
        if(contentIterator.hasNext())currentContent = contentIterator.next();
        printMenu();
        printContent();
        mediator.notify(this, new Event("content-next", readCode()));
    }


    public void deleteContent() {
        printMenu();
        printContent();
        println("          -----          ");
        if (currentContent != null) {
            userService.deleteUserContent(currentContent.getId());
            println("Контент " + currentContent.getName() + " удален из вашего личного кабинета");
        } else {
            println("Контент отсутствует");
        }

        mediator.notify(this, new Event("content-delete", readCode()));
    }

    private void printMenu(){
        println("          *****          ");
        println("Вы находитесь в меню управления услгуой и редактирования");
        println("персональной фонетики, нажмите:");
        println("1 - для перехода к следующей единице контента;");
        println("2 - для удаление текущей единицы контента;");
        println("* - для выхода в основное меню;");

    }

    private void printContent() {
        println("          #####          ");
        if (currentContent != null) {
            println("Контент:");
            println("id   - " + currentContent.getId());
            println("name - " + currentContent.getName());
        } else {
            println("Контент отсутствует");
        }
    }

    private void loadContent() {
        contentIterator = userService.getContentIterator();
        if(contentIterator.hasNext())currentContent = contentIterator.next();
    }

    private String readCode() {
        String code = "*";
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
