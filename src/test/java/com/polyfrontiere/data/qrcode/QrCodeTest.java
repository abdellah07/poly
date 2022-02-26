package com.polyfrontiere.data.qrcode;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.*;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public class QrCodeTest {
    String json;
    QrCode qrCode;
    @Before
    public void setup() throws IOException {
        json = "[{\n" +
                "        \"societeDeTransit\":\"transTOTO\",\n" +
                "        \"conducteur\":{\n" +
                "            \"nom\":\"kherraf\",\n" +
                "            \"prenom\":\"taha\",\n" +
                "            \"mail\":\"kherraftaha@gmail.com\",\n" +
                "            \"passPortID\":\"A1JH99KH\"\n" +
                "        },\n" +
                "        \"listeDeMarchandise\":[\n" +
                "                {\n" +
                "                    \"produit\":\"Banane\",\n" +
                "                    \"quantite\":\"2000\",\n" +
                "                    \"valeurMonnaie\":\"2200\",\n" +
                "                    \"paysOrigine\":\"Bresil\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"produit\":\"Orange\",\n" +
                "                    \"quantite\":\"2000\",\n" +
                "                    \"valeurMonnaie\":\"1600\",\n" +
                "                    \"paysOrigine\":\"Espagne\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"produit\":\"Pomme\",\n" +
                "                    \"quantite\":\"3000\",\n" +
                "                    \"valeurMonnaie\":\"2700\",\n" +
                "                    \"paysOrigine\":\"Espagne\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"produit\":\"Ananas\",\n" +
                "                    \"quantite\":\"3000\",\n" +
                "                    \"valeurMonnaie\":\"3400\",\n" +
                "                    \"paysOrigine\":\"France\"\n" +
                "                }\n" +
                "        ]\n" +
                "    }]";

        qrCode = new QrCode(json);
    }

    @Test
    public void testEncode() throws Exception {
        StringEncoder stringEncoder = new StringEncoder(json);
        String encoded = stringEncoder.encode();
        String decoded = stringEncoder.decode();
        assertFalse(encoded.equals(json));
        assertTrue(decoded.equals(json));
        assertFalse(decoded.equals(encoded));
    }

    @Test
    public void testQrCode() throws Exception {
        ImageIcon imageIcon = new ImageIcon(qrCode.generateQRCodeImage());
        qrCode.saveImage("test");
        JFrame jFrame = new JFrame();

    }
}
