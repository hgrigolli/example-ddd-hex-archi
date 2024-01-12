package dev.grigoll.example.dddhexarchi.domain.utils;

public final class IDUtils {

        private IDUtils() {}

        public static String uuid() {
            return java.util.UUID.randomUUID().toString().toLowerCase().replace("-", "");
        }
}
