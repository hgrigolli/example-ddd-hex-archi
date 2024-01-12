#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

#set($lowerName = $Domain.substring(0,1).toLowerCase() + $Domain.substring(1))

import java.util.Objects;

public class DefaultGet${Domain}ByIDUseCase extends Get${Domain}ByIDUseCase {

    private final ${Domain}Gateway ${lowerName}Gateway;

    public DefaultGet${Domain}ByIDUseCase(final ${Domain}Gateway a${Domain}Gateway) {
        this.${lowerName}Gateway = Objects.requireNonNull(a${Domain}Gateway);
    }

    @Override
    public Get${Domain}ByIDOutput execute(String anID) {
        final var a${Domain}ID = ${Domain}ID.from(anID);

        return this.${lowerName}Gateway.findById(a${Domain}ID)
                .map(Get${Domain}ByIDOutput::from)
                .orElseThrow(notFound(a${Domain}ID));
                
    }
    
}