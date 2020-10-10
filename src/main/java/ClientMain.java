import client.ClientNet.Connection;
import client.GraphicsUtils.GlobalWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;


import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

public class ClientMain {
    
    
    /*private static void createAndShowGUI() {
        //Create and set up the window.
        
        frame.setBounds(200,300,500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel p1=new JPanel();
        JPanel p2=new JPanel();
        JPanel p3=new JPanel();
        
        LayoutManager l1 = new GridLayout(1,2);
        LayoutManager l2 = new GridLayout(3,2);
        LayoutManager l3 = new BorderLayout();
        p1.setLayout(l1);
        p2.setLayout(l2);
        p3.setLayout(l3);
        frame.add(p1);
        p1.add(p2);
        p1.add(p3);
        JButton b1=new JButton("1");
        b1.addActionListener(new ActionListener(){  
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setTitle("title");
                p3.add(new JButton("fife"),BorderLayout.CENTER);
                p2.setBackground(Color.GREEN);
                p3.revalidate();
            }

            }
        );
        p2.add(b1);
        p2.add(new JButton("2"));
        p2.add(new JButton("3"));
        p2.add(new JButton("4"));
        p2.add(new JButton("5"));
        p2.add(new JButton("6"));
        p2.add(new JButton("7"));
        p2.add(new JButton("8"));
        p2.add(new JButton("9"));
        p2.add(new JButton("10"));
        p2.add(new JButton("11"));
        p2.add(new JButton("12"));
        p2.add(new JButton("13"));
        p2.add(new JButton("14"));
        
        p3.add(new JButton("one"),BorderLayout.WEST);
        p3.add(new JButton("two"),BorderLayout.EAST);
        p3.add(new JButton("three"),BorderLayout.NORTH);
        p3.add(new JButton("for"),BorderLayout.SOUTH);
        //p3.add(new JButton("fife"),BorderLayout.CENTER);
        //Add the ubiquitous "Hello World" label.
        
        //Display the window.
        //frame.pack();
        frame.setVisible(true);
    }*/
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GlobalWindow.getInstance().cleanAndInit();
            }
        });
        System.out.println("client ready to start");
        Connection.getInstance().setServerAddress(new InetSocketAddress("127.0.0.1",8989));
        reader=null;
        

        client.ShellUtils.ShellIO.initAndPushSource(null);//null is special value for console. usualy arg is path to file
        client.ShellUtils.ShellInterpretator.run();
    }
}