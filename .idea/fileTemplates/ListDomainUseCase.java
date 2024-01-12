#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

public abstract class List${Domain}UseCase
        extends UseCase<SearchQuery, Pagination<${Domain}ListOutput>> {
}
