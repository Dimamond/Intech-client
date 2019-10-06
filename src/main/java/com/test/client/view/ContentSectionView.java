package com.test.client.view;

import com.test.client.dto.ContentDTO;
import com.test.client.service.ContentService;
import com.test.client.service.UserService;

import java.io.IOException;
import java.util.Iterator;

import static com.test.client.util.ConsoleHelper.*;


public class ContentSectionView implements View {


    private Mediator mediator;
    private ContentService contentService;
    private UserService userService;

    private Iterator<ContentDTO> contentIterator;
    private ContentDTO currentContent;

    public ContentSectionView(ContentService contentService, UserService userService) {
        this.contentService = contentService;
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


    public void addContent() {
        printMenu();
        printContent();
        println("          +++++          ");
        if (currentContent != null) {
            userService.addUserContent(currentContent.getId());
            println("Контент " + currentContent.getName() + " добавлен в ваш личный кабинет");
        } else {
            println("Контент не может быть добавлен");
        }
        mediator.notify(this, new Event("content-add", readCode()));
    }

    private void loadContent() {
        contentIterator = contentService.getContentIterator();
        if(contentIterator.hasNext())currentContent = contentIterator.next();
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

    private void printMenu() {
        println("          *****          ");
        println("Нажмите:");
        println("1 - для перехода к следующей единице контента;");
        println("2 - для покупки текущего контента;");
        println("* - для выхода в основное меню;");
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
