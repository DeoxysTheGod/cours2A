Quentin BEAUQUIER

# Compte rendu TP4

## Question 1.

Commande : `docker pull mysql:latest`

## Question 2.

Commande : `docker run --name sql -e MYSQL_ROOT_PASSWORD=1234 -d mysql`

Le conteneur est maintenant lancé.

## Question 3.

On se connecte au docker avec `docker exec -it sql bash`

Une fois dans le prompt du conteneur on fait : `mysql -u root -p`

On peut désormais lancer les commandes mysql.

## Question 4.

On fait `dokcer commit sql uzaki/mysql:tp`

## Question 5.

On fait `docker push uzaki/mysql:tp`

## Question 6.

Lorsque qu'on créer un conteneur depuis cette image la database n'existe pas car un conteneur est éphémère.
