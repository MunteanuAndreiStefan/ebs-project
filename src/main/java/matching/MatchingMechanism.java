package matching;

import com.ebs.project.proto.PublicationMessage;
import com.ebs.project.proto.SubscriptionMessage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MatchingMechanism {

    private static final List<SubscriptionMessage.SubscriptionDTO> subscriptions = new ArrayList<>();

    public static void addSubscription(SubscriptionMessage.SubscriptionDTO subscriptionDTO) {
        subscriptions.add(subscriptionDTO);
    }

    public static List<SubscriptionMessage.SubscriptionDTO> getSubscriptionsBy(PublicationMessage.PublicationDTO publicationDTO) {
        return subscriptions.stream()
                .filter(subscriptionDTO -> pubMatchesSub(subscriptionDTO, publicationDTO))
                .collect(Collectors.toList());
    }

    private static boolean pubMatchesSub(SubscriptionMessage.SubscriptionDTO subscriptionDTO,
                                         PublicationMessage.PublicationDTO publicationDTO) {

        return stringMatches(subscriptionDTO.getCompanyName(), subscriptionDTO.getCompanyNameOperation(), publicationDTO.getCompanyName())
                && doubleMatches(subscriptionDTO.getCompanyDrop(), subscriptionDTO.getCompanyDropOperation(), publicationDTO.getDrop())
                && doubleMatches(subscriptionDTO.getCompanyValue(), subscriptionDTO.getCompanyValueOperation(), publicationDTO.getValue())
                && doubleMatches(subscriptionDTO.getCompanyVariation(), subscriptionDTO.getCompanyVariationOperation(), publicationDTO.getVariation())
                && datesMatches(subscriptionDTO.getCompanyDate(), subscriptionDTO.getCompanyDateOperation(), publicationDTO.getDate());
    }

    private static boolean stringMatches(String companyString, String companyOperation, String publicationValue) {
        if (companyString.isBlank() && companyOperation.isBlank()) {
            return true;
        }
        return companyOperation.equals("=") && companyString.equals(publicationValue);
    }

    private static boolean doubleMatches(double subscriptionDouble, String subscriptionOperation,
                                         double publicationValue) {
        if (subscriptionDouble == 0 && subscriptionOperation.isBlank()) {
            return true;
        } else if (subscriptionOperation.equals("<")) {
            return publicationValue < subscriptionDouble;
        } else if (subscriptionOperation.equals("<=")) {
            return publicationValue <= subscriptionDouble;
        } else if (subscriptionOperation.equals(">")) {
            return publicationValue > subscriptionDouble;
        } else if (subscriptionOperation.equals(">=")) {
            return publicationValue >= subscriptionDouble;
        }
        return false;
    }

    private static boolean datesMatches(String companyDate, String companyOperation,
                                        String publicationValue) {
        if (companyOperation.isBlank()) {
            return true;
        }
        LocalDate subDate = LocalDate.parse(companyDate);
        LocalDate pubDate = LocalDate.parse(publicationValue);
        if (companyOperation.equals("<")) {
            return subDate.isBefore(pubDate);
        } else if (companyOperation.equals("<=")) {
            return subDate.isBefore(pubDate) || subDate.isEqual(pubDate);
        } else if (companyOperation.equals(">")) {
            return subDate.isAfter(pubDate);
        } else if (companyOperation.equals(">=")) {
            return subDate.isAfter(pubDate) || subDate.isEqual(pubDate);
        }
        return false;
    }
}
