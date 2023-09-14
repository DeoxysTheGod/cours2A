#include <arpa/inet.h>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define TAILLE_BUFFER 5 //petite taille pour le test

using namespace std;

void exitErreur(const char * msg) {
	perror(msg);
	exit( EXIT_FAILURE);

}

int main(int argc, char * argv[]){

// argv[1] : l'adresse IP du serveur auquel on veut se connecter
// argv[2] : le port du serveur auquel on veut se connecter
	
if(argc <3){
    cout << "Usage executable adresse_serveur port_serveur";
	exit(EXIT_FAILURE);
}
		

int sock_client = socket(AF_INET, SOCK_STREAM,0);

struct sockaddr_in sockaddr_serveur;

sockaddr_serveur.sin_family = AF_INET; 

inet_aton(argv[1], &sockaddr_serveur.sin_addr);

sockaddr_serveur.sin_port = htons(atoi(argv[2])) ;


if (connect(sock_client, (struct sockaddr *)&sockaddr_serveur, sizeof (sockaddr_serveur)) == -1)  
	exitErreur("connect");  

// envoie de requête : 
string message ("Hi\nça va?\n.\n");

if (write(sock_client, message.c_str(), message.size()) == -1) exitErreur("write");

close(sock_client);

}
