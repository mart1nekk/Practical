package Streamy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Health {
    static class osoba{
        String jmeno;
        int vek;
        String pohlavi;
        String KrevniSkupina;
        double castka;

        public osoba(String jmeno, int vek, String pohlavi, String krevniSkupina, double castka) {
            this.jmeno = jmeno;
            this.vek = vek;
            this.pohlavi = pohlavi;
            KrevniSkupina = krevniSkupina;
            this.castka = castka;
        }

        public String getJmeno() {
            return jmeno;
        }

        public void setJmeno(String jmeno) {
            this.jmeno = jmeno;
        }

        public int getVek() {
            return vek;
        }

        public void setVek(int vek) {
            this.vek = vek;
        }

        public String getPohlavi() {
            return pohlavi;
        }

        public void setPohlavi(String pohlavi) {
            this.pohlavi = pohlavi;
        }

        public String getKrevniSkupina() {
            return KrevniSkupina;
        }

        public void setKrevniSkupina(String krevniSkupina) {
            KrevniSkupina = krevniSkupina;
        }

        public double getCastka() {
            return castka;
        }

        public void setCastka(double castka) {
            this.castka = castka;
        }
    }

    public static void main(String[] args) throws IOException {
        List<osoba> idk = (List<osoba>) Files.lines(Paths.get("health.csv"))
                .map(line -> line.split(","))
                .map(split -> new osoba(
                        split[0],
                        Integer.parseInt(split[1]),
                        split[2],
                        split[3],
                        Double.parseDouble(split[4])
                )).toList();


        //Kolik darcu ma krevni skupinu A- a jsou ve věku od 20 do 50 let?
        idk.stream()
                .filter(s -> s.getKrevniSkupina().equals("A-") && s.getVek() > 20 && s.getVek()< 50 )
                .forEach(System.out::println);

        //kolik procent všech dárců tvoří ti, jenž mají skupinu 0+ a 0-?
        long countDarci = idk.stream()
                .filter(s -> s.getKrevniSkupina().equals("O+") || s.getKrevniSkupina().equals("O-"))
                .count();
        double percentage = ((double) countDarci/idk.size()) *100;
        System.out.println(" ti kteri maji skupina ----" + percentage);

        //Kolikrát se mezi ženami-dárkyněmi nachází duplicitní záznamy?
        Long duplicates = idk.stream()
                .map(osoba::getJmeno)
                .distinct()
                .count();
        System.out.println("duplicitních bylo"+ ( idk.size() - duplicates));

        //Vdsypište do konzole všechny dárce, kteří platili částku pod 10 000 a zároveň jsou z pozivivních krevních skupin (všechny + skupiny)
        idk.stream()
                .filter(s -> s.getCastka()< 10000 && s.getKrevniSkupina().equals("A+") || s.getCastka()< 10000 && s.getKrevniSkupina().equals("B+") || s.getCastka()< 10000 && s.getKrevniSkupina().equals("AB+")||s.getCastka()< 10000 && s.getKrevniSkupina().equals("O+") )
                .forEach(s -> System.out.println(s.getJmeno()));

        //do nové kolekce ulože všechny dárce, kteří mohou darovat člověku s krevní skupinou B+ (to jsou obě 0 a obě B)
        List<osoba> darci = idk.stream()
                .filter(s -> s.getKrevniSkupina().equals("O+") ||
                        s.getKrevniSkupina().equals("O-") ||
                        s.getKrevniSkupina().equals("B+") ||
                        s.getKrevniSkupina().equals("B-"))
                .collect(Collectors.toList());
        System.out.println(darci);


       /* Map<String,Double> manss = idk.stream()
                .filter(s -> s.getPohlavi().equals("Male"))
                .collect(Collectors.groupingBy(osoba::getPohlavi),Collectors.averagingDouble(osoba::getCastka));
        manss.forEach((pohlavi, prumer) -> System.out.println(pohlavi + ": " + prumer));
        */

        //spočítejte průměrnou částku, kterou zaplatily pojišťovny při hospitaci všem mužům
        double prumernaCastka = idk.stream()
                .filter(s ->s.getPohlavi().equals("Male"))
                .collect(Collectors.averagingDouble(osoba::getCastka));
        System.out.println(prumernaCastka);

        //spocitejte castku, kterou zaplatily pojišťovny při hospitaci všem ženám
        double celkovaCastka = idk.stream()
                .filter(s -> s.getPohlavi().equals("Female"))
                .mapToDouble(osoba::getCastka)
                .sum();
        System.out.println(celkovaCastka);







    }
}