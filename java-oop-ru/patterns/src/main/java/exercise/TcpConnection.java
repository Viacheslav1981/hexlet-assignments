package exercise;

import exercise.connections.Connected;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection implements Connection {
    private Connection connection;
    private String ipAdres;
    private int port;

    public static void main(String[] args) {
        TcpConnection tcpConnection = new TcpConnection("132.223.243.88", 2342);
        tcpConnection.connect();
        tcpConnection.getCurrentState();
        tcpConnection.connect();
        tcpConnection.disconnect();
        tcpConnection.getCurrentState();
        tcpConnection.disconnect();

    }

    public TcpConnection(String ipAdress, int port) {
        this.ipAdres = ipAdress;
        this.port = port;

    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String getCurrentState() {
        String status = "";
        if (this.getConnection() instanceof Connected) {
            status = "connected";
        }
        if (connection instanceof Disconnected) {
            status = "disconnected";
        }

        return status;
    }

    @Override
    public void connect() {
        Connected connected = new Connected(this);
        connected.connect();
        setConnection(connected);

    }

    @Override
    public void disconnect() {
        Disconnected disconnected = new Disconnected(this);
        disconnected.disconnect();
        setConnection(disconnected);
    }

    @Override
    public void write(String data) {
        if (this.getCurrentState().equals("disconnected")){
            System.out.println("Error");
        }

    }
}

// END
