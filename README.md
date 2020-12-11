# Projet PhineLoops, équipe alt-loop

Membres de l'équipe : Alexandre Schwitthal, Liam Kormann, Thomas Leclerc



Pour compiler le projet, éxécuter la commande suivante dans le dossier du projet :
	mvn package



Pour générer une grille, éxécuter la commande suivante dans le dossier du projet :

	java -jar target/phineloops-1.0-jar-with-dependencies.jar --generate HxW --output outputFile
	
où H et W sont respectivement la hauteur et la largeur de la grille, exprimés en nombres entiers et outputFile le nom de fichier de sortie voulu

Par exemple, cette commande générera un fichier exempleGenerate.dat représentant une grille 84x84 :

	java -jar target/phineloops-1.0-jar-with-dependencies.jar --generate 84x84 --output exempleGenerate



Pour vérifier si une grille est une solution, éxécuter la commande suivante dans le dossier du projet :

	java -jar target/phineloops-1.0-jar-with-dependencies.jar --check inputFile
	
où inputFile est le fichier contenant la grille à vérifier

Par exemple, la commande suivante testera le fichier grid_8x8_dist.0_vflip.false_hflip.false_messedup.false_id.3.dat contenu dans le dossier des instances :

	java -jar target/phineloops-1.0-jar-with-dependencies.jar --check instances/public/grid_8x8_dist.0_vflip.false_hflip.false_messedup.false_id.3.dat



Pour lancer le solver, éxécuter la commande suivante dans le dossier du projet :

	java -jar target/phineloops-1.0-jar-with-dependencies.jar --solve inputFile --output outputFile
	
où inputFile est le fichier contenant la grille à résoudre et outputFile le fichier contenant la grille après traitement du solver

Par exemple, la commande suivante générera le fichier result.dat représentant la sortie du solver quand il tente de corriger le fichier grid_8x8_dist.0_vflip.false_hflip.false_messedup.false_id.3.dat

	java -jar target/phineloops-1.0-jar-with-dependencies.jar --solve instances/public/grid_8x8_dist.0_vflip.false_hflip.false_messedup.false_id.3.dat --output result 
	
Pour lancer le solver en version Multithread, éxécuter la commande suivante dans le dossier du projet :

	java -jar target/phineloops-1.0-jar-with-dependencies.jar --solve inputFile --output outputFile -t nbThreads
	
où inputFile est le fichier contenant la grille à résoudre et outputFile le fichier contenant la grille après traitement du solver

et nbThreads le nombre de threads souhaités pour résoudre la grille

Par exemple, la commande suivante lancera une résolution à 3 threads sur le fichier grid_256x256_dist.6_vflip.false_hflip.true_messedup.false_id.0.dat et générera le fichier result.dat en sortie :

	java -jar target/phineloops-1.0-jar-with-dependencies.jar --solve instances/public/grid_256x256_dist.6_vflip.false_hflip.true_messedup.false_id.0.dat --output result -t 3



Pour lancer l'interface graphique, éxécuter la commande suivante dans le dossier du projet :

	java -jar target/phineloops-1.0-jar-with-dependencies.jar --gui HxW
	
où H et W sont respectivement la hauteur et la largeur de la grille, exprimés en nombres entiers

Note : l'interface graphique ne pourra pas être générée si une des deux dimensions est supérieure ou égale à 17

Par exemple, la commande suivante ouvrira l'interface graphique pour manipuler une grille 8x8 :

	java -jar target/phineloops-1.0-jar-with-dependencies.jar --gui 8x8

L'utilisateur peut intéragir en cliquant à plusieurs endroits de l'interface :

	- Le bouton "Mélanger" mélange aléatoirement la grille actuelle
	
	- Le bouton "Résolution" lance un solver monothread sur la grille actuelle
	
	- Le bouton "Check" affiche une fenêtre "YOU WIN" si la grille, dans son état actuel, est résolue. Elle affiche "TRY AGAIN..." si la grille n'est pas résolue
	
	- Le bouton "New Game" initialise une nouvelle grille selon les dimensions définies au lancement de l'interface
	
	- Un clic sur une pièce de la grille la fait tourner de 90 degrés dans le sens des aiguilles d'une montre 
