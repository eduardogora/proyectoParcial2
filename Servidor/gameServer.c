#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>


#define PORT 5000

int main(int argc, char *argv[]) {
    int server_fd, socket_p1, socket_p2, actual_socket, valread;
    struct sockaddr_in address;
    int opt = 1;
    int addrlen = sizeof(address);
    char buffer[3500] = {0};
    char buffer_p1[3500] = {0};
    char buffer_p2[3500] = {0};
    int authenticated_players = 0; 

    int bytes_read, bytes_read_p1, bytes_read_p2;

    // Creating socket file descriptor
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }

    // Forcefully attaching socket to the port 5000
    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt))) {
        perror("setsockopt");
        exit(EXIT_FAILURE);
    }
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(PORT);

    // Forcefully attaching socket to the port 5000
    if (bind(server_fd, (struct sockaddr *)&address, sizeof(address)) < 0) {
        perror("bind failed");
        exit(EXIT_FAILURE);
    }

    if (listen(server_fd, 3) < 0) {
        perror("listen");
        exit(EXIT_FAILURE);
    }

    printf("Server listening on port %d\n", PORT);

    
    while (authenticated_players < 2) {

        // Receive player from client
        if ((actual_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen)) < 0) {
            perror("accept");
            exit(EXIT_FAILURE);
        }
        printf("Actual socket s: %d\n", actual_socket);

        bytes_read = read(actual_socket, buffer, sizeof(buffer));
        if (bytes_read < 0) {
            perror("read");
            exit(EXIT_FAILURE);
        }
        printf("blen: %d\n", bytes_read);

        if (authenticated_players == 0 || authenticated_players == 2) {
            socket_p1 = actual_socket;
            bytes_read_p1 = bytes_read;
            memcpy(buffer_p1, buffer, bytes_read);
        } else if (authenticated_players == 1 || authenticated_players == 3) {
            socket_p2 = actual_socket;
            bytes_read_p2 = bytes_read;
            strcpy(buffer_p2, buffer);
            memcpy(buffer_p2, buffer, bytes_read);
        }

        authenticated_players++;

        if (authenticated_players == 2) {
            // Send wait or play
            if (send(socket_p1, buffer_p2, bytes_read_p2, 0) <= 0) {
                perror("Error sending result to client");
                return -1;
            }

            // Send wait or play
            if (send(socket_p2, buffer_p1, bytes_read_p1, 0) <= 0) {
                perror("Error sending result to client");
                return -1;
            }

            printf("Game started!\n\n");

            // Receive player from client
            /*if ((actual_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen)) < 0) {
                perror("accept");
                exit(EXIT_FAILURE);
            }
            
            bytes_read = read(actual_socket, buffer, sizeof(buffer));
            if (bytes_read < 0) {
                perror("read");
                exit(EXIT_FAILURE);
            }

            if (strcmp(buffer, "1") == 0) {
                socket_p1 = actual_socket;
            } else {
                socket_p2 = actual_socket;
            }
            printf("Actual socket b1: %d", actual_socket);

            if ((actual_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen)) < 0) {
                perror("accept");
                exit(EXIT_FAILURE);
            }

            bytes_read = read(actual_socket, buffer, sizeof(buffer));
            if (bytes_read < 0) {
                perror("read");
                exit(EXIT_FAILURE);
            }

            if (strcmp(buffer, "1") == 0) {
                socket_p1 = actual_socket;
            } else {
                socket_p2 = actual_socket;
            }
            printf("Actual socket b2: %d", actual_socket);*/

            /*while (1) {

                printf("Waiting...");
                // Receive player from client
                if ((actual_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen)) < 0) {
                    perror("accept");
                    exit(EXIT_FAILURE);
                }
                printf("Actual socket c: %d", actual_socket);

                bytes_read = read(actual_socket, buffer, sizeof(buffer));
                if (bytes_read < 0) {
                    perror("read");
                    exit(EXIT_FAILURE);
                }
                printf("Player recv \n");
                printf("blen: %d\n", bytes_read);

                // Determine which player sent the message
                if (actual_socket == socket_p1) {
                    // Player 1 sent a message
                    // Send the message to Player 2
                    if (send(socket_p2, buffer, bytes_read, 0) <= 0) {
                        perror("Error sending message to client");
                        winner = '3';
                        break;
                    }
                    printf("Player 1 -> 2 sent\n");
                } else {
                    // Player 2 sent a message
                    // Send the message to Player 1
                    if (send(socket_p1, buffer, bytes_read, 0) <= 0) {
                        perror("Error sending message to client");
                        winner = '3';
                        break;
                    }
                    printf("Player 2 -> 1 sent\n");
                }

                // Check if either player has won
                // Set game_over to true if either player has won

                // Close the socket for the current player
            }*/

            /*
            while (winner == '0')
            {
                printf("Waiting P1...\n\n");
                
                // Receive player from client 1
                if ((actual_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen)) < 0) {
                    perror("accept");
                    exit(EXIT_FAILURE);
                }

                bytes_read = read(actual_socket, buffer, sizeof(buffer));
                if (bytes_read < 0) {
                    perror("read");
                    exit(EXIT_FAILURE);
                }

                printf("Player 1 recv \n");
                printf("blen: %d\n", bytes_read);

                bytes_read_p1 = bytes_read;
                memcpy(buffer_p1, buffer, bytes_read);

                // Send player to client 2
                if (send(socket_p2, buffer_p1, bytes_read_p1, 0) <= 0) {
                    perror("Error sending result to client");
                    return -1;
                }

                printf("Player 1 -> 2 sent\n");

                // Receive player from client 2
                bytes_read = read(socket_p2, buffer, sizeof(buffer));
                if (bytes_read < 0) {
                    perror("read");
                    exit(EXIT_FAILURE);
                }

                printf("Player 2 recv \n");
                printf("blen: %d\n", bytes_read);

                bytes_read_p2 = bytes_read;
                memcpy(buffer_p2, buffer, bytes_read);

                // Send player to client 1
                if (send(socket_p1, buffer_p2, bytes_read_p2, 0) <= 0) {
                    perror("Error sending result to client");
                    return -1;
                }

                printf("Player 2 -> 1 sent\n");
            }

            printf("Winner is player %c\n", winner);
            */
            
            // close(socket_p1);
            // close(socket_p2);
        } /*else if (authenticated_players == 4) {
            printf("\n\n******** Start *******\n\n");

            char winner = '0';
            int rec_socket = socket_p1;
            int sen_socket = socket_p2;

            while (winner == '0')
            {
                printf("\n\nRec socket: %d\n", rec_socket);

                bytes_read = read(rec_socket, buffer, sizeof(buffer));
                if (bytes_read < 0) {
                    perror("read");
                    exit(EXIT_FAILURE);
                }
                printf("blen: %d\n", bytes_read);

                // Send wait or play
                if (send(sen_socket, buffer, bytes_read, 0) <= 0) {
                    perror("Error sending result to client");
                    return -1;
                }

                if (rec_socket == socket_p1) {
                    rec_socket = socket_p2;
                    sen_socket = socket_p1;
                } else {
                    rec_socket = socket_p1;
                    sen_socket = socket_p2;
                }
            }
        }*/

    }


    printf("Okay...\n\n");
    authenticated_players = 0;

    while (authenticated_players < 2) {
        printf("Waiting for players...\n\n");
        // Accept a new connection
        int actual_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen);
        if (actual_socket < 0) {
            perror("accept");
            exit(EXIT_FAILURE);
        }
        printf("Player %d connected on socket %d\n", authenticated_players + 1, actual_socket);

        // Assign the socket to the correct player
        if (authenticated_players == 0) {
            socket_p1 = actual_socket;
        } else {
            socket_p2 = actual_socket;
        }

        authenticated_players++;

        // Receive player from client
        /*if ((actual_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen)) < 0) {
            perror("accept");
            exit(EXIT_FAILURE);
        }
        
        printf("Actual socket g: %d\n", actual_socket);

        bytes_read = read(actual_socket, buffer, sizeof(buffer));
        if (bytes_read < 0) {
            perror("read");
            exit(EXIT_FAILURE);
        }
        printf("blen: %d\n", bytes_read);

        if (authenticated_players == 2) {
            socket_p1 = actual_socket;
            bytes_read_p1 = bytes_read;
            memcpy(buffer_p1, buffer, bytes_read);
        } else {
            socket_p2 = actual_socket;
            bytes_read_p2 = bytes_read;
            strcpy(buffer_p2, buffer);
            memcpy(buffer_p2, buffer, bytes_read);
        }

        authenticated_players++;*/
    }

    
    char winner = '0';
    int rec_socket = socket_p1;
    int sen_socket = socket_p2;

    while (winner == '0') {
        printf("\n\nRec socket: %d\n", rec_socket);

        bytes_read = read(rec_socket, buffer, sizeof(buffer));
        if (bytes_read < 0) {
            perror("read");
            exit(EXIT_FAILURE);
        }
        printf("blen: %d\n", bytes_read);

        // Send wait or play
        if (send(sen_socket, buffer, bytes_read, 0) <= 0) {
            perror("Error sending result to client");
            return -1;
        }

        if (rec_socket == socket_p1) {
            rec_socket = socket_p2;
            sen_socket = socket_p1;
        } else {
            rec_socket = socket_p1;
            sen_socket = socket_p2;
        }
    }

    printf("Winner is player %c\n", winner);
            
    close(socket_p1);
    close(socket_p2);

    return 0;
}