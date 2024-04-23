package com.etqr.sbqrcodedemo.utils;
import com.etqr.sbqrcodedemo.model.event.Event;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGenerator {

    public static void generateQRCode(Event event) throws WriterException, IOException {
        String qrCodePath = "E:\\ENGINEERING\\FOE-UOR\\SEM 5\\Software Project\\QR";
        String qrCodeName = qrCodePath + event.getEventName() + event.getId() + "-QRCODE.png";
        var qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                "Event Name: " + event.getEventName() + "\n" +
                        "Event ID: " + event.getEventId() + "\n" +
                        "Ticket ID: " + event.getTicketId() + "\n" +
                        "Booking ID: " + event.getBookingId() + "\n" +
                        "User Name: " + event.getUserName() + "\n" +
                        "NIC Number: " + event.getNicNumber() + "\n" +
                        "Mobile No: " + event.getMobileNo() + "\n" +
                        "Email: " + event.getEmail(), BarcodeFormat.QR_CODE, 400, 400);
        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}

