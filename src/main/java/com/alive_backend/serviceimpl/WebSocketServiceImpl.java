package com.alive_backend.serviceimpl;

import com.alive_backend.service.WebSocketService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WebSocketServiceImpl implements WebSocketService {
    private Map<String, String> nameMap;
    public void WebSocketService() {
        this.nameMap = new HashMap<>();
    }

    public void addUser(String username, String name) {
        nameMap.put(username, name);
    }

    public void removeUser(String username) {
        nameMap.remove(username);
    }

    public Map<String, String> getNameMap() {
        return nameMap;
    }
}
