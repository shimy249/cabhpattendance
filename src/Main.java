import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by kevin on 7/11/16.
 */
public class Main {
    private JTextField inputDirField;
    private JButton importBrowseButton;
    private JTextField outputFileField;
    private JButton outputBrowseButton;
    private JList fileList;
    private JButton generateButton;
    private JPanel mainPanel;
    private JTextField attendeeFIleField;
    private JButton attendeeBrowseButton;
    private Path outputPath;
    public JProgressBar progressBar;

    public Main() {
        final JFileChooser fc = new JFileChooser();


        importBrowseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = fc.showOpenDialog(mainPanel);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    //This is where a real application would open the file.
                    inputDirField.setText(file.toString());
                    try {
                        DefaultListModel<String> foundFiles = new DefaultListModel<String>();
                        Files.walk(file.toPath(),1).forEach(filePath -> {
                            if (Files.isRegularFile(filePath)) {
                                try {
                                    if (Files.probeContentType(filePath).equals("text/csv")) {
                                        foundFiles.addElement(filePath.toString());
                                        fileList.setModel(foundFiles);
                                    }
                                }catch (Exception ex){}
                            }
                        });
                    }
                    catch(Exception ex){

                    }
                }
            }
        });


        outputBrowseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser sfc = new JFileChooser();
                int returnVal = sfc.showSaveDialog(mainPanel);
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    outputPath = sfc.getSelectedFile().toPath();
                    outputFileField.setText(sfc.getSelectedFile().toString());
                }
            }
        });
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genReport();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void genReport(){



    }

    class ReportGen extends SwingWorker<Void, Void>{
        @Override
        protected Void doInBackground() throws Exception {

            ListModel model = fileList.getModel();
            for(int i = 0; i < model.getSize(); i++){

            }
            return null;
        }

        @Override
        protected void done() {
            super.done();
        }
    }


}