package com.polyfrontiere.data.qrcode;

import com.polyfrontiere.data.utils.FileSystemPaths;
import net.glxn.qrgen.javase.QRCode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * This class generate a QrCode using a string given by the user
 * The user can create save and show a QrCode
 */
public class QrCode {

    private String barcodeText;

    public QrCode(String content) {
        this.barcodeText = content;
    }

    /**
     * this methode is used to create QrCode depending on the string that the user entered
     * @return
     * @throws Exception
     */
    public BufferedImage generateQRCodeImage() throws Exception {
        StringEncoder stringEncoder = new StringEncoder(barcodeText);
        ByteArrayOutputStream stream = QRCode
                .from(stringEncoder.encode())
                .withSize(250, 250)
                .stream();
        ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());

        return ImageIO.read(bis);
    }

    /**
     * this methode is used to save an image of the qrcode in ressource folder
     * @param nomImage
     * @throws IOException
     */
    public File saveImage(String nomImage) throws Exception {

        File file = getImage(nomImage);
        ImageIO.write(generateQRCodeImage(), "jpg", file);
        return file;
    }

    public File getImage(String nomImage) throws Exception {
        File file = new File(FileSystemPaths.PATH_QR_IMAGES.getPath() + nomImage + ".jpg");
        if (file.getAbsolutePath().startsWith("/app")) {
        	file = new File("polyfrontiere/" + FileSystemPaths.PATH_QR_IMAGES.getPath() + nomImage + ".jpg");
        }
        return file;
    }



    /**
     * this methode will show the user the qr code in a frame
     * @throws Exception
     */
    public void showQr() throws Exception {
        ImageIcon imageIcon = new ImageIcon(generateQRCodeImage());
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new FlowLayout());

        jFrame.setSize(300, 300);
        JLabel jLabel = new JLabel("QR CODE");

        jLabel.setIcon(imageIcon);
        jFrame.add(jLabel);

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}