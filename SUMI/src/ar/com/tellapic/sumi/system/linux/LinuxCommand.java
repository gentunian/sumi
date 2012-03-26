/**
 * 
 */
package ar.com.tellapic.sumi.system.linux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ar.com.tellapic.sumi.system.SystemCommand;


/**
 *   Copyright (c) 2010 Sebastián Treu.
 *
 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation; version 2 of the License.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 * @author
 *         Sebastian Treu 
 *         sebastian.treu(at)gmail.com
 *
 */
public class LinuxCommand implements SystemCommand {

    private String[] shellCmd = new String[] {"/bin/sh", "-c", ""};
    private BufferedReader cmdResult;

    private static final class Holder {
        public static final LinuxCommand INSTANCE = new LinuxCommand();
    }
    
    public static LinuxCommand getInstance() {
        return Holder.INSTANCE;
    }
    
    private LinuxCommand() {
        cmdResult = null;
    }
    /* (non-Javadoc)
     * @see ar.com.tellapic.sumi.SystemCommand#runCmd(java.lang.String)
     */
    @Override
    public BufferedReader runCmd(String cmd) throws IOException {
        //TODO: Synchronization
        shellCmd[2] = cmd;
        Process p = Runtime.getRuntime().exec(shellCmd);
        cmdResult = new BufferedReader(new InputStreamReader(p.getInputStream()));
        return cmdResult;
    }

}
