#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

public record ${Operation}${Domain}Command(
        //TODO: Attributes here
) {

        public static ${Operation}${Domain}Command with(
                //TODO: Attributes here
        ) {
            return new ${Operation}${Domain}Command(
				//TODO: Attributes here
			);

        }
}