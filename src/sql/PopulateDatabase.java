package sql;

import hotelsearch.model.Hotel;
import hotelsearch.model.HotelDB;
import hotelsearch.model.Room;
import hotelsearch.model.SearchOptions;

import java.time.LocalDate;
import java.util.Random;

public class PopulateDatabase {

    private int n;
    private HotelDB db;


        public static String[] hotelNames;         // webScraping ! eða handavinna
        public static String[] hotelAddressess;    // webScraping ! eða handavinna
        public static String[] hotelDescriptions;  // webScraping ! eða handavinna
        //public static String[] peopleNames;

        public static String[] iceland_zipCode_city;

    public PopulateDatabase(int n, HotelDB db) {
        this.n = n;
        this.db = db;
        iceland_zipCode_city= new String[]{
                "101 Reykjavík",
                "102 Reykjavík",
                "103 Reykjavík",
                "104 Reykjavík",
                "105 Reykjavík",
                "107 Reykjavík",
                "108 Reykjavík",
                "109 Reykjavík",
                "110 Reykjavík",
                "111 Reykjavík",
                "112 Reykjavík",
                "113 Reykjavík",
                "116 Reykjavík",
                "121 Reykjavík",
                "123 Reykjavík",
                "124 Reykjavík",
                "125 Reykjavík",
                "127 Reykjavík",
                "128 Reykjavík",
                "129 Reykjavík",
                "130 Reykjavík",
                "132 Reykjavík",
                "150 Reykjavík",
                "155 Reykjavík",
                "170 Seltjarnarnes",
                "172 Seltjarnarnes",
                "190 Vogar",
                "200 Kópavogur",
                "201 Kópavogur",
                "202 Kópavogur",
                "203 Kópavogur",
                "210 Garðabær",
                "212 Garðabær",
                "220 Hafnarfjörður",
                "221 Hafnarfjörður",
                "222 Hafnarfjörður",
                "225 Álftanes",
                "230 Reykjanesbær",
                "232 Reykjanesbær",
                "233 Reykjanesbær",
                "235 Reykjanesbær",
                "240 Grindavík",
                "245 Sandgerði",
                "250 Garður",
                "260 Reykjanesbær",
                "270 Mosfellsbær",
                "271 Mosfellsbær",
                "276 Mosfellsbær",
                "300 Akranes",
                "301 Akranes",
                "302 Akranes",
                "310 Borgarnes",
                "311 Borgarnes",
                "320 Reykholt",
                "340 Stykkishólmur",
                "345 Flatey",
                "350 Grundarfjörður",
                "355 Ólafsvík",
                "356 Snæfellsbær",
                "360 Hellissandur",
                "370 Búðardalur",
                "371 Búðardalur",
                "380 Reykhólahreppur",
                "400 Ísafjörður",
                "401 Ísafjörður",
                "410 Hnífsdalur",
                "415 Bolungarvík",
                "420 Súðavík",
                "425 Flateyri",
                "430 Suðureyri",
                "450 Patreksfjörður",
                "451 Patreksfjörður",
                "460 Tálknafjörður",
                "465 Bíldudalur",
                "470 Þingeyri",
                "471 Pingeyri",
                "500 Staður",
                "510 Hólmavík",
                "512 Hólmavík",
                "520 Drangsnes",
                "524 Árneshreppur",
                "530 Hvammstangi",
                "531 Hvammstangi",
                "540 Blönduós",
                "541 Blönduós",
                "545 Skagaströnd",
                "550 Sauðárkrókur",
                "551 Sauðárkrókur",
                "560 Varmahlíð",
                "565 Hofsós",
                "566 Hofsós",
                "570 Fljót",
                "580 Siglufjörður",
                "600 Akureyri",
                "601 Akureyri",
                "602 Akureyri",
                "603 Akureyri",
                "610 Grenivík",
                "611 Grímsey",
                "620 Dalvík",
                "621 Dalvík",
                "625 Ólafsfjörður",
                "630 Hrísey",
                "640 Húsavík",
                "641 Húsavík",
                "645 Fosshóll",
                "650 Laugar",
                "660 Mývatn",
                "670 Kópasker",
                "671 Kópaskeri",
                "675 Raufarhöfn",
                "680 Pórshöfn",
                "681 Pórshöfn",
                "685 Bakkafjörður",
                "690 Vopnafjörður",
                "700 Fellabær og Egilsstaðir",
                "701 Egilsstaðir",
                "710 Seyðisfjörður",
                "715 Mjóifjörður",
                "720 Borgarfjörður eystri",
                "730 Reyðarfjörður",
                "735 Eskifjörður",
                "740 Neskaupstaður",
                "750 Fáskrúðsfjörður",
                "755 Stöðvarfjörður",
                "760 Breiðdalsvík",
                "765 Djúpivogur",
                "780 Höfn",
                "781 Höfn",
                "785 Öræfi",
                "800 Selfoss",
                "801 Selfoss",
                "802 Selfoss",
                "810 Hveragerði",
                "815 Porlákshöfn",
                "816 Ölfus",
                "820 Eyrarbakki",
                "825 Stokkseyri",
                "840 Laugarvatn",
                "845 Flúðir",
                "850 Hella",
                "851 Hella",
                "860 Hvolsvöllur",
                "861 Hvolsvöllur",
                "870 Vík",
                "871 Vík",
                "880 Kirkjubæjarklaustur",
                "900 Vestmannaeyjar",
                "902 Vestmannaeyjar",
        };

        hotelNames = new String[] {
                "Sapphire Point Hotel",
                "White Wolf Resort",
                "King's Lodge Resort",
                "Secret Camp Hotel",
                "Primal Creek Resort",
                "Oriental Seashore Hotel",
                "Asylum Hotel & Spa",
                "Rain Resort & Spa",
                "Shoreline Resort",
                "Seacoast Resort & Spa",
                "Primal Market Hotel",
                "Glorious Crown Hotel & Spa",
                "Primal Landscape Hotel",
                "Atlantic Ribbon Resort",
                "Obsidian Carnaval Motel",
                "Oracle Hotel",
                "Travel Hotel",
                "Prism Resort",
                "Dual Grotto Hotel",
                "Remote Nimbus Hotel",
                "Autumn Tundra Resort",
                "Northern Point Resort",
                "Private Bazaar Resort",
                "Golden Sanctum Hotel",
                "Palace Hotel",
                "Paragon Resort & Spa",
                "Monolith Hotel & Spa",
                "Panorama Motel",
                "Twin Season Resort",
                "Ebony Peaks Hotel",
                "Rose Luxury Hotel",
                "Elite Spa Resort & Spa",
                "Autumn Petal Hotel",
                "Outlook Hotel & Spa",
                "Monolith Hotel",
                "Expedition Hotel",
                "Ebony Expanse Resort",
                "White Haven Resort & Spa",
                "Viridian Phoenix Resort",
                "Grand Cosmos Resort",
                "Elite Willow Resort",
                "Amber Hotel & Spa",
                "Baroque Hotel & Spa",
                "Stargaze Hotel",
                "Secluded Park Hotel",
                "Ebony Rose Resort",
                "Glorious Rose Hotel",
                "Ancient Tropic Hotel",
                "Modest Pool Resort & Spa",
                "Harborview Resort & Spa",
                "Paradise Hotel & Spa",
                "Ocean Motel"
        };
        hotelAddressess = new String[] {
                "777 Brockton Avenue,",
                "30 Memorial Drive,",
                "250 Hartford Avenue",
                "700 Oak Street",
                "66-4 Parkhurst Rd",
                "591 Memorial Dr",
                "55 Brooksby Village Way",
                "137 Teaticket Hwy",
                "42 Fairhaven Commons Way",
                "374 William S Canning Blvd",
                "121 Worcester Rd",
                "677 Timpany Blvd",
                "337 Russell St",
                "295 Plymouth Street",
                "1775 Washington St",
                "280 Washington Street",
                "20 Soojian Dr",
                "11 Jungle Road",
                "301 Massachusetts Ave",
                "780 Lynnway",
                "70 Pleasant Valley Street",
                "830 Curran Memorial Hwy",
                "1470 S Washington St",
                "506 State Road",
                "742 Main Street",
                "72 Main St",
                "200 Otis Street",
                "180 North King Street",
                "555 East Main St",
                "555 Hubbard Ave-Suite 12",
                "300 Colony Place",
                "301 Falls Blvd",
                "36 Paramount Drive",
                "450 Highland Ave",
                "1180 Fall River Avenue",
                "1105 Boston Road",
                "100 Charlton Road",
                "262 Swansea Mall Dr",
                "333 Main Street",
                "550 Providence Hwy",
                "352 Palmer Road",
                "3005 Cranberry Hwy Rt 6 28",
                "250 Rt 59","Airmont",
                "141 Washington Ave Extension",
                "13858 Rt 31 W",
                "2055 Niagara Falls Blvd",
                "101 Sanford Farm Shpg Center",
                "297 Grant Avenue",
                "4133 Veterans Memorial Drive",
                "6265 Brockport Spencerport Rd",
                "5399 W Genesse St",
                "3191 County rd 10",
                "30 Catskill",
                "161 Centereach Mall",
                "3018 East Ave",
                "100 Thruway Plaza",
                "8064 Brewerton Rd",
                "5033 Transit Road",
                "3949 Route 31",
                "139 Merchant Place",
                "85 Crooked Hill Road",
                "872 Route 13",
                "279 Troy Road",
                "2465 Hempstead Turnpike",
                "6438 Basile Rowe",
                "25737 US Rt 11",
                "901 Route 110",
                "2400 Route 9",
                "10401 Bennett Road",
                "1818 State Route 3",
                "4300 Lakeville Road",
                "990 Route 5 20",
                "311 RT 9W",
                "200 Dutch Meadows Ln",
                "100 Elm Ridge Center Dr",
                "1549 Rt 9",
                "5360 Southwestern Blvd",
                "103 North Caroline St",
                "1000 State Route 36",
                "1400 County Rd 64",
                "135 Fairgrounds Memorial Pkwy",
                "2 Gannett Dr",
                "233 5th Ave Ext",
                "601 Frank Stottile Blvd",
                "350 E Fairmount Ave",
                "4975 Transit Rd",
                "579 Troy-Schenectady Road",
                "5783 So Transit Road",
                "7155 State Rt 12 S",
                "425 Route 31",
                "3222 State Rt 11",
                "200 Sunrise Mall",
                "43 Stephenville St",
                "750 Middle Country Road",
                "470 Route 211 East",
                "3133 E Main St",
                "288 Larkin",
                "41 Anawana Lake Road",
                "4765 Commercial Drive",
                "1201 Rt 300",
                "255 W Main St",
                "120 Commercial Parkway",
                "1400 Farmington Ave",
                "161 Berlin Road",
                "67 Newton Rd",
                "656 New Haven Ave",
                "69 Prospect Hill Road",
                "150 Gold Star Hwy",
                "900 Boston Post Road",
                "2300 Dixwell Ave",
                "495 Flatbush Ave",
        };

        // bokstaflega bara rugl
        hotelDescriptions = new String[] {
                "Maintaining, repairing, or restoring your home as provided by this license includes the following:",
        "Performing a home maintenance task: cleaning or repairing your kitchen or closets",
        "Restoring any items or items stolen or lost during the storage of items held in your home",
        "Removing items from your home",
        "Removing items from unclaimed areas like bathrooms or closets.",
                "Performing a home maintenance task at a place of worship or temple includes providing your house with a bath, spa, or public building.",
        "Performing a home maintenance task at:",
        "Any place or space that has a water supply system of a plumbing, sanitary, electric, heating or cooling type (see list below)",
        "Any area that meets any of the requirements of Section 30.01 to 30.01",
        "Any area that is required by Subpart D or Section 35.15, to provide adequate care for people who are homeless, injured, or blind or with epilepsy or a disability. (See list below)",
        "Performing your home maintenance tasks at a community place or a church will prevent items from falling from the walls or ceiling, making it impossible to retrieve them if items are damaged or missing.",
                "Removing items from your home (or from a place of worship) includes the following:",
        "Removing items stored in or under a structure (including, but not limited to, a garage",
                "My family had an excellent child while he was out there and we haven't tried to do anything to improve his. We've always looked for things to bring to him, some things to give him comfort and to help him think about what is in his heart. It's never been the same.",
        "At first we never even thought about the idea of taking more children to school. But soon thereafter we realised that we could bring an opportunity to their lives by the way they were raised and that we had a big impact. I'm grateful that someone like me raised this generation of kids here and that the other people are seeing the same. They have given me a special responsibility to do what is right for my daughter and our country - I'm honored to be part of their lives.",
        "We'll do our part to make sure that he's aware of the fact that kids are different to what they are. We won't wait for him to get his own home and to start loving the idea of education. And that was the first thing we did.",
        "The concept behind BJJ will revolutionise education in India. The idea is to connect children with the teachers who will bring them a home and start their own. Everyone has to think about BJJ and they must think about their children. This will help them,"
        };
        /*
        peopleNames = new String[] {
                "erur cahe",
                "ekhed hikhad",
                "dallul Stouttrapper",
                "grocrudd Crestgazer",
                "pardef kask",
                "hef glovrav",
                "blarcun Highguard",
                "grum Oatsky",
                "kah-kiovid nenzipvikt",
                "hiokad vindehr",
                "gemorgouk gremzevzaka",
                "jarvook rantamro",
                "ho iaoy",
                "tha kian",
                "zatercul hisisqin",
                "craintor gernoma",
                "nehnim pudu",
                "nisum shunon",
                "agrim Featherbrace",
                "dondim Dragonlance",
                "egrim gron",
                "vom velorsk",
                "mogrom Ravenwhisper",
                "rath Nosehammer",
                "jemuziz jandrechuld"
                "tiduf sathrab",
                "drorloodjek gredzirgugi",
                "bardet vatvelbe",
                "ey zim",
                "wap kun",
                "chairtavier mosqadran",
                "erte fovostes",
                "eirdeim shake",
                "nhabun khissar",
                "gio Grandcrag",
                "vigrirk Hawkbasher",
                "agif rug",
                "gal stirkadz",
                "tradvar Moonhell",
                "larth Shieldbluff",
                "keovuek-dor dazuldruhr",
                "tuhpu duldrud",
                "kousvizaud rekivzomri",
                "dethed zadovu",
                "qiay pua",
                "aw pue",
                "craresti elalbu",
                "chonchain mudrosqi"
        };
        */
        }

    public static void makeRooms(int n, HotelDB db, int hotelID, Hotel ht) {
        Random rnd = new Random();
        //int uniqueID = UUID.randomUUID().hashCode();
        for(int i = 0; i < n; i++) {
            Room rm = new Room(
                    i,       // should just be = i
                    hotelID,
                    rnd.nextInt(5) + 1, // between 1 and 4 beds
                    (rnd.nextInt(50) + 3) * 100, // between 3000 - 40000 isk
                    bools(),bools(),bools()
            );
            db.insertRoom(rm);
            //if (bools25()) bookTheRoom(db,ht);    // 25% of rooms are booked, can be adjusted
        }
    }

    public static void bookTheRoom(HotelDB db, Hotel ht) {
        Random rnd = new Random();
        LocalDate loc = LocalDate.of(
                rnd.nextInt(6) + 2017, // between 2017 - 2022
                rnd.nextInt(12) + 1,        // between 1-12
                rnd.nextInt(28) + 1);       // between 1-28,
        SearchOptions op = new SearchOptions(
                randomString(rnd.nextInt(13) + 7),  //CITY
                randomString(rnd.nextInt(13) + 7),  // NAME
                loc,
                loc.plusDays(rnd.nextInt(10) + 1),
                rnd.nextInt(8)+1
        );
        db.book(
                ht,
                randomString(rnd.nextInt(13) + 7) + "@gmail.com",
                randomString(rnd.nextInt(13) + 7),
                op
                );
    }
    public static void makeHotels(int n, HotelDB db) {
        Random rnd = new Random();
        for(int i = 0; i < n; i++) {
            Hotel ht = new Hotel(
                    i,
                    hotelNames[rnd.nextInt(hotelNames.length)],
                    hotelAddressess[rnd.nextInt(hotelAddressess.length)] + ", " + iceland_zipCode_city[rnd.nextInt(iceland_zipCode_city.length)],
                    hotelDescriptions[rnd.nextInt(hotelDescriptions.length)],
                    "src/images/hotel" + rnd.nextInt(16) + ".jpg",
                    rnd.nextInt(5)+1, // between 1-5 int
                    (rnd.nextInt(47000) + 3000) , // between 3000 - 50000 isk       alltaf .0 í endann
                    Math.round(rnd.nextDouble(3)*10.0)/10.0, // between 1 - 3 km?
                    Math.round(rnd.nextDouble(3)*10.0)/10.0,
                    bools(),bools(),bools(),bools(),bools()
            );
            db.insertHotel(ht);
            makeRooms(rnd.nextInt(291)+10,db,i,ht);  // make all the rooms corresponding to the hotelID, between 10 and 300 rooms per hotel

        }
    }
    private static boolean bools() {
        Random rnd = new Random();
        int var = rnd.nextInt(2);
        if (var == 1) return true;
        else return false;
    }
    private static boolean bools25() {
        Random rnd = new Random();
        int var = rnd.nextInt(4);
        if (var == 0) return true;
        else return false;
    }   // 25% chance we get true
    private static String randomString(int n) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < n; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
    public static void main(String[] args) {
       HotelDB db = new HotelDB();
       PopulateDatabase pop = new PopulateDatabase(100,db);    // til ad initializa oll nöfnin
        pop.makeHotels(10,db);
    }
}
