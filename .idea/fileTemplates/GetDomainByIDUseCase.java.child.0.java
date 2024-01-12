#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

public record Get${Domain}ByIDOutput(
        ${Domain}ID id
) {

   public static Get${Domain}ByIDOutput from(final ${Domain}ID anId) {
        return new Get${Domain}ByIDOutput(anId);
    }
    
    public static Get${Domain}ByIDOutput from(final ${Domain} a${Domain}) {
        return new Get${Domain}ByIDOutput(a${Domain}.getId());
    }

}