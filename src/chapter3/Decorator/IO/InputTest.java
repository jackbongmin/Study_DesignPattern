package chapter3.Decorator.IO;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class InputTest {

    public static void main(String[] args) throws IOException {
        int c;
        String text = "I'd LOVE to Decorate THIS Text with DECORATOR PATTERN!";

        System.out.println("=== 원본 문자열 ===");
        System.out.println(text);

        System.out.println("\n=== LowerCaseInputStream 데코레이터 적용 후 ===");
        try (InputStream in = new LowerCaseInputStream(
                new BufferedInputStream(
                        new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8))))) {
            while ((c = in.read()) >= 0) {
                System.out.print((char) c);
            }
            System.out.println();
        }
    }
}
