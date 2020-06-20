package matching;

import com.ebs.project.proto.PublicationMessage;
import com.ebs.project.proto.SubscriptionMessage;

import java.util.List;

public class MatchingDTO {

    private PublicationMessage.PublicationDTO publicationDTO;

    private List<SubscriptionMessage.SubscriptionDTO> subscriptionDTOS;

    public MatchingDTO(PublicationMessage.PublicationDTO publicationDTO, List<SubscriptionMessage.SubscriptionDTO> subscriptionDTOS) {
        this.publicationDTO = publicationDTO;
        this.subscriptionDTOS = subscriptionDTOS;
    }

    public PublicationMessage.PublicationDTO getPublicationDTO() {
        return this.publicationDTO;
    }

    public List<SubscriptionMessage.SubscriptionDTO> getSubscriptionDTOS() {
        return this.subscriptionDTOS;
    }

    public void setPublicationDTO(PublicationMessage.PublicationDTO publicationDTO) {
        this.publicationDTO = publicationDTO;
    }

    public void setSubscriptionDTOS(List<SubscriptionMessage.SubscriptionDTO> subscriptionDTOS) {
        this.subscriptionDTOS = subscriptionDTOS;
    }
}
