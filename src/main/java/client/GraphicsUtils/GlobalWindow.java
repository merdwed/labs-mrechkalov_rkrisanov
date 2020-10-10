package client.GraphicsUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import java.lang.IllegalArgumentException;

import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DataClasses.Account;
import DataClasses.Ticket;
import DataClasses.CommandTypeUtils.CommandType;
import DataClasses.CommandTypeUtils.CommandTypeInformation;
import DataClasses.Comparators.IdComparator;
import client.ClientCommands.Command;
import client.ClientCommands.CommandFactory;
import client.ClientCommands.CommandParameterDistributor;
import client.ClientNet.ClientNetMediator;
import client.ClientNet.Connection;
import client.ShellUtils.ShellInterpretator;
import javafx.geometry.Dimension2D;
import javafx.scene.layout.Border;

public class GlobalWindow {
    GlobalWindow() {
        frame = new JFrame("Laba vosem Client");
        globalPanel = new JPanel();
        frame.setBounds(200, 300, 500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //frame.pack();
        frame.setVisible(true);
        //frame.repaint();
    }

    static GlobalWindow instance = new GlobalWindow();

    public static GlobalWindow getInstance() {
        return instance;
    }

    JFrame frame;
    JPanel globalPanel = new JPanel();
    //JPanel workPanel=new JPanel();
    JPanel topPanel = new JPanel();
    JPanel collectionPanel = new JPanel();
    JPanel editPanel = new JPanel();
    JPanel loginCentrPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel commandPanel = new JPanel();
    JPanel workSpacePanel = new JPanel();
    JTextField hostTextField, portTextField;
    JTextArea informationTextArea=new JTextArea();
    JButton checkHost;
    JLabel hostLabel, portLabel;
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    HostAndPortActionListener hostAndPortActionListener = new HostAndPortActionListener();
    private void switchToWorkForm() {
        frame.setSize(1200,800);
        frame.setLocation(20, 20);
        globalPanel.remove(loginCentrPanel);
        Stream.of(bottomPanel.getComponents()).forEach(bottomPanel::remove);
        
        //JPanel drawPanel = new JPanel();
        
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                editTicketForm(null);
            }
        });
        informationTextArea.setAutoscrolls(true);
        informationTextArea.setEditable(false);

        collectionPanel.setLayout(new GridBagLayout());
        collectionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        workSpacePanel.setLayout(new BoxLayout(workSpacePanel, BoxLayout.Y_AXIS));
        //workPanel.setLayout(new BorderLayout());




        //drawPanel.add(new JButton("SuperTestButton"));
        workSpacePanel.add(collectionPanel);
        workSpacePanel.add(editPanel);
        commandPanel.add(Box.createHorizontalGlue());
        commandPanel.add(addButton);
        bottomPanel.add(informationTextArea);
        bottomPanel.add(commandPanel);

        //workPanel.add(bottomPanel,BorderLayout.SOUTH);
        //workPanel.add(topPanel,BorderLayout.NORTH);
        //workPanel.add(workSpacePanel,BorderLayout.CENTER);
        globalPanel.add(workSpacePanel,BorderLayout.CENTER);
        //frame.add(workPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void createAndShowLoginForm() {

        globalPanel.setLayout(new BorderLayout());
        
        globalPanel.add(topPanel, BorderLayout.NORTH);

        hostTextField = new JTextField("127.0.0.1", 10);
        portTextField = new JTextField("8989", 5);
        hostLabel = new JLabel("host:");
        portLabel = new JLabel("port:");
        checkHost = new JButton("check server");
        hostTextField.addActionListener(hostAndPortActionListener);
        portTextField.addActionListener(hostAndPortActionListener);
        checkHost.addActionListener(hostAndPortActionListener);
        topPanel.add(hostLabel);
        topPanel.add(hostTextField);
        topPanel.add(portLabel);
        topPanel.add(portTextField);
    
        topPanel.add(checkHost);

        JTextField userTextField = new JTextField(15);
        JPasswordField passwordTextField = new JPasswordField(15);
        userTextField.setMaximumSize(new Dimension(150,20));
        passwordTextField.setMaximumSize(new Dimension(150,20));
        
        JPanel centrPanel = new JPanel();
        JPanel centrUserPanel = new JPanel();
        JPanel centrPasswordPanel=new JPanel();
        JLabel userLabel=new JLabel("user:");
        JLabel passwordLabel=new JLabel("password:");
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        loginCentrPanel.setLayout(new BoxLayout(loginCentrPanel, BoxLayout.X_AXIS));
        centrPanel.setLayout(new BoxLayout(centrPanel,BoxLayout.Y_AXIS));
        centrUserPanel.setLayout(new BoxLayout(centrUserPanel,BoxLayout.X_AXIS));
        centrPasswordPanel.setLayout(new BoxLayout(centrPasswordPanel,BoxLayout.X_AXIS));


      

        centrUserPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        centrPasswordPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        centrUserPanel.add(userLabel);
        centrUserPanel.add(userTextField);
        centrPasswordPanel.add(passwordLabel);
        centrPasswordPanel.add(passwordTextField);
        centrPanel.add(centrUserPanel);
        centrPanel.add(centrPasswordPanel);
        loginCentrPanel.add(Box.createHorizontalGlue());
        loginCentrPanel.add(centrPanel);
        loginCentrPanel.add(Box.createHorizontalGlue());
        globalPanel.add(loginCentrPanel, BorderLayout.CENTER);
        //loginJPanel.add(centrPanel);

        JButton signInButton = new JButton("Sign in");
        JButton createAccountButton = new JButton("Create account");
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command command = CommandFactory.createNewCommand("sign_in");
                CommandParameterDistributor.fillIn(command,  (List<Object>) Arrays.asList(
                        (Object) new Account(userTextField.getText(), new String(passwordTextField.getPassword()))));
                command.execute();
                ClientNetMediator.formThePackageOut(CommandType.SHOW, Arrays.asList());
                ClientNetMediator.sendAndRecieveFromServer(CommandType.SHOW);

                switchToWorkForm();
            }
        });
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(signInButton);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(createAccountButton);
        bottomPanel.add(Box.createHorizontalGlue());
        globalPanel.add(bottomPanel,BorderLayout.SOUTH);
        
        frame.add(globalPanel);
        hostAndPortActionListener.actionPerformed(new ActionEvent((Object)hostTextField,Event.ACTION_EVENT,"127.0.0.1"));
        //globalPanel.revalidate();
    }
    private Boolean checkCorrectTicket(Ticket ticket){
        if(ticket.getName()==null || ticket.getName().equals(""))return false;
        if(ticket.getCoordinates()==null)return false;
        if(ticket.getPrice()==null || ticket.getPrice().equals(0D))return false;
        if(ticket.getPerson()!=null){
            if(ticket.getPersonHeight()==0)return false;
            if(ticket.getPersonWeight()==null || ticket.getPersonWeight().equals(0))return false;
            if(ticket.getPersonLocation()!=null){
                if(ticket.getPersonLocationName()==null || ticket.getPersonLocationName().length()>852)return false;
            }
        }
        return true;
    }
    private JLabel rightAlignmentJLabel(String s){
        
        JLabel temp = new JLabel(s);
       
        return temp;
    }
    private void editTicketForm(Long id){
        JPanel editCommandPanel = new JPanel();
        workSpacePanel.add(editPanel);
        bottomPanel.remove(commandPanel);//удаляем всё кроме информационного табла
        Ticket ticket;
        if(id==null){
            ticket=new Ticket();
        }
        else{
            ticket=Dataset.getCurrentInstance().getTicket(id);
        }
        Stream.of(editPanel.getComponents()).forEach(editPanel::remove);
        JButton saveButton = new JButton("Save");
        JButton deleteButton = new JButton("Delete");
        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");

        GridBagConstraints editConstrains = new GridBagConstraints();
        editPanel.setLayout(new GridBagLayout());
        editConstrains.anchor= GridBagConstraints.WEST;
        editConstrains.gridy=0;editConstrains.gridx=1;
        editPanel.add(new JLabel("Name:"),editConstrains);
        editConstrains.gridy=1;editConstrains.gridx=1;
        editPanel.add(new JLabel("Coordinates:"),editConstrains);
        editConstrains.gridy=2;editConstrains.gridx=2;
        editPanel.add(new JLabel("X:"),editConstrains);
        editConstrains.gridy=3;editConstrains.gridx=2;
        editPanel.add(new JLabel("Y:"),editConstrains);
        editConstrains.gridy=4;editConstrains.gridx=1;
        editPanel.add(new JLabel("Price:"),editConstrains);
        editConstrains.gridy=5;editConstrains.gridx=1;
        editPanel.add(new JLabel("Type:"),editConstrains);
        editConstrains.gridy=6;editConstrains.gridx=1;
        editPanel.add(new JLabel("Person:"),editConstrains);
        editConstrains.gridy=7;editConstrains.gridx=2;
        editPanel.add(new JLabel("Height:"),editConstrains);
        editConstrains.gridy=8;editConstrains.gridx=2;
        editPanel.add(new JLabel("Weight:"),editConstrains);
        editConstrains.gridy=9;editConstrains.gridx=2;
        editPanel.add(new JLabel("Location:"),editConstrains);
        editConstrains.gridy=10;editConstrains.gridx=3;
        editPanel.add(new JLabel("X:"),editConstrains);
        editConstrains.gridy=11;editConstrains.gridx=3;
        editPanel.add(new JLabel("Y:"),editConstrains);
        editConstrains.gridy=12;editConstrains.gridx=3;
        editPanel.add(new JLabel("Z:"),editConstrains);
        editConstrains.gridy=13;editConstrains.gridx=3;
        editPanel.add(new JLabel("Name:"),editConstrains);
        editConstrains.gridy=5;editConstrains.gridx=0;
        editPanel.add(new JCheckBox(),editConstrains);
        editConstrains.gridy=6;editConstrains.gridx=0;
        editPanel.add(new JCheckBox(),editConstrains);
        editConstrains.gridy=9;editConstrains.gridx=0;
        editPanel.add(new JCheckBox(),editConstrains);


        if(id==null){
            editCommandPanel.add(addButton,BorderLayout.SOUTH);
            addButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(checkCorrectTicket(ticket)){
                        ClientNetMediator.formThePackageOut(CommandType.ADD,Arrays.asList(ticket));
                        ClientNetMediator.sendAndRecieveFromServer(CommandType.ADD);
                        bottomPanel.remove(editCommandPanel);
                        bottomPanel.add(commandPanel);
                        workSpacePanel.remove(editPanel);
                        frame.revalidate();
                        frame.repaint();
                    }
                }
    
            });
        }
        else{
            editCommandPanel.add(saveButton,BorderLayout.SOUTH);
            editCommandPanel.add(Box.createHorizontalGlue());
            editCommandPanel.add(deleteButton,BorderLayout.SOUTH);
            saveButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(checkCorrectTicket(ticket)){
                        ClientNetMediator.formThePackageOut(CommandType.UPDATE,Arrays.asList(id,ticket));
                        ClientNetMediator.sendAndRecieveFromServer(CommandType.UPDATE);
                        bottomPanel.remove(editCommandPanel);
                        bottomPanel.add(commandPanel);
                        workSpacePanel.remove(editPanel);
                        frame.revalidate();
                        frame.repaint();
                    }
                }
    
            });
            deleteButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    ClientNetMediator.formThePackageOut(CommandType.REMOVE_BY_ID,Arrays.asList(id));
                    ClientNetMediator.sendAndRecieveFromServer(CommandType.REMOVE_BY_ID);
                    bottomPanel.remove(editCommandPanel);
                    bottomPanel.add(commandPanel);
                    workSpacePanel.remove(editPanel);
                    frame.revalidate();
                    frame.repaint();
                }
    
            });
        }
        editCommandPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                bottomPanel.remove(editCommandPanel);
                bottomPanel.add(commandPanel);
                workSpacePanel.remove(editPanel);
                frame.revalidate();
                frame.repaint();
            }
        });
        bottomPanel.add(editCommandPanel);

        
        
        frame.revalidate();
        frame.repaint();
    }
    public void cleanAndInit() {
        //frame.remove(globalJPanel);
        createAndShowLoginForm();
        frame.revalidate();
    }
    public void printInformation(String string){
        informationTextArea.setText(string);
    }
    private JLabel newJlabelBorder(String text){
        JLabel temp=new JLabel(text);
        //temp.setEditable(false);
        //temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return temp;
    }
    private Comparator<Ticket> lastComparator=Dataset.idComparator;
    public void update(Comparator<Ticket> comp){
        ArrayList<Ticket> collection = Dataset.getCurrentInstance().getSortedArrayList(comp);
        if(comp==lastComparator){
            Collections.reverse(collection);
            lastComparator=null;
        }
        else
            lastComparator=comp;
        Stream.of(collectionPanel.getComponents()).forEach(collectionPanel::remove);
        //gridBagConstraints.weightx = 0.5;
        gridBagConstraints.gridy=0;
        gridBagConstraints.gridx=0; JButton idButton = new JButton("id"); 
        collectionPanel.add(idButton,gridBagConstraints);
        idButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                update(Dataset.idComparator);
            }
        });
        
        gridBagConstraints.gridx=1; JButton priceButton = new JButton("price"); 
        collectionPanel.add(priceButton,gridBagConstraints);
        priceButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                update(Dataset.priceComparator);
            }
        });
        
        gridBagConstraints.gridx=2; JButton nameButton = new JButton("name"); 
        collectionPanel.add(nameButton,gridBagConstraints);
        nameButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                update(Dataset.nameComparator);
            }
        });
        
        gridBagConstraints.gridx=3; JButton heightButton = new JButton("Height"); 
        collectionPanel.add(heightButton,gridBagConstraints);
        heightButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                update(Dataset.heightComparator);
            }
        });
        
        gridBagConstraints.gridx=4; JButton weightButton = new JButton("Weight"); 
        collectionPanel.add(weightButton,gridBagConstraints);
        weightButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                update(Dataset.weightComparator);
            }
        });
        gridBagConstraints.gridx=5; JButton locationXButton = new JButton("location X"); 
        collectionPanel.add(locationXButton,gridBagConstraints);
        locationXButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                update(Dataset.locationXComparator);
            }
        });
        
        gridBagConstraints.gridx=6; JButton locationYButton = new JButton("location Y"); 
        collectionPanel.add(locationYButton,gridBagConstraints);
        locationYButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                update(Dataset.locationYComparator);
            }
        });

        gridBagConstraints.gridx=7; JButton locationZButton = new JButton("location Z"); 
        collectionPanel.add(locationZButton,gridBagConstraints);
        locationZButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                update(Dataset.locationXComparator);
            }
        });

        gridBagConstraints.gridx=8; JButton locationNameButton = new JButton("location name"); 
        collectionPanel.add(locationNameButton,gridBagConstraints);
        locationNameButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                update(Dataset.locationNameComparator);
            }
        });

        gridBagConstraints.gridx=9; JButton coordinatesXButton = new JButton("coordinates X"); 
        collectionPanel.add(coordinatesXButton,gridBagConstraints);
        coordinatesXButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                update(Dataset.coordinatesXComparator);
            }
        });

        gridBagConstraints.gridx=10; JButton coordinatesYButton = new JButton("coordinates Y"); 
        collectionPanel.add(coordinatesYButton,gridBagConstraints);
        coordinatesYButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                update(Dataset.coordinatesYComparator);
            }
        });

        gridBagConstraints.gridx=11; JButton typeButton = new JButton("Type"); 
        collectionPanel.add(typeButton,gridBagConstraints);
        typeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                update(Dataset.ticketTypeComparator);
            }});
        
        gridBagConstraints.gridx=12; JButton creatorButton = new JButton("creator"); 
        collectionPanel.add(creatorButton,gridBagConstraints);
        creatorButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                update(Dataset.creatorComparator);
            }});
        
        //collectionPanel
        int index=1;
        for(Ticket t:collection){
            gridBagConstraints.gridy=index;
            gridBagConstraints.gridx=0; collectionPanel.add(newJlabelBorder(t.getId().toString()),gridBagConstraints);
            gridBagConstraints.gridx=1; collectionPanel.add(newJlabelBorder(t.getPrice().toString()),gridBagConstraints);
            gridBagConstraints.gridx=2; collectionPanel.add(newJlabelBorder(t.getName()),gridBagConstraints);
            if(t.getPerson()==null){
                gridBagConstraints.gridx=3; collectionPanel.add(newJlabelBorder(""),gridBagConstraints);
                gridBagConstraints.gridx=4; collectionPanel.add(newJlabelBorder(""),gridBagConstraints);
                gridBagConstraints.gridx=5; collectionPanel.add(newJlabelBorder(""),gridBagConstraints);
                gridBagConstraints.gridx=6; collectionPanel.add(newJlabelBorder(""),gridBagConstraints);
                gridBagConstraints.gridx=7; collectionPanel.add(newJlabelBorder(""),gridBagConstraints);
                gridBagConstraints.gridx=8; collectionPanel.add(newJlabelBorder(""),gridBagConstraints);
            }
            else{
                gridBagConstraints.gridx=3; collectionPanel.add(newJlabelBorder(Double.toString( t.getPersonHeight())),gridBagConstraints);
                gridBagConstraints.gridx=4; collectionPanel.add(newJlabelBorder(t.getPersonWeight().toString()),gridBagConstraints);
                if(t.getPersonLocation()==null){
                    gridBagConstraints.gridx=5; collectionPanel.add(newJlabelBorder(""),gridBagConstraints);
                    gridBagConstraints.gridx=6; collectionPanel.add(newJlabelBorder(""),gridBagConstraints);
                    gridBagConstraints.gridx=7; collectionPanel.add(newJlabelBorder(""),gridBagConstraints);
                    gridBagConstraints.gridx=8; collectionPanel.add(newJlabelBorder(""),gridBagConstraints);
                }    
                else{
                    gridBagConstraints.gridx=5; collectionPanel.add(newJlabelBorder(Double.toString( t.getPersonLocationX())),gridBagConstraints);
                    gridBagConstraints.gridx=6; collectionPanel.add(newJlabelBorder(Long.toString( t.getPersonLocationY())),gridBagConstraints);
                    gridBagConstraints.gridx=7; collectionPanel.add(newJlabelBorder(Long.toString( t.getPersonLocationZ())),gridBagConstraints);
                    gridBagConstraints.gridx=8; collectionPanel.add(newJlabelBorder(t.getPersonLocationName()),gridBagConstraints);
                }
            }
            gridBagConstraints.gridx=9; collectionPanel.add(newJlabelBorder(Float.toString( t.getCoordinatesX())),gridBagConstraints);
            gridBagConstraints.gridx=10; collectionPanel.add(newJlabelBorder(Long.toString(t.getCoordinatesY())),gridBagConstraints);
            if(t.getType()==null){
                gridBagConstraints.gridx=11; collectionPanel.add(newJlabelBorder(""),gridBagConstraints);
            }
            else{
            gridBagConstraints.gridx=11; collectionPanel.add(newJlabelBorder(t.getType().toString()),gridBagConstraints);
            }
            gridBagConstraints.gridx=12; collectionPanel.add(newJlabelBorder(t.getCreator()),gridBagConstraints);
            JButton editButton=new JButton("edit");
            editButton.addActionListener(new ActionListener(){
                Long id=t.getId();
				@Override
				public void actionPerformed(ActionEvent e) {
                    editTicketForm(id);
				}
                
            });
            gridBagConstraints.gridx=13; collectionPanel.add(editButton,gridBagConstraints);
            index++;
        }
        frame.revalidate();
        frame.repaint();
    }
    public void setHostAndPortColor(Color bg) {
        hostTextField.setBackground(bg);
        portTextField.setBackground(bg); 
    }

    public class HostAndPortActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String var1=hostTextField.getText();
                if(var1==null)var1="";
                hostTextField.selectAll();
                int var2=0;
                
                try{var2=Integer.parseInt(portTextField.getText());}catch(Exception ex){}
                System.out.println(var1 + ":" + var2);
                Connection.getInstance().setServerAddress(new InetSocketAddress(var1,var2));
                Boolean b=ClientNetMediator.getCurrentAccount()==null;
                if(b)
                    ClientNetMediator.setCurrentAccount(new Account("default", "default"));
                ClientNetMediator.formThePackageOut(CommandType.INFO, null);
                ClientNetMediator.sendAndRecieveFromServer(CommandType.INFO);
                if(b)
                    ClientNetMediator.setCurrentAccount(null);
            } catch (IllegalArgumentException e1) {
                setHostAndPortColor(Color.PINK);
                System.out.println("exception in actionPerformed");
            }

        }

   }
}
