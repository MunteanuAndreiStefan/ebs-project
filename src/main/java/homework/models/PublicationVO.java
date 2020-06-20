package homework.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PublicationVO {
    private String companyName;
    private Double companyDrop;
    private Double companyValue;
    private Double companyVariation;
    private String companyDate;

    PublicationVO(String companyName, Double companyDrop, Double companyValue, Double companyVariation, String companyDate) {
        this.companyName = companyName;
        this.companyDrop = companyDrop;
        this.companyValue = companyValue;
        this.companyVariation = companyVariation;
        this.companyDate = companyDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PublicationVO that = (PublicationVO) o;

        return new EqualsBuilder()
                .append(companyName, that.companyName)
                .append(companyDrop, that.companyDrop)
                .append(companyValue, that.companyValue)
                .append(companyVariation, that.companyVariation)
                .append(companyDate, that.companyDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(companyName)
                .append(companyDrop)
                .append(companyValue)
                .append(companyVariation)
                .append(companyDate)
                .toHashCode();
    }

    public static PublicationDTOBuilder builder() {
        return new PublicationDTOBuilder();
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public Double getCompanyDrop() {
        return this.companyDrop;
    }

    public Double getCompanyValue() {
        return this.companyValue;
    }

    public Double getCompanyVariation() {
        return this.companyVariation;
    }

    public String getCompanyDate() {
        return this.companyDate;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyDrop(Double companyDrop) {
        this.companyDrop = companyDrop;
    }

    public void setCompanyValue(Double companyValue) {
        this.companyValue = companyValue;
    }

    public void setCompanyVariation(Double companyVariation) {
        this.companyVariation = companyVariation;
    }

    public void setCompanyDate(String companyDate) {
        this.companyDate = companyDate;
    }

    public String toString() {
        return "PublicationDTO(companyName=" + this.getCompanyName() + ", companyDrop=" + this.getCompanyDrop() + ", companyValue=" + this.getCompanyValue() + ", companyVariation=" + this.getCompanyVariation() + ", companyDate=" + this.getCompanyDate() + ")";
    }

    public static class PublicationDTOBuilder {

        private String companyName;

        private Double companyDrop;

        private Double companyValue;

        private Double companyVariation;

        private String companyDate;

        PublicationDTOBuilder() {
        }

        public PublicationVO.PublicationDTOBuilder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public PublicationVO.PublicationDTOBuilder companyDrop(Double companyDrop) {
            this.companyDrop = companyDrop;
            return this;
        }

        public PublicationVO.PublicationDTOBuilder companyValue(Double companyValue) {
            this.companyValue = companyValue;
            return this;
        }

        public PublicationVO.PublicationDTOBuilder companyVariation(Double companyVariation) {
            this.companyVariation = companyVariation;
            return this;
        }

        public PublicationVO.PublicationDTOBuilder companyDate(String companyDate) {
            this.companyDate = companyDate;
            return this;
        }

        public PublicationVO build() {
            return new PublicationVO(companyName, companyDrop, companyValue, companyVariation, companyDate);
        }

        public String toString() {
            return "PublicationDTO.PublicationDTOBuilder(companyName=" + this.companyName + ", companyDrop=" + this.companyDrop + ", companyValue=" + this.companyValue + ", companyVariation=" + this.companyVariation + ", companyDate=" + this.companyDate + ")";
        }
    }
}
