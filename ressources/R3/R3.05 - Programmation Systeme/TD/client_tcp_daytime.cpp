#include <arpa/inet.h>
#include <sys/socket.h>
#include <unistd.h>
#include <iostream>
#include <string.h>
#include <stdio.h>
#include <time.h>
#include <stdlib.h>

#define TAILLE_BUFFER 30

using namespace std;

void exitErreur(const char * msg) {
    perror(msg);
    exit( EXIT_FAILURE);

}

int main(int argc, char * argv[]) {

    if (argc <3) {
        cout << "Invalid arguments" << endl;
        exit(EXIT_FAILURE);
    }

    int sock_client = socket(AF_INET, SOCK_STREAM, 0);

    struct sockaddr_in sockaddr_serveur;

    sockaddr_serveur.sin_family = AF_INET;
    inet_aton(argv[1], &sockaddr_serveur.sin_addr);
    sockaddr_serveur.sin_port = htons(atoi(argv[2]));
     
    if (connect(sock_client, (struct sockaddr *) &sockaddr_serveur, sizeof (sockaddr_serveur)) == -1)
        exitErreur("connect");
        
        
    int n;
    
    char msg[TAILLE_BUFFER];
    
    while (true) {
        n = read(sock_client, msg, sizeof(msg));
        
        if (n == -1)
            exitErreur("read");
        
        if (!n) break;
        
        cout << string (msg,n);
    }
    return 0;
}
