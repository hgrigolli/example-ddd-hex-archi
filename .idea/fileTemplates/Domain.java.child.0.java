#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

#set($lowerName = $Domain.substring(0,1).toLowerCase() + $Domain.substring(1))

public class ${Domain}Validator extends Validator {

    private final ${Domain} ${lowerName};

    public ${Domain}Validator(final ${Domain} ${lowerName}, final ValidationHandler aHandler) {
        super(aHandler);
        this.${lowerName} = ${lowerName};
    }

    @Override
    public void validate() {

    }
}