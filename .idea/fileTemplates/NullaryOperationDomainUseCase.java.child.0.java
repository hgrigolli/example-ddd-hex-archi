#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

#set($lowerName = $Domain.substring(0,1).toLowerCase() + $Domain.substring(1))

public class Default${Operation}${Domain}UseCase extends ${Operation}${Domain}UseCase {

    private final ${Domain}Gateway ${lowerName}Gateway;
    
    public Default${Operation}${Domain}UseCase(final ${Domain}Gateway a${Domain}Gateway) {
        this.${lowerName}Gateway = Objects.requireNonNull(a${Domain}Gateway);
    }
    
    @Override
    public ${OutputType} execute() {
        //TODO: implement execute method
    }
    
}
