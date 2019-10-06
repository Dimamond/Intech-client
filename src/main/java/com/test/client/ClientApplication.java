package com.test.client;


import com.test.client.client.ContentClient;
import com.test.client.client.UserClient;
import com.test.client.service.ContentService;
import com.test.client.service.UserService;
import com.test.client.view.*;

public class ClientApplication {
	public static void main(String[] args) {

		ContentClient contentClient = new ContentClient();
		UserClient userClient = new UserClient();
		ContentService contentService = new ContentService(contentClient);

		UserService userService = new UserService(userClient);

		MainMenuView mainMenuView = new MainMenuView(userService);
		ContentSectionView contentSectionView = new ContentSectionView(contentService, userService);
		PersonalAreaView personalAreaView = new PersonalAreaView(userService);
		ErrorView errorView = new ErrorView();

		Dialog dialog = new Dialog(mainMenuView, contentSectionView, personalAreaView, errorView);

		mainMenuView.setMediator(dialog);
		contentSectionView.setMediator(dialog);
		personalAreaView.setMediator(dialog);
		errorView.setMediator(dialog);

		dialog.initDialog();
	}
}
