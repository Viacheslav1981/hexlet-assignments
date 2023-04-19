package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection{
    private TcpConnection tcpConnection;
    private Connection connection;

    public Connected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
       // System.out.println("connected");
    }

    @Override
    public String getCurrentState() {

        return null;
    }

    @Override
    public void connect() {
        if (tcpConnection.getCurrentState().equals("connected")) {
            System.out.println("Error");
        }
    }

    @Override
    public void disconnect() {

    }

    @Override
    public void write(String data) {

    }
}

// END
