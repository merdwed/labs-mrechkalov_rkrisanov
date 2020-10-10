package client.GraphicsUtils;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
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
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DataClasses.Account;
import DataClasses.Location;
import DataClasses.Locales;
import DataClasses.Person;
import DataClasses.Ticket;
import DataClasses.TicketType;
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
        frame.setLocation(50, 50);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // frame.pack();
        frame.setVisible(true);
        // frame.repaint();
    }

    static GlobalWindow instance = new GlobalWindow();

    public static GlobalWindow getInstance() {
        return instance;
    }

    JFrame frame;
    JPanel globalPanel = new JPanel();
    // JPanel workPanel=new JPanel();
    JPanel topPanel = new JPanel();
    JPanel collectionPanel = new JPanel();
    JPanel editPanel = new JPanel();
    JPanel loginCentrPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel commandPanel = new JPanel();
    JPanel workSpacePanel = new JPanel();
    JPanel editCommandPanel = new JPanel();
    JTextField hostTextField, portTextField;
    JTextArea informationTextArea = new JTextArea();
    JButton checkHost = null;
    JButton signOutButton = null;
    JLabel hostLabel, portLabel;
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    HostAndPortActionListener hostAndPortActionListener = new HostAndPortActionListener();

    private void switchToWorkForm() {
        frame.setSize(1200, 800);
        // frame.setLocation(20, 20);
        globalPanel.remove(loginCentrPanel);
        Stream.of(bottomPanel.getComponents()).forEach(bottomPanel::remove);

        // JPanel drawPanel = new JPanel();

        JButton addButton = new JButton("Add");
        signOutButton = new JButton("Sign out");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editTicketForm(null);
            }
        });
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAndShowLoginForm();
                CommandFactory.createNewCommand("sign_out").execute();
                ClientNetMediator.setCurrentAccount(null);
                Dataset.getCurrentInstance().clear();
            }
        });
        informationTextArea.setAutoscrolls(true);
        informationTextArea.setEditable(false);

        collectionPanel.setLayout(new GridBagLayout());
        collectionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        workSpacePanel.setLayout(new BoxLayout(workSpacePanel, BoxLayout.Y_AXIS));
        // workPanel.setLayout(new BorderLayout());

        // drawPanel.add(new JButton("SuperTestButton"));
        
        topPanel.add(new JLabel("Account: "));
        topPanel.add(new JLabel(ClientNetMediator.getCurrentAccount().getLogin()));
        topPanel.add(signOutButton);
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
        
        frame.setSize(500, 300);
        Stream.of(globalPanel.getComponents()).forEach(globalPanel::remove);
        Stream.of(topPanel.getComponents()).forEach(topPanel::remove);
        Stream.of(bottomPanel.getComponents()).forEach(bottomPanel::remove);
        Stream.of(loginCentrPanel.getComponents()).forEach(loginCentrPanel::remove);
        String[] localesString=new String[20];
        Locales.getInstance().getListOfLocales().toArray(localesString);
        JComboBox<String> localeComboBox = new JComboBox<String>(localesString);
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
        
        topPanel.add(localeComboBox);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(hostLabel);
        topPanel.add(hostTextField);
        topPanel.add(portLabel);
        topPanel.add(portTextField);
        topPanel.add(checkHost);
        topPanel.add(Box.createHorizontalGlue());

        JTextField userTextField = new JTextField(15);
        JPasswordField passwordTextField = new JPasswordField(15);

        hostTextField.setMaximumSize(new Dimension(150,20));
        portTextField.setMaximumSize(new Dimension(150,20));
        userTextField.setMaximumSize(new Dimension(150,20));
        passwordTextField.setMaximumSize(new Dimension(150,20));
        
        JPanel centrPanel = new JPanel();
        JPanel centrUserPanel = new JPanel();
        JPanel centrPasswordPanel=new JPanel();
        JLabel userLabel=new JLabel("user:");
        JLabel passwordLabel=new JLabel("password:");
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
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
        localeComboBox.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               Locales.getInstance().resetLocale((String)localeComboBox.getSelectedItem());
            }
            
        });
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command command = CommandFactory.createNewCommand("sign_in");
                CommandParameterDistributor.fillIn(command,  (List<Object>) Arrays.asList(
                        (Object) new Account(userTextField.getText(), new String(passwordTextField.getPassword()))));
                command.execute();
                
                ClientNetMediator.sendAndRecieveFromServer(CommandType.SHOW,
                ClientNetMediator.formThePackageOut(CommandType.SHOW, null)
                );

                switchToWorkForm();
            }
        });
        createAccountButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientNetMediator.setCurrentAccount(new Account("default","default"));
                ClientNetMediator.sendAndRecieveFromServer(CommandType.CREATE_ACCOUNT,
                ClientNetMediator.formThePackageOut(CommandType.CREATE_ACCOUNT, 
                    Arrays.asList(
                    new Account(userTextField.getText(), new String(passwordTextField.getPassword())))
                    ));
                Command command = CommandFactory.createNewCommand("sign_in");
                CommandParameterDistributor.fillIn(command,  (List<Object>) Arrays.asList(
                        (Object) new Account(userTextField.getText(), new String(passwordTextField.getPassword()))));
                command.execute();
                ClientNetMediator.sendAndRecieveFromServer(CommandType.SHOW,
                ClientNetMediator.formThePackageOut(CommandType.SHOW, null)
                );

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
        frame.revalidate();
        frame.repaint();
        
    }
    private void editTicketForm(Long id){
        Stream.of(editCommandPanel.getComponents()).forEach(editCommandPanel::remove);
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

        JTextField ticketNameTextField= new JTextField(15);
        JTextField ticketCoordinatesXTextField= new JTextField(15);
        JTextField ticketCoordinatesYTextField= new JTextField(15);
        JTextField ticketPriceTextField= new JTextField(15);
        JComboBox<TicketType> ticketTypeComboBox = new JComboBox<TicketType>(TicketType.values());
        JTextField ticketPersonHeightTextField= new JTextField(15);
        JTextField ticketPersonWeightTextField= new JTextField(15);
        JTextField ticketPersonLocationXTextField= new JTextField(15);
        JTextField ticketPersonLocationYTextField= new JTextField(15);
        JTextField ticketPersonLocationZTextField= new JTextField(15);
        JTextField ticketPersonLocationNameTextField= new JTextField(15);

        JCheckBox typeCheckBox=new JCheckBox("",true);
        JCheckBox personCheckBox=new JCheckBox("",true);
        JCheckBox locationCheckBox=new JCheckBox("",true);


        Supplier<Boolean> checkCorrectTextFieldsAmdPackTicket =()->{
            Boolean ret=true;
            if(ticketNameTextField.getText()==null || ticketNameTextField.getText().equals("")){
                ticketNameTextField.setBackground(Color.PINK);
                ret= false;
            }
            else{
                ticketNameTextField.setBackground(Color.WHITE);
                ticket.setName(ticketNameTextField.getText());
            }
            try{
                ticket.setCoordinatesX(Float.parseFloat(ticketCoordinatesXTextField.getText()));
                ticketCoordinatesXTextField.setBackground(Color.WHITE);
            }catch(NumberFormatException ex){
                ticketCoordinatesXTextField.setBackground(Color.PINK);
                ret=false;
            }

            try{
                ticket.setCoordinatesY(Long.parseLong(ticketCoordinatesYTextField.getText()));
                ticketCoordinatesYTextField.setBackground(Color.WHITE);
            }catch(NumberFormatException ex){
                ticketCoordinatesYTextField.setBackground(Color.PINK);
                ret=false;
            }

            try{
                Double d=Double.valueOf( ticketPriceTextField.getText());
                if(d==null || d.equals(0D)){
                    ticketPriceTextField.setBackground(Color.PINK);
                    ret=false;
                }
                else{
                    ticketPriceTextField.setBackground(Color.WHITE);
                    ticket.setPrice(d);
                }

            }catch(NumberFormatException ex){
                ticketPriceTextField.setBackground(Color.PINK);
                ret=false;
            }

            if(typeCheckBox.isSelected()){
                ticket.setType((TicketType)ticketTypeComboBox.getSelectedItem());
            }
            else{
                ticket.setType((TicketType)null);
            }
            if(personCheckBox.isSelected()){
                ticket.setPerson(new Person());
                try{
                    ticket.setPersonHeight(Double.parseDouble(ticketPersonHeightTextField.getText()));
                    ticketPersonHeightTextField.setBackground(Color.WHITE);
                }catch(NullPointerException|NumberFormatException ex){
                    ticketPersonHeightTextField.setBackground(Color.PINK);
                    ret=false;
                }

                try{
                    ticket.setPersonWeight(Integer.valueOf(ticketPersonWeightTextField.getText()));
                    ticketPersonWeightTextField.setBackground(Color.WHITE);
                }catch(NullPointerException|NumberFormatException ex){
                    ticketPersonWeightTextField.setBackground(Color.PINK);
                    ret=false;
                }
                if(locationCheckBox.isSelected()){
                    ticket.setPersonLocation(new Location());
                    try{
                        ticket.setPersonLocationX(Double.parseDouble(ticketPersonLocationXTextField.getText()));
                        ticketPersonLocationXTextField.setBackground(Color.WHITE);
                    }catch(NumberFormatException|NullPointerException ex){
                        ticketPersonLocationXTextField.setBackground(Color.PINK);
                        ret=false;
                    }
                    try{
                        ticket.setPersonLocationY(Long.parseLong(ticketPersonLocationXTextField.getText()));
                        ticketPersonLocationXTextField.setBackground(Color.WHITE);
                    }catch(NumberFormatException|NullPointerException ex){
                        ticketPersonLocationXTextField.setBackground(Color.PINK);
                        ret=false;
                    }
                    try{
                        ticket.setPersonLocationZ(Long.parseLong(ticketPersonLocationXTextField.getText()));
                        ticketPersonLocationXTextField.setBackground(Color.WHITE);
                    }catch(NumberFormatException|NullPointerException ex){
                        ticketPersonLocationXTextField.setBackground(Color.PINK);
                        ret=false;
                    }
                    if(ticketPersonLocationNameTextField.getText()==null || ticketPersonLocationNameTextField.getText().length()>852){
                        ret=false;
                        ticketPersonLocationNameTextField.setBackground(Color.PINK);
                    }
                    else{
                        ticketPersonLocationNameTextField.setBackground(Color.WHITE);
                    }
                }
                else{
                    ticket.setPersonLocation(null);
                }
            }
            else{
                ticket.setPerson(null);
            }
            return ret;
        };
        ticketTypeComboBox.setEditable(false);

        
        
        ActionListener personCheckBoxActionListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(personCheckBox.isSelected()==false && locationCheckBox.isSelected()==true)
                {
                    locationCheckBox.setSelected(false);
                    editPanel.remove(ticketPersonLocationXTextField);
                    editPanel.remove(ticketPersonLocationYTextField);
                    editPanel.remove(ticketPersonLocationZTextField);
                    editPanel.remove(ticketPersonLocationNameTextField);
                    
                }
                if(personCheckBox.isSelected()){
                    editConstrains.gridy=7;editConstrains.gridx=4;
                    editPanel.add(ticketPersonHeightTextField,editConstrains);
                    editConstrains.gridy=8;editConstrains.gridx=4;
                    editPanel.add(ticketPersonWeightTextField,editConstrains);
                    //locationCheckBoxActionListener.actionPerformed(new ActionEvent(e.getSource(),1,""));
                    
                }
                else{
                    editPanel.remove(ticketPersonHeightTextField);
                    editPanel.remove(ticketPersonWeightTextField);
                }
                checkCorrectTextFieldsAmdPackTicket.get();
                frame.revalidate();
                frame.repaint();
            }
        };
        ActionListener locationCheckBoxActionListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (!personCheckBox.isSelected() && locationCheckBox.isSelected()){
                    personCheckBox.setSelected(true);
                    personCheckBoxActionListener.actionPerformed(new ActionEvent(e.getSource(),0,""));
                    //return;//костыльно но работает, чтобы не создавалось рекурсии
                }
                if(locationCheckBox.isSelected()){
                    editConstrains.gridy=10;editConstrains.gridx=4;
                    editPanel.add(ticketPersonLocationXTextField,editConstrains);
                    editConstrains.gridy=11;editConstrains.gridx=4;
                    editPanel.add(ticketPersonLocationYTextField,editConstrains);
                    editConstrains.gridy=12;editConstrains.gridx=4;
                    editPanel.add(ticketPersonLocationZTextField,editConstrains);
                    editConstrains.gridy=13;editConstrains.gridx=4;
                    editPanel.add(ticketPersonLocationNameTextField,editConstrains);
                }
                else{
                    editPanel.remove(ticketPersonLocationXTextField);
                    editPanel.remove(ticketPersonLocationYTextField);
                    editPanel.remove(ticketPersonLocationZTextField);
                    editPanel.remove(ticketPersonLocationNameTextField);
                }
                checkCorrectTextFieldsAmdPackTicket.get();
                frame.revalidate();
                frame.repaint();
            }
        };
        ActionListener typeCheckBoxActionListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (typeCheckBox.isSelected()){
                    editConstrains.gridy=5;editConstrains.gridx=4;
                    editPanel.add(ticketTypeComboBox,editConstrains);
                }
                else{
                    editPanel.remove(ticketTypeComboBox);
                }
                checkCorrectTextFieldsAmdPackTicket.get();
                frame.revalidate();
                frame.repaint();
            }
        };
        ActionListener universalAvtionListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                checkCorrectTextFieldsAmdPackTicket.get();
                frame.revalidate();
                frame.repaint();
            }
        };





        editConstrains.gridy=0;editConstrains.gridx=4;
        editPanel.add(ticketNameTextField,editConstrains);
        editConstrains.gridy=2;editConstrains.gridx=4;
        editPanel.add(ticketCoordinatesXTextField,editConstrains);
        editConstrains.gridy=3;editConstrains.gridx=4;
        editPanel.add(ticketCoordinatesYTextField,editConstrains);
        editConstrains.gridy=4;editConstrains.gridx=4;
        editPanel.add(ticketPriceTextField,editConstrains);
       

        
        editConstrains.gridy=5;editConstrains.gridx=0;
        editPanel.add(typeCheckBox,editConstrains);
        editConstrains.gridy=6;editConstrains.gridx=0;
        editPanel.add(personCheckBox,editConstrains);
        editConstrains.gridy=9;editConstrains.gridx=0;
        editPanel.add(locationCheckBox,editConstrains);
        
        personCheckBox.addActionListener(personCheckBoxActionListener);
        locationCheckBox.addActionListener(locationCheckBoxActionListener);
        typeCheckBox.addActionListener(typeCheckBoxActionListener);

        ticketNameTextField.addActionListener(universalAvtionListener);
        ticketCoordinatesYTextField.addActionListener(universalAvtionListener);
        ticketPriceTextField.addActionListener(universalAvtionListener);
        ticketTypeComboBox.addActionListener(universalAvtionListener);           
        ticketPersonHeightTextField.addActionListener(universalAvtionListener);
        ticketPersonWeightTextField.addActionListener(universalAvtionListener);
        ticketPersonLocationXTextField.addActionListener(universalAvtionListener);
        ticketPersonLocationYTextField.addActionListener(universalAvtionListener);
        ticketPersonLocationZTextField.addActionListener(universalAvtionListener);
        ticketPersonLocationNameTextField.addActionListener(universalAvtionListener);           

        if(id==null){
            editCommandPanel.add(addButton,BorderLayout.SOUTH);
            addButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(checkCorrectTextFieldsAmdPackTicket.get()){
                        ;
                        ClientNetMediator.sendAndRecieveFromServer(CommandType.ADD,
                        ClientNetMediator.formThePackageOut(CommandType.ADD,Arrays.asList(ticket)));
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
             ticketNameTextField.setText(ticket.getName());
             ticketCoordinatesXTextField.setText(String.valueOf(ticket.getCoordinatesX()));
             ticketCoordinatesYTextField.setText(String.valueOf(ticket.getCoordinatesY()));
             ticketPriceTextField.setText(ticket.getPrice().toString());
             Person p=ticket.getPerson();
             if(ticket.getType()==null){
                typeCheckBox.setSelected(false);
             }
                else{
                TicketType temp=ticket.getType();
                ticketTypeComboBox.setSelectedItem(temp);
            }
            ticket.setPerson(p);
             if(ticket.getPerson()!=null){
                ticketPersonHeightTextField.setText(String.valueOf(ticket.getPersonHeight()));
                ticketPersonWeightTextField.setText(ticket.getPersonWeight().toString());
                if(ticket.getPersonLocation() != null){
                    ticketPersonLocationXTextField.setText(String.valueOf(ticket.getPersonLocationX()));
                    ticketPersonLocationYTextField.setText(String.valueOf(ticket.getPersonLocationY()));
                    ticketPersonLocationZTextField.setText(String.valueOf(ticket.getPersonLocationZ()));
                    ticketPersonLocationNameTextField.setText(ticket.getPersonLocationName());
                }
                else{
                    locationCheckBox.setSelected(false);
                }
            }
            else{
                locationCheckBox.setSelected(false);
                personCheckBox.setSelected(false);
            }
            editCommandPanel.add(saveButton,BorderLayout.SOUTH);
            editCommandPanel.add(Box.createHorizontalGlue());
            editCommandPanel.add(deleteButton,BorderLayout.SOUTH);
            saveButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(checkCorrectTextFieldsAmdPackTicket.get()){
                        ;
                        ClientNetMediator.sendAndRecieveFromServer(CommandType.UPDATE,
                        ClientNetMediator.formThePackageOut(CommandType.UPDATE,Arrays.asList(id,ticket))
                        );
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
                    ;
                    ClientNetMediator.sendAndRecieveFromServer(CommandType.REMOVE_BY_ID,
                    ClientNetMediator.formThePackageOut(CommandType.REMOVE_BY_ID,Arrays.asList(id))
                    );
                    bottomPanel.remove(editCommandPanel);
                    bottomPanel.add(commandPanel);
                    workSpacePanel.remove(editPanel);
                    frame.revalidate();
                    frame.repaint();
                }
    
            });
        }
        typeCheckBoxActionListener.actionPerformed(new ActionEvent(typeCheckBox,0,""));
        locationCheckBoxActionListener.actionPerformed(new ActionEvent(typeCheckBox,0,""));
        personCheckBoxActionListener.actionPerformed(new ActionEvent(typeCheckBox,0,""));
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
        informationTextArea.setText(Locales.getInstance().getString( string));
    }
    private JLabel newJlabelBorder(String text){
        JLabel temp=new JLabel(text);
        //temp.setEditable(false);
        //temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return temp;
    }
    private Comparator<Ticket> lastComparator=Dataset.idComparator;
    public void update(Comparator<Ticket> comp){
        if(comp==null){
            comp=lastComparator;
            lastComparator=null;
        }
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
                ;
                ClientNetMediator.sendAndRecieveFromServer(CommandType.INFO,
                ClientNetMediator.formThePackageOut(CommandType.INFO, null));
                if(b)
                    ClientNetMediator.setCurrentAccount(null);
            } catch (IllegalArgumentException e1) {
                setHostAndPortColor(Color.PINK);
                System.out.println("exception in actionPerformed");
            }

        }

   }
}
