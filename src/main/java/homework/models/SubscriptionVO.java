package homework.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SubscriptionVO {
    private String companyName;
    private String companyNameOperation;

    private Double companyDrop;
    private String companyDropOperation;

    private Double companyValue;
    private String companyValueOperation;

    private Double companyVariation;
    private String companyVariationOperation;

    private String companyDate;
    private String companyDateOperation;

    SubscriptionVO(String companyName, String companyNameOperation, Double companyDrop, String companyDropOperation, Double companyValue,
                   String companyValueOperation, Double companyVariation, String companyVariationOperation, String companyDate,
                   String companyDateOperation) {
        this.companyName = companyName;
        this.companyNameOperation = companyNameOperation;
        this.companyDrop = companyDrop;
        this.companyDropOperation = companyDropOperation;
        this.companyValue = companyValue;
        this.companyValueOperation = companyValueOperation;
        this.companyVariation = companyVariation;
        this.companyVariationOperation = companyVariationOperation;
        this.companyDate = companyDate;
        this.companyDateOperation = companyDateOperation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SubscriptionVO that = (SubscriptionVO) o;

        return new EqualsBuilder()
                .append(companyName, that.companyName)
                .append(companyNameOperation, that.companyNameOperation)
                .append(companyDrop, that.companyDrop)
                .append(companyDropOperation, that.companyDropOperation)
                .append(companyValue, that.companyValue)
                .append(companyValueOperation, that.companyValueOperation)
                .append(companyVariation, that.companyVariation)
                .append(companyVariationOperation, that.companyVariationOperation)
                .append(companyDate, that.companyDate)
                .append(companyDateOperation, that.companyDateOperation)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(companyName)
                .append(companyNameOperation)
                .append(companyDrop)
                .append(companyDropOperation)
                .append(companyValue)
                .append(companyValueOperation)
                .append(companyVariation)
                .append(companyVariationOperation)
                .append(companyDate)
                .append(companyDateOperation)
                .toHashCode();
    }

    public static SubscriptionDTOBuilder builder() {
        return new SubscriptionDTOBuilder();
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public String getCompanyNameOperation() {
        return this.companyNameOperation;
    }

    public Double getCompanyDrop() {
        return this.companyDrop;
    }

    public String getCompanyDropOperation() {
        return this.companyDropOperation;
    }

    public Double getCompanyValue() {
        return this.companyValue;
    }

    public String getCompanyValueOperation() {
        return this.companyValueOperation;
    }

    public Double getCompanyVariation() {
        return this.companyVariation;
    }

    public String getCompanyVariationOperation() {
        return this.companyVariationOperation;
    }

    public String getCompanyDate() {
        return this.companyDate;
    }

    public String getCompanyDateOperation() {
        return this.companyDateOperation;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyNameOperation(String companyNameOperation) {
        this.companyNameOperation = companyNameOperation;
    }

    public void setCompanyDrop(Double companyDrop) {
        this.companyDrop = companyDrop;
    }

    public void setCompanyDropOperation(String companyDropOperation) {
        this.companyDropOperation = companyDropOperation;
    }

    public void setCompanyValue(Double companyValue) {
        this.companyValue = companyValue;
    }

    public void setCompanyValueOperation(String companyValueOperation) {
        this.companyValueOperation = companyValueOperation;
    }

    public void setCompanyVariation(Double companyVariation) {
        this.companyVariation = companyVariation;
    }

    public void setCompanyVariationOperation(String companyVariationOperation) {
        this.companyVariationOperation = companyVariationOperation;
    }

    public void setCompanyDate(String companyDate) {
        this.companyDate = companyDate;
    }

    public void setCompanyDateOperation(String companyDateOperation) {
        this.companyDateOperation = companyDateOperation;
    }

    public String toString() {
        return "SubscriptionDTO(companyName=" + this.getCompanyName() + ", companyNameOperation=" + this.getCompanyNameOperation() + ", companyDrop" +
                "=" + this.getCompanyDrop() + ", companyDropOperation=" + this.getCompanyDropOperation() + ", companyValue=" + this.getCompanyValue() + ", companyValueOperation=" + this.getCompanyValueOperation() + ", companyVariation=" + this.getCompanyVariation() + ", companyVariationOperation=" + this.getCompanyVariationOperation() + ", companyDate=" + this.getCompanyDate() + ", companyDateOperation=" + this.getCompanyDateOperation() + ")";
    }

    public static class SubscriptionDTOBuilder {

        private String companyName;

        private String companyNameOperation;

        private Double companyDrop;

        private String companyDropOperation;

        private Double companyValue;

        private String companyValueOperation;

        private Double companyVariation;

        private String companyVariationOperation;

        private String companyDate;

        private String companyDateOperation;

        SubscriptionDTOBuilder() {
        }

        public SubscriptionVO.SubscriptionDTOBuilder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public SubscriptionVO.SubscriptionDTOBuilder companyNameOperation(String companyNameOperation) {
            this.companyNameOperation = companyNameOperation;
            return this;
        }

        public SubscriptionVO.SubscriptionDTOBuilder companyDrop(Double companyDrop) {
            this.companyDrop = companyDrop;
            return this;
        }

        public SubscriptionVO.SubscriptionDTOBuilder companyDropOperation(String companyDropOperation) {
            this.companyDropOperation = companyDropOperation;
            return this;
        }

        public SubscriptionVO.SubscriptionDTOBuilder companyValue(Double companyValue) {
            this.companyValue = companyValue;
            return this;
        }

        public SubscriptionVO.SubscriptionDTOBuilder companyValueOperation(String companyValueOperation) {
            this.companyValueOperation = companyValueOperation;
            return this;
        }

        public SubscriptionVO.SubscriptionDTOBuilder companyVariation(Double companyVariation) {
            this.companyVariation = companyVariation;
            return this;
        }

        public SubscriptionVO.SubscriptionDTOBuilder companyVariationOperation(String companyVariationOperation) {
            this.companyVariationOperation = companyVariationOperation;
            return this;
        }

        public SubscriptionVO.SubscriptionDTOBuilder companyDate(String companyDate) {
            this.companyDate = companyDate;
            return this;
        }

        public SubscriptionVO.SubscriptionDTOBuilder companyDateOperation(String companyDateOperation) {
            this.companyDateOperation = companyDateOperation;
            return this;
        }

        public SubscriptionVO build() {
            return new SubscriptionVO(companyName, companyNameOperation, companyDrop, companyDropOperation, companyValue, companyValueOperation,
                    companyVariation, companyVariationOperation, companyDate, companyDateOperation);
        }

        public String toString() {
            return "SubscriptionDTO.SubscriptionDTOBuilder(companyName=" + this.companyName + ", companyNameOperation=" + this.companyNameOperation + ", companyDrop=" + this.companyDrop + ", companyDropOperation=" + this.companyDropOperation + ", companyValue=" + this.companyValue + ", companyValueOperation=" + this.companyValueOperation + ", companyVariation=" + this.companyVariation + ", companyVariationOperation=" + this.companyVariationOperation + ", companyDate=" + this.companyDate + ", companyDateOperation=" + this.companyDateOperation + ")";
        }
    }
}
