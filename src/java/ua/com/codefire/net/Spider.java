package ua.com.codefire.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by human on 12/5/16.
 */
public class Spider {

    private static final String ADDRESS_REGEX = "<URL_REGEX>";
    private static final Pattern ADDRESS_PATTERN;

    // Static Initializer
    static {
        ADDRESS_PATTERN = Pattern.compile(ADDRESS_REGEX);
    }

    public List<String> scan(String address) {
        List<String> foundAddressList = new ArrayList<>();

        try {
            URL url = new URL(address);
            URLConnection conn = url.openConnection();

            try (Scanner scanner = new Scanner(conn.getInputStream())) {

                while (scanner.hasNext(ADDRESS_PATTERN)) {
                    String foundAddress = scanner.next(ADDRESS_PATTERN);

                    foundAddressList.add(foundAddress);
                }

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return foundAddressList;
    }
}
