package dev.rneacy.dao;

import dev.rneacy.exception.VendingException;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@Component
public class AuditDAO implements IAuditDAO {
    public void write(String entry) throws VendingException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("audit.txt", true))) {
            writer.append(String.format("[%s] %s\n", LocalDateTime.now(), entry));
        }
        catch(IOException e) {
            throw new VendingException("Could not write to audit file.");
        }
    }
}
