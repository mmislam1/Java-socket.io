import java.net.*;

import java.io.*;

class Serverread implements Runnable {

    ServerSocket x;
    Socket y;
    BufferedReader b;
    Thread thread;
    DataInputStream in;
    DataOutputStream out;
    public String client, server;

    public Serverread(ServerSocket x, Socket y, BufferedReader b, DataInputStream in, DataOutputStream out) {
        this.x = x;
        this.y = y;
        this.b = b;
        this.in = in;
        this.out = out;
        thread = new Thread(this);
        thread.start();

    }

    public void run() {
        try {
            while (true) {
                client = in.readUTF();
                System.out.println("#Client : " + client);
                if (client.equals("stop")) {
                    System.out.println("#Client stopped chat");

                    break;
                }
                Serverwrite s1 = new Serverwrite(Integer.parseInt(client), x, y, b, in, out);
            }

            y.close();
            x.close();
            b.close();

        } catch (Exception e) {
        }
    }

}

class Serverwrite implements Runnable {

    ServerSocket x;
    Socket y;
    BufferedReader b;
    Thread thread;
    DataInputStream in;
    DataOutputStream out;
    public String client, server;
    int c, a;

    public Serverwrite(int c, ServerSocket x, Socket y, BufferedReader b, DataInputStream in, DataOutputStream out) {
        this.x = x;
        this.y = y;
        this.b = b;
        this.in = in;
        this.out = out;
        this.c = c;
        thread = new Thread(this);
        thread.start();

    }

    public void run() {
        try {
            a = 0;
            while (a <= 10) {
                out.writeUTF("=> " + String.valueOf(c * a));
                out.flush();
                System.out.println("=> " + String.valueOf(c * a));
                a++;
            }

        } catch (Exception e) {
        }
    }

}


public class Assignment_server {

  
    public static void main(String[] args) throws Exception {
        
        String client, server;
        ServerSocket x = new ServerSocket(6666);
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            Socket y = x.accept();
            DataInputStream in = new DataInputStream(y.getInputStream());
            DataOutputStream out = new DataOutputStream(y.getOutputStream());

            Serverread s = new Serverread(x, y, b, in, out);
        }

        
    }

}