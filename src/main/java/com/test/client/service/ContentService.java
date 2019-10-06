package com.test.client.service;


import com.test.client.client.ContentClient;
import com.test.client.common.SupplierIterator;
import com.test.client.dto.ContentDTO;
import com.test.client.common.ListSupplier;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class ContentService {


    private ContentClient contentClient;

    public ContentService(ContentClient contentClient) {
        this.contentClient = contentClient;
    }

    public Page<ContentDTO> getContent(int pageNum, int pageSize){
        Page<ContentDTO> page = null;
        try {
            page = contentClient.getContentPage(pageNum, pageSize);
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
                    Page<ContentDTO> content = getContent(pageNum, pageSize);
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
