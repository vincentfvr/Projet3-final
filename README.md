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
* [Fichier des propriétés](#settings)
* [Fichier des logs](#logs)


## Build <a id="build"></a>
Version finale du projet.
Fonctionne avec Java™ SE Development Kit 11.0.4 (version JDK 11.0.4)

## Installation <a id="install"></a>
Exemple sur PC Windows 10

### Version JDK <a id="jdkVerion"></a>
Vérifier la version dejà installée :

- Ouvrir l'invite de commande : ou utiliser l'outil de recherche Windows (ou raccourci "Windows + R") et taper ``cmd``
- pour JDK (Compiling Java Code), taper la commande suivante : ``javac -version``
- pour JRE (Running Java Code), taper la commande suivante : ``java -version``

Il faut 11.0.4 ou supérieure pour les deux. Sinon se rendre sur le site oracle suivant, puis télécharger et installer la bonne version :
https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html


### Compiler le projet <a id="compilingJavaProject"></a>
ATTENTION : pour les systèmes Unix (Linux, macOS) il faut remplacer le symbole de séparation de chemin ";" par ":")

Toujours dans l'invite de commande, ouvrir le dossier contenant le projet commande : ``cd [path]``<br>
Remplacer [path] avec par exemple ``C:\Users\nomUtilisateur\Documents\Workplace\Projet3-final``<br>
(Remplacer "nomUtilisateur" par le nom de l'utilisateur windows)<br>
Taper ``cd C:\Users\nomUtilisateur\Documents\Workplace\Projet3-final``

et tapez la commande suivante pour compiler le projet :<br>
``javac -d bin -sourcepath src -encoding UTF-8 -cp lib/log4j-1.2.17.jar src/com/ocr/vincent/Main.java``

Un dossier nommé bin va être créé dans le dossier racine du projet, contenant les fichiers .class

### Exécuter le projet  <a id="runningJavaProject"></a>
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

### Fichier des propriétés  settings.properties<a id="settings"></a>
``combLen`` valeur par défaut : 4. Nombre de chiffres de la combinaison secrète<br>
``nbTryLimit`` valeur par défaut : 6. Nombre d'essais max pour tous les modes de jeux<br>
``devMode`` valeur par défaut : false. Permet d'activité directement le mode développeur


### Fichier des logs : log/loging.log<a id="logs"></a>
Contient les logs de toutes les parties effectuées et des messages d'erreur.

