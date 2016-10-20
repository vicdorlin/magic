package org.vic.net.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author vicdor
 * @create 2016-10-04 14:01
 */
public class VicClient {
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private boolean notStop = true;

    public static void main(String[] args) {
        new VicClient().serverConnect(6808,"阿珂");
    }

    private class ReaderThread implements Runnable{

        public void run() {
            while (true){
                try {
                    String response;
                    if ((response = bufferedReader.readLine()) != null) {
                        System.out.println(response);
                    } else {
                        notStop = !notStop;
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void serverConnect(int port,String uName){
        BufferedReader consoleReader = null;
        try {
            socket = new Socket("127.0.0.1", port);
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            consoleReader = new BufferedReader(new InputStreamReader(System.in));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String content;
            printWriter.println(uName);
            Thread thread = new Thread(new ReaderThread());
            thread.start();
            while ((content = consoleReader.readLine()) != null && notStop) {
                printWriter.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (consoleReader != null) {
                try {
                    consoleReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void connectServer(int port,String uName) {
        Socket socket = null;
        BufferedReader consoleReader = null;
        try {
            socket = new Socket("127.0.0.1", port);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String content;
            printWriter.println(uName);
            while ((content = consoleReader.readLine()) != null) {
                printWriter.println(content);

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String response;
                if ((response = bufferedReader.readLine()) != null) {
                    System.out.println(response);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (consoleReader != null) {
                try {
                    consoleReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
