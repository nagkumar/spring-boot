package com.tejasoft.sboot.api.controllers;

import com.tejasoft.sboot.api.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/client")
public final class ClientController
{
    private final ClientService clientService;

    @Autowired
    public ClientController(final ClientService aClientService)
    {
	clientService = aClientService;
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody ResponseEntity<Boolean> delete(@PathVariable("id") Long aId)
    {
	return new ResponseEntity<>(clientService.delete(aId), HttpStatus.OK);
    }
}
