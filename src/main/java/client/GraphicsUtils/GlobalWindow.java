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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
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
        //globalPanel = new JPanel();
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
    
    // JPanel workPanel=new JPanel();
    JPanel topPanel = new JPanel();
    JPanel workSpacePanel = new JPanel();
    JPanel loginCentrPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    
    
    JPanel editPanel = new JPanel();
    JPanel commandPanel = new JPanel();
    JPanel editCommandPanel = new JPanel();

    
    JTextArea informationTextArea = new JTextArea(2,20);
    
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    
    
    private Boolean isEditNow=false;

    
    class GlobalPanelClass{
        public GlobalPanelClass(){
            
        }
        JPanel globalPanel = new JPanel();
        public void initGlobalPanel() {
        
            globalPanel.setLayout(new BorderLayout());
            globalPanel.add(topPanel, BorderLayout.NORTH);
            globalPanel.add(bottomPanel, BorderLayout.SOUTH);
            frame.add(globalPanel);
            
        }
        public void refreshGlobalPanel(){
            topPanelClass.refreshTopPanel();
            bottomPanelClass.refreshBottomPanel();
            globalPanel.remove(workSpacePanel);
            globalPanel.remove(loginCentrPanel);
            if(ClientNetMediator.getCurrentAccount()==null){
                globalPanel.add(loginCentrPanel,BorderLayout.CENTER);
                loginCentrPanelClass.refreshLoginCentrPanel();
            }
            else{
                globalPanel.add(workSpacePanel,BorderLayout.CENTER);
                workSpacePanelClass.refreshWorkSpacePanel();
            }
            
            frame.revalidate();
            frame.repaint();
        }
        
    }
    
    class TopPanelClass{
        public TopPanelClass(){
         
        }
        private JLabel accountLabel=new JLabel("account");
        private JLabel accountUserLabel=new JLabel();
        private JButton signOutButton = new JButton("sign out");
        private JLabel hostLabel = new JLabel("host");
        private JLabel portLabel = new JLabel("port");
        private JTextField hostTextField = new JTextField("127.0.0.1", 10);
        private JTextField portTextField = new JTextField("8989", 5);
        private HostAndPortActionListener hostAndPortActionListener = new HostAndPortActionListener();
        JButton checkHost = new JButton("check server");
        JComboBox<String> localeComboBox = new JComboBox<String>(Locales.getInstance().getListOfLocales().toArray(new String[0]));  
        public void setHostAndPortColor(Color bg) {
            hostTextField.setBackground(bg);
            portTextField.setBackground(bg); 
        }
        private void initTopPanel(){
            
            topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
            
            hostTextField.addActionListener(hostAndPortActionListener);
            portTextField.addActionListener(hostAndPortActionListener);
            checkHost.addActionListener(hostAndPortActionListener);
            localeComboBox.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                   Locales.getInstance().resetLocale((String)localeComboBox.getSelectedItem());
                   globalPanelClass.refreshGlobalPanel();
                }
            });
            signOutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //createAndShowLoginForm();
                    CommandFactory.createNewCommand("sign_out").execute();
                    ClientNetMediator.setCurrentAccount(null);
                    Dataset.getCurrentInstance().clear();
                    frame.setSize(400,400);
                    globalPanelClass.refreshGlobalPanel();
                }
            });
            
            topPanel.add(localeComboBox);
            topPanel.add(Box.createHorizontalGlue());
            topPanel.add(hostLabel);
            topPanel.add(hostTextField);
            topPanel.add(portLabel);
            topPanel.add(portTextField);
            topPanel.add(checkHost);
            topPanel.add(Box.createHorizontalGlue());
            
            hostTextField.setMaximumSize(new Dimension(150,20));
            portTextField.setMaximumSize(new Dimension(150,20));
    
        }
        private void refreshTopPanel(){
            accountLabel.setText(Locales.getInstance().getString("account") + ": ");
            signOutButton.setText(Locales.getInstance().getString("sign out"));
            hostLabel.setText(Locales.getInstance().getString("host"));
            portLabel.setText(Locales.getInstance().getString("port"));
            checkHost.setText(Locales.getInstance().getString("check server"));
            topPanel.remove(accountLabel);
            topPanel.remove(accountUserLabel);
            topPanel.remove(signOutButton);
            if( ClientNetMediator.getCurrentAccount()==null ){
            }
            else{
                topPanel.add(accountLabel);
                topPanel.add(accountUserLabel);
                topPanel.add(signOutButton);
                accountUserLabel.setText(ClientNetMediator.getCurrentAccount().getLogin());
            }
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
    
    class LoginCentrPanelClass{
        public LoginCentrPanelClass(){
            
        }
        private JTextField userTextField = new JTextField(15);
        private JPasswordField passwordTextField = new JPasswordField(15);
        private JLabel userLabel=new JLabel("user");
        private JLabel passwordLabel=new JLabel("password");
        public String getUser(){
            return userTextField.getText();
        }
        public String getPassword(){
            return new String(passwordTextField.getPassword());
        }
        public void initLoginCentrPanel(){
        JPanel centrPanel = new JPanel();
        JPanel centrUserPanel = new JPanel();
        JPanel centrPasswordPanel=new JPanel();
        
        

        loginCentrPanel.setLayout(new BoxLayout(loginCentrPanel, BoxLayout.X_AXIS));
        centrPanel.setLayout(new BoxLayout(centrPanel,BoxLayout.Y_AXIS));
        centrUserPanel.setLayout(new BoxLayout(centrUserPanel,BoxLayout.X_AXIS));
        centrPasswordPanel.setLayout(new BoxLayout(centrPasswordPanel,BoxLayout.X_AXIS));

        centrUserPanel.add(userLabel);
        centrUserPanel.add(userTextField);
        centrPasswordPanel.add(passwordLabel);
        centrPasswordPanel.add(passwordTextField);
        centrPanel.add(centrUserPanel);
        centrPanel.add(centrPasswordPanel);
        loginCentrPanel.add(Box.createHorizontalGlue());
        loginCentrPanel.add(centrPanel);
        loginCentrPanel.add(Box.createHorizontalGlue());


        centrUserPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        centrPasswordPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        userTextField.setMaximumSize(new Dimension(150,20));
        passwordTextField.setMaximumSize(new Dimension(150,20));
    }
        public void refreshLoginCentrPanel(){
            userLabel.setText(Locales.getInstance().getString("user"));
            passwordLabel.setText(Locales.getInstance().getString("password"));
        }
    
    }
    
    class WorkSpacePanelClass{
        JButton idButton = new JButton("id");
        JButton priceButton = new JButton("price"); 
        JButton nameButton = new JButton("name"); 
        JButton heightButton = new JButton("Height"); 
        JButton weightButton = new JButton("Weight"); 
        JButton locationXButton = new JButton("location X"); 
        JButton locationYButton = new JButton("location Y"); 
        JButton locationZButton = new JButton("location Z"); 
        JButton locationNameButton = new JButton("location name"); 
        JButton coordinatesXButton = new JButton("coordinates X"); 
        JButton coordinatesYButton = new JButton("coordinates Y");
        JButton typeButton = new JButton("Type"); 
        JButton creatorButton = new JButton("creator"); 
        JButton editButton=new JButton("edit");
        JPanel collectionPanel = new JPanel();
        JScrollPane collectionScrollPane = new JScrollPane(collectionPanel);
        JButton saveEditButton = new JButton("Save");
        JButton deleteEditButton = new JButton("Delete");
        JButton addEditButton = new JButton("Add");
        JButton cancelEditButton = new JButton("Cancel");
        public WorkSpacePanelClass(){
            
        }
        public void initWorkSpacePanel(){
            


            idButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    update(Dataset.idComparator);
                }
            });
            priceButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    update(Dataset.priceComparator);
                }
            });
            nameButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                   update(Dataset.nameComparator);
                }
            });
            heightButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    update(Dataset.heightComparator);
                }
            });
            weightButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    update(Dataset.weightComparator);
                }
            });
            locationXButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    update(Dataset.locationXComparator);
                }
            });  
            locationYButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    update(Dataset.locationYComparator);
                }
            });
            locationZButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    update(Dataset.locationXComparator);
                }
            });
            locationNameButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    update(Dataset.locationNameComparator);
                }
            });
            coordinatesXButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    update(Dataset.coordinatesXComparator);
                }
            });
            coordinatesYButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    update(Dataset.coordinatesYComparator);
                }
            });
            typeButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    update(Dataset.ticketTypeComparator);
                }});
            creatorButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    update(Dataset.creatorComparator);
                }});
                
            
            
            collectionPanel.setLayout(new GridBagLayout());
            collectionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            workSpacePanel.setLayout(new BoxLayout(workSpacePanel, BoxLayout.Y_AXIS));
            workSpacePanel.add(collectionScrollPane);
        }
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
            gridBagConstraints.gridy=0;
            gridBagConstraints.gridx=0;  
            collectionPanel.add(idButton,gridBagConstraints);
            
            gridBagConstraints.gridx=1; 
            collectionPanel.add(priceButton,gridBagConstraints);
            
            gridBagConstraints.gridx=2; 
            collectionPanel.add(nameButton,gridBagConstraints);
            
            gridBagConstraints.gridx=3;
            collectionPanel.add(heightButton,gridBagConstraints);
            
            gridBagConstraints.gridx=4; 
            collectionPanel.add(weightButton,gridBagConstraints);

            gridBagConstraints.gridx=5; 
            collectionPanel.add(locationXButton,gridBagConstraints);
            
            gridBagConstraints.gridx=6; 
            collectionPanel.add(locationYButton,gridBagConstraints);
            
            gridBagConstraints.gridx=7; 
            collectionPanel.add(locationZButton,gridBagConstraints);
            
            gridBagConstraints.gridx=8; 
            collectionPanel.add(locationNameButton,gridBagConstraints);
            
            gridBagConstraints.gridx=9; 
            collectionPanel.add(coordinatesXButton,gridBagConstraints);
            
            gridBagConstraints.gridx=10;  
            collectionPanel.add(coordinatesYButton,gridBagConstraints);
            
            gridBagConstraints.gridx=11; 
            collectionPanel.add(typeButton,gridBagConstraints);
            
            gridBagConstraints.gridx=12; 
            collectionPanel.add(creatorButton,gridBagConstraints);
            
            //collectionPanel
            int index=1;
            for(Ticket t:collection){
                gridBagConstraints.gridy=index;
                gridBagConstraints.gridx=0; collectionPanel.add(new JLabel(t.getId().toString()),gridBagConstraints);
                gridBagConstraints.gridx=1; collectionPanel.add(new JLabel(t.getPrice().toString()),gridBagConstraints);
                gridBagConstraints.gridx=2; collectionPanel.add(new JLabel(t.getName()),gridBagConstraints);
                if(t.getPerson()==null){
                    gridBagConstraints.gridx=3; collectionPanel.add(new JLabel(""),gridBagConstraints);
                    gridBagConstraints.gridx=4; collectionPanel.add(new JLabel(""),gridBagConstraints);
                    gridBagConstraints.gridx=5; collectionPanel.add(new JLabel(""),gridBagConstraints);
                    gridBagConstraints.gridx=6; collectionPanel.add(new JLabel(""),gridBagConstraints);
                    gridBagConstraints.gridx=7; collectionPanel.add(new JLabel(""),gridBagConstraints);
                    gridBagConstraints.gridx=8; collectionPanel.add(new JLabel(""),gridBagConstraints);
                }
                else{
                    gridBagConstraints.gridx=3; collectionPanel.add(new JLabel(Double.toString( t.getPersonHeight())),gridBagConstraints);
                    gridBagConstraints.gridx=4; collectionPanel.add(new JLabel(t.getPersonWeight().toString()),gridBagConstraints);
                    if(t.getPersonLocation()==null){
                        gridBagConstraints.gridx=5; collectionPanel.add(new JLabel(""),gridBagConstraints);
                        gridBagConstraints.gridx=6; collectionPanel.add(new JLabel(""),gridBagConstraints);
                        gridBagConstraints.gridx=7; collectionPanel.add(new JLabel(""),gridBagConstraints);
                        gridBagConstraints.gridx=8; collectionPanel.add(new JLabel(""),gridBagConstraints);
                    }    
                    else{
                        gridBagConstraints.gridx=5; collectionPanel.add(new JLabel(Double.toString( t.getPersonLocationX())),gridBagConstraints);
                        gridBagConstraints.gridx=6; collectionPanel.add(new JLabel(Long.toString( t.getPersonLocationY())),gridBagConstraints);
                        gridBagConstraints.gridx=7; collectionPanel.add(new JLabel(Long.toString( t.getPersonLocationZ())),gridBagConstraints);
                        gridBagConstraints.gridx=8; collectionPanel.add(new JLabel(t.getPersonLocationName()),gridBagConstraints);
                    }
                }
                gridBagConstraints.gridx=9; collectionPanel.add(new JLabel(Float.toString( t.getCoordinatesX())),gridBagConstraints);
                gridBagConstraints.gridx=10; collectionPanel.add(new JLabel(Long.toString(t.getCoordinatesY())),gridBagConstraints);
                if(t.getType()==null){
                    gridBagConstraints.gridx=11; collectionPanel.add(new JLabel(""),gridBagConstraints);
                }
                else{
                gridBagConstraints.gridx=11; collectionPanel.add(new JLabel(t.getType().toString()),gridBagConstraints);
                }
                gridBagConstraints.gridx=12; collectionPanel.add(new JLabel(t.getCreator()),gridBagConstraints);
                JButton editButtonTemp = new JButton(editButton.getText());
                editButtonTemp.addActionListener(new ActionListener(){
                    Long id=t.getId();
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        isEditNow=true;
                        editTicketForm(id);
                        globalPanelClass.refreshGlobalPanel();
                    }
                    
                });
                
                gridBagConstraints.gridx=13; collectionPanel.add(editButtonTemp,gridBagConstraints);
                index++;
            }
            frame.revalidate();
            frame.repaint();
        }
        public void editTicketForm(Long id){
            editCommandPanel.removeAll();
            isEditNow=true;
            Ticket ticket;
            if(id==null){
                ticket=new Ticket();
            }
            else{
                ticket=Dataset.getCurrentInstance().getTicket(id);
            }
            Stream.of(editPanel.getComponents()).forEach(editPanel::remove);
            
    
            GridBagConstraints editConstrains = new GridBagConstraints();
            editPanel.setLayout(new GridBagLayout());
            editConstrains.anchor= GridBagConstraints.WEST;
            editConstrains.gridy=0;editConstrains.gridx=1;
            editPanel.add(new JLabel("name"),editConstrains);
            editConstrains.gridy=1;editConstrains.gridx=1;
            editPanel.add(new JLabel("coordinates"),editConstrains);
            editConstrains.gridy=2;editConstrains.gridx=2;
            editPanel.add(new JLabel("x"),editConstrains);
            editConstrains.gridy=3;editConstrains.gridx=2;
            editPanel.add(new JLabel("y"),editConstrains);
            editConstrains.gridy=4;editConstrains.gridx=1;
            editPanel.add(new JLabel("price"),editConstrains);
            editConstrains.gridy=5;editConstrains.gridx=1;
            editPanel.add(new JLabel("type"),editConstrains);
            editConstrains.gridy=6;editConstrains.gridx=1;
            editPanel.add(new JLabel("person"),editConstrains);
            editConstrains.gridy=7;editConstrains.gridx=2;
            editPanel.add(new JLabel("height"),editConstrains);
            editConstrains.gridy=8;editConstrains.gridx=2;
            editPanel.add(new JLabel("qeight"),editConstrains);
            editConstrains.gridy=9;editConstrains.gridx=2;
            editPanel.add(new JLabel("location"),editConstrains);
            editConstrains.gridy=10;editConstrains.gridx=3;
            editPanel.add(new JLabel("x"),editConstrains);
            editConstrains.gridy=11;editConstrains.gridx=3;
            editPanel.add(new JLabel("y"),editConstrains);
            editConstrains.gridy=12;editConstrains.gridx=3;
            editPanel.add(new JLabel("z"),editConstrains);
            editConstrains.gridy=13;editConstrains.gridx=3;
            editPanel.add(new JLabel("name"),editConstrains);
    
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
                            ticket.setPersonLocationY(Long.parseLong(ticketPersonLocationYTextField.getText()));
                            ticketPersonLocationYTextField.setBackground(Color.WHITE);
                        }catch(NumberFormatException|NullPointerException ex){
                            ticketPersonLocationYTextField.setBackground(Color.PINK);
                            ret=false;
                        }
                        try{
                            ticket.setPersonLocationZ(Long.parseLong(ticketPersonLocationZTextField.getText()));
                            ticketPersonLocationZTextField.setBackground(Color.WHITE);
                        }catch(NumberFormatException|NullPointerException ex){
                            ticketPersonLocationZTextField.setBackground(Color.PINK);
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
                        //personCheckBoxActionListener.actionPerformed(new ActionEvent(e.getSource(),0,""));
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
            ActionListener universalActionListener = new ActionListener(){
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
    
            ticketNameTextField.addActionListener(universalActionListener);
            ticketCoordinatesYTextField.addActionListener(universalActionListener);
            ticketPriceTextField.addActionListener(universalActionListener);
            ticketTypeComboBox.addActionListener(universalActionListener);           
            ticketPersonHeightTextField.addActionListener(universalActionListener);
            ticketPersonWeightTextField.addActionListener(universalActionListener);
            ticketPersonLocationXTextField.addActionListener(universalActionListener);
            ticketPersonLocationYTextField.addActionListener(universalActionListener);
            ticketPersonLocationZTextField.addActionListener(universalActionListener);
            ticketPersonLocationNameTextField.addActionListener(universalActionListener);           
    
            if(id==null){
                editCommandPanel.add(addEditButton,BorderLayout.SOUTH);
                addEditButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(checkCorrectTextFieldsAmdPackTicket.get()){
                            ClientNetMediator.sendAndRecieveFromServer(CommandType.ADD,
                            ClientNetMediator.formThePackageOut(CommandType.ADD,Arrays.asList(ticket)));
                            isEditNow=false;
                            globalPanelClass.refreshGlobalPanel();
                        }
                    }
        
                });
            }
            else{
                 ticketNameTextField.setText(ticket.getName());
                 ticketCoordinatesXTextField.setText(String.valueOf(ticket.getCoordinatesX()));
                 ticketCoordinatesYTextField.setText(String.valueOf(ticket.getCoordinatesY()));
                 ticketPriceTextField.setText(ticket.getPrice().toString());
                 //Person p=ticket.getPerson();
                 
                //ticket.setPerson(p);
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
                if(ticket.getType()==null){
                    typeCheckBox.setSelected(false);
                 }
                    else{
                    TicketType temp=ticket.getType();
                    ticketTypeComboBox.setSelectedItem(temp);
                }
                editCommandPanel.add(saveEditButton,BorderLayout.SOUTH);
                editCommandPanel.add(Box.createHorizontalGlue());
                editCommandPanel.add(deleteEditButton,BorderLayout.SOUTH);
                saveEditButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(checkCorrectTextFieldsAmdPackTicket.get()){
                            ClientNetMediator.sendAndRecieveFromServer(CommandType.UPDATE,
                            ClientNetMediator.formThePackageOut(CommandType.UPDATE,Arrays.asList(id,ticket))
                            );
                            isEditNow=false;
                            globalPanelClass.refreshGlobalPanel();
                        }
                    }
        
                });
                deleteEditButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ClientNetMediator.sendAndRecieveFromServer(CommandType.REMOVE_BY_ID,
                        ClientNetMediator.formThePackageOut(CommandType.REMOVE_BY_ID,Arrays.asList(id))
                        );
                        isEditNow=false;
                        globalPanelClass.refreshGlobalPanel();
    
                    }
        
                });
            }
            editCommandPanel.add(cancelEditButton);
            cancelEditButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    isEditNow=false;
                    globalPanelClass.refreshGlobalPanel();
                }
            });
            bottomPanel.add(editCommandPanel);
    
            personCheckBoxActionListener.actionPerformed(new ActionEvent(personCheckBoxActionListener,0,""));
            locationCheckBoxActionListener.actionPerformed(new ActionEvent(locationCheckBoxActionListener,0,""));
            globalPanelClass.refreshGlobalPanel();
            frame.revalidate();
            frame.repaint();
        }
        public void refreshWorkSpacePanel(){
            idButton.setText(Locales.getInstance().getString("id"));
            priceButton.setText(Locales.getInstance().getString("price")); 
            nameButton.setText(Locales.getInstance().getString("name")); 
            heightButton.setText(Locales.getInstance().getString("height")); 
            weightButton.setText(Locales.getInstance().getString("weight")); 
            locationXButton.setText(Locales.getInstance().getString("location X"));
            locationYButton.setText(Locales.getInstance().getString("location Y"));
            locationZButton.setText(Locales.getInstance().getString("location Z"));
            locationNameButton.setText(Locales.getInstance().getString("location") + Locales.getInstance().getString("name"));
            coordinatesXButton.setText(Locales.getInstance().getString("coordinates X"));
            coordinatesYButton.setText(Locales.getInstance().getString("coordinates Y"));
            typeButton.setText(Locales.getInstance().getString("type")); 
            creatorButton.setText(Locales.getInstance().getString("creator")); 
            editButton.setText(Locales.getInstance().getString("edit"));
            saveEditButton.setText(Locales.getInstance().getString("save"));
            deleteEditButton.setText(Locales.getInstance().getString("delete"));
            addEditButton.setText(Locales.getInstance().getString("add"));
            cancelEditButton.setText(Locales.getInstance().getString("cancel"));
            workSpacePanel.remove(editPanel);
            if(isEditNow){
                workSpacePanel.add(editPanel);
            }
            
        }
    }
    
    class BottomPanelClass{
        public BottomPanelClass(){
           
        }
        private JButton signInButton = new JButton("sign in");
        private JButton createAccountButton = new JButton("create account");
        private JScrollPane informationScrollPane=new JScrollPane(informationTextArea);
        private JButton addButton = new JButton("add");
        private JButton clearButton = new JButton("clear");
        private JButton showButton = new JButton("show");
        public void initBottomPanel(){
            bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
            loginBottomPanel.setLayout(new BoxLayout(loginBottomPanel, BoxLayout.X_AXIS));
            workBottomPanel.setLayout(new BoxLayout(workBottomPanel, BoxLayout.X_AXIS));
            
            signInButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Command command = CommandFactory.createNewCommand("sign_in");
                    CommandParameterDistributor.fillIn(command,  (List<Object>) Arrays.asList(
                            (Object) new Account(loginCentrPanelClass.getUser(), loginCentrPanelClass.getPassword())));
                    command.execute();
                    
                    ClientNetMediator.sendAndRecieveFromServer(CommandType.SHOW,
                    ClientNetMediator.formThePackageOut(CommandType.SHOW, null)
                    );
                    frame.setSize(1200,800);
                    globalPanelClass.refreshGlobalPanel();
                }
            });
            createAccountButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    ClientNetMediator.setCurrentAccount(new Account("default","default"));
                    ClientNetMediator.sendAndRecieveFromServer(CommandType.CREATE_ACCOUNT,
                    ClientNetMediator.formThePackageOut(CommandType.CREATE_ACCOUNT, 
                        Arrays.asList(
                        new Account(loginCentrPanelClass.getUser(), loginCentrPanelClass.getPassword()))
                        ));
                    Command command = CommandFactory.createNewCommand("sign_in");
                    CommandParameterDistributor.fillIn(command,  (List<Object>) Arrays.asList(
                            (Object) new Account(loginCentrPanelClass.getUser(), loginCentrPanelClass.getPassword())));
                    command.execute();
                    ClientNetMediator.sendAndRecieveFromServer(CommandType.SHOW,
                    ClientNetMediator.formThePackageOut(CommandType.SHOW, null)
                    );
                    frame.setSize(1200,800);
                    globalPanelClass.refreshGlobalPanel();
                }
            });
            clearButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    ClientNetMediator.sendAndRecieveFromServer(CommandType.CLEAR,
                    ClientNetMediator.formThePackageOut(CommandType.CLEAR, null));
                }
            });
            showButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    ClientNetMediator.sendAndRecieveFromServer(CommandType.SHOW,
                    ClientNetMediator.formThePackageOut(CommandType.SHOW, null));
                }
            });
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isEditNow=true;
                    workSpacePanelClass.editTicketForm(null);
                    globalPanelClass.refreshGlobalPanel();
                }
            });
            
            loginBottomPanel.add(Box.createHorizontalGlue());
            loginBottomPanel.add(signInButton);
            loginBottomPanel.add(Box.createHorizontalGlue());
            loginBottomPanel.add(createAccountButton);
            loginBottomPanel.add(Box.createHorizontalGlue());
    
            workBottomPanel.add(informationScrollPane);
            workBottomPanel.add(Box.createHorizontalGlue());

            commandPanel.add(clearButton);
            commandPanel.add(addButton);
            commandPanel.add(showButton);
        }
        public void refreshBottomPanel(){
            bottomPanel.remove(loginBottomPanel);
            bottomPanel.remove(workBottomPanel);
            signInButton.setText(Locales.getInstance().getString("sign in"));
            createAccountButton.setText(Locales.getInstance().getString("create account"));
            addButton.setText(Locales.getInstance().getString("add"));
            clearButton.setText(Locales.getInstance().getString("clear"));
            showButton.setText(Locales.getInstance().getString("show"));
            if(ClientNetMediator.getCurrentAccount()==null){
               bottomPanel.add(loginBottomPanel);
            }
            else{
                bottomPanel.add(workBottomPanel);
                if(isEditNow){
                    workBottomPanel.remove(commandPanel);
                    workBottomPanel.add(editCommandPanel);
                    
                }
                else{
                    workBottomPanel.remove(editCommandPanel);
                    workBottomPanel.add(commandPanel);
                    
                }
            }
        }
        
    }
    
    GlobalPanelClass globalPanelClass;
    TopPanelClass topPanelClass;
    LoginCentrPanelClass loginCentrPanelClass;
    WorkSpacePanelClass workSpacePanelClass;
    BottomPanelClass bottomPanelClass;
    
    JPanel loginBottomPanel = new JPanel();
    JPanel workBottomPanel = new JPanel();
    
    
    
    public void printInformation(String string){
        informationTextArea.setText(string + "\n" +informationTextArea.getText());
    }
    public void update(Comparator<Ticket> comp){
        workSpacePanelClass.update(comp);
    }
    private Comparator<Ticket> lastComparator=Dataset.idComparator;
    public void setHostAndPortColor(Color bg) {
        topPanelClass.setHostAndPortColor(bg);
    }
    public void init(){
        globalPanelClass = new GlobalPanelClass();
        topPanelClass = new TopPanelClass();
        loginCentrPanelClass = new LoginCentrPanelClass();
        workSpacePanelClass = new WorkSpacePanelClass();
        bottomPanelClass = new BottomPanelClass();
        globalPanelClass.initGlobalPanel();
        topPanelClass.initTopPanel();
        loginCentrPanelClass.initLoginCentrPanel();
        workSpacePanelClass.initWorkSpacePanel();
        bottomPanelClass.initBottomPanel();
        globalPanelClass.refreshGlobalPanel();
        frame.setSize(400, 400);
        frame.revalidate();
        frame.repaint();
    }
    
}
