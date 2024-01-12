#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

public record ${Domain}ListOutput(
        ${Domain}ID id
) {

    public static ${Domain}ListOutput from(final ${Domain} a${Domain}) {
        return new ${Domain}ListOutput(a${Domain}.getId());
    }

}