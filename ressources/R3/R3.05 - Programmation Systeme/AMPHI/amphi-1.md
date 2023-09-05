# R3.05 - Programmation Système - amphi 1

## Protocole TCP

* Un protocole **orienté connexion**
* Même principe qu'une conversation téléphonique :

## Pprotocole Udp

* Utilise un protocole sans connexion
* Peut être vu comme un service postal : deux applications peuvent s'envoyer des messages sans rétablir une connexion entre elles
* Protocole **non fiable** ! l'arrivée n'est pas vérifiée par l'éméteur
* Convient à des applications en temps réel, comme la visioconférence

## Sockets

* Les **sockets sont un mécanisme de bas niveu d'IPC permettant l'échange de données entre applications sur une même machine ou sur des machines 
distantes connectées via un réseau
* Types de sockets :
	* Sockets du domaine **UNIX** (local)
	* Sockets du domaine **Internet** (IPv4 et IPv6) : mode TCP, mode UDP, etc

**Exemple**

* Serveur : 192.168.1.81 (80) `Socket passive attends les requêtes du client`
* Client : 192.168.2.82 (random)

pour chaque client  le serveur crée une socket active pour communiquer avec le client

**Communication bidirectionnelle**

## Implémentations de Socket

## socket()

``` C
#include <sys/socket.h>
int socket(int domain, int type, int protocol);
```

Paramètre domain : domaine de communication

| Domaine  |        Format d'adresse         |   Type d'adresse    |
|:--------:|:-------------------------------:|:-------------------:|
| AF_UNIX  |            pathname             | struct sockaddr_in  |
| AF_INET  | IPv4 32 bits + un port 16 bits  | struct sockaddr_in  |
| AF_INET6 | IPv6 138 bits + un port 16 bits | struct sockaddr_in6 |

``` C
#include <sys/socket.h>
int socket(int domain, int type, int protocol);
```

Paramètre protocol : indique le protocol de communication.  
En genéral, protocol = 0

### Exemple : serveur daytime TCP

``` C
int main() {
	int sock_serveur = socket(AF_INET, SOCK_STREAM, 0);
	
	return 0;
}
```

## bind()

``` C
#include <sys/socket.h>
int socket(int sockfd, const struct sockaddr *addr, socklen_t addrlen);
```

## Structure sockaddr_in

``` C
struct sockaddr_in
{
sa_family_t sin_family; 	/*vaut AF_INET*/
uint19_t sin_port; 		/*port dans l'ordre réseau*/
struct in_addr sin_addr; 	/*Adresse IP*/
};
```

## String vers in_addr

``` C
int inet_aton(const char *cp, struct in_addr *inp);
```

## Exemple : serveur daytime TCP (suite)

``` C
int sock_server = socket (AF_INET, SOCK_STREAM, 0);

struct sockaddr_in sockaddr_serveur;

sockaddr_serveur.sin_family = AF_INET;

sockaddr_serveur.sin_port = httons(50013);

inet_aton("192.168.1.1", &sockaddr_serveur.sin_addr);
// sockaddr_serveur.sin_addr = "192.168.1.1"
bind (sock_serveur, (struct sockaddr *) &sockaddr_serveur, sizeof(struct sockaddr_in) );
```

## Network byte order / Host byte order

Convertir des entiers non signés de l'ordre machine vers l'ordre... ?

## Exemple : serveur daytime TCP (suite de la suite)

``` C
int sock_server = socket (AF_INET, SOCK_STREAM, 0);

struct sockaddr_in sockaddr_serveur;

sockaddr_serveur.sin_family = AF_INET;

sockaddr_serveur.sin_port = httons(50013);

inet_aton("192.168.1.1", &sockaddr_serveur.sin_addr);
// sockaddr_serveur.sin_addr = "192.168.1.1"
bind (sock_serveur, (struct sockaddr *) &sockaddr_serveur, sizeof(struct sockaddr_in) );

listen(sock_serveur, 128);

int sock_client;

sock_client = accept(sock_serveur, NULL, NULL);
```

## Exemple : serveur daytime TCP (suite de la suite de la suite)

``` C
int sock_server = socket (AF_INET, SOCK_STREAM, 0);

struct sockaddr_in sockaddr_serveur;

sockaddr_serveur.sin_family = AF_INET;

sockaddr_serveur.sin_port = httons(50013);

inet_aton("192.168.1.1", &sockaddr_serveur.sin_addr);
// sockaddr_serveur.sin_addr = "192.168.1.1"
bind (sock_serveur, (struct sockaddr *) &sockaddr_serveur, sizeof(struct sockaddr_in) );

listen(sock_serveur, 128);

int sock_client;

sock_client = accept(sock_serveur, NULL, NULL);

char *msg; time_t date; date = time(NULL); msg = ctime(&date);
// msg contient la date et l'heure
write(sock_client, msg, strlen(msg));
```