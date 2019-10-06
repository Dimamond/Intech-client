package com.test.client.view;

import com.test.client.exception.MyServerException;
import org.springframework.beans.factory.annotation.Autowired;





public class Dialog implements Mediator {


    private MainMenuView mainMenuView;
    private ContentSectionView contentSectionView;
    private PersonalAreaView personalAreaView;
    private ErrorView errorView;

    public Dialog(MainMenuView mainMenuView, ContentSectionView contentSectionView, PersonalAreaView personalAreaView, ErrorView errorView) {
        this.mainMenuView = mainMenuView;
        this.contentSectionView = contentSectionView;
        this.personalAreaView = personalAreaView;
        this.errorView = errorView;
    }

    @Override
    public void notify(View view, Event event) {

        if (view instanceof MainMenuView)
            process((MainMenuView) view, event);
        else if (view instanceof ContentSectionView)
            process((ContentSectionView) view, event);
        else if (view instanceof PersonalAreaView)
            process((PersonalAreaView) view, event);
        else if (view instanceof ErrorView)
            process((ErrorView) view, event);
    }

    public void initDialog(){
        mainMenuView.authorization();


    }

    private void process(MainMenuView view, Event event){

        if(event.getType().equals("authorization")){

            if(event.getSuccess())
                mainMenuView.menu();
            else
               mainMenuView.authorization();




        }else if(event.getType().equals("menu")){

            switch (event.getCode()){

                case "1":
                    try {
                        contentSectionView.menu();
                    } catch (MyServerException e) {
                        if (e.getStatusCode() == 401)
                            mainMenuView.authorization();
                    }
                    break;
                case "2":
                    try {
                        personalAreaView.menu();
                    } catch (MyServerException e) {
                        if (e.getStatusCode() == 401)
                            mainMenuView.authorization();
                    }

                    break;
                default:
                    errorView.printError("Неверно введен код:" + event.getCode());
                    break;


            }

        }

    }


    private void process(ContentSectionView view, Event event){

        if(event.getType().equals("menu")){

            switch (event.getCode()){

                case "1":
                    try {
                        contentSectionView.nextContent();
                    } catch (MyServerException e) {
                        if (e.getStatusCode() == 401)
                            mainMenuView.authorization();
                    }
                    break;
                case "2":
                    try {
                        contentSectionView.addContent();
                    } catch (MyServerException e) {
                        if (e.getStatusCode() == 401)
                            mainMenuView.authorization();
                    };
                    break;
                case "*":
                    mainMenuView.menu();
                    break;
                default:
                    errorView.printError("Неверно введен код:" + event.getCode());
                    break;


            }


        }else if(event.getType().equals("content-next")){
            switch (event.getCode()){
                case "1":
                    try {
                        contentSectionView.nextContent();
                    } catch (MyServerException e) {
                        if (e.getStatusCode() == 401)
                            mainMenuView.authorization();
                    };
                    break;
                case "2":
                    try {
                        contentSectionView.addContent();
                    } catch (MyServerException e) {
                        if (e.getStatusCode() == 401)
                            mainMenuView.authorization();
                    };
                    break;
                case "*":
                    mainMenuView.menu();
                    break;
                default:
                    errorView.printError("Неверно введен код:" + event.getCode());
                    break;

            }




        }else if(event.getType().equals("content-add")){

            switch (event.getCode()){
                case "1":
                    try {
                        contentSectionView.nextContent();
                    } catch (MyServerException e) {
                        if (e.getStatusCode() == 401)
                            mainMenuView.authorization();
                    };
                    break;
                case "2":
                    try {
                        contentSectionView.addContent();
                    } catch (MyServerException e) {
                        if (e.getStatusCode() == 401)
                            mainMenuView.authorization();
                    };
                    break;
                case "*":
                    mainMenuView.menu();
                    break;
                default:
                    errorView.printError("Неверно введен код:" + event.getCode());
                    break;

            }


        }

    }
    private void process(PersonalAreaView view, Event event){

        if(event.getType().equals("menu")){

            switch (event.getCode()){

                case "1":
                    try {
                        personalAreaView.nextContent();
                    } catch (MyServerException e) {
                        if (e.getStatusCode() == 401)
                            mainMenuView.authorization();
                    }
                    break;
                case "2":
                    try {
                        personalAreaView.deleteContent();
                    } catch (MyServerException e) {
                        if (e.getStatusCode() == 401)
                            mainMenuView.authorization();
                    };
                    break;
                case "*":
                    mainMenuView.menu();
                    break;
                default:
                    errorView.printError("Неверно введен код:" + event.getCode());
                    break;


            }


        }else if(event.getType().equals("content-next")){
            switch (event.getCode()){
                case "1":
                    try {
                        personalAreaView.nextContent();
                    } catch (MyServerException e) {
                        if (e.getStatusCode() == 401)
                            mainMenuView.authorization();
                    };
                    break;
                case "2":
                    try {
                        personalAreaView.deleteContent();
                    } catch (MyServerException e) {
                        if (e.getStatusCode() == 401)
                            mainMenuView.authorization();
                    };
                    break;
                case "*":
                    mainMenuView.menu();
                    break;
                default:
                    errorView.printError("Неверно введен код:" + event.getCode());
                    break;

            }




        }else if(event.getType().equals("content-delete")){

            switch (event.getCode()){
                case "1":
                    try {
                        personalAreaView.nextContent();
                    } catch (MyServerException e) {
                        if (e.getStatusCode() == 401)
                            mainMenuView.authorization();
                    };
                    break;
                case "2":
                    try {
                        personalAreaView.deleteContent();
                    } catch (MyServerException e) {
                        if (e.getStatusCode() == 401)
                            mainMenuView.authorization();
                    };
                    break;
                case "*":
                    mainMenuView.menu();
                    break;
                default:
                    errorView.printError("Неверно введен код:" + event.getCode());
                    break;

            }


        }

    }


    private void process(ErrorView view, Event event){

        if(event.getType().equals("error")){
            mainMenuView.menu();
        }

    }


}
