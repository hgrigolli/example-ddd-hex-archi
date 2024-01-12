#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

public abstract class Delete${Domain}UseCase extends UnitUseCase<String> {
}