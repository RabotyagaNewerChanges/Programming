package control_unit;

import storable.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.TreeSet;

public class DataParser {

    private Scanner     fileScanner;
    private String      sourceString;
    private File        sourceFile;
    private JSONObject  jsonWrapper;

    public DataParser(String sourceFileName) throws ReaderCreationException, FileNotFoundException {
        try {
            sourceFile = new File(sourceFileName);
            if (!sourceFile.exists()) {
                System.out.println("File does not exist");
                throw new ReaderCreationException();
            } else if (sourceFile.isDirectory()) {
                System.out.println("Given name dose not lead to file");
                throw new ReaderCreationException();
            } else if (!sourceFile.canRead()) {
                System.out.println("Unable to read from file");
                throw new ReaderCreationException();
            } else {
                fileScanner = new Scanner(sourceFile);
                read();
                parseJsonWrapper();
            }
        } catch (NullPointerException e) {
            System.out.println("File should be given");
            throw new FileNotFoundException();
        }
    }

    private void read() {
        while(fileScanner.hasNextLine()) {
            sourceString += fileScanner.nextLine();
        }
    }

    private void parseJsonWrapper() {
        JSONParser jsonParser = new JSONParser();
        try {
            jsonWrapper = (JSONObject) jsonParser.parse(sourceString.substring(4));
        }
        catch (ParseException e) {
            System.out.println("Unable to parse given data. " +
                               "Check source file contains data in valid format (json)");
        }
    }

    public Metadata parseMetadata() throws NullPointerException{

        JSONObject metadata = (JSONObject) jsonWrapper.get("metadata");
        Long firstAvailableID = (Long) metadata.get("first_available_id");
        String creationDateAndTime = (String) metadata.get("creation_date_and_time");
        String collectionType = (String) metadata.get("collection_type");
        return new Metadata(firstAvailableID, creationDateAndTime, collectionType);
    }

    private Coordinates parseCoordinates(JSONObject coordinates) throws NullPointerException{

        Long x = (Long) coordinates.get("x");
        Double y = (Double) coordinates.get("y");

        return new Coordinates(Math.toIntExact(x), y);
    }

    private Location parseLocation(JSONObject location) throws NullPointerException{

        double x = (double) location.get("x");
        double y = (double) location.get("y");
        String name = (String) location.get("location");

        return new Location(x, y, name);
    }

    private Address parseAddress(JSONObject address) throws NullPointerException{

        String zipCode = (String) address.get("zip_code");
        JSONObject location = (JSONObject) address.get("location");

        return new Address(zipCode, parseLocation(location));
    }

    private Organization parseOrganization(JSONObject organization) throws NullPointerException{

        Long id = (Long) ((long) organization.get("id"));
        String name = (String) organization.get("name");
        JSONObject coordinates = (JSONObject) organization.get("coordinates");
        String creationDateAndTime = (String)  organization.get("creation_date_and_time");
        Long annualTurnover = (Long) organization.get("annual_turnover");
        String fullName = (String) organization.get("full_name");
        if(fullName.equals(""))
            fullName = null;
        Long employeesAmount = (Long) ((long) organization.get("employees_amount"));
        OrganizationType organizationType = OrganizationType.valueOf(((String) organization.get("organization_type")).toUpperCase());
        JSONObject postalAddress = (JSONObject) organization.get("postal_address");


        return new Organization(id, name, parseCoordinates(coordinates),
                                creationDateAndTime, Math.toIntExact(annualTurnover),
                                fullName, employeesAmount, organizationType,
                                parseAddress(postalAddress));
    }

    public TreeSet<Organization> parseOrganizations() throws NullPointerException{
        TreeSet<Organization> organizations = new TreeSet<>();

        JSONArray collection = (JSONArray) jsonWrapper.get("collection");

        for(Object object: collection) {
            JSONObject jsonObject = (JSONObject) object;
            organizations.add(parseOrganization(jsonObject));
         }

        return organizations;
    }

    public void close() {
        fileScanner.close();
    }


}
