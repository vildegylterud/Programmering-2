public class NyString {

    //Objektsvariabler

    private String string1;

    //Konstruktør

    public NyString(String string2) {

        this.string1 = string2;
    }


    public String Forkort(String ordSomSkalForkortes) { // Metoden forkort
        String[] arr = ordSomSkalForkortes.split("\\s+");  //  splitter opp alle ordene med mellomrom
        String forkortet = "";   //lager en tom string som heter forkortet
        for(int i = 0; i < arr.length; i++) {  //
            forkortet += arr[i].charAt(0);   //henter ut første bokstav i lista og legger til i forkortet
        }
        return forkortet;           //returnerer forkortet
    }

    public String FjernTegn(String ord) {    //metoden FjernTegn tar inn ord
        boolean finished = false;     //lager boolean og setter den false

        if(ord.indexOf('e') == -1) {      //hvis det ikke finnes noen e sett finished lik true
            finished = true;
        }

        while(!finished) {      //løkka kjører hvis finished ikke er true
            int place = ord.indexOf('e');   //
            ord = ord.substring(0,place) + ord.substring(place+1, ord.length());    //leter etter e i 1. plass, hvis ingen e går den til neste og leter osv
            if(ord.indexOf('e') == -1) {           //   hvis den ikke finner flere går den ut av løkka
                finished = true;      //
            }
        }
        return ord;      //når løkka er ferdig returnerer den ordet uten alle e-ene
    }

    public static void main(String[] args) {   //main-metode
        String ord = "Jeg heter Vilde";    //    lager en string
        String etAnnetOrd = "På grunn av";   //     lager en string
        NyString nyStringObjekt = new NyString(ord);       //bruker konstruktøren NyString til å ta inn verdien ord 
        String forkortetOrd = nyStringObjekt.Forkort(etAnnetOrd);   //
        System.out.println(forkortetOrd);  //printer ut forkortetOrd
        String forkortetObjektVariabel = nyStringObjekt.Forkort(nyStringObjekt.string1);
        System.out.println(forkortetObjektVariabel); //printer ut forkortetObjektVariabel
        System.out.println(nyStringObjekt.FjernTegn("denne setningen kan forkortes"));
    }
}
