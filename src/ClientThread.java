import java.io.*;
import java.net.Socket;

/**
 * Created by NEC on 19.12.13.
 */
public class ClientThread extends Thread {

    private Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
        this.start();
    }

    public void run() {
        DataInputStream in = null;
        DataOutputStream out = null;
        try {
            System.out.println("Соединение установлено");
            InputStream sIn = socket.getInputStream();
            OutputStream sOut = socket.getOutputStream();

            //Конвертируем потоки в удобный для обработки вид
            in = new DataInputStream(sIn);
            out = new DataOutputStream(sOut);

            String line = null;

            while (true){
                line = in.readUTF(); //Ждем текстовую строку от клиента
                System.out.println("Вам прислали команду: " + line);
                out.writeUTF("Вы прислали команду:" + line); // Отсылаем строку обратно

                System.out.println("Ждем следующую строку");
            }
        } catch (EOFException EOFEx){
            System.out.println("Клиент отключился");
        } catch (Exception ex){
                ex.printStackTrace();
        } finally {
            try {
               socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
