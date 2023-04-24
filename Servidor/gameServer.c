/*
   Battle Ship Main Server using TCP
   Codigo del servidor

   Nombre Archivo: gameServer.c
   Archivos relacionados: client.c
   Fecha: Abril 2023

   Compilacion: cc gameServer.c -lnsl -o gameServer
   Ejecución: ./gameServer
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>
#include <sys/socket.h>
#include <arpa/inet.h>

#define PORT 15001

volatile sig_atomic_t stop;

void sigint_handler(int sig)
{
	stop = 1;
}

void handle_error(const char *message)
{
	perror(message);
	exit(EXIT_FAILURE);
}

int main()
{
	int server_fd, client_fd;
	struct sockaddr_in server_addr, client_addr;
	socklen_t client_len;
	char buffer[1024];

	// Create a TCP socket
	if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) < 0)
	{
		handle_error("socket");
	}

	// Prepare the server address
	memset(&server_addr, 0, sizeof(server_addr));
	server_addr.sin_family = AF_INET;
	server_addr.sin_addr.s_addr = htonl(INADDR_ANY);
	server_addr.sin_port = htons(PORT);

	// Bind the socket to the server address
	if (bind(server_fd, (struct sockaddr *)&server_addr, sizeof(server_addr)) < 0)
	{
		handle_error("bind");
	}

	// Listen for incoming connections
	if (listen(server_fd, 2) < 0)
	{
		handle_error("listen");
	}

	// Register the signal handler for SIGINT
	signal(SIGINT, sigint_handler);

	printf("Waiting for players...\n");

	// Accept the first connection
	client_len = sizeof(client_addr);
	if ((client_fd = accept(server_fd, (struct sockaddr *)&client_addr, &client_len)) < 0)
	{
		handle_error("accept");
	}
	printf("Player 1 connected\n");

	// Send a message to the first client to wait for the second player
	if (send(client_fd, "Waiting for the second player...", strlen("Waiting for the second player..."), 0) < 0)
	{
		handle_error("send");
	}

	// Accept the second connection
	client_len = sizeof(client_addr);
	if ((client_fd = accept(server_fd, (struct sockaddr *)&client_addr, &client_len)) < 0)
	{
		handle_error("accept");
	}
	printf("Player 2 connected\n");

	// Send a message to the second client that the game is starting
	if (send(client_fd, "The game is starting!", strlen("The game is starting!"), 0) < 0)
	{
		handle_error("send");
	}

	// Game loop
	while (!stop)
	{
		// Receive a message from player 1
		if (recv(client_fd, buffer, sizeof(buffer), 0) < 0)
		{
			handle_error("recv");
		}

		// Send the message to player 2
		if (send(client_fd == 3 ? 4 : 3, buffer, strlen(buffer), 0) < 0)
		{
			handle_error("send");
		}
	}

	// Close the sockets
	close(client_fd);
	close(server_fd);

	return 0;
}
