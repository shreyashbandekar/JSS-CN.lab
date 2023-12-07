#include <iostream>
#include <fstream>
#include <string>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>

int main() {
    int server_fd, new_socket, valread;
    struct sockaddr_in address;
    int opt = 1;
    int addrlen = sizeof(address);
    char buffer[1024] = {0};
    std::string fname;

    // Creating socket file descriptor
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }

    // Forcefully attaching socket to the port 4000
    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt))) {
        perror("setsockopt");
        exit(EXIT_FAILURE);
    }
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(4000);

    // Forcefully attaching socket to the port 4000
    if (bind(server_fd, (struct sockaddr *)&address, sizeof(address)) < 0) {
        perror("bind failed");
        exit(EXIT_FAILURE);
    }
    if (listen(server_fd, 3) < 0) {
        perror("listen");
        exit(EXIT_FAILURE);
    }
    if ((new_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen)) < 0) {
        perror("accept");
        exit(EXIT_FAILURE);
    }

    std::cout << "Connection successful, waiting for filename" << std::endl;

    char buffer[1024] = {0};
    valread = read(new_socket, buffer, 1024);
    fname = buffer;

    std::ifstream contentRead(fname);
    std::string str;
    if (contentRead.is_open()) {
        while (getline(contentRead, str)) {
            send(new_socket, str.c_str(), str.length(), 0);
        }
        contentRead.close();
    } else {
        std::string errorMsg = "File does not exist";
        send(new_socket, errorMsg.c_str(), errorMsg.length(), 0);
    }

    std::cout << "Closing connection" << std::endl;
    close(new_socket);
    close(server_fd);

    return 0;
}


