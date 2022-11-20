package mouli;

import java.util.function.UnaryOperator;

public class Decoder implements IBifid {
    // the key string with which the message needs to be decoded
    private final String key;
    // the string to be decoded.
    private final String message;

    public Decoder(String key, String message) {
        this.key = key;
        this.message = message;
    }

    /**
     * This method uses the key and the encoded message and returns the decoded bifid.
     *
     * @return The decoded string.
     */
    public String decodeBifid() {

        UnaryOperator<String> convert = str -> {
                    // find the row position
                    var row = new StringBuilder(str.substring(0, str.length() / 2));
                    // System.out.println(row);

                    // find the column position
                    var column = new StringBuilder(str.substring(str.length() / 2));
                    // System.out.println(column);


                    // Find the 2-D position (row-column)
                    // of each character.
                    // (Join the row-column position)
                    var cipher = new StringBuilder();

                    for (int i = 0; i < row.length(); i++) {

                        cipher.append(row.charAt(i))
                                .append(column.charAt(i));
                    }

                    // System.out.println(cipher.toString());
                    return cipher.toString();
                };

        // return decoded bifid
        return bifid(key, message, convert);
    }

}
