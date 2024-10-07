package com.pruebaTecnica.init.services;


import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;


@Service
public class MenuService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void processMenuJson() throws IOException, java.io.IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = new ClassPathResource("menu.json").getInputStream();
        JsonNode menuNode = objectMapper.readTree(inputStream);

        processMenuRecursively(menuNode, null);
    }

    private void processMenuRecursively(JsonNode node, Integer parentId) {
        for (JsonNode item : node) {
            String icon = item.get("icon").asText();
            String key = item.get("key").asText();
            String link = item.get("link").asText();
            int order = item.get("order").asInt();
            String title = item.get("title").asText();
            String type = item.get("type").asText();

            jdbcTemplate.update("INSERT INTO menu (Icon, `Key`, Link, `Order`, Parent_id, Title, `Type`) VALUES (?, ?, ?, ?, ?, ?, ?)", 
                                icon, key, link, order, parentId, title, type);

            if (item.has("children")) {
                int newParentId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
                processMenuRecursively(item.get("children"), newParentId);
            }
        }
    }
}
