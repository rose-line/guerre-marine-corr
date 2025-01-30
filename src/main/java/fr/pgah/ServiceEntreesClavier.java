package fr.pgah;

import java.util.Scanner;

/**
 * La classe ServiceEntreesClavier représente un service permettant de demander des entrées à
 * l'utilisateur via le clavier. Elle offre des méthodes pour configurer les messages de prompt et
 * d'erreur, ainsi que pour demander les entrées et les parser.
 */
public class ServiceEntreesClavier {

  private String messagePrompt = "? ";
  private String messageErreur = "Entrée incorrecte.";
  private int nbEntrees;
  private String separation;
  private int limiteInferieure;
  private int limiteSuperieure;
  private Scanner clavier;

  /**
   * Constructeur de la classe ServiceEntreesClavier.
   *
   * @param nbEntrees Le nombre d'entrées attendues.
   * @param sepRegex La séparation utilisée pour diviser les entrées.
   * @param limiteInf La limite inférieure pour les entrées.
   * @param limiteSup La limite supérieure pour les entrées.
   */
  public ServiceEntreesClavier(int nbEntrees, String sepRegex, int limiteInf, int limiteSup) {
    clavier = new Scanner(System.in);
    this.nbEntrees = nbEntrees;
    this.separation = sepRegex;
    this.limiteInferieure = limiteInf;
    this.limiteSuperieure = limiteSup;
  }

  /**
   * Définit le message d'invite affiché lors de la demande d'entrée.
   *
   * @param message Le message d'invite.
   */
  public void setMessagePrompt(String message) {
    this.messagePrompt = message;
  }

  /**
   * Définit le message d'erreur affiché en cas d'entrée incorrecte.
   *
   * @param message Le message d'erreur.
   */
  public void setMessageErreur(String message) {
    this.messageErreur = message;
  }

  /**
   * Demande les entrées à l'utilisateur et les retourne sous forme d'un tableau d'entiers.
   *
   * @return Le tableau d'entiers représentant les entrées.
   */
  public int[] demanderEntrees() {
    int[] entreesSeparees = null;
    boolean entreesInvalides = true;
    while (entreesInvalides) {
      afficherMessageEntree();
      String entrees = clavier.nextLine().trim();
      try {
        entreesSeparees = parserEntrees(entrees);
        entreesInvalides = false;
      } catch (NumberFormatException e) {
        afficherMessageErreur();
        entreesInvalides = true;
      }
    }
    return entreesSeparees;
  }

  /**
   * Parse les entrées en les séparant et les convertissant en entiers.
   *
   * @param entrees Les entrées à parser.
   * @return Le tableau d'entiers représentant les entrées.
   */
  private int[] parserEntrees(String entrees) {
    String[] entreesSplitees = entrees.split(separation);
    validerNombreDEntrees(entreesSplitees);
    int[] nombres = new int[nbEntrees];
    for (int i = 0; i < nbEntrees; i++) {
      nombres[i] = parserEntree(entreesSplitees[i]);
    }
    return nombres;
  }

  /**
   * Valide le nombre d'entrées en comparant avec le nombre attendu.
   *
   * @param entreesSplitees Les entrées séparées.
   * @throws NumberFormatException Si le nombre d'entrées est incorrect.
   */
  private void validerNombreDEntrees(String[] entreesSplitees) {
    if (entreesSplitees.length != nbEntrees) {
      throw new NumberFormatException();
    }
  }

  /**
   * Parse une entrée en la convertissant en entier.
   *
   * @param entree L'entrée à parser.
   * @return L'entier représentant l'entrée.
   * @throws NumberFormatException Si l'entrée est incorrecte.
   */
  private int parserEntree(String entree) {
    int nombre = Integer.parseInt(entree);
    if (esthorsLimites(nombre)) {
      throw new NumberFormatException();
    }
    return nombre;
  }

  /**
   * Vérifie si un nombre est hors des limites spécifiées.
   *
   * @param nb Le nombre à vérifier.
   * @return {@code true} si le nombre est hors des limites, {@code false} sinon.
   */
  private boolean esthorsLimites(int nb) {
    return nb < limiteInferieure || nb > limiteSuperieure;
  }

  /**
   * Affiche le message d'invite pour la saisie d'une entrée.
   */
  private void afficherMessageEntree() {
    System.out.print(messagePrompt + " : ");
  }

  /**
   * Affiche le message d'erreur en cas d'entrée incorrecte.
   */
  private void afficherMessageErreur() {
    System.out.println(messageErreur);
  }

  /**
   * Attend que l'utilisateur appuie sur une touche quelconque pour continuer.
   */
  public void attendreFrappe() {
    System.out.println("Appuyez sur une Entrée pour continuer...");
    clavier.nextLine();
  }
}
