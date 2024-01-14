package dev.grigolli.example.dddhexarchi.application;

public abstract class UnitUseCase<IN> implements NotFoundable {

    public abstract void execute(IN anIn);

}
