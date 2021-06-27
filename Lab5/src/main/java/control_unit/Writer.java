package control_unit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import storable.Address;
import storable.Coordinates;
import storable.Location;
import storable.Organization;

import java.io.*;
import java.util.Scanner;
import java.util.TreeSet;

public class Writer {

    private File fileForWriting;
    private BufferedWriter jsonWriter;

    public Writer(String fileName) throws FileNotFoundException {
        try {
            fileForWriting = new File(fileName);
            if (!fileForWriting.exists()) {
                System.out.println("File does not exist");
                throw new WriterCreationException();
            } else if (fileForWriting.isDirectory()) {
                System.out.println("Given name dose not lead to file");
                throw new WriterCreationException();
            } else if (!fileForWriting.canRead()) {
                System.out.println("Unable to read from file");
                throw new WriterCreationException();
            } else {
                jsonWriter = new BufferedWriter(new FileWriter(fileForWriting));
            }
        } catch (NullPointerException | IOException e) {
            System.out.println("File should be given");
            throw new FileNotFoundException();
        }
    }

    public JSONObject parseMetadataToJson(Metadata metadata) {

        JSONObject jsonMetadataObject = new JSONObject();
        jsonMetadataObject.put("first_available_id",metadata.getFirstAvailableID());
        jsonMetadataObject.put("creation_date_and_time",metadata.getCreationDateAndTime().toString());
        jsonMetadataObject.put("collection_type", metadata.getCollectionType());
        return jsonMetadataObject;

    }

    public JSONObject parseCoordinatesToJson(Coordinates coordinates) {
        JSONObject jsonCoordinatesObject = new JSONObject();
        jsonCoordinatesObject.put("x", coordinates.getX());
        jsonCoordinatesObject.put("y", coordinates.getY().doubleValue());
        return jsonCoordinatesObject;
    }

    public JSONObject parseLocationToJson(Location location) {
        JSONObject jsonLocationObject = new JSONObject();
        jsonLocationObject.put("x", location.getX());
        jsonLocationObject.put("y", location.getY());
        jsonLocationObject.put("location", location.getName());
        return jsonLocationObject;
    }

    public JSONObject parseAddressToJson(Address address) {
        JSONObject jsonAddressObject = new JSONObject();
        jsonAddressObject.put("zip_code", address.getZipCode());
        jsonAddressObject.put("location", parseLocationToJson(address.getLocation()));
        return jsonAddressObject;
    }


    public JSONObject parseOrganizationToJson(Organization organization) {
        JSONObject jsonOrganizationObject = new JSONObject();
        jsonOrganizationObject.put("id", organization.getId().longValue());
        jsonOrganizationObject.put("name", organization.getName());
        jsonOrganizationObject.put("coordinates", parseCoordinatesToJson(organization.getCoordinates()));
        jsonOrganizationObject.put("creation_date_and_time", organization.getCreationDateAndTime().toString());
        jsonOrganizationObject.put("annual_turnover", organization.getAnnualTurnover().intValue());
        jsonOrganizationObject.put("fullName", organization.getFullName());
        jsonOrganizationObject.put("employees_amount", organization.getEmployeesAmount().longValue());
        jsonOrganizationObject.put("organization_type", organization.getOrganizationType().toString());
        jsonOrganizationObject.put("postal_address", parseAddressToJson(organization.getPostalAdress()));
        return jsonOrganizationObject;
    }

    public JSONArray parseOrganizationsToJson(TreeSet<Organization> organizations) {
        JSONArray jsonOrganizationsArray = new JSONArray();
        for(Organization organization : organizations){
            JSONObject jsonOrganizationObject = parseOrganizationToJson(organization);
            jsonOrganizationsArray.add(jsonOrganizationObject);
        }
        return jsonOrganizationsArray;
    }

    public JSONObject parseWrapperToJson(Metadata metadata, TreeSet<Organization> organizations) {
        JSONObject jsonWrapperObject = new JSONObject();
        jsonWrapperObject.put("metadata", parseMetadataToJson(metadata));
        System.out.println("Metadata was written as JSON String successfully");
        jsonWrapperObject.put("collection", parseOrganizationsToJson(organizations));
        System.out.println("Collection was written as JSON String successfully");
        System.out.println(jsonWrapperObject);
        return jsonWrapperObject;

    }

    public void writeToFile(TreeSet<Organization> organizations, Metadata metadata) {
        if(fileForWriting.exists()) {
            try {
                jsonWriter.write(parseWrapperToJson(metadata, organizations).toJSONString());
            } catch (IOException e) {
                System.out.println("File could not be overwritten");
            }
        }
        else {
            System.out.println("File could not be created");
        }
    }

    public void closeFileWriter() throws IOException {
        jsonWriter.close();
    }
}
