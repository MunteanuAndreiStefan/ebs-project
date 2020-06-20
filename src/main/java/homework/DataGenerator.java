package homework;

import homework.models.PublicationVO;
import homework.models.SubscriptionVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;


public class DataGenerator {

    private static List<SubscriptionVO> subscriptionVOS = new ArrayList<>();

    private static List<PublicationVO> publicationVOS = new ArrayList<>();

    private static void callTheScript() throws IOException {
        Runtime rt = Runtime.getRuntime();
        String[] commands = {"py", "-3", "C:\\Users\\amunteanu\\Desktop\\ebs\\communication\\src\\main\\java\\homework\\python\\GenPubSub.py"};
        Process proc = rt.exec(commands);
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

        String s = null;
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
            if (s.startsWith("P-")) {
                publicationVOS.add(processPublication(s));
            } else if (s.startsWith("S-")) {
                subscriptionVOS.add(processSubscription(s));
            }
        }

        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }
    }

    private static PublicationVO processPublication(String publication) {
        List<String> fields = Arrays.asList(publication.substring(3, publication.length() - 2).split(", "));
        Map<String, String> map = fields.stream()
                .map(subValue -> subValue.split(": "))
                .collect(Collectors.groupingBy(strings -> strings[0].substring(1, strings[0].length() - 1),
                        Collectors.mapping(strings -> strings[1], joining())));
        PublicationVO.PublicationDTOBuilder builder = PublicationVO.builder();
        map.forEach((key, value) -> {
            value = value.replace("'", "");
            switch (key) {
                case "company": {
                    builder.companyName(value);
                    break;
                }
                case "drop": {
                    builder.companyDrop(Double.parseDouble(value));
                    break;
                }
                case "value": {
                    builder.companyValue(Double.parseDouble(value));
                    break;
                }
                case "variation": {
                    builder.companyVariation(Double.parseDouble(value));
                    break;
                }
                case "date": {
                    builder.companyDate(value.replace(".", "-"));
                    break;
                }
            }
        });
        return builder.build();
    }

    private static SubscriptionVO processSubscription(String subscription) {
        SubscriptionVO.SubscriptionDTOBuilder builder = SubscriptionVO.builder();
        if (subscription.length() < 10) {
            return builder.build();
        }
        List<String> fields = Arrays.asList(subscription.substring(4, subscription.length() - 3).split("], \\["));
        Map<String, List<List<String>>> map = fields.stream()
                .map(subValue -> subValue.split(", "))
                .filter(strings -> strings.length > 2)
                .collect(Collectors.groupingBy(strings -> strings[0].substring(1, strings[0].length() - 1),
                        Collectors.mapping(strings -> Arrays.asList(strings[1], strings[2]),
                                Collectors.mapping(list -> list.subList(0, 2), Collectors.toList()))
                ));

        map.forEach((key, val) -> {
            List<String> vals = val.get(0);
            String operator = vals.get(0).replace("'", "");
            String value = vals.get(1).replace("'", "");
            switch (key) {
                case "company": {
                    builder.companyName(value);
                    builder.companyNameOperation(operator);
                    break;
                }
                case "drop": {
                    builder.companyDrop(Double.parseDouble(value));
                    builder.companyDropOperation(operator);
                    break;
                }
                case "value": {
                    builder.companyValue(Double.parseDouble(value));
                    builder.companyValueOperation(operator);
                    break;
                }
                case "variation": {
                    builder.companyVariation(Double.parseDouble(value));
                    builder.companyVariationOperation(operator);
                    break;
                }
                case "date": {
                    builder.companyDate(value.replace(".", "-"));
                    builder.companyDateOperation(operator);
                    break;
                }
            }
        });

        return builder.build();
    }

    public static List<SubscriptionVO> getSubscriptions() {
        checkCollectionsAndCall();
        return subscriptionVOS;
    }

    public static List<PublicationVO> getPublications() {
        checkCollectionsAndCall();
        return publicationVOS;
    }

    private static void checkCollectionsAndCall() {
        if (publicationVOS.isEmpty() || subscriptionVOS.isEmpty()) {
            try {
                callTheScript();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
