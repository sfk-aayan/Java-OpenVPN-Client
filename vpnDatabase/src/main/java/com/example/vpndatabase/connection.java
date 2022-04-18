package com.example.vpndatabase;

import java.io.IOException;

public class connection {
    private final String NEW_LINE = System.getProperty("line.separator");

    public void estalishConnection(boolean choice) {

        if(choice) {

            Runtime rt1 = Runtime.getRuntime();
            try {
                Process pr1 = rt1.exec("C:\\Program Files\\OpenVPN\\bin\\openvpn-gui.exe --command silent_connection 1");
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

            Runtime rt = Runtime.getRuntime();
            try {
                Process pr = rt.exec("C:\\Program Files\\OpenVPN\\bin\\openvpn-gui.exe --connect South_Korea.ovpn");
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }

        else{
            Runtime rt = Runtime.getRuntime();
            try {
                Process pr = rt.exec("C:\\Program Files\\OpenVPN\\bin\\openvpn-gui.exe --command disconnect Japan.ovpn");
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
