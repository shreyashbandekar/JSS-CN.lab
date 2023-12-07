#include <iostream>
#include <string>
#include <fstream>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>

int main() {
    int sock = socket(AF_INET, SOCK_STREAM, 0);
    if (sock == -1) {
        std::cerr << "Failed to create socket" << std::endl;
        return 1;
    }

    sockaddr_in server;
    server.sin_addr.s_addr = inet_addr("127.0.0.1");
    server.sin_family = AF_INET;
    server.sin_port = htons(4000);

    if (connect(sock, (struct sockaddr *)&server, sizeof(server)) < 0) {
        std::cerr << "Connection failed" << std::endl;
        return 1;
    }

    std::cout << "Enter the file name: ";
    std::string fname;
    std::cin >> fname;

    std::ofstream ostream(sock);
    ostream << fname << std::endl;

    std::string str;
    while (std::getline(istream, str)) {
        std::cout << str << std::endl;
    }

    ostream.close();
    close(sock);

    return 0;
}


