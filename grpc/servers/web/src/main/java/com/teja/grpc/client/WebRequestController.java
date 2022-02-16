package com.teja.grpc.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebRequestController
{
    @Autowired
    private WebRequestHandler webClientService;

    @RequestMapping("/")
    public String printMessage(@RequestParam(defaultValue = "Tano") String name)
    {
	return webClientService.sendMessage(name);
    }
}
