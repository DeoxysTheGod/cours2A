Quentin BEAUQUIER

# Compte rendu TP3

## Question 1.

j'ai lancé un conteneur nginx en mode daemon avec le nom tp_hostname

Commande : `docker run -d -p 8002:80 --name tp_hostname nginx`

En faisant `docker ps` on peut voir qu'il est bien lancé

## Question 2.

je lance la commande : `docker cp tp_hostname:/usr/share/nginx/html .`

ensuite avec nano je modifi le fichier index.html en rajoutant mon nom dans une balise h2.

Ensuite je renvoi le dossier html

En faisant `docker cp . tp_hostname:/usr/share/nginx/`

En allant sur `localhost:8002` je vois bien ma ligne que j'ai rajouté.

## Question 3.

Je lance la commande `docker commit tp_hostname uzaki/nginx:tp`

## Question 4.

Je push avec la commande `docker push uzaki/nginx:tp`

## Question 5.

Je lance le nouveau conteneur `docker run -d -p 8082:80 --name tp_myimage uzaki/nginx:tp`

Si on va sur le `localhost:8082` on voit bien que le site affiche mon nom tel que je l'avais défini dans la question 2
