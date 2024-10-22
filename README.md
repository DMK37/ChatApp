# Chat app based on TCP servers

## Usage

1. Run Main from Server package.
2. Run Main from Client package.
3. In Client type in port number. You can pick from already servers or type in new port number (At the start there aren't any created chat servers, so just type in number for new chat server).
4. After message in client: "Server is ready" you can start sending messages by just writing message to console.
5. To exit from chat type "$exit".
6. There is no functionality to stop main server or chat servers so just terminate it when you are done.
7. The user who sent the message doesn't receive it, so to check functionality start at least two users in one chat (for example build maven and run: java client/Main from target/classes).

## Classes
### Client Package
  - Main: runs first client to establish connection with Main Server and in the end returns port number to connect to chat server.
  - Client: client which communicates with Main server to pick port for chat server.
  - ChatClient: client which communicates with chat server. It starts new thread to listen for new messages in chat and waits for user input and send it to the server.
  - ChatClientHandler: Runnable class which listens for new messages in chat.
  - Message: class which contains content, username and timestamp

### Server Package
  - Main: starts main server
  - MainServer: requests port number for chat from user and checks if chat on this port is already created or not. If not starts new chat server in new thread. Retrieves each user on new thread.
  - MainServerHandler: runnable class which runs MainServer functionality for one client.
  - ChatServer: class which starts new chat server and retrieves input and output for messages in new thread for each client.
  - ChatServerHandler: runnable class with ChatServer logic.
