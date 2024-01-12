#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import java.util.Objects;
import java.util.UUID;

public class ${Domain}ID extends Identifier {

    private String value;
    
    private ${Domain}ID(String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }
    
    public static ${Domain}ID unique() {
        return new ${Domain}ID(UUID.randomUUID().toString().toLowerCase());
    }

    public static ${Domain}ID from(final String anID) {
        return new ${Domain}ID(anID);
    }

    public static ${Domain}ID from(final UUID anID) {
        return new ${Domain}ID((anID.toString().toLowerCase()));
    }
    
    @Override
    public String getValue() {
        return value;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ${Domain}ID object = (${Domain}ID) o;
        return Objects.equals(value, object.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    

}