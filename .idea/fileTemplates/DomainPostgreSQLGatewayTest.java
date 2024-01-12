#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

#set($lowerDomain = $Domain.substring(0,1).toLowerCase() + $Domain.substring(1))

import org.springframework.beans.factory.annotation.Autowired;

@PostgreSQLGatewayTest
public class ${Domain}PostgreSQLGatewayTest {

    @Autowired
    private ${Domain}PostgreSQLGateway ${lowerDomain}Gateway;

    @Autowired
    private ${Domain}Repository ${lowerDomain}Repository;

    @Test
    public void testInjectedDependencies() {
        Assertions.assertNotNull(${lowerDomain}Gateway);
        Assertions.assertNotNull(${lowerDomain}Repository);
    }
	
}