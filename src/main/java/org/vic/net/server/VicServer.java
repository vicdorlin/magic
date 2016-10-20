package org.vic.net.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vicdor
 * @create 2016-10-04 13:31
 */
public class VicServer {
    private List<ServerThread> serverThreads = new ArrayList<ServerThread>();

    public static void main(String[] args) {
        new VicServer().serverStart(6808);
    }

    public void serverStart(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                ServerThread serverThread = new ServerThread();
                serverThread.socket = serverSocket.accept();
                Thread thread = new Thread(serverThread);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ServerThread implements Runnable {
        private Socket socket;
        private String name;
        private PrintWriter printWriter;

        public PrintWriter getPrintWriter() {
            return printWriter;
        }

        public void run() {
            try {
                serverThreads.add(this);
                name = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                printWriter = new PrintWriter(socket.getOutputStream(), true);
                String uName = bufferedReader.readLine();
                name += "[" + uName + "]";
                System.out.println(name + "已连接");
                String clientMsg;
                while ((clientMsg = bufferedReader.readLine()) != null) {
                    if (clientMsg.equals("quit")) {
                        break;
                    }
                    for (ServerThread serverThread : serverThreads) {
                        serverThread.getPrintWriter().println(name + "说了:" + clientMsg);
                    }
                }
                System.out.println(name + "已下线");
                serverThreads.remove(this);
            } catch (SocketException e) {
                System.out.println(name + "连接断开");
                serverThreads.remove(this);
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
            }

        }
    }
}
