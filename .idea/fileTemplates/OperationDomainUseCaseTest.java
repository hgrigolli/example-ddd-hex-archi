#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

#set($lowerName = $Domain.substring(0,1).toLowerCase() + $Domain.substring(1))

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

class ${Operation}${Domain}UseCaseTest extends UseCaseTest {

    @InjectMocks
    private Default${Operation}${Domain}UseCase useCase;

    @Mock
    private ${Domain}Gateway ${lowerName}Gateway;
    
        @Override
    protected List<Object> getMocks() {
        return List.of(${lowerName}Gateway);
    }

}