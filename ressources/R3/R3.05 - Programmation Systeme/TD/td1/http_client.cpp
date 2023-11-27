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

void exitError(const char * msg) {
    perror(msg);
    exit(EXIT_FAILURE);
}   

int main(int argc, char * argv[]) {

    if (argc < 4) {
        cout << "Invalid arguments" << endl;
        exit(EXIT_FAILURE);
    }

    int sock_client = socket(AF_INET, SOCK_STREAM, 0);

    struct sockaddr_in sockaddr_serveur;

    sockaddr_serveur.sin_family = AF_INET;
    inet_aton(argv[1], &sockaddr_serveur.sin_addr);
    sockaddr_serveur.sin_port = htons(atoi(argv[2]));

    if (connect(sock_client, (struct sockaddr *) &sockaddr_serveur,
    sizeof (sockaddr_serveur)) == -1) {
        exitError("connect");
    }

    int n;
    int w;

    char msg[TAILLE_BUFFER];

    string requete = "GET " + string(argv[3]) + string("\r\n");

    w = write(sock_client, requete.c_str(), sizeof(requete));

    if (w == -1)
        exitError("write");

    while (true) {
        

        n = read(sock_client, msg, sizeof(msg));

        if (n == -1)
            exitError("read");

        if (!n) break;
        
        cout << string(msg,n);
    }

    close(sock_client);
    return EXIT_SUCCESS;
}