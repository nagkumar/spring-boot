package com.tejasoft.sboot.archunit.repository;

import com.tejasoft.sboot.archunit.model.Client;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long>
{
}
