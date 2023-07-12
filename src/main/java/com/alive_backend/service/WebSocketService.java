package com.alive_backend.service;

import java.util.Map;

public interface WebSocketService {
    void addUser(String username, String name);
    void removeUser(String username) ;
    Map<String, String> getNameMap();
}
