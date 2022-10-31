import java.util.Arrays;

public class Tekstbehandling {

    private String tekst;

    //Konstruktør
    public Tekstbehandling(String tekst) {
        this.tekst = tekst;
    }


    //finne antall ord i teksten

    public int antallOrd(String tekst) {
        String[] oppdeltOrd = tekst.split("\\s+");
        return oppdeltOrd.length;
    }

    //finne gjennomsnittlig ordlengde (-skilletekst)


    public double gjennomsnittligOrdlengde(String tekst) {
        int antallOrd = antallOrd(tekst);
        int ordlengde = totalOrdlengde(tekst);
        double gjennomsnitt = ordlengde / antallOrd;
        return gjennomsnitt;
    }


    public int totalOrdlengde(String tekst) {
        int teller = 0;
        String[] oppdeltOrd = tekst.split("\\s+");
        for (int i = 0; i < oppdeltOrd.length; i++) {
            teller += oppdeltOrd[i].length();
        }
        return teller;
    }


    //finne gjennomsnittlig antall ord per periode
    // antall ord /antall perioder = gjennomsnitt


    public double gjennomsnittligPeriodelengde(String tekst) {
        int antallPerioder = totalAntallPerioder(tekst);
        double gjennomsnittPeriode = antallOrd(tekst) / (double) antallPerioder;
        return gjennomsnittPeriode;
    }

    public int totalAntallPerioder(String tekst) {
        String[] antallPerioder = tekst.split("[\\.\\!\\?]");
        return antallPerioder.length;
    }


    //skifte et ord med et annet i en hel tekst


   public String bytteOrd(String tekst) {
       String[] ord = tekst.split("\\s+");
       for(int i = 0; i < ord.length; i++) {
           if (ord[i].equals("synes")) {
               ord[i] = "syns";
           }
       }
       return Arrays.toString(ord);
    }

    //hente ut teksten slik den står uten endringer

    public String orginalTekst(String tekst) {
        return tekst;
    }

    //hente ut teksten, men slik at alle bokstaver er store

    public String storeBokstaverTekst(String tekst) {
       String storeBokstaver = tekst.toUpperCase();
       return storeBokstaver;
    }


    //Testprogram
    public static void main(String[] args) {
        Tekstbehandling testTekst = new Tekstbehandling("Hei! Jeg heter Vilde. Jeg synes progging er vanskelig! synes du det er vanskelig?");

        System.out.println(testTekst.antallOrd(testTekst.tekst));
        System.out.println(testTekst.gjennomsnittligOrdlengde(testTekst.tekst));
        System.out.println(testTekst.gjennomsnittligPeriodelengde(testTekst.tekst));
        System.out.println(testTekst.orginalTekst(testTekst.tekst));
        System.out.println(testTekst.storeBokstaverTekst(testTekst.tekst));
        System.out.println(testTekst.bytteOrd(testTekst.tekst));

        //System.out.println(testTekst.totalAntallPerioder(testTekst.tekst));


    }
}



