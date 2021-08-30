import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

public record Server(int portNumber) {

    @SuppressWarnings("InfiniteLoopStatement")
    public void startServer() throws IOException {
        final var serverSocket = new ServerSocket(portNumber);
        final var threadPool = Executors.newFixedThreadPool(64);
        System.out.println("Сервер запущен...");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                final var serverThread = new ThreadSer(socket);
                threadPool.submit(serverThread);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}