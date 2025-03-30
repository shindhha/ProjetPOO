package canardfigther;

import canardfigther.canards.Canard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BattleManager {

    // Constructeur de canards et liste des canards disponibles
    private Canard.CanardBuilder canardBuilder;
    private List<Canard> availableCanards;
    private Scanner scanner;
    private Canard canard1;
    private Canard canard2;

    // Constructeur principal, initialise avec des objets externes
    public BattleManager(Canard.CanardBuilder canardBuilder,
                         List<Canard> availableCanards,
                         Scanner scanner) {
        this.availableCanards = availableCanards;
        this.canardBuilder = canardBuilder;
        this.scanner = scanner;
    }

    // Constructeur par défaut, initialise des valeurs par défaut
    public BattleManager() {
        this(new Canard.CanardBuilder(), new ArrayList<>(), new Scanner(System.in));
    }

    // Ajoute un canard à la liste des canards disponibles
    public void addCanard(Canard canard) {
        availableCanards.add(canard);
    }

    // Permet à l'utilisateur de créer un canard avec des attributs personnalisés
    public void creerCanard() {
        System.out.println("Entrez le nom du canard : ");
        String nom = scanner.nextLine();

        // Vérification si un canard avec ce nom existe déjà
        boolean exists = availableCanards.stream()
                .map(Canard::getNom)
                .anyMatch(n -> n.equalsIgnoreCase(nom));
        if (exists) {
            throw new IllegalStateException("Un canard avec ce nom existe déjà !");
        }

        System.out.println("Choisissez le type de canard (EAU, FEU, GLACE, VENT, ELECTRIQUE, TOXIQUE, SOL) : ");
        String type = scanner.nextLine().toUpperCase();

        System.out.println("Combien de points de vie ? :");
        int pdv = scanner.nextInt();

        System.out.println("Combien de points d'attaque ? :");
        int atk = scanner.nextInt();

        System.out.println("Combien de points de vitesse ? :");
        int vit = scanner.nextInt();

        // Création d'un canard via le builder
        Canard canard = canardBuilder.type(type).nom(nom).pointsDeVie(pdv).pointsAttaque(atk).vitesse(vit).getCanard();
        System.out.println("Canard créé : " + canard.getNom());
        addCanard(canard);
    }

    // Permet à l'utilisateur de choisir un canard parmi la liste disponible
    public Canard chooseCanard() {
        Canard canard = null;
        while (canard == null) {
            for (int i = 0; i < availableCanards.size(); i++) {
                System.out.println((i + 1) + ". " + availableCanards.get(i).getNom());
            }
            int indexCanard1 = scanner.nextInt();
            scanner.nextLine();

            if (indexCanard1 < 1 || indexCanard1 > availableCanards.size()) {
                System.out.println("Index invalide, essayez à nouveau.");
                return null;
            }

            canard = availableCanards.get(indexCanard1 - 1);
        }
        return canard;
    }

    // Lance un combat entre deux canards choisis par l'utilisateur
    public void launchBattle() {
        try {
            setUpChallengers();
            Canard attacker = canard1;
            Canard defender = canard2;
            Canard temp;

            System.out.println("Bataille entre " + canard1.getNom() + " et " + canard2.getNom());

            // Boucle de combat jusqu'à ce qu'un canard soit KO
            while (!canard1.estKO() && !canard2.estKO()) {
                try {
                    nextTurn(attacker, defender);
                    temp = attacker;
                    attacker = defender;
                    defender = temp;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            // Affichage du vainqueur
            if (canard1.estKO()) {
                System.out.println(canard2.getNom() + " gagne !");
            } else {
                System.out.println(canard1.getNom() + " gagne !");
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    // Configuration des challengers avant le combat
    private void setUpChallengers() {
        if (availableCanards.size() < 2) {
            throw new IllegalStateException("Vous devez créer au moins deux canards pour lancer une bataille !");
        }

        System.out.println("Choisissez le premier canard : ");
        canard1 = canardBuilder.copyCanard(chooseCanard());

        do {
            System.out.println("Choisissez le deuxième canard : ");
            canard2 = canardBuilder.copyCanard(chooseCanard());

            if (canard1.equals(canard2)) {
                System.out.println("Vous ne pouvez pas choisir le même canard !");
            }
        } while (canard1.equals(canard2));
    }

    // Gère le tour d'un canard durant le combat
    public void nextTurn(Canard attacker, Canard defender) {
        System.out.println("C'est au tour de " + attacker.getNom() + ": pv : " + attacker.getPointsDeVie());

        System.out.println("1. Attaquer");
        System.out.println("2. Utiliser capacité spéciale");

        int choix = scanner.nextInt();

        switch (choix) {
            case 1:
                attacker.attaquer(defender);
                break;
            case 2:
                SpecialEffect effect = attacker.utiliserCapaciteSpeciale();
                effect.apply(defender);
                break;
        }
    }

    // Affichage du menu principal
    public void displayMenu() {
        System.out.println("Bienvenue dans Canard Fighter Simulator !");

        while (true) {
            System.out.println("1. Créer un canard");
            System.out.println("2. Lancer une bataille");
            System.out.println("3. Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choix) {
                    case 1:
                        creerCanard();
                        break;
                    case 2:
                        launchBattle();
                        break;
                    case 3:
                        scanner.close();
                        System.out.println("Au revoir !");
                        System.exit(0);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Une erreur est survenue : " + e.getMessage());
            }
        }
    }
}