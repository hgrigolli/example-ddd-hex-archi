#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

#set($lowerName = $Domain.substring(0,1).toLowerCase() + $Domain.substring(1))

public class DefaultDelete${Domain}UseCase extends Delete${Domain}UseCase {

    private final ${Domain}Gateway ${lowerName}Gateway;
    
    public DefaultDelete${Domain}UseCase(final ${Domain}Gateway a${Domain}Gateway) {
        this.${lowerName}Gateway = Objects.requireNonNull(a${Domain}Gateway);
    }
    
    @Override
    public void execute(String anID) {
        this.${lowerName}Gateway.deleteById(${Domain}ID.from(anID));
    }
    
}
