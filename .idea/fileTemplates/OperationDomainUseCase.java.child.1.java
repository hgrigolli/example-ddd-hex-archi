#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

public record ${Operation}${Domain}Output(
        String id
) {

    public static ${Operation}${Domain}Output from(final ${Domain} a${Domain}) {
        return new ${Operation}${Domain}Output(a${Domain}.getId().getValue());
    }

}