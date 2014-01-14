import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by NEC on 19.12.13.
 */
public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket socketListener = new ServerSocket(Config.PORT);

            System.out.println("Сервер запущен");
            System.out.println("Ожидаем подключения клиентов");

            while (true) {
                Socket client = null;
                while (client == null) {
                    client = socketListener.accept();
                }
                //Создаем новый поток, которому передаем сокет
                new ClientThread(client);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
