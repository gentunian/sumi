/**
 * 
 */
package ar.com.tellapic.sumi;

import java.util.ArrayList;

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
public interface UsersInfo {

    public ArrayList<FileInfo> getFiles(String userName);
    
    public void getFiles(int uid);
    
    public ArrayList<SocketInfo> getSockets(String userName);
    
    public void getProcessesId(String userName);
    
    public void getProcessesId(int uid);
    
    public void getProcessesNames(String userName);
    
    public void getProcessesNames(int uid);
    
    public ArrayList<ProcessInfo> getProcesses(String userName);
    
    public void getProcesses(int uid);
}
