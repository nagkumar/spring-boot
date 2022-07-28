package com.tejasoft.sboot.api.client.repositories;

import com.tejasoft.sboot.api.client.models.Client;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository
	extends PagingAndSortingRepository<Client, Long>
{
}
