package mouli;

import java.util.function.UnaryOperator;

public class Encoder implements IBifid {
    // the key string with which the message needs to be encoded
    private final String key;
    // the string to be encoded.
    private final String message;

    public Encoder(String key, String message) {
        this.key = key;
        this.message = message;
    }

    /**
     * This method takes the key and
     * the message to be encoded and
     * returns the encoded bifid.
     *
     * @return The encoded string.
     */

    public String encodeBifid() {

        UnaryOperator<String> convert =
                str -> {
                    // store the row position
                    var row = new StringBuilder();
                    // store the column position
                    var column = new StringBuilder();

                    // iterate over the string
                    // skipping one letter
                    for (int i = 0; i < str.length(); i += 2) {
                        // build row position
                        row.append(str.charAt(i));
                        // build column position
                        column.append(str.charAt(i + 1));

                    }
                    // join the row-column position
                    return row.append(column)
                            .toString();
                };
        // return the encoded string.
        return bifid(key, message, convert);
    }
}
