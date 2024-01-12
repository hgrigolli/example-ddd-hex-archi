package dev.grigolli.example.dddhexarchi.domain.utils;


import dev.grigolli.example.dddhexarchi.domain.Identifier;
import dev.grigolli.example.dddhexarchi.domain.pagination.Pagination;
import dev.grigolli.example.dddhexarchi.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface DomainGateway<Domain, DomainID extends Identifier> {

    Domain create(Domain aDomain);

    void deleteById(DomainID anID);

    Optional<Domain> findById(DomainID anID);

    Domain update(Domain aDomain);

    Pagination<Domain> findAll(SearchQuery aQuery);

    List<DomainID> existsByIds(Iterable<DomainID> ids);

}
