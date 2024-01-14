package dev.grigolli.example.dddhexarchi.application;

public abstract class NullaryUseCase<OUT> implements NotFoundable {

    public abstract OUT execute();

}
