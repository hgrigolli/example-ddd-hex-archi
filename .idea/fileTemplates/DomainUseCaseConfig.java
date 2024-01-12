#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")
#set($lowerName = $Domain.substring(0,1).toLowerCase() + $Domain.substring(1))

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ${Domain}UseCaseConfig {

    private final ${Domain}Gateway ${lowerName}Gateway;

    public ${Domain}UseCaseConfig(final ${Domain}Gateway ${lowerName}Gateway) {
        this.${lowerName}Gateway = ${lowerName}Gateway;
    }

    @Bean
    public Create${Domain}UseCase create${Domain}UseCase() {
        return new DefaultCreate${Domain}UseCase(${lowerName}Gateway);
    }

    @Bean
    public Update${Domain}UseCase update${Domain}UseCase() {
        return new DefaultUpdate${Domain}UseCase(${lowerName}Gateway);
    }

    @Bean
    public Get${Domain}ByIDUseCase get${Domain}ByIDUseCase() {
        return new DefaultGet${Domain}ByIDUseCase(${lowerName}Gateway);
    }

    @Bean
    public List${Domain}UseCase list${Domain}UseCase() {
        return new DefaultList${Domain}UseCase(${lowerName}Gateway);
    }

    @Bean
    public Delete${Domain}UseCase delete${Domain}UseCase() {
        return new DefaultDelete${Domain}UseCase(${lowerName}Gateway);
    }

}