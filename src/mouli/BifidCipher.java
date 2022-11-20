package mouli;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/***
 * {@code BifidCipher} class takes the key string
 * and the message string and returns the encoded
 * and decoded Bifid cipher.
 * More info in the comments.
 *
 * Input format : Give two strings
 *                in separate lines
 *                as input.
 *
 * @author Minho
 * @since Feb 1, 2021
 */


public class BifidCipher {
    public static void main(String[] args) {

        final String title = "bifid cipher";
        printTitleBox(title);

        String key, msg;

        try (var sc = new Scanner(System.in)) {

            out.println("Enter the key.");
            key = sc.nextLine();

            out.println("Enter the message.");
            msg = sc.nextLine();

        } catch (Exception e) {

            out.println("Please enter key and message in separate lines.\nUsing default..");

            key = "SOLOLEARN";
            msg = "HELLOWORLD";
        }

        out.format("Key -> %s%nMessage -> %s%n%n", key, msg);

        String encode = new Encoder(key, msg).encodeBifid();
        out.println("Encoded Bifid -> " + encode);


        String decode = new Decoder(key, encode).decodeBifid();
        out.println("Decoded Bifid -> " + decode);
    }


    private static void printTitleBox(String plainText) {

        // Convert the plain text to
        // title case.
        String titleCase = Arrays.stream(plainText.split(" "))
                        .map(title -> // map first character
                                Character.toUpperCase(title.charAt(0))
                                +
                                title.substring(1).toLowerCase()
                        )
                        .collect(joining(" "));

        int len = titleCase.length();

        // Print the box pattern
        // with the title in middle.
        System.out.println(
                "#".repeat(len + 6) +
                "\n#" +
                " ".repeat(len + 4) +
                "#\n#  " +
                titleCase +
                "  #\n#" +
                " ".repeat(len + 4) +
                "#\n" +
                "#".repeat(len + 6) +
                "\n\n"
        );
    }
}











