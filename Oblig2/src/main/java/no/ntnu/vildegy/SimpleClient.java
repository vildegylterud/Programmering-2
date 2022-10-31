package no.ntnu.vildegy;

/**
 * Oppgave 3 oblig2, SimpleClient
 */

import java.time.LocalDate;

public class SimpleClient {
    public static void main(String[] args) {

        //Oppretter et bonusmedlem med initiell bonuspoeng saldo

        BonusMember member = new BonusMember(2, LocalDate.now(), 70000, "Vilde Gylterud", "vilde@blabla");

        //skriver ut medlemmets detaljer(minimum medlemsnr, navn, bonuspoeng, og medlemsskapsnivå)

        System.out.println(member.toString());

        //registrerer nye bonuspoeng for medlemmet, feks tilstrekkelig for at meldelmmet går opp i nivå

        member.registerBonusPoints(10000);

        System.out.println(member.getBonusPointsBalance());

        //skriv ut medlemmets detaljer igjen og kontroller 1. har fått utregnet riktig ny saldo 2. har fått riktig medlemsskapsnivå

        System.out.println(member.toString());


    }
}

