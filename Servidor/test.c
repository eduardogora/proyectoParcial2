#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>

#define PORT 5000

typedef struct {
    char * username;
    char * password;
} User;

int authenticate(int new_socket, char * user, char * pass) {
    User user1 = {"user1", "user1"};
    User user2 = {"user2", "user2"};
    char * login_successful = "0";
    int first_login = 0;    
    char * message;

    printf("%s:%s\n", user, pass);

    
    // Check if username and password match hardcoded users
    if (strcmp(user, user1.username) == 0 && strcmp(pass, user1.password) == 0) {
        login_successful = "1";
        first_login = 1;
    } else if (strcmp(user, user2.username) == 0 && strcmp(pass, user2.password) == 0) {
        login_successful = "1";
        first_login = 0;
    }

    message = login_successful; // + ":"; // + first_login;
    printf("%s\n", login_successful);

    // Send authentication result to client
    if (send(new_socket, message, strlen(message), 0) <= 0) {
        perror("Error sending authentication result to client");
        return -1;
    }

    printf("Hello message sent\n");

    
    return 0; //first_login;
}

int main(int argc, char *argv[]) {
    int server_fd, new_socket, valread;
    struct sockaddr_in address;
    int opt = 1;
    int addrlen = sizeof(address);
    char buffer[1024] = {0};

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
    
    

    // Receive username and password from client
    if ((new_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen)) < 0) {
        perror("accept");
        exit(EXIT_FAILURE);
    }

    valread = read(new_socket, buffer, 1024);

    char *user = strtok(buffer, ":");
    char *pswrd = strtok(NULL, ":");

    authenticate(new_socket, user, pswrd);

    return 0;
}


/*
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netinet/in.h>

#define BUF_SIZE 1024
#define SERV_PORT 5000

int main(int argc, char *argv[])
{
    int serv_sock, n;
    struct sockaddr_in serv_addr, clnt_addr;
    socklen_t clnt_addr_len = sizeof(clnt_addr);
    char buf[BUF_SIZE];
    char ack[] = "ACK";

    // create socket
    if ((serv_sock = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP)) < 0) {
        perror("socket");
        exit(1);
    }

    // bind socket to port
    memset(&serv_addr, 0, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    serv_addr.sin_port = htons(SERV_PORT);
    if (bind(serv_sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0) {
        perror("bind");
        exit(1);
    }

    // receive message from client
    memset(buf, 0, sizeof(buf));
    n = recvfrom(serv_sock, buf, BUF_SIZE, 0, (struct sockaddr *)&clnt_addr, &clnt_addr_len);
    if (n < 0) {
        perror("recvfrom");
        exit(1);
    }
    printf("Received message from client: %s\n", buf);

    // send acknowledgement to client
    if (sendto(serv_sock, ack, sizeof(ack), 0, (struct sockaddr *)&clnt_addr, clnt_addr_len) < 0) {
        perror("sendto");
        exit(1);
    }
    printf("Sent acknowledgement to client\n");

    // close socket
    close(serv_sock);

    return 0;
}
*/

/*#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>

#define BUFFER_SIZE 1024
#define PORT 5000

typedef struct {
    char *username;
    char *password;
} User;

User users[2] = {{"user1", "user1"}, {"user2", "user2"}};

int authenticate(char *username, char *password) {
    for (int i = 0; i < 2; i++) {
        if (strcmp(users[i].username, username) == 0 && strcmp(users[i].password, password) == 0) {
            return i + 1; // returns 1 for user1 and 2 for user2
        }
    }
    return 0; // authentication failed
}

int main(int argc, char *argv[]) {
    int server_socket;
    struct sockaddr_in server_address, client_address;
    char buffer[BUFFER_SIZE];

    // create socket for incoming connections
    if ((server_socket = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP)) < 0) {
        perror("socket() failed");
        exit(1);
    }

    // construct local address structure
    memset(&server_address, 0, sizeof(server_address));
    server_address.sin_family = AF_INET;
    server_address.sin_addr.s_addr = INADDR_ANY;
    server_address.sin_port = htons(PORT);

    // bind to the local address
    if (bind(server_socket, (struct sockaddr *) &server_address, sizeof(server_address)) < 0) {
        perror("bind() failed");
        exit(1);
    }

    printf("Server listening on port %d\n", PORT);

    while (1) {
        // receive username from client
        unsigned int client_length = sizeof(client_address);
        memset(buffer, 0, BUFFER_SIZE);
        if (recvfrom(server_socket, buffer, BUFFER_SIZE, 0, (struct sockaddr *)&client_address, &client_length) < 0) {
            perror("recvfrom() failed");
            exit(1);
        }
        char username[BUFFER_SIZE];
        strcpy(username, buffer);
        printf("Received username from client %s\n", inet_ntoa(client_address.sin_addr));

        // receive password from client
        memset(buffer, 0, BUFFER_SIZE);
        if (recvfrom(server_socket, buffer, BUFFER_SIZE, 0, (struct sockaddr *)&client_address, &client_length) < 0) {
            perror("recvfrom() failed");
            exit(1);
        }
        char password[BUFFER_SIZE];
        strcpy(password, buffer);

        // authenticate user and send response to client
        int result = authenticate(username, password);
        memset(buffer, 0, BUFFER_SIZE);
        sprintf(buffer, "%d", result);
        if (sendto(server_socket, buffer, BUFFER_SIZE, 0, (struct sockaddr *)&client_address, client_length) < 0) {
            perror("sendto() failed");
            exit(1);
        }
        printf("Sent response to client %s\n", inet_ntoa(client_address.sin_addr));
    }

    return 0;
}
*/

/*#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netinet/in.h>

#define PORT 5000

int main() {
    int server_socket, client_socket;
    struct sockaddr_in server_addr, client_addr;
    char buffer[1024] = {0};
    int valread;

    // Create server socket
    if ((server_socket = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }

    // Set server address parameters
    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = INADDR_ANY;
    server_addr.sin_port = htons(PORT);

    // Bind server socket to specified address
    if (bind(server_socket, (struct sockaddr *)&server_addr, sizeof(server_addr)) < 0) {
        perror("bind failed");
        exit(EXIT_FAILURE);
    }

    // Start listening on server socket
    if (listen(server_socket, 2) < 0) {
        perror("listen failed");
        exit(EXIT_FAILURE);
    }

    printf("Server listening on port %d\n", PORT);

    int first_connected = 0;
    int second_connected = 0;

    while (1) {
        int addrlen = sizeof(client_addr);

        // Accept client connection
        if ((client_socket = accept(server_socket, (struct sockaddr *)&client_addr, (socklen_t*)&addrlen)) < 0) {
            perror("accept failed");
            exit(EXIT_FAILURE);
        }

        // Receive data from client
        valread = read(client_socket, buffer, 1024);
        printf("Received message from client: %s\n", buffer);

        // Parse username and password
        char username[256] = {0};
        char password[256] = {0};
        sscanf(buffer, "%s %s", username, password);

        // Check if username and password are valid
        int success = 0;
        int user_id = 0;
        if (strcmp(username, "user1") == 0 && strcmp(password, "user1") == 0) {
            if (!first_connected) {
                success = 1;
                first_connected = 1;
                user_id = 1;
            }
        } else if (strcmp(username, "user2") == 0 && strcmp(password, "user2") == 0) {
            if (!second_connected) {
                success = 1;
                second_connected = 1;
                user_id = 2;
            }
        }

        // Send response to client
        char response[256] = {0};
        if (success) {
            sprintf(response, "%d %d", user_id, (first_connected && second_connected) ? 2 : 1);
        } else {
            sprintf(response, "%d", 0);
        }
        send(client_socket, response, strlen(response), 0);

        // Close client socket
        close(client_socket);
    }

    // Close server socket
    close(server_socket);

    return 0;
}
*/
// #include <stdio.h>
// #include <string.h>
// #include <stdlib.h>
// #include <unistd.h>
// #include <sys/socket.h>
// #include <arpa/inet.h>

// #define PORT 5000

// typedef struct {
//     char username[10];
//     char password[10];
// } User;

// int authenticate(int client_sockfd, User* user) {
//     User user1 = {"user1", "user1"};
//     User user2 = {"user2", "user2"};
//     int login_successful = 0;
//     int first_login = 0;
    
//     // Receive username and password from client
//     if (recv(client_sockfd, user, sizeof(User), <=  0)0) {
//         perror("Error receiving user data from client");
//         return -1;
//     }
    
//     // Check if username and password match hardcoded users
//     if (strcmp(user->username, user1.username) == 0 && strcmp(user->password, user1.password) == 0) {
//         login_successful = 1;
//         first_login = 1;
//     } else if (strcmp(user->username, user2.username) == 0 && strcmp(user->password, user2.password) == 0) {
//         login_successful = 1;
//         first_login = 0;
//     }
    
//     // Send authentication result to client
//     if (send(client_sockfd, &login_successful, sizeof(int), 0) <= 0) {
//         perror("Error sending authentication result to client");
//         return -1;
//     }
    
//     return first_login;
// }

// int main() {
//     int server_sockfd, client_sockfd, addr_len, first_login;
//     struct sockaddr_in server_addr, client_addr;
//     User user;
    
//     // Create socket
//     if ((server_sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
//         perror("Error creating socket");
//         return -1;
//     }
    
//     // Configure server address
//     memset(&server_addr, 0, sizeof(server_addr));
//     server_addr.sin_family = AF_INET;
//     server_addr.sin_addr.s_addr = INADDR_ANY;
//     server_addr.sin_port = htons(PORT);
    
//     // Bind socket to server address
//     if (bind(server_sockfd, (struct sockaddr*)&server_addr, sizeof(server_addr)) < 0) {
//         perror("Error binding socket to server address");
//         return -1;
//     }
    
//     // Listen for client connections
//     if (listen(server_sockfd, 2) < 0) {
//         perror("Error listening for client connections");
//         return -1;
//     }
    
//     printf("Server listening on port %d\n", PORT);
    
//     while (1) {
//         // Accept client connection
//         addr_len = sizeof(client_addr);
//         if ((client_sockfd = accept(server_sockfd, (struct sockaddr*)&client_addr, (socklen_t*)&addr_len)) < 0) {
//             perror("Error accepting client connection");
//             return -1;
//         }
        
//         // Authenticate client
//         first_login = authenticate(client_sockfd, &user);
//         if (first_login < 0) {
//             close(client_sockfd);
//             continue;
//         }
        
//         // Limit number of connections to 2
//         if (first_login) {
//             printf("First user connected\n");
//         } else {
//             printf("Second user connected\n");
//             close(server_sockfd);
//             break;
//         }
//     }
    
//     return 0;
// }
