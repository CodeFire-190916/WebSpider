package ua.com.codefire.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by human on 12/5/16.
 */
public class Spider {


    //<a href="ua/navchannya-u-maup/navchalno-vihovnij-kompleks-prestizh.html" >
    private static final String ADDRESS_REGEX = "href=\"(?<url>[^\"]+)";
    private static final Pattern ADDRESS_PATTERN;

    // Static Initializer
    static {
        ADDRESS_PATTERN = Pattern.compile(ADDRESS_REGEX, Pattern.MULTILINE);
    }

    public List<String> scan(String address) {
        List<String> foundAddressList = new ArrayList<>();

        try {
            URL url = new URL(address);
            URLConnection conn = url.openConnection();

            StringBuilder buffer = new StringBuilder();

            try (Scanner scanner = new Scanner(conn.getInputStream())) {
                while (scanner.hasNextLine()) {
                    buffer.append(scanner.nextLine()).append("\n");
                }
            }

            Matcher matcher = ADDRESS_PATTERN.matcher(buffer);

            while (matcher.find()) {
//                String foundAddress = matcher.group(1);
                String foundAddress = matcher.group("url");

                foundAddressList.add(foundAddress);
            }

            System.out.println(foundAddressList);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return foundAddressList;
    }
}
