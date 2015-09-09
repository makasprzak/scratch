Each submodule here contains two executables for consumer and publisher microservices (or client/server if you prefer). For spring-boot based to launch client use --server.port=8081 program argument, so that the port does not conflict with server.
When both server and client are started you can go to your browser and type:

http://localhost:8081/?delayMillis=1

This will call a client which then will call server with similar request. Server will sleep for time specified as delayMillis and return response containing the request received and response sent nonotimes. Client will analyze it against its time records and return you the time taken for communication. In addition client checks the time needed for serializatin/deserialization itself.
