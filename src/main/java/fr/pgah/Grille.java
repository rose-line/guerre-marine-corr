package fr.pgah;

public class Grille {

  private int taille;
  private int nbNaviresRestants;
  private char[][] grille;

  public Grille(int taille) {
    this.taille = taille;
    this.nbNaviresRestants = taille;
    grille = new char[taille][taille];
    remplirDeVide();
  }

  private void remplirDeVide() {
    for (int ligne = 0; ligne < grille.length; ligne++) {
      for (int colonne = 0; colonne < grille.length; colonne++) {
        grille[ligne][colonne] = '-';
      }
    }
  }

  public void afficherComplete() {
    afficher(true);
  }

  public void afficherSansNavire() {
    afficher(false);
  }

  private void afficher(boolean afficherNavires) {
    System.out.print("  ");
    for (int colonne = 0; colonne < grille.length; colonne++) {
      System.out.print(colonne + " ");
    }
    System.out.println();
    for (int ligne = 0; ligne < taille; ligne++) {
      System.out.print(ligne + " ");
      for (int colonne = 0; colonne < taille; colonne++) {
        int[] coordonnees = {ligne, colonne};
        if (navireEstSur(coordonnees) && !afficherNavires) {
          System.out.print('-' + " ");
        } else {
          System.out.print(getCar(coordonnees) + " ");
        }
      }
      System.out.println();
    }
    System.out.println();
  }

  public int getNbNaviresRestants() {
    return nbNaviresRestants;
  }

  private void setCar(int[] coords, char car) {
    grille[coords[0]][coords[1]] = car;
  }

  private char getCar(int[] coords) {
    return grille[coords[0]][coords[1]];
  }

  public boolean navireEstSur(int[] coords) {
    int ligne = coords[0];
    int colonne = coords[1];
    return grille[ligne][colonne] == '@';
  }

  public void placerNavireSur(int[] coords) {
    setCar(coords, '@');
  }

  public boolean toutEstDetruit() {
    return nbNaviresRestants == 0;
  }

  public boolean aRecuTirSur(int[] coords) {
    return getCar(coords) == '0' || getCar(coords) == 'X';
  }

  public boolean recevoirTir(int[] coords) {
    if (navireEstSur(coords)) {
      setCar(coords, 'X');
      nbNaviresRestants--;
      return true;
    }
    setCar(coords, 'O');
    return false;
  }

}
