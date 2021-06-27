package control_unit;

import java.time.ZonedDateTime;

public class Metadata {

    private Long firstAvailableID;
    private final ZonedDateTime creationDateAndTime;
    private final String collectionType;

    public Metadata(Long firstAvailableID, String creationDateAndTime, String collectionType) {
        this.firstAvailableID = firstAvailableID;
        this.creationDateAndTime = ZonedDateTime.parse(creationDateAndTime);
        this.collectionType = collectionType;
    }

    public Metadata(String creationDateAndTime, String collectionType) {
        this.firstAvailableID = 1L;
        this.creationDateAndTime = ZonedDateTime.parse(creationDateAndTime);
        this.collectionType = collectionType;
    }

    public Metadata(String collectionType) {
        this.firstAvailableID = 1L;
        this.creationDateAndTime = ZonedDateTime.now();
        this.collectionType = collectionType;

    }

    public Long getFirstAvailableID() {
        return firstAvailableID;
    }

    public void updateFirstAvailableID() {
        firstAvailableID += 1;
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

    public String getCollectionType() {
        return collectionType;
    }
}
