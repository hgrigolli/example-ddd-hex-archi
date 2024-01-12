#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

#set($lowerName = $Domain.substring(0,1).toLowerCase() + $Domain.substring(1))

import java.util.Objects;

public class DefaultList${Domain}UseCase extends List${Domain}UseCase {

    private final ${Domain}Gateway ${lowerName}Gateway;

    public DefaultList${Domain}UseCase(final ${Domain}Gateway a${Domain}Gateway) {
        this.${lowerName}Gateway = Objects.requireNonNull(a${Domain}Gateway);
    }

    @Override
    public Pagination<${Domain}ListOutput> execute(SearchQuery anInput) {
        return this.${lowerName}Gateway.findAll(anInput)
                .map(${Domain}ListOutput::from);
    }
    
}