/**
 * 
 */
package utils;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

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
public final class Utils {
	
	/**
	 * 
	 * @param w
	 * @param h
	 * @param path
	 * @return
	 */
	public static Image createIconImage(int w, int h, String path) {
		URL url = Utils.class.getResource(path);
		if (url == null)
			return null;
		Image image = Toolkit.getDefaultToolkit().createImage(url);
		return image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
	}
	
}
