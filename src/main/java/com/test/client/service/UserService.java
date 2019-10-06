package com.test.client.service;



import com.test.client.client.UserClient;
import com.test.client.common.SupplierIterator;
import com.test.client.dto.ContentDTO;
import com.test.client.common.ListSupplier;

import org.springframework.data.domain.Page;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class UserService {

    private UserClient userClient;

    public UserService(UserClient userClient) {
        this.userClient = userClient;
    }

    public void authorization(String login) {
        try {
            userClient.authorization(login);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public void addUserContent(Long contentId) {
        try {
            userClient.addUserContent(contentId);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserContent(Long contentId) {
        try {
            userClient.deleteUserContent(contentId);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public Page<ContentDTO> getUserContent(int pageNum, int pageSize){
        Page<ContentDTO> page = null;
        try {
            page = userClient.getContentPage(pageNum, pageSize);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return page;
    }

    public Iterator<ContentDTO> getContentIterator() {

        return new SupplierIterator<>(new ListSupplier<ContentDTO>() {
            private final int pageSize = 10;
            private int pageNum = 0;
            private int totalElements = 0;
            private boolean isFinished = false;

            @Override
            public List<ContentDTO> getAsList() {

                List<ContentDTO> list = new ArrayList<>();

                if (!isFinished) {
                    Page<ContentDTO> content = getUserContent(pageNum, pageSize);
                    if (content != null) {
                        List<ContentDTO> listContent = content.getContent();
                        if (listContent.size() < pageSize) isFinished = true;
                        else pageNum += 1;
                        totalElements += listContent.size();
                        list.addAll(listContent);
                    }
                }

                return list;
            }
        });




    }



}
