public class Main {
    public static void main(String[] args) throws PlaceIsNotValid, MiracleDidNotHappen {
        //Initialize
        Troll troll = new Troll("Муми-тролль");
        Troll snusmumrik = new Troll("Снусмумрик");
        Troll sniff = new Troll("Снифф");
        Troll freckenSnork = new Troll("Фрекен Снорк");
        Troll mumiDad = new Troll("Муми-папа");
        Troll mumiMom = new Troll("Муми-мама");
        Essence[] company = new Troll[3];
        company[0] = troll;
        company[1] = snusmumrik;
        company[2] = sniff;
        Essence[] family = new Troll[2];
        family[0] = mumiDad;
        family[1] = mumiMom;
        Troll everyone = new Troll("все");

        Room livingRoom = new Room("гостиная", family);
        Room kitchen = new Room("куххня");
        Place angle = new Place("угол между комодом и дверью на кухню") {
            @Override
            public void setContent(Essence[] content) {
                this.content = content;
            }
        };
        Place terrace = new Place("веранда") {
            @Override
            public void setContent(Essence[] content) {
                this.content = content;
            }
        };
        Place commode = new Place("комод") {
            @Override
            public void setContent(Essence[] content) {
                this.content = content;
            }
        };
        MumiDol mumiDol = new MumiDol("Муми-дол");

        Hat hat = new Hat(angle);
        Thing eggshell = new Thing("яичная скорлупа");
        hat.setContent(eggshell);
        eggshell.setPlace(hat);

        //Main body

        troll.raiseSomething(hat);
        troll.lookAtSomething(hat);
        troll.keepSomething(hat);
        System.out.println("---Муми-тролль поднял шляпу и стал ее рассматривать.");

        mumiDol.transform();
        System.out.println("---Вот как получилось, что они нашли шляпу Волшебника и забрали ее с собой, " +
                "не подозревая о том, что тем самым превратили Муми-дол в арену всяческого волшебства и удивительнейших событий.");

        everyone.drunkCoffee();
        everyone.goAway();
        troll.go(terrace);
        snusmumrik.go(terrace);
        sniff.go(terrace);
        terrace.setContent(company);
        System.out.println("---Когда Муми-тролль, Снусмумрик и Снифф вошли на веранду, все уже попили кофе и разбрелись кто куда.");

        mumiDad.lookAtSomething(hat);
        mumiDad.tryOn(hat);
        mumiMom.openTheDoor();
        mumiDad.lookAtMirror();
        mumiDad.takeOff(hat);
        hat.setPlace(commode);
        System.out.println("---Муми-папа оглядел шляпу со всех сторон, а потом примерил перед зеркалом в гостиной. " +
                "Шляпа была для него чуточку великовата и тяжела, но общее впечатление было весьма внушительное." +
                " Муми-мама открыла дверь да так и застыла на пороге от удивления. " +
                "Муми-папа оглядел себя в зеркале и спереди и сзади, оглядел с боков и со вздохом положил шляпу на комод.");

        System.out.println(livingRoom.toString());
        mumiMom.go(kitchen);
        System.out.println("---И она снова ушла на кухню.");
        livingRoom.setContent(null);
        System.out.println(livingRoom.toString());
        System.out.println("---Гостинная опустела.");

        System.out.println(hat.toString());
        System.out.println("---А в углу, между комодом и дверью на кухню, осталась шляпа Волшебника с яичной скорлупой.");

        eggshell.setLayingForLongTime(true);
        Miracle.setCreated(true);
        eggshell.transform();
        if(!Miracle.isCreated())
            throw new MiracleDidNotHappen();
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

        hat.setContent(null);
        hat.setPlace(mumiDad);
        Thing[] clothes = new Thing[1];
        clothes[0] = hat;
        mumiDad.setClothes(clothes);

        while(true) {
            if(mumiDad.isHatIsOnForLongTime()) {
                System.out.print("Если бы " + mumiDad.getName() + " посидел в шляпе дольше, то: " );
                mumiDad.transform();
                break;
            }
            else {
                mumiDad.transform();
                System.out.println("Как же вовремя он снял шляпу " );
                mumiDad.setHatIsOnForLongTime(true);
            }
        }

        System.out.println("---Муми-папе ужасно повезло, что шляпа ему не подошла: побудь он в ней чуточку подольше" +
                " - и только покровителю всех троллей и Сниффов известно, какая участь его ожидала.");

        mumiDad.gotHeadache();
        mumiDad.cureHeadache();
        System.out.println("---. Муми-папе ужасно повезло, что шляпа ему не подошла: побудь он в ней чуточку подольше -- и только покровителю всех троллей и Сниффов известно, какая участь его ожидала. " +
                "Муми-папа заработал лишь легкую головную боль (которая прошла после обеда). " +
                "Зато яичные скорлупки, оставшиеся в шляпе, мало-помалу начали менять свой вид. " +
                "Они сохранили белый цвет, но все росли и росли в размерах и стали мягкими и пухлыми. " +
                "Немного погодя они целиком заполнили шляпу, а потом из шляпы выпорхнули пять маленьких круглых тучек.");

        Cloud[] clouds = new Cloud[5];
        int i = 1;
        for (Cloud cloud : clouds) {
            cloud = new Cloud("Облако" + i++);
            cloud.fly();
        }
        System.out.println("---Они выплыли на веранду, мягко спустились с крыльца и повисли в воздухе над самой землей. " +
                "А в шляпе Волшебника стало пусто. Тучки неподвижно стояли перед ними и словно чего-то ждали. ");

        freckenSnork.touch(clouds[0]);
        snusmumrik.push(clouds[0]);
        snusmumrik.cry("'А ну давай!'");
        clouds[0].doSomersault();
        System.out.println("---Фрекен Снорк тихонечко протянула лапу и потрогала тучку, которая была к ней поближе. " +
                "Тут все придвинулись ближе и стали ощупывать тучки. Снусмумрик осторожно толкнул одну из тучек. " +
                "Она проплыла немного в воздухе и снова застыла на месте. Муми-тролль только покачал головой в ответ. " +
                "И только он крикнул: \"А ну давай!\" -- как тучка поднялась над землей и описала небольшую изящную дугу. ");
    }

        static class Miracle{

            private static boolean isCreated;

            public static boolean isCreated() {
                return isCreated;
            }

            public static void setCreated(boolean created) {
                isCreated = created;
                System.out.print("Случилось чудо: ");
            }
        }

}
