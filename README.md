# Projet 3
Escape Game ONLINE - GAMEPLAY STUDIO

Auteur : Favre Vincent

Sommaire
---
* [Build](#build)
* [Installation](#install)
	* [1. Vérification version JDK](#jdkVersion)
	* [2. Compiler le projet Java](#compilingJavaProject)
	* [3. Exécuter le projet Java](#runningJavaProject)
* [Activer le mode développeur](#devMode)

## Build <a id="build"></a>
Version finale du projet.
Fonctionne avec Java™ SE Development Kit 11.0.4 (version JDK 11.0.4)

## Installation <a id="install"></a>
Exemple sur PC Windows 10

### Version JDK <a id="jdkVerion"></a>
Vérifier la version dejà installée :

- Ouvrir l'invite de commande (raccourci "Windows + X") : ou utiliser l'outil de recherche Windows  et taper ``cmd``
- pour JDK (Compiling Java Code), taper la commande suivante : ``javac -version``
- pour JRE (Running Java Code), taper la commande suivante : ``java -version``

Il faut 11.0.4 ou supérieure pour les deux. Sinon se rendre sur le site oracle suivant, puis téléchrger et installer la bonne version :
https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html


### Compiler le projet <a id="compilingJavaProject"></a>
ATTENTION : pour les systèmes Unix (Linux, macOS) il faut remplacer le symbole de séparation de chemin ";" par ":")

Toujours dans l'invite de commande : ouvrir le dossier contenant le projet commande cd Path
Exemple de path ``C:\Users\nomUtilisateur\Documents\Workplace\Projet3-final``<br>
Taper la commande ``cd C:\Users\nomUtilisateur\Documents\Workplace\Projet3-final``
(Remplacer "nomUtilisateur" par le nom de l'utilisateur windows)
et tapez la commande suivante :
<br>
``javac -d bin -sourcepath src -encoding UTF-8 -cp lib/log4j-1.2.17.jar src/com/ocr/vincent/Main.java``

Un dossier nommé bin va être créé dans le dossier racine du projet, contenant les fichiers .class

### Executer le projet  <a id="runningJavaProject"></a>
Enfin, toujours dans l'invite de commande et dans le dossier du projet, taper la commande suivante pour lancer le projet : <br>
``java -cp bin;lib/log4j-1.2.17.jar;log;resources com.ocr.vincent.Main``

### Utilisation du jeu <a id="useProject"></a>

3 modes de jeu sont proposés : 1 - CHALLENGER, 2 - DEFENDER, 3 - DUEL

* ``1. MODE CHALLENGER`` : Le joueur CPU crée une combinaiosn, le joueur USER doit la retrouver 
* ``2. MODE DEFENDER``: Le joueur USER crée une combinaison, le joueur CPU doit la retrouver
* ``3. MODE DUAL``: Les 2 joueurs créent une combinaison, et font des propositions à tour de rôle.

Taper 1,2 ou 3 et valider avec la touche ENTER<br>
A la fin d'une partie, possibilité d'en relancer une nouvelle ou de quitter. Taper 1 pour rejouer ou 2 pour quitter.
___
### Activer le mode développeur <a id="devMode"></a>
Au moment de choisir un mode de jeux, taper ``123`` pour activer le mode développeur.
Il est aussi possible d'activer ce mode par défaut en modifiant dans resources/settings.properties la valeur ``devMode = true``
___
