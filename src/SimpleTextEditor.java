import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class SimpleTextEditor extends JFrame implements ActionListener {
    JFrame frame;
    JTextArea textArea;
    SimpleTextEditor(){
        frame=new JFrame("Simple Text Editor");
        textArea =new JTextArea();
        frame.add(textArea);
        frame.setSize(800,800);
        frame.setVisible(true);
        frame.setBackground(Color.ORANGE);
        JMenuBar menuBar=new JMenuBar();
        JMenu FileMenu=new JMenu("file menu");
        JMenu EditMenu=new JMenu("edit menu");
        menuBar.add(FileMenu);
        menuBar.add(EditMenu);
        JMenuItem Open=new JMenuItem("open file");
        JMenuItem Save=new JMenuItem("save file");
        JMenuItem Print=new JMenuItem("print file");
        JMenuItem New=new JMenuItem("new file");
        FileMenu.add(Open);
        FileMenu.add(Save);
        FileMenu.add(Print);
        FileMenu.add(New);
        JMenuItem Cut=new JMenuItem("Cut");
        JMenuItem Copy=new JMenuItem("Copy");
        JMenuItem Paste=new JMenuItem("Paste");
        JMenuItem Close=new JMenuItem("Close");
        EditMenu.add(Cut);
        EditMenu.add(Copy);
        EditMenu.add(Paste);
        EditMenu.add(Close);
        Copy.addActionListener(this);
        Cut.addActionListener(this);
        Paste.addActionListener(this);
        Save.addActionListener(this);
        Print.addActionListener(this);
        Open.addActionListener(this);
        Close.addActionListener(this);

        frame.setJMenuBar(menuBar);


        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        SimpleTextEditor obj=new SimpleTextEditor();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s == "Cut") {
            //This will cut the selected text and pin that part in the storage
            textArea.cut();
        } else if (s == "Copy") {
            //this will copy the selected area and pin that part in the storage
            textArea.copy();
        } else if (s == "Paste") {
            // this will paste the pin text to the provided area
            textArea.paste();
        }
        else if(s=="save file"){
            JFileChooser filechooser=new JFileChooser("C:");
            int ans=filechooser.showOpenDialog(null);
            if(ans== JFileChooser.APPROVE_OPTION){
                File file=new File(filechooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer= null; try {
                    writer = new BufferedWriter(new FileWriter(file,false));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    writer.write(textArea.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    writer.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }

        }
        else if(s=="print file"){
            try {
                textArea.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
        else if(s=="open file"){
            JFileChooser fileChooser=new JFileChooser("C:");
            int ans=fileChooser.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION){
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    String s1 = "", s2 = "";
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2 = bufferedReader.readLine();
                    while((s1 = bufferedReader.readLine() )!= null) {
                        s2 +=s1+"\n";
                    }
                    textArea.setText(s2);
                } catch (IOException ex){
                    throw new RuntimeException(ex);

                }

            }
        }
        else if(s=="new file"){
            textArea.setText("");
        }
        else if(s=="Close"){
            textArea.setVisible(false);
        }
    }
}