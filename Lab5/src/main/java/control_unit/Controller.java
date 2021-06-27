package control_unit;

import storable.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;
import java.io.File;

public class Controller {
    private String sourceFileName;
    private boolean programState;
    private boolean isInScript;
    private int historyLogLength;
    private Queue<String> historyLog;

    private TreeSet<Organization> organizations;
    private Metadata metadata;

    public Controller(TreeSet<Organization> organizations) {
        this.organizations = organizations;
        isInScript = false;
        programState = true;
        sourceFileName = System.getenv("DATA_FOR_LAB");
        try {
            readFromFile();
        } catch (ReaderCreationException e) {
            System.out.println("Unable to read given file. New collection have been created");
            initializeMetadata();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found. New collection have been created ");
            initializeMetadata();
        }
        initializeHistoryLog();
        executeHelpCommand();
    }

    private void initializeMetadata() {
        metadata = new Metadata("TreeSet");
    }

    public void readFromFile() throws ReaderCreationException, FileNotFoundException{
        DataParser dataParser = new DataParser(sourceFileName);
        try {
            metadata = dataParser.parseMetadata();
        } catch (NullPointerException e) {
            System.out.println("Unable to parse Metadata from given file");
        }
        try {
            organizations = dataParser.parseOrganizations();
        } catch (NullPointerException e) {
            System.out.println("Unable to parse Collection from given file");
        }
        dataParser.close();
    }

    private void writeToFile() {
        try {
            Writer writer = new Writer(sourceFileName);
            writer.writeToFile(organizations, metadata);
            writer.closeFileWriter();
        } catch (IOException e) {
            System.out.println("Unable to write data to file");
        }

    }

    public TreeSet<Organization> getOrganizations() {
        return organizations;
    }

    public boolean getProgramState() {
        return this.programState;
    }

    public void executeHelpCommand() {

        System.out.print(
                "Command format: \n" +
                        "    command_name (standard type argument) {composite type argument}\n\n" +
                        "Available commands list:\n" +
                        "    help : get information about available commands\n" +
                        "    history : returns list of 8 last comma (without arguments)\n" +

                        "    clear : clears collection\n" +
                        "    info : returns information about collection" +
                        "(type, creation date and time etc.)\n" +
                        "    show : returns all elements from collection" +
                        "in string format\n" +
                        "    save : saves collection to file\n" +

                        "    add {element} : adds new element to the collection\n" +
                        "    add_if_max {element} : adds new element to the collection, " +
                        "if its value greater than current collection maximum\n" +
                        "    add_if_min {element} : adds new element to the collection, " +
                        "if its value less than current collection minimum\n" +
                        "    update (id) {element} : updates element which id equals to given id\n" +
                        "    remove_by_id (id) : removes element which id equals to given id\n" +

                        "    filter_starts_with_full_name fullName : returns all elements, " +
                        "the value of the fullName field which starts with the given substring\n" +
                        "    filter_less_than_annual_turnover annualTurnover : returns all elements, " +
                        "the value of the annualTurnover field is less than given value\n" +
                        "    filter_greater_than_postal_address {postalAddress} : returns all elements, " +
                        "the value of the postalAddress field is greater than the given value\n" +

                        "    execute_script (file_name) : reads and executes script from file with given name\n" +

                        "    exit : ends current program session (without saving temporary data to file)\n"
        );

        updateHistory("help");
    }

    private void initializeHistoryLog() {
        historyLogLength = 8;
        historyLog = new LinkedList<>();
        for (int i = 0; i < historyLogLength; i++)
            historyLog.add("");
    }

    private void updateHistory(String command) {
        historyLog.poll();
        historyLog.add(command);
    }

    public void executeHistoryCommand() {
        for (String command : historyLog)
            System.out.println("----" + command);

        updateHistory("history");
    }

    public void executeClearCommand() {
        organizations.clear();

        updateHistory("clear");
    }

    public void executeInfoCommand() {
        System.out.println("Creation date and time: " + metadata.getFormatedCreationDateAndTime());
        System.out.println("Collection type: " + metadata.getCollectionType());
        System.out.println("Amount of elements: " + organizations.size());

        updateHistory("info");
    }

    public void executeShowCommand() {
        for (Organization organization : organizations)
            System.out.println("----" + organization.getAsString());

        updateHistory("show");
    }

    public void executeSaveCommand(Scanner scanner) {
        writeToFile();

        updateHistory("save");
    }

    public Coordinates coordinatesCreation(Scanner scanner) {

        int x = 0;
        boolean xCoordinateIsValid = false;
        while (!xCoordinateIsValid) {
            System.out.print("Input X coordinate (coordinate should " +
                    "have value greater than -877 and be an integer): ");
            try {
                x = Integer.parseInt(scanner.nextLine());
                xCoordinateIsValid = true;

                if (x <= -877) {
                    System.out.println("You have got out of given bounds");
                    xCoordinateIsValid = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input an integer, please");
            }
        }

        Double y = 0D;
        boolean yCoordinateIsValid = false;
        while (!yCoordinateIsValid) {
            System.out.print("Input Y coordinate (coordinate should be a double): ");
            try {
                y = Double.parseDouble(scanner.nextLine());
                yCoordinateIsValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Input a double, please");
            }
        }
        return new Coordinates(x, y);
    }

    public Location locationCreation(Scanner scanner) {
        double x = 0;
        boolean xCoordinateIsValid = false;
        while(!xCoordinateIsValid) {
            System.out.print("Input X coordinate (coordinate should be a double): ");
            try {
                x = Double.parseDouble(scanner.nextLine());
                xCoordinateIsValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Input a double, please");
            }

        }

        double y = 0;
        boolean yCoordinateIsValid = false;
        while (!yCoordinateIsValid) {
            System.out.print("Input Y coordinate (coordinate should be a double): ");
            try {
                y = Double.parseDouble(scanner.nextLine());
                yCoordinateIsValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Input a double, please");
            }
        }

        String name;
        System.out.print("Input location: ");
        name = scanner.nextLine();

        return new Location(x, y, name);
    }

    public Address addressCreation(Scanner scanner) {
        String zipCode;
        System.out.print("Input zip-code: ");
        zipCode = scanner.nextLine();

        Location location = locationCreation(scanner);

        return new Address(zipCode, location);
    }

    public Organization organizationCreation(Long givenID) {
        Scanner scanner = new Scanner(System.in);
        Long id = givenID;

        System.out.print("Input company name: ");
        String name = scanner.nextLine();

        Coordinates coordinates = coordinatesCreation(scanner);

        ZonedDateTime creationDateAndTime = ZonedDateTime.now();

        String userInput = "";
        Integer annualTurnover = Integer.valueOf(1);
        boolean annualTurnoverIsValid = false;
        while (!annualTurnoverIsValid) {
            System.out.print("Input annual turnover: ");
            userInput = scanner.nextLine();
            if (userInput.equals("")) {
                annualTurnover = null;
                annualTurnoverIsValid = true;
            } else {
                try {
                    annualTurnover = Integer.parseInt(userInput);
                    annualTurnoverIsValid = true;
                } catch (NumberFormatException e) {
                    System.out.println("Input correct number, please");
                }
            }
        }

        String fullName;
        System.out.print("Input company full name (or press Enter to skip): ");
        fullName = scanner.nextLine();
        if (fullName.equals(""))
            fullName = null;

        Long employeesAmount = Long.valueOf(1);
        boolean employeesAmountIsValid = false;
        while (!employeesAmountIsValid) {
            System.out.print("Input employees amount: ");
            try {
                employeesAmount = Long.parseLong(scanner.nextLine());
                employeesAmountIsValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Input an integer, please");
            }
        }

        OrganizationType organizationType = null;
        boolean organizationTypeIsValid = false;
        while(!organizationTypeIsValid) {
            System.out.print("Choose company type from given variants\n" +
                               "    COMMERCIAL,\n" +
                               "    PUBLIC,\n" +
                               "    PRIVATE_LIMITED_COMPANY,\n" +
                               "    OPEN_JOINT_STOCK_COMPANY: ");
            try {
                organizationType = OrganizationType.valueOf(scanner.nextLine());
                organizationTypeIsValid = true;
            }
            catch (IllegalArgumentException e) {
                System.out.println("Invalid company type. " +
                                   "Input valid company type, please");
            }
        }

        Address postalAddress = addressCreation(scanner);

        return new Organization(id, name, coordinates, creationDateAndTime.toString(),
                annualTurnover, fullName, employeesAmount,
                organizationType, postalAddress);
    }

    public void executeAddCommand() {
        Organization organization = organizationCreation( metadata.getFirstAvailableID());
        metadata.updateFirstAvailableID();
        organizations.add(organization);

        updateHistory("add");
    }

    public void executeAddIfMinCommand() {
        Organization organization = organizationCreation(metadata.getFirstAvailableID());
        boolean worthToAdd = true;
        for(Organization organizationFromSet : organizations) {
            if(organization.compareTo(organizationFromSet) >= 0)
                worthToAdd = false;
        }
        if(worthToAdd) {
            organizations.add(organization);
            metadata.updateFirstAvailableID();
        }

        updateHistory("add_if_min");
    }

    public void executeAddIfMaxCommand() {
        Organization organization = organizationCreation(metadata.getFirstAvailableID());
        boolean worthToAdd = true;
        for(Organization organizationFromSet : organizations) {
            if(organization.compareTo(organizationFromSet) <= 0)
                worthToAdd = false;
        }
        if(worthToAdd) {
            organizations.add(organization);
            metadata.updateFirstAvailableID();
        }

        updateHistory("add_if_max");
    }

    public void executeExitCommand() {
        this.programState = false;
    }

    public void executeUpdateByIDCommand(Scanner scanner){
        try {
            Long id = Long.parseLong(scanner.next());
            for (Organization organization : organizations) {
                if(id.compareTo(organization.getId()) == 0)
                    organizations.remove(organization);


            }
            Organization organization = organizationCreation(id);
            organizations.add(organization);

            updateHistory("update_by_id");
        }
        catch(NumberFormatException e) {
            System.out.println("Invalid \"id\" value");
        }
    }

    public void executeRemoveByIDCommand(Scanner scanner) {
        try {
            Long id = Long.parseLong(scanner.next());
            for (Organization organization : organizations) {
                if(id.compareTo(organization.getId()) == 0)
                    organizations.remove(organization);
            }

            updateHistory("remove_by_id");
        }
        catch(NumberFormatException e) {
            System.out.println("Invalid \"id\" value");
        }
    }

    public void executeFilterStartsWithFullNameCommand(Scanner scanner) {

        String fullName = scanner.nextLine();
        for(Organization organization : organizations) {
            if(organization.getFullName() != null)
                if(organization.getFullName().startsWith(fullName))
                    System.out.println(organization.getAsString());
        }
        updateHistory("filter_starts_with_full_name");
    }

    public void executeFilterLessThanAnnualTurnoverCommand(Scanner scanner) {
        try {
            Integer annualTurnover = Integer.parseInt(scanner.nextLine());
            updateHistory("filter_less_than_annual_turnover");
            for(Organization organization : organizations) {
                if(organization.getAnnualTurnover() != null)
                    if(organization.getAnnualTurnover().compareTo(annualTurnover) < 0)
                        System.out.println(organization.getAsString());
            }
        }
        catch (NumberFormatException e) {

        }
    }

    public void executeFilterGreaterThanPostalAddressNameCommand(Scanner scanner) {

        Address postalAddress = addressCreation(scanner);
        for(Organization organization : organizations) {
            if(organization.getPostalAdress() != null)
                if(organization.getPostalAdress().compareTo(postalAddress) > 0)
                    System.out.println(organization.getAsString());
        }

        updateHistory("filter_greater_than_postal_address");
    }

    public void parseCommand(Scanner scanner) {
        String command = scanner.next();
        switch (command) {
            case "help":
                executeHelpCommand();
                break;
            case "history":
                executeHistoryCommand();
                break;

            case "clear":
                executeClearCommand();
                break;
            case "info":
                executeInfoCommand();
                break;
            case "show":
                executeShowCommand();
                break;
            case "save":
                executeSaveCommand(scanner);
                break;

            case "add":
                executeAddCommand();
                break;
            case "add_if_max":
                executeAddIfMaxCommand();
                break;
            case "add_if_min":
                executeAddIfMinCommand();
                break;
            case "update_by_id":
                executeUpdateByIDCommand(scanner);
                break;
            case "remove_by_id":
                executeRemoveByIDCommand(scanner);
                break;

            case "filter_starts_with_full_name":
                executeFilterStartsWithFullNameCommand(scanner);
                break;

            case "filter_less_than_annual_turnover":
                executeFilterLessThanAnnualTurnoverCommand(scanner);
                break;

            case "filter_greater_than_postal_address":
                executeFilterGreaterThanPostalAddressNameCommand(scanner);
                break;

            case "execute_script":
                if(!isInScript)
                    executeScriptCommand(scanner.next());
                else
                    System.out.println("Уже запущен другой скрипт");
                break;

            case "exit":
                executeExitCommand();
                break;
            default:

        }
    }

    public void executeScriptCommand(String filename) {
        try {
            isInScript = true;
            File script = new File(filename);
            Scanner scriptFileScanner = new Scanner(script);

            while (scriptFileScanner.hasNext()) {
                parseCommand(scriptFileScanner);
            }

            isInScript = false;
            scriptFileScanner.close();
            updateHistory("execute_script");
        }
        catch (FileNotFoundException e) {
            System.out.println("Не удается прочитать скрипт");
        }
    }
}
