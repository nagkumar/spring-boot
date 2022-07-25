package com.tejasoft.sboot.api.client.controllers;

import com.tejasoft.sboot.api.client.services.ClientService;
import com.tejasoft.sboot.api.utils.StringConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = StringConsts.CLIENT_PATH)
public final class ClientController
{
    private final ClientService clientService;

    @Autowired
    public ClientController(final ClientService aClientService)
    {
	clientService = aClientService;
    }

    @DeleteMapping(value = StringConsts.ID_PATH)
    public @ResponseBody ResponseEntity<Boolean> delete(@PathVariable(StringConsts.ID) Long aId)
    {
	return new ResponseEntity<>(clientService.delete(aId), HttpStatus.OK);
    }
}
