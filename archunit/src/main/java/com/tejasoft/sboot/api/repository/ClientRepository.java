package com.tejasoft.sboot.api.repository;

import com.tejasoft.sboot.api.model.Client;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long>
{
}
