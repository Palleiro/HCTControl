package com.hctrom.romcontrol.otaupdater.otalibary.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by sumit on 5/11/16.
 */

public class ShellExecuter {

    public static final String runAsRoot(String command)
    {

        try {
            // Executes the command.
            Process process = Runtime.getRuntime().exec(command);
            // Reads stdout.
            // NOTE: You can write to stdin of the command using
            //       process.getOutputStream().
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            int read;
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();

            // Waits for the command to finish.
            process.waitFor();

            return output.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e)

        {
            throw new RuntimeException(e);
        }
    }
}
