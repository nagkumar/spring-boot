package com.tejasoft.sboot.archunit.service;

import com.tejasoft.sboot.archunit.model.Client;
import com.tejasoft.sboot.archunit.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class ClientService
{
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(final ClientRepository aClientRepository)
    {
	clientRepository = aClientRepository;
    }

    public Boolean delete(final Long aId)
    {
	Optional<Client> client = clientRepository.findById(aId);

	if (client.isPresent())
	{
	    client.get().setActive(Boolean.FALSE);
	    clientRepository.save(client.get());
	    return Boolean.TRUE;
	}

	return Boolean.FALSE;
    }
}
