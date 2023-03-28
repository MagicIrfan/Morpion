# Morpion

Ce projet a été réalisé en Java, dans le but d'apprendre à utiliser JavaFX et FXML tout en faisant en sorte que le code soit le plus compréhensible possible grâce aux respect des principes SOLID et à l'utilisation de certains patrons de conception tels que :
* Builder : Ce patron a été implémenté afin de générer des boîtes de dialogue avec beaucoup de paramètres
* Visitor : Ce patron a été implémenté afin d'implémenter des algorithmes lorsqu'on clique sur une case du plateau
* Strategy : Ce patron a été implémenté afin d'implémenter des algorithmes de choix du plateau : choix du joueur ou choix du bot
* Factory : Ce patron a été implémenté dans le but d'instancier les cases du plateau dans une classe à part

L'application utilise aussi une architecture MVC (Modèle-Vue-Contrôleur), qui permet de séparer les responsabilités de l'application en trois parties distinctes : le modèle qui gère les données et la logique de l'application, la vue qui affiche les informations à l'utilisateur, et le contrôleur qui gère les interactions entre l'utilisateur et l'application. Cette architecture permet une meilleure maintenabilité et évolutivité de l'application, ainsi qu'une meilleure organisation du code.

Le jeu permet de jouer soit en un contre un, soit contre un bot. Le bot utilise une stratégie simple pour choisir ses coups.

# Comment jouer ?

Au lancement du jeu, vous pouvez choisir de jouer contre un autre joueur ou contre le bot. 

Le jeu se joue à tour de rôle. Le joueur O commence. Pour placer votre pion, cliquez sur la case correspondante. Le but est de réussir à aligner trois pions de sa propre couleur horizontalement, verticalement ou en diagonale.

Le jeu se termine lorsque l'un des joueurs a réussi à aligner trois pions de sa couleur ou lorsque le plateau est rempli sans qu'aucun joueur n'ait réussi à aligner ses pions.

# Comment lancer le jeu ?

Le jeu peut être lancé depuis l'IDE IntelliJ IDEA en ouvrant le projet et en exécutant la classe Main. Il est également possible compiler puis éxécuter l'application grâce aux commandes suivantes

```gradle build``` ceci va compiler le programme

```gradle run``` ceci va éxécuter le programme

# Le jeu

Le jeu a été conçu avec une interface graphique agréable et intuitive. Les cases du plateau sont représentées par des boutons, et les pions par des formes géométriques (O pour le joueur O et X pour le joueur X).
Fonctionnalités

Le jeu permet :

* De jouer à deux joueurs sur le même ordinateur
* De jouer contre un bot qui utilise une stratégie simple pour choisir ses coups

# Images

### Menu principal
![Menu principal](/readmeImages/menu.png)

### Jeu en cours
![Jeu en cours](/readmeImages/encours.png)

### Fin du jeu
![Victoire](/readmeImages/victoire.png)

# Auteur

Ce projet a été réalisé par moi-même. N'hésitez pas à me contacter pour toute question ou suggestion d'amélioration.


