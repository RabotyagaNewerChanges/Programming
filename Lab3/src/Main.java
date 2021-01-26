public class Main {
    public static void main(String[] args) {
        //Initialize
        Essence[] family = new Troll[2];
        family[0] = new Troll("Муми-папа");
        family[1] = new Troll("Муми-мама");

        Room livingRoom = new Room("гостиная", family);
        Place angle = new Place("угол между комодом и дверью на кухню") {
            @Override
            public void setContent(Essence[] content) {
                this.content = content;
            }
        };

        Hat hat = new Hat(angle);
        Thing eggshell = new Thing("яичная скорлупа");
        hat.setContent(eggshell);
        eggshell.setPlace(hat);
        Miracle miracle = new Miracle();

        //Main body
        System.out.println(livingRoom.toString());
        livingRoom.setContent(null);
        System.out.println(livingRoom.toString());

        System.out.println("---Гостинная опустела.");

        System.out.println(hat.toString());
        System.out.println("---А в углу, между комодом и дверью на кухню, осталась шляпа Волшебника с яичной скорлупой.");

        eggshell.setLayingForLongTime(true);
        miracle.create();
        eggshell.transform();
        System.out.println("---И тут сотворилось чудо: яичная скорлупа начала преображаться.");

        Thing someThing = new Thing("всякая вещь");
        while(true) {
            if (someThing.getLayingForLongTime()) {
                System.out.print("Однако ");
                someThing.transform();
                break;
            } else {
                System.out.print("Если " + someThing.getName() + " лежит в шляпе недостаточно долго, то ");
                someThing.transform();
                someThing.setLayingForLongTime(true);
            }
        }
        System.out.println("---Дело в том, что всякая вещь, если она достаточно долго пролежит в шляпе " +
                "Волшебника, превращается в нечто совершенно иное -- и никогда нельзя знать заранее, во что именно.");

        Troll dad = (Troll) family[0];
        hat.setContent(null);
        hat.setPlace(dad);
        Thing[] clothes = new Thing[1];
        clothes[0] = hat;
        dad.setClothes(clothes);

        while(true) {
            if(dad.isHatIsOnForLongTime()) {
                System.out.print("Если бы " + dad.getName() + " посидел в шляпе дольше, то: " );
                dad.transform();
                break;
            }
            else {
                dad.transform();
                System.out.println("Как же вовремя он снял шляпу " );
                dad.setHatIsOnForLongTime(true);
            }
        }

        System.out.println("---Муми-папе ужасно повезло, что шляпа ему не подошла: побудь он в ней чуточку подольше" +
                " - и только покровителю всех троллей и Сниффов известно, какая участь его ожидала.");


    }
}
