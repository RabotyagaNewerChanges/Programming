package storable;

import java.time.ZonedDateTime;

public class Organization implements Comparable<Organization>{

    private Long id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDateAndTime;
    private Integer annualTurnover;
    private String fullName;
    private Long employeesAmount;
    private OrganizationType organizationType;
    private Address postalAddress;

    public Organization(Long id, String name, Coordinates coordinates,
                        String creationDateAndTime, Integer annualTurnover,
                        String fullName, Long employeesAmount,
                        OrganizationType organizationType, Address postalAddress) {
        setId(id);
        setName(name);
        setCoordinates(coordinates);
        setCreationDateAndTime(creationDateAndTime);
        setAnnualTurnover(annualTurnover);
        setFullName(fullName);
        setEmployeesAmount(employeesAmount);
        setOrganizationType(organizationType);
        setPostalAdress(postalAddress);
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
            this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCoordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setAnnualTurnover(Integer annualTurnover) {
            this.annualTurnover = annualTurnover;
    }

    public Integer getAnnualTurnover() {
        return annualTurnover;
    }

    public void setFullName(String fullName) {
            this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setEmployeesAmount(Long employeesAmount) {
            this.employeesAmount = employeesAmount;
    }

    public Long getEmployeesAmount() {
        return employeesAmount;
    }

    public void setOrganizationType(OrganizationType organizationType) {
            this.organizationType = organizationType;
    }

    public void setPostalAdress(Address postalAdress) {
        this.postalAddress = postalAdress;
    }

    public Address getPostalAdress() {
        return postalAddress;
    }

    public OrganizationType getOrganizationType() {
        return organizationType;
    }

    public void setCreationDateAndTime(String creationDateAndTime) {
        if(!creationDateAndTime.equals(""))
            this.creationDateAndTime = ZonedDateTime.parse(creationDateAndTime);
        else
            this.creationDateAndTime = ZonedDateTime.now();
    }

    public ZonedDateTime getCreationDateAndTime() {
        return creationDateAndTime;
    }

    public String getFormatedCreationDateAndTime() {
        return creationDateAndTime.getHour() +
                ":" + creationDateAndTime.getMinute() +
                ":" + creationDateAndTime.getSecond() +
                " " + creationDateAndTime.getDayOfMonth() +
                "-" + creationDateAndTime.getMonthValue() +
                "-" + creationDateAndTime.getYear();
    }

    public String getAsString() {
        if(fullName != null)
            return fullName + " (" + getFormatedCreationDateAndTime() + ")";
        else
            return name + " (" + getFormatedCreationDateAndTime() + ")";
    }

    @Override
    public int compareTo(Organization organization) {
        return (int) (employeesAmount - organization.employeesAmount);
    }
}
