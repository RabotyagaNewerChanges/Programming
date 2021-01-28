public class MiracleDidNotHappen extends RuntimeException{
    MiracleDidNotHappen(String report) {
        super(report);
    }

    MiracleDidNotHappen() {
        super("Чудо не произошло - дальше сказки не будет!");
    }

}
