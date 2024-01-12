package dev.grigoll.example.dddhexarchi.domain.utils;

import br.com.docenela.admin.domain.Identifier;
import br.com.docenela.admin.domain.pagination.Pagination;
import br.com.docenela.admin.domain.pagination.SearchQuery;

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
