package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection{
    private TcpConnection tcpConnection;

    public Disconnected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public String getCurrentState() {

        return null;
    }

    @Override
    public void connect() {

    }

    @Override
    public void disconnect() {
        if (tcpConnection.getCurrentState().equals("disconnected")) {
            System.out.println("Error");
        }
    }

    @Override
    public void write(String data) {

    }
}

// END
