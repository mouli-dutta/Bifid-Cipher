package mouli;

import java.util.HashMap;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.iterate;
import static java.util.stream.Stream.of;

public interface IBifid {
    /**
     * This method converts the given
     * key and message to the corresponding
     * bifid string (encoded or decoded).
     *
     * @param key     Takes the key string.
     * @param message Takes the string to be
     *                encoded or decoded.
     * @return The corresponding bifid
     * (encoded or decoded) string.
     */
    default String bifid(String key, String message, UnaryOperator<String> convert) {

        // If the message is empty
        // then return an empty string.
        if (message.isEmpty()) return "";     

        // Replcae all the symbols which
        // are not letters.
        // convert the letters to uppercase.
        // replace J with I.
        // Do same for both key and message.
        key = key.replaceAll("\\W+", "")
                .toUpperCase()
                .replace("J", "I");

        message = message
                .replaceAll("\\W+", "")
                .toUpperCase()
                .replace("J", "I");

        // store the converted bifid string.

        // System.out.println(bifid);
        return decode(key, convert.apply(encode(key, message)));
    }

    /**
     * This method maps each letter
     * to the position of letter
     * in the polybius.
     *
     * @param key     Takes the key string.
     * @param message Takes the string to be encoded.
     * @return The position of each letter
     * corresponding to the polybius.
     */
    private String encode(String key, String message) {

        // Get the polybius depending
        // on key.
        String codes = getPolybius.apply(key);

        // create a HashMap to store
        // each letter and the
        // position of the corresponding letter
        // of the polybius.
        var map = IntStream.range(0, codes.length())
                .boxed()
                .collect(
                        Collectors.toMap(i -> "" + codes.charAt(i),
                                i -> i / 5 + "" + i % 5,
                                (a, b) -> b, HashMap::new)
                );

        // get the position of each letter
        // of the message corresponding
        // to the polybius and
        // collect to a string.

        // System.out.println(encode);
        return of(message.split(""))
                .map(map::get)
                .collect(joining());
    }

    /**
     * This method maps the position
     * of each letter to the letter
     * in the polybius.
     *
     * @param key    Takes the key string.
     * @param cipher Takes the encoded string.
     * @return The string mapped to the position.
     */
    private String decode(String key, String cipher) {

        // Get the polybius depending
        // on key.
        String codes = getPolybius.apply(key);

        // create a HashMap to store the
        // position of each letter to the
        // corresponding letter of the polybius.
        var map = IntStream.range(0, codes.length())
                .boxed()
                .collect(
                        Collectors.toMap(i -> i / 5 + "" + i % 5,
                                i -> "" + codes.charAt(i),
                                (a, b) -> b, HashMap::new)
                );

        // collect the decoded string
        // depending on the cipher.


        // System.out.println(decode);
        return iterate(0, i -> i < cipher.length(), i -> i + 2)
                .mapToObj(i -> map.get(cipher.substring(i, i + 2)))
                .collect(joining());

    }

    /**
     * This anonymous method takes the
     * "key" string and returns the
     * corresponding Polybius string.
     */
    UnaryOperator<String> getPolybius = key -> {

        // Build the head of the polybius
        // with only distinct letters.
        String head = of(key.split(""))
                // collect only non-duplicate
                // letters.
                .distinct()
                .collect(joining());

        // Fill the polybius with the
        // remaining letters of the alphabet.
        String restOfLetters = of("ABCDEFGHIKLMNOPQRSTUVWXYZ".split(""))
                // check if the polybius
                // contains any letter from
                // #head and filter
                .filter(c -> !head.contains(c))
                // collect in a string.
                .collect(joining());

        // Concatinate the head with
        // the rest of letters to get
        // the complete polybius.
        return head.concat(restOfLetters);
    };

}
