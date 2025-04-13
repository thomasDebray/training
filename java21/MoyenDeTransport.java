import java.util.List;

public class MoyenDeTransport {
    public record Roue(int diametre) {}
    public record Moteur(int nombreDIN, String energie) {}
    public record Voiture(Roue avantGauche, Roue avantDroite, Roue arriereGauche, Roue arriereDroite, Moteur moteur) {}
    public record Moto(Roue avant, Roue arriere, Moteur moteur) {}

    static void describe(Object obj) {
        switch (obj) {
            case Voiture(Roue roue1,Roue roue2, Roue roue3, Roue roue4, Moteur moteur) ->
                System.out.println("Le véhicule est une voiture avec des roues de " + roue1.diametre + " pouces avec un moteur à énergie " + moteur.energie + " de " + moteur.nombreDIN + " CV");
            case Moto(Roue roue1,Roue roue2, Moteur moteur) ->
                System.out.println("Le véhicule est une moto avec des roues de " + roue1.diametre + " pouces avec un moteur à énergie " + moteur.energie + " de " + moteur.nombreDIN + " CV");
            default ->
                System.out.println("Type inconnu");
        }       
    }
    public static void main(String[] args) {
        Voiture voiture1 = new Voiture(new Roue(17), new Roue(17), new Roue(17), new Roue(17), 
        new Moteur(200,"electrique"));

        Moto moto1 = new Moto(new Roue(20), new Roue(22), new Moteur(300, "fossile"));
        
        List<Object> maListe= List.of(voiture1,moto1); 
        
        for (Object object : maListe) {
            describe(object);
        }
    }
}
