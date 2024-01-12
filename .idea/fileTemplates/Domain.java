#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

public class ${Domain} extends AggregateRoot<${Domain}ID> implements Cloneable {

    //TODO: Attributes here

    private ${Domain}(
        final ${Domain}ID anID
    //TODO: Attributes here
    ) {
        super(anID);
        //TODO: Attributes here
        selfValidate();
    }
    
    public static ${Domain} new${Domain}(
        //TODO: Attributes here
    ) {
        final var id = ${Domain}ID.unique();
        return new ${Domain}(
            id
            //TODO: Attributes here
        );
    }

    public static ${Domain} with(
            final ${Domain}ID anID
            ) {
        return new ${Domain}(anID);
    }
    
    @Override
    public void validate(ValidationHandler handler) {
        new ${Domain}Validator(this, handler).validate();
    }
    
    private void selfValidate() {
        this.validate(new ThrowsValidationHandler());
    }
    
    @Override
    public ${Domain} clone() {
        try {
            ${Domain} clone = (${Domain}) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            // In case there are any lists, a copy should be done here.
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    
    //TODO: Getters
}