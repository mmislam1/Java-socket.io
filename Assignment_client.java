import java.net.*;

import java.io.*;

class Clientread implements Runnable {

    
    Socket y;
    BufferedReader b;
    Thread thread;
    DataInputStream in;
    DataOutputStream out;
    public String client, server;

    public Clientread(Socket y, BufferedReader b, DataInputStream in, DataOutputStream out) {
        
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
                server = in.readUTF();
                System.out.println("#Server : " + server);
                if (server.equals("stop")) {
                    System.out.println("#Server stopped chat");
                    
                    
                    break;
                }

            }
        
            y.close();
            b.close();
        } catch (Exception e) {
        }
    }

}

class Clientwrite implements Runnable {

    
    Socket y;
    BufferedReader b;
    Thread thread;
    DataInputStream in;
    DataOutputStream out;
    public String client, server;

    public Clientwrite(Socket y, BufferedReader b, DataInputStream in, DataOutputStream out) {
        
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
                out.writeUTF(b.readLine());
                out.flush();
                if((b.readLine()).equals("stop"))
                {
                System.out.println("#You stopped chat");

                    break;
                }
              

            }
            y.close();
            b.close();
            
        } catch (Exception e) {
        }
    }

}


public class Assignment_client1 {

    
    public static void main(String[] args) throws Exception {
        
        String client, server;
   
        Socket y = new Socket("localhost",6666);
        DataInputStream in = new DataInputStream(y.getInputStream());
        DataOutputStream out = new DataOutputStream(y.getOutputStream());
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        
        Clientwrite c = new Clientwrite(y, b, in, out);
        Clientread c1 = new Clientread(y, b, in, out);
        
    }

}
