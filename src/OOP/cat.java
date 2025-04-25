package OOP;

public class cat implements animal {
    String name;

    public cat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void speak(){
        System.out.println("mnau");;
    }

    @Override
    public void play() {
        System.out.println("hraju si demente");
    }

    public static void main(String[] args) {
        cat kocka = new cat("micka");
        kocka.play();
    }
}
