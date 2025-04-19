package Streamy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparingPractical {

    static class Song{
        String name;
        int yearOfrealese;
        double rating;

        public Song(String name, int yearOfrealese, double rating) {

            this.name = name;
            this.yearOfrealese = yearOfrealese;
            this.rating = rating;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getYearOfrealese() {
            return yearOfrealese;
        }

        public void setYearOfrealese(int yearOfrealese) {
            this.yearOfrealese = yearOfrealese;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        @Override
        public String toString() {
            return name + " (" + yearOfrealese + ") - rating: " + rating;
        }
    }
    public static void main(String[] args) throws IOException {
        List<Song> Songs = (List<Song>) Files.lines(Paths.get("Songs.txt"))
                .map(line -> line.split(";"))
                .map(split -> new Song(
                        split[0],
                        Integer.parseInt(split[1]),
                        Double.parseDouble(split[2])
                )).toList();

        //top 3 pisnicky
        Songs.stream()
                .sorted(Comparator.comparing(Song::getRating))
                .limit(3)
                .forEach(System.out::println);


        //podle jmena, (vypíše seznam seřazený abecedně dle jména)
        Songs.stream()
                .sorted(Comparator.comparing(Song::getName))
                .forEach(System.out::println);

        //podle roku vydani
        Songs.stream()
                .sorted(Comparator.comparing(Song::getYearOfrealese))
                .forEach(System.out::println);

    }
}
