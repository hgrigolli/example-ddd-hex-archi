package dev.grigolli.example.dddhexarchi.application.flight.retrieve.list;

import dev.grigolli.example.dddhexarchi.application.UseCase;
import dev.grigolli.example.dddhexarchi.domain.pagination.Pagination;
import dev.grigolli.example.dddhexarchi.domain.pagination.SearchQuery;

public abstract class ListFlightUseCase
        extends UseCase<SearchQuery, Pagination<FlightListOutput>> {
}
