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

class SocketReader{
private:
    int socketfd;
    char msgRead[TAILLE_BUFFER];
    int  start ; //la position du premier élément de l'intervalle de recherche de \n
    int count; // la taille de l'intervalle de recherhce de \n

public :
    SocketReader(int socketfd){
       count = 0;
        this->socketfd = socketfd;
    }

    int readLine(int sockfd, string & line)
    {
        //line : la ligne lue sans \n
        line = ""; int i ;
        while(true) {
            if (count ==0){
                count = read(sockfd, msgRead, sizeof(msgRead));
                
                if (count == -1) return -1; // cas d'erreur
                if (count == 0) return 0; // cas de deconnexion
                start = 0 ;
            }
            for ( i=start; i<count; i++)
                if (msgRead[i] == '\n') break;
                
            if(i<count) { // on a trouvé /n
                line = line + string(msgRead, start,(i-1)-start + 1); // rappel : nb élements dans une suite = l'indice du dernier - l'indice du premier +1
                if (i==count-1) // \n est le dernier élement
                    count = 0; // la prochaine fois il faut lire depuis la socket
                else start =i+1; // il reste encore des élements non traites dans msgRead => le premier élement du prochain readLine() est i+1
                break ;}
            else {
                // on n'a pas trouvé le \n
                line = line + string(msgRead,start, (i-1)-start+1); // l'indice du dernier élement à considérer est i-1 (l'element i est en dehors de l'intervalle)
                count = 0 ; // la prochaine fois il faut lire depuis la socket
            }
            
            
        }
        
        return line.size();
    }
};

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

string msgSend = "";


    // Le serveur attend un client
    sock_client = accept(sock_client, NULL, NULL);
    if (sock_client == -1)
        exitErreur("accept");

    cout << "Nouveau client "<< endl;

    SocketReader sr(sock_client);

    while(true) {
        string ligne;

        int n = sr.readLine(sock_client,ligne);

        // client s'est déconnecté
        if (!n ) break ;

        // cas d'erreur
        if (n==-1) break ;

        cout << "Client : " + ligne << endl;
        
        cout << "Me : ";
        getline(cin, msgSend);
        msgSend += "\n";
        write(sock_client, msgSend.c_str(), msgSend.size());

        // client envoie une ligne contenant juste "." (on rajoute \r pour telnet)
        if (ligne == "Bye" || ligne=="Bye\r") {
            cout << "FIN" << endl;
            write(sock_client, string("Bye\n").c_str(), string("Bye\n").size());
            break;
        }
    }

    close(sock_client);


close(sock_client);

}
