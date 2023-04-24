/*

   Battle Ship Authentication Server using UDP
   Codigo del servidor

   Nombre Archivo: authServer.c
   Archivos relacionados: client.java
   Fecha: Abril 2023

   Compilacion: cc authServer.c -lnsl -o authServer
   Ejecución: ./authServer
*/

// server program for udp connection
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <stdlib.h>

#define PORT 15000
#define MAXLINE 1000
#define RANGE 2
#define IP "172.18.2.2"

// Driver code
int main()
{
	char buffer[MAXLINE];
	char users[RANGE][MAXLINE] = {"User1", "User"};
	char pswrds[RANGE][MAXLINE] = {"Password1", "User"};
	int listenfd, len;
	char *message = malloc(sizeof(char) * MAXLINE);
	struct sockaddr_in servaddr, cliaddr;
	int empezar;
	char *user;
	char *pswrd;
	char current[2];
	int static USER = 0;
	printf("Listening in port number: %d\n", PORT);
	// printf("\nPresione cualquier tecla para empezar \n");
	// scanf("%d",&empezar);

	// bzero(&servaddr, sizeof(servaddr));

	// Create a UDP Socket
	listenfd = socket(AF_INET, SOCK_DGRAM, 0);
	if (listenfd == -1)
	{
		perror("socket creation failed");
		exit(EXIT_FAILURE);
	}

	servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
	servaddr.sin_port = htons(PORT);
	servaddr.sin_family = AF_INET;

	printf("Listening: %d\n", servaddr.sin_addr.s_addr);

	// bind server address to socket descriptor
	bind(listenfd, (struct sockaddr *)&servaddr, sizeof(servaddr));

	// Get the socket's local address
	struct sockaddr_in addr;
	socklen_t addrlen = sizeof(addr);
	if (getsockname(listenfd, (struct sockaddr *)&addr, &addrlen) < 0)
	{
		perror("Error getting socket name");
		exit(1);
	}

	char ip[INET_ADDRSTRLEN];
	inet_ntop(AF_INET, &addr.sin_addr, ip, sizeof(ip));
	printf("Server IP address: %s\n", ip);
	char sigue = 'S';
	while (sigue == 'S')
	{

		// receive the datagram
		len = sizeof(cliaddr);
		int n = recvfrom(listenfd, buffer, MAXLINE,
						 0, (struct sockaddr *)&cliaddr, &len); // receive message from server
		if (n < 0)
		{
			perror("recvfrom failed");
			exit(EXIT_FAILURE);
		}
		else
		{
			buffer[n] = '\0';
			printf("\nHe recibido del cliente: ");
			printf("%s\n", buffer);
		}

		// VALID USER
		for (int i = 0; i < RANGE; i++)
		{
			char *tmp = malloc(strlen(users[i]) + 1);
			strcpy(tmp, users[i]);
			char *tmpP = malloc(strlen(pswrds[i]) + 1);
			strcpy(tmpP, pswrds[i]);
			user = strtok(buffer, ":");
			pswrd = strtok(NULL, ":");
			if (strcmp(tmp, user))
			{
				if (strcmp(tmpP, pswrd))
				{
					if (USER == 0)
					{
						USER = 1;
					}
					else
					{
						USER = 2;
					}
					sprintf(current, "%d", USER);
					strcpy(message, current);
					break;
				}
			}
			strcpy(message, "0");
		}

		// send the response
		sendto(listenfd, message, strlen(message), 0,
			   (struct sockaddr *)&cliaddr, sizeof(cliaddr));

		// receive the datagram
		len = sizeof(cliaddr);
		n = recvfrom(listenfd, buffer, MAXLINE,
					 0, (struct sockaddr *)&cliaddr, &len); // receive message from server
		if (n < 0)
		{
			perror("recvfrom failed");
			exit(EXIT_FAILURE);
		}
		else
		{
			buffer[n] = '\0';
			printf("\nHe recibido del cliente: ");
			printf("%s\n", buffer);
		}
		// send the response
		sendto(listenfd, buffer, MAXLINE, 0,
			   (struct sockaddr *)&cliaddr, sizeof(cliaddr));
	}

	close(listenfd);
	printf("Conexion cerrada\n");
	return 0;
}